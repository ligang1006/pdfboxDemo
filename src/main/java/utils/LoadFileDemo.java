package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.*;

/**
 * @description: 加载文件
 * @author: lee
 * @create: 2020-08-24 14:02
 **/
public class LoadFileDemo {
    // Loading an existing document
    static PDPageContentStream pdPageContentStream = null;
    static PDDocument document = null;
    private final static String TEXT = "abcdef";

    public static void main(String[] args) {

        try {
            File file = new File("/Users/ligang/Desktop/xx.pdf");
            document = PDDocument.load(file);
            System.out.println("PDF loaded");

            // Adding a blank page to the document
            document.addPage(new PDPage());
            //第一页
            PDPage page = document.getPage(0);

            pdPageContentStream = new PDPageContentStream(document, page,);
            pdPageContentStream.beginText();
            //设置字体
            pdPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            pdPageContentStream.newLineAtOffset(0, 0);
            //添加文字
            pdPageContentStream.showText(TEXT);
            pdPageContentStream.endText();
            pdPageContentStream.close();
            // Saving the document
            document.save("/Users/ligang/Desktop/xx2.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    private static void close() {
        // Closing the document

        try {
            if (pdPageContentStream != null) {
                pdPageContentStream.close();
            }
            if (document != null) {
                document.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
