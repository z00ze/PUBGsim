public class LogVehicleLeave {
    // "character": {Character}, "vehicle": {Vehicle}, "rideDistance": number, "seatIndex": integer
    Character character;
    Vehicle vehicle;
    float rideDistance;
    int seatIndex;

    public LogVehicleLeave(Character character, Vehicle vehicle, float rideDistance, int seatIndex) {
        this.character = character;
        this.vehicle = vehicle;
        this.rideDistance = rideDistance;
        this.seatIndex = seatIndex;
    }

    public Character getCharacter() {
        return character;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public float getRideDistance() {
        return rideDistance;
    }

    public int getSeatIndex() {
        return seatIndex;
    }
}
