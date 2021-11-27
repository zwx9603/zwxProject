package com.dscomm.iecs.schedule.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public static List<Map<String,String>> fromExcel(String path){
        List<Map<String,String>> mapExcels = new ArrayList<>();
        try {

            Workbook wb = new HSSFWorkbook(new FileInputStream(path));
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 1;i<= sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                Map<String,String> microMap= new HashMap<>();
                if (row != null){
                    for (int j = 0;j < row.getLastCellNum();j++){
                        Cell cell = row.getCell(j);
                        String value = cell.getStringCellValue();
                        switch (j){
                            case 0:
                                microMap.put("name",value);
                                break;
                            case 1:
                                microMap.put("district",value);
                                break;
                            case 2:
                                microMap.put("street",value);
                                break;
                            case 3:
                                microMap.put("address",value);
                                break;
                            case 4:
                                microMap.put("brigade",value);
                                break;
                            case 5:
                                microMap.put("squadron",value);
                                break;
                            case 6:
                                microMap.put("status",value);
                                break;
                            case 7:
                                microMap.put("type",value);
                                break;
                            case 8:
                                microMap.put("onDuty",value);
                                break;
                            case 9:
                                microMap.put("handle",value);
                                break;
                            case 10:
                                microMap.put("contactPerson",value);
                                break;
                            case 11:
                                microMap.put("contactPersonPhone",value);
                                break;
                            case 12:
                                microMap.put("dutyPhone",value);
                                break;
                            case 13:
                                microMap.put("personNum",value);
                                break;
                            default:
                                break;

                        }
                    }
                    mapExcels.add(microMap);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return mapExcels;
    }


}
