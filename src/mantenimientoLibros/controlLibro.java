/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoLibros;



import principal.conexion;
import java.awt.Dimension;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ACER
 */
public class controlLibro {
    libro L = new libro();
    interfazLibro IL;
    ejemplarLibro EL = new ejemplarLibro();
    conexion c = new conexion();
    interfazModificarLibro modificar ;
    public String titulo;
    public String autor;
    public String ano;
    public String cantidad;
    public String clasificacion;
    public String SQL;
    String Estado ="";
    
    String TODO="";
    boolean respuesta ;
    boolean resultado ;
    String Faltantes =" ";
    String ClaveLibro="";
    
     
    DefaultTableModel DTMLib = new DefaultTableModel();
    JTable TbConsultaLib = new JTable();
    
    DefaultTableModel DTMRegLib = new DefaultTableModel();
    JTable TbConsulTitLib = new JTable();
    
    Connection Conexion;
    ResultSet resultset;
    Statement statement;
    ResultSetMetaData resultsetMD;
    PreparedStatement Pstatement;
    
    
    /*Constructor*/
    public controlLibro(){
        
        /*Columnas de Tabla de Consultas Libros*/
        DTMLib.addColumn("Clave Libro");
        DTMLib.addColumn("Ejemplar");
        DTMLib.addColumn("Título");
        DTMLib.addColumn("Autor");
        DTMLib.addColumn("Clasificación");
        DTMLib.addColumn("Editorial");
        DTMLib.addColumn("Año");
        DTMLib.addColumn("Estado");
        TbConsultaLib.setModel(DTMLib);
        
        /*Columnas de Tabla de Registros Libros */
        
        DTMRegLib.addColumn("Clave Libro");
        DTMRegLib.addColumn("Título");
        DTMRegLib.addColumn("Autor");
        DTMRegLib.addColumn("Año");
        DTMRegLib.addColumn("Editorial");
        DTMRegLib.addColumn("Clasificación");
        DTMRegLib.addColumn("Excistencia");
        DTMRegLib.addColumn("Disponibles");
        TbConsulTitLib.setModel(DTMRegLib);
    }
    
    
    public boolean verificarBD(){
        resultado = true;
        /*Checar getConexion antes c.conexion();*/
        Conexion = c.getConexion();
        try
        {
        
        statement=Conexion.createStatement();    
        statement.execute("SELECT * FROM Libro");
        resultset=statement.getResultSet();
        while(resultset.next()){
            if (resultset.getRow() == 0){ 
                resultado = true;
                System.out.println("Esta Vacio");
             }else{ 
                resultado = false;
                System.out.println("No esta Vacio");
            }
        }
        }catch(SQLException e){
        }

        return resultado;
    }
    
