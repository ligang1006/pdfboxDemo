package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: lee
 * @create: 2020-08-20 17:29
 **/
public class AddText {

    public static void main(String[] args) throws IOException {
        final String PATH = "/Users/ligang/Desktop/1.pdf";
        final String PATH2 = "/Users/ligang/Desktop/2.pdf";
        //Loading an existing document
        File file = new File(PATH);
        PDDocument document = PDDocument.load(file);

        //Retrieving the pages of the document
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Begin the Content stream
        contentStream.beginText();
        // contentStream.set
        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 100);

        String text = "This is the sample document and we are adding content to it. - By yiibai.com";

        //Adding text in the form of string
        contentStream.showText(text);

        //Ending the content stream
        contentStream.endText();

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save(new File(PATH2));

        //Closing the document
        document.close();

    }

}
