import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Date;

public class Match {
    // {"MatchId":"match.bro.official.2018-09.eu.squad-fpp.2018.09.05.ea4b9c42-aa10-49a0-a25f-68dc3c28cb61","PingQuality":"high","_D":"2018-09-05T18:46:45.8551644Z","_T":"LogMatchDefinition"}
    String MatchId;
    String PingQuality;
    Long _D; // epoch milli
    String _T;
    LinkedList<Event> events = new LinkedList<>();
    Player longestPlayer;

    public Match(String filename) throws IOException {

        JsonParser parser = new JsonParser();
        String json = openJsonFile(filename);
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonArray()) {
            JsonArray rootdata = jsonTree.getAsJsonArray();
            for (JsonElement element:rootdata) {
                if(element.isJsonObject()){
                    if(element.getAsJsonObject().get("_T").equals("LogMatchDefinition")){
                        this.MatchId = element.getAsJsonObject().get("MatchId").getAsString();
                        this.PingQuality = element.getAsJsonObject().get("PingQuality").getAsString();
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_INSTANT;
                        TemporalAccessor accessor = timeFormatter.parse(element.getAsJsonObject().get("_D").getAsString());
                        this._D = Instant.from(accessor).toEpochMilli();
                        this._T = element.getAsJsonObject().get("_T").getAsString();
                    }
                    Event event = new Event(element.getAsJsonObject());
                    this.events.add(event);
                }
            }
            this.events.sort(Comparator.comparing(Event::getTime));
        }
        else {
            System.out.println("FAIL");
        }

    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public String getMatchId() {
        return MatchId;
    }

    public String getPingQuality() {
        return PingQuality;
    }

    public Long get_D() {
        return _D;
    }

    public String get_T() {
        return _T;
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
}
