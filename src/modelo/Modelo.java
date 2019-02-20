

package modelo;

import javax.swing.table.DefaultTableModel;
import controlador.Controlador;
import java.util.Calendar;
import javax.swing.JOptionPane;
import vista.Vista;
import static vista.Vista.Combo;
import java.sql.*;
/**
 *
 * @author Jimmy
 */
public class Modelo {
    
 
     Vista v;
     Controlador c;
     Connection cc;
     Connection cn= Conexion();
     void MostrarTabla()
    {
        DefaultTableModel  modelo= new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Sexo");
        modelo.addColumn("Fecha");
        v.Tabla.setModel(modelo);
        
        v.Combo.addItem("Selecciona");
        v.Combo.addItem("Masculino");
        v.Combo.addItem("Femenino");
        
        String sql="SELECT * FROM usuario";
        String datos[]= new String[6];
        try {
            
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            
            while(rs.next()){
            
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                modelo.addRow(datos);
            }
            v.Tabla.setModel(modelo);
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "no se pudo enviar los datos");
        }
        
        
    }
   
     public Connection Conexion(){
     
     try{
     
         Class.forName("com.mysql.jdbc.Driver");
         cc=DriverManager.getConnection("jdbc:mysql://localhost/sistema","root","");
         System.out.println("conexion exitosa");
     }catch(Exception ex){
         System.out.println(ex);
             
      }
     return cc;
     }
     
     public static void main(String[] args) {
     Modelo m= new Modelo();
     m.Conexion();
     
    }
    public void EnviarDatos(){
     
      /*Botones.add(RbMujer);a 
        Botones.add(RbHombre);-*/
            
        String dia = Integer.toString(v.Fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(v.Fecha.getCalendar().get(Calendar.MONTH));
        String anio = Integer.toString(v.Fecha.getCalendar().get(Calendar.YEAR));
        
        String fecha= (dia+"/"+mes+"/"+anio);
        
        try {
        
        PreparedStatement ppt=cn.prepareStatement("INSERT INTO usuario(Nombre,Apellido,Edad,Sexo,Fecha)"
        +"VALUES(?,?,?,?,?)");
        
        ppt.setString(1, v.TxtNombre.getText());
        ppt.setString(2, v.TxtApellido.getText());
        ppt.setString(3, v.TxtEdad.getText());
        
       
        if(v.Combo.getSelectedItem().equals("Selecciona")){
            JOptionPane.showMessageDialog(null,"Selecione su genero");}
        else{
                v.TxtNombre.setText("");
                v.TxtApellido.setText("");
                v.TxtEdad.setText("");
                ppt.setString(4,v.Combo.getSelectedItem().toString());
                ppt.setString(5,fecha);
                
              ppt.executeUpdate();
              MostrarTabla();
             JOptionPane.showMessageDialog(null,"Datos Guardados");  
        }

        } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Datos no guardados");
        }
        
               
     
        
      /*  if(RbHombre.isSelected())
        {Datos[3]= "Masculino";}
        else{
            Datos[3]= "Femenino";}*/
        
       
     }
     public void Consultar()
     {
         int fila= v.Tabla.getSelectedRow();
         if(fila>=0)
         {
             v.TxtNombre.setText(v.Tabla.getValueAt(fila, 1).toString());
             v.TxtApellido.setText(v.Tabla.getValueAt(fila, 2).toString());
             v.TxtEdad.setText(v.Tabla.getValueAt(fila, 3).toString());
             v.Combo.setSelectedItem(v.Tabla.getValueAt(fila,4).toString());
             v.txtFecha.setText(v.Tabla.getValueAt(fila, 5).toString());
             JOptionPane.showMessageDialog(null, "mostrando datos");
         }else
         {
             JOptionPane.showMessageDialog(null, "seleccione una fila valida");
         }
     
     }
        
    public void CargarMetodos(){
         MostrarTabla();
     }
    public void selecionar (){
    }
}
