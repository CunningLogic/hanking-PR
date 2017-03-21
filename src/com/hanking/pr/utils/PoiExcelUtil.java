package com.hanking.pr.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 操作Excel工具类
 * @author 金洋
 * @date 2015-7-14
 * */
public class PoiExcelUtil {

    /**
     * 导出excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String keys[],String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?"": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }
    
	/** 
     * 下载excel模版文件
     * @param fileName  文件名
     * @param request
     * @param response
     */
    public static String download(String fileName, HttpServletRequest request,
            HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try {
        	//获取模版路径
            String path = request.getSession().getServletContext().getRealPath("/")+ "template\\";
            //System.out.println("---------->path===="+path);
            InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 针对读取Excel 03和07版本
     * 根据文件的路径创建Workbook对象
     * @param ins  文件流
     * @exception NoClassDefFoundError  由于目前只支持03和07的Excel，10的会报"NoClassDefFoundError"错误，将错误
     * 抛出页面显示版本错误
     */
    public static Workbook getExcelWorkBook(InputStream ins) throws NoClassDefFoundError,IllegalArgumentException{
    	Workbook wb = null;
    	try {
			wb = WorkbookFactory.create(ins);
			ins.close();
			return wb;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ins != null){
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return null;
    }
    /**  
     * 以Map的格式存储数据  
     * 读取Excel文件的数据 
     * @param ins  Excel文件流
     * @param headTitle     
     * @return  
     */
    public static Map<String, List<Map<String, Object>>> readExcelMap(InputStream ins,String[] headTitle) throws NoClassDefFoundError,IllegalArgumentException{
    	Workbook wb = getExcelWorkBook(ins);//获取workbook对象
    	int sheetNum = wb.getNumberOfSheets();//获取sheet页个数
    	//存储Excel相关的数据
    	Map<String, List<Map<String, Object>>> excelData = new HashMap<String, List<Map<String,Object>>>();
    	if(sheetNum > 0){//遍历相关sheet页并获取sheet的相关数据
    		for(int i = 0;i < sheetNum;i++){
    			Sheet sheet = wb.getSheetAt(i);//创建sheet
    			String sheetName = wb.getSheetName(i);//获取sheet页名
    			List<Map<String, Object>> sheetData = getExcelMapData(sheet,headTitle);//获取相关sheet页的数据
    			excelData.put(sheetName, sheetData);
    		}
    	}
    	return excelData;
    }
    /** 
     * 获取sheet表中的数据 
     * @param sheet 
     * @return headTitle 格式为.1.2....列标做为key 
     */  
    public static List<Map<String,Object>> getExcelMapData(Sheet sheet,String[] headTitle){
    	int startRow = sheet.getFirstRowNum();//获取开始行和结束行
    	int lastRow = sheet.getLastRowNum();
    	List<Map<String,Object>> allRowMapData = new ArrayList<Map<String,Object>>();
    	if(startRow != lastRow){
    		startRow = startRow+1;//忽略第一行数据
    		for(int indexRow = startRow;indexRow <= lastRow;indexRow++){//获取行数据
    			Row row = sheet.getRow(indexRow);
    			if(row == null){
    				continue;
    			}
    			int firstCellNum=row.getFirstCellNum();
    			int lastCellNum=row.getLastCellNum();
    			Map<String,Object> RowDataMap=new HashMap<String,Object>();
    			for (int indexCol = firstCellNum; indexCol <lastCellNum; indexCol++) {//遍历sheet页相关的列数据
    				Cell cell=row.getCell(indexCol);
    				String cellKey=headTitle[indexCol-firstCellNum];
    				if(cell==null){
    					continue;
    				}
					Object cellValue = getCellValue(cell);//获取列的数据的信息
					RowDataMap.put(cellKey, cellValue);
    			}
    			allRowMapData.add(RowDataMap);
    		}
    	}
    	return allRowMapData;
    }
    /**
     * 格式化Excel单元格的数据
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell){
    	Object cellValue=null;
    	switch (cell.getCellType()) {
    	case Cell.CELL_TYPE_BLANK:
    		cellValue = "";
    		break;
    	case Cell.CELL_TYPE_ERROR:
    		cellValue = Byte.toString(cell.getErrorCellValue());
    		break;
    	case Cell.CELL_TYPE_STRING:
    		cellValue= cell.getRichStringCellValue().getString();
    		break;
    		/** 在excel中日期也是数字,在此要进行判*/ 
    	case Cell.CELL_TYPE_NUMERIC:
    		double number=cell.getNumericCellValue();
    		if (DateUtil.isCellDateFormatted(cell)) {
    			cellValue =DateTimesUtil.getTimes(number);
    		} else {
    			//cellValue = cellValue;
    			cellValue = number + "";//Integer.toString((int)cell.getNumericCellValue());
    		}
    		break;
    	case Cell.CELL_TYPE_BOOLEAN:
    		cellValue= Boolean.toString(cell.getBooleanCellValue());
    		break;
    	case Cell.CELL_TYPE_FORMULA:
    		try {
				cellValue= String.valueOf(cell.getNumericCellValue());
			} catch (Exception e) {
				cellValue = String.valueOf(cell.getRichStringCellValue());
			}
    		break;
    	default:
    		cellValue = "";
    	}
    	return cellValue;
    }
}