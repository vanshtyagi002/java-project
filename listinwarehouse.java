package warehouse;

//public class listinwarehouse {

//}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package modul.gudang;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author deep
 */
public class listinwarehouse {
        private final String[][] product = new String[100][6];
        private int i = 0;
        

	public listinwarehouse() throws SQLException{
            connectiondatabase connection = new connectiondatabase();  
            connection.getconnection();
            Statement stat = connection.getconnection().createStatement();
            ResultSet rs = stat.executeQuery("select * from simple_erp.product");
            while (rs.next()){
                product[i][0] = rs.getString("id");
                product[i][1] = rs.getString("name");
                product[i][2] = rs.getString("cost");
                product[i][3] = rs.getString("price");
                product[i][4] = rs.getString("stockavail");
                product[i][5] = rs.getString("shelfstock");
                i++;
            }
	}
        
        public void createNewProduct(String id, String name, int cost, int price, int stock, int shelf) throws SQLException{
            connectiondatabase connection = new connectiondatabase();  
            connection.getconnection();
            String sql = "INSERT INTO `simple_erp`.`product` "
                    + "(`id`, `name`, `cost`, `totalprice`, `stockavail`, `shelfstock`) "
                    + "VALUES ('"+id+"', '"+name+"', '"+cost+"', '"+price+"', '"+stock+"', '"+shelf+"')";
            PreparedStatement stat = connection.getconnection().prepareStatement(sql);
            stat.executeUpdate();
	}
        
        public void deleteProduct(String order) throws SQLException{
            connectiondatabase connection = new conectiondatabase();  
            connection.getconnection();
            Statement stmt1 = connection.getconnection().createStatement();
            int rs = stmt1.executeUpdate("DELETE FROM `simple_erp`.`product` WHERE `id` ='"+order+"';");
	}
        
	public void stockOpname(String order, String stock, String stockk2, int opname) throws SQLException{
            int warehouse = Integer.parseInt(stock) - opname;
            int shelf = Integer.parseInt(stock2) + opname;
            connectiondatabase connection = new connectiondatabase();  
            connection.getconnection();
            Statement stmt1 = connection.getconnection().createStatement();
            int rs = stmt1.executeUpdate("UPDATE `simple_erp`.`product` SET `stockavail` = '"+warehouse+"' WHERE `product`.`idProduct` = '"+order+"';");
            int rs1 = stmt1.executeUpdate("UPDATE `simple_erp`.`product` SET `shelfstock` = '"+shelf+"' WHERE `product`.`idProduct` = '"+order+"';");
	}


	public void editProduct(String id, String name, int cost, int price, int stockavail) throws SQLException{
                        
            String update = JOptionPane.showInputDialog(null, "choose edit :\n"
            + "1. Edit basedon ID Product\n"
            + "2. Edit basedon name Product");
            
            connectiondatabase connection = new connectiondatabase();  
            connection.getconnection();
            Statement stat = connection.getconnection().createStatement();
    
            int choose = Integer.parseInt(update);
            switch (choose) {
                case 1:
                    try {
                    int rs = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `name` = '"+name+"' "
                            + "WHERE `product`.`id` = '"+id+"';");
                    int rs1 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `cost` = '"+cost+"' "
                            + "WHERE `product`.`id` = '"+idt+"';");
                    int rs2 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `price` = '"+price+"' "
                            + "WHERE `product`.`id` = '"+id+"';");
                    int rs3 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `stockavail` = '"+stockavailg+"' "
                            + "WHERE `produk`.`id` = '"+id+"';");
                    } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Make sure the Product ID is the same as the previous data");
                    }
                    break;
                case 2: 
                    try {
                    int rs4 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `id` = '"+id+"' "
                            + "WHERE `product`.`name` = '"+name+"';");
                    int rs5 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `cost` = '"+cost+"' "
                            + "WHERE `product`.`name` = '"+name+"';");
                    int rs6 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `price` = '"+price+"' "
                            + "WHERE `product`.`name` = '"+name+"';");
                    int rs7 = stat.executeUpdate("UPDATE `simple_erp`.`product` SET `stockavail` = '"+stockavail+"' "
                            + "WHERE `product`.`name` = '"+name+"';"); 
                    } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Make sure the Product ID is the same as the previous data");
                    }
                    
                    break;
            }
	}

	String displayProduct(int productKe, int atributKe){
            return product[productKe][atributKe];
	}
        
        Integer accountproduct(){
            return i;
        } 
}
