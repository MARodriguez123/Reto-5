package Modelo.dao;
import java.sql.*;
import org.sqlite.SQLiteConnection;
import Utilidades.*;

public class ProyectoBancoDao 
{
  //creamos 
  private static Statement stmt = null;
  //creamos donde almacenar la consulta
  private static ResultSet rs = null;
  //Creamos conexion
  private static Connection conn = null;

  
  public static ResultSet consulta(String banco)
  {
    try
    {
      conn = JDBCUtilities.getConnection();

      //Creamos consulta SQL
      String csql = "SELECT ID_Proyecto AS ID, Constructora, Ciudad, Proyecto.Clasificacion, Estrato, Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido AS LIDER FROM Proyecto JOIN Tipo ON Proyecto.ID_Tipo=Tipo.ID_Tipo JOIN Lider ON Proyecto.ID_Lider=Lider.ID_Lider WHERE Banco_Vinculado='"+banco+"' ORDER BY Fecha_Inicio DESC, Ciudad, Constructora";

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