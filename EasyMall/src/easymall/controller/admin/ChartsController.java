package easymall.controller.admin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Black;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import easymall.po.Category;
import easymall.po.Chart;
import easymall.po.Products;
import easymall.service.ProductsService;

@Controller("chartsController")
@RequestMapping("/charts")
public class ChartsController {
	@Autowired
	private ProductsService productsService;
	@RequestMapping("/sales")
	public String sales(Model model) {
		List<Category> categorys=productsService.allcategorys();
		model.addAttribute("categorys",categorys);
		List<Products> products;
		return "echartsTest";
	}
	@RequestMapping("/soldnum")
	@ResponseBody
	 public Chart soldnum() {
	  List<Category> categories=productsService.allcategorys();
	  List<Products> products=productsService.allProducts();
	  Chart chart = new Chart();
	  chart.setCategories(categories);
	  chart.setProducts(products);
	  return chart;
	 }
	@RequestMapping("/testJson")
	@ResponseBody
	public List<Category> testJson() {
		List<Category> categories=productsService.allcategorys();
		return categories;
	}
	@RequestMapping("/getCatenum")
	@ResponseBody
	public List<Integer> getCatenum(){
		List<Category> categories=productsService.allcategorys();
		List<Products> products=productsService.allProducts();
		//ArrayList<Integer> catecount=new ArrayList<>();
		List<Integer> catecount = new ArrayList<Integer>();
		int[] arr1=new int[7];
		arr1[products.get(0).getCategory()-1]=products.get(0).getSoldnum();
		for(int i=1;i<products.size();i++){
			arr1[products.get(i).getCategory()-1]=arr1[products.get(i-1).getCategory()-1]+products.get(i).getSoldnum();
		}
		for(int i=0;i<7;i++){
			catecount.add(arr1[i]);
		}
		return catecount;
	}
	@RequestMapping("/getexcel")
	@ResponseBody
	public String getexcel() throws Exception {
		List<Category> categories=productsService.allcategorys();
		List<Products> products=productsService.allProducts();
		List<Integer> procount = new ArrayList<Integer>();
		ArrayList<String> rowname = new ArrayList<String> (); 
		
		//1.????workbook??????
		XSSFWorkbook workbook=new XSSFWorkbook();
		//2.????????sheet
		XSSFSheet sheet=workbook.createSheet("sheet1");
		//3.??????
		FileOutputStream fout=new FileOutputStream("D:\\????????.xlsx");
		//4.??????????????????0????
		Row row=sheet.createRow(0);
		//5.??????????
		Cell cell=row.createCell(0);
		cell.setCellValue("????????");
//		
		XSSFRow row2=sheet.createRow(1);   
         //??????????????????????????
		for(int i=0;i<products.size();i++){
			row2.createCell(i).setCellValue(products.get(i).getName());
		}
         //??sheet????????????
         XSSFRow row3=sheet.createRow(2);
         for(int i=0;i<products.size();i++){
        	 row3.createCell(i).setCellValue(products.get(i).getSoldnum());
         }

		//6.??????????????
		CellStyle cellStyle=workbook.createCellStyle();
		//????????(top,bottom,left,right)
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());		
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		//????????
		Font font=workbook.createFont();
		font.setFontName("????????");//????????????excel????????
		font.setFontHeightInPoints((short) 32);//????????????
		cellStyle.setFont(font);
		//????????
		CellRangeAddress region=new CellRangeAddress(0,0,0,products.size()-1);//(firstRow,lastRow,firstCol,lastCol)
		sheet.addMergedRegion(region);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//????????
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//????????	
		//????????
		cell.setCellStyle(cellStyle);
		//7.????????
		//FileInputStream fin=new FileInputStream();
		//????????
		workbook.write(fout);
		fout.close();
		return "Export excel successfully!";
	}

}
