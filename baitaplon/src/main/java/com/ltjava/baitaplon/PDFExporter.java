/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.baitaplon;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.User;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HIEN
 */
public class PDFExporter {
    private List<Student> listStudents;
    private Thesis thesis;
    private List<Object[]> listScores;
    private Student student;
     
    public PDFExporter(Thesis thesis, List<Object[]> list,Student student ) {
        this.thesis = thesis;
        this.listScores = list;
        this.student = student;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(8);
         
        Font font = FontFactory.getFont(FontFactory.defaultEncoding);
        font.setColor(Color.BLACK);
         
        cell.setPhrase(new Phrase("STT", font));
        cell.setPadding(8);
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tiêu chí", font));
        cell.setPadding(8);
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Điểm tối đa", font));
        cell.setPadding(8);
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Điểm đánh giá", font));
        cell.setPadding(8);
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
        Integer i = 1;
        Float sumMax = Float.valueOf(0);
        Float sum = Float.valueOf(0);
        for (Object[] obj: listScores) {
            table.addCell(i.toString());
            table.addCell(obj[1].toString());
            table.addCell(obj[2].toString());
            table.addCell(obj[3].toString());
            sumMax += Float.valueOf(obj[2].toString());
            sum += Float.valueOf(obj[3].toString());
            i++;
        }
        table.addCell("");
        table.addCell("Tổng");
        table.addCell(sumMax.toString());
        table.addCell(sum.toString());
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font fontHeader = FontFactory.getFont(FontFactory.defaultEncoding);
        fontHeader.setSize(18);
        fontHeader.setColor(Color.BLACK);
        fontHeader.setStyle(Font.BOLDITALIC);
         
        Paragraph p = new Paragraph("PHIẾU ĐIỂM", fontHeader);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
        
        Font fontContent = FontFactory.getFont(FontFactory.defaultEncoding);
        fontContent.setSize(14);
        fontContent.setColor(Color.BLACK);
        
        document.add(new Paragraph("1.Họ và tên sinh viên: "+ student.getFullName(), fontContent));
        document.add(new Paragraph("2.Mã sinh viên: "+ student.getId(), fontContent));
        document.add(new Paragraph("3.Lớp: "+ student.getClassId().getName(), fontContent));
        document.add(new Paragraph("4.Khoa: "+ student.getClassId().getMajorId().getName(), fontContent));
        document.add(new Paragraph("5.Tên đề tài khóa luận tốt nghiệp: "+ thesis.getTopic(), fontContent));
        
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 4.0f, 3.5f, 3.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
