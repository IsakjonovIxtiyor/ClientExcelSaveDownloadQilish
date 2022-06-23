package com.example.clientexcelsavedownloadqilish.Service;

import com.example.clientexcelsavedownloadqilish.Entity.Client;
import com.example.clientexcelsavedownloadqilish.Repository.ClientRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepository clientRepository;

    // EXCEL FILE DAN DATABASE IMPORT QILADI

    public List<Client> ReadDataFromExcel(MultipartFile file) throws IOException {

        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Client client = new Client();

                Row row = worksheet.getRow(index);
                client.setID((long) row.getCell(1).getNumericCellValue());
                client.setBRANCH(row.getCell(2).getStringCellValue());
                client.setIdClient( row.getCell(3).getStringCellValue());
                client.setName(row.getCell(4).getStringCellValue());
                client.setCodeCountry( row.getCell(5).getStringCellValue());
                client.setCodeType( row.getCell(6).getStringCellValue());
                client.setCodeResident(row.getCell(7).getStringCellValue());
                client.setCodeSubject(row.getCell(8).getStringCellValue());
                client.setCodeFrom( row.getCell(9).getStringCellValue());
//                client.setCodeOpen(row.getCell(10).getStringCellValue());
//                client.setCodeOpen(row.getCell(10).getStringCellValue());
                client.setState(row.getCell(12).getNumericCellValue());
                client.setKodErr(row.getCell(13).getNumericCellValue());
                client.setFillName(row.getCell(14).getStringCellValue());
                client.setSignRegister( row.getCell(15).getNumericCellValue());
                client.setCrmId( row.getCell(16).getStringCellValue());
                client.setSubbranch(row.getCell(17).getStringCellValue());

                clientRepository.save(client);
            }
        }

        return null;

    }

    // Postgresql dagi data excelga kochiradi!!!!!!!!!!!!!!

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ByteArrayInputStream Download(List<Client> clients) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Clients");

            Row row = sheet.createRow(0);

            // Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("ID");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("BRANCH");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("ID_CLIENT");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("NAME");
            cell.setCellStyle(headerCellStyle);


            cell = row.createCell(4);
            cell.setCellValue("CODE_COUNTRY");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("CODE_TYPE");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("CODE_REGISTER");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("CODE_SUBJECT");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(8);
            cell.setCellValue("CODE_FROM");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(9);
            cell.setCellValue("DATE_OPEN");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(10);
            cell.setCellValue("DATE_CLOSE");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(11);
            cell.setCellValue("STATE");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(12);
            cell.setCellValue("KOD_ERR");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(13);
            cell.setCellValue("FILL_NAME");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(14);
            cell.setCellValue("SIGN_REGISTER");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(15);
            cell.setCellValue("CRM_ID");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(16);
            cell.setCellValue("SUBBRANCH");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows for each client
            for (int i = 0; i < clients.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(clients.get(i).getID());
                dataRow.createCell(1).setCellValue(clients.get(i).getBRANCH());
                dataRow.createCell(2).setCellValue(clients.get(i).getClientId());
                dataRow.createCell(3).setCellValue(clients.get(i).getName());
                dataRow.createCell(4).setCellValue(clients.get(i).getCodeCountry());
                dataRow.createCell(5).setCellValue(clients.get(i).getCodeType());
                dataRow.createCell(6).setCellValue(clients.get(i).getCodeResident());
                dataRow.createCell(7).setCellValue(clients.get(i).getCodeSubject());
                dataRow.createCell(8).setCellValue(clients.get(i).getCodeFrom());
//                dataRow.createCell(9).setCellValue(clients.get(i).getDateOpen());
//                dataRow.createCell(10).setCellValue(clients.get(i).getDateClose());
                dataRow.createCell(11).setCellValue(clients.get(i).getState());
                dataRow.createCell(12).setCellValue(clients.get(i).getKodErr());
                dataRow.createCell(13).setCellValue(clients.get(i).getFillName());
                dataRow.createCell(14).setCellValue(clients.get(i).getSignRegister());
                dataRow.createCell(15).setCellValue(clients.get(i).getCrmId());
                dataRow.createCell(16).setCellValue(clients.get(i).getSubbranch());

            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }


}