    public boolean validarDatos(String titulo,String autor,String ano,String Editorial,String cantidad,String clasificacion){
        IL = new interfazLibro();
        respuesta = false;
        Faltantes = "";
        
        
        if(titulo.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Título."+"\n";
            respuesta = true;
        }
        if(autor.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Autor."+"\n";
            respuesta = true;
        }
        if(ano.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Año."+"\n";
            respuesta = true;
        }
        if(Editorial.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Editorial."+"\n";
            respuesta = true;
        }
        if(cantidad.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Cantidad."+"\n";
            respuesta = true;
        }
        if(clasificacion.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se seleccione: Clasificación."+"\n";
            respuesta = true;
        }
        
        if(respuesta){
            JOptionPane.showMessageDialog(IL.jpCatalogo,Faltantes);
        }
        return respuesta;
    }
    
    
    public String generarClaveLibro(){
        Conexion = c.getConexion();
  
        String ClaveMax=null;
        String ClaveLibro=null;
        String b="";
        
        if(resultado){
            ClaveLibro = "DASLIB00001";
        }else{     
            try{
                statement=Conexion.createStatement();    
                statement.execute("SELECT MAX(claveLibro) FROM libro");
                resultset=statement.getResultSet();
                while(resultset.next()){
                    ClaveMax=resultset.getString(1);  
                }
            }catch(SQLException e){
            }

            int cont_numero=0,cont_total=0;
            String CMCortarNum=null;

            char[] arrayChar = ClaveMax.toCharArray();

            for(int i=0; i<arrayChar.length; i++){
                cont_total++;
                    if(isNumeric(arrayChar[i])){
                    cont_numero++;}
            }
            if(cont_total==11){
                System.out.println("maximo"+ClaveMax);
                CMCortarNum=ClaveMax.substring(6,11);
            }

            System.out.println("cortar"+CMCortarNum);
            int CMNum = Integer.parseInt(CMCortarNum);


            String CMNumS = Integer.toString(CMNum);
            char[] arrayChar_pre_1 = CMNumS.toCharArray();
            int CMNCont=0;
            for(int i=0; i<arrayChar_pre_1.length; i++){
                CMNCont++;        
            }
            System.out.println("Contador pre_1:"+CMNCont);

            int Clavesuma=0;


            if(CMNCont==1){
            System.out.println("estoy en 1");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveLibro ="DASLIB0000"+Clavesuma;
            System.out.println("Clave"+ClaveLibro);

            }
            if(CMNCont==2){
            System.out.println("estoy en 2");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveLibro ="DASLIB000"+Clavesuma;
            System.out.println("Clave"+ClaveLibro);
            }
            if(CMNCont==3){
            System.out.println("estoy en 3");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveLibro ="DASLIB00"+Clavesuma;
            System.out.println("Clave"+ClaveLibro);
            }
            if(CMNCont==4){
            System.out.println("estoy en 4");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveLibro ="DASLIB0"+Clavesuma;
            System.out.println("Clave"+ClaveLibro);

            }
            if(CMNCont==5){
            System.out.println("estoy en 5");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveLibro ="DASLIB"+Clavesuma;
            System.out.println("Clave"+ClaveLibro);

            }
        }    

        return ClaveLibro;
    }
       
    /*Registrar libros y generar claves*/
    
