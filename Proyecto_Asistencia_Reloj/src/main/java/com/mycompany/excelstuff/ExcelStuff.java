/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.excelstuff;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.util.DateFormatConverter; 
import org.apache.poi.ss.usermodel.*;
import javax.swing.*;

/**
 *
 * @author Sarel
 */
public class ExcelStuff {
String DATAPATH = "C:\\Datadata\\ManejoDeDatos.xlsx";
static String Usuarios = "Usuarios", Empleados = "Empleados", Asistencias = "Asistencias", Planilla = "Planilla", Departamentos = "Departamentos"; 
  int ultimoID = UltimaIDEmpleado();

    
     public String ReadExcelFile(String TableName, int Rnum, int Cnum){
        
        String data = ""; 
        try(FileInputStream fis = new FileInputStream(DATAPATH);
            Workbook wb = WorkbookFactory.create(fis)) {
             /**/
             
            Sheet s = wb.getSheet(TableName);
            if( s !=null){ 
            Row r = s.getRow(Rnum);
                if (r !=null){
                Cell c = r.getCell(Cnum);
                    if (c != null) {
                        DataFormatter FormatCell = new DataFormatter(); 
                        data = FormatCell.formatCellValue(c);
                    }                    
                }
            }   
        }catch (Exception e) {
        System.out.println("Then this cell its empty or contain an error (ReadExcelFile) ");
            return null; 
        }
            return data; 
        
            }
     
     public int IDAEstadoEmpleado(int ID_Empleado ){
         int UltimaIDA =  UltimaIDAsistencia(); 
         
          String result, resultpass;
      int resultInt=0,i=0,resultInttemp = 0; 
      int RInum=0, Cinum = 1; 
      
      Cell TID;
         
             RInum +=1;
             try (FileInputStream fis = new FileInputStream(DATAPATH); /**/
            Workbook wb = WorkbookFactory.create(fis);)  {
            while (i != UltimaIDA){Sheet s = wb.getSheet(Asistencias);
            Row r = s.getRow(RInum), rp;
            Cell c = r.getCell(Cinum),cp; 
            DataFormatter FormatCell = new DataFormatter(),formatp = new DataFormatter(); 
            result = FormatCell.formatCellValue(c);
            
            if(result.equals(UltimaIDA)){
                rp = s.getRow(RInum);
                cp = rp.getCell(5);
                resultpass = formatp.formatCellValue(cp);
                if(resultpass.equals("1")){
                    i = UltimaIDA; 
                    wb.close();
                    return 8; 
                }else if(resultpass.equals("2")){
                 i = UltimaIDA; 
                 wb.close();
                 return -8;
                    }                   
            }else {
                i +=1;
            }
            }     
            
             } catch (Exception e) {
                 System.out.println("Look mon! i found the last one!" + "Fail Estado Asistencia");
                 i = ultimoID;
                 return 8;
             }
               
         
            
         
        return -1; 
     }
     
         
     
     
     
     public void WriteExcelFile(String TableName, int Rnum, int Cnum, String Data, String tipoFormato){
            String ExcelStyleString = tipoFormato;
            
         
            try (FileInputStream fis = new FileInputStream(DATAPATH))
             {
            Workbook wb = WorkbookFactory.create(fis); 
            Sheet s = wb.getSheet(TableName);    
            DataFormat pioFormat = wb.createDataFormat();
            CellStyle OpaCellStyle = wb.createCellStyle();
            
            OpaCellStyle.setDataFormat(pioFormat.getFormat(tipoFormato));
            if (s == null) s = wb.createSheet(TableName);
            
            Row r = s.getRow(Rnum);
            if (r == null) {
                r = s.createRow(Rnum);
            }
            Cell c = r.getCell(Cnum);
            if (c == null) {
                c = r.createCell(Cnum);
            }
            
            c.setCellValue(Data);
            c.setCellStyle(OpaCellStyle);
            try(FileOutputStream fos = new FileOutputStream(DATAPATH)){
                wb.write(fos); 
            }            
             System.out.println("Then You Success writing stuff" + c);
         } catch (Exception e) {
             System.out.println("Then you fucked up somehow.");
             JOptionPane.showMessageDialog(null,"los datos no se guardaron correctamente", "Error", JOptionPane.ERROR_MESSAGE);
             
             e.printStackTrace();
         } 
     }
     public int verificar_usuarios(String user, String password){
      String result, resultpass;
      int resultInt=-1,i=0,resultInttemp = 0; 
      int RInum=0, Cinum = 1; 
      
      Cell TID;
         while(i != ultimoID){
             RInum +=1;
             try {
                 FileInputStream fis = new FileInputStream(DATAPATH); /**/
            Workbook wb = WorkbookFactory.create(fis); 
            Sheet s = wb.getSheet(Usuarios);
            Row r = s.getRow(RInum), rp;
            Cell c = r.getCell(Cinum),cp; 
            DataFormatter FormatCell = new DataFormatter(),formatp = new DataFormatter(); 
            result = FormatCell.formatCellValue(c);
            fis.close();
            if(result.equals(user)){
                rp = s.getRow(RInum);
                cp = rp.getCell(2);
                resultpass = formatp.formatCellValue(cp);
                if(resultpass.equals(password)){
                 TID = r.getCell(3);
                 resultInt = Integer.parseInt(FormatCell.formatCellValue(TID)); 
                 i = ultimoID;
                 wb.close();
                 if(resultInt > 0){
                     return resultInt;
                 }
                 
                }                   
            }else{
                i +=1;
            }         
            resultInttemp = resultInt;
             } catch (Exception e) {
                 System.out.println("Look mon! i found the last one!" + "Amo");
                 i = ultimoID;
                 return -1;
             }
               
         
            }
        return resultInt; 

     }
    
