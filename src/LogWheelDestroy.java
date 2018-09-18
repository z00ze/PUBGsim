public class LogWheelDestroy {
    // "attackId": int, "attacker": {Character}, "vehicle": {Vehicle},
    // "damageTypeCategory": string, "damageCauserName": string
    int attackId;
    Character attacker;
    Vehicle vehicle;
    String damageTypeCategory;
    String damageCauserName;

    public LogWheelDestroy(int attackId, Character attacker, Vehicle vehicle, String damageTypeCategory, String damageCauserName) {
        this.attackId = attackId;
        this.attacker = attacker;
        this.vehicle = vehicle;
        this.damageTypeCategory = damageTypeCategory;
        this.damageCauserName = damageCauserName;
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
}
