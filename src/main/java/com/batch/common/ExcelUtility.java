package com.batch.common;

import com.batch.dto.Payphone;
import com.thoughtworks.xstream.core.util.Fields;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {
    public static <T> void generateSimpleExcelFile(String[] columnHeaders,
                                                   String[] fieldList, List<T> rows,
                                                   String fileDirectory,
                                                   String fileName,
                                                   String sheetName  )
    {

        if( columnHeaders.length != fieldList.length )
           throw new RuntimeException("Number of columns do no match the number of data fields!");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet( sheetName );

        //Create row titles
        XSSFRow headerRow = spreadsheet.createRow(0);
        for(int j = 0; j < columnHeaders.length; j++)
            headerRow.createCell(j).setCellValue( columnHeaders[j]);
        //Insert the data rows into the spreadsheet
        int rowCount = 1;
        XSSFRow dataRow = null;
        for( int x = 0; x < rows.size(); x++ )
        {
            dataRow = spreadsheet.createRow( rowCount++ );
            //Only include the fields requested in @param FieldList
            for( int y = 0; y < fieldList.length; y++ )
            {
                String value = null;
                try
                {
                    Field objField = rows.get(x).getClass().getDeclaredField(fieldList[y]);
                    objField.setAccessible(true); //Mark as accessible to access private fields
                    value = objField.get( rows.get(x) ).toString();
                }
                catch(NoSuchFieldException noFieldException)
                {
                    noFieldException.printStackTrace();
                }
                catch(IllegalAccessException illegalAccessException )
                {
                    illegalAccessException.printStackTrace();
                }
                dataRow.createCell( y ).setCellValue( value );
            }
        }
        //Write the Excel file to file system
        writeToFile(workbook,fileName, fileDirectory);
    }
    private static void writeToFile(XSSFWorkbook workbook, String fileName, String fileDirectory )
    {
        FileOutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream( new File(fileDirectory + "\\" + fileName ) );
            workbook.write( outputStream );
            outputStream.close();
        }
        catch(FileNotFoundException fileNotFound )
        {
            fileNotFound.printStackTrace();
        }
        catch(IOException ioException )
        {
            ioException.printStackTrace();
        }
    }


    public static void main(String[] arhs) throws IllegalAccessException, NoSuchFieldException {
        List<Payphone> a = new ArrayList<Payphone>();
        Payphone b = new Payphone();
        b.setCompany("MSFT");
        b.setBorough("QNS");
        a.add( b );
        generateSimpleExcelFile( new String[]{"company", "borough"},
                                 new String[]{"company", "borough"},
                                 a,
                                "C:\\Users\\Anton\\Desktop",
                                "payphones",
                                "Payphones in NYC" );
    }

}
