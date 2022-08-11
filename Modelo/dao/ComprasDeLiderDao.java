package Modelo.dao;
import java.sql.*;
import org.sqlite.SQLiteConnection;
import Utilidades.*;

public class ComprasDeLiderDao
{
  //creamos 
  private static Statement stmt = null;
  //creamos donde almacenar la consulta
  private static ResultSet rs = null;
  //Creamos conexion
  private static Connection conn = null;

  
  public static ResultSet consulta(Integer numeroLideres)
  {
    try
    {
      conn = JDBCUtilities.getConnection();

      //Creamos consulta SQL
      String csql = "SELECT Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido AS LIDER, SUM(Precio_Unidad*Cantidad) AS VALOR FROM Lider JOIN Proyecto ON Lider.ID_Lider=Proyecto.ID_Lider JOIN Compra ON Proyecto.ID_Proyecto=Compra.ID_Proyecto JOIN MaterialConstruccion ON MaterialConstruccion.ID_MaterialConstruccion=Compra.ID_MaterialConstruccion GROUP BY LIDER ORDER BY VALOR DESC LIMIT "+numeroLideres+";";


      //Realizamos la consulta
      stmt = conn.createStatement();
      rs=stmt.executeQuery(csql);

      return rs;
    }
    catch(Exception ex)
    {
      System.out.println(ex);
      return rs;
    }
  }
}



