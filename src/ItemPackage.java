import java.util.LinkedList;

public class ItemPackage {
    // { "itemPackageId": string, "location": Location "items": [{Item}, ...] }
    String itemPackageId;
    Location location;
    LinkedList<Item> items;

    public ItemPackage(String itemPackageId, Location location, LinkedList<Item> items) {
        this.itemPackageId = itemPackageId;
        this.location = location;
        this.items = items;
    }

    public String getItemPackageId() {
        return itemPackageId;
    }

    public Location getLocation() {
        return location;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ItemPackage{" +
                "itemPackageId='" + itemPackageId + '\'' +
                ", location=" + location +
                ", items=" + items +
                '}';
    }
}
