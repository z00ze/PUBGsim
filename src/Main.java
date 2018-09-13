import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Map mainmap = new Map("erangel");


        LinkedList<Death> deaths = new LinkedList<Death>();

        JsonParser parser = new JsonParser();
        String json = openJsonFile("match1.json");
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonArray()) {
            JsonArray rootdata = jsonTree.getAsJsonArray();
            for (int i = 0; i < rootdata.size(); i++) {
                if(rootdata.get(i).isJsonObject()){

                        if(rootdata.get(i).getAsJsonObject().has("common") && rootdata.get(i).getAsJsonObject().get("common").getAsJsonObject().has("isGame")){
                            double phase = rootdata.get(i).getAsJsonObject().get("common").getAsJsonObject().get("isGame").getAsDouble();
                            // ignoring phases before 1st bluezone
                            if(phase > 0.4){
                                String event = rootdata.get(i).getAsJsonObject().get("_T").getAsString();
                                if(rootdata.get(i).getAsJsonObject().has("character") && rootdata.get(i).getAsJsonObject().get("character").getAsJsonObject().has("location")) {
                                    float x = rootdata.get(i).getAsJsonObject().get("character").getAsJsonObject().get("location").getAsJsonObject().get("x").getAsFloat();
                                    float y = rootdata.get(i).getAsJsonObject().get("character").getAsJsonObject().get("location").getAsJsonObject().get("y").getAsFloat();
                                    float z = rootdata.get(i).getAsJsonObject().get("character").getAsJsonObject().get("location").getAsJsonObject().get("z").getAsFloat();
                                    for (int a = 0; a < mainmap.phases.size(); a++) {
                                        if (mainmap.phases.get(a).phase == phase) {
                                            mainmap.phases.get(a).addEvent(new Event(event, x, y, z));
                                        }
                                    }
                                }
                                rootdata.get(i).getAsJsonObject().get("common").getAsJsonObject().get("isGame");
                            }

                        }

                        if(rootdata.get(i).getAsJsonObject().get("_T").getAsString().equals("LogPlayerKill")){
                            deaths.add(new Death(rootdata.get(i).getAsJsonObject().get("victim").getAsJsonObject().get(("location")).getAsJsonObject().get("x").getAsFloat(),rootdata.get(i).getAsJsonObject().get("victim").getAsJsonObject().get(("location")).getAsJsonObject().get("y").getAsFloat()));
                        }
                }
            }
        }
        else{
            System.out.println("FAIL");
        }
        LinkedList<Event> events = mainmap.phases.get(6).getEvents();
        for(int i = 0; i < events.size(); i++){
            System.out.println(events.get(i).getType());
            System.out.println(events.get(i).toString());
        }
        // END OF JSON
        //Creating an image
        Image image = new Image(new FileInputStream(System.getProperty("user.dir")+"/src/Erangel_Main_High_Res.jpg"));
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();

        //Creating a writable image
        WritableImage wImage = new WritableImage(width, height);

        //Reading color from the loaded image
        PixelReader pixelReader = image.getPixelReader();

        //getting the pixel writer
        PixelWriter writer = wImage.getPixelWriter();

        //Reading the color of the image
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(x, y);
                //Setting the color to the writable image
                writer.setColor(x, y, color);
            }
        }
        for(int i = 0; i < events.size(); i++){
            Color color = new Color(1,0,0,1);
            System.out.println(events.get(i).getX());
            System.out.println(events.get(i).getY());
            int x = (Math.round(events.get(i).getX()) / 1000);
            int y = (Math.round(events.get(i).getY()) / 1000);
            writer.setColor(x,y, color);
        }

        //Setting the image view
        ImageView imageView = new ImageView(wImage);

        //Setting the position of the image
        imageView.setX(0);
        imageView.setY(0);

        //setting the fit height and width of the image view
        imageView.setFitHeight(8192);
        imageView.setFitWidth(8192);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        Scene scene = new Scene(root, 816, 816);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
    public static String openJsonFile(String s) throws IOException {
        File file = new File(System.getProperty("user.dir")+"/json/"+s);

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder everything = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null){
            everything.append(st);
        }
        return everything.toString();
    }
}
