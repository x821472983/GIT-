package com.base.pojo;

import com.base.constants.ConstantsBase;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
    /**
     * @param list
     * @param fileName
     * @return
     * @throws Exception
     * @Description 导出excel文件, 返回导出的excel路径
     * @author Enzo
     * @date 2015-8-21
     */
    public static String expTemplateExcel(List<?> list, String fileName) throws Exception {
        Map<String, Object> beanParams = new HashMap<String, Object>();
        beanParams.put("list", list);
        String webappRoot = System.getProperty(ConstantsBase.WEBAPP_ROOT);
        String destPath = webappRoot + "files" + File.separator + "temp" + File.separator + fileName;// 导出的临时文件目录
        File destFile = new File(destPath);//导出的文件
        if (!destFile.getParentFile().exists()) {//导出的文件夹是否存在
            destFile.getParentFile().mkdirs();//创建导出文件的文件夹
        }
        String templatePath = webappRoot + "files" + File.separator + "xlsExportTemplate" + File.separator + fileName;// 导出的模板目录
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templatePath, beanParams, destPath);// 生成excel文件
        return destPath;
    }

    /**
     * @param
     * @return
     * @throws Exception
     * @Description 导入时解析excel文件（读取excel内容，保存在baseModel的data中）
     * @author Enzo
     * @date 2015-8-26
     */
    public static ArrayList<Map<String, Object>> getListMapWorkBook(MultipartFile[] tempFiles) throws Exception {
        if (tempFiles == null || tempFiles.length < 1) {
            throw new BusinessException("请选择导入的文件");
        }
        ArrayList<Map<String, Object>> list = null;
        MultipartFile tempFile = tempFiles[0];
        if (tempFile.getOriginalFilename().endsWith("xls") || tempFile.getOriginalFilename().endsWith("xlsx")) {// 如果是excel文件
            Workbook book = new HSSFWorkbook(tempFile.getInputStream());// 创建excel文件对象
            Sheet sheet = book.getSheetAt(0);// 获得工作簿对象
            Row row = null;
            list = new ArrayList<Map<String, Object>>();// 定义map对象用于保存读取到的excel文件
            try {
                Row rowTitle = sheet.getRow(0);// 获得表格第一行（标题行）
                String title = rowTitle.getCell(0).getStringCellValue();// 获得标题
                int end = 0;// 1为结束标志
                Row rowField = sheet.getRow(2);// 获得表字段
                for (int i = 4; i <= sheet.getLastRowNum(); i++) {// 循环单元格
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    row = sheet.getRow(i);
                    if (row.getCell(0) == null) {
                        break;
                    }
                    for (int j = 0; j < rowField.getLastCellNum(); j++) {// 循环列
                        if (j == 0 && (row.getCell(j) == null || "".equals(row.getCell(j)))
                                && "count".equals(rowField.getCell(j).getStringCellValue())) { // 如果第一列count并且为空，则不导入
                            end = 1;
                            break;
                        }
                        map.put(rowField.getCell(j).getStringCellValue(), getValue(row.getCell(j)));
                    }
                    if (end == 1) {
                        break;
                    }
                    list.add(map);
                }

            } catch (Exception e) {
                new BusinessException("请选择导入的文件");

            }
        } else {
            throw new BusinessException("请传入正确的文件！");
        }
        return list;
    }

    /**
     * @param cell
     * @return
     * @Description 获取excel单元格内容, 将其转换为String类型
     * @author Enzo
     * @date 2016-1-21
     */
    public static String getValue(Cell cell) {
        String value = "";
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()) {
            // 数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);
                } else {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && item[1].trim().length() > 3) {// 如果小数点后面大于三位，则保留三位小数
                            value = item[0] + "." + item[1].trim().substring(0, 3);
                        }
                    }
                }
                break;
            // 字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                // 读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                value = "";
                // LogUtil.getLogger().error("excel出现空值");
                break;
            // 故障
            case Cell.CELL_TYPE_ERROR:
                value = "";
                // LogUtil.getLogger().error("excel出现故障");
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

}
