package hel;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
	public class Generate {
	    public static void main(String[] args) throws IOException, DocumentException{
	        String FILE_NAME = "/home/nageswararao/Documents/info_file.pdf";
	        Document document = new Document();
	        try {
	        	
	        	
	        	 PdfWriter writer=   PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
	            document.open();
	            Paragraph p = new Paragraph();
	            p.add("Casual_Question");
	            p.setAlignment(Element.ALIGN_CENTER);
	            document.add(p);
	            Paragraph p2 = new Paragraph(String.valueOf('\u00FE'));
	            p2.add("here is quetions");
	            //no alignment
	            p2.add("                                                                "+"yes   no");
	           
	            document.add(p2);
	            Font f = new Font();
	            f.setStyle(Font.BOLD);
	            f.setSize(4);
	            Paragraph pq = new Paragraph();
	            pq.add("\n");
	            DBConnection obj_DBConnection = new DBConnection();
	            Connection connection = obj_DBConnection.getConnection();
	            String query = "select Quetion ,Qno from info";
	            Statement stmt = null;
	            stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            Paragraph p3 = null;
	            while (rs.next()) {
	                p3 = new Paragraph();  p3.add(rs.getString("Qno")); p3.add(".");
	                p3.add(rs.getString("Quetion"));
	                document.add(p3);
	                RadioCheckField checkbox = new RadioCheckField(writer, new Rectangle(10, 10, 200, 200), "a-name", "Yes");
	                checkbox.setCheckType(RadioCheckField.TYPE_CHECK);
	                checkbox.setChecked(true);
	                checkbox.setBorderWidth(BaseField.BORDER_WIDTH_THICK);
	                checkbox.setBackgroundColor(CMYKColor.BLACK);
	                checkbox.setBackgroundColor(CMYKColor.WHITE);
	               
	               writer.addAnnotation(checkbox.getCheckField());
	            }
	            document.close();
	            System.out.println("Done");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


