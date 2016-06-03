/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoMaterialVisual;

import mantenimientoMaterialVisual.ejempMatVisual;
import mantenimientoMaterialVisual.materialVisual;
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
public class controlMaterialVisual {
    
    ejempMatVisual EMV = new ejempMatVisual();
    materialVisual MV = new materialVisual();
    interfazMaterialVisual IMV;
    conexion c = new conexion();
    interfazModificarMaterialVisual MMV;
    
    Connection Conexion;
    ResultSet resultset;
    Statement statement;
    ResultSetMetaData resultsetMD;
    PreparedStatement Pstatement;
    
   
    public String SQL;
    
     DefaultTableModel DTMRegMV = new DefaultTableModel();
    JTable TbConsulTitMV = new JTable();
    
    String TODO="";
    
    DefaultTableModel DTMMV = new DefaultTableModel();
    JTable TbMV = new JTable();
    
    boolean resultado,respuesta;
    String Faltantes;
    String Estado;
    String ClaveMV="";
    
    public controlMaterialVisual(){
      
         /*Columnas de Tabla de Consultas ejemplares*/
        DTMMV.addColumn("Clave Mat.Vis.");
        DTMMV.addColumn("Ejemplar");
        DTMMV.addColumn("Título");
        DTMMV.addColumn("Volumen");
        DTMMV.addColumn("Clasificación");
       
        DTMMV.addColumn("Año");
        DTMMV.addColumn("Estado");
        TbMV.setModel(DTMMV);
        
        /*Columnas de Tabla de Registros MV */
        
        DTMRegMV.addColumn("Clave Mat.Vis.");
        DTMRegMV.addColumn("Título");
        DTMRegMV.addColumn("Volumen");
        DTMRegMV.addColumn("Año");
         
        DTMRegMV.addColumn("Clasificación");
        DTMRegMV.addColumn("Excistencia");
        DTMRegMV.addColumn("Disponibles");
        TbConsulTitMV.setModel(DTMRegMV);
    }
    
