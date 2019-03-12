package sample;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Controller  {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField FieldPeriodOfService;

    @FXML
    private TextField FieldNameTechnic;

    @FXML
    private TextField FieldFirstMilage;

    @FXML
    private Button ButtonSend;

    @FXML
    private TextField FieldIndexEngineHours;

    @FXML
    private TableView <?> TableTechnic;

    @FXML
    void initialize() {
      ButtonSend.setOnAction(event -> {
                  DataBaseHandler Handler = new DataBaseHandler();


                  try {

                      Handler.addTech(FieldNameTechnic.getText(), Integer.parseInt(FieldFirstMilage.getText()) ,Integer.parseInt(FieldPeriodOfService.getText()),Double.parseDouble(FieldIndexEngineHours.getText()));

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

System.out.println(TableTechnic.getSelectionModel().getSelectedItem());


        Pattern pattern4= Pattern.compile(".{0,50}");
        TextFormatter formatter4 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern4.matcher(change.getControlNewText()).matches() ? change : null;
        });
        FieldNameTechnic.setTextFormatter(formatter4);


        Pattern pattern3= Pattern.compile("\\d{0,6}");
        TextFormatter formatter3 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern3.matcher(change.getControlNewText()).matches() ? change : null;
        });
        FieldFirstMilage.setTextFormatter(formatter3);




        Pattern pattern2= Pattern.compile("\\d{0,6}");
        TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern2.matcher(change.getControlNewText()).matches() ? change : null;
        });
        FieldPeriodOfService.setTextFormatter(formatter2);


        Pattern pattern= Pattern.compile("\\d{0,1}|\\d{0,1}+\\.(\\d{0,2})");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        FieldIndexEngineHours.setTextFormatter(formatter);

    }


}
