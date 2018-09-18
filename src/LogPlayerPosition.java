public class LogPlayerPosition {
    // "character": {Character}, "elapsedTime": number, "numAlivePlayers": int
    Character character;
    float elapsedTime;
    int numAlivePlayers;

    public LogPlayerPosition(Character character, float elapsedTime, int numAlivePlayers) {
        this.character = character;
        this.elapsedTime = elapsedTime;
        this.numAlivePlayers = numAlivePlayers;
    }

    public Character getCharacter() {
        return character;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public int getNumAlivePlayers() {
        return numAlivePlayers;
    }
}
