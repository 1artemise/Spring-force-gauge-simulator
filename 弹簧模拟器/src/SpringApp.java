import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SpringApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        SpringModel model = new SpringModel();
        Canvas canvas = new Canvas(600, 800);
        SpringView view = new SpringView(canvas.getGraphicsContext2D(), 800);
        SpringController controller = new SpringController(model);

        new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double dt = (now - lastTime) / 1e9;
                lastTime = now;

                if (controller.isRunning) {
                    model.update(dt);
                }
                view.render(model);
            }
        }.start();

        HBox root = new HBox(20, canvas, controller.getPanel());
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #FFF8E1, #FFE0B2); -fx-padding: 20;");

        Scene scene = new Scene(root);
        primaryStage.setTitle("🌀 弹簧模拟器（高级版）");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
