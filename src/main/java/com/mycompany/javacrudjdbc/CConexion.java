/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author steven
 */

public class CConexion {
    
     Connection conectar = null;
     
     String usuario = "root";
     String contrasenia = "";
     String bd = "dbCrudJDBC";
     String ip = "localhost";
     String puerto = "3306";
     
     String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
     
     public Connection establecerConexion(){
         
         try {
             
             Class.forName("com.mysql.cj.jdbc.Driver");
             conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
             JOptionPane.showMessageDialog(null,"La conexión a la base de datos se realizó con éxito");
             
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null,"Error al conectar la base de datos, Error: "+e.toString());
         }
         
         return conectar;
     }
}
