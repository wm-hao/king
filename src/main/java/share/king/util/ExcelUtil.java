package share.king.util;

import com.alibaba.fastjson.JSON;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import share.king.comparator.TradeRecordComparator;
import share.king.entity.TradeRecord;
import share.king.exception.BaseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    private static Log logger = LogFactory.getLog(ExcelUtil.class);

    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     *
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<TradeRecord> parseExcel2TradeRecord(String fileName) throws BaseException {
        List<TradeRecord> resultDataList = new ArrayList<>();
        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                logger.warn("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            resultDataList = doParseExcel2TradeRecord(workbook);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BaseException(e.getMessage());
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                String errMsg = "关闭数据流出错！错误信息：" + e.getMessage();
                logger.error(errMsg);
            }
        }
        return resultDataList;
    }

    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<TradeRecord> doParseExcel2TradeRecord(Workbook workbook) throws BadHanyuPinyinOutputFormatCombination {
        List<TradeRecord> resultDataList = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            logger.error("第一行:" + firstRowNum);
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warn("解析Excel失败，在第一行没有读取到任何数据！");
            }

            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = firstRowNum; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }

                resultDataList.add(convertRowToData(row));
            }
        }

        return parseToCorrectData(resultDataList);
    }

    /**
     * 将单元格内容转换为字符串
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                returnValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                break;
            case STRING:
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                boolean booleanValue = cell.getBooleanCellValue();
                returnValue = String.valueOf(booleanValue);
                break;
            case BLANK:
                break;
            case FORMULA:
                returnValue = cell.getCellFormula();
                break;
            case ERROR:
                break;
            default:
                break;
        }
        if (returnValue != null) {
            return returnValue.trim().replaceAll("\\t", "");
        }
        return null;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     */
    private static TradeRecord convertRowToData(Row row) throws BadHanyuPinyinOutputFormatCombination {
        TradeRecord resultData = new TradeRecord();

        Cell cell;
        int cellNum = 0;
        cell = row.getCell(cellNum++);
        String date = convertCellValueToString(cell);
        cell = row.getCell(cellNum++);
        String time = convertCellValueToString(cell);
        Date tradeTime = TimeUtil.getTimestampByFormat(date + time, TimeUtil.yyyyMMddHH_mm_ss);
        cell = row.getCell(cellNum++);
        String name = convertCellValueToString(cell);
        cell = row.getCell(cellNum++);
        String code = convertCellValueToString(cell);
        cell = row.getCell(cellNum++);
        String optType = convertCellValueToString(cell);
        cell = row.getCell(cellNum++);
        cell = row.getCell(cellNum++);
        cell = row.getCell(cellNum++);
        String count = convertCellValueToString(cell);
        cell = row.getCell(cellNum);
        String price = convertCellValueToString(cell);
        boolean by = optType.contains("买入");
        int buyOrSellCount = (int) (Math.abs(Double.parseDouble(count)));
        int buyOrSellPrice = (int) (Double.parseDouble(price) * 1000);
        if (by) {
            resultData.setState(1);
            resultData.setBuyPrice(buyOrSellPrice);
            resultData.setBuyTime(tradeTime);
        } else {
            resultData.setState(0);
            resultData.setSellPrice(buyOrSellPrice);
            resultData.setSellTime(tradeTime);
        }
        resultData.setBuyCount(buyOrSellCount);
        resultData.setName(name);
        resultData.setCode(code);
        resultData.setCreateDate(tradeTime);
        resultData.setAlias(PinyinUtil.getPinYinHeadChar(name));
        return resultData;
    }

    private static List<TradeRecord> parseToCorrectData(List<TradeRecord> originData) {
        List<TradeRecord> finalRecords = new ArrayList<>();
        List<TradeRecord> buyRecords = new ArrayList<>();
        List<TradeRecord> sellRecords = new ArrayList<>();
        for (TradeRecord tradeRecord : originData) {
            int hand = Math.abs(tradeRecord.getBuyCount()) / 100;
            for (int i = 0; i < hand; i++) {
                tradeRecord.setBuyCount(100);
                TradeRecord newTradeRecord = JSON.parseObject(JSON.toJSONString(tradeRecord), TradeRecord.class);
                if (tradeRecord.getState() > 0) {
                    buyRecords.add(newTradeRecord);
                } else {
                    sellRecords.add(newTradeRecord);
                }
            }
        }
        Collections.sort(buyRecords, new TradeRecordComparator());
        Collections.sort(sellRecords, new TradeRecordComparator());
        for (TradeRecord buyRecord : buyRecords) {
            for (TradeRecord sellRecord : sellRecords) {
                if (sellRecord.getState() != 999 && buyRecord.getCode().equals(sellRecord.getCode())) {
                    buyRecord.setSellTime(sellRecord.getSellTime());
                    buyRecord.setSellPrice(sellRecord.getSellPrice());
                    sellRecord.setState(999);
                    break;
                }
            }
            finalRecords.add(buyRecord);
        }
        return finalRecords;
    }
}
