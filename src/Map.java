import java.util.LinkedList;

public class Map {

    String mapname;
    LinkedList<Phase> phases = new LinkedList<>();
    LinkedList<Event> drawnEvents = new LinkedList<>();

    Map(String map){
        double i = 0;
        while(i <= 10){
            phases.add(new Phase(i));
            i += 0.5;
        }
        if(map.toLowerCase() == "erangel"){
            this.mapname = "Erangel_Main_High_Res";
        }
        else mapname = "";
    }
}
