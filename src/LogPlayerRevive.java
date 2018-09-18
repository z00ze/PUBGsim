public class LogPlayerRevive {
    // "reviver": {Character}, "victim": {Character}, // Yes, it's actually called victim
    Character reviver;
    Character victim;

    public LogPlayerRevive(Character reviver, Character victim) {
        this.reviver = reviver;
        this.victim = victim;
    }

    public Character getReviver() {
        return reviver;
    }

    public Character getVictim() {
        return victim;
    }
}
