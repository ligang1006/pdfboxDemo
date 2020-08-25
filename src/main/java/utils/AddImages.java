package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;

import static utils.Constant.PATH;

/**
 * @description: 添加图片
 * @author: lee
 * @create: 2020-08-25 09:08
 **/
public class AddImages {
    public static void main(String args[]) throws Exception {
        //Loading an existing document
        File file = new File(Constant.PATH + "addPage.pdf");
        PDDocument doc = PDDocument.load(file);

        //Retrieving the page
        PDPage page = doc.getPage(0);

        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFile(PATH + "ss.png", doc);

        //creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page);

        //Drawing the image in the PDF document
        contents.drawImage(pdImage, 70, 70);

        System.out.println("Image inserted");

        //Closing the PDPageContentStream object
        contents.close();

        //Saving the document
        doc.save(PATH + "sample-image.pdf");

        //Closing the document
        doc.close();
    }
}
