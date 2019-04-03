package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import database.DataBaseHandler;
import javafx.util.StringConverter;
import recievers.JournalUsingRecieveData;
import recievers.TechnicReceiveData;

public class MenuJournalUsingController extends ControllersHandler {

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
    private TableView <JournalUsingRecieveData> TableViewJournalUsing;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_id;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_date;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_time;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_idtech;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_using_time;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_order;

    @FXML
    private TableColumn<JournalUsingRecieveData, String> tb_note;


    @FXML
    private DatePicker DateUsing;


    @FXML
    private TextArea TextAreaNote;

    @FXML
    private TextField TextFieldOrder;

    @FXML
    private ComboBox<TechnicReceiveData> ComboBoxTechnics;

    @FXML
    private Button ButtonSend;



    private ObservableList<String> DataSelectTechnic = FXCollections.observableArrayList();



    @FXML
    private ChoiceBox<String> ChoiseBoxTechnics;

    private HashMap<String,Integer> Map = new HashMap<String,Integer>();

    private   ObservableList <JournalUsingRecieveData> DataSelectJusing = FXCollections.observableArrayList();


    @FXML
    private ContextMenu ContextMenuTable;

    @FXML
    private MenuItem ContextItemDelete;


    @FXML
    private MainMenuController Parent=null;

    @FXML
    private Spinner<Integer> SpinnerHours;

    @FXML
    private Spinner<Integer> SpinnerMinutes;


    @FXML
    private Spinner<Double> SpinnerUsingHours;

    @FXML
    private Spinner<Double> SpinnerUsingMinutes;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException
    {
        DateUsing.setValue(LocalDate.now());
            fillChoiceBox();



// назначение параметров для столбцов таблицы
        tb_id.setCellValueFactory(cell->cell.getValue().id_noteProperty());
        tb_date.setCellValueFactory(cell -> cell.getValue().filling_dateProperty());
        tb_time.setCellValueFactory(cell->cell.getValue().filling_timeProperty());
        tb_idtech.setCellValueFactory(cell -> cell.getValue().id_technicProperty());
        tb_using_time.setCellValueFactory(cell -> cell.getValue().work_timeProperty());
        tb_order.setCellValueFactory(cell->cell.getValue().order_on_taskProperty());
        tb_note.setCellValueFactory(cell -> cell.getValue().comment_of_usingProperty());
        fillTableView();


        spinnerHandler(SpinnerUsingMinutes);
        spinnerHandler(SpinnerMinutes);
        spinnerHandler(SpinnerHours);
        spinnerHandler(SpinnerUsingHours);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 0);
        SpinnerMinutes.setValueFactory(valueFactory);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 23, 0);
        SpinnerHours.setValueFactory(valueFactory);

        SpinnerValueFactory<Double> valueFactory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(00, 23, 0);
        SpinnerUsingHours.setValueFactory(valueFactory2);

        valueFactory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(00, 59, 0);
        SpinnerUsingMinutes.setValueFactory(valueFactory2);


//      конец обработчика спинеров



        ButtonSend.setOnAction(event ->
        {





            DataBaseHandler Handler = new DataBaseHandler();

            LocalDate CurrentDateFromPicker = DateUsing.getValue();
            Date CurrentDate =  convertToDateViaSqlDate(CurrentDateFromPicker);


            if (getTime(SpinnerHours,SpinnerMinutes)!=null && getUsingTime(SpinnerUsingHours,SpinnerUsingMinutes)!=null)
            {




                try
                {
                    Handler.setJournalUsing(CurrentDate,getTime(SpinnerHours,SpinnerMinutes), getChoiceBoxTechnicID(),getUsingTime(SpinnerUsingHours,SpinnerUsingMinutes),TextFieldOrder.getText(),TextAreaNote.getText());
                    Handler.updateTechnicAfterUsing(getChoiceBoxTechnicID(),getUsingTime(SpinnerUsingHours,SpinnerUsingMinutes));
                    fillTableView();

                    messageSaveSuccesful();


                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ошибка при добавлении записи в базу данных");
                    alert.setContentText("Заполните поле 'Время использования'");
                    alert.showAndWait();
                }


            }
            else {    Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка при добавлении записи в базу данных");
                alert.setContentText("Проверьте заполненность полей Время использования и Длительность использования");
                alert.showAndWait();     }




        });

//обработчик ввода для текстовых полей
        Pattern pattern120words = Pattern.compile(".{0,119}");
        TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern120words.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextAreaNote.setTextFormatter(formatter2);


        Pattern pattern8words = Pattern.compile(".{0,8}");
        TextFormatter formatter3 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern8words.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFieldOrder.setTextFormatter(formatter3);
//конец intialize


    }


    private void fillChoiceBox() throws SQLException, ClassNotFoundException {
        DataBaseHandler Query = new DataBaseHandler();
        Query.getTechnicRowForChoiseList(DataSelectTechnic,  Map);
        ChoiseBoxTechnics.setItems(DataSelectTechnic);
        ChoiseBoxTechnics.getSelectionModel().select(0);
    }



    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }



 public Integer getChoiceBoxTechnicID()
 {
    return Map.get(ChoiseBoxTechnics.getValue());
 }


    private void fillTableView()
    {
        TableViewJournalUsing.getItems().clear();
        DataBaseHandler Query = new DataBaseHandler();

        try {
            Query.getAllRowsJUsing(DataSelectJusing);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TableViewJournalUsing.setItems(DataSelectJusing);
        updateTableViewParentController();
    }


    public void TableViewJournalUsing(MouseEvent mouseEvent)
    {
        TableViewJournalUsing.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                ContextMenuTable.show(TableViewJournalUsing,event.getScreenX(),event.getScreenY());

            }
        });
    }


    public void ContextItemDeleteClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, java.lang.RuntimeException {
        JournalUsingRecieveData  selectedRow= TableViewJournalUsing.getSelectionModel().getSelectedItem();
        System.out.println(selectedRow.getId_note());
        DataBaseHandler Delete = new DataBaseHandler();



        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText("Удалить запись с идентификатором: "+selectedRow.getId_note());
        Optional<ButtonType> option= alert.showAndWait();

        if(option.get()==null) {                 }

        else if(option.get()==ButtonType.OK)
        {
            Delete.deleteJusingRow(selectedRow.getId_note(),Integer.parseInt(selectedRow.getId_technic()),Double.parseDouble(selectedRow.getWork_time()));
            fillTableView();


        }
        else if(option.get()==ButtonType.CANCEL) { }


    }
    private void messageSaveSuccesful()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно");
        alert.setHeaderText("Запись добавлена успешно");
        alert.setContentText("");
        alert.showAndWait();

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



}
