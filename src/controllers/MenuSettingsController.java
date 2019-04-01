package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

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

    }

}
