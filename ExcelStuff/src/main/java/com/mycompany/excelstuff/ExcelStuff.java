/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.excelstuff;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 *
 * @author Sarel
 */
public class ExcelStuff {
String DATAPATH = "C:\\Datadata\\ManejoDeDatos.xlsx";
static String Usuarios = "Usuarios", Empleados = "Empleados", Asistencias = "Asistencias", Planilla = "Planilla"; 
    public static void main(String[] args) {/* this funtion as a debugger of somekind*/
        ExcelStuff test = new ExcelStuff();
        String Name = test.ReadExcelFile(Usuarios,0, 0);
        String PassName = test.ReadExcelFile(Usuarios, 0, 3);
        if(PassName == null){
            System.out.println("Then this cell its empty or contain an error");
        }
        System.out.println("Name: " + Name + " And Pass: " + PassName);
        
       int CNom = 3, Rnum = 5;
       String WriteData = "Somethings to write on"; 
       test.WriteExcelFile("Usuarios", Rnum, CNom, "Working so far" );
    }
    
     public String ReadExcelFile(String TableName, int Rnum, int Cnum){
        
        String data = ""; 
        try {
            FileInputStream fis = new FileInputStream(DATAPATH); /**/
            Workbook wb = WorkbookFactory.create(fis); 
            Sheet s = wb.getSheet(TableName);
            Row r = s.getRow(Rnum);
            Cell c = r.getCell(Cnum); 
            DataFormatter FormatCell = new DataFormatter(); 
            data = FormatCell.formatCellValue(c);
            fis.close();
        } catch (Exception e) {
        System.out.println("Then this cell its empty or contain an error");
            return null; 
        }
            return data; 
        
    }
     
     public void WriteExcelFile(String TableName, int Rnum, int Cnum, String Data){
            
         try {
            FileInputStream fis = new FileInputStream(DATAPATH);
            Workbook wb = WorkbookFactory.create(fis); 
            Sheet s = wb.getSheet(TableName);
            Row r = s.getRow(Rnum);
             if (r == null){
                r = s.createRow(Cnum);
            }
            Cell c = r.createCell(Cnum);
            if (c == null){
                c = r.createCell(Cnum);
            }
            c.setCellValue(Data);
            FileOutputStream fos = new FileOutputStream(DATAPATH);
            wb.write(fos);
            
         } catch (Exception e) {
             System.out.println("Then you Screw it up somehow");
             e.printStackTrace();
         }
         
            
     }
    
}
