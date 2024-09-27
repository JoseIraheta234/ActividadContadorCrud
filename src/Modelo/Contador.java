package Modelo;

import Vista.frmContador;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Contador {

    public String getUUID_Contador() {
        return UUID_Contador;
    }

    public void setUUID_Contador(String UUID_Contador) {
        this.UUID_Contador = UUID_Contador;
    }

    public String getNombre_Contador() {
        return Nombre_Contador;
    }

    public void setNombre_Contador(String Nombre_Contador) {
        this.Nombre_Contador = Nombre_Contador;
    }

    public int getEdad_Contador() {
        return Edad_Contador;
    }

    public void setEdad_Contador(int Edad_Contador) {
        this.Edad_Contador = Edad_Contador;
    }

    public double getPeso_Contador() {
        return Peso_Contador;
    }

    public void setPeso_Contador(double Peso_Contador) {
        this.Peso_Contador = Peso_Contador;
    }

    public String getCorreo_Contador() {
        return Correo_Contador;
    }

    public void setCorreo_Contador(String Correo_Contador) {
        this.Correo_Contador = Correo_Contador;
    }
    
    String UUID_Contador;
    String Nombre_Contador;
    int Edad_Contador;
    double Peso_Contador;
    String Correo_Contador;
    
    
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addContador = conexion.prepareStatement("INSERT INTO tbContador(UUID_Contador, Nombre_Contador, Edad_Contador, Peso_Contador,Correo_Contador) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addContador.setString(1, UUID.randomUUID().toString());
            addContador.setString(2, getNombre_Contador());
            addContador.setInt(3, getEdad_Contador());
            addContador.setDouble(4, getPeso_Contador());
            addContador.setString(5, getCorreo_Contador());
 
            //Ejecutar la consulta
            addContador.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
       public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloContador = new DefaultTableModel();
        modeloContador.setColumnIdentifiers(new Object[]{"UUID_Contador", "Nombre_Contador", "Edad_Contador", "Peso_Contador","Correo_Contador"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbContador");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloContador.addRow(new Object[]{rs.getString("UUID_Contador"), 
                    rs.getString("Nombre_Contador"), 
                    rs.getInt("Edad_Contador"), 
                    rs.getString("Peso_Contador"),
                    rs.getString("Correo_Contador")
                
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloContador);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
       
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbContador where UUID_Contador = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
        
          public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbContador set Nombre_Contador= ?, Edad_Contador = ?, Peso_Contador = ?, Correo_Contador = ? where UUID_Contador = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_Contador());
                updateUser.setInt(2, getEdad_Contador());
                updateUser.setDouble(3, getPeso_Contador());
                updateUser.setString(4, getCorreo_Contador());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
          
           public void limpiar(frmContador vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
           
          public void cargarDatosTabla(frmContador vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbContadores.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbContadores.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbContadores.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbContadores.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbContadores.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbContadores.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }
}
