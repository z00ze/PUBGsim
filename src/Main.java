import java.io.*;
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


        JsonParser parser = new JsonParser();
        String json = openJsonFile("match1.json");
        JsonElement jsonTree = parser.parse(json);
        int count = 0;
        if(jsonTree.isJsonArray()) {
            JsonArray rootdata = jsonTree.getAsJsonArray();
            for (int i = 0; i < rootdata.size(); i++) {
                if(rootdata.get(i).isJsonObject()){
                    JsonObject obj = rootdata.get(i).getAsJsonObject();
                    if(obj.has("common") && obj.get("common").getAsJsonObject().has("isGame")){
                        if((obj.get("common").getAsJsonObject().get("isGame").getAsFloat() > 0.5)){
                            if(obj.has("_T") && obj.get("_T").getAsString().equals("LogItemAttach")){
                                if(obj.has("character") && obj.get("character").getAsJsonObject().has("name")){
                                    if(obj.get("character").getAsJsonObject().get("name").getAsString().equals("MaXZoiDZ")){
                                        Event event = new Event(rootdata.get(i).getAsJsonObject());
                                        System.out.println(event.getLogItemAttach().toString());
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        else{
            System.out.println("FAIL");
        }
        System.out.println(count);
        // END OF JSON
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
/*        for(int i = 0; i < events.size(); i++){
            Color color = new Color(1,0,0,1);
            System.out.println(events.get(i).getX());
            System.out.println(events.get(i).getY());
            int x = (Math.round(events.get(i).getX()) / 1000);
            int y = (Math.round(events.get(i).getY()) / 1000);
            writer_blank.setColor(x,y, color);
        }*/

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
