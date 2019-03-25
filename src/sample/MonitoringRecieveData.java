package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MonitoringRecieveData
{

    private final StringProperty technic_id;
    private final StringProperty name_technic;
    private final StringProperty index_engine_hours;
    private final StringProperty full_engine_hours;
    private final StringProperty period_of_service;
    private final StringProperty filling_date;
    private final StringProperty filling_time;
    private final StringProperty service_manager;
    private final StringProperty percent_of_load;
    private final StringProperty next_service_milage;


    public MonitoringRecieveData(Integer technic_id, String name_technic, Double index_engine_hours, Integer full_engine_hours, Integer period_of_service, String filling_date, String filling_time, String service_manager,Integer next_service_milage) {
        this.technic_id = new SimpleStringProperty(String.valueOf(technic_id));
        this.name_technic = new SimpleStringProperty(name_technic);
        this.index_engine_hours = new SimpleStringProperty(String.valueOf(index_engine_hours));
        this.full_engine_hours = new SimpleStringProperty(String.valueOf(full_engine_hours));
        this.period_of_service = new SimpleStringProperty(String.valueOf(period_of_service));
        this.filling_date = new SimpleStringProperty(filling_date);
        this.filling_time = new SimpleStringProperty(filling_time);
        this.service_manager = new SimpleStringProperty(service_manager);
        this.next_service_milage = new SimpleStringProperty(String.valueOf(next_service_milage));
        this.percent_of_load = new SimpleStringProperty(String.valueOf((full_engine_hours*100)/next_service_milage));
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

    public String getName_technic() {
        return name_technic.get();
    }

    public StringProperty name_technicProperty() {
        return name_technic;
    }

    public void setName_technic(String name_technic) {
        this.name_technic.set(name_technic);
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

    public String getPeriod_of_service() {
        return period_of_service.get();
    }

    public StringProperty period_of_serviceProperty() {
        return period_of_service;
    }

    public void setPeriod_of_service(String period_of_service) {
        this.period_of_service.set(period_of_service);
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

    public String getService_manager() {
        return service_manager.get();
    }

    public StringProperty service_managerProperty() {
        return service_manager;
    }

    public void setService_manager(String service_manager) {
        this.service_manager.set(service_manager);
    }

    public String getPercent_of_load() {
        return percent_of_load.get();
    }

    public StringProperty percent_of_loadProperty() {
        return percent_of_load;
    }

    public void setPercent_of_load(String percent_of_load) {
        this.percent_of_load.set(percent_of_load);
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
