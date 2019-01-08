/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorSerial.Dao;

import MonitorSerial.Dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import MonitorSerial.model.Temperatura;

/**
 *
 * @author Victor
 */
public class Sensor1DAO {
      public boolean save (Temperatura t) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO sensor1(temperatura,umidade,lux) VALUES (?,?,?)");
            stmt.setFloat(1, t.getTemperatura());
            stmt.setFloat(2, t.getUmidade());
            stmt.setFloat(3, t.getLux());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("erro ao salvar" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }
    }
//      public static void main(String [] args){
//      
//      Temperatura temp = new Temperatura();
//      temp.setTemperatura(23.0);
//      temp.setUmidade(12);
//      
//      Sensor1DAO s = new Sensor1DAO();
//      if(s.save(temp)){
//          System.out.println("salvo");
//      
//      }else { System.err.println("errou");}
//      
//      }
}


