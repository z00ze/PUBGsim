import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Player {
    String accountId;
    LinkedList<Event> events = new LinkedList<>();
    Event phase1;
    Event phase2;
    Event phase3;
    Event phase4;
    Event phase5;
    Event phase6;
    Event phase7;
    Event phase8;
    Event phase9;
    Event phase10;
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
                    if(e1.getTime() < e2.getTime()) return -1;
                    if(e1.getTime() == e2.getTime()) return 0;
                    return 1;
                }
        );
        for(Event event:events){
            if(event.getTime() >= phase1.getTime() && !this.insideSafetyzone[0]){
                this.insideSafetyzone[0] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase1.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase1.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase1.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase2.getTime() && !this.insideSafetyzone[1]){
                this.insideSafetyzone[1] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase2.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase2.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase2.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase3.getTime() && !this.insideSafetyzone[2]){
                this.insideSafetyzone[2] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase3.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase3.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase3.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase4.getTime() && !this.insideSafetyzone[3]){
                this.insideSafetyzone[3] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase4.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase4.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase4.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase5.getTime() && !this.insideSafetyzone[4]){
                this.insideSafetyzone[4] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase6.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase6.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase6.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase6.getTime() && !this.insideSafetyzone[5]){
                this.insideSafetyzone[0] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase6.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase6.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase6.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase7.getTime() && !this.insideSafetyzone[6]){
                this.insideSafetyzone[6] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase7.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase7.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase7.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase8.getTime() && !this.insideSafetyzone[7]){
                this.insideSafetyzone[7] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase8.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase8.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase8.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase9.getTime() && !this.insideSafetyzone[8]){
                this.insideSafetyzone[8] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase9.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase9.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase9.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
            if(event.getTime() >= phase10.getTime() && !this.insideSafetyzone[9]){
                this.insideSafetyzone[9] = (distance(
                        event.getLocation().getX(),
                        event.getLocation().getY(),
                        phase10.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),
                        phase10.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY()
                ) < phase10.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
            }
        }

        return this.insideSafetyzone;
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
