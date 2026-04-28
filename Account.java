package warehouse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package modul.gudang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author deep
 */

public class account {
    String username;
    String password;

//    public account(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public void signUp (String user, String pass) throws SQLException{
        connectiondatabase connection = new connectiondatabase();
        connection.getconnection();
        Statement stmt = connection.getconnection().createStatement();
        stmt.executeUpdate("INSERT INTO `simple_erp`.`account` (`username`, `password`) VALUES ('" + user + "', '" + pass + "');");
        System.out.println("Sign up success");
    }*/
    
    public void signIn (String user, String pass) throws SQLException{
        connectiondatabase connection = new connectiondatabase();
        connection.getconnection();
        //Statement stmt = connection.getconnection().createStatement();

         //System.out.println(stmt.executeQuery("SELECT username FROM `account` WHERE `username` = 'user id mu' AND password = 'passwordmu'"));
        String query = "SELECT COUNT(*) AS TOTAL FROM `account` WHERE `username` = '"+user.trim()+"' AND password = '"+pass.trim()+"'";

        try (Statement stmt = connection.getconnection().createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String total = rs.getString("TOTAL");
                int i_total = Integer.parseInt(total);
                
                if(i_total==1){
                    System.out.println("sign in berhasil");
                }else{
                    System.out.println("sign in gagal");
                }
            }
        } catch (SQLException e) {
            System.out.println(""+e.getMessage().toString());
            //JDBCTutorialUtilities.printSQLException(e);
        }
    }
}
