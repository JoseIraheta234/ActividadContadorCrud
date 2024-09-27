package Controlador;

import Modelo.Contador;
import Vista.frmContador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



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
          Modelo.setNombre_Contador(Vista.txtNombre.getText());
          Modelo.setEdad_Contador(Integer.parseInt(Vista.txtEdad.getText()));
          Modelo.setPeso_Contador(Double.parseDouble( Vista.txtPeso.getText()));
          Modelo.setCorreo_Contador(Vista.txtCorreo.getText());
          
          Modelo.Guardar();
          Modelo.Mostrar(Vista.jtbContadores);
         
      }
         
            if (e.getSource() == Vista.btnEliminar) {
            
                Modelo.Eliminar(Vista.jtbContadores);
                Modelo.Mostrar(Vista.jtbContadores);
                
      
            
        }
               if (e.getSource() == Vista.btnActualizar) {
            
            
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    Modelo.setNombre_Contador(Vista.txtNombre.getText());
                    Modelo.setEdad_Contador(Integer.parseInt(Vista.txtEdad.getText()));
                    Modelo.setPeso_Contador(Double.parseDouble( Vista.txtPeso.getText()));
                    Modelo.setCorreo_Contador(Vista.txtCorreo.getText());
                    //Ejecutar el método    
                    Modelo.Actualizar(Vista.jtbContadores);
                    Modelo.Mostrar(Vista.jtbContadores);
                    
              
            
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
