import com.google.gson.JsonObject;

public class Location {
    // { "x": number, "y": number, "z": number }
    float x;
    float y;
    float z;

    public Location(JsonObject element) {
        if(!element.toString().equals("{}")) {
            this.x = (element.get("x").getAsFloat() > 818000) ? 818000 : element.get("x").getAsFloat();
            this.y = (element.get("y").getAsFloat() > 818000) ? 818000 : element.get("y").getAsFloat();
            this.z =(element.get("z").getAsFloat() > 818000) ? 818000 : element.get("z").getAsFloat();
        }
        else{
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
    }
    public Location(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
