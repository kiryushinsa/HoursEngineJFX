package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private Slider SpinnerHours;

    @FXML
    private DatePicker DateUsing;

    @FXML
    private Slider SpinnerMinutes;

    @FXML
    private TextArea TextAreaNote;

    @FXML
    private TextField TextFieldTask;

    @FXML
    private ComboBox<?> ComboBoxTechnics;

    @FXML
    private Button ButtonSend;

    @FXML
    private Label LabelTime;

    @FXML
    void initialize() {


    }
}