    public boolean verificarBD(){
        resultado = true;
        /*Checar getConexion antes c.conexion();*/
        Conexion = c.getConexion();
        try
        {
        
        statement=Conexion.createStatement();    
        statement.execute("SELECT * FROM materialvisual");
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
    
    public boolean validarDatos(String titulo,String volumen,String ano,String cantidad,String clasificacion){
        IMV = new interfazMaterialVisual();
        respuesta = false;
        Faltantes = "";
        
        
        if(titulo.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Título."+"\n";
            respuesta = true;
        }
        if(volumen.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Volumen."+"\n";
            respuesta = true;
        }
        if(ano.equalsIgnoreCase("")==true){
            Faltantes = Faltantes + "Se requiere que se ingrese: Año."+"\n";
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
            System.out.println("RESPUESTA:"+Faltantes);
            JOptionPane.showMessageDialog(IMV.jpRegMV,Faltantes);
        }
        return respuesta;
    }
    
    public String generarClaveMV(){
        Conexion = c.getConexion();
  
        String ClaveMax=null;
        String ClaveMV=null;
        String b="";
        
        if(resultado){
            ClaveMV = "DASMV00001";
        }else{     
            try{
                statement=Conexion.createStatement();    
                statement.execute("SELECT MAX(claveMatVis) FROM materialvisual");
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
            if(cont_total==10){
                System.out.println("maximo"+ClaveMax);
                CMCortarNum=ClaveMax.substring(5,10);
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
            ClaveMV ="DASMV0000"+Clavesuma;
            System.out.println("Clave"+ClaveMV);

            }
            if(CMNCont==2){
            System.out.println("estoy en 2");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveMV ="DASMV000"+Clavesuma;
            System.out.println("Clave"+ClaveMV);
            }
            if(CMNCont==3){
            System.out.println("estoy en 3");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveMV ="DASMV00"+Clavesuma;
            System.out.println("Clave"+ClaveMV);
            }
            if(CMNCont==4){
            System.out.println("estoy en 4");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveMV ="DASMV0"+Clavesuma;
            System.out.println("Clave"+ClaveMV);

            }
            if(CMNCont==5){
            System.out.println("estoy en 5");
            System.out.println("entero:"+CMNum);

            Clavesuma = CMNum+1;
            System.out.println("suma"+Clavesuma);
            ClaveMV ="DASMV"+Clavesuma;
            System.out.println("Clave"+ClaveMV);

            }
        }    

        return ClaveMV;
    }
     
    public void registrarEjemplar(int cantidad,String ClaveMV){
        System.out.println("--METODO REGISTRAR EJEMPLAR--");
        Conexion = c.getConexion();
        int ejemplar = cantidad;
        
        int cont_numero=0,cont_total=0;
        String CMCortarNum=null;
        
        char[] arrayChar = ClaveMV.toCharArray();
 
        for(int i=0; i<arrayChar.length; i++){
            cont_total++;
                if(isNumeric(arrayChar[i])){
                cont_numero++;}
        }
        if(cont_total==10){
            CMCortarNum=ClaveMV.substring(5,10);
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
            ClaveE ="MV"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="MV"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==2){
        System.out.println("estoy en 2");

            if(x<10){
            ClaveE ="MV"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="MV"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==3){
        System.out.println("estoy en 3");

            if(x<10){
            ClaveE ="MV"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="MV"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==4){
            System.out.println("estoy en 4");
            if(x<10){
            ClaveE ="MV"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="MV"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        
        if(CMNCont==5){
        System.out.println("estoy en 5");

            if(x<10){
            ClaveE ="MV"+CMCortarNum+"-0"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
            if(x>=10){
            ClaveE ="MV"+CMCortarNum+"-"+x;
            System.out.println("ClaveEjem:"+ClaveE);
            }
        }
        

        Estado="DISPONIBLE";
        
        EMV.estadoM = Estado;
        EMV.idEjemplarM = ClaveE;
        EMV.materialvisual_claveMatVis = ClaveMV;
            System.out.println(">esto es CLAVEMC"+ClaveMV);
            System.out.println(">esto es idEjemplarM"+ClaveE);
            
        SQL="INSERT INTO ejempmatvisual(idEjemplarM,estadoM,materialvisual_claveMatVis)VALUES(?,?,?)";

        
        try {
            Pstatement = Conexion.prepareStatement(SQL);
            Pstatement.setString(1, EMV.idEjemplarM);
            Pstatement.setString(2, EMV.estadoM);
            Pstatement.setString(3, EMV.materialvisual_claveMatVis);

            int validar = Pstatement.executeUpdate();
            
            if(validar>0){//JOptionPane.showMessageDialog(null, "Registro exitoso");
                
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlMaterialVisual.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        }
    }
    
    /*Registrar Material VIsual*/
    public void registrarMV(String titulo,String volumen,String ano,int cantidad,String clasificacion){
      Conexion = c.getConexion();
      ClaveMV = generarClaveMV();

               
        MV.claveMatVis = ClaveMV;
        MV.tituloM = titulo;
        MV.volumenM = volumen;
        MV.añoM = ano;

        MV.clasificacionM = clasificacion;
        MV.existenciaM = cantidad;
        MV.disponibilidadM = cantidad;
        
                          
        SQL="INSERT INTO materialvisual(claveMatVis,tituloM,volumenM,añoM,clasificacionM,existenciaM,disponibilidadM)VALUES(?,?,?,?,?,?,?)";

        
        try {
            
            Pstatement = Conexion.prepareStatement(SQL);
            Pstatement.setString(1, MV.claveMatVis);
            Pstatement.setString(2, MV.tituloM);
            Pstatement.setString(3, MV.volumenM);
            Pstatement.setString(4, MV.añoM);
            Pstatement.setString(5, MV.clasificacionM);
            Pstatement.setInt(6, MV.existenciaM);
            Pstatement.setInt(7, MV.disponibilidadM);
            
            
            int validar = Pstatement.executeUpdate();
            registrarEjemplar(cantidad,ClaveMV);
            
            if(validar>0){
                //JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(controlMaterialVisual.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
    
    /*Crear tabla MV de ejemplares*/
    public JTable generarTablaEjemplarMV(){
         
        DTMMV.setRowCount(0);
        TbMV.setPreferredScrollableViewportSize(new Dimension(600, 250));
        RowSorter sorter = new TableRowSorter(DTMMV);
        TbMV.setRowSorter(sorter);
         try{
                Conexion= c.getConexion();
                statement= Conexion.createStatement();
                SQL = "SELECT claveMatVis,idEjemplarM,tituloM,volumenM,clasificacionM,añoM,estadoM FROM materialvisual INNER JOIN ejempmatvisual ON claveMatVis=materialvisual_claveMatVis";
                resultset = statement.executeQuery(SQL);
                while(resultset.next()==true){
                
                String[] fila=new String[7];
                fila[0]=resultset.getString("claveMatVis");
                fila[1]=resultset.getString("idEjemplarM");
                fila[2]=resultset.getString("tituloM");
                fila[3]=resultset.getString("volumenM");
                fila[4]=resultset.getString("clasificacionM");
                  
                fila[5]=resultset.getString("añoM");
                fila[6]=resultset.getString("estadoM");
              
                DTMMV.addRow(fila);
           
                
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
         
        return TbMV;
   
    }
     
     /*Metod de numeros*/
    private static boolean isNumeric(char caracter){
             try {
             Integer.parseInt(String.valueOf(caracter));
             return true;
             } catch (NumberFormatException ex){
             return false;
             }
             }
      
    
    /*Mostrar tabla de titulos de MV*/
    public JTable generarTablaMV(){
         
        DTMRegMV.setRowCount(0);
        TbConsulTitMV.setPreferredScrollableViewportSize(new Dimension(600, 250));
        RowSorter sorter = new TableRowSorter(DTMRegMV);
        TbConsulTitMV.setRowSorter(sorter);
         try{
                Conexion= c.getConexion();
                statement= Conexion.createStatement();
                SQL = "SELECT * FROM materialvisual";
                resultset = statement.executeQuery(SQL);
                while(resultset.next()==true){
                
                String[] Renglon = new String[7];
                Renglon[0]=resultset.getString("claveMatVis");
                Renglon[1]=resultset.getString("tituloM");
                Renglon[2]=resultset.getString("volumenM");
                Renglon[3]=resultset.getString("añoM");
                Renglon[4]=resultset.getString("clasificacionM");
                Renglon[5]=resultset.getString("existenciaM");
                Renglon[6]=resultset.getString("disponibilidadM");
              
                DTMRegMV .addRow(Renglon);
           
                
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
         
        return TbConsulTitMV;
   
    }   
    
    /*Mostrar Clave de MV*/
    public String mostrarClaveMV(){
         
         String Clave=null;
         
          try
        {
        Conexion = c.getConexion();
        statement=Conexion.createStatement();    
        SQL="SELECT MAX(ClaveMatVis) FROM materialvisual";
        
        resultset = statement.executeQuery(SQL);
        
        while(resultset.next()==true){
            Clave=resultset.getString(1);
        }
        }catch(SQLException e)
        {
        }

         
         
         try
        {
        
        statement=Conexion.createStatement();    
        SQL="SELECT * FROM ejempmatvisual WHERE materialvisual_claveMatVis='"+Clave+"'";
        resultset = statement.executeQuery(SQL);
          
        TODO="";
        String[] Renglon = new String[3];
        while(resultset.next()==true){
                
                
                Renglon[0]=resultset.getString("materialvisual_ClaveMatVis");
                Renglon[1]=resultset.getString("idEjemplarM");

                Renglon[2]="";
                System.out.println("CLAVE"+Renglon[0]);
                System.out.println("ID"+Renglon[1]);
                Renglon[2]= Renglon[2]+"\n" +Renglon[0]+" "+Renglon[1];
                System.out.println("Renglon 2:"+Renglon[2]);
                
                TODO = TODO+Renglon[2];
            
               System.out.println("TODO ELDONTENIDO"+TODO);
            }
            
              
         }catch(SQLException e){
        
        
    }
           return TODO;
    
     }
    
    /*Filtros que estan en registrar material visual*/
     
    public void filtrarDatosMV(String txtTitulo,String txtVolumen,String txtAno){
             DTMRegMV.setRowCount(0);
         if(txtTitulo.equalsIgnoreCase("")==true&&txtVolumen.equalsIgnoreCase("")==true&&txtAno.equalsIgnoreCase("")==true){
            DTMRegMV.setRowCount(0);
             generarTablaMV();
          }
         String bu="";
        int ban=0;
         try{
            Conexion= c.getConexion();
            statement= Conexion.createStatement();
            
            if(txtVolumen.equalsIgnoreCase("")==false||txtTitulo.equalsIgnoreCase("")==false||txtAno.equalsIgnoreCase("")==false){
                bu=" where "; 
                
        
                DTMRegMV.setRowCount(0);
            }
            
            MV.tituloM = txtTitulo;
            MV.volumenM = txtVolumen;
            MV.añoM = txtAno;
            if(txtTitulo.equalsIgnoreCase("")==false){
               
             bu+= " tituloM like '"+MV.tituloM+"%'";
             ban=1;
             DTMRegMV.setRowCount(0);
            
            }
            
            if(txtVolumen.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " volumenM like '"+MV.volumenM+"%'";
             ban=1;
            DTMRegMV.setRowCount(0);
            }
            
            
            
            if(txtAno.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " añoM like '"+MV.añoM+"%'";
             ban=1;
             DTMRegMV.setRowCount(0);
            
            }
            DTMRegMV.setRowCount(0);
            SQL = "SELECT * FROM materialvisual "+bu;
          
            resultset = statement.executeQuery(SQL);
            int x=0;
            
            while(resultset.next()==true){   
                
                String[] Renglon = new String[7];
                Renglon[0]=resultset.getString("ClaveMatVis");
                Renglon[1]=resultset.getString("tituloM");
                Renglon[2]=resultset.getString("volumenM");
                Renglon[3]=resultset.getString("añoM");
                
                Renglon[4]=resultset.getString("clasificacionM");
                Renglon[5]=resultset.getString("existenciaM");
                Renglon[6]=resultset.getString("disponibilidadM");
              
                DTMRegMV.addRow(Renglon);
                
               
            }
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
    }
        
      
      /*Filtro MV ejemplares*/
    public void filtrarDatosEjemplar(String clav,String ejem,String titu,String volumen,String clasi,String ano,String est){
             DTMMV.setRowCount(0);
             
         if(clav.equalsIgnoreCase("")==true&&ejem.equalsIgnoreCase("")==true&&titu.equalsIgnoreCase("")==true&&volumen.equalsIgnoreCase("")==true&&clasi.equalsIgnoreCase("")==true&&ano.equalsIgnoreCase("")==true&&est.equalsIgnoreCase("")==true){
             DTMMV.setRowCount(0);
             
          }
         
        String bu="";
        int ban=0;
        
         try{
             Conexion= c.getConexion();
             statement= Conexion.createStatement();
             
             
            if(clav.equalsIgnoreCase("")==false||ejem.equalsIgnoreCase("")==false||titu.equalsIgnoreCase("")==false||volumen.equalsIgnoreCase("")==false||clasi.equalsIgnoreCase("")==false||ano.equalsIgnoreCase("")==false||est.equalsIgnoreCase("")==false){
                bu=" where "; 
                DTMMV.setRowCount(0);
                
            }
           
            
            MV.claveMatVis = clav;
            MV.tituloM = titu;
            MV.volumenM = volumen;
            MV.clasificacionM = clasi;
            MV.añoM = ano;
            
            EMV.idEjemplarM = ejem;
            EMV.estadoM = est;
            
            if(clav.equalsIgnoreCase("")==false){
               
             bu+= " ClaveMatVis like '"+MV.claveMatVis+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }
            
            if(ejem.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " idEjemplarM like '"+EMV.idEjemplarM+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }
            
            if(titu.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " tituloM like '"+MV.tituloM+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }
            
            if(volumen.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " volumenM like '"+MV.volumenM+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }


	    if(clasi.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " clasificacionM like '"+MV.clasificacionM+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }

	    if(ano.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " añoM like '"+MV.añoM+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }

	    if(est.equalsIgnoreCase("")==false){
                if(ban!=0)
             bu+=" and ";
             bu+= " estadoM like '"+EMV.estadoM+"%'";
             ban=1;
            DTMMV.setRowCount(0);
            }
            DTMMV.setRowCount(0);
             SQL = "SELECT claveMatVis,idEjemplarM,tituloM,volumenM,clasificacionM,añoM,estadoM FROM materialvisual INNER JOIN ejempmatvisual ON claveMatVis=materialvisual_claveMatVis"+bu;
            
             resultset = statement.executeQuery(SQL);
             
                while(resultset.next()==true){
                
                String[] fila=new String[7];
                fila[0]=resultset.getString("claveMatVis");
                fila[1]=resultset.getString("idEjemplarM");
                fila[2]=resultset.getString("tituloM");
                fila[3]=resultset.getString("volumenM");
                fila[4]=resultset.getString("clasificacionM");
                  
                fila[5]=resultset.getString("añoM");
                fila[6]=resultset.getString("estadoM");
              
                DTMMV.addRow(fila);
		}		
           
            resultset.close();
            Conexion.close();
            
         }catch(SQLException e){
         
         }
    }
    
    /*llenar formulario en modificarMV titulos*/
    
    public void mostrarMV (int fila){
        String ClaveT=null;
        try
        {
        Conexion = c.getConexion();
        statement=Conexion.createStatement();    
        SQL="SELECT COUNT(materialvisual_ClaveMatVis) FROM ejempmatvisual WHERE materialvisual_ClaveMatVis='"+TbConsulTitMV.getValueAt(fila, 0)+"'";
        
        resultset = statement.executeQuery(SQL);
        
        while(resultset.next()==true){
            ClaveT=resultset.getString(1);
        }
        }catch(SQLException e)
        {
        }
        
        
            MMV = new interfazModificarMaterialVisual();
       
            Conexion= c.getConexion();

            MMV.txtClaveL.setText(TbConsulTitMV.getValueAt(fila, 0).toString());
            MMV.txtTitulo.setText(TbConsulTitMV.getValueAt(fila, 1).toString());
            MMV.txtVolumen.setText(TbConsulTitMV.getValueAt(fila, 2).toString());
            MMV.cbClasificacion.setSelectedItem(TbConsulTitMV.getValueAt(fila, 4));
            
            MMV.txtAno.setText(TbConsulTitMV.getValueAt(fila, 3).toString());
            MMV.txtCantidad.setText(ClaveT);
            MMV.txtCantidad2.setText(ClaveT);
            

    }
    
    /*llenar formulario en modificar MV ejemplares*/
      
    public void mostrarEjem (int fila){
        String ClaveT=null;
        try
        {
        Conexion = c.getConexion();
        statement=Conexion.createStatement();    
        SQL="SELECT COUNT(materialvisual_ClaveMatVis) FROM ejempmatvisual WHERE materialvisual_ClaveMatVis='"+TbMV.getValueAt(fila, 0)+"'";
        
        resultset = statement.executeQuery(SQL);
        
        while(resultset.next()==true){
            ClaveT=resultset.getString(1);
        }
        }catch(SQLException e)
        {
        }
        
        
        System.out.println("----CLAVET----"+ClaveT);
        
            MMV = new interfazModificarMaterialVisual();
       
            Conexion= c.getConexion();

            MMV.txtClaveL.setText(TbMV.getValueAt(fila, 0).toString());
            MMV.txtTitulo.setText(TbMV.getValueAt(fila, 2).toString());
            MMV.txtVolumen.setText(TbMV.getValueAt(fila, 3).toString());
            MMV.cbClasificacion.setSelectedItem(TbMV.getValueAt(fila, 4));
            
            MMV.txtAno.setText(TbMV.getValueAt(fila, 5).toString());
            MMV.txtCantidad.setText(ClaveT);
            MMV.txtCantidad2.setText(ClaveT);
            

    }
      
    /*Eliminar Ejemplar*/
    public void eliminarEjemplar(int fila){
        
        int Existencia=0;
        int Disponibilidad=0;
        
        try{
            Conexion = c.getConexion();
            statement=Conexion.createStatement();    
            SQL="SELECT * FROM materialvisual WHERE claveMatVis='"+TbMV.getValueAt(fila, 0)+"'";

            resultset = statement.executeQuery(SQL);

            while(resultset.next()==true){
                Existencia=resultset.getInt(6);
                Disponibilidad=resultset.getInt(7);
            }
        }catch(SQLException e){
        }
        
       
        
        if(TbMV.getValueAt(fila, 6).equals("DISPONIBLE")==true){

            int CantidadE = Existencia-1;
            int CantidadD = Disponibilidad-1;
            try{
                SQL="UPDATE materialvisual SET existenciaM='"+CantidadE+"',disponibilidadM='"+CantidadD+"' WHERE claveMatVis='"+TbMV.getValueAt(fila, 0)+"'";

                Pstatement = Conexion.prepareStatement(SQL);
                Pstatement.executeUpdate();
            }catch(SQLException e){
            }

            try {
                Conexion= c.getConexion();
                statement= Conexion.createStatement();

                SQL = "DELETE FROM ejempmatvisual WHERE idEjemplarM='"+TbMV.getValueAt(fila, 1)+"'";
                String r= TbMV.getValueAt(fila, 1).toString();

                int validar = statement.executeUpdate(SQL);
                if(validar>0){

                    JOptionPane.showMessageDialog(null, "Se ha eliminado el ejemplar.");}
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(controlMaterialVisual.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else{
            
            JOptionPane.showMessageDialog(null, "El ejemplar no puede ser eliminado,este sigue prestado.");      
                
        }
    }
    
    /*Modificar Informacion*/
    public void modificarMV(int ExistCant, int NuevCant,String txtClaveMV,String titulo,String volumen,String ano,String clasificacion){
        
        Conexion= c.getConexion();
        /*Estas Variables son para crear los nuevos Ejemplares*/
        String Clave="";
        
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
            SQL="SELECT MAX(idEjemplarM) FROM ejempmatvisual WHERE materialvisual_claveMatVis='"+txtClaveMV+"'";
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
            SQL="SELECT existenciaM,disponibilidadM FROM materialvisual WHERE claveMatVis='"+txtClaveMV+"'";
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
            String generar = txtClaveMV.substring(3,10);
            Clave=generar+"-00";
            
        }
            CortarClaveT=Clave.substring(0,8);
            CortarNumMax=Clave.substring(8,10);
                
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
            SQL="INSERT INTO ejempmatvisual(idEjemplarM,estadoM,materialvisual_claveMatVis)VALUES(?,?,?)";
            
            Pstatement = Conexion.prepareStatement(SQL);
            Pstatement.setString(1, NuevoEjem);
            Pstatement.setString(2, Estado);
            Pstatement.setString(3, txtClaveMV);

            int validar = Pstatement.executeUpdate();
            
            if(validar>0){
                //JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(materialVisual.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        }    
                /*Aqui se hace la Modificacion*/
                
                try {
                SQL="UPDATE materialvisual SET tituloM='"+titulo.toUpperCase()+"',volumenM='"+volumen.toUpperCase()+"',añoM='"+ano+"',clasificacionM='"+clasificacion+"',existenciaM='"+TotalExis+"',disponibilidadM='"+Totaldispo+"' WHERE claveMatVis='"+txtClaveMV+"'";
                 Pstatement = Conexion.prepareStatement(SQL);
                 Pstatement.executeUpdate();

                int validar = Pstatement.executeUpdate(SQL);
                if(validar>0){
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                }
                 
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(materialVisual.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                
         /*Se modifican los datos si estos se cambiaron*/   
                 
   }else{    
            try {
                       
                SQL="UPDATE materialvisual SET tituloM='"+titulo.toUpperCase()+"',volumenM='"+volumen.toUpperCase()+"',añoM='"+ano+"',ClasificacionM='"+clasificacion+"' WHERE claveMatVis='"+txtClaveMV+"'";


                 Pstatement = Conexion.prepareStatement(SQL);
                 Pstatement.executeUpdate();


                int validar = Pstatement.executeUpdate(SQL);
                if(validar>0){

                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro.");}
                 
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(materialVisual.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        }/*FIN DEL ELSE*/
        
        
    }
        
        DTMRegMV.setRowCount(0);
        DTMMV.setRowCount(0);
    }

}
