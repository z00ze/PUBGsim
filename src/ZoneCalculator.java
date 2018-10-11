import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ZoneCalculator {

    Match main;
    String matchId = "";
    LinkedList<Player> players = new LinkedList<>();
    LinkedList<Event> events;
    LinkedList<Event> phases;
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
    double[] inside = new double[10];

    public ZoneCalculator(String file) throws IOException {
        this.main = new Match(file);
        this.matchId = this.main.getMatchId();
        this.events = main.getEvents();
        this.phases = new LinkedList<>();
        setPhases();
        setSafetyZones();


    }

    public String getMatchId() {
        return this.matchId;
    }

    private void setSafetyZones() {

        for(int i = 0; i < players.size(); i++){
            boolean[] inzone = players.get(i).getInsideSafetyzone();
            for(int a = 0; a < inzone.length; a++){
                if(inzone[a]){
                    this.inside[a] = this.inside[a] + 1;
                }
            }
        }
        for(int i = 0; i < this.inside.length; i++) {
            if (!getPhase(i).isDummy()){
                /*if(this.inside[i] > getPhase(i).getLogGameStatePeriodic().getGameState().getNumAlivePlayers()){
                    System.out.println(inside[i] + " " + getPhase(i).getLogGameStatePeriodic().getGameState().getNumAlivePlayers());
                }*/
                this.inside[i] = (getPhase(i).getLogGameStatePeriodic().getGameState().getNumAlivePlayers() == 0) ? 0 : this.inside[i] / getPhase(i).getLogGameStatePeriodic().getGameState().getNumAlivePlayers();
                if(this.inside[i] > 1) this.inside[i] = 1;
            }
        }
    }

    private void setPhases() {
        Collections.sort(this.events, (e1, e2) -> {
                    if(e1.getTime() < e2.getTime()) return -1;
                    if(e1.getTime() == e2.getTime()) return 0;
                    return 1;
                }
        );
        boolean[] ischeck = {false,false,false,false,false,false,false,false,false,false};
        for (Event event : this.events) {

            if (event.getEventType() == Event.EventType.LogGameStatePeriodic && event.getCommon().getIsGame() % 1 == 0) {
                if(event.getCommon().getIsGame() == 1.0 && !ischeck[0]){
                    ischeck[0] = true;
                    this.phase1 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 2.0 && !ischeck[1]){
                    ischeck[1] = true;
                    this.phase2 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 3.0 && !ischeck[2]){
                    ischeck[2] = true;
                    this.phase3 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 4.0 && !ischeck[3]){
                    ischeck[3] = true;
                    this.phase4 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 5.0 && !ischeck[4]){
                    ischeck[4] = true;
                    this.phase5 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 6.0 && !ischeck[5]){
                    ischeck[5] = true;
                    this.phase6 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 7.0 && !ischeck[6]){
                    ischeck[6] = true;
                    this.phase7 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 8.0 && !ischeck[7]){
                    ischeck[7] = true;
                    this.phase8 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 9.0 && !ischeck[8]){
                    ischeck[8] = true;
                    this.phase9 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 10.0 && !ischeck[9]){
                    ischeck[9] = true;
                    this.phase10 = event;
                    continue;
                }
            }
        }

        for(Event event:events) {
            if (event.getAccountId() == "") continue;
            if (players.size() > 0) {
                boolean add = true;
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getAccountId().equals(event.getAccountId())) {
                        players.get(i).addToEvents(event);
                        add = false;
                    }
                }
                if (add) {
                    players.add(new Player(event.getAccountId(),
                            event,
                            this.phase1,
                            this.phase2,
                            this.phase3,
                            this.phase4,
                            this.phase5,
                            this.phase6,
                            this.phase7,
                            this.phase8,
                            this.phase9,
                            this.phase10
                    ));
                }
            } else {
                players.add(new Player(event.getAccountId(),
                        event,
                        this.phase1,
                        this.phase2,
                        this.phase3,
                        this.phase4,
                        this.phase5,
                        this.phase6,
                        this.phase7,
                        this.phase8,
                        this.phase9,
                        this.phase10
                ));
            }
        }
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

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public double[] getInside() {
        return this.inside;
    }

    @Override
    public String toString() {
        return "ZoneCalculator{" +
                "phase1=" + phase1.getCommon().getIsGame() +
                ", phase2=" + phase2.getCommon().getIsGame() +
                ", phase3=" + phase3.getCommon().getIsGame() +
                ", phase4=" + phase4.getCommon().getIsGame() +
                ", phase5=" + phase5.getCommon().getIsGame() +
                ", phase6=" + phase6.getCommon().getIsGame() +
                ", phase7=" + phase7.getCommon().getIsGame() +
                ", phase8=" + phase8.getCommon().getIsGame() +
                ", phase9=" + phase9.getCommon().getIsGame() +
                '}';
    }
}
