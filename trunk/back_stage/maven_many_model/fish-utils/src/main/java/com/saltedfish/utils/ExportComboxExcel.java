package com.saltedfish.utils;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
  
import org.apache.poi.hssf.usermodel.DVConstraint;  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFDataValidation;  
import org.apache.poi.hssf.usermodel.HSSFFont;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;  
import org.apache.poi.ss.usermodel.DataValidation;  
import org.apache.poi.ss.usermodel.Name;  
import org.apache.poi.ss.util.CellRangeAddressList;  
  
public class ExportComboxExcel {  
    //信息系统尸体  
    class InformationSystem{  
        private int id;  
        private String name;  
  
        public int getId() {  
            return id;  
        }  
        public void setId(int id) {  
            this.id = id;  
        }  
        public String getName() {  
            return name;  
        }   
        public void setName(String name) {  
            this.name = name;  
        }  
    }  
  
    List<InformationSystem> systemList = new ArrayList<InformationSystem>();  
    List<String> systemNames = new ArrayList<String>();  
  
      
    private HSSFWorkbook workbook = null;   
    private HSSFCellStyle titleStyle = null;   
    private HSSFCellStyle dataStyle = null;  
  
    /** 
     * 列头样式 
     * @param workbook 
     * @param sheet 
     */  
    public void setTitleCellStyles(HSSFWorkbook workbook,HSSFSheet sheet){  
        titleStyle = workbook.createCellStyle();  
  
        //设置边框  
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //设置背景色  
        titleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);  
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        //设置居中  
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置字体  
        HSSFFont font = workbook.createFont();  
        font.setFontName("宋体");  
        font.setFontHeightInPoints((short) 11); //设置字体大小  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示  
        titleStyle.setFont(font);//选择需要用到的字体格式  
        //设置自动换行  
        titleStyle.setWrapText(true);  
        //设置列宽 ,第一个参数代表列id(从0开始),第2个参数代表宽度值  
        sheet.setColumnWidth(0, 3000);   
        sheet.setColumnWidth(1, 12000);   
        sheet.setColumnWidth(2, 15000);   
        sheet.setColumnWidth(3, 7000);   
        sheet.setColumnWidth(4, 7000);   
    }  
    /** 
     * 数据样式 
     * @param workbook 
     * @param sheet 
     */  
    public void setDataCellStyles(HSSFWorkbook workbook,HSSFSheet sheet){  
        dataStyle = workbook.createCellStyle();  
  
        //设置边框  
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //设置背景色  
        dataStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);  
        dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        //设置居中  
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);  
        //设置字体  
        HSSFFont font = workbook.createFont();  
        font.setFontName("宋体");  
        font.setFontHeightInPoints((short) 11); //设置字体大小  
        dataStyle.setFont(font);//选择需要用到的字体格式  
        //设置自动换行  
        dataStyle.setWrapText(true);  
    }  
  
    /** 
     * 创建隐藏页和数据域 
     * @param workbook 
     * @param hideSheetName 
     */  
    public void creatHideSheet(HSSFWorkbook workbook,String hideSheetName){  
        HSSFSheet hideselectinfosheet = workbook.createSheet(hideSheetName);//隐藏一些信息  
        //1.查询所有的系统名称，作为Excel的名称管理  
        getAllEnableInfoSystemList();  
        if(systemList != null && systemList.size() > 0){  
            for (InformationSystem sys : systemList) {  
                this.systemNames.add(sys.getName().trim() + "_id" + sys.getId());  
            }  
        }  
  
        if(this.systemNames != null && this.systemNames.size() > 0){  
            //系统，在隐藏页设置选择信息  
            HSSFRow provinceRow = hideselectinfosheet.createRow(0);  
            this.creatRow(provinceRow, this.systemNames);  
            //子系统，在隐藏页设置选择信息  
            List<String> systemSonNames;  
            for (int i = 0; i < this.systemNames.size(); i++) {  
                systemSonNames = this.findSon(this.systemNames.get(i).split("_id")[1]);  
                systemSonNames.add(0, this.systemNames.get(i));  
  
                HSSFRow zjProvinceRow = hideselectinfosheet.createRow(i+1);  
                this.creatRow(zjProvinceRow, systemSonNames);  
            }  
        }  
  
        //设置隐藏页标志  
        workbook.setSheetHidden(workbook.getSheetIndex(hideSheetName), false);  
    }  
  
    /**获取所有信息系统数据**/  
    private void getAllEnableInfoSystemList() {  
        for (int i = 1; i < 11; i++) {  
            InformationSystem infoSys = new InformationSystem();  
            infoSys.setId(i);  
            infoSys.setName("系统");  
            systemList.add(infoSys);  
        }  
    }  
    /** 
     * 创建一列数据 
     * @param currentRow 
     * @param textList 
     */  
    public void creatRow(HSSFRow currentRow,List<String> textList){  
        if(textList!=null&&textList.size()>0){  
            int i = 0;  
            for(String cellValue : textList){  
                HSSFCell userNameLableCell = currentRow.createCell(i++);  
                userNameLableCell.setCellValue(cellValue);  
            }  
        }  
    }  
  
    /** 
     * 找到系统下的子系统 
     * @param fatherId 
     * @return 
     */  
    private List<String> findSon(String fatherId){  
        List<String> sonNames = new ArrayList<String>();  
        for (int i = 1; i < 3; i++) {  
            sonNames.add("子系统_" + fatherId + i);  
        }  
        return sonNames;  
    }  
  
    /** 
     * 名称管理 
     * @param workbook 
     * @throws FrameworkException  
     */  
    public void creatExcelNameList(HSSFWorkbook workbook){  
        if(systemList != null && systemList.size() > 0){  
            //名称管理  
            Name name;  
            name = workbook.createName();  
            name.setNameName("sysytemInfo");  
            name.setRefersToFormula("hideselectinfosheet!$A$1:$"+this.getcellColumnFlag(systemList.size())+"$1");  
  
            if(this.systemNames != null && this.systemNames.size() > 0){  
                //子系统，在隐藏页设置选择信息  
                List<String> systemSonNames;  
                for (int i = 0; i < this.systemNames.size(); i++) {  
                    systemSonNames = this.findSon(this.systemNames.get(i).split("id")[1]);  
                    systemSonNames.add(0, this.systemNames.get(i));  
  
                    name = workbook.createName();  
                    //父级系统  
                    name.setNameName(this.systemNames.get(i));  
                    //子系统的范围  
                    name.setRefersToFormula("hideselectinfosheet!$B$"+(i+2)+":$"+this.getcellColumnFlag(systemSonNames.size())+"$"+(i+2));  
                }  
            }  
        }  
    }  
  
    //根据数据值确定单元格位置（比如：28-AB）  
    private String getcellColumnFlag(int num) {  
        String columFiled = "";  
        int chuNum = 0;  
        int yuNum = 0;  
        if(num >= 1 && num <= 26){  
            columFiled = this.doHandle(num);  
        }else{  
            chuNum = num / 26;  
            yuNum = num % 26;  
  
            columFiled +=  this.doHandle(chuNum);  
            columFiled +=  this.doHandle(yuNum);  
        }  
        return columFiled;  
    }  
  
    private String doHandle(final int num) {  
        String[] charArr = {"A","B","C","D","E","F","G","H","I","J","K","L","M"  
                           ,"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};  
        return charArr[num-1].toString();  
    }  
      
    /** 
     * 创建一列应用列头 
     * @param userinfosheet1 
     * @param userName 
     */  
    public void creatAppRowHead(HSSFSheet userinfosheet1,int naturalRowIndex){  
        HSSFRow row = userinfosheet1.createRow(naturalRowIndex-1);  
  
        //0.序号  
        HSSFCell serialNumberCell = row.createCell(0);  
        serialNumberCell.setCellValue("序号");  
        serialNumberCell.setCellStyle(titleStyle);  
  
        //1.标题  
        HSSFCell titleCell = row.createCell(1);  
        titleCell.setCellValue("标题");  
        titleCell.setCellStyle(titleStyle);  
  
        //2.提出时间  
        HSSFCell proposeTimeCell = row.createCell(2);  
        proposeTimeCell.setCellValue("提出时间");  
        proposeTimeCell.setCellStyle(titleStyle);  
  
        //3.所属信息系统  
        HSSFCell systemCell = row.createCell(3);  
        systemCell.setCellValue("所属信息系统");  
        systemCell.setCellStyle(titleStyle);  
  
        //4.所属分组  
        HSSFCell groupCell = row.createCell(4);  
        groupCell.setCellValue("所属分组");  
        groupCell.setCellStyle(titleStyle);  
    }  
      
    /** 
     * 创建一列应用数据 
     * @param userinfosheet1 
     * @param userName 
     */  
    public void creatAppRow(HSSFSheet userinfosheet1,int num,String titels,int naturalRowIndex){  
        //构造一个信息输入表单，用户姓名，出生省份，出生城市  
        //要求省份是可以下拉选择的，出生城市根据所选择的省份级联下拉选择  
        //在第一行第一个单元格，插入下拉框  
        HSSFRow row = userinfosheet1.createRow(naturalRowIndex-1);  
  
        //0.序号  
        HSSFCell serialNumberCell = row.createCell(0);  
        serialNumberCell.setCellValue(num-1+"");  
        serialNumberCell.setCellStyle(dataStyle);  
  
        //1.标题  
        HSSFCell titelCell = row.createCell(1);  
        titelCell.setCellValue(titels);  
        titelCell.setCellStyle(dataStyle);  
  
        //2.提出时间  
        HSSFCell proposeTimeCell = row.createCell(2);  
        dataStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd"));  
        proposeTimeCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());  
        proposeTimeCell.setCellStyle(dataStyle);  
  
        //3.所属信息系统  
        HSSFCell systemCell = row.createCell(3);  
        systemCell.setCellValue("请选择");  
        systemCell.setCellStyle(dataStyle);  
  
        //4.所属分组  
        HSSFCell groupCell = row.createCell(4);  
        groupCell.setCellValue("请选择");  
        groupCell.setCellStyle(dataStyle);  
  
        //得到验证对象    
        DataValidation data_validation_list = this.getDataValidationByFormula("sysytemInfo",naturalRowIndex,4); //从1开始下拉框处于第几列  
        //工作表添加验证数据    
        userinfosheet1.addValidationData(data_validation_list);  
        DataValidation data_validation_list2 = this.getDataValidationByFormula("INDIRECT($D"+naturalRowIndex+")",naturalRowIndex,5);  
        //工作表添加验证数据    
        userinfosheet1.addValidationData(data_validation_list2);  
    }  
      
    /** 
     * 使用已定义的数据源方式设置一个数据验证 
     * @param formulaString 
     * @param naturalRowIndex 
     * @param naturalColumnIndex 
     * @return 
     */  
    public DataValidation getDataValidationByFormula(String formulaString,int naturalRowIndex,int naturalColumnIndex){  
        //加载下拉列表内容    
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaString);   
        //设置数据有效性加载在哪个单元格上。    
        //四个参数分别是：起始行、终止行、起始列、终止列    
        int firstRow = naturalRowIndex-1;  
        int lastRow = naturalRowIndex-1;  
        int firstCol = naturalColumnIndex-1;  
        int lastCol = naturalColumnIndex-1;  
        CellRangeAddressList regions=new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);    
        //数据有效性对象   
        DataValidation data_validation_list = new HSSFDataValidation(regions,constraint);  
        return data_validation_list;    
    }  
  
    /** 
     * 生成导出下拉框excel 
     * @param outPathStr 输出路径 
     */  
    public ExportComboxExcel(String outPathStr) {  
        try {  
            workbook = new HSSFWorkbook();//excel文件对象    
            HSSFSheet sheet1 = workbook.createSheet("sheet1");//工作表对象  
            //设置列头样式  
            this.setTitleCellStyles(workbook,sheet1);  
            //设置数据样式  
            this.setDataCellStyles(workbook,sheet1);  
            //创建一个隐藏页和隐藏数据集  
            this.creatHideSheet(workbook, "hideselectinfosheet");  
            //设置名称数据集  
            this.creatExcelNameList(workbook);  
            //创建一行列头数据  
            this.creatAppRowHead(sheet1,1);  
            //创建一行数据  
            for (int i = 2; i < 1001; i++) {  
                this.creatAppRow(sheet1, i, "标题......",i);  
            }  
  
            //生成输入文件  
            FileOutputStream out=new FileOutputStream(outPathStr);    
            workbook.write(out);    
            out.close();  
              
            System.out.println("导出成功!");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) {  
        new ExportComboxExcel("d:\\ok.xls");  
    }  
}  