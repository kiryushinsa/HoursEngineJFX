package recievers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JournalToReceiveData
{
    private final StringProperty id_tech;
    private final StringProperty filling_date;
    private final StringProperty filling_time;
    private final StringProperty id_technic;
    private final StringProperty service_manager;
    private final StringProperty type_of_service;
    private final StringProperty comment_of_tech_service;
    private final StringProperty resetTO;

    public JournalToReceiveData(String id_tech, String filling_date, String filling_time, String id_technic, String service_manager, String type_of_service, String comment_of_tech_service, String resetTO) {
        this.id_tech = new SimpleStringProperty(id_tech);
        this.filling_date = new SimpleStringProperty(filling_date);
        this.filling_time = new SimpleStringProperty(filling_time);
        this.id_technic = new SimpleStringProperty(id_technic);
        this.service_manager = new SimpleStringProperty(service_manager);
        this.type_of_service = new SimpleStringProperty(type_of_service);
        this.comment_of_tech_service = new SimpleStringProperty(comment_of_tech_service);
        this.resetTO = new SimpleStringProperty(resetTO);
    }


    public String getId_tech() {
        return id_tech.get();
    }

    public StringProperty id_techProperty() {
        return id_tech;
    }

    public void setId_tech(String id_tech) {
        this.id_tech.set(id_tech);
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

    public String getService_manager() {
        return service_manager.get();
    }

    public StringProperty service_managerProperty() {
        return service_manager;
    }

    public void setService_manager(String service_manager) {
        this.service_manager.set(service_manager);
    }

    public String getType_of_service() {
        return type_of_service.get();
    }

    public StringProperty type_of_serviceProperty() {
        return type_of_service;
    }

    public void setType_of_service(String type_of_service) {
        this.type_of_service.set(type_of_service);
    }

    public String getComment_of_tech_service() {
        return comment_of_tech_service.get();
    }

    public StringProperty comment_of_tech_serviceProperty() {
        return comment_of_tech_service;
    }

    public void setComment_of_tech_service(String comment_of_tech_service) {
        this.comment_of_tech_service.set(comment_of_tech_service);
    }

    public String getResetTO() {
        return resetTO.get();
    }

    public StringProperty resetTOProperty() {
        return resetTO;
    }

    public void setResetTO(String resetTO) {
        this.resetTO.set(resetTO);
    }




}
