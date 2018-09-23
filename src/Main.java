import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.util.Comparator;
import java.util.LinkedList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import sun.awt.image.ImageWatched;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, ParseException {
        String[] colors = {"#e6194b", "#3cb44b", "#ffe119", "#4363d8", "#f58231", "#911eb4", "#46f0f0", "#f032e6", "#bcf60c", "#fabebe", "#008080", "#e6beff", "#9a6324", "#fffac8", "#800000", "#aaffc3", "#808000", "#ffd8b1", "#000075", "#808080", "#ffffff", "#000000"};
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

        //Phases
        LinkedList<PixelWriter> pixelWriters = new LinkedList<>();
        LinkedList<WritableImage> writableImages = new LinkedList<>();
        for(int i = 0; i < 10; i++){
            writableImages.add(new WritableImage(width, height));
            pixelWriters.add(writableImages.get(writableImages.size()-1).getPixelWriter());
        }

        //Map
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
            Color color = Color.valueOf(colors[(int) event.getCommon().getIsGame()]);
            int x = (Math.round(event.getLocation().getX()) / 1000);
            int y = (Math.round(event.getLocation().getY()) / 1000);
            pixelWriters.get((int) event.getCommon().getIsGame()).setColor(x, y, color);

            if(event.getEventType() == Event.EventType.LogGameStatePeriodic && event.getCommon().getIsGame() % 1 == 0){
                Boolean add = true;
                Circle circle = new Circle(event.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX()/1000,event.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()/1000,event.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius()/1000);
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

        //Setting the image view
        ImageView imageView = new ImageView(wImage);

        //Setting the position of the image
        imageView.setX(0);
        imageView.setY(0);

        //setting the fit height and width of the image view
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        // Group object
        Group root = new Group(imageView);

        for(WritableImage writableImage:writableImages){
            ImageView imgview = new ImageView(writableImage);
            imgview.setX(0);
            imgview.setY(0);
            imgview.setFitHeight(height);
            imgview.setFitWidth(width);
            imgview.setPreserveRatio(true);
            root.getChildren().add(imgview);
        }

        for(Circle circle:safetyzones) {
            root.getChildren().add(circle);
        }

        Scene scene = new Scene(root, 919, 819);

        for(int i = 0; i <= 10; i++){
            Button button = new Button("Phase "+i);
            button.setPrefWidth(100);
            button.setPrefHeight(35);
            button.setLayoutX(819);
            button.setLayoutY(35*i);
            button.setStyle("-fx-border-color: #777; -fx-border-width: 1px; -fx-background-color: #FFF; -fx-background-color: "+colors[i]+";");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    /*Node node = root.getChildren().get((Integer.parseInt( ((Button) e.getSource()).getText().split(" ")[1]))+1).;
                    //Node circle = root.lookup("#" + ((Button) e.getSource()).getText().split(" ")[1] + ".0");
                    Node circle = root.getChildren().get((Integer.parseInt( ((Button) e.getSource()).getText().split(" ")[1]))+10);*/
                    for(Node n:root.getChildren()){
                        if( n.getId().equals(((Button) e.getSource()).getText().split(" ")[1]+".0")
                            &&

                        ){
                            if (node.isVisible() && circle.isVisible()) {
                                node.setVisible(false);
                                circle.setVisible(false);
                            } else {
                                node.setVisible(true);
                                circle.setVisible(true);
                            }
                        }
                    }

                }
            });
            root.getChildren().add(button);
        }





        //Setting title to the Stage
        stage.setTitle("PUBGsim by Marko Loponen");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
}
