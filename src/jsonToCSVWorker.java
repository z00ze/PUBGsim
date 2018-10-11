import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class jsonToCSVWorker {

    public jsonToCSVWorker(String s, String threadname) throws IOException {

        BufferedWriter bw = null;
        FileWriter fw = null;
        BufferedWriter bw2 = null;
        FileWriter fw2 = null;

        try {
            fw = new FileWriter("results/data.csv", true);
            bw = new BufferedWriter(fw);
            //bw.write("MatchId,IsCustom,EventType,Time,AccountId,X,Y,IsGame\n");

            fw2 = new FileWriter("results/zone.csv", true);
            bw2 = new BufferedWriter(fw2);
            //bw2.write("MatchId,IsCustom,Time,IsGame,X,Y,R,nAlive\n");

            Match match = new Match("json/" + s);
            boolean isCustomGame = match.isCustomGame();
            LinkedList<Event> events = match.getEvents();
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getEventType() == Event.EventType.LogArmorDestroy ||
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
                if (events.get(i).getEventType() == Event.EventType.LogGameStatePeriodic) {
                    bw2.write(match.getMatchId() + "," + isCustomGame + "," + events.get(i).getTime() + "," + events.get(i).getCommon().getIsGame() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getSafetyZoneRadius() + "," + events.get(i).getLogGameStatePeriodic().getGameState().getNumAlivePlayers() + "\n");
                }
                bw.flush();
                bw2.flush();
            }
            fw.flush();
            fw.close();
            fw2.flush();
            fw2.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
        System.out.println("Done! " + threadname);
    }
}
