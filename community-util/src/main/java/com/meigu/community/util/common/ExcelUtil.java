package com.meigu.community.util.common;

import java.io.*;
import java.text.*;
import java.util.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;




@SuppressWarnings({"deprecation","rawtypes"})
public class ExcelUtil {

	private	static	SimpleDateFormat  sdf=new SimpleDateFormat();
	
	public static void main(String[] args) throws Exception {

	       File file = new File("e:/12.xls");

	       String[][] result = getData(file, 2);

	       int rowLength = result.length;

	       for(int i=0;i<rowLength;i++) {

	           for(int j=0;j<result[i].length;j++) {

	              System.out.print(result[i][j]+"\t\t");

	           }

	           System.out.println();

	       }

	      

	    }
	/**

     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行

     * @param file 读取数据的源Excel

     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1

     * @return 读出的Excel中数据的内容

     * @throws FileNotFoundException

     * @throws IOException

     */

	public static String[][] getData(File file, int ignoreRows)

           throws FileNotFoundException, IOException {

       List<String[]> result = new ArrayList<String[]>();

       int rowSize = 0;

       BufferedInputStream in = new BufferedInputStream(new FileInputStream(

              file));

       // 打开HSSFWorkbook

       POIFSFileSystem fs = new POIFSFileSystem(in);

       HSSFWorkbook wb = new HSSFWorkbook(fs);

       HSSFCell cell = null;

       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {

           HSSFSheet st = wb.getSheetAt(sheetIndex);

           // 第一行为标题，不取

           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

              HSSFRow row = st.getRow(rowIndex);

              if (row == null) {

                  continue;

              }

              int tempRowSize = row.getLastCellNum() + 1;

              if (tempRowSize > rowSize) {

                  rowSize = tempRowSize;

              }

              String[] values = new String[rowSize];

              Arrays.fill(values, "");

              boolean hasValue = false;

              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {

                  String value = "";

                  cell = row.getCell(columnIndex);

                  if (cell != null) {

                   
                     //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    
                     switch (cell.getCellType()) {

                     case HSSFCell.CELL_TYPE_STRING:

                         value = cell.getStringCellValue();

                         break;

                     case HSSFCell.CELL_TYPE_NUMERIC:

                         if (HSSFDateUtil.isCellDateFormatted(cell)) {

                            Date date = cell.getDateCellValue();

                            if (date != null) {

                                value = new SimpleDateFormat("yyyy-MM-dd")

                                       .format(date);

                            } else {

                                value = "";

                            }

                         } else {

                            value = new DecimalFormat("0").format(cell

                                   .getNumericCellValue());

                         }

                         break;

                     case HSSFCell.CELL_TYPE_FORMULA:

                         // 导入时如果为公式生成的数据则无值

                         if (!cell.getStringCellValue().equals("")) {

                            value = cell.getStringCellValue();

                         } else {

                            value = cell.getNumericCellValue() + "";

                         }

                         break;

                     case HSSFCell.CELL_TYPE_BLANK:

                         break;

                     case HSSFCell.CELL_TYPE_ERROR:

                         value = "";

                         break;

                     case HSSFCell.CELL_TYPE_BOOLEAN:

                         value = (cell.getBooleanCellValue() == true ? "Y"

                                : "N");

                         break;

                     default:

                         value = "";

                     }

                  }

                  if (columnIndex == 0 && value.trim().equals("")) {

                     break;

                  }

                  values[columnIndex] = rightTrim(value);

                  hasValue = true;

              }

 

              if (hasValue) {

                  result.add(values);

              }

           }

       }

       in.close();

       String[][] returnArray = new String[result.size()][rowSize];

       for (int i = 0; i < returnArray.length; i++) {

           returnArray[i] = (String[]) result.get(i);

       }

