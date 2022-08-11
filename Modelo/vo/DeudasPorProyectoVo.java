package Modelo.vo;
import Modelo.dao.*;
import java.sql.*;

import org.sqlite.ExtendedCommand.RestoreCommand;

public class DeudasPorProyectoVo 
{
  static Object[][] values;
  
  public static Object[][] valores(Double limiteInferior)
  {
    try
    {
      int rowsCount = 0;
      
      ResultSet rs = DeudasPorProyectoDao.consulta(limiteInferior);
      
      while (rs.next())
      {
      	rowsCount++;
      }
      
      rs = DeudasPorProyectoDao.consulta(limiteInferior);
      values = new Object [rowsCount][2];

      rowsCount=0;
 
      while (rs.next())
      {
        values[rowsCount][0] = rs.getInt("ID_Proyecto");
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