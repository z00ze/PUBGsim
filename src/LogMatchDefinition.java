public class LogMatchDefinition {
    // "MatchId": string, "PingQuality": string
    String MatchId;
    String PingQuality;

    public LogMatchDefinition(String matchId, String pingQuality) {
        MatchId = matchId;
        PingQuality = pingQuality;
    }

    public String getMatchId() {
        return MatchId;
    }

    public String getPingQuality() {
        return PingQuality;
    }
}
