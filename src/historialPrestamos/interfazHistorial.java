/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historialPrestamos;

import com.qt.datapicker.DatePicker;
import java.awt.Color;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Mac
 */
public class interfazHistorial extends JFrame {
    controlHistorial ch = new controlHistorial(this);
    JPanel jpPrincHist;
    
    ButtonGroup grupoRbTipoHist;
    JRadioButton rbHistGeneral;
    JRadioButton rbHistLibros; 
    JRadioButton rbHistMatVis; 
    
    //General   
    JPanel jpTablaHistGral;
    JTable tbHistGeneral;
    JScrollPane spHistGeneral;
    
    //Libros
    JPanel jpTablaHistLib;
    JTable tbHistLibros;
    JScrollPane spHistLibros;
    
    //Materiales Visuales
    JPanel jpTablaHistMatVis;
    JTable tbHistMatVis;
    JScrollPane spHistMatVis;
    
    public JPanel jpPrincHist() {  
        jpPrincHist = new JPanel();
        jpPrincHist.setLayout(null);
        jpPrincHist.setBounds(10, 50, 1340, 650);
        jpPrincHist.setBackground(Color.YELLOW);
        jpPrincHist.setVisible(true);
        
        //General
        jpTablaHistGral = new JPanel();
        jpTablaHistGral.setLayout(null);
        jpTablaHistGral.setBounds(20, 100, 1300, 500);
        jpPrincHist.add(jpTablaHistGral);
        jpTablaHistGral.setVisible(false);        
        
        tbHistGeneral = new JTable() ;               
        tbHistGeneral.setBounds(20, 100, 1400, 500);         
        spHistGeneral = new JScrollPane(tbHistGeneral);
        spHistGeneral.setBounds(0, 0, 1300, 500);
        jpTablaHistGral.add(spHistGeneral);
        spHistGeneral.setVisible(true);
        ch.listaHistGral(tbHistGeneral);
        
        //Libros
        jpTablaHistLib = new JPanel();
        jpTablaHistLib.setLayout(null);
        jpTablaHistLib.setBounds(10, 100, 620, 100);
        jpPrincHist.add(jpTablaHistLib);
        jpTablaHistLib.setVisible(false);
        
        tbHistLibros = new JTable();
        tbHistLibros.setBounds(10, 100, 720, 100);
        spHistLibros = new JScrollPane(tbHistLibros);
        spHistLibros.setBounds(0, 0, 620, 100);
        jpTablaHistLib.add(spHistLibros);
        spHistLibros.setVisible(true);        
        
        //Materiales Visuales
        jpTablaHistMatVis = new JPanel();
        jpTablaHistMatVis.setLayout(null);
        jpTablaHistMatVis.setBounds(10, 100, 620, 100);
        jpPrincHist.add(jpTablaHistMatVis);
        jpTablaHistMatVis.setVisible(false);
        
        tbHistMatVis = new JTable();
        tbHistMatVis.setBounds(10, 100, 720, 100);
        spHistMatVis = new JScrollPane(tbHistMatVis);
        spHistMatVis.setBounds(0, 0, 620, 100);
        jpTablaHistMatVis.add(spHistMatVis);
        spHistMatVis.setVisible(true);        
        
        grupoRbTipoHist = new ButtonGroup();
        rbHistGeneral = new JRadioButton("General");
        rbHistGeneral.setBounds(300, 20, 100, 30);
        rbHistGeneral.setVisible(true);
        jpPrincHist.add(rbHistGeneral);
        grupoRbTipoHist.add(rbHistGeneral);        
        
        rbHistLibros = new JRadioButton("Libros");
        rbHistLibros.setBounds(500, 20, 120, 30);
        rbHistLibros.setVisible(true);   
        jpPrincHist.add(rbHistLibros);
        grupoRbTipoHist.add(rbHistLibros);
        
        rbHistMatVis = new JRadioButton("Materiales Visuales");
        rbHistMatVis.setBounds(700, 20, 140, 30);        
        rbHistMatVis.setVisible(true);   
        jpPrincHist.add(rbHistMatVis);
        grupoRbTipoHist.add(rbHistMatVis);
        
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

        jpPrincHist.add(datePicker);
        
        
        rbHistGeneral.addActionListener(ch);
        rbHistLibros.addActionListener(ch);
        rbHistMatVis.addActionListener(ch);
        
        return jpPrincHist;
    }
    
}
