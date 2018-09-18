public class LogPlayerTakeDamage {
    // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string,
    // "damageReason": string, "damage": number, // 1.0 damage = 1.0 health // Net damage after armor; damage to health
    // "damageCauserName": string
    int attackId;
    Character attacker;
    Character victim;
    String damageTypeCategory;
    String damageReason;
    float damage;
    String damageCauserName;

    public LogPlayerTakeDamage(int attackId, Character attacker, Character victim, String damageTypeCategory, String damageReason, float damage, String damageCauserName) {
        this.attackId = attackId;
        this.attacker = attacker;
        this.victim = victim;
        this.damageTypeCategory = damageTypeCategory;
        this.damageReason = damageReason;
        this.damage = damage;
        this.damageCauserName = damageCauserName;
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

    public float getDamage() {
        return damage;
    }

    public String getDamageCauserName() {
        return damageCauserName;
    }
}
