import com.google.gson.JsonObject;

public class Vehicle {
    // { "vehicleType": string, "vehicleId": string, "healthPercent": number, "feulPercent": number }
    String vehicleType;
    String vehicleId;
    float healthPercent;
    float feulPercent;

    public Vehicle(JsonObject element) {
        if(!element.toString().equals("{}")) {
            this.vehicleType = element.get("vehicleType").getAsString();
            this.vehicleId = element.get("vehicleId").getAsString();
            this.healthPercent = element.get("healthPercent").getAsFloat();
            this.feulPercent = element.get("feulPercent").getAsFloat();
        }
        else{
            this.vehicleType = "";
            this.vehicleId = "";
            this.healthPercent = 0;
            this.feulPercent = 0;
        }
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", healthPercent=" + healthPercent +
                ", feulPercent=" + feulPercent +
                '}';
    }
}
