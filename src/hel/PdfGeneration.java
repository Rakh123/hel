package hel;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGeneration {

	public static void main(String ar[]) throws ClassNotFoundException {

		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("/home/nageswararao/Documents/AddTableExample.pdf"));
			document.open();
			Paragraph p = new Paragraph();
			p.add("Casual_Question");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			PdfPTable table = new PdfPTable(5); // 5 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 0.1f, 1f, 0.1f, 0.1f, 0.1f };
			table.setWidths(columnWidths);
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

			DBConnection obj_DBConnection = new DBConnection();
			Connection connection = obj_DBConnection.getConnection();
			String query = "select * from info";
			Statement stmt = null;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			PdfPCell cell0 = new PdfPCell(new Paragraph("Sno", boldFont));
			cell0.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cell01 = new PdfPCell(new Paragraph("QUESTIONS", boldFont));
			cell01.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell02 = new PdfPCell(new Paragraph("YES", boldFont));
			cell02.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cell03 = new PdfPCell(new Paragraph("NO", boldFont));
			cell03.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cell04 = new PdfPCell(new Paragraph("NA", boldFont));
			cell03.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell0);
			table.addCell(cell01);
			table.addCell(cell02);
			table.addCell(cell03);
			table.addCell(cell04);
			String FONT = "/home/nageswararao/Documents/pdf_fonts/dejavu-sans//DejaVuSans.ttf";
			FontSelector selector = new FontSelector();
			BaseFont base = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			selector.addFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
			selector.addFont(new Font(base, 12));
			char tickSymbol = 10003;
			String text = String.valueOf(tickSymbol);
			Phrase ph = selector.process(text);

			while (rs.next()) {
//	                

				PdfPCell cell1 = new PdfPCell(new Paragraph(rs.getString("Sno")));
				cell1.setVerticalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell1);
				// table.addCell(new PdfPCell(new
				// Paragraph("NO"))).setBorderColor(BaseColor.WHITE)
				PdfPCell cell2 = new PdfPCell(new Paragraph(rs.getString("Quetion")));
				cell2.setVerticalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell2);

				if (rs.getString("answer").equalsIgnoreCase("yes")) {
					PdfPCell cell3 = new PdfPCell(new Paragraph(ph));
					cell3.setVerticalAlignment(Element.ALIGN_LEFT);

					PdfPCell cell4 = new PdfPCell(new Paragraph(" "));
					cell4.setVerticalAlignment(Element.ALIGN_LEFT);

					PdfPCell cell5 = new PdfPCell(new Paragraph(" "));
					cell5.setVerticalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
				} else if (rs.getString("answer").equalsIgnoreCase("NO")) {

					PdfPCell cell4 = new PdfPCell(new Paragraph(ph));
					cell4.setVerticalAlignment(Element.ALIGN_LEFT);

					PdfPCell cell3 = new PdfPCell(new Paragraph(" "));
					cell3.setVerticalAlignment(Element.ALIGN_LEFT);

					PdfPCell cell5 = new PdfPCell(new Paragraph(" "));
					cell5.setVerticalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
				} else {

					PdfPCell cell5 = new PdfPCell(new Paragraph(ph));
					cell5.setVerticalAlignment(Element.ALIGN_LEFT);
					PdfPCell cell3 = new PdfPCell(new Paragraph(" "));
					cell3.setVerticalAlignment(Element.ALIGN_LEFT);
					PdfPCell cell4 = new PdfPCell(new Paragraph(" "));
					cell3.setVerticalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);

				}

			}
			document.add(table);
			System.out.println("done");

			document.close();
			writer.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
