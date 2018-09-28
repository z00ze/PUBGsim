import com.google.gson.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.LinkedList;

public class Event {

    EventType eventType;
    Long time; // epoch milli
    Common common;
    String accountId = "";

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

    public Event(){
        this.time = 9999999999999l;
    }

    public Event(JsonObject element) {

        this.eventType = EventType.valueOf(element.get("_T").getAsString());

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_INSTANT;
        TemporalAccessor accessor = timeFormatter.parse(element.get("_D").getAsString());
        this.time = Instant.from(accessor).toEpochMilli();
        if(element.has("common") && element.get("common").getAsJsonObject().has("isGame")) {
            common = new Common(element.get("common").getAsJsonObject().get("isGame").getAsFloat());
        }else{
            common = new Common(0);
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
                accountId = element.get("victim").getAsJsonObject().get("accountId").getAsString();
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
                LinkedList<Item> items2 = new LinkedList<>();
                if (element.has("items")){
                    for (int i = 0; i < element.get("items").getAsJsonArray().size(); i++) {
                        // { "itemId": string, "stackCount": int, "category": string, "subCategory": string, "attachedItems": [Item, ...] }
                        items2.add(new Item(element.get("items").getAsJsonArray().get(i).getAsJsonObject()));
                    }
                }
                // "itemPackage": {ItemPackage}
                logCarePackageSpawn =   new LogCarePackageSpawn(
                        new ItemPackage((element.has("itemPackageId") ? element.get("itemPackageId").getAsString() : ""),
                                new Location((element.has("location")) ? element.get("location").getAsJsonObject() : new JsonObject()),
                                items2)
                );
                break;
            case LogGameStatePeriodic:
                // "gameState": {GameState}
                logGameStatePeriodic =  new LogGameStatePeriodic(
                        // { "elapsedTime": int, "numAliveTeams": int, "numJoinPlayers": int, "numStartPlayers": int, "numAlivePlayers": int, "safetyZonePosition": Location, "safetyZoneRadius": number, "poisonGasWarningPosition": Location,
                        // "poisonGasWarningRadius": number, "redZonePosition": Location, "redZoneRadius": number }
                        new GameState(
                                (element.get("gameState").getAsJsonObject().has("elapsedTime")) ? element.get("gameState").getAsJsonObject().get("elapsedTime").getAsInt() : 0,
                                (element.get("gameState").getAsJsonObject().has("numAliveTeams")) ? element.get("gameState").getAsJsonObject().get("numAliveTeams").getAsInt() : 0,
                                (element.get("gameState").getAsJsonObject().has("numJoinPlayers")) ? element.get("gameState").getAsJsonObject().get("numJoinPlayers").getAsInt() : 0,
                                (element.get("gameState").getAsJsonObject().has("numStartPlayers")) ? element.get("gameState").getAsJsonObject().get("numStartPlayers").getAsInt() : 0,
                                (element.get("gameState").getAsJsonObject().has("numAlivePlayers")) ? element.get("gameState").getAsJsonObject().get("numAlivePlayers").getAsInt() : 0,
                                new Location((element.get("gameState").getAsJsonObject().has("safetyZonePosition")) ? element.get("gameState").getAsJsonObject().get("safetyZonePosition").getAsJsonObject() : new JsonObject()),
                                (element.get("gameState").getAsJsonObject().has("safetyZoneRadius")) ? element.get("gameState").getAsJsonObject().get("safetyZoneRadius").getAsFloat() : 0,
                                new Location((element.get("gameState").getAsJsonObject().has("poisonGasWarningPosition")) ? element.get("gameState").getAsJsonObject().get("poisonGasWarningPosition").getAsJsonObject() : new JsonObject()),
                                (element.get("gameState").getAsJsonObject().has("poisonGasWarningRadius")) ? element.get("gameState").getAsJsonObject().get("poisonGasWarningRadius").getAsFloat() : 0,
                                new Location((element.get("gameState").getAsJsonObject().has("redZonePosition")) ? element.get("gameState").getAsJsonObject().get("redZonePosition").getAsJsonObject() : new JsonObject()),
                                (element.get("gameState").getAsJsonObject().has("redZoneRadius")) ? element.get("gameState").getAsJsonObject().get("redZoneRadius").getAsFloat() : 0
                        )
                );
                break;
            case LogItemAttach:
                // "character": {Character}, "parentItem": {Item}, "childItem": {Item}
                logItemAttach = new LogItemAttach(
                                                new Character(element.get("character").getAsJsonObject()),
                                                new Item(element.get("parentItem").getAsJsonObject()),
                                                new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogItemDetach:
                // "character": {Character}, "parentItem": {Item}, "childItem": {Item}
                logItemDetach = new LogItemDetach(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item(element.get("parentItem").getAsJsonObject()),
                                    new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogItemDrop:
                // "character": {Character}, "item": {Item}
                logItemDrop =   new LogItemDrop(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogItemEquip:
                // "character": {Character}, "item": {Item}
                logItemEquip =  new LogItemEquip(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item((element.has("item")) ? element.get("item").getAsJsonObject() : new JsonObject())
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogItemPickup:
                // "character": {Character}, "childItem": {Item}
                logItemPickup = new LogItemPickup(
                                    new Character(element.get("character").getAsJsonObject()),
                                    new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogItemUnequip:
                // "character": {Character}, "item": {Item}
                logItemUnequip = new LogItemUnequip(
                        new Character(element.get("character").getAsJsonObject()),
                        new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogItemUse:
                // "character": {Character}, "item": {Item}
                logItemUse = new LogItemUse(
                        new Character(element.get("character").getAsJsonObject()),
                        new Item((element.has("childItem")) ? element.get("childItem").getAsJsonObject() : new JsonObject())
                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
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
                accountId = element.get("attacker").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogPlayerCreate:
                // "character": {Character}
                logPlayerCreate =   new LogPlayerCreate(
                                        new Character(element.get("character").getAsJsonObject())
                                    );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
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
                accountId = element.get("victim").getAsJsonObject().get("accountId").getAsString();
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
                accountId = element.get("victim").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogPlayerPosition:
                // "character": {Character}, "elapsedTime": number, "numAlivePlayers": int
                logPlayerPosition = new LogPlayerPosition(
                                        new Character(element.get("character").getAsJsonObject()),
                                        element.get("elapsedTime").getAsFloat(),
                                        element.get("numAlivePlayers").getAsInt()
                                    );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogPlayerRevive:
                // "reviver": {Character}, "victim": {Character}, // Yes, it's actually called victim
                logPlayerRevive =   new LogPlayerRevive(
                        new Character(element.get("reviver").getAsJsonObject()),
                        new Character(element.get("victim").getAsJsonObject())
                );
                accountId = element.get("victim").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogPlayerTakeDamage:
                // "attackId": int, "attacker": {Character}, "victim": {Character}, "damageTypeCategory": string,
                // "damageReason": string, "damage": number, "damageCauserName": string
                logPlayerTakeDamage =   new LogPlayerTakeDamage(
                                            element.get("attackId").getAsInt(),
                                            new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                            new Character((element.has("victim") && element.get("victim").isJsonObject()) ? element.get("victim").getAsJsonObject() : new JsonObject()),
                                            element.get("damageTypeCategory").getAsString(),
                                            element.get("damageReason").getAsString(),
                                            element.get("damage").getAsFloat(),
                                            element.get("damageCauserName").getAsString()
                                        );
                accountId = element.get("victim").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogSwimEnd:
                // "character": {Character}, "swimDistance": float
                logSwimEnd =    new LogSwimEnd(
                                    new Character(element.get("character").getAsJsonObject()),
                                    element.get("swimDistance").getAsFloat()
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogSwimStart:
                // "character": {Character}
                logSwimStart =  new LogSwimStart(
                                    new Character(element.get("character").getAsJsonObject())
                                );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
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
                accountId = element.get("attacker").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogVehicleLeave:
                // "character": {Character}, "vehicle": {Vehicle}, "rideDistance": number, "seatIndex": integer
                logVehicleLeave =   new LogVehicleLeave(
                                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                                        element.get("rideDistance").getAsFloat(),
                                        element.get("seatIndex").getAsInt()
                                    );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogVehicleRide:
                // "character": {Character}, "vehicle": {Vehicle}, "seatIndex": int
                logVehicleRide =    new LogVehicleRide(
                                    new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                                        (element.has("rideDistance")) ? element.get("rideDistance").getAsInt() : 0
                                    );
                accountId = element.get("character").getAsJsonObject().get("accountId").getAsString();
                break;
            case LogWheelDestroy:
                // "attackId": int, "attacker": {Character}, "vehicle": {Vehicle},
                // "damageTypeCategory": string, "damageCauserName": string
                logWheelDestroy =   new LogWheelDestroy(
                                        (element.has("attackId") ? element.get("attackId").getAsInt() : 0),
                                        new Character((element.has("attacker") && element.get("attacker").isJsonObject()) ? element.get("attacker").getAsJsonObject() : new JsonObject()),
                                        new Vehicle(element.get("vehicle").getAsJsonObject()),
                                        element.get("damageTypeCategory").getAsString(),
                                        element.get("damageCauserName").getAsString()
                                    );
                accountId = element.get("attacker").getAsJsonObject().get("accountId").getAsString();
                break;
        }

    }

    public EventType getEventType() {
        return eventType;
    }

    public Long getTime() {
        return time;
    }

    public Common getCommon() {
        return common;
    }

    public Location getLocation(){
        switch(this.eventType){
            case LogArmorDestroy:
                return logArmorDestroy.getVictim().getLocation();
            case LogCarePackageLand:
                return logCarePackageLand.getItemPackage().getLocation();
            case LogCarePackageSpawn:
                return logCarePackageSpawn.getItemPackage().getLocation();
            case LogGameStatePeriodic:
                return new Location(0,0,0);
            case LogItemAttach:
                return logItemAttach.getCharacter().getLocation();
            case LogItemDetach:
                return logItemDetach.getCharacter().getLocation();
            case LogItemDrop:
                return logItemDrop.getCharacter().getLocation();
            case LogItemEquip:
                return logItemEquip.getCharacter().getLocation();
            case LogItemPickup:
                return logItemPickup.getCharacter().getLocation();
            case LogItemUnequip:
                return logItemUnequip.getCharacter().getLocation();
            case LogItemUse:
                return logItemUse.getCharacter().getLocation();
            case LogMatchDefinition:
                return new Location(0,0,0);
            case LogMatchEnd:
                return new Location(0,0,0);
            case LogMatchStart:
                return new Location(0,0,0);
            case LogPlayerAttack:
                return logPlayerAttack.getAttacker().getLocation();
            case LogPlayerCreate:
                return logPlayerCreate.getCharacter().getLocation();
            case LogPlayerKill:
                return logPlayerKill.getKiller().getLocation();
            case LogPlayerLogin:
                return new Location(0,0,0);
            case LogPlayerLogout:
                return new Location(0,0,0);
            case LogPlayerMakeGroggy:
                return logPlayerMakeGroggy.getAttacker().getLocation();
            case LogPlayerPosition:
                return logPlayerPosition.getCharacter().getLocation();
            case LogPlayerRevive:
                return logPlayerRevive.getVictim().getLocation();
            case LogPlayerTakeDamage:
                return logPlayerTakeDamage.getVictim().getLocation();
            case LogSwimEnd:
                return logSwimEnd.getCharacter().getLocation();
            case LogSwimStart:
                return logSwimStart.getCharacter().getLocation();
            case LogVehicleDestroy:
                return logVehicleDestroy.getAttacker().getLocation();
            case LogVehicleLeave:
                return logVehicleLeave.getCharacter().getLocation();
            case LogVehicleRide:
                return logVehicleRide.getCharacter().getLocation();
            case LogWheelDestroy:
                return logWheelDestroy.getAttacker().getLocation();
            default:
                return new Location(0,0,0);
        }
    }

    public String getAccountId() {
        return accountId;
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
                ", datetime=" + time +
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
