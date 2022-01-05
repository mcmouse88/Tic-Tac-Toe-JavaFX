package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    private boolean isStart = false;
    private boolean isCross = true;
    private boolean isStep = false;
    private final String CROSS = "X";
    private final String ZERO = "0";
    public final int SIZE = 3;
    private Stage stage = new Stage();
    private double x;
    private double y;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button buttonStart;

    @FXML
    private Label labelStep;

    @FXML
    private Label labelWin;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Pane imageIcon;

    @FXML
    void handle(MouseEvent event) {

    }

    @FXML
    void initialize() {
        buttonStart.setOnAction(actionEvent -> {
            if (!isStart) {
                buttonStart.setText("STOP");
                labelStep.setOpacity(1);
                labelStep.setText("Сейчас ходит \"X\"");
                isStart = true;
                isStep = true;
            } else {
                buttonStart.setText("START");
                isStart = false;
                Button[] buttons = {
                        button1,
                        button2,
                        button3,
                        button4,
                        button5,
                        button6,
                        button7,
                        button8,
                        button9
                };
                appDefault(buttons);
            }
        });
        button1.setOnAction(actionEvent -> buttonAction(button1));
        button2.setOnAction(actionEvent -> buttonAction(button2));
        button3.setOnAction(actionEvent -> buttonAction(button3));
        button4.setOnAction(actionEvent -> buttonAction(button4));
        button5.setOnAction(actionEvent -> buttonAction(button5));
        button6.setOnAction(actionEvent -> buttonAction(button6));
        button7.setOnAction(actionEvent -> buttonAction(button7));
        button8.setOnAction(actionEvent -> buttonAction(button8));
        button9.setOnAction(actionEvent -> buttonAction(button9));

        closeButton.setOnAction(actionEvent -> {
            System.exit(0);
        });
        closeButton.setOnMouseEntered(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                closeButton.setStyle("-fx-background-color: red");
            }
        });

        closeButton.setOnMouseExited(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                closeButton.setStyle("-fx-background-color: black");
            }
        });

        minimizeButton.setOnMouseEntered(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                minimizeButton.setStyle("-fx-background-color: grey");
            }
        });

        minimizeButton.setOnMouseExited(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                minimizeButton.setStyle("-fx-background-color: black");
            }
        });

        minimizeButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });

        imageIcon.setOnMousePressed(event -> {
            stage = (Stage) ((Pane) event.getSource()).getScene().getWindow();
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });

        imageIcon.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });
    }

    private void stepByStep() {
        if (isStart) {
            labelStep.setText(!isCross ? "Сейчас ходит \"X\"" : "Сейчас ходит \"0\"");
            isCross = !isCross;
        }
    }

    private void determinationWinner() {
        Button[][] buttons = {{button1, button2, button3}, {button4, button5, button6}, {button7, button8, button9}};
        if (WinDetector.isWin(buttons, isCross ? CROSS : ZERO)) {
            labelWin.setText("Победил \"" + (isCross ? CROSS : ZERO) + "\"");
            labelWin.setOpacity(1);
            labelStep.setOpacity(0);
            isStep = false;
        } else if (WinDetector.isNobodyWin(buttons, SIZE)) {
            labelWin.setText("\"Ничья\"");
            labelWin.setOpacity(1);
            labelStep.setOpacity(0);
            labelWin.setLayoutX(150);
        }
    }

    private void buttonAction(Button button) {
        if (isStart && isStep) {
            if (button.getText().equals("")) {
                button.setStyle("-fx-background-color: white");
                button.setText(isCross ? CROSS : ZERO);
                determinationWinner();
                stepByStep();
            }
        }
    }

    private void appDefault(Button[] buttons) {
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: black");
            String empty = "";
            button.setText(empty);
            labelWin.setOpacity(0);
            labelStep.setOpacity(0);
            isCross = true;
            isStep = false;
        }
    }
}
