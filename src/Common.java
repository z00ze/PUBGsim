public class Common {
    // { "isGame": number }
    float isGame;

    public Common(float isGame) {
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
