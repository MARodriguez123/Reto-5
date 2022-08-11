package vista;
import Utilidades.*;
import java.sql.*;
import Modelo.dao.*;
import Modelo.vo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportesView
{
  
  private static Object[] columnNames;
  private Object[][] data;

  
  public static Object[] getColumnNames ()
  {
     return columnNames;
  }
  
  public Object[][] proyectosFinanciadosPorBanco(String banco)
  {
    
    if (banco != null && !banco.isBlank())
    {
      Object[] columnNames2 = {"ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"};
      columnNames = columnNames2;
      try
      {
        data=ProyectoBancoVo.valores(banco);
      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
    }
    return data;
  }
  
  public Object[][] totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior)
  {
    if (limiteInferior != null)
    {
      Object[] columnNames2 = {"ID", "VALOR"};
      columnNames = columnNames2;
      try
      {
        data=DeudasPorProyectoVo.valores(limiteInferior);
      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
    }
    return data;
  }
  
  public Object[][] lideresQueMasGastan(Integer numeroLideres)
  {
    Object[] columnNames2 = {"LIDER", "VALOR"};
    columnNames = columnNames2;
    try
    {
      data=ComprasDeLiderVo.valores(numeroLideres);
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
    return data;
  }
  
  private Object repitaCaracter(Character caracter, Integer veces)
  {
    String respuesta = "";
    for (int i = 0; i < veces; i++)
    {
      respuesta += caracter;
    }
    return respuesta;
  }
}