package hel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class reader {
	
	public void parse(String filename) throws IOException {
		  PdfReader reader = new PdfReader("home/nageswararao/Downloads/Sampledeclaration.pdf");
		  Rectangle rect = new Rectangle(36, 750, 559, 806);
		  RenderFilter regionFilter = new RegionTextRenderFilter(rect);
		  FontRenderFilter fontFilter = new FontRenderFilter();
		  TextExtractionStrategy strategy = new FilteredTextRenderListener(
		      new LocationTextExtractionStrategy(), regionFilter, fontFilter);
		  System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
		  reader.close();
		}
	//Reading a table or cell value in a pdf 
	public void parsePdf(String pdf, String txt) throws IOException {
		  PdfReader reader = new PdfReader(pdf);
		  PrintWriter out = new PrintWriter(new FileOutputStream(txt));
		  Rectangle rect = new Rectangle(70, 80, 490, 580);
		  RenderFilter filter = new RegionTextRenderFilter(rect);
		  TextExtractionStrategy strategy;
		  for (int i = 1; i <= reader.getNumberOfPages(); i++) {
		    strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
		    out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
		  }
		  out.flush();
		  out.close();
		  reader.close();
		}

}
