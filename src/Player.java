import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Player {
    String accountId;
    LinkedList<Event> events = new LinkedList<>();
    Event phase1 = new Event();
    Event phase2 = new Event();
    Event phase3 = new Event();
    Event phase4 = new Event();
    Event phase5 = new Event();
    Event phase6 = new Event();
    Event phase7 = new Event();
    Event phase8 = new Event();
    Event phase9 = new Event();
    Event phase10 = new Event();
    boolean[] ischeck = {false,false,false,false,false,false,false,false,false,false};
    boolean[] insideSafetyzone = {false,false,false,false,false,false,false,false,false,false};

    public Player(String name) {
        this.accountId = name;
    }

    public Player(String accountId, Event event, Event phase1, Event phase2, Event phase3, Event phase4, Event phase5, Event phase6, Event phase7, Event phase8, Event phase9, Event phase10) {
        this.accountId = accountId;
        addToEvents(event);
        this.phase1 = phase1;
        this.phase2 = phase2;
        this.phase3 = phase3;
        this.phase4 = phase4;
        this.phase5 = phase5;
        this.phase6 = phase6;
        this.phase7 = phase7;
        this.phase8 = phase8;
        this.phase9 = phase9;
        this.phase10 = phase10;
    }

    public String getAccountId() {
        return accountId;
    }

    public void addToEvents(Event event) {
        this.events.add(event);
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public boolean[] getInsideSafetyzone(){

        Collections.sort(this.events, (e1, e2) -> {
                    if(e1.getTime() < e2.getTime()) return 1;
                    if(e1.getTime() == e2.getTime()) return 0;
                    return -1;
                }
        );
        for(Event event:events) {
            if(event.getAccountId().equals("") || event.getLocation().getX() == 0) continue;
            if(event.getCommon().getIsGame() % 1 == 0.5 || event.getCommon().getIsGame() < 1){
                int eventIsGame = 0;
                if(event.getCommon().getIsGame() > 1){
                    eventIsGame = (int) (event.getCommon().getIsGame()-0.5);
                }

                if(getPhase(eventIsGame).isDummy()) continue;
                Event phase = getPhase(eventIsGame);

                if(!ischeck[eventIsGame]){
                    ischeck[eventIsGame] = true;
                    if(event.getEventType() == Event.EventType.LogPlayerKill){
                        this.insideSafetyzone[eventIsGame] = false;
                    }
                    else {
                        this.insideSafetyzone[eventIsGame] = (distance(
                                event.getLocation().getX(),
                                event.getLocation().getY(),
                                phase.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                                phase.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                        ) <= phase.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
                    }
                }
            }
        }
        return this.insideSafetyzone;
    }

    public Event getPhase(int i){
        switch (i){
            case 0 :
                return phase1;
            case 1 :
                return phase2;
            case 2 :
                return phase3;
            case 3 :
                return phase4;
            case 4 :
                return phase5;
            case 5 :
                return phase6;
            case 6 :
                return phase7;
            case 7 :
                return phase8;
            case 8 :
                return phase9;
            case 9 :
                return phase10;
        }
        return new Event();
    }

    public double distance(float x1, float y1, double x2, double y2){
        return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
    }

    @Override
    public String toString() {
        return "Player{" +
                "accountId='" + accountId + '\'' +
                ", events=" + events +
                ", phase1=" + phase1 +
                ", phase2=" + phase2 +
                ", phase3=" + phase3 +
                ", phase4=" + phase4 +
                ", phase5=" + phase5 +
                ", phase6=" + phase6 +
                ", phase7=" + phase7 +
                ", phase8=" + phase8 +
                ", phase9=" + phase9 +
                ", phase10=" + phase10 +
                ", insideSafetyzone=" + Arrays.toString(insideSafetyzone) +
                '}';
    }
}
