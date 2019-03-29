package sample;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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

public class MenuJournalUsingController {

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
    private Slider SliderHours;

    @FXML
    private DatePicker DateUsing;

    @FXML
    private Slider SliderMinutes;

    @FXML
    private TextArea TextAreaNote;

    @FXML
    private TextField TextFieldOrder;

    @FXML
    private ComboBox<TechnicReceiveData> ComboBoxTechnics;

    @FXML
    private Button ButtonSend;

    @FXML
    private Label LabelHours;

    @FXML
    private Label LabelMinutes;

    private ObservableList<String> DataSelectTechnic = FXCollections.observableArrayList();

    @FXML
    private TextField TextFieldUsingTime;


    @FXML
    private ChoiceBox<String> ChoiseBoxTechnics;

    private HashMap<String,Integer> Map = new HashMap<String,Integer>();

    private   ObservableList <JournalUsingRecieveData> DataSelectJusing = FXCollections.observableArrayList();


    @FXML
    private ContextMenu ContextMenuTable;

    @FXML
    private MenuItem ContextItemDelete;


    @FXML
    private Button ButtonUpdate;

    @FXML

    private sample.MainMenuController Parent=null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException
    {
        DateUsing.setValue(LocalDate.now());
            FillChoiceBox();
                TextFieldUsingTime.setText("5");




        tb_id.setCellValueFactory(cell->cell.getValue().id_noteProperty());
        tb_date.setCellValueFactory(cell -> cell.getValue().filling_dateProperty());
        tb_time.setCellValueFactory(cell->cell.getValue().filling_timeProperty());
        tb_idtech.setCellValueFactory(cell -> cell.getValue().id_technicProperty());
        tb_using_time.setCellValueFactory(cell -> cell.getValue().work_timeProperty());
        tb_order.setCellValueFactory(cell->cell.getValue().order_on_taskProperty());
        tb_note.setCellValueFactory(cell -> cell.getValue().comment_of_usingProperty());
        FillTableView();




        ButtonSend.setOnAction(event ->
        {
            DataBaseHandler Handler = new DataBaseHandler();

            LocalDate CurrentDateFromPicker = DateUsing.getValue();
            Date CurrentDate =  convertToDateViaSqlDate(CurrentDateFromPicker);

            Number Hours = SliderHours.getValue();
            Number Minutes = SliderMinutes.getValue();
            LocalTime CurrentTime = LocalTime.of( Hours.intValue(),Minutes.intValue());

            Double UsingTime =Double.parseDouble(TextFieldUsingTime.getText())/60  ;



            try
            {
                Handler.setJournalUsing(CurrentDate,CurrentTime, getChoiceBoxTechnicID(),UsingTime,TextFieldOrder.getText(),TextAreaNote.getText());
                Handler.updateTechnicAfterUsing(getChoiceBoxTechnicID(),UsingTime);
                FillTableView();

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
        });

//обработчик ввода

        Pattern pattern3Integer = Pattern.compile("\\d{0,3}");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern3Integer.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFieldUsingTime.setTextFormatter(formatter);


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


//конкатенация слайдеров в лейбл
        SliderHours.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                  newValue = newValue.intValue();
                    LabelHours.setText(newValue + ":");

            }
        });

        SliderMinutes.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValueMinutes, Number newValueMinutes) {
                newValueMinutes = newValueMinutes.intValue();

                if(newValueMinutes.intValue()<10) {
                                        LabelMinutes.setText("0" + newValueMinutes + "");
                }
                else {LabelMinutes.setText(newValueMinutes + "");}
            }
        });

//конец intialize


    }


    private void FillChoiceBox() throws SQLException, ClassNotFoundException {
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


    private void FillTableView()
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
            FillTableView();


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


    public void setParentController(sample.MainMenuController MainController)
    {
        Parent = MainController;
    }

    protected sample.MainMenuController getParentController ()
    {
        return Parent;
    }

    protected void  updateTableViewParentController ()
    {
        if(Parent!=null) {  Parent.fillTableView();}
    }



}
