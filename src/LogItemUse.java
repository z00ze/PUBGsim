public class LogItemUse {
    // "character": {Character}, "item": {Item}
    Character character;
    Item item;

    public LogItemUse(Character character, Item item) {
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
