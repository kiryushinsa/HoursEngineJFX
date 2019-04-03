package controllers;


import database.DataBaseHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import recievers.JournalUsingRecieveData;

import javax.swing.text.TableView;
import java.sql.SQLException;
import java.time.LocalTime;




public class ControllersHandler
{


  protected void spinnerHandler( Spinner  spinner)
    {
//позволяет обновлять данные введенные вручную в spinner.
        // без этого для ввода данных будут использоваться данные введенные ранее

        spinner.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            //intuitive method on textField, has no effect, though
            //spinner.getEditor().commitValue();
            try {
                commitEditorText(spinner);
            }
            catch (NumberFormatException e){}
        });


    }

    private  void commitEditorText(Spinner spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter converter = valueFactory.getConverter();
            if (converter != null) {
                Object value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }
// конец обработчика спинера

    protected LocalTime getTime(Spinner <Integer> hours,Spinner<Integer> minutes)
    {



        try
        { Integer.parseInt(minutes.getEditor().getText());
            Integer.parseInt(hours.getEditor().getText());
        }
        catch (NumberFormatException e){return null; }

        if (Integer.parseInt(hours.getEditor().getText())>=0 &&Integer.parseInt(hours.getEditor().getText())<=23 &&
                Integer.parseInt(minutes.getEditor().getText())>=0 && Integer.parseInt(minutes.getEditor().getText())<60){ return LocalTime.of(hours.getValue(), minutes.getValue()); }
        else return null;

    }

    protected Double getUsingTime(Spinner <Double> hours,Spinner<Double> minutes)
    {
        try
        {       Integer.parseInt(minutes.getEditor().getText());
            Integer.parseInt(hours.getEditor().getText());
        }
        catch (NumberFormatException e){return null;}



        if( Integer.parseInt(hours.getEditor().getText())>=0 &&  Integer.parseInt(hours.getEditor().getText())<=23 &&
                Integer.parseInt(minutes.getEditor().getText())>=0 && Integer.parseInt(minutes.getEditor().getText())<60){

            return ( ( (hours.getValue()*60) + minutes.getValue() ) /60 )  ;
        }
        else return null;
    }





}
