import java.sql.PreparedStatement;
package warehouse;

public class confirmstock {
	private final String[][] stockpurchase = new String[100][3];
    private int j = 0;

    public confirmstock() throws SQLException{
        connectiondatabase connection = new connectiondatabase();  
        connection.getconnection();
        Statement stat = connection.getconnection().createStatement();
        ResultSet rs = stat.executeQuery("SELECT * from simple_erp.pengadaan "
                + "INNER JOIN simple_erp.detailpengadaan "
                + "ON pengadaan.idPengadaan = detailpengadaan.idPengadaan");
        while (rs.next()){
            stockpurchase[j][0] = rs.getString("idPengadaan");
            stockpurchase[j][1] = rs.getString("idProduct");
            stockpurchase[j][2] = rs.getString("Jumlah");
            j++;
        }
}
    
    public void productPurchase(String idPengadaan) throws SQLException{
        connectiondatabase connection = new connectiondatabase();  
        connection.getconnection();
        String sql = "SELECT * FROM `simple_erp`.`detailpengadaan` WHERE `idPengadaan` = `"+idPengadaan+"` ";
        PreparedStatement stat = connection.getconnection().prepareStatement(sql);
        stat.executeUpdate();
    }
    
    public void productstock(int idProduct)throws SQLException{
        String po = stokpembelian[id][0];
        String id = stokpembelian[id][1];
        String jumlah = stockpurchase[id][2];
        int total = Integer.parseInt(jumlah);
        listinwarehouse DB = new listinwarehouse();
        int totalStock = total + Integer.parseInt(DB.tampilProduct(idProduct, 4));
        
        connectiondatabase connection = new connectiondatabase();  
        connection.getconnection();
        
        Statement stmt1 = connection.getconnection().createStatement();
        int rs = stmt1.executeUpdate("UPDATE `simple_erp`.`product` SET `stockavail` = '"+totalStock+"' WHERE `product`.`id` = '"+id+"';");
        int rs1 = stmt1.executeUpdate("DELETE FROM `simple_erp`.`detailpengadaan` WHERE `id` ='"+id+"' AND `idPengadaan` = '"+po+"';");
        //String sql = ("DELETE FROM `simple_erp`.`detailpengadaan` WHERE `idProduk` =`"+id+"` AND `idPengadaan` = `"+po+"`;");
        //PreparedStatement stat = koneksi.getKoneksi().prepareStatement(sql);
        //stat.executeUpdate();
    }
    
    String displaystock(int productKe, int atributKe){
        return stockpurchase[productKe][atributKe];
}
    
    Integer productstock(){
        return j;
    } 
}


}
