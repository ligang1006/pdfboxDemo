package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;

/**
 * @description:
 * @author: lee
 * @create: 2020-08-20 14:53
 **/
public class ShowRetra {

    public static void main(String args[]) throws Exception {
        final String PATH = "/Users/ligang/Desktop/1.pdf";
        final String PATH2 = "/Users/ligang/Desktop/2.pdf";
        //Loading an existing document
        File file = new File(PATH);
        PDDocument document = PDDocument.load(file);

        //Retrieving a page of the PDF Document
        PDPage page = document.getPage(0);

        //Instantiating the PDPageContentStream class
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Setting the non stroking color
        contentStream.setNonStrokingColor(Color.DARK_GRAY);

        //Drawing a rectangle
        contentStream.addRect(200, 650, 100, 100);

        //Drawing a rectangle
        contentStream.fill();

        System.out.println("rectangle added");

        //Closing the ContentStream object
        contentStream.close();

        //Saving the document
        File file1 = new File(PATH2);
        document.save(file1);

        //Closing the document
        document.close();//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/pdfbox/pdfbox_adding_rectangles.html


    }

}
