public class LogPlayerAttack {
    // "attackId": int, "attacker": {Character}, "attackType": string, "weapon": {Item}, "vehicle": {Vehicle}
    int attackId;
    Character attacker;
    String attackType;
    Item weapon;
    Vehicle vehicle;

    public LogPlayerAttack(int attackId, Character attacker, String attackType, Item weapon, Vehicle vehicle) {
        this.attackId = attackId;
        this.attacker = attacker;
        this.attackType = attackType;
        this.weapon = weapon;
        this.vehicle = vehicle;
    }

    public int getAttackId() {
        return attackId;
    }

    public Character getAttacker() {
        return attacker;
    }

    public String getAttackType() {
        return attackType;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
