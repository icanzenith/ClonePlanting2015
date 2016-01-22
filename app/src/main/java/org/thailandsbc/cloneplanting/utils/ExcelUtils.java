package org.thailandsbc.cloneplanting.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Icanzenith on 1/18/2016 AD.
 */
public class ExcelUtils {

    private static final String TAG = "ExcelUtils";
    WritableWorkbook workbook;
    File newFile;
    Context context;
    public ExcelUtils(){
    }

    public ExcelUtils(Context context){
        this.context = context;
    }

    public void createWorkBook() {
        try {
            //TODO Change File Name Here!!!!
            //get the sdcard's directory
            File sdCard = Environment.getExternalStorageDirectory();
            //add on the your app's path
            File dir = new File(sdCard.getAbsolutePath() + "/JExcelApiTest.xls");

            workbook = Workbook.createWorkbook(dir);
            WritableSheet sheet     = workbook.createSheet("First  Sheet" , 0);
            WritableSheet sheet2    = workbook.createSheet("Second Sheet" , 1);
            WritableSheet sheet3    = workbook.createSheet("Third  Sheet" , 2);

            Label label = new Label(0, 2, "A label record");
            sheet.addCell(label);

            Number number = new Number(3, 4, 3.1459);
            sheet.addCell(number);

            workbook.write();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

}
