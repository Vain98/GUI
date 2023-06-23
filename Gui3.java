package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Circle circle1 = new Circle(80, 20, 20);
        circle1.setFill(Color.WHITE);
        Circle circle2 = new Circle(80, 75, 35);
        circle2.setFill(Color.WHITE);
        Circle circle3 = new Circle(80, 170, 60);
        circle3.setFill(Color.WHITE);

        VBox vb = new VBox(circle1, circle2, circle3);
        vb.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vb, 425, 230, Color.BLUE);
        circle1.radiusProperty().bind(scene.heightProperty().divide(11.7));
        circle2.radiusProperty().bind(scene.heightProperty().divide(7.7));
        circle3.radiusProperty().bind(scene.heightProperty().divide(3.7));

        stage.setTitle("JavaFX Snowman App");
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
