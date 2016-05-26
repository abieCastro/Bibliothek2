/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoSolicitantes;

import principal.conexion;



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
public class controlSolicitante {
    alumno a = new alumno();
    docente d = new docente();
    interfazSolicitante is;
    interfazModAlumno ima;
    interfazModDocente imd;
    boolean respuesta;
    String Faltantes="";
    
    
    conexion c = new conexion();
    Connection Conexion;
    ResultSet resultset;
    Statement statement;
    ResultSetMetaData resultsetMD;
    PreparedStatement Pstatement;
    String SQL;
    
    JTable TbConsultaAlumno = new JTable();
    JTable TbConsDocente = new JTable();
    DefaultTableModel DTMAlumno = new DefaultTableModel();
    DefaultTableModel DTMDocente = new DefaultTableModel();
    
    public controlSolicitante(){
        /*Columnas de  tabla consultas de Alumnos*/
        DTMAlumno.addColumn("NoControl");
        DTMAlumno.addColumn("Nombre");
        DTMAlumno.addColumn("A.Paterno");
        DTMAlumno.addColumn("A.Materno");
        DTMAlumno.addColumn("Grado");
        DTMAlumno.addColumn("Grupo");
        DTMAlumno.addColumn("Telefono");
        DTMAlumno.addColumn("Celular");
        DTMAlumno.addColumn("Lim.Libro");
        DTMAlumno.addColumn("Lim.MV");
        DTMAlumno.addColumn("Libros Solicitados");
        DTMAlumno.addColumn("MV Solicitados");
        TbConsultaAlumno.setModel(DTMAlumno);
        
        /*Columnas de tabla consultas de Docentes*/
        DTMDocente.addColumn("NoControl");
        DTMDocente.addColumn("Nombre");
        DTMDocente.addColumn("A.Paterno");
        DTMDocente.addColumn("A.Materno");
        DTMDocente.addColumn("Lim.Libro");
        DTMDocente.addColumn("Lim. MV");
        DTMDocente.addColumn("Libros solicitados");
        DTMDocente.addColumn("MV solicitados");
        TbConsDocente.setModel(DTMDocente);
    }
    
    public boolean validarDatos(String nControl, String nombre, String paterno, String materno, String grado, String grupo){
        is = new interfazSolicitante();
        respuesta = false;
        Faltantes = "";

        if(nControl.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: No.Control."+"\n";
            respuesta = true;
        }

        if(nombre.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Nombre."+"\n";
            respuesta = true;
        }

        if(paterno.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Apellido Paterno."+"\n";
            respuesta = true;
        }

        if(materno.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Apellido Materno."+"\n";
            respuesta = true;
        }

        if(grado.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se seleccione: Grado."+"\n";
            respuesta = true;
        }

        if(grupo.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se seleccione: Grupo."+"\n";
            respuesta = true;
        }

        if(respuesta){
            System.out.println("RESPUESTA:"+Faltantes);
            JOptionPane.showMessageDialog(is.jpCatalogo,Faltantes);
        }

        return respuesta;
    }    
    
    public boolean validarLimire(String LLibro, String LMV){
    
        if(LLibro.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Limite de libro."+"\n";
            respuesta = true;
        }
        
        if(LMV.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Material visual."+"\n";
            respuesta = true;
        }
    
        if(respuesta){
            System.out.println("RESPUESTA:"+Faltantes);
            JOptionPane.showMessageDialog(is.jpCatalogo,Faltantes);
        }
    
    return respuesta;
    
    }
    
    
    
