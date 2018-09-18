import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.LinkedList;
import java.util.Date;

public class Match {
    // {"MatchId":"match.bro.official.2018-09.eu.squad-fpp.2018.09.05.ea4b9c42-aa10-49a0-a25f-68dc3c28cb61","PingQuality":"high","_D":"2018-09-05T18:46:45.8551644Z","_T":"LogMatchDefinition"}
    String MatchId;
    String PingQuality;
    Date _D;
    String _T;
    LinkedList<Event> events = new LinkedList<>();

    public Match(String MatchId, String PinQuality, String _D, String _T){
        this.MatchId = MatchId;
        this.PingQuality = PinQuality;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(_D);
        this._D = Date.from(Instant.from(accessor));
        this._T = _T;
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

    public Date get_D() {
        return _D;
    }

    public String get_T() {
        return _T;
    }
}
