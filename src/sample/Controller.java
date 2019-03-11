package sample;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField FieldPeriodMilage;

    @FXML
    private TextField FieldNameTechnic;

    @FXML
    private TextField FieldFirstMilage;

    @FXML
    private TextField FieldIndexMilage;

    @FXML
    private TextField FieldFullMilage;

    @FXML
    private Button ButtonSend;

    @FXML
    Spinner <Integer> spiner1= new Spinner(2,4,1) ;


    @FXML
    void initialize() {
      ButtonSend.setOnAction(event -> {
                  DataBaseHandler Handler = new DataBaseHandler();
                  double  indexEngine=3.5;

                  try {
                      Handler.addTech(FieldNameTechnic.getText(), Integer.parseInt(FieldFirstMilage.getText()) ,Integer.parseInt(FieldPeriodMilage.getText()),Double.parseDouble(FieldIndexMilage.getText()),Integer.parseInt(FieldFullMilage.getText()));
                  } catch (SQLException e) {
                      e.printStackTrace();
                  } catch (ClassNotFoundException e) {
                      e.printStackTrace();
                  }


              }  );

      FieldFullMilage.textProperty().addListener(new ChangeListener<String>()
      {
          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
          {
              if (!newValue.matches("\\d")) { newValue.replaceAll("[^\\d]",""); }
          }


      });

    }
}
