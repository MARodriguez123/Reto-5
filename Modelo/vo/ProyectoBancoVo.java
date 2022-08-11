package Modelo.vo;
import Modelo.dao.*;
import java.sql.*;

import org.sqlite.ExtendedCommand.RestoreCommand;

public class ProyectoBancoVo
{
  static Object[][] values;
  
  public static Object[][] valores(String banco)
  {
    try
    {
      int rowsCount = 0;
      
      ResultSet rs = ProyectoBancoDao.consulta(banco);
      
      while (rs.next())
      {
      	rowsCount++;
      }
      
      rs = ProyectoBancoDao.consulta(banco);
      values = new Object [rowsCount][6];

      rowsCount=0;
 
      while (rs.next())
      {
        values[rowsCount][0] = rs.getInt("ID");
        values[rowsCount][1] = rs.getString("Constructora");
        values[rowsCount][2] = rs.getString("Ciudad");
        values[rowsCount][3] = rs.getString("Clasificacion");
        values[rowsCount][4] = rs.getInt("Estrato");
        values[rowsCount][5] = rs.getString("LIDER");
        rowsCount++;
      }
      rs.close();
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
    return values;
  }
}