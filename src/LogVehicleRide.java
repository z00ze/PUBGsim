public class LogVehicleRide {
    // "character": {Character}, "vehicle": {Vehicle}, "seatIndex": int
    Character character;
    Vehicle vehicle;
    int seatIndex;

    public LogVehicleRide(Character character, Vehicle vehicle, int seatIndex) {
        this.character = character;
        this.vehicle = vehicle;
        this.seatIndex = seatIndex;
    }

    public Character getCharacter() {
        return character;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSeatIndex() {
        return seatIndex;
    }
}
