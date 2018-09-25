import javafx.scene.shape.Circle;

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
    Circle safezone;

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

    public Circle getSafezone() {
        return safezone;
    }

    public void setSafezone(Circle safezone) {
        this.safezone = safezone;
    }

    @Override
    public String toString() {
        return "LogPlayerKill{" +
                "attackId=" + attackId +
                ", killer=" + killer +
                ", victim=" + victim +
                ", damageTypeCategory='" + damageTypeCategory + '\'' +
                ", damageCauserName='" + damageCauserName + '\'' +
                ", damageReason='" + damageReason + '\'' +
                ", distance=" + distance +
                ", safezone=" + safezone +
                '}';
    }
}
