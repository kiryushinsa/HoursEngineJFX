package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import database.DataBaseHandler;
import recievers.MonitoringRecieveData;

public class  MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem Menu_Save;

    @FXML
    private MenuItem Menu_Exit;

    @FXML
    private MenuItem Menu_technica;

    @FXML
    private MenuItem Menu_journalTO;

    @FXML
    private MenuItem Menu_techUsing;

    @FXML
    private MenuItem Menu_Settings;

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
    private Button ButtonUpdate;

    @FXML
    private Menu Journal;

@FXML
    public MainMenuController getController(){
        return this;
    }



    @FXML
    void click(ActionEvent event) {
        System.out.println("klыыыыыk");
    }

    @FXML
    void initialize()
    {



        ButtonUpdate.setOnAction(event -> { fillTableView();});



       

                Menu_journalTO.setOnAction(event ->
                {

                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../fxml/MenuJournalTO.fxml"));
                        root = loader.load();
                        MenuJournalTOController example = (MenuJournalTOController) (loader.getController());
                        example.setParentController(getController());
                    }

                    catch (IOException e) { e.printStackTrace(); }

                    Stage stageFrame = new Stage();
                    stageFrame.setScene(new Scene(root));
                    stageFrame.getIcons().add(new Image("/image/icon.png"));
                    stageFrame.initModality(Modality.APPLICATION_MODAL);
                    stageFrame.show();

                });


                Menu_technica.setOnAction(event ->
                {


                        Parent root = null;
                        try {

                            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../fxml/MenuTechnic.fxml"));
                            root = loader.load();
                            MenuTechnicController example =(MenuTechnicController) loader.getController();
                            example.setParentController(getController());



                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stageFrame = new Stage();
                        stageFrame.setScene(new Scene(root));
                    stageFrame.getIcons().add(new Image("/image/icon.png"));
                        stageFrame.initModality(Modality.APPLICATION_MODAL);
                        stageFrame.show();



                });


                Menu_techUsing.setOnAction(event -> {
                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../fxml/MenuJournalUsing.fxml"));
                        root = loader.load();
                        MenuJournalUsingController example =(MenuJournalUsingController) loader.getController();
                        example.setParentController(getController());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stageFrame = new Stage();
                    stageFrame.setScene(new Scene(root));
                    stageFrame.getIcons().add(new Image("/image/icon.png"));
                    stageFrame.initModality(Modality.APPLICATION_MODAL);
                    stageFrame.show();



                });

                Menu_Settings.setOnAction(event -> {
                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../fxml/MenuSettings.fxml"));
                        root = loader.load();



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stageFrame = new Stage();
                    stageFrame.setScene(new Scene(root));
                    stageFrame.getIcons().add(new Image("/image/icon.png"));
                    stageFrame.initModality(Modality.APPLICATION_MODAL);
                    stageFrame.show();



                });



        tb_id.setCellValueFactory(cell->cell.getValue().technic_idProperty());
        tb_name.setCellValueFactory(cell -> cell.getValue().name_technicProperty());
        tb_date.setCellValueFactory(cell->cell.getValue().filling_dateProperty());
        tb_fio.setCellValueFactory(cell -> cell.getValue().service_managerProperty());
        tb_index.setCellValueFactory(cell->cell.getValue().index_engine_hoursProperty());
        tb_fullEngine.setCellValueFactory(cell -> cell.getValue().full_engine_hoursProperty());
        tb_period_service.setCellValueFactory(cell->cell.getValue().period_of_serviceProperty());
        tb_percent.setCellValueFactory(cell->cell.getValue().percent_of_loadProperty());

        fillTableView();
    }

    protected void print()
    {
        System.out.println("вызывал метод");

    }


     protected void fillTableView()
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
         catch ( NullPointerException e){}
         TableMonitoring.setItems(DataSelectMonitoring);


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
                     Double percentInt;

                     try {

                         try {
                             percentInt = Double.parseDouble(item);
                         }
                         catch (java.lang.NullPointerException e){ percentInt=-1.0;}

                         if (percentInt>=0.0 && percentInt < 50.0) { currentRow.setStyle("-fx-background-color: #ace6c6"); }
                             else if (percentInt < 90.0 && percentInt > 50.0  ) { currentRow.setStyle("-fx-background-color:#f7ff99"); }
                                 else if (percentInt == null || percentInt<0 ) {  }
                                    else {currentRow.setStyle("-fx-background-color:#ed4949"); }




                     }
                     catch (java.lang.NumberFormatException e){ }


                 }
             };

         });




     }
}
