
package kahonen;

import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;



public class ExcelWriter {
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    
    public ExcelWriter(){
        
    }
    
    public ExcelWriter(double[][] data){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        int numRow = 0;
        for(double[] r: data){
            int numCol = 0;
            XSSFRow row = sheet.createRow(numRow);
            for(Object c: r){
                XSSFCell cell = row.createCell(numCol);
                cell.setCellValue(data[numRow][numCol]);
                numCol++;
            }
            numRow++;
        }
    }
    
    public void read(double[][] data){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        int numRow = 0;
        for(double[] r: data){
            int numCol = 0;
            XSSFRow row = sheet.createRow(numRow);
            for(Object c: r){
                XSSFCell cell = row.createCell(numCol);
                cell.setCellValue(data[numRow][numCol]);
                numCol++;
            }
            numRow++;
        }
    }
    
    public boolean write(String filename){
        try{
            FileOutputStream outStream = new FileOutputStream(filename+".xlsx");
            workbook.write(outStream);
            System.out.println(filename+".xlsx successfully created");
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
}
