import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginControllers implements Initializable {
    @FXML
    private Button cancelBtn;

    @FXML
    private TextField usernameTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private Button exitBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Label notificationLabel;
    private double x;
    private double y;

    public void exitButtonClicked(ActionEvent e) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Επιβεβαίωση εξόδου");
        alert.setHeaderText("Επιβεβαίωση");
        alert.setContentText("Είστε σίγουροι ότι θέλετε να τερματίσετε την εφαρμογή;");

        ButtonType buttonTypeOK = new ButtonType("Έξοδος", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Ακύρωση", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeOK);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK)
            System.exit(0);
    }

    public void connectButtonClicked(ActionEvent event) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String result = connectDB.getAccess(username, password);
        if (result != null) {
            String query = "SELECT w.wrk_lname, w.wrk_name, w.wrk_salary, w.wrk_br_code, it.password, it.start_date " +
                    "FROM worker w JOIN it " +
                    "ON w.wrk_AT=it.IT_AT " +
                    "WHERE it.IT_AT = ?";

            try (Connection conn = connectDB.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, result);
                ResultSet info = stmt.executeQuery();
                if (info.next()) {
                    userInformation.setAT(result);
                    userInformation.setLastname(info.getString("wrk_lname"));
                    userInformation.setName(info.getString("wrk_name"));
                    userInformation.setSalary(info.getFloat("wrk_salary"));
                    userInformation.setBranch(info.getInt("wrk_br_code"));
                    userInformation.setPassword(info.getString("password"));
                    userInformation.setStart_date(info.getDate("start_date").toLocalDate());
                    notificationLabel.setText("Επιτυχής σύνδεση!");

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("fxml code/mainWindow.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        root.setOnMousePressed((MouseEvent e) -> {
                            x = e.getSceneX();
                            y = e.getSceneY();
                        });

                        root.setOnMouseDragged((MouseEvent e) -> {
                            stage.setX(e.getScreenX() - x);
                            stage.setY(e.getSceneY() - y);

                            stage.setOpacity(.8);
                        });

                        root.setOnMouseReleased((MouseEvent e) -> {
                            stage.setOpacity(1);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setScene(scene);
                        loginBtn.getScene().getWindow().hide();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    notificationLabel.setText("Προέκυψε πρόβλημα. Ξαναπροσπαθείστε!");
                notificationLabel.setVisible(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            notificationLabel.setText("Ο χρήστης δεν υπάρχει, δεν έχει πρόσβαση ή τα στοιχεία είναι λάθος!");
            notificationLabel.setVisible(true);
        }
    }

    public void cancelButtonClicked(ActionEvent event) {
        notificationLabel.setVisible(false);
        usernameTF.clear();
        passwordTF.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificationLabel.setVisible(false);

    }

}