import java.io.*;
import java.text.ParseException;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sun.awt.image.ImageWatched;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, ParseException {
        String[] colors = {"#e6194b", "#3cb44b", "#ffe119", "#4363d8", "#f58231", "#911eb4", "#46f0f0", "#f032e6", "#bcf60c", "#fabebe", "#008080", "#e6beff", "#9a6324", "#fffac8", "#800000", "#aaffc3", "#808000", "#ffd8b1", "#000075", "#808080", "#ffffff", "#000000"};
        int toolbarHeight = 25;

        Match main = new Match("match1.json");

        //Creating an image
        Image image = new Image(new FileInputStream(System.getProperty("user.dir")+"/src/Erangel_Main_High_Res_SMALL.jpg"));
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();

        //Map
        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        //Phases
        LinkedList<Phase> phases = new LinkedList<>();
        for(int i = 0; i < 11; i++){
            WritableImage writableImage = new WritableImage(width,height);
            PixelWriter pixelWriter = writableImage.getPixelWriter();
            phases.add(new Phase(writableImage,pixelWriter,i));
        }

        //Map coloring
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(x, y);
                //Setting the color to the writable image
                writer.setColor(x, y, color);
            }
        }

        // Safety zones
        LinkedList<Circle> safetyzones = new LinkedList<>();
        LinkedList<Event> events = main.getEvents();
        for(Event event:events){
            if(event.getCommon().getIsGame() == 0) continue;
            Color color = Color.valueOf(colors[(int) event.getCommon().getIsGame()]);
            int x = (Math.round(event.getLocation().getX()) / 1000);
            int y = (Math.round(event.getLocation().getY()) / 1000);
            for(Phase p:phases){
                if(p.getPhase() == event.getCommon().getIsGame()){
                    p.getPixelWriter().setColor(x, y, color);
                }
            }

            // Draw just safetyzones on stable phases (0.5, 1.5, ... n.5 = moving safetyzone)
            if(event.getEventType() == Event.EventType.LogGameStatePeriodic && event.getCommon().getIsGame() % 1 == 0){ // && event.getCommon().getIsGame() >= 0.5
                Boolean add = true;
                Circle circle = new Circle(event.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),event.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()+toolbarHeight,event.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
                for(Circle c:safetyzones){
                    if(c.getCenterX() == circle.getCenterX() && c.getCenterY() == circle.getCenterY() && c.getRadius() == circle.getRadius()){
                        add = false;
                        break;
                    }
                }
                if(add) {
                    circle.setStroke(Color.WHITE);
                    circle.setFill(Color.TRANSPARENT);
                    circle.setStrokeWidth(1);
                    circle.setId(event.getCommon().getIsGame()+"");
                    safetyzones.add(circle);
                }
            }
        }

        // Add safetyzone to kill
        for(Event event:events){
            for(Circle safetyzone:safetyzones){
                if(event.getEventType() == Event.EventType.LogPlayerKill && Double.parseDouble(safetyzone.getId()) == event.getCommon().getIsGame()){
                        event.getLogPlayerKill().setSafezone(safetyzone);
                }
            }
        }

        //Setting the image view
        ImageView imageView = new ImageView(wImage);

        //Setting the position of the image
        imageView.setX(0);
        imageView.setY(toolbarHeight);

        //setting the fit height and width of the image view
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        // Setting the preserve ratio of the image view (???)
        imageView.setPreserveRatio(true);

        // Group object
        Group root = new Group(imageView);

        // Phases
        for(Phase p:phases){
            ImageView imgview = new ImageView(p.getWritableImage());
            imgview.setX(0);
            imgview.setY(toolbarHeight);
            imgview.setFitHeight(height);
            imgview.setFitWidth(width);
            imgview.setPreserveRatio(true);
            imgview.setId(p.getPhase()+"");
            root.getChildren().add(imgview);
        }
        // Circles
        for(Circle circle:safetyzones) {
            if(Double.parseDouble(circle.getId()) % 1 != 0) continue;
            Circle c = new Circle(circle.getCenterX()/1000,circle.getCenterY()/1000+toolbarHeight,circle.getRadius()/1000);
            c.setStroke(Color.WHITE);
            c.setFill(Color.TRANSPARENT);
            c.setStrokeWidth(1);
            c.setId(circle.getId());
            root.getChildren().add(c);
        }

        // MAIN SCENE // <---- NOTICE ME SENPAI!
        Scene scene = new Scene(root, 919, 819);

        // Phase buttons
        for(int i = 0; i <= 10; i++){
            Button button = new Button("Phase "+i);
            button.setPrefWidth(100);
            button.setPrefHeight(35);
            button.setLayoutX(819);
            button.setLayoutY(35*i+toolbarHeight);
            button.setStyle("-fx-border-color: #777; -fx-border-width: 2px; -fx-background-color: "+colors[i]+";");
            button.setId(i+"");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    for(Node node:root.getChildren()){
                        if(node.getId() != null && node.getId().equals(((Button) e.getSource()).getText().split(" ")[1]+".0")){
                            if (node.isVisible()) {
                                node.setVisible(false);
                                ((Button) e.getSource()).setStyle("-fx-border-color: #777; -fx-border-width: 2px; -fx-background-color: #FFFFFF;");
                            } else {
                                ((Button) e.getSource()).setStyle("-fx-border-color: #777; -fx-border-width: 2px; -fx-background-color: "+colors[Integer.parseInt(((Button) e.getSource()).getId())]+";");
                                node.setVisible(true);
                            }
                        }
                    }

                }
            });
            root.getChildren().add(button);
        }
        //Menus
        MenuBar menuBar = new MenuBar();
            Menu filemenu = new Menu("File");
                Menu menuItemNew = new Menu("New");
                    MenuItem menuitemNewMatch = new MenuItem("New Match");
                    menuitemNewMatch.setOnAction(e -> {
                        Label secondLabel = new Label("I'm a Label on new Window");

                        StackPane secondaryLayout = new StackPane();
                        secondaryLayout.getChildren().add(secondLabel);

                        Scene secondScene = new Scene(secondaryLayout, 230, 100);
                        // New window (Stage)
                        Stage newWindow = new Stage();
                        newWindow.setTitle("Second Stage");
                        newWindow.setScene(secondScene);

                        // Set position of second window, related to primary window.
                        newWindow.setX(stage.getX() + 200);
                        newWindow.setY(stage.getY() + 100);

                        newWindow.show();
                    });
                    menuitemNewMatch.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
                    menuItemNew.getItems().add(menuitemNewMatch);
                MenuItem menuItemOpen = new MenuItem("Open");
                menuItemOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
                MenuItem menuItemQuit = new MenuItem("Quit");
                menuItemOpen.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
                filemenu.getItems().add(menuItemNew);
                filemenu.getItems().add(menuItemOpen);
                filemenu.getItems().add(menuItemQuit);
            Menu editmenu = new Menu("Edit");
                MenuItem menuItemCurrent = new MenuItem("Current match info");
                menuItemCurrent.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN));
                editmenu.getItems().add(menuItemCurrent);
            menuBar.getMenus().addAll(filemenu,editmenu);

        VBox vBox = new VBox(menuBar);
        root.getChildren().add(menuBar);

        //Setting title to the Stage
        stage.setTitle("PUBGsim by Marko Loponen");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

        LinkedList<Double> deathDistances = new LinkedList<>();
        // Death calculating
        for(Event event:events){
            if(event.getEventType() == Event.EventType.LogPlayerKill){ //  && event.getCommon().getIsGame() % 1 == 0
                if(event.getLogPlayerKill().getSafezone() == null || event.getLogPlayerKill().getSafezone() == null) continue;
                deathDistances.add(distance(
                        event.getLogPlayerKill().getVictim().getLocation().getX(),
                        event.getLogPlayerKill().getVictim().getLocation().getY(),
                        event.getLogPlayerKill().getSafezone().getCenterX(),
                        event.getLogPlayerKill().getSafezone().getCenterY()
                ) / event.getLogPlayerKill().getSafezone().getRadius());
            }
        }
        int centerSafetyzoneDeathAmount = 0;
        int outerSafetyzoneDeathAmount = 0;
        for(Double d:deathDistances){
            if(d > 0.5) outerSafetyzoneDeathAmount++;
            else centerSafetyzoneDeathAmount++;
        }
        System.out.println("centerSafetyzoneDeathAmount : " + centerSafetyzoneDeathAmount);
        System.out.println("outerSafetyzoneDeathAmount : " + outerSafetyzoneDeathAmount);
    }
    public static void main(String args[]) {
        launch(args);
    }

    public double distance(float x1, float y1, double x2, double y2){
        return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
    }

}
