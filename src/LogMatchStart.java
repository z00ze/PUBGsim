import java.util.LinkedList;

public class LogMatchStart {
    // "mapName": string, "weatherId": string, "characters": [{Character}, ...], "cameraViewBehaviour": string, "teamSize": int, "isCustomGame": bool, // PC only "isEventMode": bool, // PC only "blueZoneCustomOptions": string
    String mapName;
    String weatherId;
    LinkedList<Character> characters;
    String cameraViewBehaviour;
    int teamSize;
    boolean isCustomGame;
    boolean isEventMode;
    String blueZoneCustomOptions;

    public LogMatchStart(String mapName, String weatherId, LinkedList<Character> characters, String cameraViewBehaviour, int teamSize, boolean isCustomGame, boolean isEventMode, String blueZoneCustomOptions) {
        this.mapName = mapName;
        this.weatherId = weatherId;
        this.characters = characters;
        this.cameraViewBehaviour = cameraViewBehaviour;
        this.teamSize = teamSize;
        this.isCustomGame = isCustomGame;
        this.isEventMode = isEventMode;
        this.blueZoneCustomOptions = blueZoneCustomOptions;
    }

    public String getMapName() {
        return mapName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public LinkedList<Character> getCharacters() {
        return characters;
    }

    public String getCameraViewBehaviour() {
        return cameraViewBehaviour;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public boolean isCustomGame() {
        return isCustomGame;
    }

    public boolean isEventMode() {
        return isEventMode;
    }

    public String getBlueZoneCustomOptions() {
        return blueZoneCustomOptions;
    }
}
