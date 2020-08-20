package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: lee
 * @create: 2020-08-20 17:35
 **/
public class DrawText {
    public static void main(String[] args) throws IOException {
        final String PATH = "/Users/ligang/Desktop/f0krpqfu.pdf";
        File file = new File(PATH);
        PDDocument document = PDDocument.load(file);
        //Retrieving a page of the PDF Document
        PDPage page = document.getPage(1);
        PDPageContentStream content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        int cursorX = 70;
        int cursorY = 50;
        //draw text
        content.setNonStrokingColor(Color.BLACK);
        content.beginText();
        content.setFont(font, 20);
//        Matrix matrix = new Matrix();
//        content.setTextMatrix(matrix);
//        content.setTextMatrix(cursorX,cursorY);
        content.newLineAtOffset(cursorX + 10, cursorY + 10);
        content.showText("sssssssssss");
        content.endText();

        //draw rectangle
        content.setNonStrokingColor(Color.RED);
        content.addRect(cursorX, cursorY, 70, 50);
        content.close();
        document.save(new File("/Users/ligang/Desktop/textOnBackground.pdf"));
        document.close();
    }
}
