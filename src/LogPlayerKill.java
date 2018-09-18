public class LogPlayerKill {
    // "attackId": int, "killer": {Character}, "victim": {Character}, "damageTypeCategory": string,
    // "damageCauserName": string, "damageReason": string, "distance": number
    int attackId;
    Character killer;
    Character victim;
    String damageTypeCategory;
    String damageCauserName;
    String damageReason;
    float distance;

    public LogPlayerKill(int attackId, Character killer, Character victim, String damageTypeCategory, String damageCauserName, String damageReason, float distance) {
        this.attackId = attackId;
        this.killer = killer;
        this.victim = victim;
        this.damageTypeCategory = damageTypeCategory;
        this.damageCauserName = damageCauserName;
        this.damageReason = damageReason;
        this.distance = distance;
    }

    public int getAttackId() {
        return attackId;
    }

    public Character getKiller() {
        return killer;
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

    public String getDamageReason() {
        return damageReason;
    }

    public float getDistance() {
        return distance;
    }
}
