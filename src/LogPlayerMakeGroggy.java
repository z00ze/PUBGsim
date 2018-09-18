public class LogPlayerMakeGroggy {
    // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string,
    // "damageCauserName": string, "distance": float, "isAttackerInVehicle": bool, "dBNOId": int
    int attackId;
    Character attacker;
    Character victim;
    String damageTypeCategory;
    String damageCauserName;
    float distance;
    boolean isAttackerInVehicle;
    int dBNOId;

    public LogPlayerMakeGroggy(int attackId, Character attacker, Character victim, String damageTypeCategory, String damageCauserName, float distance, boolean isAttackerInVehicle, int dBNOId) {
        this.attackId = attackId;
        this.attacker = attacker;
        this.victim = victim;
        this.damageTypeCategory = damageTypeCategory;
        this.damageCauserName = damageCauserName;
        this.distance = distance;
        this.isAttackerInVehicle = isAttackerInVehicle;
        this.dBNOId = dBNOId;
    }

    public int getAttackId() {
        return attackId;
    }

    public Character getAttacker() {
        return attacker;
    }

    public Character getVictim() {
        return victim;
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

    public boolean isAttackerInVehicle() {
        return isAttackerInVehicle;
    }

    public int getdBNOId() {
        return dBNOId;
    }
}
