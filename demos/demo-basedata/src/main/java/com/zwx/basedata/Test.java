package com.zwx.basedata;

import com.zwx.basedata.service.TestService;
import com.zwx.basedata.service.impl.TestServiceImpl;
import com.zwx.basedata.until.MD5;
import com.zwx.basedata.until.util.PinYinUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.mx.spring.task.TaskFactory;
import org.springframework.scheduling.config.Task;

import java.io.*;
import java.util.*;


public class Test {
    public static void main(String[] args) {
        /*Test test = new Test();
        test.testPinYin();*/
//        System.out.println(MD5.GetMD5Code("asdfasdfdfgfdgfdg345345dsfgfdgfdg"));
        TestService testService = new TestServiceImpl();
        try {
            readColumn(new File("C:\\Users\\17803829936\\Desktop\\新建文件夹\\test1.xls"),2,3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testPinYin(){
        String testString ="乘";
        String[] duoYinHanziPinYin = PinYinUtils.getDuoYinHanziPinYin(testString);
        System.out.println("获取汉字的拼音:"+Arrays.toString(duoYinHanziPinYin));
        String duoYinHeaderNumberInitials = PinYinUtils.getDuoYinHeaderNumberInitials(testString);
        System.out.println("获取汉字拼音首字母缩写:"+duoYinHeaderNumberInitials);
        String duoYinHeaderNumberInitialsLower = PinYinUtils.getDuoYinHeaderNumberInitialsLower(testString).split(",")[0];
        System.out.println("获取汉字的拼音首字母小写:"+duoYinHeaderNumberInitialsLower);
        String duoYinHeaderNumberInitialsUpper = PinYinUtils.getDuoYinHeaderNumberInitialsUpper(testString);
        System.out.println("获取汉字的拼音首字母大写:"+duoYinHeaderNumberInitialsUpper);
        System.out.println("获取汉字的拼音:"+PinYinUtils.getDuoYinInitials(testString));
        System.out.println("获取汉字的拼音首字母大写:" +PinYinUtils.getDuoYinInitialsUpper(testString));
    }

    public void testTaskFactory(){
        TaskFactory taskFactory = new TaskFactory();
//        Task task = new Task();
    }

    public static void readColumn(File file, int index,int cloumn) throws Exception {
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        Set<String> paramList = new LinkedHashSet<>();
        for (int i = cloumn; i < rows; i++) {
            Cell cell = sheet.getCell(index, i);
            paramList.add(cell.getContents());
        }
        System.out.println(paramList.toString());
        paramList.forEach(ele -> {
            FileOutputStream fos = null;
            File file1 = new File("C:\\Users\\17803829936\\Desktop\\新建文件夹\\" + ele);
            try {
                //创建文件字节输出流  这个路径下的文件必须存在
                FileInputStream fis = new FileInputStream("C:\\Users\\17803829936\\Desktop\\新建文件夹\\read");
                //创建文件字节输入流   如果这个文件不存在  会自动创建一个
                fos = new FileOutputStream(file1);
                //一边读一边写
                byte[] bytes = new byte[1024];
                while (true){
                    int res = fis.read(bytes);
                    if(res == -1) break;
                    fos.write(bytes,0,res);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
