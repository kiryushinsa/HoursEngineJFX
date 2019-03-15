package sample;

public class Configs
{
    protected String dbHost="localhost";
    protected String dbport="5432" ;
    protected String dbUser="user";
    protected String dbPassword="vsks";
    protected String dbName="enginehours";

    protected  String TECHNIC_TABLE ="technic";
    protected  String JUSING_TABLE ="journal_using";
  protected String TechnicColumns ="(name_technic,first_milage,period_of_service,index_engine_hours,full_engine_hours,next_service_milage)";

    protected String JUSINGColumns ="(filling_date ,filling_time,id_technic,order_on_task,comment_of_using)";


}
