package controllers;

import database.Configs;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class MenuSettingsController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextFieldIP;

    @FXML
    private TextField TextFieldPort;

    @FXML
    private TextField TextFieldUser;

    @FXML
    private TextField TextFieldNameDB;

    @FXML
    private PasswordField PasswordFieldUser;

    @FXML
    private Button ButtonSend;

    @FXML
    void initialize()
    {
        Configs configs = new Configs();

        TextFieldIP.setText(configs.getIp());
        TextFieldPort.setText(configs.getPort());
        TextFieldUser.setText(configs.getUser());
        TextFieldNameDB.setText(configs.getDatabase());
        PasswordFieldUser.setText(configs.getPassword());

        ButtonSend.setOnAction(event ->  {
            try {
                configs.setSettings(TextFieldIP.getText(),TextFieldPort.getText(),TextFieldUser.getText(),PasswordFieldUser.getText(),TextFieldNameDB.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



            if(configs.checkSettings()){ messageDataBaseConnectSuccessfulMessage();

            }
            else {
              messageDataBaseConnectWarningMessage();
            }


        });


    }

    private void messageDataBaseConnectWarningMessage()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка подключения к базе данных");
        alert.setHeaderText("Поменяйте данные в пункте меню" +"'Настройки'");
        alert.setContentText("");
        alert.showAndWait();

    }


    private void messageDataBaseConnectSuccessfulMessage()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно");
        alert.setHeaderText("Подключение к базе данных произошло успешно");
        alert.setContentText("");
        alert.showAndWait();

    }

}
