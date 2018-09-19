import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;

public class LogMatchEnd {
    // "characters": [{Character}, ...]
    LinkedList<Character> characters;

    public LogMatchEnd(JsonArray element) {
        this.characters = (new Gson().fromJson(element, new TypeToken<LinkedList<Character>>() {
        }.getType()));
    }

    public LinkedList<Character> getCharacters() {
        return characters;
    }

    @Override
    public String toString() {
        return "LogMatchEnd{" +
                "characters=" + characters +
                '}';
    }
}
