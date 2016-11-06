// [JavaSE-1.8]
// The program generates all cards in standard card deck and imports them into PDF file using the external library iTextPdf
package pdfwriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.FileOutputStream;
import java.io.IOException;

public class DeckOfCards
{
	// local variable containing the path and the name of the PDF file
    public static final String RESULT = "src/lib/DeckOfCards.pdf";
    
    // main() method used only to call the generator
    public static void main(String[] args)
            throws DocumentException, IOException
    {
        new DeckOfCards().createPdf(RESULT);
    }
    
    // the method that creates and fills the PDF file with already generated table from writeCards() method; it also sets the card parameters (suit, color)   
    public void createPdf(String filename)
            throws DocumentException, IOException
    {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));

        String diamond = "\u2666";
        String heart = "\u2665";
        String spade = "\u2663";
        String club = "\u2660";

        document.open();

        document.add(writeCards(diamond,255));
        document.add(new Paragraph("\n\n"));

        document.add(writeCards(heart,255));
        document.add(new Paragraph("\n\n"));

        document.add(writeCards(spade,0));
        document.add(new Paragraph("\n\n"));

        document.add(writeCards(club,0));

        document.close();
    }

    // the actual writer which generates all cards and fills them into a table which is then returned by the method
    public static PdfPTable writeCards(String card, int c)
            throws DocumentException, IOException
    {
        String[] deck = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        BaseFont bfCard = BaseFont.createFont("lib/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font myFont = new Font(bfCard,14);

        myFont.setColor(c, 0, 0);

        PdfPTable table = new PdfPTable(deck.length);
        table.setWidthPercentage(100);

        for (int i = 0; i < deck.length; i++)
        {
        	PdfPCell cell = new PdfPCell(new Phrase(deck[i] + card + "    ", myFont));
        	
        	cell.setFixedHeight(60);
        	cell.setPaddingTop(20.0f);
        	cell.setPaddingLeft(10.0f);
        	
            table.addCell(cell);
        };

        System.out.println();

        return table;
    }
}


