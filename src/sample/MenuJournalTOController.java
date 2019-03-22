package sample;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuJournalTOController {

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
    private Slider SliderHours;

    @FXML
    private DatePicker DateUsing;

    @FXML
    private Slider SliderMinutes;

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

        FillTableView();




        ButtonSend.setOnAction(event -> {
            DataBaseHandler Handler = new DataBaseHandler();




            LocalDate CurrentDateFromPicker = DateUsing.getValue();
            Date CurrentDate =  convertToDateViaSqlDate(CurrentDateFromPicker);

            Number Hours = SliderHours.getValue();
            Number Minutes = SliderMinutes.getValue();
            LocalTime CurrentTime = LocalTime.of( Hours.intValue(),Minutes.intValue());


            if(CheckerReset.isSelected() == true)
            {
                try {
                    Handler.updateTechnicAfterTO(getChoiceBoxTechnicID());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                if(TextFieldServiceManager.getText()==null || TextFieldServiceManager.getText().trim().isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ошибка при добавлении записи в базу данных");
                    alert.setContentText("Заполните поле 'ФИО проводившего ТО'");
                    alert.showAndWait();}

                else {   Handler.setJournalTo(CurrentDate,CurrentTime, getChoiceBoxTechnicID(),TextFieldServiceManager.getText(),TextFieldTypeTO.getText(),TextAreaNote.getText(),CheckerReset.isSelected());
                   FillTableView();}

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e)
            {
            }

        }  );


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

    private void FillTableView()
    {

        TableViewJournalTO.getItems().clear();
        DataBaseHandler Query = new DataBaseHandler();

        try {
            Query.getAllRowsJTO(DataSelectJTo);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        TableViewJournalTO.setItems(DataSelectJTo);


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
        } else if (option.get() == ButtonType.OK) {
            Delete.deleteJToRow(selectedRow.getId_tech());
            FillTableView();

        } else if (option.get() == ButtonType.CANCEL) {
        }
    }
}
