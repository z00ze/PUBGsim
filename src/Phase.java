import java.util.LinkedList;

public class Phase {

    double phase;
    LinkedList<Event> events = new LinkedList<>();

    Phase(double phase){
        this.phase = phase;
    }
    public LinkedList<Event> getEvents(){
        return events;
    }
    public void addEvent(Event event){
        this.events.add(event);
    }
}
