public class GameState {
    // { "elapsedTime": int, "numAliveTeams": int, "numJoinPlayers": int, "numStartPlayers": int, "numAlivePlayers": int, "safetyZonePosition": Location, "safetyZoneRadius": number, "poisonGasWarningPosition": Location,
    // "poisonGasWarningRadius": number, "redZonePosition": Location, "redZoneRadius": number }
    int elapsedTime;
    int numAliveTeams;
    int numJoinPlayers;
    int numStartPlayers;
    int numAlivePlayers;
    Location safetyZonePosition;
    float safetyZoneRadius;
    Location poisonGasWarningPosition;
    float poisonGasWarningRadius;
    Location redZonePosition;
    float redZoneRadius;

    public GameState(int elapsedTime, int numAliveTeams, int numJoinPlayers, int numStartPlayers, int numAlivePlayers, Location safetyZonePosition, float safetyZoneRadius, Location poisonGasWarningPosition, float poisonGasWarningRadius, Location redZonePosition, float redZoneRadius) {
        this.elapsedTime = elapsedTime;
        this.numAliveTeams = numAliveTeams;
        this.numJoinPlayers = numJoinPlayers;
        this.numStartPlayers = numStartPlayers;
        this.numAlivePlayers = numAlivePlayers;
        this.safetyZonePosition = safetyZonePosition;
        this.safetyZoneRadius = safetyZoneRadius;
        this.poisonGasWarningPosition = poisonGasWarningPosition;
        this.poisonGasWarningRadius = poisonGasWarningRadius;
        this.redZonePosition = redZonePosition;
        this.redZoneRadius = redZoneRadius;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public int getNumAliveTeams() {
        return numAliveTeams;
    }

    public int getNumJoinPlayers() {
        return numJoinPlayers;
    }

    public int getNumStartPlayers() {
        return numStartPlayers;
    }

    public int getNumAlivePlayers() {
        return numAlivePlayers;
    }

    public Location getSafetyZonePosition() {
        return safetyZonePosition;
    }

    public float getSafetyZoneRadius() {
        return safetyZoneRadius;
    }

    public Location getPoisonGasWarningPosition() {
        return poisonGasWarningPosition;
    }

    public float getPoisonGasWarningRadius() {
        return poisonGasWarningRadius;
    }

    public Location getRedZonePosition() {
        return redZonePosition;
    }

    public float getRedZoneRadius() {
        return redZoneRadius;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "elapsedTime=" + elapsedTime +
                ", numAliveTeams=" + numAliveTeams +
                ", numJoinPlayers=" + numJoinPlayers +
                ", numStartPlayers=" + numStartPlayers +
                ", numAlivePlayers=" + numAlivePlayers +
                ", safetyZonePosition=" + safetyZonePosition +
                ", safetyZoneRadius=" + safetyZoneRadius +
                ", poisonGasWarningPosition=" + poisonGasWarningPosition +
                ", poisonGasWarningRadius=" + poisonGasWarningRadius +
                ", redZonePosition=" + redZonePosition +
                ", redZoneRadius=" + redZoneRadius +
                '}';
    }
}
