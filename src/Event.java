import com.google.gson.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.LinkedList;

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
        if(element.has("common") && element.get("common").getAsJsonObject().has("isGame")) {
            common = new Common(element.get("common").getAsJsonObject().get("isGame").getAsFloat());
        }
        switch(this.eventType) {
            case LogArmorDestroy:
                // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string, "damageReason": string, "damageCauserName": string, "item": {Item}, "distance": number
                logArmorDestroy = new LogArmorDestroy(element.get("attackId").getAsInt(),
                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                        new Character(element.get("victim").getAsJsonObject()),
                        element.get("damageTypeCategory").getAsString(),
                        element.get("damageReason").getAsString(),
                        element.get("damageCauserName").getAsString(),
                        new Item(element.get("item").getAsJsonObject()),
                        element.get("distance").getAsFloat()
                );
                break;
            case LogCarePackageLand:
                LinkedList<Item> items = new LinkedList<>();
                if (element.has("items")){
                    for (int i = 0; i < element.get("items").getAsJsonArray().size(); i++) {
                        // { "itemId": string, "stackCount": int, "category": string, "subCategory": string, "attachedItems": [Item, ...] }
                        items.add(new Item(element.get("items").getAsJsonArray().get(i).getAsJsonObject()));
                    }
                }
                // "itemPackage": {ItemPackage}
                logCarePackageLand =    new LogCarePackageLand(
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
                // "character": {Character}, "parentItem": {Item}, "childItem": {Item}
                logItemAttach = new LogItemAttach(
                                                new Character(element.get("character").getAsJsonObject()),
                                                new Item(element.get("parentItem").getAsJsonObject()),
                                                new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                break;
            case LogItemDetach:
                // "character": {Character}, "parentItem": {Item}, "childItem": {Item}
                logItemDetach = new LogItemDetach(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item(element.get("parentItem").getAsJsonObject()),
                                    new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                break;
            case LogItemDrop:
                // "character": {Character}, "item": {Item}
                logItemDrop =   new LogItemDrop(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                break;
            case LogItemEquip:
                // "character": {Character}, "item": {Item}
                logItemEquip =  new LogItemEquip(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item((element.has("item")) ? element.get("item").getAsJsonObject() : new JsonObject())
                                );
                break;
            case LogItemPickup:
                // "character": {Character}, "childItem": {Item}
                logItemPickup = new LogItemPickup(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                break;
            case LogItemUnequip:
                // "character": {Character}, "item": {Item}
                logItemUnequip = new LogItemUnequip(
                        new Character(element.get("character").getAsJsonObject()),
                        new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                );
                break;
            case LogItemUse:
                // "character": {Character}, "item": {Item}
                logItemUse = new LogItemUse(
                        new Character(element.get("character").getAsJsonObject()),
                        new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                );
                break;
            case LogMatchDefinition:
                // "MatchId": string, "PingQuality": string
                logMatchDefinition =    new LogMatchDefinition(
                                            element.get("MatchId").getAsString(),
                                            element.get("PingQuality").getAsString()
                                        );
                break;
            case LogMatchEnd:
                // "characters": [{Character}, ...]
                logMatchEnd = new LogMatchEnd(element.get("characters").getAsJsonArray());
                break;
            case LogMatchStart:
                // "mapName": string, "weatherId": string, "characters": [{Character}, ...], "cameraViewBehaviour": string, "teamSize": int, "isCustomGame": bool, "isEventMode": bool, "blueZoneCustomOptions": string
                logMatchStart = new LogMatchStart(
                                    element.get("mapName").getAsString(),
                                    element.get("weatherId").getAsString(),
                                    element.get("characters"),
                                    element.get("cameraViewBehaviour").getAsString(),
                                    element.get("teamSize").getAsInt(),
                                    element.get("isCustomGame").getAsBoolean(),
                                    element.get("isEventMode").getAsBoolean(),
                                    element.get("blueZoneCustomOptions").getAsString()
                                );
                break;
            case LogPlayerAttack:
                // "attackId": int, "attacker": {Character}, "attackType": string, "weapon": {Item}, "vehicle": {Vehicle}
                logPlayerAttack =   new LogPlayerAttack(
                                        element.get("attackId").getAsInt(),
                                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        element.get("attackType").getAsString(),
                                        new Item(element.get("weapon").getAsJsonObject()),
                                        new Vehicle((element.has("vehicle") && element.get("vehicle").isJsonObject()) ? element.get("vehicle").getAsJsonObject() : new JsonObject())
                                    );
                break;
            case LogPlayerCreate:
                // "character": {Character}
                logPlayerCreate =   new LogPlayerCreate(
                                        new Character(element.get("character").getAsJsonObject())
                                    );
                break;
            case LogPlayerKill:
                // "attackId": int, "killer": {Character}, "victim": {Character}, "damageTypeCategory": string,
                // "damageCauserName": string, "damageReason": string, "distance": number
                logPlayerKill = new LogPlayerKill(
                                    element.get("attackId").getAsInt(),
                                    new Character(element.get("killer").getAsJsonObject()),
                                    new Character(element.get("victim").getAsJsonObject()),
                                    element.get("damageTypeCategory").getAsString(),
                                    element.get("damageCauserName").getAsString(),
                                    element.get("damageReason").getAsString(),
                                    element.get("distance").getAsFloat()
                                );
                break;
            case LogPlayerLogin:
                // "accountId": string
                logPlayerLogin =    new LogPlayerLogin(
                                    element.get("accountId").getAsString()
                                    );
                break;
            case LogPlayerLogout:
                // "accountId": string
                logPlayerLogin =    new LogPlayerLogin(
                                        element.get("accountId").getAsString()
                                    );
                break;
            case LogPlayerMakeGroggy:
                // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string,
                // "damageCauserName": string, "distance": float, "isAttackerInVehicle": bool, "dBNOId": int
                logPlayerMakeGroggy =   new LogPlayerMakeGroggy(
                                            element.get("attackId").getAsInt(),
                                            new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                            new Character(element.get("victim").getAsJsonObject()),
                                            element.get("damageTypeCategory").getAsString(),
                                            element.get("damageCauserName").getAsString(),
                                            element.get("distance").getAsFloat(),
                                            element.get("isAttackerInVehicle").getAsBoolean(),
                                            element.get("dBNOId").getAsInt()
                                        );
                break;
            case LogPlayerPosition:
                // "character": {Character}, "elapsedTime": number, "numAlivePlayers": int
                logPlayerPosition = new LogPlayerPosition(
                                        new Character(element.get("character").getAsJsonObject()),
                                        element.get("elapsedTime").getAsFloat(),
                                        element.get("numAlivePlayers").getAsInt()
                                    );
                break;
            case LogPlayerRevive:
                // "reviver": {Character}, "victim": {Character}, // Yes, it's actually called victim
                logPlayerRevive =   new LogPlayerRevive(
                        new Character(element.get("reviver").getAsJsonObject()),
                        new Character(element.get("victim").getAsJsonObject())
                );
                break;
            case LogPlayerTakeDamage:
                // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string,
                // "damageReason": string, "damage": number, "damageCauserName": string
                logPlayerTakeDamage =   new LogPlayerTakeDamage(
                                            element.get("attackId").getAsInt(),
                                            new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                            new Character(element.get("victim").getAsJsonObject()),
                                            element.get("damageTypeCategory").getAsString(),
                                            element.get("damageReason").getAsString(),
                                            element.get("damage").getAsFloat(),
                                            element.get("damageCauserName").getAsString()
                                        );
                break;
            case LogSwimEnd:
                // "character": {Character}, "swimDistance": float
                logSwimEnd =    new LogSwimEnd(
                                    new Character(element.get("character").getAsJsonObject()),
                                    element.get("swimDistance").getAsFloat()
                                );

                break;
            case LogSwimStart:
                // "character": {Character}
                logSwimStart =  new LogSwimStart(
                                    new Character(element.get("character").getAsJsonObject())
                                );
                break;
            case LogVehicleDestroy:
                // "atackId": int, "attacker": {Character}, "vehicle": {Vehicle}, "damageTypeCategory": string,
                // "damageCauserName": string, "distance": number
                logVehicleDestroy = new LogVehicleDestroy(
                                        element.get("attackId").getAsInt(),
                                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                                        element.get("damageTypeCategory").getAsString(),
                                        element.get("damageCauserName").getAsString(),
                                        element.get("distance").getAsFloat()
                                    );
                break;
            case LogVehicleLeave:
                // "character": {Character}, "vehicle": {Vehicle}, "rideDistance": number, "seatIndex": integer
                logVehicleLeave =   new LogVehicleLeave(
                                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                                        element.get("rideDistance").getAsFloat(),
                                        element.get("seatIndex").getAsInt()
                                    );
                break;
            case LogVehicleRide:
                // "character": {Character}, "vehicle": {Vehicle}, "seatIndex": int
                logVehicleRide =    new LogVehicleRide(
                                    new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                                        (element.has("rideDistance")) ? element.get("rideDistance").getAsInt() : 0
                                    );
                break;
            case LogWheelDestroy:
                // "attackId": int, "attacker": {Character}, "vehicle": {Vehicle},
                // "damageTypeCategory": string, "damageCauserName": string
                logWheelDestroy =   new LogWheelDestroy(
                        element.get("attackId").getAsInt(),
                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                        element.get("damageTypeCategory").getAsString(),
                        element.get("damageCauserName").getAsString()
                );
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
