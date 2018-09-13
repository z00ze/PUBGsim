public class Event {

    EventType type;
    float x;
    float y;
    float z;

    Event(String type, float x, float y, float z){
        this.type = EventType.valueOf(type);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public EventType getType(){
        return type;
    }

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public float getZ(){
        return this.z;
    }
    public String toString(){
        return x + " " + y + " " + " " + z ;
    }

    public enum EventType {
        LogArmorDestroy, LogCarePackageLand, LogCarePackageSpawn, LogGameStatePeriodic, LogItemAttach, LogItemDetach,
        LogItemDrop, LogItemEquip, LogItemPickup, LogItemUnequip, LogItemUse, LogMatchDefinition, LogMatchEnd, LogMatchStart,
        LogPlayerAttack, LogPlayerCreate, LogPlayerKill, LogPlayerLogin, LogPlayerLogout, LogPlayerMakeGroggy, LogPlayerPosition,
        LogPlayerRevive, LogPlayerTakeDamage, LogSwimEnd, LogSwimStart, LogVehicleDestroy, LogVehicleLeave, LogVehicleRide, LogWheelDestroy
    }
}
