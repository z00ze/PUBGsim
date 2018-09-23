import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Circle;

public class Phase {

    WritableImage writableImage;
    PixelWriter pixelWriter;
    double phase;
    Circle safetyzone;

    public Phase(WritableImage writableImage, PixelWriter pixelWriter, double phase) {
        this.pixelWriter = pixelWriter;
        this.writableImage = writableImage;
        this.phase = phase;
        this.safetyzone = safetyzone;
    }

    public PixelWriter getPixelWriter() {
        return pixelWriter;
    }

    public WritableImage getWritableImage() {
        return writableImage;
    }

    public double getPhase() {
        return phase;
    }

    public Circle getSafetyzone() {
        return safetyzone;
    }

    public void setSafetyzone(Circle safetyzone) {
        this.safetyzone = safetyzone;
    }
}
