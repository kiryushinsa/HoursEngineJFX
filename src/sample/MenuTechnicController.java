package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

public class MenuTechnicController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField FieldNameTechnic;

    @FXML
    private TextField FieldFirstMilage;

    @FXML
    private TextField FieldPeriodOfService;

    @FXML
    private TextField FieldIndexEngineHours;

    @FXML
    private Button ButtonSend;



    @FXML
    private TableView <TechnicReceiveData> TableTechnic;

    @FXML
    private TableColumn<TechnicReceiveData, String> tb_id;

    @FXML
    private TableColumn<TechnicReceiveData, String> tb_name;

    @FXML
    private TableColumn<TechnicReceiveData, String> tb_first_milage;

    @FXML
    private TableColumn<TechnicReceiveData, String> tb_period_of_service;

    @FXML
    private TableColumn<TechnicReceiveData,String> tb_index_of_engine_hours;

    @FXML
    private TableColumn<TechnicReceiveData, String> tb_full_engine_hours;

    @FXML
    private TableColumn<TechnicReceiveData, String> tb_next_service_milage;

    private   ObservableList <TechnicReceiveData> DataSelectTechnic = FXCollections.observableArrayList();

    @FXML
    private ContextMenu ContextMenuTable;

    @FXML
    private MenuItem ContextItemDelete;

    @FXML
    void initialize() {
      ButtonSend.setOnAction(event -> {
                  DataBaseHandler Handler = new DataBaseHandler();


                  try {

                      Handler.setTechnic(FieldNameTechnic.getText(), Integer.parseInt(FieldFirstMilage.getText()) ,Integer.parseInt(FieldPeriodOfService.getText()),Double.parseDouble(FieldIndexEngineHours.getText()));
                      FillTableView();
                      messageSaveSuccesful();

                  } catch (SQLException e) {
                      e.printStackTrace();
                  } catch (ClassNotFoundException e) {
                      e.printStackTrace();
                  }
                  catch (NumberFormatException e)
                  {
                      System.out.println("Заполните все поля ");
                      Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Error");
                      alert.setHeaderText("Ошибка при добавлении записи в базу данных");
                      alert.setContentText("Заполните все поля");
                      alert.showAndWait();
                  }

              }  );

        tb_id.setCellValueFactory(cell->cell.getValue().technic_idProperty());
        tb_name.setCellValueFactory(cell -> cell.getValue().nameProperty());
        tb_first_milage.setCellValueFactory(cell->cell.getValue().first_milageProperty());
        tb_period_of_service.setCellValueFactory(cell -> cell.getValue().period_of_serviceProperty());
        tb_index_of_engine_hours.setCellValueFactory(cell->cell.getValue().index_engine_hoursProperty());
        tb_full_engine_hours.setCellValueFactory(cell -> cell.getValue().full_engine_hoursProperty());
        tb_next_service_milage.setCellValueFactory(cell->cell.getValue().next_service_milageProperty());

        FillTableView();



// Ниже идет блок с обработчиком вводимых значений для полей ввода
            Pattern pattern4 = Pattern.compile(".{0,50}");
            TextFormatter formatter4 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
                return pattern4.matcher(change.getControlNewText()).matches() ? change : null;
            });
            FieldNameTechnic.setTextFormatter(formatter4);


            Pattern pattern3 = Pattern.compile("\\d{0,6}");
            TextFormatter formatter3 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
                return pattern3.matcher(change.getControlNewText()).matches() ? change : null;
            });
            FieldFirstMilage.setTextFormatter(formatter3);


            Pattern pattern2 = Pattern.compile("\\d{0,6}");
            TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
                return pattern2.matcher(change.getControlNewText()).matches() ? change : null;
            });
            FieldPeriodOfService.setTextFormatter(formatter2);


            Pattern pattern = Pattern.compile("\\d{0,1}|\\d{0,1}+\\.(\\d{0,2})");
            TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
                return pattern.matcher(change.getControlNewText()).matches() ? change : null;
            });
            FieldIndexEngineHours.setTextFormatter(formatter);

    }



    private void FillTableView()
    {

        TableTechnic.getItems().clear();
        DataBaseHandler Query = new DataBaseHandler();

        try {
            Query.getAllRowsTechnic(DataSelectTechnic);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableTechnic.setItems(DataSelectTechnic);


    }
    public void TableTechnic(MouseEvent mouseEvent)
    {
                TableTechnic.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                    @Override
                    public void handle(ContextMenuEvent event) {
                        ContextMenuTable.show(TableTechnic,event.getScreenX(),event.getScreenY());

                    }
                });
    }


    public void ContextItemDeleteClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, java.lang.RuntimeException {
        TechnicReceiveData  selectedRow= TableTechnic.getSelectionModel().getSelectedItem();
        System.out.println(selectedRow.getTechnic_id());
        DataBaseHandler Delete = new DataBaseHandler();



        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText("Удалить запись с идентификатором: "+selectedRow.getTechnic_id());
        Optional<ButtonType> option= alert.showAndWait();

        if(option.get()==null) {                 }

        else if(option.get()==ButtonType.OK)
        {
            Delete.deleteTechnicRow(selectedRow.getTechnic_id());
            FillTableView();

            }
        else if(option.get()==ButtonType.CANCEL){ }






    }
    private void messageSaveSuccesful()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно");
        alert.setHeaderText("Запись добавлена успешно");
        alert.setContentText("");
        alert.showAndWait();

    }
}


