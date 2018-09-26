import java.text.DecimalFormat;

public class Common {
    // { "isGame": number }
    float isGame;

    public Common(float isGame) {
        DecimalFormat value = new DecimalFormat("##.#");
        value.format(isGame);
        this.isGame = isGame;
    }

    public float getIsGame() {
        return isGame;
    }

    @Override
    public String toString() {
        return "Common{" +
                "isGame=" + isGame +
                '}';
    }
}
