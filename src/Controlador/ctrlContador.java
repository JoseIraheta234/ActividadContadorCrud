package Controlador;

import Modelo.Contador;
import Vista.frmContador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;



public class ctrlContador implements MouseListener{
    private Contador Modelo;
    private frmContador Vista;
    
    public ctrlContador(frmContador Vista, Contador Modelo){
        this.Vista = Vista;
        this.Modelo = Modelo;
        
        Vista.btnGuardar.addMouseListener(this);  
        
        Vista.jtbContadores.addMouseListener(this);
        Modelo.Mostrar(Vista.jtbContadores);
        
        Vista.btnEliminar.addMouseListener(this);
       
        
        Vista.btnActualizar.addMouseListener(this);
        
        Vista.btnLimpiar.addMouseListener(this);
        
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
  
         if(e.getSource() == Vista.btnGuardar){
             boolean validacionesCorrectas = true;
             
             if(Vista.txtNombre.getText().isEmpty()||Vista.txtEdad.getText().isEmpty()||Vista.txtPeso.getText().isEmpty()||Vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(Vista, "Llene los campos");
                validacionesCorrectas = false;
            }else {
              try {
                int edadNumerica = Integer.parseInt(Vista.txtEdad.getText());
                if(edadNumerica > 100 || edadNumerica < 18){
                    JOptionPane.showMessageDialog(Vista, "Ingrese una edad valida");
                    validacionesCorrectas = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Ingrese solo numeros");
                validacionesCorrectas = false;
            }
             
               if(!Vista.txtCorreo.getText().contains("@") || !Vista.txtCorreo.getText().contains(".com")){
                JOptionPane.showMessageDialog(Vista, "Ingrese un correo valido");
                validacionesCorrectas = false;
            }
                try {
                Double Peso = Double.parseDouble(Vista.txtPeso.getText());
                if(Peso > 635 || Peso < 35){
                    JOptionPane.showMessageDialog(Vista, "Ingrese un peso valido Ejemplo: 75.0 ");
                    validacionesCorrectas = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Ingrese solo numeros");
                validacionesCorrectas = false;
            }
               
               
               
             }
             
             
              if(validacionesCorrectas){
          Modelo.setNombre_Contador(Vista.txtNombre.getText());
          Modelo.setEdad_Contador(Integer.parseInt(Vista.txtEdad.getText()));
          Modelo.setPeso_Contador(Double.parseDouble( Vista.txtPeso.getText()));
          Modelo.setCorreo_Contador(Vista.txtCorreo.getText());
          
          Modelo.Guardar();
          Modelo.Mostrar(Vista.jtbContadores);
          
              }
         
      }
         
            if (e.getSource() == Vista.btnEliminar) {
            
                Modelo.Eliminar(Vista.jtbContadores);
                Modelo.Mostrar(Vista.jtbContadores);
                
      
            
        }
            
            if(e.getSource() == Vista.btnActualizar){
             boolean validacionesCorrectas = true;
             
             if(Vista.txtNombre.getText().isEmpty()||Vista.txtEdad.getText().isEmpty()||Vista.txtPeso.getText().isEmpty()||Vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(Vista, "Llene los campos");
                validacionesCorrectas = false;
            }else {
              try {
                int edadNumerica = Integer.parseInt(Vista.txtEdad.getText());
                if(edadNumerica > 100 || edadNumerica < 18){
                    JOptionPane.showMessageDialog(Vista, "Ingrese una edad valida");
                    validacionesCorrectas = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Ingrese solo numeros");
                validacionesCorrectas = false;
            }
             
               if(!Vista.txtCorreo.getText().contains("@") || !Vista.txtCorreo.getText().contains(".com")){
                JOptionPane.showMessageDialog(Vista, "Ingrese un correo valido");
                validacionesCorrectas = false;
            }
                try {
                Double Peso = Double.parseDouble(Vista.txtPeso.getText());
                if(Peso > 635 || Peso < 35){
                    JOptionPane.showMessageDialog(Vista, "Ingrese un peso valido Ejemplo: 75.0");
                    validacionesCorrectas = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Ingrese solo numeros");
                validacionesCorrectas = false;
            }
               
               
               
             }
             
             
              if(validacionesCorrectas){
          Modelo.setNombre_Contador(Vista.txtNombre.getText());
          Modelo.setEdad_Contador(Integer.parseInt(Vista.txtEdad.getText()));
          Modelo.setPeso_Contador(Double.parseDouble( Vista.txtPeso.getText()));
          Modelo.setCorreo_Contador(Vista.txtCorreo.getText());
          
          Modelo.Actualizar(Vista.jtbContadores);
          Modelo.Mostrar(Vista.jtbContadores);
          
              }
         
      }
            
               
               
                 if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.jtbContadores) {
            Modelo.cargarDatosTabla(Vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
