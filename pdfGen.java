package pdfGenerator.gen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
//import com.itextpdf.layout.element.Cell;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pdfGen {
	static Document document = new Document(PageSize.A4);
	static PdfWriter writer = null;
	static PdfPTable table;
	static PdfPTable table2;

	public static void main(String[] args) throws MalformedURLException, IOException {

		try {
			writer = PdfWriter.getInstance(document,
					new FileOutputStream("/home/nageswararao/Documents/myPdfDemo.pdf"));
			document.open();
			// page:1
			PdfDocumentTop("/home/nageswararao/Downloads/left.jpg", "/home/nageswararao/Downloads/right.jpg",
					"/home/nageswararao/Downloads/middle.jpg", "/home/nageswararao/Downloads/im.jpg");
			
			PdfDocumentBottom("/home/nageswararao/Downloads/left.jpg", "/home/nageswararao/Downloads/right.jpg",
					"/home/nageswararao/Downloads/middle.jpg", "/home/nageswararao/Downloads/im.jpg");

			// table page
			document.newPage();
			Image im1 = Image.getInstance("/home/nageswararao/Downloads/left.jpg");
			im1.scaleToFit(100, 100);
			im1.setAbsolutePosition(28, 795);
			document.add(im1);

			Image im2 = Image.getInstance("/home/nageswararao/Downloads/right.jpg");
			im2.scaleToFit(100, 100);
			im2.setAbsolutePosition(450, 790);
			document.add(im2);

			PdfContentByte canvas = writer.getDirectContent();
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			canvas.beginText();
			canvas.setFontAndSize(bf, 12);

			canvas.showTextAligned(Element.ALIGN_CENTER, "58.4 Li-Ion Perfomance Test Certificate", 300, 720, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Dtae:" + LocalDate.now(), 32, 690, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Model No:", 32, 670, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Pack Cpacity:", 32, 650, 0);

			canvas.showTextAligned(Element.ALIGN_LEFT, "Location:", 230, 690, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Battery Pack Id:", 230, 670, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "IR Gradient:", 230, 650, 0);

			canvas.showTextAligned(Element.ALIGN_LEFT, "Tested at:", 420, 690, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Status:", 420, 670, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Summary", 240, 600, 0);
			canvas.endText();

			// table
			table = new PdfPTable(4); // 5 columns.
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setWidthPercentage(80); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			float[] columnWidths = { 1f, 1f, 1f, 1f };
			table.setWidths(columnWidths);
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Cell ID", boldFont));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER); cell1.setFixedHeight(25);
			table.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Paragraph("Capacity(AH)", boldFont));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell2);
			PdfPCell cell3 = new PdfPCell(new Paragraph("IR", boldFont));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			PdfPCell cell4 = new PdfPCell(new Paragraph("Status", boldFont));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell4);

			for (int i = 1; i <= 10; i++) {
				insertTable("1", "1", "1", "1");
			}

			table.setTotalWidth(100);
			table.setLockedWidth(true);
			table.setTotalWidth(document.right(document.rightMargin()) - document.left(document.leftMargin()));
			table.writeSelectedRows(0, -1, 65, 590, writer.getDirectContent());

			// Last Table Page
			document.newPage();
			document.setPageSize(PageSize.LETTER.rotate());
			im1.setAbsolutePosition(28, 790);
			document.add(im1);
			im2.setAbsolutePosition(100, 790);
			document.add(im2);
			
			
			Image longimg = Image.getInstance("/home/nageswararao/Downloads/long.jpg");
			longimg.scaleToFit(550, 350);
			longimg.setAbsolutePosition(30, 600);
			document.add(longimg);
			
			Image field = Image.getInstance("/home/nageswararao/Downloads/field.jpg");
			field.scaleToFit(250, 100);
			field.setAbsolutePosition(300, 780);
			document.add(field);
			
			

			table2 = new PdfPTable(17);
			
			table2.setWidthPercentage(100);
			table2.setTotalWidth(550);
		
			
			table2.setHorizontalAlignment(Element.ALIGN_CENTER);
			float[] colWidths = { 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
					0.1f, 0.1f, 0.1f };
			table2.setWidths(colWidths);

			PdfPCell cel0 = new PdfPCell(new Paragraph(" ", boldFont));
			cel0.setHorizontalAlignment(Element.ALIGN_CENTER);
			 cel0.setBorderColor(BaseColor.WHITE);
			 cel0.setFixedHeight(25);
			table2.addCell(cel0);
			PdfPCell cel1 = new PdfPCell(new Paragraph("Block1", boldFont));
			cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 cel1.setFixedHeight(0.1f);
			table2.addCell(cel1);
			PdfPCell cel2 = new PdfPCell(new Paragraph("Block2", boldFont));
			cel2.setHorizontalAlignment(Element.ALIGN_CENTER); cel2.setFixedHeight(0.1f);
			table2.addCell(cel2);
			PdfPCell cel3 = new PdfPCell(new Paragraph("Block3", boldFont));
			cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel3);
			PdfPCell cel4 = new PdfPCell(new Paragraph("Block4", boldFont));
			cel4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel4);
			PdfPCell cel5 = new PdfPCell(new Paragraph("Block5", boldFont));
			cel5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel5);
			PdfPCell cel6 = new PdfPCell(new Paragraph("Block6", boldFont));
			cel6.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel6);
			PdfPCell cel7 = new PdfPCell(new Paragraph("Block7", boldFont));
			cel7.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel7);
			PdfPCell cel8 = new PdfPCell(new Paragraph("Block8", boldFont));
			cel8.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel8);
			PdfPCell cel9 = new PdfPCell(new Paragraph("Block9", boldFont));
			cel9.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel9);
			PdfPCell cel10 = new PdfPCell(new Paragraph("Block10", boldFont));
			cel10.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel10);

			PdfPCell cel11 = new PdfPCell(new Paragraph("Block11", boldFont));
			cel11.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel11);
			PdfPCell cel12 = new PdfPCell(new Paragraph("Block12", boldFont));
			cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel12);
			PdfPCell cel13 = new PdfPCell(new Paragraph("Block13", boldFont));
			cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel13);
			PdfPCell cel14 = new PdfPCell(new Paragraph("Block14", boldFont));
			cel4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel14);
			PdfPCell cel15 = new PdfPCell(new Paragraph("Block15", boldFont));
			cel5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel15);
			PdfPCell cel16 = new PdfPCell(new Paragraph("Block16", boldFont));
			cel6.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cel16);

			for(int i=0;i<=5;i++) {
				insertFinalTable("head",  "1234.5", "b2","b3","b4","b5", " b6",
						"b7","b8","b9","b10", "b11", "b12","b13","b14","b15",
						"b16"); 
				
			}
			
			
			table2.setLockedWidth(true);