     public int ID_EmpleadoLogin(String user, String password){
      String result, resultpass;
      int resultInt=0,i=0,resultInttemp = 0; 
      int RInum=0, Cinum = 1; 
      
      Cell TID;
         while(i != ultimoID){
             RInum +=1;
             try {
                 FileInputStream fis = new FileInputStream(DATAPATH); /**/
            Workbook wb = WorkbookFactory.create(fis); 
            Sheet s = wb.getSheet(Usuarios);
            Row r = s.getRow(RInum), rp;
            Cell c = r.getCell(Cinum),cp; 
            DataFormatter FormatCell = new DataFormatter(),formatp = new DataFormatter(); 
            result = FormatCell.formatCellValue(c);
            fis.close();
            if(result.equals(user)){
                rp = s.getRow(RInum);
                cp = rp.getCell(2);
                resultpass = formatp.formatCellValue(cp);
                if(resultpass.equals(password)){
                 TID = r.getCell(0);
                 resultInt = Integer.parseInt(FormatCell.formatCellValue(TID)); 
                 i = ultimoID; 
                 wb.close();
                 return resultInt;
                }                   
            }else{
                i +=1;
                wb.close();
                
            }
            resultInttemp = resultInt;
             } catch (Exception e) {
                 System.out.println("Look mon! i found the last one!" + "Amo");
                 i = ultimoID;
                 return -1;
             }
               
         
            }
         
        return resultInt; 

     } 
     
     
     
     public String ID_EmpleadoDepartamento (int GetIDDepartamento){
      String result, resultpass = "a";
      int resultInt=0,i=0,resultInttemp = 0; 
      int RInum=0, Cinum = 0; 
      
      String ID_Departamento = String.valueOf(GetIDDepartamento);
      
      Cell TID;
         while(i != 4){
             RInum +=1;
             try {
                 FileInputStream fis = new FileInputStream(DATAPATH); /**/
            Workbook wb = WorkbookFactory.create(fis); 
            Sheet s = wb.getSheet(Departamentos);
            Row r = s.getRow(RInum), rp;
            Cell c = r.getCell(Cinum),cp; 
            DataFormatter FormatCell = new DataFormatter(),formatp = new DataFormatter(); 
            result = FormatCell.formatCellValue(c);
            fis.close();
            if(result.equals(ID_Departamento)){
                rp = s.getRow(RInum);
                cp = rp.getCell(1);
                resultpass = formatp.formatCellValue(cp); 
                i = ultimoID;
                wb.close();
                return resultpass;
            }else{
                i +=1;
            }
            wb.close();
            fis.close();
            resultInttemp = resultInt;
             } catch (Exception e) {
                 System.out.println("IDEmpleadoDepartamento Fail");
                 i = ultimoID;
                 return "-1";
             }
               
         
            }
         
        return resultpass; 

     } 
     
     public float ObtenerhorasTrabajadas(int Id_Empleado){
     float horasChambeadas = 0; 
         
         
     return horasChambeadas;
     }
     
  public int UltimaIDEmpleado(){
         String result;
         int i=0;
         int resultInt = 0,resultInttemp = 0; 
         int RInum=0, Cinum = 0; 
         
         try(FileInputStream fis = new FileInputStream(DATAPATH);
                     Workbook wb = WorkbookFactory.create(fis);) {
         while(i != 6){
            RInum +=1;
            Sheet s = wb.getSheet(Usuarios);
            Row r = s.getRow(RInum);
            Cell c = r.getCell(Cinum); 
            DataFormatter FormatCell = new DataFormatter(); 
            result = FormatCell.formatCellValue(c);
            resultInt = Integer.parseInt(result);
            resultInttemp = resultInt;}
         } catch (Exception e) {
                 System.out.println("Ultimo ID empleado " + resultInt);
                 i = 6;
             }
         return resultInt;
     }
  
  
    public int UltimaIDAsistencia(){
         String result;
         int i=0;
         int resultInt = 0,resultInttemp = 0; 
         int RInum=0, Cinum = 0; 
         
         try(FileInputStream fis = new FileInputStream(DATAPATH);
                     Workbook wb = WorkbookFactory.create(fis);) {
         while(i != 6){
            RInum +=1;
            Sheet s = wb.getSheet(Asistencias);
            Row r = s.getRow(RInum);
            Cell c = r.getCell(Cinum); 
            DataFormatter FormatCell = new DataFormatter(); 
            result = FormatCell.formatCellValue(c);
            resultInt = Integer.parseInt(result);
            resultInttemp = resultInt;}
         } catch (Exception e) {
                 System.out.println("Ultimo ID Asistencia " + resultInt);
                 i = 6;
             }
         return resultInt;
     }
         
     }   

    

