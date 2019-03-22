package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu Menu_technica;

    @FXML
    private Menu Menu_journalTO;

    @FXML
    private Menu Menu_techUsing;

    @FXML
    private TableView<MonitoringRecieveData> TableMonitoring;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_id;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_name;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_date;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_fio;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_index;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_fullEngine;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_period_service;

    @FXML
    private TableColumn<MonitoringRecieveData, String> tb_percent;

    private ObservableList<MonitoringRecieveData> DataSelectMonitoring = FXCollections.observableArrayList();

    @FXML
    void initialize()
    {


        tb_id.setCellValueFactory(cell->cell.getValue().technic_idProperty());
        tb_name.setCellValueFactory(cell -> cell.getValue().name_technicProperty());
        tb_date.setCellValueFactory(cell->cell.getValue().filling_dateProperty());
        tb_fio.setCellValueFactory(cell -> cell.getValue().service_managerProperty());
        tb_index.setCellValueFactory(cell->cell.getValue().index_engine_hoursProperty());
        tb_fullEngine.setCellValueFactory(cell -> cell.getValue().full_engine_hoursProperty());
        tb_period_service.setCellValueFactory(cell->cell.getValue().period_of_serviceProperty());
        tb_percent.setCellValueFactory(cell->cell.getValue().percent_of_loadProperty());

        FilTableView();



        tb_percent.setCellFactory(column -> {

            return new TableCell<MonitoringRecieveData, String>()
            {
                @Override
                protected void updateItem(String item, boolean empty)
                {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<MonitoringRecieveData> currentRow = getTableRow();
                    Integer percentInt;

                   try {
                       percentInt = Integer.parseInt(item);

                                 if (percentInt < 50) { currentRow.setStyle("-fx-background-color: #ace6c6"); }
                                    else if (Integer.parseInt(item) < 90) { currentRow.setStyle("-fx-background-color:#f7ff99"); }
                                        else if (percentInt == null) {  }
                                            else {currentRow.setStyle("-fx-background-color:#ed4949"); }

                        }
                    catch (java.lang.NumberFormatException e){ }


                }
            };

        });



    }


     private void FilTableView()
     {

         TableMonitoring.getItems().clear();
         DataBaseHandler Query = new DataBaseHandler();

         try {
             Query.selectTechnicMonitoring(DataSelectMonitoring);
         } catch (SQLException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         TableMonitoring.setItems(DataSelectMonitoring);

     }
}
