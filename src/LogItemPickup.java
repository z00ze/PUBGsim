public class LogItemPickup {
    // "character": {Character}, "item": {Item}
    Character character;
    Item item;

    public LogItemPickup(Character character, Item item) {
        this.character = character;
        this.item = item;
    }

    public Character getCharacter() {
        return character;
    }

    public Item getItem() {
        return item;
    }
}