//			table2.setTotalWidth(document.right(document.rightMargin()) - document.left(document.leftMargin()));
			table2.writeSelectedRows(0, -1, 20, 560, writer.getDirectContent());
			
			canvas.beginText();
			canvas.setFontAndSize(bf, 12);
			canvas.showTextAligned(Element.ALIGN_CENTER, "Recovery", 50, 300, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Resting pack Voltage:" , 150, 300, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Loaded pack Voltage:", 150, 275, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Recovery pack Voltage:", 150, 250, 0);
			canvas.showTextAligned(Element.ALIGN_LEFT, "Corrent:", 420, 300, 0);
			canvas.endText();
			
			
			System.out.println("created");
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	public static void PdfDocumentTop(String leftLable, String rightLable, String pass, String graph)
			throws MalformedURLException, IOException, DocumentException {

		PdfContentByte canvas = writer.getDirectContent();
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		canvas.beginText();
		canvas.setFontAndSize(bf, 12);
		canvas.showTextAligned(Element.ALIGN_LEFT, "Serial No.", 32, 780, 0);
		canvas.showTextAligned(Element.ALIGN_LEFT, "Dtae:" + LocalDate.now(), 32, 765, 0);
		canvas.showTextAligned(Element.ALIGN_LEFT, "cellCapacity:", 32, 750, 0);
		canvas.endText();

		Image im1 = Image.getInstance(leftLable);
		im1.scaleToFit(100, 100);
		im1.setAbsolutePosition(28, 795);
		document.add(im1);

		Image im2 = Image.getInstance(rightLable);
		im2.scaleToFit(100, 100);
		im2.setAbsolutePosition(450, 790);
		document.add(im2);

		Image im3 = Image.getInstance(pass);
		im3.scaleToFit(150, 100);
		im3.setAbsolutePosition(220, 745);
		document.add(im3);

		Image im = Image.getInstance(graph);
		im.scaleToFit(600, 5000);
		im.setAbsolutePosition(8, 470);
		document.add(im);

	}
	
	public static void PdfDocumentBottom(String leftLable, String rightLable, String pass, String graph)
			throws MalformedURLException, IOException, DocumentException {

		PdfContentByte canvas = writer.getDirectContent();
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		canvas.beginText();
		canvas.setFontAndSize(bf, 12);
		canvas.showTextAligned(Element.ALIGN_LEFT, "Serial No.", 32, 375, 0);
		canvas.showTextAligned(Element.ALIGN_LEFT, "Dtae:" + LocalDate.now(), 32, 360, 0);
		canvas.showTextAligned(Element.ALIGN_LEFT, "cellCapacity:", 32, 345, 0);
		canvas.endText();

		Image im1 = Image.getInstance(leftLable);
		im1.scaleToFit(100, 100);
		im1.setAbsolutePosition(28, 390);
		document.add(im1);

		Image im2 = Image.getInstance(rightLable);
		im2.scaleToFit(100, 100);
		im2.setAbsolutePosition(450, 385);
		document.add(im2);

		Image im3 = Image.getInstance(pass);
		im3.scaleToFit(150, 100);
		im3.setAbsolutePosition(220, 340);
		document.add(im3);

		Image im = Image.getInstance(graph);
		im.scaleToFit(600, 5000);
		im.setAbsolutePosition(8, 90);
		document.add(im);
		document.newPage();

	}
	

	public static void insertTable(String id, String capacity, String ir, String status) {

		PdfPCell cell1 = new PdfPCell(new Paragraph(id));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER); cell1.setFixedHeight(25);
		table.addCell(cell1);
		PdfPCell cell2 = new PdfPCell(new Paragraph(capacity));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell2);
		PdfPCell cell3 = new PdfPCell(new Paragraph(ir));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell3);
		PdfPCell cell4 = new PdfPCell(new Paragraph(status));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell4);

	}

	public static void insertFinalTable(String head, String b1, String b2, String b3, String b4, String b5, String b6,
			String b7, String b8, String b9, String b10, String b11, String b12, String b13, String b14, String b15,
			String b16) {
		
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10);
		Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		
		PdfPCell cel0 = new PdfPCell(new Paragraph(head,boldFont1));
		cel0.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table2.addCell(cel0);
		PdfPCell cel1 = new PdfPCell(new Paragraph(b1,boldFont));
		cel1.setHorizontalAlignment(Element.ALIGN_CENTER); cel1.setFixedHeight(25);
		table2.addCell(cel1);
		PdfPCell cel2 = new PdfPCell(new Paragraph(b2,boldFont));
		cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel2);
		PdfPCell cel3 = new PdfPCell(new Paragraph(b3,boldFont));
		cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel3);
		PdfPCell cel4 = new PdfPCell(new Paragraph(b4,boldFont));
		cel4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel4);
		PdfPCell cel5 = new PdfPCell(new Paragraph(b5,boldFont));
		cel5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel5);
		PdfPCell cel6 = new PdfPCell(new Paragraph(b6,boldFont));
		cel6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel6);
		PdfPCell cel7 = new PdfPCell(new Paragraph(b7,boldFont));
		cel7.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel7);
		PdfPCell cel8 = new PdfPCell(new Paragraph(b8,boldFont));
		cel8.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel8);
		PdfPCell cel9 = new PdfPCell(new Paragraph(b9,boldFont));
		cel9.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel9);
		PdfPCell cel10 = new PdfPCell(new Paragraph(b10,boldFont));
		cel10.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel10);
		PdfPCell cel11 = new PdfPCell(new Paragraph(b11,boldFont));
		cel11.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel11);
		PdfPCell cel12 = new PdfPCell(new Paragraph(b12,boldFont));
		cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel12);
		PdfPCell cel13 = new PdfPCell(new Paragraph(b13,boldFont));
		cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel13);
		PdfPCell cel14 = new PdfPCell(new Paragraph(b14,boldFont));
		cel4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel14);
		PdfPCell cel15 = new PdfPCell(new Paragraph(b15,boldFont));
		cel5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel15);
		PdfPCell cel16 = new PdfPCell(new Paragraph(b16,boldFont));
		cel6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cel16);

	}

}
