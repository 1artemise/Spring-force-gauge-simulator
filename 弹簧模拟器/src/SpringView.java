import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SpringView {
    private final GraphicsContext gc;
    private final double pixelsPerMeter = 1800;
    private final double anchorY = 100;
    private final double centerX = 300;
    private final double canvasHeight;

    public SpringView(GraphicsContext gc, double canvasHeight) {
        this.gc = gc;
        this.canvasHeight = canvasHeight;
    }

    public void render(SpringModel model) {
        gc.setFill(Color.web("#FFFDE7"));
        gc.fillRect(0, 0, 600, canvasHeight);
        drawRuler();
        double y = anchorY + model.position * pixelsPerMeter;
        drawSpring(centerX, anchorY, centerX, y, 20, 20);
        gc.setFill(Color.web("#FFA000"));
        gc.fillOval(centerX - 25, y, 50, 50);
        gc.setStroke(Color.GRAY);
        gc.strokeOval(centerX - 25, y, 50, 50);
    }

    private void drawSpring(double x1, double y1, double x2, double y2, int coils, double amplitude) {
        gc.setStroke(Color.DARKBLUE);
        gc.setLineWidth(2);
        double totalLength = y2 - y1;
        double segmentLength = totalLength / (coils + 2);
        double prevX = x1, prevY = y1;
        double currentY = y1 + segmentLength;
        gc.strokeLine(prevX, prevY, prevX, currentY);
        prevY = currentY;
        double direction = 1;
        for (int i = 0; i < coils; i++) {
            double dx = amplitude * direction;
            double dy = segmentLength;
            double x = prevX + dx;
            double y = prevY + dy;
            gc.strokeLine(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
            direction *= -1;
        }
        gc.strokeLine(prevX, prevY, x2, y2);
    }

    private void drawRuler() {
        double baseX = 50;
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        gc.strokeLine(baseX, anchorY, baseX, canvasHeight - 50);
        for (double m = 0; m <= 0.3; m += 0.05) {
            double y = anchorY + m * pixelsPerMeter;
            gc.strokeLine(baseX - 5, y, baseX + 5, y);
            gc.strokeText(String.format("%.2f m", m), baseX + 10, y + 4);
        }
    }
}
