import java.io.*;
import java.text.ParseException;
import java.util.Comparator;
import java.util.LinkedList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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

        //Creating a writable image
        WritableImage wImage = new WritableImage(width, height);
        WritableImage wImage_blank = new WritableImage(width, height);

        //Reading color from the loaded image
        PixelReader pixelReader = image.getPixelReader();
        PixelReader pixelReader_blank = image.getPixelReader();

        //getting the pixel writer
        PixelWriter writer = wImage.getPixelWriter();
        PixelWriter writer_blank = wImage_blank.getPixelWriter();

        //Reading the color of the image
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(x, y);
                //Setting the color to the writable image
                writer.setColor(x, y, color);
            }
        }
        LinkedList<Event> events = main.getEvents();
        for(Event event:events){
            if(event.getCommon().getIsGame() > 1) continue;;
            Color color;
            color = Color.valueOf(colors[(int) event.getCommon().getIsGame()]);

            int x = (Math.round(event.getLocation().getX()) / 1000);
            int y = (Math.round(event.getLocation().getY()) / 1000);
            writer_blank.setColor(x,y, color);
        }

        //Setting the image view
        ImageView imageView = new ImageView(wImage);
        ImageView imageView_blank = new ImageView(wImage_blank);

        //Setting the position of the image
        imageView.setX(0);
        imageView.setY(0);
        imageView_blank.setX(0);
        imageView_blank.setY(0);

        //setting the fit height and width of the image view
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView_blank.setFitHeight(height);
        imageView_blank.setFitWidth(width);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);
        imageView_blank.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView,imageView_blank);

        //Creating a scene object
        Scene scene = new Scene(root, 800, 800);

        //Setting title to the Stage
        stage.setTitle("PUBGsim - Marko Loponen");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
}
