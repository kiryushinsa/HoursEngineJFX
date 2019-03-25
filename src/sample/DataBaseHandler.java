package sample;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;

public class DataBaseHandler extends  Configs
{

public static void main(String[] args)
{

}


    public Connection getDbConnection() throws ClassNotFoundException,SQLException
    {
        String url = "jdbc:postgresql://"+dbHost +":" + dbport+"/" +dbName;
        Class.forName("org.postgresql.Driver");

        try {


            Connection dbconnect = DriverManager.getConnection(url, dbUser, dbPassword);
            return dbconnect;
        }
        catch (SQLException e){ e.printStackTrace(); return null;}



    }


    public void setTechnic(String name_technic, Integer first_milage, Integer period_of_service, double index_engine_hours) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();

        PreparedStatement Insert = Connect.prepareStatement("INSERT INTO "+ TECHNIC_TABLE + " "+ TechnicColumns + "VALUES(?,?,?,?,?,?)");
        Insert.setString(1, name_technic);
        Insert.setInt(2,first_milage);
        Insert.setInt(3,period_of_service);
        Insert.setDouble(4, index_engine_hours);
        Insert.setDouble(5, first_milage);
        Insert.setDouble(6, first_milage+period_of_service);

        Insert.executeUpdate();
        Insert.close();



Connect.close();

    }


    public void getAllRowsTechnic(ObservableList <TechnicReceiveData> Data) throws SQLException, ClassNotFoundException {

        Connection Connect = getDbConnection();
        Statement Statement= Connect.createStatement();
        ResultSet SELECT = Statement.executeQuery("SELECT technic_id, name_technic,first_milage,period_of_service,index_engine_hours,full_engine_hours,next_service_milage FROM technic");

        while (SELECT.next())
        {
            String id = SELECT.getString("technic_id");
            String name_technic = SELECT.getString("name_technic");
            String first_milage = SELECT.getString("first_milage");
            String period_of_service = SELECT.getString("period_of_service");
            String index_engine_hours = SELECT.getString("index_engine_hours");
            String full_engine_hours = SELECT.getString("full_engine_hours");
            String next_service_milage = SELECT.getString("next_service_milage");
            Data.add(new TechnicReceiveData(id ,name_technic ,first_milage ,period_of_service ,index_engine_hours , full_engine_hours , next_service_milage));
        }

        Connect.close();
            Statement.close();
                SELECT.close();

    }

    public void deleteTechnicRow(String row_id) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();
        Statement Statement = Connect.createStatement();
        Statement.executeUpdate("DELETE FROM journal_using WHERE id_technic=" +row_id);
        Statement.executeUpdate("DELETE FROM journal_tech_service WHERE id_technic=" +row_id);
        Statement.executeUpdate("DELETE FROM technic WHERE technic_id=" +row_id);

        Connect.close();
        Statement.close();

    }

    public void setJournalUsing(java.util.Date date, LocalTime time, Integer id_technics, Double usingtime,String order, String note) throws SQLException, ClassNotFoundException {
        Connection Connect = getDbConnection();
        PreparedStatement Insert = Connect.prepareStatement("INSERT INTO "+ JUSING_TABLE +"" + JUSINGColumns + "VALUES(?,?,?,?,?,?)");

        Insert.setObject(1,date);
        Insert.setObject(2,time);
        Insert.setInt(3,id_technics);
        Insert.setDouble(4,usingtime);
        Insert.setString(5,order);
        Insert.setString(6,note);

        Insert.executeUpdate();
        Insert.close();
        Connect.close();
        System.out.println("execute in jornal_using  succesful");

    }


    public void getTechnicRowForChoiseList(ObservableList <String> Data, HashMap <String,Integer> Map) throws SQLException, ClassNotFoundException {
        Connection Connect = getDbConnection();
        Statement Statement= Connect.createStatement();
        ResultSet SELECT = Statement.executeQuery("SELECT technic_id, name_technic FROM technic");

        while (SELECT.next())
        {
            Integer id = SELECT.getInt("technic_id");
            String name_technic = SELECT.getString("name_technic");


            Map.put(name_technic,id);
            Data.add(name_technic);
        }
        Connect.close();
        Statement.close();
        SELECT.close();
    }

    public void getAllRowsJUsing(ObservableList <JournalUsingRecieveData> Data) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();
        Statement Statement= Connect.createStatement();
        ResultSet SELECT = Statement.executeQuery("SELECT " + "id_note,filling_date ,filling_time,id_technic,work_time,order_on_task,comment_of_using FROM journal_using");

        while (SELECT.next())
        {
            String id = SELECT.getString("id_note");
            String filling_date = SELECT.getString("filling_date");
            String filling_time = SELECT.getString("filling_time");
            String id_technic = SELECT.getString("id_technic");
            String work_time = SELECT.getString("work_time");
            String order_on_task = SELECT.getString("order_on_task");
            String comment_of_using = SELECT.getString("comment_of_using");
            Data.add(new JournalUsingRecieveData(id,filling_date,filling_time,id_technic,work_time,order_on_task,comment_of_using));
        }

        Connect.close();
        Statement.close();
        SELECT.close();

    }

    public void deleteJusingRow(String row_id,Integer id_technic, Integer milage ) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();
        Statement Statement = Connect.createStatement();
        Statement.executeUpdate("DELETE FROM journal_using WHERE id_note=" + row_id);
        Statement.executeUpdate( "UPDATE technic SET full_engine_hours=full_engine_hours-" + milage +"WHERE id_technic="+ id_technic);
        Connect.close();
        Statement.close();

    }

    public void getAllRowsJTO(ObservableList <JournalToReceiveData> Data) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();
        Statement Statement= Connect.createStatement();
        ResultSet SELECT = Statement.executeQuery("SELECT " + "id_tech,filling_date,filling_time,id_technic,service_manager,type_of_service,comment_of_tech_service,reset FROM journal_tech_service");

        while (SELECT.next())
        {
            String id_tech = SELECT.getString("id_tech");
            String filling_date = SELECT.getString("filling_date");
            String filling_time = SELECT.getString("filling_time");
            String id_technic = SELECT.getString("id_technic");
            String service_manager = SELECT.getString("service_manager");
            String type_of_service = SELECT.getString("type_of_service");
            String comment_of_tech_service = SELECT.getString("comment_of_tech_service");


            Boolean resetTO = SELECT.getBoolean("reset");
            String reset;
            if(resetTO==true){ reset="Сброшено";}
            else reset="Не сброшены";


            Data.add(new JournalToReceiveData(id_tech,filling_date,filling_time,id_technic,service_manager,type_of_service,comment_of_tech_service,reset));
        }

        Connect.close();
        Statement.close();
        SELECT.close();

    }


    public void setJournalTo(java.util.Date date, LocalTime time, Integer id_technics, String fio,String type, String comment,Boolean reset) throws SQLException, ClassNotFoundException {
        Connection Connect = getDbConnection();
        PreparedStatement Insert = Connect.prepareStatement("INSERT INTO "+ "journal_tech_service" +" " + JToColumns + "VALUES(?,?,?,?,?,?,?)");

        Insert.setObject(1,date);
        Insert.setObject(2,time);
        Insert.setInt(3,id_technics);
        Insert.setString(4,fio);
        Insert.setString(5,type);
        Insert.setString(6,comment);
        Insert.setBoolean(7,reset);

        Insert.executeUpdate();
        Insert.close();
        Connect.close();
        System.out.println("execute in jornal_to  succesful");

    }


    public void deleteJToRow(String row_id, boolean reset) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();
        Statement Statement = Connect.createStatement();
        Statement.executeUpdate("DELETE FROM journal_tech_service WHERE id_tech=" + row_id);
        if(reset){Statement.executeUpdate("UPDATE technic SET next_service_milage=(next_service_milage)-period_of_service WHERE technic_id="+ row_id);}
        Connect.close();
        Statement.close();

    }

    public void updateTechnicAfterTO(Integer id_technic) throws SQLException, ClassNotFoundException {
        Connection Connect = getDbConnection();
        PreparedStatement Update = Connect.prepareStatement("UPDATE technic SET next_service_milage = next_service_milage + period_of_service WHERE technic_id = ? ");
        Update.setInt(1,id_technic);
        Update.executeUpdate();
        Update.close();
        Connect.close();

    }

    public void updateTechnicAfterUsing(Integer id_technic,Double milage) throws SQLException, ClassNotFoundException {
        Connection Connect = getDbConnection();
        PreparedStatement Update = Connect.prepareStatement("UPDATE technic SET full_engine_hours = full_engine_hours +(?*index_engine_hours) WHERE technic_id = ? ");
        Update.setDouble(1,milage);
        Update.setInt(2,id_technic);
        Update.executeUpdate();
        Update.close();
        Connect.close();

    }


    public void selectTechnicMonitoring(ObservableList <MonitoringRecieveData> Data) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();
        Statement Statement= Connect.createStatement();
        ResultSet SELECT = Statement.executeQuery("\n" +
                "SELECT t1.technic_id,t1.name_technic,t1.index_engine_hours,t1.full_engine_hours,t1.period_of_service,t1.next_service_milage,t2.filling_date,t2.service_manager,t2.filling_time\n" +
                "FROM technic t1,journal_tech_service t2\n" +
                "WHERE t2.id_technic=t1.technic_id order by t1.technic_id desc, t2.filling_date desc,t2.filling_time desc\n" +
                "");


        ArrayList <Integer> SelectionChecker = new ArrayList<>();

        while (SELECT.next())
        {

        Integer id  = SELECT.getInt("technic_id");
            String name = SELECT.getString("name_technic");
                    Double index  = SELECT.getDouble("index_engine_hours");
                            Integer hours  = SELECT.getInt("full_engine_hours");
                                    Integer period = SELECT.getInt("period_of_service");
                                            String  date = SELECT.getString("filling_date");
                                                    String time  = SELECT.getString("filling_time");
                                                            String manager  = SELECT.getString("service_manager");
                                                                  Integer nextToMilage = SELECT.getInt("next_service_milage");

        if(!SelectionChecker.contains(id)) { Data.add(new MonitoringRecieveData(id,name,index,hours,period,date,time,manager,nextToMilage)); SelectionChecker.add(id); }

        }

         SelectionChecker.clear();
            Connect.close();
                 Statement.close();
                      SELECT.close();

    }


}
