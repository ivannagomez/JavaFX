
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
//import java.lang.Math;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents the main method AirBender
 * @author Ivanna
 * @version 1.0
 */
public class FractalMuseum extends Application {
    /**
     * This class represents the main driver
     * @param args represents all the arguments taken in
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * This class represents the game
     * @param mainStage represents where the game structure is located
     */
    public void start(Stage mainStage) {
        mainStage.setTitle("Fractal Museum");
        BorderPane borderpane = new BorderPane();
        Button btn = new Button();
        btn.setText("New Game!");
        StackPane btnPane = new StackPane();
        btnPane.getChildren().add(btn);
        Button btn2 = new Button();
        btn2.setDisable(true);
        Label fractalNumLabel = new Label();
        StackPane fractalNumLabelPane = new StackPane();
        FractalFactory fractal = new FractalFactory();
        Pane fractalPane = new Pane();
        fractalPane.setPrefSize(500, 500);
        AtomicInteger amount = new AtomicInteger();
        btn.setOnAction(event -> {
                fractalPane.getChildren().clear();
                int newAmount = fractal.drawFractal(fractalPane);
                amount.set(newAmount);
                btn2.setDisable(false);
                fractalNumLabel.setVisible(false);
                fractalNumLabelPane.getChildren().clear();
                fractalNumLabelPane.getChildren().add(fractalNumLabel);
            });
        btn2.setText("Submit Guesses!");
        TextField player1TextField = new TextField();
        TextField player2TextField = new TextField();
        player1TextField.setPromptText("Player's 1 Guess");
        player2TextField.setPromptText("Player's 2 Guess");
        Region r1 = new Region();
        Region r2 = new Region();
        HBox.setHgrow(r1, Priority.ALWAYS);
        HBox.setHgrow(r2, Priority.ALWAYS);
        HBox hb = new HBox();
        hb.getChildren().addAll(r1, player1TextField, player2TextField, btn2, r2);
        hb.setSpacing(10);
        AtomicInteger p1 = new AtomicInteger();
        AtomicInteger p2 = new AtomicInteger();
        Label p1Score = new Label("Player 1 Score: " + p1);
        Label p2Score = new Label("Player 2 Score: " + p2);
        HBox hb2 = new HBox();
        Region r3 = new Region();
        Region r4 = new Region();
        HBox.setHgrow(r3, Priority.ALWAYS);
        HBox.setHgrow(r4, Priority.ALWAYS);
        hb2.getChildren().addAll(r3, p1Score, p2Score, r4);
        hb2.setSpacing(50);
        VBox bottomPane = new VBox();
        bottomPane.getChildren().addAll(hb, hb2, fractalNumLabelPane);
        btn2.setOnAction(new EventHandler() {
            /**
             * Represents the event driver of when the "Submit Guesses" button is pressed
             * @param event Represents what the program does once the button is clicked
             */
            @Override
            public void handle(Event event) {
                fractalNumLabel.setText("Number of Fractals: " + amount);
                String text1 = player1TextField.getText();
                String text2 = player2TextField.getText();
                int int1;
                int int2;
                try {
                    int1 = Integer.parseInt(text1);
                    int2 = Integer.parseInt(text2);
                } catch (Exception e) {
                    int1 = 0;
                    int2 = 0;
                }
                if (int1 <= 0 || int2 <= 0) {
                    System.out.println("Please add a valid number :)");
                } else {
                    if (Math.abs(amount.get() - int1) < Math.abs(amount.get() - int2)) {
                        fractalNumLabel.setVisible(true);
                        int updatedScore = p1.intValue() + 1;
                        int updatedScore2 = p2.intValue();
                        p1.set(updatedScore);
                        p2.set(updatedScore2);
                        Label p1Score = new Label("Player 1 Score: " + p1.intValue());
                        Label p2Score = new Label("Player 2 Score: " + p2.intValue());
                        HBox hb2 = new HBox();
                        hb2.getChildren().addAll(r3, p1Score, p2Score, r4);
                        hb2.setSpacing(50);
                        VBox bottomPane = new VBox();
                        //bottomPane.setAlignment(Pos.);
                        bottomPane.getChildren().addAll(hb, hb2, fractalNumLabelPane);
                        bottomPane.styleProperty().set("-fx-background-color: #befaff");
                        BorderPane borderpane = new BorderPane();
                        borderpane.styleProperty().set("-fx-background-color: #ffc3cb");
                        borderpane.setPadding(new Insets(5));
                        borderpane.setMargin(btnPane, new Insets(5));
                        borderpane.setMargin(fractalPane, new Insets(5));
                        borderpane.setMargin(bottomPane, new Insets(5));
                        borderpane.setTop(btnPane);
                        borderpane.setCenter(fractalPane);
                        borderpane.setBottom(bottomPane);
                        Scene scene = new Scene(borderpane, 520, 650);
                        mainStage.setScene(scene);
                        mainStage.show();
                        btn2.setDisable(true);
                    } else if (Math.abs(amount.get() - int1) > Math.abs(amount.get() - int2)) {
                        fractalNumLabel.setVisible(true);
                        int updatedScore = p1.intValue();
                        int updatedScore2 = p2.intValue() + 1;
                        p1.set(updatedScore);
                        p2.set(updatedScore2);
                        Label p1Score = new Label("Player 1 Score: " + p1.intValue());
                        Label p2Score = new Label("Player 2 Score: " + p2.intValue());
                        HBox hb2 = new HBox();
                        hb2.getChildren().addAll(r3, p1Score, p2Score, r4);
                        hb2.setSpacing(50);
                        VBox bottomPane = new VBox();
                        bottomPane.getChildren().addAll(hb, hb2, fractalNumLabelPane);
                        bottomPane.styleProperty().set("-fx-background-color: #befaff");
                        BorderPane borderpane = new BorderPane();
                        borderpane.styleProperty().set("-fx-background-color: #ffc3cb");
                        borderpane.setPadding(new Insets(5));
                        borderpane.setMargin(fractalPane, new Insets(5));
                        borderpane.setMargin(bottomPane, new Insets(5));
                        borderpane.setTop(btnPane);
                        borderpane.setCenter(fractalPane);
                        borderpane.setBottom(bottomPane);
                        Scene scene = new Scene(borderpane, 520, 650);
                        mainStage.setScene(scene);
                        mainStage.show();
                        btn2.setDisable(true);
                    } else if (Math.abs(amount.get() - int1) == Math.abs(amount.get() - int2)) {
                        fractalNumLabel.setVisible(true);
                        System.out.println("It's a tie! No one gets points :)");
                        btn2.setDisable(true);
                    }
                }
                player1TextField.clear();
                player2TextField.clear();
            }
        });
        btnPane.setPrefSize(500, 50);
        btnPane.styleProperty().set("-fx-background-color: #ceffc6");
        fractalPane.styleProperty().set("-fx-background-color: #ffefc8");
        bottomPane.styleProperty().set("-fx-background-color: #befaff");
        borderpane.styleProperty().set("-fx-background-color: #ffc3cb");
        borderpane.setPadding(new Insets(5));
        borderpane.setMargin(btnPane, new Insets(5));
        borderpane.setMargin(fractalPane, new Insets(5));
        borderpane.setMargin(bottomPane, new Insets(5));
        borderpane.setTop(btnPane);
        borderpane.setCenter(fractalPane);
        borderpane.setBottom(bottomPane);
        Scene scene = new Scene(borderpane, 520, 650);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
