import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Instance {

    Match main;
    LinkedList<Circle> safetyzones;
    LinkedList<LogGameStatePeriodic> gameStatePeriodics;
    LinkedList<Event> events;

    public Instance(String file) throws IOException {
        this.main = new Match("match1.json");
        this.events = main.getEvents();
        this.safetyzones = makeSafetyzones();
        this.sortAll();
    }

    private LinkedList<Circle> makeSafetyzones(){
        LinkedList<Circle> temp = new LinkedList<>();
        for(Event event : this.events){
            if(event.getEventType() == Event.EventType.LogGameStatePeriodic && event.getCommon().getIsGame() % 1 == 0){ //  && event.getCommon().getIsGame() % 1 == 0 // && event.getCommon().getIsGame() >= 0.5
                Circle circle = new Circle(event.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getX(),event.getLogGameStatePeriodic().getGameState().getSafetyZonePosition().getY(),event.getLogGameStatePeriodic().getGameState().getSafetyZoneRadius());
                circle.setStroke(Color.WHITE);
                circle.setFill(Color.TRANSPARENT);
                circle.setStrokeWidth(1);
                circle.setId(event.getCommon().getIsGame()+"");
                temp.add(circle);
                gameStatePeriodics.add(event.getLogGameStatePeriodic());
            }
        }
        return temp;
    }

    private void sortAll(){
        Collections.sort(events, (e1, e2) -> {
            if(e1.getTime() < e2.getTime()) return -1;
            if(e1.getTime() == e2.getTime()) return 0;
            return 1;
        }
        );
        Collections.sort(safetyzones, (c1, c2) -> {
            if(Double.parseDouble(c1.getId()) < Double.parseDouble(c2.getId())) return -1;
            if(Double.parseDouble(c1.getId()) == Double.parseDouble(c2.getId())) return 0;
            return 1;
        }
        );
    }

    // GETTERS

    public Match getMain() {
        return main;
    }

    public LinkedList<Circle> getSafetyzones() {
        return safetyzones;
    }

    public LinkedList<Event> getEvents() {
        return events;
    }
}
