package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    public void addTech(String name_technic,Integer first_milage,Integer period_of_service,double index_engine_hours) throws SQLException, ClassNotFoundException
    {
        Connection Connect = getDbConnection();

        PreparedStatement Insert = Connect.prepareStatement("INSERT INTO "+ USER_TABLE + " "+ TechnicColumns + "VALUES(?,?,?,?)");

        Insert.setString(1, name_technic);
        Insert.setInt(2,first_milage);
        Insert.setInt(3,period_of_service);
        Insert.setDouble(4, index_engine_hours);


        Insert.executeUpdate();
        System.out.println("welldone");



    }


    public void selectAllTechnic()
    {


        Connection Connect = getDbConnection();

        PreparedStatement Insert = Connect.prepareStatement("SELECT * FROM "+ USER_TABLE) ;



        Insert.executeUpdate();
        System.out.println("welldone");

    }
}
