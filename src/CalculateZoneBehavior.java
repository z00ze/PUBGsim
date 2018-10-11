import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.xml.bind.Element;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.zip.GZIPInputStream;

public class CalculateZoneBehavior {
    public static void main(String args[]) throws IOException {
        //getTelemetry();
        calculateAll();
    }
    public static void fetchData() throws IOException {
        ZoneCalculator zoneCalculator = new ZoneCalculator("match1.json");
        LinkedList<Player> players = zoneCalculator.getPlayers();
        for(int i = 0; i < players.size(); i++){
            getMatch(players.get(i).getAccountId());
        }
    }

    public static void getMatch(String accountId) throws IOException {
        URL url = new URL("https://api.pubg.com/shards/pc-eu/players/" + accountId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwNzg4MGVkMC02MmE2LTAxMzYtZDhjYS02MzU5NjllMjMyYjYiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTMwODExMzk3LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6InowMHplIn0.UF3TGr26p931RdRHLgcOjBMr8HpWXOeG4vylZxqDUns");
        conn.setRequestProperty("Accept", "application/vnd.api+json");
        conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        try
        {
            Thread.sleep(6000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        JsonParser parser = new JsonParser();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            JsonElement jsonTree = parser.parse(inputLine);
            if(jsonTree.isJsonObject()){
                if(jsonTree.getAsJsonObject().has("data") &&
                        jsonTree.getAsJsonObject().get("data").getAsJsonObject().has("relationships") &&
                        jsonTree.getAsJsonObject().get("data").getAsJsonObject().get("relationships").getAsJsonObject().has("matches") &&
                        jsonTree.getAsJsonObject().get("data").getAsJsonObject().get("relationships").getAsJsonObject().get("matches").getAsJsonObject().has("data")){
                    JsonArray matches = jsonTree.getAsJsonObject().get("data").getAsJsonObject().get("relationships").getAsJsonObject().get("matches").getAsJsonObject().get("data").getAsJsonArray();
                    int count = 0;
                    for(JsonElement element : matches){
                        if(count == 10) break;

                        if(element.getAsJsonObject().has("id")){
                            File file = new File("matches/"+element.getAsJsonObject().get("id").getAsString() + ".json");
                            if(!file.exists()){
                                saveMatch(element.getAsJsonObject().get("id").getAsString());
                            }
                            else{
                                System.out.println(element.getAsJsonObject().get("id").getAsString() + " exists already!");
                            }
                        }
                        count++;
                    }
                }
            }
        }
        in.close();
    }
    public static void saveMatch(String matchId) throws IOException {
        URL url = new URL("https://api.pubg.com/shards/pc-eu/matches/" + matchId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwNzg4MGVkMC02MmE2LTAxMzYtZDhjYS02MzU5NjllMjMyYjYiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTMwODExMzk3LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6InowMHplIn0.UF3TGr26p931RdRHLgcOjBMr8HpWXOeG4vylZxqDUns");
        conn.setRequestProperty("Accept", "application/vnd.api+json");
        conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        try
        {
            Thread.sleep(6000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            PrintWriter out = new PrintWriter("matches/"+matchId+".json");
            out.println(inputLine);
            System.out.println("matches/"+matchId+".json has been writen.");
            out.close();
        }
    }

    public static void getTelemetry() throws IOException {
        File f = new File("matches/");
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
        JsonParser parser = new JsonParser();
        for(String s : names){
            String json = openJsonFile("matches/"+s);
            JsonElement jsonTree = parser.parse(json);
            if(jsonTree.isJsonObject()) {
                JsonObject rootdata = jsonTree.getAsJsonObject();
                if(rootdata.get("data").getAsJsonObject().has("relationships") &&
                    rootdata.get("data").getAsJsonObject().get("relationships").getAsJsonObject().has("assets") &&
                    rootdata.get("data").getAsJsonObject().get("relationships").getAsJsonObject().get("assets").getAsJsonObject().has("data") &&
                    rootdata.get("data").getAsJsonObject().get("relationships").getAsJsonObject().get("assets").getAsJsonObject().has("data") &&
                    rootdata.get("data").getAsJsonObject().get("relationships").getAsJsonObject().get("assets").getAsJsonObject().get("data").isJsonArray()
                    ){
                    JsonArray data = rootdata.get("data").getAsJsonObject().get("relationships").getAsJsonObject().get("assets").getAsJsonObject().get("data").getAsJsonArray();
                    for(JsonElement element : data){
                        if(element.getAsJsonObject().has("id") &&
                            element.getAsJsonObject().has("type") &&
                            element.getAsJsonObject().get("type").getAsString().equals("asset")
                        ){
                            String telemetryID = element.getAsJsonObject().get("id").getAsString();
                            if(rootdata.has("included") && rootdata.get("included").isJsonArray()){
                                JsonArray included = rootdata.get("included").getAsJsonArray();
                                for(JsonElement includedElement : included){

                                    if(includedElement.getAsJsonObject().has("type") &&
                                        includedElement.getAsJsonObject().get("type").getAsString().equals("asset") &&
                                        includedElement.getAsJsonObject().has("id") &&
                                        telemetryID.equals(includedElement.getAsJsonObject().get("id").getAsString())
                                    ){
                                        JsonElement telemetryElement = includedElement;

                                        if(telemetryElement.getAsJsonObject().has("attributes") &&
                                            telemetryElement.getAsJsonObject().get("attributes").getAsJsonObject().has("URL")
                                            ){
                                            getMatchTelemetry(telemetryElement.getAsJsonObject().get("attributes").getAsJsonObject().get("URL").getAsString(), telemetryID);
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
        }
    }

    private static void getMatchTelemetry(String urll, String telemetryId) throws IOException {
        File file = new File("json/"+telemetryId+".json");
        if(!file.exists()) {
            URL url = new URL(urll);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/vnd.api+json");
            conn.setRequestProperty("Accept-Encoding", "gzip");
            conn.getInputStream();

            BufferedReader in = null;
            if ("gzip".equals(conn.getContentEncoding())) {
                try {
                    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
                }
                catch(Exception e){
                    System.out.println(e);
                    return;
                }
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }


            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                PrintWriter out = new PrintWriter("json/" + telemetryId + ".json");
                out.println(inputLine);
                System.out.println("json/" + telemetryId + ".json has been writen.");
                out.close();
            }
        }
        else{
            System.out.println("json/"+telemetryId+".json exists already.");
        }
    }

    public static String openJsonFile(String s) throws IOException {
        File file = new File(s);

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder everything = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null){
            everything.append(st);
        }
        return everything.toString();
    }

    public static void calculateAll() throws IOException {
        File f = new File("json/");
        ArrayList<String> names = new ArrayList<>(Arrays.asList(f.list()));


        BufferedWriter bw = null;
        FileWriter fw = null;
        BufferedWriter bw2 = null;
        FileWriter fw2 = null;

        try
        {
            int count = 0;

            fw = new FileWriter("results/data.csv",true);
            bw = new BufferedWriter(fw);
            bw.write("MatchId,IsCustom,EventType,Time,AccountId,X,Y,IsGame\n");

            fw2 = new FileWriter("results/zone.csv",true);
            bw2 = new BufferedWriter(fw2);
            bw2.write("MatchId,IsCustom,Time,IsGame,X,Y,R,nAlive\n");

            for(String s : names){

                if(count == 10) break;
                Match match = new Match("json/" + s);
                boolean isCustomGame = match.isCustomGame();
                System.out.println(count + " MatchId: "+match.getMatchId() );
                LinkedList<Event> events = match.getEvents();
                for(int i = 0; i < events.size(); i++){
                        if( events.get(i).getEventType() == Event.EventType.LogArmorDestroy ||
                                events.get(i).getEventType() == Event.EventType.LogItemAttach ||
                                events.get(i).getEventType() == Event.EventType.LogItemDetach ||
                                events.get(i).getEventType() == Event.EventType.LogItemDrop ||
                                events.get(i).getEventType() == Event.EventType.LogItemPickup ||
                                events.get(i).getEventType() == Event.EventType.LogItemUnequip ||
                                events.get(i).getEventType() == Event.EventType.LogItemUse ||
                                events.get(i).getEventType() == Event.EventType.LogPlayerAttack ||
                                events.get(i).getEventType() == Event.EventType.LogPlayerKill ||
                                events.get(i).getEventType() == Event.EventType.LogPlayerMakeGroggy ||
                                events.get(i).getEventType() == Event.EventType.LogPlayerPosition ||
                                events.get(i).getEventType() == Event.EventType.LogPlayerRevive ||
                                events.get(i).getEventType() == Event.EventType.LogPlayerTakeDamage ||
                                events.get(i).getEventType() == Event.EventType.LogSwimEnd ||
                                events.get(i).getEventType() == Event.EventType.LogSwimStart ||
                                events.get(i).getEventType() == Event.EventType.LogVehicleDestroy ||
                                events.get(i).getEventType() == Event.EventType.LogVehicleLeave ||
                                events.get(i).getEventType() == Event.EventType.LogVehicleRide ||
                                events.get(i).getEventType() == Event.EventType.LogMatchEnd
                        ) {
                            bw.write(match.getMatchId() + "," + isCustomGame + "," + events.get(i).getEventType() + "," + events.get(i).getTime() + "," + events.get(i).getAccountId() + "," + events.get(i).getLocation().getX() + "," + events.get(i).getLocation().getY() + "," + events.get(i).getCommon().getIsGame() + "\n");
                        }
                        if(events.get(i).getEventType() == Event.EventType.LogGameStatePeriodic) {
                            bw2.write(match.getMatchId() + "," + isCustomGame + "," + events.get(i).getTime() + "," + events.get(i).getCommon().getIsGame() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getSafetyZoneRadius() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getNumAlivePlayers() + "\n");
                        }
                    bw.flush();
                    bw2.flush();
                }

/*                ZoneCalculator z = new ZoneCalculator("json/"+s);
                System.out.println(count);
                //if(count == 10) break;
                fw.write(z.getMatchId()+",");
                for(int i = 0; i < z.getInside().length; i++){
                    if(i == z.getInside().length-1){
                        fw.write(z.getInside()[i]+"\n");
                    }
                    else{
                        fw.write(z.getInside()[i]+",");
                    }
                }*/
                count++;
            }
            fw.flush();
            fw.close();
            fw2.flush();
            fw2.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
