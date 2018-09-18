public class LogItemUnequip{
    // "character": {Character}, "item": {Item}
    Character character;
    Item item;

    public LogItemUnequip(Character character, Item item) {
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
