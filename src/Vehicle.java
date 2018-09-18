public class Vehicle {
    // { "vehicleType": string, "vehicleId": string, "healthPercent": number, "feulPercent": number }
    String vehicleType;
    String vehicleId;
    float healthPercent;
    float feulPercent;

    public Vehicle(String vehicleType, String vehicleId, float healthPercent, float feulPercent) {
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.healthPercent = healthPercent;
        this.feulPercent = feulPercent;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public float getHealthPercent() {
        return healthPercent;
    }

    public float getFeulPercent() {
        return feulPercent;
    }
}
