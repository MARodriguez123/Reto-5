package Modelo.vo;
import Modelo.dao.*;
import java.sql.*;

import org.sqlite.ExtendedCommand.RestoreCommand;

public class ComprasDeLiderVo 
{
  static Object[][] values;
  
  public static Object[][] valores(Integer numeroLideres)
  {
    try
    {
      int rowsCount = 0;
      
      ResultSet rs = ComprasDeLiderDao.consulta(numeroLideres);
      
      while (rs.next())
      {
      	rowsCount++;
      }
      
      rs = ComprasDeLiderDao.consulta(numeroLideres);
      values = new Object [rowsCount][2];

      rowsCount=0;
 
      while (rs.next())
      {
        values[rowsCount][0] = rs.getString("LIDER");
        values[rowsCount][1] = rs.getFloat("VALOR");
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