    public void registrarLibro(String titulo,String autor,String ano,String Editorial,int cantidad,String clasificacion){
        Conexion = c.getConexion();
        ClaveLibro = generarClaveLibro();

               
        L.claveLibro = ClaveLibro;
        L.tituloL = titulo;
        L.autorL = autor;
        L.añoL = ano;
        L.editorialL = Editorial;
        L.clasificacionL = clasificacion;
        L.existenciaL = cantidad;
        L.disponibilidadL = cantidad;
        
                          
        SQL="INSERT INTO libro(claveLibro,tituloL,autorL,añoL,editorialL,clasificacionL,existenciaL,disponibilidadL)VALUES(?,?,?,?,?,?,?,?)";

        
        try {
            
            Pstatement = Conexion.prepareStatement(SQL);
            Pstatement.setString(1, L.claveLibro);
            Pstatement.setString(2, L.tituloL);
            Pstatement.setString(3, L.autorL);
            Pstatement.setString(4, L.añoL);
            Pstatement.setString(5, L.editorialL);
            Pstatement.setString(6, L.clasificacionL);
            Pstatement.setInt(7, L.existenciaL);
            Pstatement.setInt(8, L.disponibilidadL);
            
            
            int validar = Pstatement.executeUpdate();
            registrarEjemplar(cantidad,ClaveLibro);
            
            if(validar>0){
                //JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   public void registrarEjemplar(int cantidad,String ClaveLibro){
        System.out.println("--METODO REGISTRAR EJEMPLAR--");
        Conexion = c.getConexion();
        int ejemplar = cantidad;
        
        int cont_numero=0,cont_total=0;
        String CMCortarNum=null;
        
        char[] arrayChar = ClaveLibro.toCharArray();
 
        for(int i=0; i<arrayChar.length; i++){
            cont_total++;
                if(isNumeric(arrayChar[i])){
                cont_numero++;}
        }
        if(cont_total==11){
            CMCortarNum=ClaveLibro.substring(6,11);
        }
        
        int CMNum = Integer.parseInt(CMCortarNum);
        
        String CMNumS = Integer.toString(CMNum);
        char[] arrayChar_pre_1 = CMNumS.toCharArray();
        int CMNCont=0;
        for(int i=0; i<arrayChar_pre_1.length; i++){
            CMNCont++;        
        }
        
        String ClaveE=null;
        for(int x=1; x<=ejemplar;x++){
        if(CMNCont==1){
        System.out.println("estoy en 1");

            if(x<10){
            ClaveE ="LIB"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="LIB"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==2){
        System.out.println("estoy en 2");

            if(x<10){
            ClaveE ="LIB"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="LIB"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==3){
        System.out.println("estoy en 3");

            if(x<10){
            ClaveE ="LIB"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="LIB"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==4){
            System.out.println("estoy en 4");
            if(x<10){
            ClaveE ="LIB"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="LIB"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==5){
        System.out.println("estoy en 5");

            if(x<10){
            ClaveE ="LIB"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="LIB"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
            System.out.println("CMCortarNum"+CMCortarNum);

        Estado="DISPONIBLE";
        
        EL.estadoL = Estado;
        EL.idEjemplarL = ClaveE;
        EL.libro_claveLibro = ClaveLibro;
        
        SQL="INSERT INTO ejemplarlibro(idEjemplarL,estadoL,libro_claveLibro)VALUES(?,?,?)";

        
        try {
            Pstatement = Conexion.prepareStatement(SQL);
            Pstatement.setString(1, EL.idEjemplarL);
            Pstatement.setString(2, EL.estadoL);
            Pstatement.setString(3, EL.libro_claveLibro);

            int validar = Pstatement.executeUpdate();
            
            if(validar>0){//JOptionPane.showMessageDialog(null, "Registro exitoso");
                
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlLibro.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        }
    }
    /*Consulta general de libros*/
    public JTable generarTablaEjemplar(){
         
        DTMLib.setRowCount(0);
        TbConsultaLib.setPreferredScrollableViewportSize(new Dimension(600, 250));
        RowSorter sorter = new TableRowSorter(DTMLib);
        TbConsultaLib.setRowSorter(sorter);
         try{
                Conexion= c.getConexion();
                statement= Conexion.createStatement();
                SQL = "SELECT claveLibro,idEjemplarL,tituloL,autorL,clasificacionL,editorialL,añoL,estadoL FROM libro INNER JOIN ejemplarlibro ON claveLibro=libro_claveLibro";
                resultset = statement.executeQuery(SQL);
                while(resultset.next()==true){
                
                String[] fila=new String[8];
                fila[0]=resultset.getString("claveLibro");
                fila[1]=resultset.getString("idEjemplarL");
                fila[2]=resultset.getString("tituloL");
                fila[3]=resultset.getString("autorL");
                fila[4]=resultset.getString("clasificacionL");
                fila[5]=resultset.getString("editorialL");  
                fila[6]=resultset.getString("añoL");
                fila[7]=resultset.getString("estadoL");
              
                DTMLib.addRow(fila);
              
            }
           
            
            
         }catch(SQLException e){
         
         }
         
        return TbConsultaLib;
   
    }
    
    /*Eliminar Ejemplar*/
    public void eliminarEjemplar(int fila){
        
        int Existencia=0;
        int Disponibilidad=0;
        String s="";
        
        try{
            Conexion = c.getConexion();
            statement=Conexion.createStatement();    
            SQL="SELECT * FROM libro WHERE claveLibro='"+TbConsultaLib.getValueAt(fila, 0)+"'";

            resultset = statement.executeQuery(SQL);

            while(resultset.next()==true){
                Existencia=resultset.getInt(7);
                Disponibilidad=resultset.getInt(8);
            }
        }catch(SQLException e){
        }
       

        
        if(TbConsultaLib.getValueAt(fila, 7).equals("DISPONIBLE")==true){
            
            int CantidadE = Existencia-1;
                int CantidadD = Disponibilidad-1;
                try{
                    SQL="UPDATE libro SET existenciaL='"+CantidadE+"',disponibilidadL='"+CantidadD+"' WHERE claveLibro='"+TbConsultaLib.getValueAt(fila, 0)+"'";

                    Pstatement = Conexion.prepareStatement(SQL);
                    Pstatement.executeUpdate();
                }catch(SQLException e){
                }

                try {
                    Conexion= c.getConexion();
                    statement= Conexion.createStatement();

                    SQL = "DELETE FROM ejemplarlibro WHERE idEjemplarL='"+TbConsultaLib.getValueAt(fila, 1)+"'";
                    String r= TbConsultaLib.getValueAt(fila, 1).toString();

                    int validar = statement.executeUpdate(SQL);
                    if(validar>0){

                        JOptionPane.showMessageDialog(null, "Se ha eliminado el ejemplar.");}
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(controlLibro.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "El ejemplar no puede ser eliminado,este sigue prestado.");      
                
        }
    }
    
    
    /*Llenar formulario de modificar tabla de ejemplares*/
    public void mostrarDatosEjemplar(int fila){
        String ClaveT=null;
        try
        {
        Conexion = c.getConexion();
        statement=Conexion.createStatement();    
        SQL="SELECT COUNT(libro_claveLibro) FROM ejemplarlibro WHERE libro_claveLibro='"+TbConsultaLib.getValueAt(fila, 0)+"'";
        
        resultset = statement.executeQuery(SQL);
        
        while(resultset.next()==true){
            System.out.println("----YA ENTRO----");
            ClaveT=resultset.getString(1);
        }
        }catch(SQLException e)
        {
        }
        
        
        System.out.println("----CLAVET----"+ClaveT);
        
            modificar = new interfazModificarLibro();
       
            Conexion= c.getConexion();

            modificar.txtClaveL.setText(TbConsultaLib.getValueAt(fila, 0).toString());
            modificar.txtTitulo.setText(TbConsultaLib.getValueAt(fila, 2).toString());
            modificar.txtAutor.setText(TbConsultaLib.getValueAt(fila, 3).toString());
            modificar.cbClasificacion.setSelectedItem(TbConsultaLib.getValueAt(fila, 4));
            modificar.txtEditorial.setText(TbConsultaLib.getValueAt(fila, 5).toString());
            modificar.txtAno.setText(TbConsultaLib.getValueAt(fila, 6).toString());
            modificar.txtCantidad.setText(ClaveT);
            modificar.txtCantidad2.setText(ClaveT);
            
             DTMRegLib.setRowCount(0);
             DTMLib.setRowCount(0);

    }
//    
//    /*Llenar formulario de modificar tabla de titulosdelibros*/
//    
    public void mostrarLibro(int fila){
        String ClaveT=null;
        try{
            Conexion = c.getConexion();
            statement=Conexion.createStatement();    
            SQL="SELECT COUNT(libro_claveLibro) FROM ejemplarlibro WHERE libro_claveLibro='"+TbConsulTitLib.getValueAt(fila, 0)+"'";

            resultset = statement.executeQuery(SQL);

            while(resultset.next()==true){
                System.out.println("----YA ENTRO----");
                ClaveT=resultset.getString(1);
            }
        }catch(SQLException e){
        }
        
        
        System.out.println("----CLAVET----"+ClaveT);
        
             modificar = new interfazModificarLibro();
       
            Conexion= c.getConexion();

            modificar.txtClaveL.setText(TbConsulTitLib.getValueAt(fila, 0).toString());
            modificar.txtTitulo.setText(TbConsulTitLib.getValueAt(fila, 1).toString());
            modificar.txtAutor.setText(TbConsulTitLib.getValueAt(fila, 2).toString());
            modificar.cbClasificacion.setSelectedItem(TbConsulTitLib.getValueAt(fila, 5));
            modificar.txtEditorial.setText(TbConsulTitLib.getValueAt(fila, 4).toString());
            modificar.txtAno.setText(TbConsulTitLib.getValueAt(fila, 3).toString());
            modificar.txtCantidad.setText(ClaveT);
            modificar.txtCantidad2.setText(ClaveT);
            

    }
   
    
    private static boolean isNumeric(char caracter){
             try {
             Integer.parseInt(String.valueOf(caracter));
             return true;
             } catch (NumberFormatException ex){
             return false;
             }
             }
        
                       
    /*Crear Tabla De Libros Ya existentes en Registrar Libros*/
        
    public JTable generarTablaLibro(){
         
        DTMRegLib.setRowCount(0);
        TbConsulTitLib.setPreferredScrollableViewportSize(new Dimension(600, 250));
        RowSorter sorter = new TableRowSorter(DTMRegLib);
        TbConsulTitLib.setRowSorter(sorter);
         try{
                Conexion= c.getConexion();
                statement= Conexion.createStatement();
                SQL = "SELECT * FROM libro";
                resultset = statement.executeQuery(SQL);
                while(resultset.next()==true){
                
                String[] Renglon = new String[8];
                Renglon[0]=resultset.getString("claveLibro");
                Renglon[1]=resultset.getString("tituloL");
                Renglon[2]=resultset.getString("autorL");
                Renglon[3]=resultset.getString("añoL");
                Renglon[4]=resultset.getString("editorialL");
                Renglon[5]=resultset.getString("clasificacionL");
                Renglon[6]=resultset.getString("existenciaL");
                Renglon[7]=resultset.getString("disponibilidadL");
              
                DTMRegLib.addRow(Renglon);
           
            }
           
           
            
         }catch(SQLException e){
         
         }
         
        return TbConsulTitLib;
   
    }   
        
        
     /*Filtros que estan en registrar libros*/
     
    public void filtrarDatosLibro(String txtTitulo,String txtAutor,String txtEditorial,String txtAno){
             DTMRegLib.setRowCount(0);
         if(txtTitulo.equalsIgnoreCase("")==true&&txtAutor.equalsIgnoreCase("")==true&&txtEditorial.equalsIgnoreCase("")==true&&txtAno.equalsIgnoreCase("")==true){
             DTMRegLib.setRowCount(0);
        
          }
         
         String bu="";
        int ban=0;
         try{
            libro L = new libro();
            Conexion= c.getConexion();
            statement= Conexion.createStatement();
            
            if(txtAutor.equalsIgnoreCase("")==false||txtTitulo.equalsIgnoreCase("")==false||txtEditorial.equalsIgnoreCase("")==false||txtAno.equalsIgnoreCase("")==false){
                bu=" where "; 
                
                
                DTMRegLib.setRowCount(0);
                
            
                L.tituloL = txtTitulo;
                L.autorL = txtAutor;
                L.editorialL = txtEditorial;
                L.añoL = txtAno;


            }
             
            if(txtTitulo.equalsIgnoreCase("")==false){
               
                 bu+= " tituloL like '"+L.tituloL+"%'";
                 ban=1;
                DTMRegLib.setRowCount(0);
            }
            
            if(txtAutor.equalsIgnoreCase("")==false){
                if(ban!=0)
                 bu+=" and ";
                 bu+= " autorL like '"+L.autorL+"%'";
                 ban=1;
                DTMRegLib.setRowCount(0);
            }
            
            if(txtEditorial.equalsIgnoreCase("")==false){
                if(ban!=0)
                 bu+=" and ";
                 bu+= " editorialL like '"+L.editorialL+"%'";
                 ban=1;
                DTMRegLib.setRowCount(0);
            }
            
            if(txtAno.equalsIgnoreCase("")==false){
                if(ban!=0)
                    
                 bu+=" and ";
                 bu+= " añoL like '"+L.añoL+"%'";
                 ban=1;
                DTMRegLib.setRowCount(0);
            }
            
            DTMRegLib.setRowCount(0);
            SQL = "SELECT * FROM libro "+bu;
             
            resultset = statement.executeQuery(SQL);
            int x=0;
            
            while(resultset.next()==true){   
                
                String[] Renglon = new String[8];
                Renglon[0]=resultset.getString("claveLibro");
                Renglon[1]=resultset.getString("tituloL");
                Renglon[2]=resultset.getString("autorL");
                Renglon[3]=resultset.getString("añoL");
                Renglon[4]=resultset.getString("editorialL");
                Renglon[5]=resultset.getString("clasificacionL");
                Renglon[6]=resultset.getString("existenciaL");
                Renglon[7]=resultset.getString("disponibilidadL");
              
                DTMRegLib.addRow(Renglon);               
               
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
    }
        
        
          
    /*Filtros de la tabla de ejemplares libro*/
    
    public void filtrarDatosEjemplar(String clav,String ejem,String titu,String auto,String clasi,String edit,String ano,String est){
             DTMLib.setRowCount(0);
             
         if(clav.equalsIgnoreCase("")==true&&ejem.equalsIgnoreCase("")==true&&titu.equalsIgnoreCase("")==true&&auto.equalsIgnoreCase("")==true&&clasi.equalsIgnoreCase("")==true&&edit.equalsIgnoreCase("")==true&&ano.equalsIgnoreCase("")==true&&est.equalsIgnoreCase("")==true){
             DTMLib.setRowCount(0);
             generarTablaEjemplar();
          }
         
        String bu="";
        int ban=0;
        
         try{
             Conexion= c.getConexion();
             statement= Conexion.createStatement();
            
             
            if(clav.equalsIgnoreCase("")==false||ejem.equalsIgnoreCase("")==false||titu.equalsIgnoreCase("")==false||auto.equalsIgnoreCase("")==false||clasi.equalsIgnoreCase("")==false||edit.equalsIgnoreCase("")==false||ano.equalsIgnoreCase("")==false||est.equalsIgnoreCase("")==false){
                bu=" where "; 
                DTMLib.setRowCount(0);
                 
            }
           
            L.claveLibro = clav;
            L.tituloL = titu;
            L.autorL = auto;
            L.clasificacionL = clasi;
            L.editorialL =edit;
            L.añoL = ano;
            EL.estadoL= est;
            EL.idEjemplarL = ejem;
            
            
            if(clav.equalsIgnoreCase("")==false){
               
             bu+= " claveLibro like '"+L.claveLibro+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }
            
            if(ejem.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " idEjemplarL like '"+EL.idEjemplarL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }
            
            if(titu.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " tituloL like '"+L.tituloL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }
            
            if(auto.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " autorL like '"+L.autorL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }


	    if(clasi.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " clasificacionL like '"+L.clasificacionL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }


	    if(edit.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " editorialL like '"+L.editorialL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }

	    if(ano.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " añoL like '"+L.añoL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }

	    if(est.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " estadoL like '"+EL.estadoL+"%'";
             ban=1;
            DTMLib.setRowCount(0);
            }
            DTMLib.setRowCount(0);
             SQL = "SELECT claveLibro,idEjemplarL,tituloL,autorL,clasificacionL,editorialL,añoL,estadoL FROM libro INNER JOIN ejemplarlibro ON claveLibro=libro_claveLibro"+bu;
             
             resultset = statement.executeQuery(SQL);
             
                while(resultset.next()==true){
                
                String[] fila=new String[8];
                fila[0]=resultset.getString("claveLibro");
                fila[1]=resultset.getString("idEjemplarL");
                fila[2]=resultset.getString("tituloL");
                fila[3]=resultset.getString("autorL");
                fila[4]=resultset.getString("clasificacionL");
                fila[5]=resultset.getString("editorialL");  
                fila[6]=resultset.getString("añoL");
                fila[7]=resultset.getString("estadoL");
              
                DTMLib.addRow(fila);
		}		
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
    }

     
     /*Consulta de Claves Generadas en el JTextArea*/
     
    public String mostrarClaveEjemplarLibro(){
         
         String Clave=null;
         
          try{
            Conexion = c.getConexion();
            statement=Conexion.createStatement();    
            SQL="SELECT MAX(ClaveLibro) FROM libro";

            resultset = statement.executeQuery(SQL);

            while(resultset.next()==true){
                Clave=resultset.getString(1);
            }
        }catch(SQLException e){
        }
         
          try{
        
            statement=Conexion.createStatement();    
            SQL="SELECT * FROM ejemplarlibro WHERE libro_claveLibro='"+Clave+"'";
            resultset = statement.executeQuery(SQL);

            TODO="";
            String[] Renglon = new String[3];
            while(resultset.next()==true){
                    Renglon[0]=resultset.getString("libro_claveLibro");
                    Renglon[1]=resultset.getString("idEjemplarL");

                    Renglon[2]="";
                    Renglon[2]= Renglon[2]+"\n" +Renglon[0]+" "+Renglon[1];                    
                    TODO = TODO+Renglon[2];
                }
      
         }catch(SQLException e){     }
           return TODO;
            
     }
    
    /*Modificar Informacion*/
    public void modificarLibro(int ExistCant, int NuevCant,String txtClaveL,String titulo,String autor,String ano,String editorial,String clasificacion){
       
        Conexion= c.getConexion();
        /*Estas Variables son para crear los nuevos Ejemplares*/
        String Clave=null;
        String CortarClaveT=null;
        String CortarNumMax=null;
        String STransNumMax = null;
        int ETransNumMax=0;
        String NuevoEjem=null;

        /*Estas variables son para generar la diferencia de cantidad VS la existencia*/
        int dispo= 0;
        int exist = 0;
        int diferencia = 0;

        int Totaldispo= 0;
        int TotalExis= 0;

        if(ExistCant>NuevCant){
            JOptionPane.showMessageDialog(null,"Cantidad no puede ser menor a lo existente: "+ExistCant+".\n"+"Si desea eliminar, regrese al menú."+"\n");   
        }else{
        if(ExistCant<NuevCant){
            
         /*Aqui busco la clave mayor ,para tomarla como base para los nuevos ejemplares*/  
        try{
            Conexion= c.getConexion();
            statement=Conexion.createStatement(); 
            SQL="SELECT MAX(idEjemplarL) FROM ejemplarlibro WHERE libro_claveLibro='"+txtClaveL+"'";
            resultset = statement.executeQuery(SQL);
        while(resultset.next()==true){
            Clave=resultset.getString(1);
        }
        }catch(SQLException e)
        {
        }
        
        /*Existencia y Disponibilidad*/
        String Existencia=null;
        String Disponibilidad=null;
        
        try{
            
            statement=Conexion.createStatement(); 
            SQL="SELECT existenciaL,disponibilidadL FROM libro WHERE claveLibro='"+txtClaveL+"'";
            resultset = statement.executeQuery(SQL);
        while(resultset.next()==true){
            Existencia=resultset.getString(1);
            Disponibilidad=resultset.getString(2);
         }
        }catch(SQLException e){
        }
        
         dispo= Integer.parseInt(Disponibilidad);
         exist =Integer.parseInt(Existencia);
         diferencia = NuevCant-exist;
        
         Totaldispo= dispo+diferencia;
         TotalExis= exist+diferencia;
        
        /*Aqui Corto y transformo , para poder crear el nuevo ejemplar*/
        if(Clave==null){
            String generar = txtClaveL.substring(3,11);
            Clave=generar+"-00";
        }
        CortarClaveT=Clave.substring(0,9);
        CortarNumMax=Clave.substring(9,11);

        ETransNumMax = Integer.parseInt(CortarNumMax);
        STransNumMax = Integer.toString(ETransNumMax);
 
            
            /*Con este ciclo se generan los nuevos Ejemplares*/
            for(int x=ETransNumMax+1;x<=NuevCant;x++){
                //System.out.println("El valor de x:"+x);
                
                if(x<10){                    
                    NuevoEjem=CortarClaveT+"0"+x;
                    System.out.println("Nuevo Ejemplar: "+NuevoEjem);
                }
                if(x>=10){                
                    NuevoEjem=CortarClaveT+x;
                    System.out.println("Nuevo Ejemplar: "+NuevoEjem);
                }
                
                String Estado="DISPONIBLE";
        
        
        /*Aqui se insertan los nuevos ejemplares a la Tabla ejemplarlibro*/
        
        try {
            SQL="INSERT INTO ejemplarlibro(idEjemplarL,estadoL,libro_claveLibro)VALUES(?,?,?)";
            
            Pstatement = Conexion.prepareStatement(SQL);
            Pstatement.setString(1, NuevoEjem);
            Pstatement.setString(2, Estado);
            Pstatement.setString(3, txtClaveL);

            int validar = Pstatement.executeUpdate();
            
            if(validar>0){
                //JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(libro.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        }    
                /*Aqui se hace la Modificacion*/
                
                try {
                SQL="UPDATE libro SET tituloL='"+titulo.toUpperCase()+"',autorL='"+autor.toUpperCase()+"',añoL='"+ano+"',editorialL='"+editorial.toUpperCase()+"',clasificacionL='"+clasificacion+"',existenciaL='"+TotalExis+"',disponibilidadL='"+Totaldispo+"' WHERE ClaveLibro='"+txtClaveL+"'";
                 Pstatement = Conexion.prepareStatement(SQL);
                 Pstatement.executeUpdate();

                int validar = Pstatement.executeUpdate(SQL);
                if(validar>0){
                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro.");
                    
                }
                 
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(libro.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                
         /*Se modifican los datos si estos se cambiaron*/   
                 
   }else{    
            try {
                       
                SQL="UPDATE libro SET tituloL='"+titulo.toUpperCase()+"',autorL='"+autor.toUpperCase()+"',añoL='"+ano+"',editorialL='"+editorial.toUpperCase()+"',ClasificacionL='"+clasificacion+"' WHERE ClaveLibro='"+txtClaveL+"'";


                 Pstatement = Conexion.prepareStatement(SQL);
                 Pstatement.executeUpdate();


                int validar = Pstatement.executeUpdate(SQL);
                if(validar>0){

                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro.");
                   
                }
                 
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(libro.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        }/*FIN DEL ELSE*/
        
        
    }
        DTMRegLib.setRowCount(0);
        DTMLib.setRowCount(0);
    }


}