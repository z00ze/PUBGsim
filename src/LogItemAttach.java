public class LogItemAttach {
    // "character": {Character}, "parentItem": {Item}, "childItem": {Item}
    Character character;
    Item parentItem;
    Item childItem;

    public LogItemAttach(Character character, Item parentItem, Item childItem) {
        this.character = character;
        this.parentItem = parentItem;
        this.childItem = childItem;
    }

    public Character getCharacter() {
        return character;
    }

    public Item getParentItem() {
        return parentItem;
    }

    public Item getChildItem() {
        return childItem;
    }
}
