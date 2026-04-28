
import java.sql.ResultSet;
package warehouse;

public class confirmation {
	 private final String[][] purchase = new String[100][3];
     private int j = 0;
 
     public confirmation() throws SQLException{
         connectiondatabase connection = new connectiondatabase();  
         connection.getconnection();
         Statement stat = connection.getconnection().createStatement();
     // to be continuee    ResultSet rs = stat.executeQuery("select * from simple_erp.pengadaan where statusPengadaan = 'Waiting'");
         while (rs.next()){
             purchase[j][0] = rs.getString("idPengadaan");
             purchase][1] = rs.getString("tanggalPengadaan");
             j++;
         }
	}
     
     public void changestatus(String idPengadaan) throws SQLException{                    
         connectiondatabase connection = new connectiondatabase();  
         connection.getconnection();
         Statement stat = connection.getconnectioni().createStatement();  
      // to be continue    int rs = stat.executeUpdate("UPDATE `simple_erp`.`pengadaan` SET `statusPengadaan` = 'Confirmed' "
                         + "WHERE `pengadaan`.`idPengadaan` = '"+idPengadaan+"';");                                                                            
	}
     
     String displaypurchase(int productKe, int atributKe){
         return purchase[productKe][atributKe];
	}
     
     Integer numberofpurchase(){
         return j;
     } 
}

}
