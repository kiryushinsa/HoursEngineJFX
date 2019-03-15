package sample;

import javafx.beans.property.*;

public class TechnicReceiveData
{
    private final StringProperty technic_id;
    private final StringProperty name;
    private final StringProperty first_milage;
    private final StringProperty period_of_service;
    private final StringProperty index_engine_hours;
    private final StringProperty full_engine_hours;
    private final StringProperty next_service_milage;





    public TechnicReceiveData(String technic_id, String name, String first_milage, String period_of_service, String index_engine_hours, String full_engine_hours, String next_service_milage) {
        this.technic_id = new SimpleStringProperty(technic_id);
        this.name = new SimpleStringProperty(name);
        this.first_milage =new SimpleStringProperty(first_milage);
        this.period_of_service = new SimpleStringProperty(period_of_service);
        this.index_engine_hours = new SimpleStringProperty(index_engine_hours);
        this.full_engine_hours = new SimpleStringProperty (full_engine_hours);
        this.next_service_milage = new SimpleStringProperty(next_service_milage);
    }

    public String getTechnic_id() {
        return technic_id.get();
    }

    public StringProperty technic_idProperty() {
        return technic_id;
    }

    public void setTechnic_id(String technic_id) {
        this.technic_id.set(technic_id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFirst_milage() {
        return first_milage.get();
    }

    public StringProperty first_milageProperty() {
        return first_milage;
    }

    public void setFirst_milage(String first_milage) {
        this.first_milage.set(first_milage);
    }

    public String getPeriod_of_service() {
        return period_of_service.get();
    }

    public StringProperty period_of_serviceProperty() {
        return period_of_service;
    }

    public void setPeriod_of_service(String period_of_service) {
        this.period_of_service.set(period_of_service);
    }

    public String getIndex_engine_hours() {
        return index_engine_hours.get();
    }

    public StringProperty index_engine_hoursProperty() {
        return index_engine_hours;
    }

    public void setIndex_engine_hours(String index_engine_hours) {
        this.index_engine_hours.set(index_engine_hours);
    }

    public String getFull_engine_hours() {
        return full_engine_hours.get();
    }

    public StringProperty full_engine_hoursProperty() {
        return full_engine_hours;
    }

    public void setFull_engine_hours(String full_engine_hours) {
        this.full_engine_hours.set(full_engine_hours);
    }

    public String getNext_service_milage() {
        return next_service_milage.get();
    }

    public StringProperty next_service_milageProperty() {
        return next_service_milage;
    }

    public void setNext_service_milage(String next_service_milage) {
        this.next_service_milage.set(next_service_milage);
    }

}
