import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ZoneCalculator {

    Match main;
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
        this.events = main.getEvents();
        this.phases = new LinkedList<>();
        setPhases();
        for(int i = 0; i < players.size(); i++){
            boolean[] inzone = players.get(i).getInsideSafetyzone();
            for(int a = 0; a < inzone.length; a++){
                if(inzone[a]) inside[a]++;
            }
        }
        for(int i = 0; i < inside.length; i++){
            switch (i){
                case 0:
                    double insidezone = inside[i];
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase1().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / ((insidezone != 0) ? insidezone : 1) ;
                    break;
                case 1:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase2().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 2:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase3().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 3:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase4().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 4:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase5().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 5:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase6().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 6:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase7().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 7:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase8().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 8:
                    inside[i] = (inside[i] == 0) ? 0 : players.get(0).getPhase9().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
                case 9:
                    //inside[i] = (inside[i] == 0) ? 0 : inside[i] / players.get(0).getPhase10().getLogGameStatePeriodic().getGameState().getNumAlivePlayers() / inside[i];
                    break;
            }
            for(double d : inside){
                System.out.println(d);
            }

        }
        System.out.println("DONE!");
    }

    private void setPhases() {
        for (Event event : this.events) {

            if (event.getEventType() == Event.EventType.LogGameStatePeriodic && event.getCommon().getIsGame() % 1 == 0) {

                if(event.getCommon().getIsGame() == 1.0 && (phase1.getTime() > event.getTime()) ){
                    this.phase1 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 2.0 && (phase2.getTime() > event.getTime()) ){
                    this.phase2 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 3.0 && (phase3.getTime() > event.getTime()) ){
                    this.phase3 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 4.0 && (phase4.getTime() > event.getTime()) ){
                    this.phase4 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 5.0 && (phase5.getTime() > event.getTime()) ){
                    this.phase5 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 6.0 && (phase6.getTime() > event.getTime()) ){
                    this.phase6 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 7.0 && (phase7.getTime() > event.getTime()) ){
                    this.phase7 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 8.0 && (phase8.getTime() > event.getTime()) ){
                    this.phase8 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 9.0 && (phase9.getTime() > event.getTime()) ){
                    this.phase9 = event;
                    continue;
                }
                if(event.getCommon().getIsGame() == 10.0 && (phase10.getTime() > event.getTime()) ){
                    this.phase10 = event;
                    continue;
                }
            }
        }
        for(Event event:events){
            if(event.getAccountId() == "") continue;
            if(players.size() > 0) {
                boolean add = true;
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getAccountId().equals(event.getAccountId())) {
                        players.get(i).addToEvents(event);
                        add = false;
                    }
                }
                if(add){
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
            else{
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
