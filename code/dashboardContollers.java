import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class dashboardContollers implements Initializable {

    @FXML
    private Label accounting_number_summary;

    @FXML
    private Button addBottonDest;

    @FXML
    private Button addBottonEvents;

    @FXML
    private Button addBottonTrip;

    @FXML
    private Label admin_number_summary;

    @FXML
    private DatePicker arrivalPickerDest;

    @FXML
    private TableColumn<Destination, String> arrivalTableDest;

    @FXML
    private ComboBox<String> branchListTrip;

    @FXML
    private TableColumn<Trip, String> branchTableTrip;

    @FXML
    private Button clearBottonDest;

    @FXML
    private Button clearBottonEvents;

    @FXML
    private Button clearBottonTrip;

    @FXML
    private TextField costTextTrips;

    @FXML
    private Pane dashboard;

    @FXML
    private Button dashbordButton;

    @FXML
    private DatePicker departureDateTrips;

    @FXML
    private DatePicker departurePickerDest;

    @FXML
    private DatePicker departurePickerEvents;

    @FXML
    private TableColumn<Destination, String> departureTableDest;

    @FXML
    private TableColumn<Event, String> departureTableEvents;

    @FXML
    private TableColumn<Trip, String> departureTableTrips;

    @FXML
    private TableColumn<Destination, String> descriptionTableDest;

    @FXML
    private TableColumn<Event, String> descriptionTableEvents;

    @FXML
    private TextArea descriptionTextDest;

    @FXML
    private TextArea descriptionTextEvents;

    @FXML
    private Label destinationNumber;

    @FXML
    private ComboBox<String> driverListTrips;

    @FXML
    private TableColumn<Trip, String> driverTableTrips;

    @FXML
    private Label drivers_number_summary;

    @FXML
    private Button exitButton;

    @FXML
    private ComboBox<String> guideListTrips;

    @FXML
    private TableColumn<Trip, String> guideTableTrip;

    @FXML
    private Label guide_number_summary;

    @FXML
    private Label incomes;

    @FXML
    private ComboBox<String> languageListDest;

    @FXML
    private TableColumn<Destination, String> languageTableDest;

    @FXML
    private ComboBox<String> locationListDest;

    @FXML
    private TableColumn<Destination, String> locationTableDest;

    @FXML
    private Label logistics_number_summary;

    @FXML
    private TextField nameFieldDest;

    @FXML
    private TableColumn<Destination, String> nameTableDest;

    @FXML
    private Button newReservationButton;

    @FXML
    private Button offerButton;

    @FXML
    private Label outcomes;

    @FXML
    private TableColumn<Trip, Float> priceTableTrips;

    @FXML
    private Label profit;

    @FXML
    private Label reservationNumber;

    @FXML
    private DatePicker returnDateTrip;

    @FXML
    private DatePicker returnPickerEvents;

    @FXML
    private TableColumn<Event, String> returnTableEvents;

    @FXML
    private TableColumn<Trip, String> returnTableTrips;

    @FXML
    private Button searchBottonDest;

    @FXML
    private Button searchBottonEvents;

    @FXML
    private Button searchBottonTrip;

    @FXML
    private TextField seatTextTrip;

    @FXML
    private TableColumn<Trip, Integer> seatsTableTrips;

    @FXML
    private Button settingsButton;

    @FXML
    private Button signoutButton;

    @FXML
    private TableView<Destination> tableDest;

    @FXML
    private TableView<Event> tableEvents;

    @FXML
    private TableView<Trip> tableTrip;

    @FXML
    private AnchorPane travelMenu;

    @FXML
    private Button tripButton;

    @FXML
    private TableColumn<Event, Integer> tripIdTableEvents;

    @FXML
    private ComboBox<String> tripidListDest;

    @FXML
    private ComboBox<String> tripidListEvents;

    @FXML
    private ComboBox<String> tripidListTrips;

    @FXML
    private TableColumn<Destination, Integer> tripidTableDest;

    @FXML
    private TableColumn<Trip, Integer> tripidTableTrips;

    @FXML
    private ComboBox<String> typeListDest;

    @FXML
    private TableColumn<Destination, String> typeTableDest;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button workerButton;

    @FXML
    private AnchorPane workers_summary;

    @FXML
    private Tab eventTabTrip;

    @FXML
    private Pane addOffersMenu;

    @FXML
    private Pane reservationMenu;

    @FXML
    private Pane userInformationScene;

    @FXML
    private Pane workersManagerMenu;

    @FXML
    private TableColumn<settings, String> ACTIONCOLUMNSET;

    @FXML
    private TextField ATTEXTSET;

    @FXML
    private TextField BRANCHTEXTSET;

    @FXML
    private TableColumn<settings, String> CHANGESCOLUMNSET;

    @FXML
    private Label DATETEXTSET;

    @FXML
    private TextField LNAMETEXTSET;

    @FXML
    private TextField NAMETEXTSET;

    @FXML
    private PasswordField PASSTEXTSET;

    @FXML
    private ImageView PHOTOUSERSET;

    @FXML
    private TextField SALARYTEXTSET;

    @FXML
    private TableView<settings> TABLESET;

    @FXML
    private TableColumn<settings, String> TIMECOLUMNSET;

    @FXML
    private Button UPDATESET;

    @FXML
    private TableColumn<settings, String> USERCOLUMNSET;

    @FXML
    private Button addButtonAddWorker;

    @FXML
    private ComboBox<String> adminBranchAddWorker;

    @FXML
    private ComboBox<String> adminTypeAddWorker;

    @FXML
    private ComboBox<String> branchAddWorker;

    @FXML
    private Button clearButtonAddWorker;

    @FXML
    private TextArea cvAddWorker;

    @FXML
    private TextField diplomaAddWorker;

    @FXML
    private TextField experienceAddWorker;

    @FXML
    private TextField idAddWorker;

    @FXML
    private TextField languageAddWorker;

    @FXML
    private ComboBox<String> licenseAddWorker;

    @FXML
    private TextField lnameAddWorker;

    @FXML
    private TextField nameAddWorker;

    @FXML
    private ComboBox<String> routeAddWorker;

    @FXML
    private TextField salaryAddWorker;

    @FXML
    private ComboBox<String> typeAddWorker;

    @FXML
    private Label adminLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label branchLabel;

    @FXML
    private Label diplomaLabel;

    @FXML
    private Label licenseLabel;

    @FXML
    private Label routeLabel;

    @FXML
    private Label experienceLabel;

    @FXML
    private Label driverLabel;

    @FXML
    private Label guideLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private TableView<Worker> tableWorker;

    @FXML
    private TableColumn<Worker, String> atColumnWorker;

    @FXML
    private TableColumn<Worker, String> branchColumnWorker;

    @FXML
    private TableColumn<Worker, String> lnameColumnWorker;

    @FXML
    private TableColumn<Worker, String> nameColumnWorker;

    @FXML
    private TableColumn<Worker, Float> salaryColumnWorker;

    @FXML
    private TableColumn<Worker, String> typeColumnWorker;

    @FXML
    private ComboBox<String> destinationListOffer;

    @FXML
    private ComboBox<String> idListOffer;

    @FXML
    private DatePicker startDatePickerOffer;

    @FXML
    private TextField costTextOffer;

    @FXML
    private DatePicker finishDatePickerOffer;

    @FXML
    private Button serachButtonOffer;

    @FXML
    private Button addButtonOffer;

    @FXML
    private Button clearButtonOffer;

    @FXML
    private TableView<Offers> offerTable;

    @FXML
    private TableColumn<Offers, String> idColumnOffer;

    @FXML
    private TableColumn<Offers, String> startColumnOffer;

    @FXML
    private TableColumn<Offers, String> finishColumnOffer;

    @FXML
    private TableColumn<Offers, Float> costColumnOffer;

    @FXML
    private TableColumn<Offers, String> destinationColumnOffer;

    @FXML
    private Tab offersTabReservation;

    @FXML
    private ComboBox<String> offeridListRes;

    @FXML
    private TextField nameTextRes;

    @FXML
    private TextField lnameTextRes;

    @FXML
    private TextField depositTextRes;

    @FXML
    private Label offid;

    @FXML
    private Label offname;

    @FXML
    private Label offdeposit;

    @FXML
    private Label offlname;

    @FXML
    private Button clearOfferResButton;

    @FXML
    private Button addResOfferButton;

    @FXML
    private Button searchResOfferButton;

    @FXML
    private TableView<reservation_offer> offerResTable;

    @FXML
    private TableColumn<reservation_offer, String> idColumnRes;

    @FXML
    private TableColumn<reservation_offer, String> nameColumnRes;

    @FXML
    private TableColumn<reservation_offer, String> lnameColumnRes;

    @FXML
    private TableColumn<reservation_offer, String> offeridColumnRes;

    @FXML
    private TableColumn<reservation_offer, Float> depositColumnRes;

    @FXML
    private ImageView imageReservationRes;

    @FXML
    private ComboBox<String> ageListReservationRes;

    @FXML
    private ComboBox<Integer> idReservationRes;

    @FXML
    private TextField nameFieldReservationRes;

    @FXML
    private TextField lnameFieldReservationRes;

    @FXML
    private TextField numSeatFielsReservationRes;

    @FXML
    private Button createITSettings;

    @FXML
    private ComboBox<String> workersItComboBoxSettings;

    @FXML
    private PasswordField passwordITSeettings;

    private double x, y;

    public void dashboardBottonClicked(ActionEvent e) {
        dashboard.setVisible(true);
        travelMenu.setVisible(false);
        addOffersMenu.setVisible(false);
        reservationMenu.setVisible(false);
        userInformationScene.setVisible(false);
        workersManagerMenu.setVisible(false);
    }

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

    public void logout(ActionEvent e) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Επιβεβαίωση εξόδου");
        alert.setHeaderText("Επιβεβαίωση");
        alert.setContentText("Είστε σίγουροι ότι θέλετε να αποσυνδεθείτε;");

        ButtonType buttonTypeOK = new ButtonType("Αποσύνδεση", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Ακύρωση", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeOK);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK)
            try {
                Parent root = FXMLLoader.load(getClass().getResource("fxml code/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent h) -> {
                    x = h.getSceneX();
                    y = h.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent h) -> {
                    stage.setX(h.getScreenX() - x);
                    stage.setY(h.getSceneY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent h) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                signoutButton.getScene().getWindow().hide();
                stage.show();
            } catch (IOException h) {
                h.printStackTrace();
            }
    }

    public void tripButtonPressed(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            initTripIdTrips(conn);
            initGuidetrips(conn);
            initDrivertrips(conn);
            initBranchtrips(conn);
            initTripIdEvents(conn);
            initTypeDestination(conn);
            initLocationDestination(conn);
            initLanguageDestination(conn);
            initTripIdDestination(conn);
            initTripTable(conn);
            dashboard.setVisible(false);
            travelMenu.setVisible(true);
            addOffersMenu.setVisible(false);
            reservationMenu.setVisible(false);
            userInformationScene.setVisible(false);
            workersManagerMenu.setVisible(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void workerButtonPressed(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            dashboard.setVisible(false);
            travelMenu.setVisible(false);
            addOffersMenu.setVisible(false);
            reservationMenu.setVisible(false);
            userInformationScene.setVisible(false);
            workersManagerMenu.setVisible(true);
            initWorker(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void offerButtonPressed(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            dashboard.setVisible(false);
            travelMenu.setVisible(false);
            addOffersMenu.setVisible(true);
            reservationMenu.setVisible(false);
            userInformationScene.setVisible(false);
            workersManagerMenu.setVisible(false);
            initOfferScene(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void reservationButtonPressed(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            dashboard.setVisible(false);
            travelMenu.setVisible(false);
            addOffersMenu.setVisible(false);
            reservationMenu.setVisible(true);
            userInformationScene.setVisible(false);
            workersManagerMenu.setVisible(false);
            initOfferidReservation(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void settingsButtonPressed(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            dashboard.setVisible(false);
            travelMenu.setVisible(false);
            addOffersMenu.setVisible(false);
            reservationMenu.setVisible(false);
            userInformationScene.setVisible(true);
            workersManagerMenu.setVisible(false);
            initSETTINGS(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initDashboardData(Connection conn) throws SQLException {
        conn = connectDB.getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT SUM(wrk_salary) FROM worker";
        ResultSet result = stmt.executeQuery(query);
        float costs = 0;
        while (result.next()) {
            costs = result.getFloat("SUM(wrk_salary)");
            outcomes.setText(String.format(Locale.GERMAN, "%,.2f", costs));
        }

        query = "SELECT SUM(res_off_depoit) FROM reservation_offers";
        result = stmt.executeQuery(query);
        float incomesVariable = 0;
        while (result.next()) {
            incomesVariable = result.getFloat("SUM(res_off_depoit)");
        }
        query = "SELECT SUM(tr_cost) FROM trip JOIN reservation ON trip.tr_id = reservation.res_tr_id";
        result = stmt.executeQuery(query);
        while (result.next()) {
            incomesVariable += result.getFloat("SUM(tr_cost)");
        }
        incomes.setText(String.format(Locale.GERMAN, "%,.2f", incomesVariable));

        float profitVariable = incomesVariable - costs;
        profit.setText(String.format(Locale.GERMAN, "%,.2f", profitVariable));
        if (profitVariable < 0) {
            profit.setStyle("-fx-text-fill: red;");
        } else {
            profit.setStyle("-fx-text-fill: green;");
        }

        query = "SELECT COUNT(*) FROM driver";
        result = stmt.executeQuery(query);
        while (result.next()) {

            drivers_number_summary.setText(String.format(Locale.GERMAN, "%,d", result.getInt("COUNT(*)")));
        }

        query = "SELECT COUNT(*) FROM guide";
        result = stmt.executeQuery(query);
        while (result.next()) {
            guide_number_summary.setText(String.format(Locale.GERMAN, "%,d", result.getInt("COUNT(*)")));
        }

        query = "SELECT COUNT(*) FROM admin WHERE adm_type = 'LOGISTICS'";
        result = stmt.executeQuery(query);
        while (result.next()) {
            logistics_number_summary.setText(String.format(Locale.GERMAN, "%,d", result.getInt("COUNT(*)")));
        }

        query = "SELECT COUNT(*) FROM admin WHERE adm_type = 'ADMINISTRATIVE'";
        result = stmt.executeQuery(query);
        while (result.next()) {
            admin_number_summary.setText(String.format(Locale.GERMAN, "%,d", result.getInt("COUNT(*)")));
        }

        query = "SELECT COUNT(*) FROM admin WHERE adm_type = 'ACCOUNTING'";
        result = stmt.executeQuery(query);
        while (result.next()) {
            admin_number_summary.setText(String.format(Locale.GERMAN, "%,d", result.getInt("COUNT(*)")));
        }

        query = "SELECT COUNT(*) FROM reservation";
        int reserv = 0;
        result = stmt.executeQuery(query);
        while (result.next()) {
            reserv = result.getInt("COUNT(*)");
        }
        query = "SELECT COUNT(*) FROM reservation_offers";
        result = stmt.executeQuery(query);
        while (result.next()) {
            reserv += result.getInt("COUNT(*)");
        }
        reservationNumber.setText(String.format(Locale.GERMAN, "%,d", reserv));

        query = "SELECT COUNT(DISTINCT dst_name) FROM destination WHERE dst_location IS NOT NULL";
        result = stmt.executeQuery(query);
        while (result.next()) {
            destinationNumber.setText(String.format(Locale.GERMAN, "%,d", result.getInt("COUNT(DISTINCT dst_name)")));
        }

    }

    /* TRIP TAB */

    private void initTripIdTrips(Connection conn) throws SQLException {
        tripidListTrips.getItems().clear();
        String query = "SELECT tr_id FROM trip ORDER BY tr_id";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            tripidListTrips.getItems().add(Integer.toString(result.getInt("tr_id")));
        }
    }

    private void initGuidetrips(Connection conn) throws SQLException {
        guideListTrips.getItems().clear();
        String query = "SELECT DISTINCT CONCAT(wrk_name, ' ', wrk_lname) AS name " +
                "FROM guide g JOIN worker w " +
                "ON g.gui_AT = w.wrk_AT " +
                "ORDER BY CONCAT(wrk_name, ' ', wrk_lname);";

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            guideListTrips.getItems().add(result.getString("name"));
        }
    }

    private void initDrivertrips(Connection conn) throws SQLException {
        driverListTrips.getItems().clear();
        String query = "SELECT DISTINCT CONCAT(wrk_name, ' ', wrk_lname) AS name " +
                "FROM driver d JOIN worker w " +
                "ON d.drv_AT = w.wrk_AT " +
                "ORDER BY CONCAT(wrk_name, ' ', wrk_lname)";

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            driverListTrips.getItems().add(result.getString("name"));
        }
    }

    private void initBranchtrips(Connection conn) throws SQLException {
        branchListTrip.getItems().clear();
        String query = "SELECT CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) AS address FROM branch ORDER BY address;";

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            branchListTrip.getItems().add(result.getString("address"));
        }
    }

    private void initTripTable(Connection conn) throws SQLException {
        tableTrip.setEditable(true);

        String query = "SELECT DISTINCT CONCAT(wrk_name, ' ', wrk_lname) AS name " +
                "FROM guide g JOIN worker w " +
                "ON g.gui_AT = w.wrk_AT " +
                "ORDER BY CONCAT(wrk_name, ' ', wrk_lname);";

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        ObservableList<String> guides = FXCollections.observableArrayList();
        while (result.next()) {
            guides.add(result.getString("name"));
        }

        query = "SELECT CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) AS address " +
                "FROM branch ORDER BY address";

        result = stmt.executeQuery(query);

        ObservableList<String> branches = FXCollections.observableArrayList();
        while (result.next()) {
            branches.add(result.getString("address"));
        }

        query = "SELECT DISTINCT CONCAT(wrk_name, ' ', wrk_lname) AS name " +
                "FROM driver d JOIN worker w " +
                "ON d.drv_AT = w.wrk_AT " +
                "ORDER BY CONCAT(wrk_name, ' ', wrk_lname)";

        result = stmt.executeQuery(query);

        ObservableList<String> drivers = FXCollections.observableArrayList();
        while (result.next()) {
            drivers.add(result.getString("name"));
        }

        tripidTableTrips.setCellValueFactory(new PropertyValueFactory<Trip, Integer>("trip_id"));
        departureTableTrips.setCellValueFactory(new PropertyValueFactory<Trip, String>("departure"));
        returnTableTrips.setCellValueFactory(new PropertyValueFactory<Trip, String>("return_"));

        priceTableTrips.setCellValueFactory(new PropertyValueFactory<Trip, Float>("cost"));
        priceTableTrips.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        priceTableTrips.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Trip, Float>>() {

            @Override
            public void handle(CellEditEvent<Trip, Float> arg0) {
                try (Connection conn1 = connectDB.getConnection()) {
                    Trip trip = arg0.getRowValue();
                    int id = trip.getTrip_id();

                    String updateQuery = "SET @USER = ?";
                    PreparedStatement preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, userInformation.getLastname());
                    preparedStmt.executeQuery();

                    updateQuery = "UPDATE trip SET tr_cost = ? WHERE tr_id = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setFloat(1, arg0.getNewValue());
                    preparedStmt.setInt(2, id);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        seatsTableTrips.setCellValueFactory(new PropertyValueFactory<Trip, Integer>("seats"));
        seatsTableTrips.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        seatsTableTrips.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Trip, Integer>>() {

            @Override
            public void handle(CellEditEvent<Trip, Integer> arg0) {
                try (Connection conn1 = connectDB.getConnection()) {
                    Trip trip = arg0.getRowValue();
                    int id = trip.getTrip_id();

                    String updateQuery = "SET @USER = ?";
                    PreparedStatement preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, userInformation.getLastname());
                    preparedStmt.executeQuery();

                    updateQuery = "UPDATE trip SET tr_maxseats = ? WHERE tr_id = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setInt(1, arg0.getNewValue());
                    preparedStmt.setInt(2, id);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        branchTableTrip.setCellValueFactory(new PropertyValueFactory<Trip, String>("branch"));
        branchTableTrip.setCellFactory(ComboBoxTableCell.forTableColumn(branches));
        branchTableTrip.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Trip, String>>() {

            @Override
            public void handle(CellEditEvent<Trip, String> arg0) {
                try (Connection conn1 = connectDB.getConnection()) {
                    Trip trip = arg0.getRowValue();
                    int id = trip.getTrip_id();

                    String updateQuery = "SET @USER = ?";
                    PreparedStatement preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, userInformation.getLastname());
                    preparedStmt.executeQuery();

                    updateQuery = "SELECT br_code FROM branch WHERE CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) LIKE ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, arg0.getNewValue());
                    ResultSet result = preparedStmt.executeQuery();
                    result.next();
                    int branchId = result.getInt("br_code");

                    updateQuery = "UPDATE trip SET tr_br_code = ? WHERE tr_id = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setInt(1, branchId);
                    preparedStmt.setInt(2, id);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        guideTableTrip.setCellValueFactory(new PropertyValueFactory<Trip, String>("guide"));
        guideTableTrip.setCellFactory(ComboBoxTableCell.forTableColumn(guides));
        guideTableTrip.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Trip, String>>() {

            @Override
            public void handle(CellEditEvent<Trip, String> arg0) {
                try (Connection conn1 = connectDB.getConnection()) {
                    Trip trip = arg0.getRowValue();
                    int id = trip.getTrip_id();

                    String updateQuery = "SET @USER = ?";
                    PreparedStatement preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, userInformation.getLastname());
                    preparedStmt.executeQuery();

                    updateQuery = "SELECT gui_AT FROM guide g JOIN worker w ON g.gui_AT = w.wrk_AT WHERE CONCAT(wrk_name, ' ', wrk_lname) = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, arg0.getNewValue());
                    ResultSet result = preparedStmt.executeQuery();
                    result.next();
                    String guideAT = result.getString("gui_AT");

                    updateQuery = "UPDATE trip SET tr_gui_AT = ? WHERE tr_id = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, guideAT);
                    preparedStmt.setInt(2, id);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        driverTableTrips.setCellValueFactory(new PropertyValueFactory<Trip, String>("driver"));
        driverTableTrips.setCellFactory(ComboBoxTableCell.forTableColumn(drivers));
        driverTableTrips.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Trip, String>>() {

            @Override
            public void handle(CellEditEvent<Trip, String> arg0) {
                try (Connection conn1 = connectDB.getConnection()) {
                    Trip trip = arg0.getRowValue();
                    int id = trip.getTrip_id();

                    String updateQuery = "SET @USER = ?";
                    PreparedStatement preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, userInformation.getLastname());
                    preparedStmt.executeQuery();

                    updateQuery = "SELECT drv_AT FROM driver d JOIN worker w ON d.drv_AT = w.wrk_AT WHERE CONCAT(wrk_name, ' ', wrk_lname) = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, arg0.getNewValue());
                    ResultSet result = preparedStmt.executeQuery();
                    result.next();
                    String driverAT = result.getString("drv_AT");

                    updateQuery = "UPDATE trip SET tr_drv_AT = ? WHERE tr_id = ?";
                    preparedStmt = conn1.prepareStatement(updateQuery);
                    preparedStmt.setString(1, driverAT);
                    preparedStmt.setInt(2, id);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        stmt.close();
    }

    public void searchTrip(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            tableTrip.getItems().clear();
            String tripid = tripidListTrips.getValue();
            LocalDate departure = departureDateTrips.getValue();
            LocalDate ret = returnDateTrip.getValue();
            String cost = costTextTrips.getText();
            String seats = seatTextTrip.getText();
            String branch = branchListTrip.getValue();
            String guide = guideListTrips.getValue();
            String driver = driverListTrips.getValue();

            String query = "SELECT gui_AT FROM guide g JOIN worker w ON g.gui_AT = w.wrk_AT WHERE CONCAT(wrk_name, ' ', wrk_lname) = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, guide);
            ResultSet result = stmt.executeQuery();
            String guiAT = null;
            while (result.next()) {
                guiAT = result.getString("gui_AT");
            }

            query = "SELECT drv_AT FROM driver d JOIN worker w ON d.drv_AT = w.wrk_AT WHERE CONCAT(wrk_name, ' ', wrk_lname) = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, driver);
            result = stmt.executeQuery();
            String drvAT = null;
            while (result.next()) {
                drvAT = result.getString("drv_AT");
            }

            query = "SELECT br_code FROM branch WHERE CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, branch);
            result = stmt.executeQuery();
            String branch_code = null;
            while (result.next()) {
                branch_code = result.getString("br_code");
            }

            ArrayList<String> listOfWhereClause = new ArrayList<>();
            if (tripid != null)
                listOfWhereClause.add("tr_id=" + tripid);
            if (!costTextTrips.getText().isEmpty())
                listOfWhereClause.add("tr_cost=" + cost);
            if (!seatTextTrip.getText().isEmpty())
                listOfWhereClause.add("tr_maxseats=" + seats);
            if (branch_code != null)
                listOfWhereClause.add("tr_br_code='" + branch_code + "'");
            if (guiAT != null)
                listOfWhereClause.add("tr_gui_AT='" + guiAT + "'");
            if (drvAT != null)
                listOfWhereClause.add("tr_drv_AT='" + drvAT + "'");
            if (departure != null && ret != null)
                listOfWhereClause.add("tr_departure BETWEEN '" + departure + " 00:00:00' " + " AND '" + departure
                        + " 23:59:59' AND tr_return BETWEEN " + ret + " 00:00:00' AND '" + ret + " 23:59:59'");
            else if (departure != null)
                listOfWhereClause
                        .add("tr_departure BETWEEN '" + departure + " 00:00:00' AND '" + departure + " 23:59:59'");
            else if (ret != null)
                listOfWhereClause.add("tr_return BETWEEN '" + ret + " 00:00:00'" + " AND  '" + ret + " 23:59:59'");

            String whereClause = String.join(" AND ", listOfWhereClause);

            query = "SELECT t.tr_id, t.tr_departure, t.tr_return, t.tr_maxseats, t.tr_cost, " +
                    "CONCAT(b.br_city, ', ', b.br_street, ' ', b.br_num) AS address," +
                    " CONCAT(w.wrk_name, ' ', w.wrk_lname) AS guide, " +
                    "CONCAT(w1.wrk_name, ' ', w1.wrk_lname) AS driver " +
                    "FROM trip t JOIN branch b ON t.tr_br_code = b.br_code" +
                    " JOIN worker w ON t.tr_gui_AT = w.wrk_AT " +
                    "JOIN worker w1 ON t.tr_drv_AT = w1.wrk_AT";

            if (!whereClause.isEmpty())
                query += " WHERE " + whereClause;

            result = stmt.executeQuery(query);

            while (result.next()) {
                int trip_id_temp = result.getInt("t.tr_id");
                String departure_temp = result.getString("t.tr_departure");
                String return__temp = result.getString("t.tr_return");
                float cost_temp = result.getFloat("t.tr_cost");
                int seats_temp = result.getInt("t.tr_maxseats");
                String branch_temp = result.getString("address");
                String guide_temp = result.getString("guide");
                String driver_temp = result.getString("driver");

                tableTrip.getItems().add(new Trip(trip_id_temp, departure_temp, return__temp, cost_temp, seats_temp,
                        branch_temp, guide_temp, driver_temp));
            }

        } catch (SQLException ex) {
            ex.getSQLState();
        }
    }

    public void addTrip(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            java.sql.Timestamp departure = java.sql.Timestamp.from(
                    departureDateTrips.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            java.sql.Timestamp ret = java.sql.Timestamp.from(
                    returnDateTrip.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            String cost = costTextTrips.getText();
            String seats = seatTextTrip.getText();
            String branch = branchListTrip.getValue();
            String guide = guideListTrips.getValue();
            String driver = driverListTrips.getValue();
            String query = "SELECT gui_AT FROM guide g JOIN worker w ON g.gui_AT = w.wrk_AT WHERE CONCAT(wrk_name, ' ', wrk_lname) = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, guide);
            ResultSet result = stmt.executeQuery();
            String guiAT = null;
            while (result.next()) {
                guiAT = result.getString("gui_AT");
            }

            query = "SELECT drv_AT FROM driver d JOIN worker w ON d.drv_AT = w.wrk_AT WHERE CONCAT(wrk_name, ' ', wrk_lname) = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, driver);
            result = stmt.executeQuery();
            String drvAT = null;
            while (result.next()) {
                drvAT = result.getString("drv_AT");
            }

            query = "SELECT br_code FROM branch WHERE CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, branch);
            result = stmt.executeQuery();
            String branch_code = null;
            while (result.next()) {
                branch_code = result.getString("br_code");
            }

            stmt.executeQuery("SET @USER = '" + userInformation.getLastname() + "'");
            query = "INSERT INTO trip VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setTimestamp(1, departure);
            stmt.setTimestamp(2, ret);
            stmt.setInt(3, Integer.parseInt(seats));
            stmt.setFloat(4, Float.parseFloat(cost));
            stmt.setString(5, branch_code);
            stmt.setString(6, guiAT);
            stmt.setString(7, drvAT);
            stmt.executeUpdate();

            initBranchtrips(conn);
            initDrivertrips(conn);
            initGuidetrips(conn);
            initTripIdTrips(conn);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή ταξιδιου!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής ταξιδιού");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Η ημερομηνία είναι υποχρεωτική");
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }

    }

    public void clearBottonTrips(ActionEvent e) {
        tripidListTrips.setValue(null);
        guideListTrips.setValue(null);
        driverListTrips.setValue(null);
        branchListTrip.setValue(null);
        departureDateTrips.setValue(null);
        returnDateTrip.setValue(null);
        costTextTrips.clear();
        seatTextTrip.clear();
        tableTrip.getItems().clear();
    }

    /* EVENTS TAB */

    private void initTripIdEvents(Connection conn) throws SQLException {
        tripidListEvents.getItems().clear();
        String query = "SELECT DISTINCT ev_tr_id FROM event ORDER BY ev_tr_id";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            tripidListEvents.getItems().add(Integer.toString(result.getInt("ev_tr_id")));
        }
    }

    public void searchEvents(ActionEvent e) {
        tableEvents.getItems().clear();
        try (Connection conn = connectDB.getConnection()) {
            String tripId = tripidListEvents.getValue();
            LocalDate departure = departurePickerEvents.getValue();
            LocalDate ret = returnPickerEvents.getValue();
            String description = descriptionTextEvents.getText();

            ArrayList<String> listWhereClause = new ArrayList<>();
            if (tripId != null)
                listWhereClause.add("ev_tr_id = " + tripId);
            if (departure != null && ret != null)
                listWhereClause.add("ev_start BETWEEN '" + departure + " 00:00:00' " + " AND '" + departure
                        + " 23:59:59' AND ev_end BETWEEN '" + ret + " 00:00:00' AND '" + ret + " 23:59:59'");
            else if (departure != null)
                listWhereClause
                        .add("ev_start BETWEEN '" + departure + " 00:00:00' AND '" + departure + " 23:59:59'");
            else if (ret != null)
                listWhereClause.add("ev_end BETWEEN '" + ret + " 00:00:00'" + " AND  '" + ret + " 23:59:59'");
            if (!descriptionTextDest.getText().isEmpty())
                listWhereClause.add("ev_descr = '" + description + "'");

            String whereClause = String.join(" AND ", listWhereClause);

            String query = "SELECT * FROM event";

            if (!whereClause.isEmpty())
                query += " WHERE " + whereClause;

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            tripIdTableEvents.setCellValueFactory(new PropertyValueFactory<Event, Integer>("tripId"));
            departureTableEvents.setCellValueFactory(new PropertyValueFactory<Event, String>("start"));
            returnTableEvents.setCellValueFactory(new PropertyValueFactory<Event, String>("end"));
            descriptionTableEvents.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));

            while (result.next()) {
                int trip_id = result.getInt("ev_tr_id");
                String start = result.getString("ev_start");
                String end = result.getString("ev_end");
                String desc = result.getString("ev_descr");

                tableEvents.getItems().add(new Event(trip_id, start, end, desc));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void addEventsClicked(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String tripId = tripidListEvents.getValue();
            java.sql.Timestamp departure = java.sql.Timestamp.from(
                    departurePickerEvents.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault())
                            .toInstant());
            java.sql.Timestamp ret = java.sql.Timestamp.from(
                    returnPickerEvents.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            String description = descriptionTextEvents.getText();

            PreparedStatement stmt = conn.prepareStatement("SET @USER = ?");
            stmt.setString(1, userInformation.getLastname());
            stmt.executeQuery();
            String query = "INSERT INTO event VALUES(?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(tripId));
            stmt.setTimestamp(2, departure);
            stmt.setTimestamp(3, ret);
            stmt.setString(4, description);
            stmt.executeUpdate();

            stmt.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή εκδήλωσης!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής εκδήλωσης");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Η ημερομηνία είναι υποχρεωτική");
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }
    }

    public void clearEventsClicked(ActionEvent e) {
        tripidListEvents.setValue(null);
        departurePickerEvents.setValue(null);
        returnPickerEvents.setValue(null);
        descriptionTextEvents.clear();
        ;
    }

    /* DESTINATION TAB */

    private void initTripIdDestination(Connection conn) throws SQLException {
        tripidListDest.getItems().clear();
        String query = "SELECT DISTINCT to_tr_id FROM destination dest JOIN travel_to tro ON dest.dst_id = tro.to_dst_id ORDER BY to_tr_id";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            tripidListDest.getItems().add(Integer.toString(result.getInt("to_tr_id")));
        }
    }

    private void initTypeDestination(Connection conn) throws SQLException {
        typeListDest.getItems().clear();
        String query = "SELECT DISTINCT dsrt_type FROM destination";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            typeListDest.getItems().add(result.getString("dsrt_type"));
        }
    }

    private void initLocationDestination(Connection conn) throws SQLException {
        locationListDest.getItems().clear();
        String query = "SELECT DISTINCT dst_name FROM destination WHERE dst_location IS NULL";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        locationListDest.getItems().add("");
        while (result.next()) {
            locationListDest.getItems().add(result.getString("dst_name"));
        }
    }

    private void initLanguageDestination(Connection conn) throws SQLException {
        languageListDest.getItems().clear();
        String query = "SELECT DISTINCT dst_language FROM destination ORDER BY dst_language";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            languageListDest.getItems().add(result.getString("dst_language"));
        }
    }

    public void searchDest(ActionEvent e) {
        tableDest.getItems().clear();
        try (Connection conn = connectDB.getConnection()) {
            String trip_id = tripidListDest.getValue();
            String name = nameFieldDest.getText();
            LocalDate departure = departurePickerDest.getValue();
            LocalDate ret = arrivalPickerDest.getValue();
            String type = typeListDest.getValue();
            String location = locationListDest.getValue();
            String language = languageListDest.getValue();
            String description = descriptionTextDest.getText();

            String query = "SELECT tro.to_tr_id, d.dst_name, tro.to_departure, tro.to_arrival," +
                    " d.dsrt_type, d.dst_language, d.dst_descr, d1.dst_name FROM destination d JOIN " +
                    "travel_to tro ON d.dst_id = tro.to_dst_id JOIN destination d1 ON d.dst_location = d1.dst_id";

            ArrayList<String> listWhereClause = new ArrayList<>();
            if (trip_id != null)
                listWhereClause.add("tro.to_tr_id = " + trip_id);
            if (!nameFieldDest.getText().isEmpty())
                listWhereClause.add("d.dst_name = '" + name + "'");
            if (departure != null && ret != null)
                listWhereClause.add("ev_start BETWEEN '" + departure + " 00:00:00' " + " AND '" + departure
                        + " 23:59:59' AND ev_end BETWEEN " + ret + " 00:00:00' AND '" + ret + " 23:59:59'");
            else if (departure != null)
                listWhereClause
                        .add("ev_start BETWEEN '" + departure + " 00:00:00' AND '" + departure + " 23:59:59'");
            else if (ret != null)
                listWhereClause.add("ev_end BETWEEN '" + ret + " 00:00:00'" + " AND  '" + ret + " 23:59:59'");
            if (type != null)
                listWhereClause.add("d.dsrt_type = '" + type + "'");
            if (location != null)
                listWhereClause.add("d1.dst_name = '" + location + "'");
            if (language != null)
                listWhereClause.add("d.dst_language = '" + language + "'");
            if (!descriptionTextDest.getText().isEmpty())
                listWhereClause.add("d.dst_descr LIKE '" + description + "'");

            String whereClause = String.join(" AND ", listWhereClause);

            if (!whereClause.isEmpty())
                query += " WHERE " + whereClause;

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            tripidTableDest.setCellValueFactory(new PropertyValueFactory<Destination, Integer>("trip_id"));
            nameTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("name"));
            departureTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("departure"));
            arrivalTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("arrival"));
            typeTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("type"));
            locationTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("location"));
            languageTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("language"));
            descriptionTableDest.setCellValueFactory(new PropertyValueFactory<Destination, String>("description"));

            while (result.next()) {
                int tripID = result.getInt("tro.to_tr_id");
                String nameString = result.getString("d.dst_name");
                String departureString = result.getString("tro.to_departure");
                String arrivalString = result.getString("tro.to_arrival");
                String typeString = result.getString("d.dsrt_type");
                String locationString = result.getString("d1.dst_name");
                String languageString = result.getString("d.dst_language");
                String descriptionString = result.getString("d.dst_descr");

                tableDest.getItems().add(new Destination(tripID, nameString, departureString, arrivalString, typeString,
                        locationString, languageString, descriptionString));
            }
            // stmt.close();
        } catch (SQLException ex) {
            ex.getSQLState();
        }
    }

    public void addDest(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String trip_id = tripidListDest.getValue();
            String name = nameFieldDest.getText();
            java.sql.Timestamp departure = java.sql.Timestamp.from(
                    departurePickerDest.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault())
                            .toInstant());
            java.sql.Timestamp arrival = java.sql.Timestamp.from(
                    arrivalPickerDest.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            String type = typeListDest.getValue();
            String location = locationListDest.getValue();
            String language = languageListDest.getValue();
            String description = descriptionTextDest.getText();

            Integer location_id = null;
            PreparedStatement stmt = conn.prepareStatement("SET @USER = ?");
            stmt.setString(1, userInformation.getLastname());
            stmt.executeQuery();
            String query = "SELECT dst_id FROM destination WHERE dst_name LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, location);
            ResultSet result = stmt.executeQuery();
            if (result.next())
                location_id = result.getInt("dst_id");

            if (location_id != null) {
                query = "INSERT INTO destination(dst_name, dst_descr, dsrt_type, dst_language, dst_location) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setString(3, type);
                stmt.setString(4, language);
                stmt.setInt(5, location_id);
            } else {
                query = "INSERT INTO destination(dst_name, dst_descr, dsrt_type, dst_language, dst_location) VALUES (?, ?, ?, ?, NULL)";
                stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setString(3, type);
                stmt.setString(4, language);
            }

            stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();

            query = "INSERT INTO travel_to VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(trip_id));
            genKeys.next();
            stmt.setInt(2, genKeys.getInt(1));
            stmt.setTimestamp(3, arrival);
            stmt.setTimestamp(4, departure);

            stmt.executeUpdate();
            stmt.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή προορισμού!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής προορισμού");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Η ημερομηνία είναι υποχρεωτική");
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }
    }

    public void clearDest(ActionEvent e) {
        tripidListDest.setValue(null);
        nameFieldDest.setText("");
        departurePickerDest.setValue(null);
        arrivalPickerDest.setValue(null);
        typeListDest.setValue(null);
        locationListDest.setValue(null);
        languageListDest.setValue(null);
        descriptionTextDest.clear();
        tableDest.getItems().clear();
    }

    // WORKERS SCENE
    private void initTableOfWorker(Connection conn) throws SQLException {
        tableWorker.getItems().clear();
        String query = "SELECT w.wrk_AT, " +
                "w.wrk_name, " +
                "w.wrk_lname, " +
                "w.wrk_salary, " +
                "CONCAT(b.br_city, ', ', b.br_street, ' ', IF(b.br_num IS NULL, '-', b.br_num)) AS branchName, " +
                "'DRIVER' AS type " +
                "FROM worker w JOIN branch b	ON w.wrk_br_code = b.br_code " +
                "JOIN driver d ON d.drv_AT = w.wrk_AT " +
                "UNION " +
                "SELECT w.wrk_AT, " +
                "w.wrk_name, " +
                "w.wrk_lname, " +
                "w.wrk_salary, " +
                "CONCAT(b.br_city, ', ', b.br_street, ' ', IF(b.br_num IS NULL, '-', b.br_num)) AS branchName, " +
                "'GUIDE' " +
                "FROM worker w JOIN branch b ON w.wrk_br_code = b.br_code " +
                "JOIN guide g ON g.gui_AT = w.wrk_AT " +
                "UNION " +
                "SELECT w.wrk_AT, " +
                "w.wrk_name, " +
                "w.wrk_lname, " +
                "w.wrk_salary, " +
                "CONCAT(b.br_city, ', ', b.br_street, ' ', IF(b.br_num IS NULL, '-', b.br_num)) AS branchName, " +
                "ad.adm_type " +
                "FROM worker w JOIN branch b	ON w.wrk_br_code = b.br_code " +
                "JOIN admin ad ON ad.adm_AT = w.wrk_AT " +
                "UNION " +
                "SELECT w.wrk_AT, " +
                "w.wrk_name, " +
                "w.wrk_lname, " +
                "w.wrk_salary, " +
                "CONCAT(b.br_city, ', ', b.br_street, ' ', IF(b.br_num IS NULL, '-', b.br_num)) AS branchName, " +
                "'WORKER'" +
                "FROM worker w JOIN branch b	" +
                "ON w.wrk_br_code = b.br_code " +
                "WHERE w.wrk_AT NOT IN ( " +
                "SELECT w.wrk_AT " +
                "FROM worker w JOIN driver d ON d.drv_AT = w.wrk_AT " +
                "UNION " +
                "SELECT w.wrk_AT " +
                "FROM worker w JOIN guide g ON g.gui_AT = w.wrk_AT " +
                "UNION " +
                "SELECT w.wrk_AT " +
                "FROM worker w JOIN admin ad ON ad.adm_AT = w.wrk_AT " +
                ") ORDER BY wrk_AT";

        atColumnWorker.setCellValueFactory(new PropertyValueFactory<Worker, String>("at"));
        nameColumnWorker.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
        lnameColumnWorker.setCellValueFactory(new PropertyValueFactory<Worker, String>("lname"));
        salaryColumnWorker.setCellValueFactory(new PropertyValueFactory<Worker, Float>("salry"));
        branchColumnWorker.setCellValueFactory(new PropertyValueFactory<Worker, String>("branch"));
        typeColumnWorker.setCellValueFactory(new PropertyValueFactory<Worker, String>("type"));

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            String AT = result.getString("wrk_AT");
            String name = result.getString("wrk_name");
            String lastname = result.getString("wrk_lname");
            float salary = result.getFloat("wrk_salary");
            String branch = result.getString("branchName");
            String type = result.getString("type");

            tableWorker.getItems().add(new Worker(AT, name, lastname, branch, salary, type));
        }
        stmt.close();
    }

    private void initWorker(Connection conn) throws SQLException {
        tableWorker.getItems().clear();
        branchAddWorker.getItems().clear();
        typeAddWorker.getItems().clear();
        adminTypeAddWorker.getItems().clear();
        licenseAddWorker.getItems().clear();
        routeAddWorker.getItems().clear();
        String query = "SELECT CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) FROM branch";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        // final String selectedValue;
        while (result.next()) {
            branchAddWorker.getItems().add(result.getString(1));
            adminBranchAddWorker.getItems().add(result.getString(1));
        }
        initTableOfWorker(conn);

        typeAddWorker.getItems().add("Worker");
        typeAddWorker.getItems().add("Driver");
        typeAddWorker.getItems().add("Administrator");
        typeAddWorker.getItems().add("Guide");

        licenseAddWorker.getItems().add("A");
        licenseAddWorker.getItems().add("B");
        licenseAddWorker.getItems().add("C");
        licenseAddWorker.getItems().add("D");

        routeAddWorker.getItems().add("LOCAL");
        routeAddWorker.getItems().add("ABROAD");

        adminTypeAddWorker.getItems().add("LOGISTICS");
        adminTypeAddWorker.getItems().add("ADMINISTRATIVE");
        adminTypeAddWorker.getItems().add("ACCOUNTING");

        guideLabel.setVisible(false);
        languageLabel.setVisible(false);
        cvAddWorker.setVisible(false);
        languageAddWorker.setVisible(false);

        licenseLabel.setVisible(false);
        routeLabel.setVisible(false);
        experienceLabel.setVisible(false);
        driverLabel.setVisible(false);
        licenseAddWorker.setVisible(false);
        routeAddWorker.setVisible(false);
        experienceAddWorker.setVisible(false);

        adminLabel.setVisible(false);
        typeLabel.setVisible(false);
        branchLabel.setVisible(false);
        diplomaLabel.setVisible(false);
        adminTypeAddWorker.setVisible(false);
        adminBranchAddWorker.setVisible(false);
        diplomaAddWorker.setVisible(false);

        typeAddWorker.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedValue = newValue;
            switch (selectedValue) {
                case "Worker":
                    guideLabel.setVisible(false);
                    languageLabel.setVisible(false);
                    cvAddWorker.setVisible(false);
                    languageAddWorker.setVisible(false);

                    licenseLabel.setVisible(false);
                    routeLabel.setVisible(false);
                    experienceLabel.setVisible(false);
                    driverLabel.setVisible(false);
                    licenseAddWorker.setVisible(false);
                    routeAddWorker.setVisible(false);
                    experienceAddWorker.setVisible(false);

                    adminLabel.setVisible(false);
                    typeLabel.setVisible(false);
                    branchLabel.setVisible(false);
                    diplomaLabel.setVisible(false);
                    adminTypeAddWorker.setVisible(false);
                    adminBranchAddWorker.setVisible(false);
                    diplomaAddWorker.setVisible(false);
                    break;
                case "Driver":
                    guideLabel.setVisible(false);
                    languageLabel.setVisible(false);
                    cvAddWorker.setVisible(false);
                    languageAddWorker.setVisible(false);

                    adminLabel.setVisible(false);
                    typeLabel.setVisible(false);
                    branchLabel.setVisible(false);
                    diplomaLabel.setVisible(false);
                    adminTypeAddWorker.setVisible(false);
                    adminBranchAddWorker.setVisible(false);
                    diplomaAddWorker.setVisible(false);

                    licenseLabel.setVisible(true);
                    routeLabel.setVisible(true);
                    experienceLabel.setVisible(true);
                    driverLabel.setVisible(true);
                    licenseAddWorker.setVisible(true);
                    routeAddWorker.setVisible(true);
                    experienceAddWorker.setVisible(true);
                    break;
                case "Administrator":
                    guideLabel.setVisible(false);
                    languageLabel.setVisible(false);
                    cvAddWorker.setVisible(false);
                    languageAddWorker.setVisible(false);

                    adminLabel.setVisible(true);
                    typeLabel.setVisible(true);
                    branchLabel.setVisible(true);
                    diplomaLabel.setVisible(true);
                    adminTypeAddWorker.setVisible(true);
                    adminBranchAddWorker.setVisible(true);
                    diplomaAddWorker.setVisible(true);

                    licenseLabel.setVisible(false);
                    routeLabel.setVisible(false);
                    experienceLabel.setVisible(false);
                    driverLabel.setVisible(false);
                    licenseAddWorker.setVisible(false);
                    routeAddWorker.setVisible(false);
                    experienceAddWorker.setVisible(false);
                    break;
                case "Guide":
                    guideLabel.setVisible(true);
                    languageLabel.setVisible(true);
                    cvAddWorker.setVisible(true);
                    languageAddWorker.setVisible(true);

                    adminLabel.setVisible(false);
                    typeLabel.setVisible(false);
                    branchLabel.setVisible(false);
                    diplomaLabel.setVisible(false);
                    adminTypeAddWorker.setVisible(false);
                    adminBranchAddWorker.setVisible(false);
                    diplomaAddWorker.setVisible(false);

                    licenseLabel.setVisible(false);
                    routeLabel.setVisible(false);
                    experienceLabel.setVisible(false);
                    driverLabel.setVisible(false);
                    licenseAddWorker.setVisible(false);
                    routeAddWorker.setVisible(false);
                    experienceAddWorker.setVisible(false);
                    break;
            }
        });

        stmt.close();
    }

    public void addWorkerButtonClicked(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String name = nameAddWorker.getText();
            String lname = lnameAddWorker.getText();
            String idNumber = idAddWorker.getText();
            Float salary = Float.parseFloat(salaryAddWorker.getText());
            String worker_type = typeAddWorker.getValue().toString();
            int branch = 0;
            String branchName = branchAddWorker.getValue();

            String query = "SELECT br_code FROM branch WHERE CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) = '"
                    +
                    branchName + "'";

            Statement branchid = conn.createStatement();
            ResultSet result = branchid.executeQuery(query);
            while (result.next()) {
                branch = result.getInt("br_code");
            }

            query = "INSERT INTO worker VALUES (?, ?, ?, ?, ?)";

            switch (worker_type) {
                case "Worker":
                    PreparedStatement stmt1 = conn.prepareStatement(query);
                    stmt1.setString(1, idNumber);
                    stmt1.setString(2, name);
                    stmt1.setString(3, lname);
                    stmt1.setFloat(4, salary);
                    stmt1.setInt(5, branch);
                    stmt1.executeUpdate();
                    stmt1.close();
                    branchid.close();
                    break;
                case "Driver":
                    CallableStatement stmt = conn.prepareCall("CALL addNewDriver(?, ?, ?, ?, ?, ?, ?)");
                    stmt.setString(1, idNumber);
                    stmt.setString(2, name);
                    stmt.setString(3, lname);
                    stmt.setFloat(4, salary);
                    stmt.setString(5, licenseAddWorker.getValue());
                    stmt.setString(6, routeAddWorker.getValue());
                    stmt.setString(7, experienceAddWorker.getText());
                    stmt.executeUpdate();
                    stmt.close();
                    break;
                case "Administrator":
                    PreparedStatement stmt2 = conn.prepareStatement(query);
                    stmt2.setString(1, idNumber);
                    stmt2.setString(2, name);
                    stmt2.setString(3, lname);
                    stmt2.setFloat(4, salary);
                    stmt2.setInt(5, branch);
                    stmt2.executeUpdate();

                    query = "INSERT INTO admin VALUES(?, ?, ?)";
                    stmt2 = conn.prepareStatement(query);
                    stmt2.setString(1, idNumber);
                    stmt2.setString(2, adminTypeAddWorker.getValue());
                    stmt2.setString(3, diplomaAddWorker.getText());
                    stmt2.executeUpdate();

                    query = "SELECT br_code FROM branch WHERE CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) = '"
                            +
                            adminBranchAddWorker.getValue() + "'";

                    branchid = conn.createStatement();
                    result = branchid.executeQuery(query);
                    if (result.next()) {
                        branch = result.getInt("br_code");
                    }
                    branchid.close();

                    query = "INSERT INTO manages VALUES(?, ?)";
                    stmt2 = conn.prepareStatement(query);
                    stmt2.setString(1, idNumber);
                    stmt2.setInt(2, branch);
                    stmt2.executeUpdate();
                    stmt2.close();
                    break;
                case "Guide":
                    PreparedStatement stmt3 = conn.prepareStatement(query);
                    stmt3.setString(1, idNumber);
                    stmt3.setString(2, name);
                    stmt3.setString(3, lname);
                    stmt3.setFloat(4, salary);
                    stmt3.setInt(5, branch);
                    stmt3.executeUpdate();

                    query = "INSERT INTO guide VALUES(?, ?)";
                    stmt3 = conn.prepareStatement(query);
                    stmt3.setString(1, idNumber);
                    stmt3.setString(2, cvAddWorker.getText());
                    stmt3.executeUpdate();

                    query = "INSERT INTO languages VALUES(?, ?)";
                    stmt3 = conn.prepareStatement(query);
                    stmt3.setString(1, idNumber);
                    stmt3.setString(2, languageAddWorker.getText());
                    stmt3.executeUpdate();
                    stmt3.close();
                    break;
            }
            initTableOfWorker(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void clearButtonWorkersClicked(ActionEvent e) {
        nameAddWorker.clear();
        lnameAddWorker.clear();
        idAddWorker.clear();
        salaryAddWorker.clear();
        experienceAddWorker.clear();
        diplomaAddWorker.clear();
        cvAddWorker.clear();
        languageAddWorker.clear();

        branchAddWorker.setValue(null);
        typeAddWorker.setValue("Worker");
        licenseAddWorker.setValue(null);
        routeAddWorker.setValue(null);
        adminTypeAddWorker.setValue(null);
        adminBranchAddWorker.setValue(null);
    }

    // OFFER SCENE

    private void initOfferScene(Connection conn) throws SQLException {
        idListOffer.getItems().clear();
        destinationListOffer.getItems().clear();
        String query = "SELECT offer_id FROM offers ORDER BY offer_id";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            idListOffer.getItems().add(Integer.toString(result.getInt("offer_id")));
        }

        query = "SELECT DISTINCT dst_name FROM destination WHERE dst_location IS NOT NULL ORDER BY dst_name";
        result = stmt.executeQuery(query);

        while (result.next()) {
            destinationListOffer.getItems().add(result.getString("dst_name"));
        }

        stmt.close();
    }

    public void searchOfferBottonClicked(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            offerTable.getItems().clear();
            String id = idListOffer.getValue();
            LocalDate start = startDatePickerOffer.getValue();
            LocalDate finish = finishDatePickerOffer.getValue();
            String cost = costTextOffer.getText();
            String destination = destinationListOffer.getValue();

            String query = "SELECT offer_id, offer_start, offer_finish, offer_cost, dst_name FROM offers JOIN destination ON offer_dst_id = dst_id";

            ArrayList<String> listWhereClause = new ArrayList<>();
            if (id != null)
                listWhereClause.add(" offer_id = '" + id + "'");
            if (start != null && finish != null)
                listWhereClause.add("offer_start BETWEEN '" + start + " 00:00:00' " + " AND '" + start
                        + " 23:59:59' AND offer_finish BETWEEN " + finish + " 00:00:00' AND '" + finish + " 23:59:59'");
            else if (start != null)
                listWhereClause.add("offer_start BETWEEN '" + start + " 00:00:00' AND '" + start + " 23:59:59'");
            else if (finish != null)
                listWhereClause.add("ev_end BETWEEN '" + finish + " 00:00:00'" + " AND  '" + finish + " 23:59:59'");
            if (!costTextOffer.getText().isEmpty())
                listWhereClause.add(" offer_cost = " + cost);
            if (destination != null)
                listWhereClause.add(" dst_name LIKE '" + destination + "'");

            String whereClause = String.join(" AND ", listWhereClause);

            if (!whereClause.isEmpty())
                query += " WHERE " + whereClause;

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            idColumnOffer.setCellValueFactory(new PropertyValueFactory<Offers, String>("id"));
            startColumnOffer.setCellValueFactory(new PropertyValueFactory<Offers, String>("start"));
            finishColumnOffer.setCellValueFactory(new PropertyValueFactory<Offers, String>("finish"));
            costColumnOffer.setCellValueFactory(new PropertyValueFactory<Offers, Float>("cost"));
            destinationColumnOffer.setCellValueFactory(new PropertyValueFactory<Offers, String>("destination"));

            while (result.next()) {
                String myid = Integer.toString(result.getInt("offer_id"));
                String mystart = result.getString("offer_start");
                String myfinish = result.getString("offer_finish");
                float mycost = result.getFloat("offer_cost");
                String mydestination = result.getString("dst_name");

                offerTable.getItems().add(new Offers(myid, mystart, myfinish, mycost, mydestination));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addOfferBottonClicked(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            java.sql.Timestamp start = java.sql.Timestamp.from(
                    startDatePickerOffer.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault())
                            .toInstant());
            java.sql.Timestamp finish = java.sql.Timestamp.from(
                    finishDatePickerOffer.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault())
                            .toInstant());
            Float costOffer = Float.parseFloat(costTextOffer.getText());
            int destination = 0;

            String query = "SELECT dst_id FROM destination WHERE dst_name LIKE '" + destinationListOffer.getValue()
                    + "'";

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {
                destination = result.getInt("dst_id");
            }
            stmt.close();

            query = "INSERT INTO offers(offer_start, offer_finish, offer_cost, offer_dst_id) VALUES(?, ?,?, ?)";
            PreparedStatement stmt1 = conn.prepareStatement(query);
            stmt1.setTimestamp(1, start);
            stmt1.setTimestamp(2, finish);
            stmt1.setFloat(3, costOffer);
            stmt1.setInt(4, destination);
            stmt1.executeUpdate();
            stmt1.close();
            initOfferScene(conn);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή ταξιδιου!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής ταξιδιού");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Η ημερομηνία είναι υποχρεωτική");
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }
    }

    public void clearBottonOffer(ActionEvent e) {
        offerTable.getItems().clear();
        idListOffer.setValue(null);
        startDatePickerOffer.setValue(null);
        finishDatePickerOffer.setValue(null);
        costTextOffer.setText("");
        destinationListOffer.setValue(null);
    }

    // SETTINGS SCENE

    private void initSETTINGS(Connection conn) throws SQLException {
        TABLESET.getItems().clear();
        ATTEXTSET.setText(userInformation.getAT());
        NAMETEXTSET.setText(userInformation.getName());
        LNAMETEXTSET.setText(userInformation.getLastname());
        SALARYTEXTSET.setText(Double.toString(userInformation.getSalary()));
        PASSTEXTSET.setText(userInformation.getPassword());
        DATETEXTSET.setText(userInformation.getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
        ATTEXTSET.disableProperty();
        workersItComboBoxSettings.getItems().clear();

        Statement stmt = conn.createStatement();
        String query = "SELECT CONCAT(br_city, ', ', br_street,' ', IF(br_num IS NULL, '-', br_num)) FROM branch WHERE br_code = "
                + userInformation.getBranch();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {

            BRANCHTEXTSET.setText(result.getString(1));
        }

        USERCOLUMNSET.setCellValueFactory(new PropertyValueFactory<settings, String>("user"));
        CHANGESCOLUMNSET.setCellValueFactory(new PropertyValueFactory<settings, String>("change"));
        ACTIONCOLUMNSET.setCellValueFactory(new PropertyValueFactory<settings, String>("action"));
        TIMECOLUMNSET.setCellValueFactory(new PropertyValueFactory<settings, String>("date"));

        query = "SELECT user_AT, action,changes,stamp FROM log";
        result = stmt.executeQuery(query);

        while (result.next()) {
            String user = result.getString("user_AT");
            String action = result.getString("action");
            String changes = result.getString("changes");
            String date = result.getString("stamp");

            TABLESET.getItems().add(new settings(user, changes, action, date));
        }

        query = "SELECT CONCAT (wrk_name, ' ', wrk_lname) AS name FROM worker LEFT JOIN it ON worker.wrk_AT = it.IT_AT WHERE IT_AT IS NULL ORDER BY name";
        result = stmt.executeQuery(query);
        while (result.next()) {
            workersItComboBoxSettings.getItems().add(result.getString("name"));
        }

        stmt.close();
    }

    public void updateButtonSettingsClicked(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String at = ATTEXTSET.getText();
            float salary = Float.parseFloat(SALARYTEXTSET.getText());

            String query = "UPDATE worker SET wrk_salary = ? WHERE wrk_AT = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setFloat(1, salary);
            stmt.setString(2, at);
            stmt.executeUpdate();
            stmt.close();

            query = "SELECT wrk_salary " +
                    "FROM worker " +
                    "WHERE wrk_AT = '" + at + "'";
            Statement stmt2 = conn.createStatement();

            ResultSet result = stmt2.executeQuery(query);
            while (result.next()) {
                Float newSalary = result.getFloat("wrk_salary");
                userInformation.setSalary(newSalary);
            }
            SALARYTEXTSET.setText(Double.toString(userInformation.getSalary()));
            stmt2.close();
        } catch (SQLException ex) {
            ex.getSQLState();
        }
    }

    public void createITbuttonClicked(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String name = workersItComboBoxSettings.getValue();

            String query = "SELECT wrk_AT FROM worker WHERE CONCAT (wrk_name, ' ', wrk_lname) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            result.next();
            String AT = result.getString("wrk_AT");

            if(passwordITSeettings.getText().isEmpty()){
                query = "INSERT INTO it(IT_AT, start_date) VALUES (?, CURRENT_TIMESTAMP)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, AT);
            }else
            {
                query = "INSERT INTO it(IT_AT, password, start_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, AT);
                stmt.setString(2, passwordITSeettings.getText());
            }

            stmt.executeUpdate();
            stmt.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή IT!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής IT");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // RESERVATION SCENE
    private void initOfferidReservation(Connection conn) throws SQLException {
        offeridListRes.getItems().clear();
        idReservationRes.getItems().clear();
        ageListReservationRes.getItems().clear();

        String query = "SELECT DISTINCT res_off_id FROM reservation_offers ORDER BY res_off_id ";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            offeridListRes.getItems().add(Integer.toString(result.getInt("res_off_id")));
        }
        stmt.close();

        query = "SELECT tr_id FROM trip ORDER BY tr_id";
        stmt = conn.createStatement();
        result = stmt.executeQuery(query);
        while (result.next()) {
            idReservationRes.getItems().add(result.getInt("tr_id"));
        }
        stmt.close();

        ageListReservationRes.getItems().add("ADULT");
        ageListReservationRes.getItems().add("MINOR");

        imageReservationRes.setImage(new Image("photo/zoumborobot.jpg"));
    }

    /* RESERVATION TAB */

    public void addReservationRes(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            int tr_id = idReservationRes.getValue();
            String name = nameFieldReservationRes.getText();
            String lname = lnameFieldReservationRes.getText();
            String age = ageListReservationRes.getValue();
            int numSeat = Integer.parseInt(numSeatFielsReservationRes.getText());
            String query = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement("SET @USER = ?");
            stmt.setString(1, userInformation.getLastname());
            stmt.executeQuery();

            query = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, tr_id);
            stmt.setInt(2, numSeat);
            stmt.setString(3, name);
            stmt.setString(4, lname);
            stmt.setString(5, age);
            stmt.executeUpdate();
            stmt.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή προσφοράς!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής προσφοράς");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);

            if (ex.getErrorCode() == 1062) {
                alert.setContentText("Η θέση δεν είναι ελεύθερη, επιλέξτε κάποια άλλη.");
            } else {
                alert.setContentText(ex.toString());
            }

            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Η ημερομηνία είναι υποχρεωτική");
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }
    }

    public void clearReservationRes(ActionEvent e) {
        idReservationRes.setValue(null);
        nameFieldReservationRes.setText("");
        lnameFieldReservationRes.setText("");
        ageListReservationRes.setValue(null);
        numSeatFielsReservationRes.setText("");
    }

    public void deleteReservationRes(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String query = null;

            ArrayList<String> listWhereClause = new ArrayList<>();
            if (idReservationRes.getValue() != null)
                listWhereClause.add("res_tr_id = " + idReservationRes.getValue());
            if (!nameFieldReservationRes.getText().isEmpty())
                listWhereClause.add("res_name = '" + nameFieldReservationRes.getText() + "'");
            if (!lnameFieldReservationRes.getText().isEmpty())
                listWhereClause.add("res_lname = '" + lnameFieldReservationRes.getText() + "'");
            if (ageListReservationRes.getValue() != null)
                listWhereClause.add("res_isadult = '" + ageListReservationRes.getValue() + "'");
            if (!numSeatFielsReservationRes.getText().isEmpty())
                listWhereClause.add("res_seatnum = " + numSeatFielsReservationRes.getText());

            if (!listWhereClause.isEmpty())
                query = "DELETE FROM reservation WHERE " + String.join(" AND ", listWhereClause);

            Statement stmt = conn.createStatement();
            stmt.executeQuery("SET @USER = '" + userInformation.getLastname() + "'");
            stmt.executeUpdate(query);

            stmt.close();
        } catch (SQLException ex) {
            ex.getSQLState();
        }
    }

    /* OFFERS TAB */

    public void searchReservationOffer(ActionEvent e) {
        offerResTable.getItems().clear();
        try (Connection conn = connectDB.getConnection()) {
            String offer_id = offeridListRes.getValue();
            String name = nameTextRes.getText();
            String lname = lnameTextRes.getText();
            String deposit = depositTextRes.getText();

            String query = "SELECT res_off_tr_id, res_off_lname, res_off_name, res_off_id, res_off_depoit FROM reservation_offers ";

            ArrayList<String> listWhereClause = new ArrayList<>();
            if (offer_id != null)
                listWhereClause.add("res_off_id = " + offer_id);
            if (!nameTextRes.getText().isEmpty())
                listWhereClause.add("res_off_name = '" + name + "'");

            if (!lnameTextRes.getText().isEmpty())
                listWhereClause.add("res_off_lname = '" + lname + "'");
            if (!depositTextRes.getText().isEmpty())
                listWhereClause.add("res_off_depoit = " + Float.parseFloat(deposit));

            String whereClause = String.join(" AND ", listWhereClause);

            if (!whereClause.isEmpty())
                query += " WHERE " + whereClause;

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            idColumnRes.setCellValueFactory(new PropertyValueFactory<reservation_offer, String>("res_off_id"));
            offeridColumnRes.setCellValueFactory(new PropertyValueFactory<reservation_offer, String>("res_off_tr_id"));
            nameColumnRes.setCellValueFactory(new PropertyValueFactory<reservation_offer, String>("res_off_name"));
            lnameColumnRes.setCellValueFactory(new PropertyValueFactory<reservation_offer, String>("res_off_lname"));
            depositColumnRes.setCellValueFactory(new PropertyValueFactory<reservation_offer, Float>("res_off_deposit"));

            while (result.next()) {
                String reservation_id = Integer.toString(result.getInt("res_off_tr_id"));
                String offerID = Integer.toString(result.getInt("res_off_id"));
                String nameString = result.getString("res_off_name");
                String lnameString = result.getString("res_off_lname");
                float deposit_ = result.getFloat("res_off_depoit");

                offerResTable.getItems()
                        .add(new reservation_offer(lnameString, reservation_id, nameString, offerID, deposit_));
            }
            stmt.close();
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }
    }

    public void addResOffer(ActionEvent e) {
        try (Connection conn = connectDB.getConnection()) {
            String offer_res_id = offeridListRes.getValue();
            String name = nameTextRes.getText();
            String lname = lnameTextRes.getText();
            String deposit = depositTextRes.getText();

            String query = "INSERT INTO reservation_offers (res_off_lname,res_off_name,res_off_id,res_off_depoit) VALUES ( ?, ?, ?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(2, name);
            stmt.setString(1, lname);
            stmt.setString(3, offer_res_id);
            stmt.setFloat(4, Float.parseFloat(deposit));
            stmt.executeUpdate();
            stmt.close();
            initOfferidReservation(conn);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Επιτυχής εισαγωγή προσφοράς!");
            alert.setTitle("Επιβεβαίωση Εισαγωγής προσφοράς");
            alert.setHeaderText("Επιτυχία");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setHeaderText("Σφάλμα " + ex.getErrorCode());
            alert.setTitle("Σφάλμα");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.setTitle("Σφάλμα");
            alert.setHeaderText("Σφάλμα");
            alert.showAndWait();
        }
    }

    public void clearOfferRes(ActionEvent e) {
        offeridListRes.setValue(null);
        nameTextRes.setText("");
        lnameTextRes.setText("");
        depositTextRes.setText("");
        offerResTable.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(userInformation.getLastname());
        dashboard.setVisible(true);
        travelMenu.setVisible(false);
        addOffersMenu.setVisible(false);
        reservationMenu.setVisible(false);
        userInformationScene.setVisible(false);
        workersManagerMenu.setVisible(false);

        try (Connection conn = connectDB.getConnection()) {
            initDashboardData(conn);
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }

    }
}
