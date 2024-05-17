import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class initializeServerControllers implements Initializable {

    @FXML
    private Button BTNconnect;
    @FXML
    private Label LBLstatus;

    @FXML
    private TextField TFdatabase;

    @FXML
    private PasswordField TFpassword;

    @FXML
    private TextField TFport;

    @FXML
    private TextField TFserverip;

    @FXML
    private TextField TFusername;
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

    public void cancelButtonClicked(ActionEvent event) {
        LBLstatus.setVisible(false);
        TFserverip.setText(connectDB.getServer());
        TFport.setText(connectDB.getPort());
        TFdatabase.setText(connectDB.getDatabase());
        TFusername.setText(connectDB.getUsername());
        TFpassword.setText(connectDB.getPassword());
    }

    public void connectButtonClicked(ActionEvent event) {
        connectDB.setServer(TFserverip.getText());
        connectDB.setPort(TFport.getText());
        connectDB.setDatabase(TFdatabase.getText());
        connectDB.setUsername(TFusername.getText());
        connectDB.setPassword(TFpassword.getText());

        try (Connection ignored = connectDB.getConnection()) {
            Parent root = FXMLLoader.load(getClass().getResource("fxml code/login.fxml"));
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
            BTNconnect.getScene().getWindow().hide();
            stage.show();
        } catch (SQLException e) {
            LBLstatus.setText("Η σύνδεση με τον server δεν ήταν δυνατή. Ελέγξτε τα στοιχεία και ξαναπροσπαθείστε!");
            LBLstatus.setVisible(true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFserverip.setText(connectDB.getServer());
        TFport.setText(connectDB.getPort());
        TFdatabase.setText(connectDB.getDatabase());
        TFusername.setText(connectDB.getUsername());
        TFpassword.setText(connectDB.getPassword());
        LBLstatus.setVisible(false);
    }
}
