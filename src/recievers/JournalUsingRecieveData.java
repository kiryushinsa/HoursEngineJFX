package recievers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JournalUsingRecieveData
{
    private final StringProperty id_note;
    private final StringProperty filling_date;
    private final StringProperty filling_time;
    private final StringProperty id_technic;
    private final StringProperty work_time;
    private final StringProperty order_on_task;
    private final StringProperty comment_of_using;

    public String getWork_time() {
        return work_time.get();
    }

    public StringProperty work_timeProperty() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time.set(work_time);
    }


    public String getId_note() {
        return id_note.get();
    }

    public StringProperty id_noteProperty() {
        return id_note;
    }

    public void setId_note(String id_note) {
        this.id_note.set(id_note);
    }

    public String getFilling_date() {
        return filling_date.get();
    }

    public StringProperty filling_dateProperty() {
        return filling_date;
    }

    public void setFilling_date(String filling_date) {
        this.filling_date.set(filling_date);
    }

    public String getFilling_time() {
        return filling_time.get();
    }

    public StringProperty filling_timeProperty() {
        return filling_time;
    }

    public void setFilling_time(String filling_time) {
        this.filling_time.set(filling_time);
    }

    public String getId_technic() {
        return id_technic.get();
    }

    public StringProperty id_technicProperty() {
        return id_technic;
    }

    public void setId_technic(String id_technic) {
        this.id_technic.set(id_technic);
    }

    public String getOrder_on_task() {
        return order_on_task.get();
    }

    public StringProperty order_on_taskProperty() {
        return order_on_task;
    }

    public void setOrder_on_task(String order_on_task) {
        this.order_on_task.set(order_on_task);
    }

    public String getComment_of_using() {
        return comment_of_using.get();
    }

    public StringProperty comment_of_usingProperty() {
        return comment_of_using;
    }

    public void setComment_of_using(String comment_of_using) {
        this.comment_of_using.set(comment_of_using);
    }




    public JournalUsingRecieveData(String id_note, String filling_date, String filling_time, String id_technic,String work_time, String order_on_task, String comment_of_using)
    {
        this.id_note = new SimpleStringProperty(id_note);
        this.filling_date =new SimpleStringProperty( filling_date);
        this.filling_time = new SimpleStringProperty(filling_time);
        this.id_technic = new SimpleStringProperty(id_technic);
        this.work_time = new SimpleStringProperty(work_time);
        this.order_on_task = new SimpleStringProperty(order_on_task);
        this.comment_of_using = new SimpleStringProperty(comment_of_using);
    }



}
