package Modelo.dao;
import java.sql.*;
import org.sqlite.SQLiteConnection;
import Utilidades.*;

public class DeudasPorProyectoDao {
// TODO Implementar la clase

  //creamos 
  private static Statement stmt = null;
  //creamos donde almacenar la consulta
  private static ResultSet rs = null;
  //Creamos conexion
  private static Connection conn = null;

  
  public static ResultSet consulta(Double limiteInferior)
  {
    try
    {
      conn = JDBCUtilities.getConnection();

      //Creamos consulta SQL
      String csql = "SELECT ID_Proyecto, SUM(Cantidad*Precio_Unidad) AS VALOR FROM Compra JOIN MaterialConstruccion ON Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion WHERE Pagado = 'No' GROUP BY ID_Proyecto HAVING SUM(Cantidad*Precio_Unidad)>"+limiteInferior+" ORDER BY VALOR DESC;";


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