    public void registrarAlumno(String nControl, String nombre, String paterno, String materno, String grado, String grupo, String telefono, String celular){
        Conexion = c.getConexion();
        int MaxClaveA =0;
        
        try{
                statement=Conexion.createStatement();    
                statement.execute("SELECT MAX(claveAlumno) FROM alumno");
                resultset=statement.getResultSet();
                while(resultset.next()){
                    MaxClaveA=resultset.getInt(1);  
                }
            }catch(SQLException e){
            }
        
        a.claveAlumno = MaxClaveA+1;
        a.noControlA = "A-"+nControl;
        a.nombreA = nombre;
        a.apellidoPatA = paterno;
        a.apellidoMatA = materno;
        a.gradoA = grado;
        a.grupoA = grupo;
        a.telefonoA = telefono;
        a.celularA = celular;
        a.limiteLibroA = 1;
        a.limiteMatVisA = 1;
        a.librosSolicA = 0;
        a.matVisSolicA = 0;
        
        
        SQL="INSERT INTO alumno(claveAlumno,noControlA,nombreA,apellidoPatA,apellidoMatA,gradoA,grupoA,telefonoA,celularA,limiteLibroA,limiteMatVisA,librosSolicA,matVisSolicA)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("SQL"+SQL);
        try {
            
            Pstatement = Conexion.prepareStatement(SQL);
           
            Pstatement.setInt(1,a.claveAlumno);
            Pstatement.setString(2, a.noControlA);
            Pstatement.setString(3, a.nombreA);
            Pstatement.setString(4, a.apellidoPatA);
            Pstatement.setString(5, a.apellidoMatA);
            Pstatement.setString(6, a.gradoA);
            Pstatement.setString(7, a.grupoA);
            
            Pstatement.setString(8, a.telefonoA);
            Pstatement.setString(9, a.celularA);
            Pstatement.setInt(10, a.limiteLibroA);
            Pstatement.setInt(11, a.limiteMatVisA);
            Pstatement.setInt(12, a.librosSolicA);
            Pstatement.setInt(13, a.matVisSolicA);
            
            
            int validar = Pstatement.executeUpdate();
            
            
            if(validar>0){
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlSolicitante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void registrarDocente(String nControl, String nombre, String paterno, String materno){
        Conexion = c.getConexion();
        
        int MaxClaveD =0;
        
        try{
                statement=Conexion.createStatement();    
                statement.execute("SELECT MAX(claveDocente) FROM docente");
                resultset=statement.getResultSet();
                while(resultset.next()){
                    MaxClaveD=resultset.getInt(1);  
                }
            }catch(SQLException e){
            }
        System.out.println("maxia"+MaxClaveD);
        d.claveDocente = MaxClaveD+1;
        d.noControlD = "D-"+nControl;
        d.nombreD = nombre;
        d.apellidoPatD = paterno;
        d.apellidoMatD = materno;

        d.limiteLibroD = 1;
        d.limiteMatVisD = 1;
        d.librosSolicD = 0;
        d.matVisSolicD = 0;
        
        SQL="INSERT INTO docente(claveDocente,noControlD,nombreD,apellidoPatD,apellidoMatD,limiteLibroD,limiteMatVisD,librosSolicD,matVisSolicD)VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            
            Pstatement = Conexion.prepareStatement(SQL);
           
            Pstatement.setInt(1,d.claveDocente);
            Pstatement.setString(2, d.noControlD);
            Pstatement.setString(3, d.nombreD);
            Pstatement.setString(4, d.apellidoPatD);
            Pstatement.setString(5, d.apellidoMatD);

            Pstatement.setInt(6, d.limiteLibroD);
            Pstatement.setInt(7, d.limiteMatVisD);
            Pstatement.setInt(8, d.librosSolicD);
            Pstatement.setInt(9, d.matVisSolicD);
            
            
            int validar = Pstatement.executeUpdate();
            
            
            if(validar>0){
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlSolicitante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public JTable generarTablaAlumnos(){
        DTMAlumno.setRowCount(0);
       // TbConsultaAlumno.setPreferredScrollableViewportSize(new Dimension(600, 250));
        RowSorter sorter = new TableRowSorter(DTMAlumno);
        TbConsultaAlumno.setRowSorter(sorter);
        
        try{
                Conexion= c.getConexion();
                statement= Conexion.createStatement();
                SQL="SELECT * FROM alumno";

                resultset = statement.executeQuery(SQL);
                while(resultset.next()==true){
                
                String[] fila=new String[12];
                fila[0]=resultset.getString("noControlA");
                fila[1]=resultset.getString("nombreA");
                fila[2]=resultset.getString("apellidoPatA");
                fila[3]=resultset.getString("apellidoMatA");
                fila[4]=resultset.getString("gradoA");
                fila[5]=resultset.getString("grupoA");  
                fila[6]=resultset.getString("telefonoA");
                fila[7]=resultset.getString("celularA");
                fila[8]=resultset.getString("limiteLibroA");
                fila[9]=resultset.getString("limiteMatVisA");
                fila[10]=resultset.getString("librosSolicA");
                fila[11]=resultset.getString("matVisSolicA");
                
              
                DTMAlumno.addRow(fila);
              
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){}
        
        return TbConsultaAlumno;
    }

    public JTable generarTablaDocente(){
        DTMDocente.setRowCount(0);
       // TbConsultaAlumno.setPreferredScrollableViewportSize(new Dimension(600, 250));
        RowSorter sorter = new TableRowSorter(DTMDocente);
        TbConsDocente.setRowSorter(sorter);
        
        try{
                Conexion= c.getConexion();
                statement= Conexion.createStatement();
                SQL="SELECT * FROM docente";

                resultset = statement.executeQuery(SQL);
                while(resultset.next()==true){
                
                String[] fila=new String[8];
                fila[0]=resultset.getString("noControlD");
                fila[1]=resultset.getString("nombreD");
                fila[2]=resultset.getString("apellidoPatD");
                fila[3]=resultset.getString("apellidoMatD");
                fila[4]=resultset.getString("limiteLibroD");
                fila[5]=resultset.getString("limiteMatVisD");
                fila[6]=resultset.getString("librosSolicD");
                fila[7]=resultset.getString("matVisSolicD");
                
              
                DTMDocente.addRow(fila);
              
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){}
        
        return TbConsDocente;
    }
    
    public void filtrarDatosAlumno(String noControl,String nombre,String paterno,String materno,String grado, String grupo){
             
          DTMAlumno.setRowCount(0);
         if(noControl.equalsIgnoreCase("")==true&&nombre.equalsIgnoreCase("")==true&&paterno.equalsIgnoreCase("")==true&&materno.equalsIgnoreCase("")==true&&grupo.equalsIgnoreCase("")==true&&grado.equalsIgnoreCase("")==true){
             DTMAlumno.setRowCount(0);
        
          }
         
         String bu="";
        int ban=0;
         try{
            
            Conexion= c.getConexion();
            statement= Conexion.createStatement();
            
            if(noControl.equalsIgnoreCase("")==false||nombre.equalsIgnoreCase("")==false||paterno.equalsIgnoreCase("")==false||materno.equalsIgnoreCase("")==false||grupo.equalsIgnoreCase("")==false||grado.equalsIgnoreCase("")==false){
                
                bu="where"; 
                              
                DTMAlumno.setRowCount(0);
                           
                a.noControlA = noControl;
                a.nombreA = nombre;
                a.apellidoPatA = paterno;
                a.apellidoMatA = materno;
                a.grupoA = grupo;
                a.gradoA = grado;              
            }
             
            if(noControl.equalsIgnoreCase("")==false){
               
                 bu+= " noControlA like '"+a.noControlA+"%'";
                 ban=1;
                DTMAlumno.setRowCount(0);
            }
            
            if(nombre.equalsIgnoreCase("")==false){
                if(ban!=0)
                 bu+=" and ";
                 bu+= " nombreA like '"+a.nombreA+"%'";
                 ban=1;
                DTMAlumno.setRowCount(0);
            }
            
            if(paterno.equalsIgnoreCase("")==false){
                if(ban!=0)
                 bu+=" and ";
                 bu+= " apellidoPatA like '"+a.apellidoPatA+"%'";
                 ban=1;
                DTMAlumno.setRowCount(0);
            }
            
            if(materno.equalsIgnoreCase("")==false){
                if(ban!=0)
                    
                 bu+=" and ";
                 bu+= " apellidoMatA like '"+a.apellidoMatA+"%'";
                 ban=1;
                DTMAlumno.setRowCount(0);
            }
            
            if(grupo.equalsIgnoreCase("")==false){
                if(ban!=0)
                    
                 bu+=" and ";
                 bu+= " grupoA like '"+a.grupoA+"%'";
                 ban=1;
                DTMAlumno.setRowCount(0);
                
            }
            
            if(grado.equalsIgnoreCase("")==false){
                if(ban!=0)
                    
                 bu+=" and ";
                 bu+= " gradoA like '"+a.gradoA+"%'";
                 ban=1;
                DTMAlumno.setRowCount(0);
                
            }
              
            DTMAlumno.setRowCount(0);
            SQL = "SELECT * FROM alumno "+bu;
            
            resultset = statement.executeQuery(SQL);
            int x=0;
            
            while(resultset.next()==true){   
                
                String[] fila=new String[12];
                fila[0]=resultset.getString("noControlA");
                fila[1]=resultset.getString("nombreA");
                fila[2]=resultset.getString("apellidoPatA");
                fila[3]=resultset.getString("apellidoMatA");
                fila[4]=resultset.getString("gradoA");
                fila[5]=resultset.getString("grupoA"); 
                
                fila[6]=resultset.getString("telefonoA");
                fila[7]=resultset.getString("celularA");
                fila[8]=resultset.getString("limiteLibroA");
                fila[9]=resultset.getString("limiteMatVisA");
                fila[10]=resultset.getString("librosSolicA");
                fila[11]=resultset.getString("matVisSolicA");
                
              
                DTMAlumno.addRow(fila);   
              
               
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
    }

    public void filtrarDatosDocente(String noControl,String nombre,String paterno,String materno){
             
          DTMDocente.setRowCount(0);
         if(noControl.equalsIgnoreCase("")==true&&nombre.equalsIgnoreCase("")==true&&paterno.equalsIgnoreCase("")==true&&materno.equalsIgnoreCase("")==true){
             DTMDocente.setRowCount(0);
        
          }
         
        String bu="";
        int ban=0;
         try{
            
            Conexion= c.getConexion();
            statement= Conexion.createStatement();
            
            if(noControl.equalsIgnoreCase("")==false||nombre.equalsIgnoreCase("")==false||paterno.equalsIgnoreCase("")==false||materno.equalsIgnoreCase("")==false){
                
                bu="where"; 
                              
                DTMDocente.setRowCount(0);
                           
                d.noControlD = noControl;
                d.nombreD = nombre;
                d.apellidoPatD = paterno;
                d.apellidoMatD = materno;
                             
            }
             
            if(noControl.equalsIgnoreCase("")==false){
               
                 bu+= " noControlD like '"+d.noControlD+"%'";
                 ban=1;
                DTMDocente.setRowCount(0);
            }
            
            if(nombre.equalsIgnoreCase("")==false){
                if(ban!=0)
                 bu+=" and ";
                 bu+= " nombreD like '"+d.nombreD+"%'";
                 ban=1;
                DTMDocente.setRowCount(0);
            }
            
            if(paterno.equalsIgnoreCase("")==false){
                if(ban!=0)
                 bu+=" and ";
                 bu+= " apellidoPatD like '"+d.apellidoPatD+"%'";
                 ban=1;
                DTMDocente.setRowCount(0);
            }
            
            if(materno.equalsIgnoreCase("")==false){
                if(ban!=0)
                    
                 bu+=" and ";
                 bu+= " apellidoMatD like '"+d.apellidoMatD+"%'";
                 ban=1;
                DTMDocente.setRowCount(0);
            }
            
            
              
            DTMDocente.setRowCount(0);
            SQL = "SELECT * FROM docente "+bu;
            
            resultset = statement.executeQuery(SQL);
            int x=0;
            
            while(resultset.next()==true){   
                
                String[] fila=new String[8];
                fila[0]=resultset.getString("noControlD");
                fila[1]=resultset.getString("nombreD");
                fila[2]=resultset.getString("apellidoPatD");
                fila[3]=resultset.getString("apellidoMatD");
              
                fila[4]=resultset.getString("limiteLibroD");
                fila[5]=resultset.getString("limiteMatVisD");
                fila[6]=resultset.getString("librosSolicD");
                fila[7]=resultset.getString("matVisSolicD");
                
              
                DTMDocente.addRow(fila);   
              
               
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
    }

    /*Llena los campos de la interfaz modificar Alumno*/
    public void mostrarDatosAlumno(int fila){
        String noControl=null;
        try
        {
        Conexion = c.getConexion();
        statement=Conexion.createStatement();    
        SQL="SELECT COUNT(noControlA) FROM alumno WHERE noControlA='"+TbConsultaAlumno.getValueAt(fila, 0)+"'";
        
        resultset = statement.executeQuery(SQL);
        
        while(resultset.next()==true){
            System.out.println("----YA ENTRO----");
            noControl=resultset.getString(1);
        }
        }catch(SQLException e){}
        
        
        ima = new interfazModAlumno();
        
        ima.txtNoControlA.setText(TbConsultaAlumno.getValueAt(fila, 0).toString());
        ima.txtNombreA.setText(TbConsultaAlumno.getValueAt(fila, 1).toString());
        ima.txtApePaterA.setText(TbConsultaAlumno.getValueAt(fila, 2).toString());
        ima.txtApeMaterA.setText(TbConsultaAlumno.getValueAt(fila, 3).toString());
        ima.cbGrado.setSelectedItem(TbConsultaAlumno.getValueAt(fila, 4));
        ima.cbGrupo.setSelectedItem(TbConsultaAlumno.getValueAt(fila, 5));
        ima.txtCelular.setText(TbConsultaAlumno.getValueAt(fila, 7).toString());
        ima.txtTelefono.setText(TbConsultaAlumno.getValueAt(fila, 6).toString());
        ima.txtLLibros.setText(TbConsultaAlumno.getValueAt(fila,8).toString());
        ima.txtLMV.setText(TbConsultaAlumno.getValueAt(fila,9).toString());
        ima.txtLLibroAntes.setText(TbConsultaAlumno.getValueAt(fila,8).toString());
        ima.txtLMVAntes.setText(TbConsultaAlumno.getValueAt(fila,9).toString());
        ima.txtPL.setText(TbConsultaAlumno.getValueAt(fila,10).toString());
        ima.txtPMV.setText(TbConsultaAlumno.getValueAt(fila,11).toString());
        
        
    }

    
    public void mostrarDatosDocente(int fila){
        String noControl=null;
        try
        {
        Conexion = c.getConexion();
        statement=Conexion.createStatement();    
        SQL="SELECT COUNT(noControlD) FROM docente WHERE noControlD='"+TbConsDocente.getValueAt(fila, 0)+"'";
        
        resultset = statement.executeQuery(SQL);
        
        while(resultset.next()==true){
            System.out.println("----YA ENTRO----");
            noControl=resultset.getString(1);
        }
        }catch(SQLException e){}
        
        
        imd = new interfazModDocente();
        
        imd.txtNoControlD.setText(TbConsDocente.getValueAt(fila, 0).toString());
        imd.txtNombreD.setText(TbConsDocente.getValueAt(fila, 1).toString());
        imd.txtApePaterD.setText(TbConsDocente.getValueAt(fila, 2).toString());
        imd.txtApeMaterD.setText(TbConsDocente.getValueAt(fila, 3).toString());
        
        imd.txtLLibros.setText(TbConsDocente.getValueAt(fila,4).toString());
        imd.txtLMV.setText(TbConsDocente.getValueAt(fila,5).toString());
        imd.txtLLibroAntes.setText(TbConsDocente.getValueAt(fila,4).toString());
        imd.txtLMVAntes.setText(TbConsDocente.getValueAt(fila,5).toString());
        imd.txtPL.setText(TbConsDocente.getValueAt(fila,6).toString());
        imd.txtPMV.setText(TbConsDocente.getValueAt(fila,7).toString());
        
        
    }
    
    public void modificarAlumno(String nControl, String nombre, String paterno, String materno, String grado, String grupo, String telefono, String celular, String LLibro, String LMV,String LLibroAntes, String LMVAntes, String PL , String PMV){
        Conexion= c.getConexion();
        int antesL = Integer.parseInt(LLibroAntes);
        int despuesL = Integer.parseInt(LLibro);
        
        int antesMV = Integer.parseInt(LMVAntes);
        int despuesMV = Integer.parseInt(LMV);
        
        int prestamoL = Integer.parseInt(PL);
        int pretamoMV = Integer.parseInt(PMV);
        
        
       if((antesL==prestamoL && despuesL<antesL) || (antesMV==pretamoMV  && despuesMV<antesMV)){
       
           JOptionPane.showMessageDialog(null, "El solicitante tiene prestamos pendientes, no se puede reducir el limite.");
       
       }else{
       
           try {

            SQL="UPDATE alumno SET nombreA='"+nombre.toUpperCase()+"',apellidoPatA='"+paterno.toUpperCase()+"',apellidoMatA='"+materno+"',grupoA='"+grupo+"',gradoA='"+grado+"',telefonoA='"+telefono+"',celularA='"+celular+"',limiteLibroA='"+LLibro+"',limiteMatVisA='"+LMV+"' WHERE noControlA='"+nControl+"'";
           
            System.out.println(">"+SQL);
             Pstatement = Conexion.prepareStatement(SQL);
             Pstatement.executeUpdate();


            int validar = Pstatement.executeUpdate(SQL);
            if(validar>0){

                JOptionPane.showMessageDialog(null, "Se ha modificado el registro.");
            }
                 
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
       }
        
        
        
        
    }
    
    
    public void modificarDocente(String nControl, String nombre, String paterno, String materno, String LLibro, String LMV,String LLibroAntes, String LMVAntes, String PL , String PMV){
        Conexion= c.getConexion();
        int antesL = Integer.parseInt(LLibroAntes);
        int despuesL = Integer.parseInt(LLibro);
        
        int antesMV = Integer.parseInt(LMVAntes);
        int despuesMV = Integer.parseInt(LMV);
        
        int prestamoL = Integer.parseInt(PL);
        int pretamoMV = Integer.parseInt(PMV);
        
        
       if((antesL==prestamoL && despuesL<antesL) || (antesMV==pretamoMV  && despuesMV<antesMV)){
       
           JOptionPane.showMessageDialog(null, "El solicitante tiene prestamos pendientes, no se puede reducir el limite.");
       
       }else{
       
           try {

            SQL="UPDATE docente SET nombreD='"+nombre.toUpperCase()+"',apellidoPatD='"+paterno.toUpperCase()+"',apellidoMatD='"+materno+"',limiteLibroD='"+LLibro+"',limiteMatVisD='"+LMV+"' WHERE noControlD='"+nControl+"'";
           
            System.out.println(">"+SQL);
             Pstatement = Conexion.prepareStatement(SQL);
             Pstatement.executeUpdate();


            int validar = Pstatement.executeUpdate(SQL);
            if(validar>0){

                JOptionPane.showMessageDialog(null, "Se ha modificado el registro.");
            }
                 
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(docente.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
       }
        
        
        
        
    }
    
    
    
    public void eliminarAlumno(int fila){
        
        if(TbConsultaAlumno.getValueAt(fila, 10).toString().equalsIgnoreCase("0")==false || TbConsultaAlumno.getValueAt(fila, 11).toString().equalsIgnoreCase("0")==false){
        
            JOptionPane.showMessageDialog(null, "El solicitante tiene prestamos pendientes, no puede ser eliminado.");
       
        }else{
            try {
            Conexion= c.getConexion();
            statement= Conexion.createStatement();
            
            SQL = "DELETE FROM alumno WHERE noControlA='"+TbConsultaAlumno.getValueAt(fila, 0)+"'";
            String r= TbConsultaAlumno.getValueAt(fila, 0).toString();
            System.out.println("SQL"+SQL);
            int validar = statement.executeUpdate(SQL);
            if(validar>0){
                
                JOptionPane.showMessageDialog(null, "Se ha eliminado el Alumno.");}
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlSolicitante.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }
        
        
        
    }
    
    public void eliminarDocente(int fila){
        
        if(TbConsDocente.getValueAt(fila, 6).toString().equalsIgnoreCase("0")==false || TbConsDocente.getValueAt(fila, 7).toString().equalsIgnoreCase("0")==false){
        
            JOptionPane.showMessageDialog(null, "El solicitante tiene prestamos pendientes, no puede ser eliminado.");
       
        }else{
        
        try {
            Conexion= c.getConexion();
            statement= Conexion.createStatement();
            
            SQL = "DELETE FROM docente WHERE noControlD='"+TbConsDocente.getValueAt(fila, 0)+"'";
            String r= TbConsDocente.getValueAt(fila, 0).toString();
            System.out.println("SQL"+SQL);
            int validar = statement.executeUpdate(SQL);
            if(validar>0){
                
                JOptionPane.showMessageDialog(null, "Se ha eliminado el Docente.");}
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlSolicitante.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    
    
    
}