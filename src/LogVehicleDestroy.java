public class LogVehicleDestroy {
    // "atackId": int, "attacker": {Character}, "vehicle": {Vehicle}, "damageTypeCategory": string,
    // "damageCauserName": string, "distance": number,
    int attackId;
    Character attacker;
    Vehicle vehicle;
    String damageTypeCategory;
    String damageCauserName;
    float distance;

    public LogVehicleDestroy(int attackId, Character attacker, Vehicle vehicle, String damageTypeCategory, String damageCauserName, float distance) {
        this.attackId = attackId;
        this.attacker = attacker;
        this.vehicle = vehicle;
        this.damageTypeCategory = damageTypeCategory;
        this.damageCauserName = damageCauserName;
        this.distance = distance;
    }

    public int getAttackId() {
        return attackId;
    }

    public Character getAttacker() {
        return attacker;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getDamageTypeCategory() {
        return damageTypeCategory;
    }

    public String getDamageCauserName() {
        return damageCauserName;
    }

    public float getDistance() {
        return distance;
    }
}
