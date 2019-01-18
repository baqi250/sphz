package com.sphz.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
/**
* 导入到EXCEL
* 类名称：ObjectExcelView.java
* @author DK
* @version 1.0
 */
public class ObjectExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook arg1, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		HSSFSheet sheet;
		HSSFRow row;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("建设工程规划许可面积及增速");
		sheet.setDefaultRowHeightInPoints(30);
		   //----------------标题样式---------------------
		  HSSFCellStyle titleStyle = workbook.createCellStyle();        //标题样式
		            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		            Font ztFont = workbook.createFont();   
		            ztFont.setItalic(false);                     // 设置字体为斜体字   
		            ztFont.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”   
		            ztFont.setFontHeightInPoints((short)16);    // 将字体大小设置为18px   
		            ztFont.setFontName("宋体");             // 将“宋体”字体应用到当前单元格上  
		            ztFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //加粗
		            ztFont.setUnderline(Font.U_DOUBLE);         // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）   
		           // ztFont.setStrikeout(true);                  // 是否添加删除线   
		            titleStyle.setFont(ztFont); 
		
		
		  ----------二级标题格样式----------------------------------
		          HSSFCellStyle titleStyle2 = workbook.createCellStyle();        //表格样式
		          titleStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		          titleStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		          titleStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		          titleStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		          titleStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		          titleStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		          Font ztFont2 = workbook.createFont();   
		          ztFont2.setItalic(false);                     // 设置字体为斜体字   
		          ztFont2.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”   
		          ztFont2.setFontHeightInPoints((short)11);    // 将字体大小设置为18px   
		          ztFont2.setFontName("宋体");             // 字体应用到当前单元格上   
		          ztFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //加粗
                  ztFont.setUnderline(Font.U_DOUBLE);         // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）   
                  ztFont.setStrikeout(true);                  // 是否添加删除线   
		          titleStyle2.setFont(ztFont2);
		      
		      
		      ----------单元格样式----------------------------------
		             HSSFCellStyle cellStyle = workbook.createCellStyle();        //表格样式
		             cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		             cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		             cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		             cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		             cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		             cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		             Font cellFont = workbook.createFont();   
		             cellFont.setItalic(false);                     // 设置字体为斜体字   
		             cellFont.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”   
		             cellFont.setFontHeightInPoints((short)10);    // 将字体大小设置为18px   
		             cellFont.setFontName("宋体");             // 字体应用到当前单元格上   
		             cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		             cellStyle.setFont(cellFont);
		             cellStyle.setWrapText(true);//设置自动换行
		             
		             // ----------------------创建第一行---------------
                     // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
                     row = sheet.createRow(0);
                     // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
                     cell = row.createCell(0);
                     // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
                     sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));
                     // 设置单元格内容
                     cell.setCellValue("占压城市地下管线、输油气管道、化工产品管道违法违规建设汇总表");
                     cell.setCellStyle(titleStyle);
                     
                     // ------------------创建第二行(单位、填表日期)---------------------
                                row = sheet.createRow(1); // 创建第二行
                                cell = row.createCell(0);
                                cell.setCellValue("填报单位名称（盖章）： ");
                                cell.setCellStyle(titleStyle2);
                                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
                                cell = row.createCell(4);
                                sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 5));
                                
                                cell.setCellValue("大数据中心");
                    //            cell.setCellValue("*****");
                                cell.setCellStyle(titleStyle2);
                                cell = row.createCell(13); // 填表时间
                                sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 16));
                                SimpleDateFormat df1 = new SimpleDateFormat("yyyy.MM.dd");// 设置日期格式
                                cell.setCellValue("填表时间："+df1.format(new Date()));
                                cell.setCellStyle(titleStyle2);
                    //            HSSFCell cell14 = row.createCell(15); // 填表时间
                    //            cell14.setCellValue();
                    //            cell14.setCellValue("2017.11.30");
                    //            cell14.setCellStyle(titleStyle2);
		          
                                // ------------------创建表头start---------------------
                                       row = sheet.createRow(0); // 创建第一行CellRangeAddress（起始行号，终止行号， 起始列号，终止列号）
                                       sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));
                                       cell = row.createCell(0);
                                       cell.setCellValue("区市");
                                       cell.setCellStyle(titleStyle2);
                                       cell = row.createCell(1);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(2);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(3);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(4);
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));
                                       
                                      
                                     
                                       cell = row.createCell(1);
                                       cell.setCellValue("规划许可建筑面积");
                                       cell.setCellStyle(titleStyle2);
                                       cell = row.createCell(2);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(3);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(4);
                                       cell.setCellStyle(cellStyle);
                                       
                                       
                                       row = sheet.createRow(1); // 创建第一行CellRangeAddress（起始行号，终止行号， 起始列号，终止列号）
                                       sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
                                      
                                       cell = row.createCell(0);
                                       cell.setCellStyle(cellStyle);
                                       
                                       cell = row.createCell(1);
                                       cell.setCellValue("本月");
                                       cell.setCellStyle(titleStyle2);
                                       cell = row.createCell(2);
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
                                       cell = row.createCell(3);
                                       cell.setCellValue("累计");
                                       cell.setCellStyle(titleStyle2);
                                       cell = row.createCell(4);
                                       cell.setCellStyle(cellStyle);
                                      
                                       
                                       row = sheet.createRow(2); // 创建第一行CellRangeAddress（起始行号，终止行号， 起始列号，终止列号）
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 1));
                                       
                                       cell = row.createCell(0);
                                       cell.setCellStyle(cellStyle);
                                       
                                       cell = row.createCell(1);
                                       cell.setCellValue("总量");
                                       cell.setCellStyle(titleStyle2);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 2));
                                       cell = row.createCell(2);
                                       cell.setCellValue("增速");
                                       cell.setCellStyle(titleStyle2);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 3));
                                       cell = row.createCell(3);
                                       cell.setCellValue("总量");
                                       cell.setCellStyle(titleStyle2);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 4));
                                       cell = row.createCell(4);
                                       cell.setCellValue("增速");
                                       cell.setCellStyle(titleStyle2);
                                       
                                       List<Object> data = new ArrayList<>();        //将前台传来的数据存入到list中
                                       data.add("市南区");
                                       data.add(966);
                                       data.add("10%");
                                       data.add(8565);
                                       data.add("20%");
                                       
                                       int rowNum = 3;    //从第四行开始
                                       row = sheet.createRow(rowNum); 
                                       for (int j = 0; j < data.size(); j++) {        //将数据添加到单元格中    
                                             System.out.println(data.get(j));
                                           sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, j, j));
                                           cell = row.createCell(j);
                                           cell.setCellValue(""+data.get(j)+"");
                                           cell.setCellStyle(cellStyle);
                                       }
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 3, 2, 2));
                                       cell = row.createCell(2);
                                       cell.setCellValue("隐患名称");
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 5));
                                       cell = row.createCell(3);
                                       cell.setCellValue("位置描述");
                                       cell.setCellStyle(cellStyle);
                                       
                                       cell = row.createCell(4);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(5);
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 10));
                                       cell = row.createCell(6);
                                       cell.setCellValue("管线情况");
                                       cell.setCellStyle(cellStyle);
                                       
                                       cell = row.createCell(7);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(8);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(9);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(10);
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 11, 13));
                                       cell = row.createCell(11);
                                       cell.setCellValue("占压物情况");
                                       cell.setCellStyle(cellStyle);
                                       
                                       cell = row.createCell(12);
                                       cell.setCellStyle(cellStyle);
                                       cell = row.createCell(13);
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 2, 14, 14));
                                       cell = row.createCell(14);
                                       cell.setCellValue("占压物用途");
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 3, 15, 15));
                                       cell = row.createCell(15);
                                       cell.setCellValue("已采用的安全防护措施");
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 3, 16, 16));
                                       cell = row.createCell(16);
                                       cell.setCellValue("备注");
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 3, 17, 17));
                                       cell = row.createCell(17);
                                       cell.setCellValue("联系人电话");
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 3, 18, 18));
                                       cell = row.createCell(18);
                                       cell.setCellValue("是否已和区管委和供热办联系");
                                       cell.setCellStyle(cellStyle);
                                       
                                       sheet.addMergedRegion(new CellRangeAddress(2, 3, 19, 19));
                                       cell = row.createCell(19);
                                       cell.setCellValue("是否采取防范措施");
                                       cell.setCellStyle(cellStyle);
                                       
                                       //--------------------------- 创建第四行--------------------
                                           row = sheet.createRow(1); 
                                           sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
                                           cell = row.createCell(3);
                                           cell.setCellValue("所在区县");
                                           cell.setCellStyle(cellStyle);
                                           
                                           cell = row.createCell(0);
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 4, 4));
                                           cell = row.createCell(4);
                                           cell.setCellValue("所在街道");
                                           cell.setCellStyle(cellStyle);
                                           
                                           cell = row.createCell(1);
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 5));
                                           cell = row.createCell(5);
                                           cell.setCellValue("详细地址");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 6));
                                           cell = row.createCell(6);
                                           cell.setCellValue("管线建成时间");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 7));
                                           cell = row.createCell(7);
                                           cell.setCellValue("管线埋深");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 8, 8));
                                           cell = row.createCell(8);
                                           cell.setCellValue("管径");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 9, 9));
                                           cell = row.createCell(9);
                                           cell.setCellValue("管线压力等级");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 10, 10));
                                           cell = row.createCell(10);
                                           cell.setCellValue("占压管线长度");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 11, 11));
                                           cell = row.createCell(11);
                                           cell.setCellValue("占压单位(个人)名称");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 12, 12));
                                           cell = row.createCell(12);
                                           cell.setCellValue("占压物建成时间");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 13, 13));
                                           cell = row.createCell(13);
                                           cell.setCellValue("占压物面积(平方米)");
                                           cell.setCellStyle(cellStyle);
                                           
                                           sheet.addMergedRegion(new CellRangeAddress(3, 3, 14, 14));
                                           cell = row.createCell(14);
                                           cell.setCellValue("经营、出租、自用、居住");
                                           cell.setCellStyle(cellStyle);
                                           
                                           cell = row.createCell(15);
                                           cell.setCellStyle(cellStyle);
                                           cell = row.createCell(16);
                                           cell.setCellStyle(cellStyle);
                                           cell = row.createCell(17);
                                           cell.setCellStyle(cellStyle);
                                           cell = row.createCell(18);
                                           cell.setCellStyle(cellStyle);
                                           cell = row.createCell(19);
                                           cell.setCellStyle(cellStyle);
                                           
                                           //-------------------------表头end---------------------
                                   
                                           List<Object> data = new ArrayList<>();        //将前台传来的数据存入到list中
                                           data.add("1");
                                           data.add("重大隐患");
                                           data.add("111");
                                           data.add("房山区");
                                           data.add("东风街道办事处");
                                           data.add("111");
                                           data.add("2018");
                                           data.add("111");
                                           data.add("11");
                                           data.add("11");
                                           data.add("11");
                                           data.add("111");
                                           data.add("2018");
                                           data.add("11");
                                           
                                           data.add("经营");
                                           data.add("11");
                                           data.add("11");
                                           data.add("11");
                                           data.add("以来安县");
                                           data.add("11");
                                          
                                           
                                           int rowNum = 4;    //从第四行开始
                                           row = sheet.createRow(rowNum); 
                                           for (int j = 0; j < data.size(); j++) {        //将数据添加到单元格中    
                                                 System.out.println(data.get(j));
                                               sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, j, j));
                                               cell = row.createCell(j);
                                               cell.setCellValue(""+data.get(j)+"");
                                               cell.setCellStyle(cellStyle);
                                           }
                                       
	}
	
    *//**
     * @param border 边框宽度
     * @param region 合并单元格区域范围
     * @param sheet  
     * @param wb
     *//*
    public static void setRegionBorder(int border, CellRangeAddress region, HSSFSheet sheet,Workbook wb){  
        RegionUtil.setBorderBottom(border,region, sheet, wb);  
        RegionUtil.setBorderLeft(border,region, sheet, wb);  
        RegionUtil.setBorderRight(border,region, sheet, wb);  
        RegionUtil.setBorderTop(border,region, sheet, wb);  
    } 
*/
}
