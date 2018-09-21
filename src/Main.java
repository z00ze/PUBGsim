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
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).eventType != Event.EventType.LogPlayerAttack) continue;
            Color attacker = new Color(1,0,0,1);
            int ax = (Math.round(events.get(i).getLogPlayerAttack().getAttacker().getLocation().getX()) / 1000);
            int ay = (Math.round(events.get(i).getLogPlayerAttack().getAttacker().getLocation().getY()) / 1000);
            writer_blank.setColor(ax,ay, attacker);
        }
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).eventType != Event.EventType.LogPlayerTakeDamage) continue;
            Color victim = new Color(1,1,0,1);
            int vx = (Math.round(events.get(i).getLogPlayerTakeDamage().getVictim().getLocation().getX()) / 1000);
            int vy = (Math.round(events.get(i).getLogPlayerTakeDamage().getVictim().getLocation().getY()) / 1000);
            writer_blank.setColor(vx,vy, victim);
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
