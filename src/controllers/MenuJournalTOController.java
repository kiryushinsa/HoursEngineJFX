package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import database.DataBaseHandler;
import recievers.JournalToReceiveData;

public class MenuJournalTOController extends ControllersHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private DatePicker DateUsing;



    @FXML
    private TextArea TextAreaNote;

    @FXML
    private Button ButtonSend;

    @FXML
    private Label LabelHours;

    @FXML
    private Label LabelMinutes;

    @FXML
    private ChoiceBox<String> ChoiseBoxTechnics;

    @FXML
    private TextField TextFieldServiceManager;

    @FXML
    private TextField TextFieldTypeTO;

    @FXML
    private CheckBox CheckerReset;

    @FXML
    private TableView<JournalToReceiveData> TableViewJournalTO;


    @FXML
    private TableColumn<JournalToReceiveData,String > tb_id;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_date;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_time;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_idtech;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_fio;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_typeTO;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_note;

    @FXML
    private TableColumn<JournalToReceiveData,String > tb_reset;

    private HashMap<String,Integer> Map = new HashMap<String,Integer>();

    private ObservableList<String> DataSelectTechnic = FXCollections.observableArrayList();

    private ObservableList<JournalToReceiveData> DataSelectJTo = FXCollections.observableArrayList();

    @FXML
    private ContextMenu ContextMenuTable;

    @FXML
    private MenuItem ContextItemDelete;


    @FXML
    private Spinner<Integer> SpinnerHours;

    @FXML
    private Spinner<Integer> SpinnerMinutes;

    @FXML
    private MainMenuController Parent=null;

    @FXML
    void initialize()
    {
        try {
            FillChoiceBox();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        DateUsing.setValue(LocalDate.now());


        tb_id.setCellValueFactory(cell->cell.getValue().id_techProperty());
        tb_date.setCellValueFactory(cell -> cell.getValue().filling_dateProperty());
        tb_time.setCellValueFactory(cell->cell.getValue().filling_timeProperty());
        tb_idtech.setCellValueFactory(cell -> cell.getValue().id_technicProperty());
        tb_fio.setCellValueFactory(cell -> cell.getValue().service_managerProperty());
        tb_typeTO.setCellValueFactory(cell->cell.getValue().type_of_serviceProperty());
        tb_note.setCellValueFactory(cell -> cell.getValue().comment_of_tech_serviceProperty());
        tb_reset.setCellValueFactory(cell -> cell.getValue().resetTOProperty());

        fillTableView();



        spinnerHandler(SpinnerMinutes);
        spinnerHandler(SpinnerHours);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 0);
        SpinnerMinutes.setValueFactory(valueFactory);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 23, 0);
        SpinnerHours.setValueFactory(valueFactory);


        ButtonSend.setOnAction(event -> {
            DataBaseHandler Handler = new DataBaseHandler();




            LocalDate CurrentDateFromPicker = DateUsing.getValue();
            Date CurrentDate =  convertToDateViaSqlDate(CurrentDateFromPicker);



            try {

                if(TextFieldServiceManager.getText()==null || TextFieldServiceManager.getText().trim().isEmpty() || getTime(SpinnerHours,SpinnerMinutes)==null)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ошибка при добавлении записи в базу данных");
                    alert.setContentText("Проверьте корректность ввода заполненных полей");
                    alert.showAndWait();}

                else
                    {
                    Handler.setJournalTo(CurrentDate,getTime(SpinnerHours,SpinnerMinutes), getChoiceBoxTechnicID(),TextFieldServiceManager.getText(),TextFieldTypeTO.getText(),TextAreaNote.getText(),CheckerReset.isSelected());

                            fillTableView();
                                messageSaveSuccesful();

                    }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e)
            {
            }
            finally {
                Handler = null;

            }

        }  );




    }

    private Date convertToDateViaSqlDate(LocalDate currentDateFromPicker)
    {
        return java.sql.Date.valueOf(currentDateFromPicker);
    }

    public Integer getChoiceBoxTechnicID()
    {
        return Map.get(ChoiseBoxTechnics.getValue());
    }

    private void FillChoiceBox() throws SQLException, ClassNotFoundException
    {
        DataBaseHandler Query = new DataBaseHandler();
        Query.getTechnicRowForChoiseList(DataSelectTechnic,  Map);
        ChoiseBoxTechnics.setItems(DataSelectTechnic);
        ChoiseBoxTechnics.getSelectionModel().select(0);
    }

    private void fillTableView()
    {

        TableViewJournalTO.getItems().clear();

        DataBaseHandler Query = new DataBaseHandler();

        try {
            Query.getAllRowsJTO(DataSelectJTo);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


        TableViewJournalTO.setItems(DataSelectJTo);
updateTableViewParentController();

    }


    public void ContextItemDeleteClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, java.lang.RuntimeException {
        JournalToReceiveData selectedRow = TableViewJournalTO.getSelectionModel().getSelectedItem();
        System.out.println(selectedRow.getId_tech());
        DataBaseHandler Delete = new DataBaseHandler();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText("Удалить запись с идентификатором: " + selectedRow.getId_tech());
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
        } else if (option.get() == ButtonType.OK)
        {

            if(selectedRow.getResetTO()=="Cброшено")  Delete.deleteJToRow(selectedRow.getId_tech(),true,selectedRow.getId_tech());
            else {Delete.deleteJToRow(selectedRow.getId_tech(),false,selectedRow.getId_tech());}

            fillTableView();
        } else if (option.get() == ButtonType.CANCEL) {       }
    }


    public void setParentController(MainMenuController MainController)
    {
        Parent = MainController;
    }

    protected MainMenuController getParentController ()
    {
        return Parent;
    }

    protected void  updateTableViewParentController ()
    {
        if(Parent!=null) {  Parent.fillTableView();}
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
