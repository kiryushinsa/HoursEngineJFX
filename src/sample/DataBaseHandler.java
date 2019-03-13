package sample;

import java.sql.*;

import javafx.collections.ObservableList;
import org.postgresql.Driver;

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


    public void addTechnic(String name_technic, Integer first_milage, Integer period_of_service, double index_engine_hours) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();

        PreparedStatement Insert = Connect.prepareStatement("INSERT INTO "+ USER_TABLE + " "+ TechnicColumns + "VALUES(?,?,?,?,?,?)");
        Insert.setString(1, name_technic);
        Insert.setInt(2,first_milage);
        Insert.setInt(3,period_of_service);
        Insert.setDouble(4, index_engine_hours);
        Insert.setDouble(5, first_milage);
        Insert.setDouble(6, first_milage+period_of_service);

        Insert.executeUpdate();
        System.out.println(" insert welldone");


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
        Statement.executeUpdate("DELETE FROM technic WHERE technic_id=" +row_id);
        Connect.close();
        Statement.close();

    }
}
