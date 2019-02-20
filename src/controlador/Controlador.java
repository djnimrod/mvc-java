
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.Vista;
import modelo.Modelo;
import vista.Login;
/**
 *
 * @author JiP
 */
public class Controlador implements ActionListener{
    
    private Modelo m;
    private Vista v;
    private Login l;
    
    
    public Controlador(Modelo m, Vista v){
        this.m=m;
        this.v=v;
        this.v.enviardatos.addActionListener(this);
        this.v.bConsultar.addActionListener(this);
    }
    public void Iniciar(){
    
    v.setTitle("Sistema MVC");
    v.pack();

    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    v.setLocationRelativeTo(null);
    v.setVisible(true);
    m.CargarMetodos();
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
         if(v.enviardatos==e.getSource())
        {
            try{
                m.EnviarDatos();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else
        {
             if(v.bConsultar==e.getSource())
             {
            try{
                m.Consultar();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
        }
    }


}
