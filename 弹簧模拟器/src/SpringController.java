import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class SpringController {
    private final SpringModel model;
    public boolean isRunning = true;

    public SpringController(SpringModel model) {
        this.model = model;
    }

    public VBox getPanel() {
        VBox box = new VBox(12,
                new Text("🎛 控制面板"),
                slider("质量 m", 0.1, 5.0, model.mass, v -> model.mass = v),
                slider("弹力系数 k", 10, 200, model.k, v -> model.k = v),
                slider("阻尼系数 c", 0, 30, model.damping, v -> model.damping = v),
                slider("初始位移 x₀", -0.2, 0.2, model.position, v -> model.initPosition = v),
                slider("初始速度 v₀", -2, 2, model.velocity, v -> model.initVelocity = v),
                createButtons()
        );
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #FFF3E0; -fx-border-color: #FFB74D; -fx-border-width: 2px; -fx-background-radius: 10; -fx-border-radius: 10;");
        return box;
    }

    private VBox slider(String label, double min, double max, double init, java.util.function.Consumer<Double> onChange) {
        Label l = new Label(label);
        l.setStyle("-fx-text-fill: #333; -fx-font-weight: bold;");
        Slider s = new Slider(min, max, init);
        s.setShowTickLabels(true);
        s.setShowTickMarks(true);
        s.setMajorTickUnit((max - min) / 4);
        s.setStyle("-fx-control-inner-background: linear-gradient(to right, #AED581, #81C784);");

        s.valueProperty().addListener((obs, oldVal, newVal) -> onChange.accept(newVal.doubleValue()));
        return new VBox(5, l, s);
    }

    private HBox createButtons() {
        Button start = new Button("▶ 开始 / 暂停");
        start.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;");
        start.setOnAction(e -> isRunning = !isRunning);

        Button reset = new Button("🔄 重置");
        reset.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-background-radius: 8;");
        reset.setOnAction(e -> model.reset());

        return new HBox(10, start, reset);
    }
}
