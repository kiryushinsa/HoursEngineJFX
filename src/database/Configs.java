package database;

public class Configs
{
    protected String dbHost="192.168.95.98";
    protected String dbport="5432";
    protected String dbUser="admin";
    protected String dbPassword="vsks";
    protected String dbName="enginehours";

    protected  String TECHNIC_TABLE ="technic";
    protected  String JUSING_TABLE ="journal_using";
    protected String JTo_TABLE="journal_tech_service";

    protected String TechnicColumns ="(name_technic,first_milage,period_of_service,index_engine_hours,full_engine_hours,next_service_milage)";
    protected String JUSINGColumns ="(filling_date ,filling_time,id_technic,work_time,order_on_task,comment_of_using)";
    protected String JToColumns="(filling_date,filling_time,id_technic,service_manager,type_of_service,comment_of_tech_service,reset,milage_correction,previos_service_to)";



}
