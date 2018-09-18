import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Event {

    EventType eventType;
    Date datetime;
    Common common;
    LogArmorDestroy logArmorDestroy;
    LogCarePackageLand logCarePackageLand;
    LogCarePackageSpawn logCarePackageSpawn;
    LogGameStatePeriodic logGameStatePeriodic;
    LogItemAttach logItemAttach;
    LogItemDetach logItemDetach;
    LogItemDrop logItemDrop;
    LogItemEquip logItemEquip;
    LogItemPickup logItemPickup;
    LogItemUnequip logItemUnequip;
    LogItemUse logItemUse;
    LogMatchDefinition logMatchDefinition;
    LogMatchEnd logMatchEnd;
    LogMatchStart logMatchStart;
    LogPlayerAttack logPlayerAttack;
    LogPlayerCreate logPlayerCreate;
    LogPlayerKill logPlayerKill;
    LogPlayerLogin logPlayerLogin;
    LogPlayerLogout logPlayerLogout;
    LogPlayerMakeGroggy logPlayerMakeGroggy;
    LogPlayerPosition logPlayerPosition;
    LogPlayerRevive logPlayerRevive;
    LogPlayerTakeDamage logPlayerTakeDamage;
    LogSwimEnd logSwimEnd;
    LogSwimStart logSwimStart;
    LogVehicleDestroy logVehicleDestroy;
    LogVehicleLeave logVehicleLeave;
    LogVehicleRide logVehicleRide;
    LogWheelDestroy logWheelDestroy;

    public Event(JsonObject element) {

        this.eventType = EventType.valueOf(element.get("_T").getAsString());

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(element.get("_D").getAsString());
        datetime = Date.from(Instant.from(accessor));
        if(element.has("commong") && element.get("common").getAsJsonObject().has("isGame")) {
            common = new Common(element.get("common").getAsJsonObject().get("isGame").getAsFloat());
        }
        switch(this.eventType) {
            case LogArmorDestroy:
                // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string, "damageReason": string, "damageCauserName": string, "item": {Item}, "distance": number
                logArmorDestroy = new LogArmorDestroy(element.get("attackId").getAsInt(),
                        new Character(element.get("attacker").getAsJsonObject()),
                        new Character(element.get("victim").getAsJsonObject()),
                        element.get("damageTypeCategory").getAsString(),
                        element.get("damageReason").getAsString(),
                        element.get("damageCauserName").getAsString(),
                        // { "itemId": string, "stackCount": int, "category": string, "subCategory": string, "attachedItems": [Item, ...] }
                        new Item(element.get("item").getAsJsonObject().get("itemId").getAsString(),
                                element.get("item").getAsJsonObject().get("stackCount").getAsInt(),
                                element.get("item").getAsJsonObject().get("category").getAsString(),
                                element.get("item").getAsJsonObject().get("subCategory").getAsString(),
                                (new Gson().fromJson(element.get("item").getAsJsonObject().get("attachedItems").getAsJsonArray(), new TypeToken<LinkedList<String>>() {
                                }.getType()))
                        ),
                        element.get("distance").getAsFloat()
                );
                break;
            case LogCarePackageLand:
                LinkedList<Item> items = new LinkedList<>();
                if (element.has("items")){
                    for (int i = 0; i < element.get("items").getAsJsonArray().size(); i++) {
                        // { "itemId": string, "stackCount": int, "category": string, "subCategory": string, "attachedItems": [Item, ...] }
                        items.add(new Item(
                                element.get("items").getAsJsonArray().get(i).getAsJsonObject().get("itemId").getAsString(),
                                element.get("items").getAsJsonArray().get(i).getAsJsonObject().get("stackCount").getAsInt(),
                                element.get("items").getAsJsonArray().get(i).getAsJsonObject().get("category").getAsString(),
                                element.get("items").getAsJsonArray().get(i).getAsJsonObject().get("subCategory").getAsString(),
                                (new Gson().fromJson(element.get("item").getAsJsonArray().get(i).getAsJsonObject().get("attachedItems").getAsJsonArray(), new TypeToken<LinkedList<Item>>() {
                                }.getType()))
                        ));
                    }
                }
                System.out.println((element.has("location")) ? element.get("location").getAsJsonObject() : "");
                // "itemPackage": {ItemPackage}
                logCarePackageLand =    new LogCarePackageLand(
                                            // { "itemPackageId": string, "location": Location "items": [{Item}, ...] }
                                            new ItemPackage((element.has("itemPackageId") ? element.get("itemPackageId").getAsString() : ""),
                                            new Location((element.has("location")) ? element.get("location").getAsJsonObject() : new JsonObject()),
                                            items)
                                        );
                break;
            case LogCarePackageSpawn:
                break;
            case LogGameStatePeriodic:
                break;
            case LogItemAttach:
                break;
            case LogItemDetach:
                break;
            case LogItemDrop:
                break;
            case LogItemEquip:
                break;
            case LogItemPickup:
                break;
            case LogItemUnequip:
                break;
            case LogItemUse:
                break;
            case LogMatchDefinition:
                break;
            case LogMatchEnd:
                break;
            case LogMatchStart:
                break;
            case LogPlayerAttack:
                break;
            case LogPlayerCreate:
                break;
            case LogPlayerKill:
                break;
            case LogPlayerLogin:
                break;
            case LogPlayerLogout:
                break;
            case LogPlayerMakeGroggy:
                break;
            case LogPlayerPosition:
                break;
            case LogPlayerRevive:
                break;
            case LogPlayerTakeDamage:
                break;
            case LogSwimEnd:
                break;
            case LogSwimStart:
                break;
            case LogVehicleDestroy:
                break;
            case LogVehicleLeave:
                break;
            case LogVehicleRide:
                break;
            case LogWheelDestroy:
                break;
        }

    }

    public EventType getEventType() {
        return eventType;
    }

    public Date getDatetime() {
        return datetime;
    }

    public Common getCommon() {
        return common;
    }

    public LogArmorDestroy getLogArmorDestroy() {
        return logArmorDestroy;
    }

    public LogCarePackageLand getLogCarePackageLand() {
        return logCarePackageLand;
    }

    public LogCarePackageSpawn getLogCarePackageSpawn() {
        return logCarePackageSpawn;
    }

    public LogGameStatePeriodic getLogGameStatePeriodic() {
        return logGameStatePeriodic;
    }

    public LogItemAttach getLogItemAttach() {
        return logItemAttach;
    }

    public LogItemDetach getLogItemDetach() {
        return logItemDetach;
    }

    public LogItemDrop getLogItemDrop() {
        return logItemDrop;
    }

    public LogItemEquip getLogItemEquip() {
        return logItemEquip;
    }

    public LogItemPickup getLogItemPickup() {
        return logItemPickup;
    }

    public LogItemUnequip getLogItemUnequip() {
        return logItemUnequip;
    }

    public LogItemUse getLogItemUse() {
        return logItemUse;
    }

    public LogMatchDefinition getLogMatchDefinition() {
        return logMatchDefinition;
    }

    public LogMatchEnd getLogMatchEnd() {
        return logMatchEnd;
    }

    public LogMatchStart getLogMatchStart() {
        return logMatchStart;
    }

    public LogPlayerAttack getLogPlayerAttack() {
        return logPlayerAttack;
    }

    public LogPlayerCreate getLogPlayerCreate() {
        return logPlayerCreate;
    }

    public LogPlayerKill getLogPlayerKill() {
        return logPlayerKill;
    }

    public LogPlayerLogin getLogPlayerLogin() {
        return logPlayerLogin;
    }

    public LogPlayerLogout getLogPlayerLogout() {
        return logPlayerLogout;
    }

    public LogPlayerMakeGroggy getLogPlayerMakeGroggy() {
        return logPlayerMakeGroggy;
    }

    public LogPlayerPosition getLogPlayerPosition() {
        return logPlayerPosition;
    }

    public LogPlayerRevive getLogPlayerRevive() {
        return logPlayerRevive;
    }

    public LogPlayerTakeDamage getLogPlayerTakeDamage() {
        return logPlayerTakeDamage;
    }

    public LogSwimEnd getLogSwimEnd() {
        return logSwimEnd;
    }

    public LogSwimStart getLogSwimStart() {
        return logSwimStart;
    }

    public LogVehicleDestroy getLogVehicleDestroy() {
        return logVehicleDestroy;
    }

    public LogVehicleLeave getLogVehicleLeave() {
        return logVehicleLeave;
    }

    public LogVehicleRide getLogVehicleRide() {
        return logVehicleRide;
    }

    public LogWheelDestroy getLogWheelDestroy() {
        return logWheelDestroy;
    }

    public enum EventType {
    LogArmorDestroy, LogCarePackageLand, LogCarePackageSpawn, LogGameStatePeriodic, LogItemAttach, LogItemDetach,
    LogItemDrop, LogItemEquip, LogItemPickup, LogItemUnequip, LogItemUse, LogMatchDefinition, LogMatchEnd, LogMatchStart,
    LogPlayerAttack, LogPlayerCreate, LogPlayerKill, LogPlayerLogin, LogPlayerLogout, LogPlayerMakeGroggy, LogPlayerPosition,
    LogPlayerRevive, LogPlayerTakeDamage, LogSwimEnd, LogSwimStart, LogVehicleDestroy, LogVehicleLeave, LogVehicleRide, LogWheelDestroy
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", datetime=" + datetime +
                ", common=" + common +
                ", logArmorDestroy=" + ((logArmorDestroy == null) ? "" : logArmorDestroy.toString()) +
                ", logCarePackageLand=" + ((logCarePackageLand == null) ? "" : logCarePackageLand.toString()) +
                ", logCarePackageSpawn=" + ((logCarePackageSpawn == null) ? "" : logCarePackageSpawn.toString()) +
                ", logGameStatePeriodic=" + ((logGameStatePeriodic == null) ? "" : logGameStatePeriodic.toString()) +
                ", logItemAttach=" + ((logItemAttach == null) ? "" : logItemAttach.toString()) +
                ", logItemDetach=" + ((logItemDetach == null) ? "" : logItemDetach.toString()) +
                ", logItemDrop=" + ((logItemDrop == null) ? "" : logItemDrop.toString()) +
                ", logItemEquip=" + ((logItemEquip == null) ? "" : logItemEquip.toString()) +
                ", logItemPickup=" + ((logItemPickup == null) ? "" : logItemPickup.toString()) +
                ", logItemUnequip=" + ((logItemUnequip == null) ? "" : logItemUnequip.toString()) +
                ", logItemUse=" + ((logItemUse == null) ? "" : logItemUse.toString()) +
                ", logMatchDefinition=" + ((logMatchDefinition == null) ? "" : logMatchDefinition.toString()) +
                ", logMatchEnd=" + ((logMatchEnd == null) ? "" : logMatchEnd.toString()) +
                ", logMatchStart=" + ((logMatchStart == null) ? "" : logMatchStart.toString()) +
                ", logPlayerAttack=" + ((logPlayerAttack == null) ? "" : logPlayerAttack.toString()) +
                ", logPlayerCreate=" + ((logPlayerCreate == null) ? "" : logPlayerCreate.toString()) +
                ", logPlayerKill=" + ((logPlayerKill == null) ? "" : logPlayerKill.toString()) +
                ", logPlayerLogin=" + ((logPlayerLogin == null) ? "" : logPlayerLogin.toString()) +
                ", logPlayerLogout=" + ((logPlayerLogout == null) ? "" : logPlayerLogout.toString()) +
                ", logPlayerMakeGroggy=" + ((logPlayerMakeGroggy == null) ? "" : logPlayerMakeGroggy.toString()) +
                ", logPlayerPosition=" + ((logPlayerPosition == null) ? "" : logPlayerPosition.toString()) +
                ", logPlayerRevive=" + ((logPlayerRevive == null) ? "" : logPlayerRevive.toString()) +
                ", logPlayerTakeDamage=" + ((logPlayerTakeDamage == null) ? "" : logPlayerTakeDamage.toString()) +
                ", logSwimEnd=" + ((logSwimEnd == null) ? "" : logSwimEnd.toString()) +
                ", logSwimStart=" + ((logSwimStart == null) ? "" : logSwimStart.toString()) +
                ", logVehicleDestroy=" + ((logVehicleDestroy == null) ? "" : logVehicleDestroy.toString()) +
                ", logVehicleLeave=" + ((logVehicleLeave == null) ? "" : logVehicleLeave.toString()) +
                ", logVehicleRide=" + ((logVehicleRide == null) ? "" : logVehicleRide.toString()) +
                ", logWheelDestroy=" + ((logWheelDestroy == null) ? "" : logWheelDestroy.toString()) +
                '}';
    }
}
