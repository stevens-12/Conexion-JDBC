/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudjdbc;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author steven
 */


/*CLASE USUARIO*/
public class CUsuarios {
    /*Declaración de variables*/
    int codigo;
    String nombresUsuario;
    String apellidosUsuario;
    String correoUsuario;
    
    /*Generando métodos GETTERS AND SETTERS de las variables*/

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
    
    /*Método para guardar usuario*/
    
   public void InsertarUsuario(JTextField paramNombres, JTextField paramApellidos, JTextField paramCorreo){
       
       setNombresUsuario(paramNombres.getText());
       setApellidosUsuario(paramApellidos.getText());
       setCorreoUsuario(paramCorreo.getText());
       
       CConexion objetoConexion = new CConexion();
       
       /*Consulta de SQL*/
       String consulta = "INSERT INTO  Usuarios ( nombres, apellidos, correo) VALUES (?,?,?);";
       
       /*Utilizar la consulta*/
       try {
           
           CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
           cs.setString(1, getNombresUsuario());
           cs.setString(2, getApellidosUsuario());
           cs.setString(3, getCorreoUsuario());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se Guardo el usuario correctamente");
           
       } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null, "No se guardo correctamente el usuario, Error: "+e.toString());
       }
   }
   
   /*Método para mostrar la tabla de usuarios*/
   public void MostrarUsuarios(JTable paramTablaTotalUsuarios){
       /*preparando conexión*/
       CConexion objetoConexion = new CConexion();
       /*Modelo vacio*/
       DefaultTableModel  modelo = new DefaultTableModel();
       
       /*Código para ordenar tabla*/
       TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
       paramTablaTotalUsuarios.setRowSorter(OrdenarTabla);
       
       /*Consulta SQL*/
       String sql = "";
       
       modelo.addColumn("Id");
       modelo.addColumn("Nombres");
       modelo.addColumn("Apellidos");
       modelo.addColumn("Correo");
       
       paramTablaTotalUsuarios.setModel(modelo);
       
       sql = "SELECT * FROM Usuarios;";
       
       String[] datos = new  String[4];
       Statement st;
       
       try {
           
           st = objetoConexion.establecerConexion().createStatement();
           
           ResultSet rs = st.executeQuery(sql);
           
           while (rs.next()){
               
               datos[0]  = rs.getString(1);
               datos[1]  = rs.getString(2);
               datos[2]  = rs.getString(3);
               datos[3]  = rs.getString(4);
               
               modelo.addRow(datos);
          
           }
           
           paramTablaTotalUsuarios.setModel(modelo);
           
       } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null,"No se pudo mostrar los registros, Error: "+ e.toString());
       }
       
   }
   
   /*Metodo para modificar*/
   public void SeleccionarUsuario(JTable paramTablaUsuarios, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramCorreo){
       
       try {
           
           int fila = paramTablaUsuarios.getSelectedRow();
           
           if (fila >= 0){
           
               paramId.setText((paramTablaUsuarios.getValueAt(fila,0).toString()));
               paramNombres.setText((paramTablaUsuarios.getValueAt(fila,1).toString()));
               paramApellidos.setText((paramTablaUsuarios.getValueAt(fila,2).toString()));
               paramCorreo.setText((paramTablaUsuarios.getValueAt(fila,3).toString()));

           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error de selección, Error: "+ e.toString());
       }
   }
   public void ModificarUsuarios (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos, JTextField paramCorreo){
   
       setCodigo(Integer.parseInt(paramCodigo.getText()));
       setNombresUsuario(paramNombres.getText());
       setApellidosUsuario(paramApellidos.getText());
       setCorreoUsuario(paramCorreo.getText());
       
       CConexion objetoConexion = new CConexion();
       
       String consulta = "UPDATE Usuarios SET usuarios.nombres = ?, usuarios.apellidos = ?,usuarios.correo = ? WHERE usuarios.id = ?;";
       
       try {
           
           CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
           cs.setString(1, getNombresUsuario());
           cs.setString(2, getApellidosUsuario());
           cs.setString(3, getCorreoUsuario());
           cs.setInt(4, getCodigo());
          
          cs.execute();
          
          JOptionPane.showMessageDialog(null,"Modificación exitosa");

       } catch (SQLException e) {
           
          JOptionPane.showMessageDialog(null,"Error al realizar la modificacion de usuario, Error: "+ e.toString());
       }
   }
   
  /*Eliminar usuarios*/
   public void EliminarUsuarios(JTextField paramCodigo){
   
       setCodigo(Integer.parseInt(paramCodigo.getText()));
       /*Conexion*/
       CConexion objetoConexion = new CConexion();
       /*Consulta SQL*/
       String consulta = "DELETE FROM Usuarios WHERE usuarios.id = ?;";
       
       try {
           CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
           cs.setInt(1, getCodigo());
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario");
           
       } catch (SQLException e) {
           
           JOptionPane.showMessageDialog(null,"No se pudo eliminar el usuario, Error: " +e.toString());
       }
   }
}
