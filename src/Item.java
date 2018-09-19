import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;

public class Item {
    // { "itemId": string, "stackCount": int, "category": string, "subCategory": string, "attachedItems": [Item, ...] }
    String itemId;
    int stackCount;
    String category;
    String subCategory;
    LinkedList<String> attachedItems;

    public Item(JsonObject element) {
        if(!element.toString().equals("{}")) {
            this.itemId = element.get("itemId").getAsString();
            this.stackCount = element.get("stackCount").getAsInt();
            this.category = element.get("category").getAsString();
            this.subCategory = element.get("subCategory").getAsString();
            this.attachedItems = (new Gson().fromJson(element.get("attachedItems").getAsJsonArray(), new TypeToken<LinkedList<String>>() {
            }.getType()));
        }
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

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", stackCount=" + stackCount +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", attachedItems=" + attachedItems +
                '}';
    }
}