       return returnArray;

    }
    
    /**

     * 去掉字符串右边的空格

     * @param str 要处理的字符串

     * @return 处理后的字符串

     */

     public static String rightTrim(String str) {

       if (str == null) {

           return "";

       }

       int length = str.length();

       for (int i = length - 1; i >= 0; i--) {

           if (str.charAt(i) != 0x20) {

              break;

           }

           length--;

       }

       return str.substring(0, length);

    }
    
     
     /**
     * @param title   	表格标题名(标题)
     * @param headers	表头(表格属性列名)
     * @param dataset  	填充数据
     * @param out		与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern   如果存在Date 值 设置时间格式化(如:yyyy-MM-dd HH:mm:ss) 如无 可设空(null)
	 * @return
	 */
	public static boolean export(String title, String[] headers, Collection<Object> dataset,OutputStream out,String pattern) {
		boolean flag = false;
		if(!StringUtil.isNotEmpty(pattern)){
			pattern="yyyy-MM-dd";
		}
		try {
			// 声明一个工作薄
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        // 生成一个表格
	        HSSFSheet sheet = workbook.createSheet("第一页");
	        // 设置表格默认列宽度为15个字节
	        sheet.setDefaultColumnWidth(20);
	        //生成一个标题样式
	        HSSFCellStyle titlestyle=workbook.createCellStyle();
	        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        titlestyle.setBottomBorderColor(HSSFColor.BLACK.index);
	        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	        titlestyle.setLeftBorderColor(HSSFColor.BLACK.index);
	        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
	        titlestyle.setRightBorderColor(HSSFColor.BLACK.index);
	        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        titlestyle.setTopBorderColor(HSSFColor.BLACK.index);
	        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        HSSFFont titlefont=workbook.createFont();
	        titlefont.setFontName("宋体");
	        titlefont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        titlefont.setFontHeightInPoints((short)22);
	        titlestyle.setFont(titlefont);
	        
	        //生成一个表头样式
	        HSSFCellStyle headstyle=workbook.createCellStyle();
	        headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        headstyle.setBottomBorderColor(HSSFColor.PLUM.index);
	        headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        headstyle.setLeftBorderColor(HSSFColor.PLUM.index);
	        headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        headstyle.setRightBorderColor(HSSFColor.PLUM.index);
	        headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        headstyle.setTopBorderColor(HSSFColor.PLUM.index);
	        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        HSSFFont  headfont=workbook.createFont();
	        headfont.setFontName("微软雅黑");
	        headfont.setFontHeightInPoints((short)16);
	        headstyle.setFont(headfont);
	        
	        //生成一个单元格样式
	        HSSFCellStyle cellstyle=workbook.createCellStyle();
	        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellstyle.setBottomBorderColor(HSSFColor.PLUM.index);
	        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellstyle.setLeftBorderColor(HSSFColor.PLUM.index);
	        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellstyle.setRightBorderColor(HSSFColor.PLUM.index);
	        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        cellstyle.setTopBorderColor(HSSFColor.PLUM.index);
	        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        HSSFFont  cellfont=workbook.createFont();
	        cellfont.setFontName("Courier New");
	        cellfont.setFontHeightInPoints((short)14);
	        cellstyle.setFont(cellfont);
	        
	        /**生成标题*/
	        HSSFRow row0 = sheet.createRow(0);
	        HSSFCell cell0 = row0.createCell(0);
	        row0.setZeroHeight(false);
	        cell0.setCellValue(title);
	        sheet.addMergedRegion(new Region(0,(short)0,1,(short)(headers.length-1)));
	        cell0.setCellStyle(titlestyle);
	        /**生成表头*/
	        HSSFRow row1 = sheet.createRow(2);
	        for (int i=0;i<headers.length;i++) {
	        	HSSFCell cell1 = row1.createCell((short)i);
	        	cell1.setCellValue(headers[i]);
	        	cell1.setCellStyle(headstyle);
			}
	       if(dataset!=null){
	    	   Iterator<Object> iterator= dataset.iterator();
		       int start=3;
		       while (iterator.hasNext()) {
		    	   Object object = (Object) iterator.next();
		    	   HSSFRow contentrow = sheet.createRow(start++);
		    	   if (object instanceof List) {
		    		   List list=((List)object);
		    		   for (int i = 0; i < list.size(); i++) {
		    			   HSSFCell cell1 =contentrow.createCell(i);
			    		   cell1.setCellValue(list.get(i).toString());
			    		   cell1.setCellStyle(cellstyle);
		    		   }
		    	   }else if (object instanceof Map) {
		    		   Map  map=((Map)object);
		    		   int i=0;
		    		   for (Object key : map.keySet()) {
		    			   HSSFCell cell1 =contentrow.createCell(i++);
			    		   cell1.setCellValue(map.get(key).toString());
			    		   cell1.setCellStyle(cellstyle);
		    		   }
		    	   }else if (object instanceof Object[]) {
		    		   Object[]  val=((Object[])object);
		    		   for (int i = 0; i < val.length; i++) {
		    			   HSSFCell cell1 =contentrow.createCell(i);
			    		   cell1.setCellValue(object.toString());
			    		   cell1.setCellStyle(cellstyle);
		    		   }
		    	   }else if (object instanceof String) {
		    		   HSSFCell cell1 =contentrow.createCell(0);
		    		   cell1.setCellValue(object.toString());
		    		   cell1.setCellStyle(cellstyle);
		    	   }else if (object instanceof Number) {
		    		   HSSFCell cell1 =contentrow.createCell(0);
		    		   cell1.setCellValue(object.toString());
		    		   cell1.setCellStyle(cellstyle);
		    	   }else if (object instanceof Boolean) {
		    		   HSSFCell cell1 =contentrow.createCell(0);
		    		   cell1.setCellValue(object.toString());
		    		   cell1.setCellStyle(cellstyle);
		    	   }else if (object instanceof Date) {
		    		   HSSFCell cell1 =contentrow.createCell(0);
		    		   sdf.applyPattern(pattern);
		    		   cell1.setCellValue(sdf.format((Date)object));
		    		   cell1.setCellStyle(cellstyle);
		    	   }
		       }
	       }
	       workbook.write(out);
		} catch (Throwable e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
