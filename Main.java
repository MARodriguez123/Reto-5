import java.sql.DriverManager;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vista.ReportesView;

class Main {
  public static JFrame frame;
  public static JLabel labelSeleccione;
  public static JLabel labelIngrese;
  public static JTextField textBoxCondicion;
  public static JButton buttonConsulta;
  public static JComboBox comboBox;
  public static JTable tableConsulta;
  public static JScrollPane panelTabla;

  public static void main(String[] args)
  {
    frame=new JFrame("REPORTE");
    
    labelSeleccione =new JLabel("SELECCIONE TIPO DE CONSULTA");
    labelSeleccione.setBounds(200,10,300,30);
    //pos x, pos y, width, height

    labelIngrese =new JLabel("PRUEBA");
    labelIngrese.setVisible(false);
    labelIngrese.setBounds(135,70,150,30);
    //pos x, pos y, width, height
    
    panelTabla = new JScrollPane();
    panelTabla.setVisible(false);
    panelTabla.setBounds(50,175,500,280);
    //pos x, pos y, width, height
    
    textBoxCondicion = new JTextField();
    textBoxCondicion.setVisible(false);
    textBoxCondicion.setBounds(275,70,200,30);
    //pos x, pos y, width, height
    
    buttonConsulta = new JButton("REALICE LA CONSULTA");
    buttonConsulta.setVisible(false);
    buttonConsulta.setBounds(200,120,200,30);
    //pos x, pos y, width, height

    
    comboBox = new JComboBox();
    comboBox.addItem("SELECCIONE");
    comboBox.addItem("LISTADO DE PROYECTOS POR BANCO");
    comboBox.addItem("TOTAL DEUDAS POR PROYECTO");
    comboBox.addItem("LIDERES MAS COMPRADORES");
    comboBox.setBounds(160,40,300,20);


    textBoxCondicion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        
        var reportesView = new ReportesView();
        
        if(comboBox.getSelectedItem().toString()=="LISTADO DE PROYECTOS POR BANCO")
        {
          String banco = textBoxCondicion.getText();
          
          Object[][] data = reportesView.proyectosFinanciadosPorBanco(banco);
          Object[] columnNames = ReportesView.getColumnNames();
  
          tableConsulta = new JTable(data, columnNames);
          
          tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(30);
          tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(150);
          tableConsulta.getColumnModel().getColumn(2).setPreferredWidth(130);
          tableConsulta.getColumnModel().getColumn(3).setPreferredWidth(120);
          tableConsulta.getColumnModel().getColumn(4).setPreferredWidth(70);
          tableConsulta.getColumnModel().getColumn(5).setPreferredWidth(200);
          
        }
        else if(comboBox.getSelectedItem().toString()=="TOTAL DEUDAS POR PROYECTO")
        {
          //var limiteInferior = 80_000d;
          Double limiteInferior = Double.parseDouble(textBoxCondicion.getText());
          
          Object[][] data = reportesView.totalAdeudadoPorProyectosSuperioresALimite(limiteInferior);
          Object[] columnNames = ReportesView.getColumnNames();
  
          tableConsulta = new JTable(data, columnNames);
          
          tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(30);
          tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(80);
        }
        else if(comboBox.getSelectedItem().toString()=="LIDERES MAS COMPRADORES")
        {
          int numeroLideres =  Integer.parseInt(textBoxCondicion.getText());
          
          Object[][] data = reportesView.lideresQueMasGastan(numeroLideres);
          Object[] columnNames = ReportesView.getColumnNames();
  
          tableConsulta = new JTable(data, columnNames);
          
          tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(200);
          tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(80);
        }
        
        tableConsulta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        tableConsulta.setBounds(0,0,300,200);
        
        frame.remove(panelTabla);
        panelTabla=new JScrollPane(tableConsulta);
        panelTabla.setBounds(50,175,500,280);
        frame.add(panelTabla);

        labelIngrese.setVisible(true);
        buttonConsulta.setVisible(true);
        textBoxCondicion.setVisible(true);
        panelTabla.setVisible(true);
        
      }
    });
    
    buttonConsulta.addActionListener(new ActionListener()
    {
      
      public void actionPerformed(ActionEvent e)
      {
        var reportesView = new ReportesView();
        
        if(comboBox.getSelectedItem().toString()=="LISTADO DE PROYECTOS POR BANCO")
        {
          String banco = textBoxCondicion.getText();
          
          Object[][] data = reportesView.proyectosFinanciadosPorBanco(banco);
          Object[] columnNames = ReportesView.getColumnNames();
  
          tableConsulta = new JTable(data, columnNames);
          tableConsulta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          
          tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(30);
          tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(150);
          tableConsulta.getColumnModel().getColumn(2).setPreferredWidth(130);
          tableConsulta.getColumnModel().getColumn(3).setPreferredWidth(120);
          tableConsulta.getColumnModel().getColumn(4).setPreferredWidth(70);
          tableConsulta.getColumnModel().getColumn(5).setPreferredWidth(200);
          
          tableConsulta.setBounds(0,0,300,200);
          frame.remove(panelTabla);
          panelTabla=new JScrollPane(tableConsulta);
          panelTabla.setBounds(50,175,500,280);
        }
        else if(comboBox.getSelectedItem().toString()=="TOTAL DEUDAS POR PROYECTO")
        {
          Double limiteInferior = Double.parseDouble(textBoxCondicion.getText());
          
          Object[][] data = reportesView.totalAdeudadoPorProyectosSuperioresALimite(limiteInferior);
          Object[] columnNames = ReportesView.getColumnNames();
  
          tableConsulta = new JTable(data, columnNames);
          tableConsulta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          
          tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(30);
          tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(80);
        
          tableConsulta.setBounds(0,0,110,200);
          frame.remove(panelTabla);
          panelTabla=new JScrollPane(tableConsulta);
          panelTabla.setBounds(245,175,130,280);
        }
        else if(comboBox.getSelectedItem().toString()=="LIDERES MAS COMPRADORES")
        {
          int numeroLideres =  Integer.parseInt(textBoxCondicion.getText());
          
          Object[][] data = reportesView.lideresQueMasGastan(numeroLideres);
          Object[] columnNames = ReportesView.getColumnNames();
  
          tableConsulta = new JTable(data, columnNames);
          tableConsulta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          
          tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(200);
          tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(80);
        
          tableConsulta.setBounds(0,0,280,200);
          frame.remove(panelTabla);
          panelTabla=new JScrollPane(tableConsulta);
          panelTabla.setBounds(160,175,300,280);
        }
        
        frame.add(panelTabla);
        
        labelIngrese.setVisible(true);
        buttonConsulta.setVisible(true);
        textBoxCondicion.setVisible(true);
        panelTabla.setVisible(true);
        
      }
    });
    
    comboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        textBoxCondicion.setText("");
        if(comboBox.getSelectedItem().toString()=="LISTADO DE PROYECTOS POR BANCO")
        {
          labelIngrese.setText("NOMBRE BANCO");
          frame.setTitle("LISTADO DE PROYECTOS POR BANCO");
        }
        else if(comboBox.getSelectedItem().toString()=="TOTAL DEUDAS POR PROYECTO")
        {
          labelIngrese.setText("LIMITE INFERIOR");
          frame.setTitle("TOTAL DEUDAS POR PROYECTO");
        }
        else if(comboBox.getSelectedItem().toString()=="LIDERES MAS COMPRADORES")
        {
          labelIngrese.setText("CANTIDAD LIDERES");
          frame.setTitle("LIDERES MAS COMPRADORES");
        }

        if(comboBox.getSelectedItem().toString()=="SELECCIONE")
        {
          labelIngrese.setVisible(false);
          buttonConsulta.setVisible(false);
          panelTabla.setVisible(false);
          textBoxCondicion.setVisible(false);
        }
        else
        {
          labelIngrese.setVisible(true);
          buttonConsulta.setVisible(true);
          textBoxCondicion.setVisible(true);
          panelTabla.setVisible(false);
        }
      }
    });
    
    frame.add(panelTabla);
    frame.add(buttonConsulta);
    frame.add(comboBox);
    frame.add(labelSeleccione);
    frame.add(labelIngrese);
    frame.add(textBoxCondicion);
    frame.setSize(600,500);
    frame.setLayout(null);
    frame.setVisible(true);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
  }
}



