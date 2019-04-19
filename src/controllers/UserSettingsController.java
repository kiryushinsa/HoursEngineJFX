package controllers;

import database.DataBaseHandler;

import java.io.*;
import java.sql.SQLException;


public class UserSettingsController
{


    private String ip;
    private String port;
    private String user;
    private String password;
    private String database;

    public UserSettingsController()
    {
        readFile();

    }

    public void readFile()
    {
        try{
            FileInputStream fstream = new FileInputStream("configs.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int checker = 0;
            while ((strLine = br.readLine()) != null)
            {
                checker++;
                switch (checker) {
                    case 1: setIp(strLine); break;
                    case 2: setPort(strLine);break;
                    case 3: setUser(strLine);break;
                    case 4: setPassword(strLine);break;
                    case 5: setDatabase(strLine);break;
                    default: break;
                }
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }

    }


    public void setSettings(String ip,String port,String user,String password,String database) throws SQLException, ClassNotFoundException {
        File myFile =  new File("configs.txt");

        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile,false)));
            writer.println(ip);
            writer.println(port);
            writer.println(user);
            writer.println(password);
            writer.println(database);
            writer.flush();
            writer.close();

        }
        catch (IOException e){ e.printStackTrace();}


    }

 public boolean checkSettings()
 {
     DataBaseHandler db = new DataBaseHandler();

     try {
         if (db.getDbConnection()==null)
         {
                return false;
         }
         else return true;
     } catch (ClassNotFoundException e) {
         return false;
     } catch (SQLException e) {
         return false;
     }





 }




    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }











}
