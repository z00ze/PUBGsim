public class LogArmorDestroy {
    // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string, "damageReason": string, "damageCauserName": string, "item": {Item}, "distance": number
    int attackId;
    Character attacker;
    Character victim;
    String damageTypeCategory;
    String damageReason;
    String damageCausername;
    Item item;
    float distance;

    public LogArmorDestroy(int attackId, Character attacker, Character victim, String damageTypeCategory, String damageReason, String damageCausername, Item item, float distance) {
        this.attackId = attackId;
        this.attacker = attacker;
        this.victim = victim;
        this.damageTypeCategory = damageTypeCategory;
        this.damageReason = damageReason;
        this.damageCausername = damageCausername;
        this.item = item;
        this.distance = distance;
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

    public String getDamageReason() {
        return damageReason;
    }

    public String getDamageCausername() {
        return damageCausername;
    }

    public Item getItem() {
        return item;
    }

    public float getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "LogArmorDestroy{" +
                "attackId=" + attackId +
                ", attacker=" + attacker +
                ", victim=" + victim +
                ", damageTypeCategory='" + damageTypeCategory + '\'' +
                ", damageReason='" + damageReason + '\'' +
                ", damageCausername='" + damageCausername + '\'' +
                ", item=" + item +
                ", distance=" + distance +
                '}';
    }
}
