import java.util.LinkedList;

public class Item {
    // { "itemId": string, "stackCount": int, "category": string, "subCategory": string, "attachedItems": [Item, ...] }
    String itemId;
    int stackCount;
    String category;
    String subCategory;
    LinkedList<String> attachedItems;

    public Item(String itemId, int stackCount, String category, String subCategory, LinkedList<String> attachedItems) {
        this.itemId = itemId;
        this.stackCount = stackCount;
        this.category = category;
        this.subCategory = subCategory;
        this.attachedItems = attachedItems;
    }

    public String getItemId() {
        return itemId;
    }

    public int getStackCount() {
        return stackCount;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public LinkedList<String> getAttachedItems() {
        return attachedItems;
    }
}
