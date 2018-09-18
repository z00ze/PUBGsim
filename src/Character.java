import com.google.gson.JsonObject;

public class Character {
    //{ "name": string, "teamId": int, "health": number, "location": Location, "ranking": int, "accountId": string }
    String name;
    int teamId;
    float health;
    Location location;
    int ranking;
    String accountId;

    public Character(JsonObject element) {
        this.name = element.get("name").getAsString();
        this.teamId = element.get("teamId").getAsInt();
        this.health = element.get("health").getAsFloat();
        this.location = new Location(element.get("location").getAsJsonObject());
        this.ranking = element.get("ranking").getAsInt();
        this.accountId = element.get("accountId").getAsString();
    }

    public String getName() {
        return name;
    }

    public int getTeamId() {
        return teamId;
    }

    public float getHealth() {
        return health;
    }

    public Location getLocation() {
        return location;
    }

    public int getRanking() {
        return ranking;
    }

    public String getAccountId() {
        return accountId;
    }
}
