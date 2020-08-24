package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:添加文档，设置自定义字体
 * @author: lee
 * @create: 2020-08-20 17:35
 **/
public class DrawText {
    public static void main(String[] args) throws IOException {
        final String PATH = "/Users/ligang/Desktop/f0krpqfu.pdf";
        File file = new File(PATH);

        PDDocument document = PDDocument.load(file);
        //Retrieving a page of the PDF Document
        for (int i = 0; i < 10; i++) {
            PDPage page = document.getPage(i);
            PDPageContentStream content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ygyjfcs.ttf");

            PDType0Font font = PDType0Font.load(document, resourceAsStream);
//            PDType0Font font = PDType0Font.load(document, new File("/Users/ligang/IdeaProjects/pdfboxDemo/src/main/resources/ygyjfcs.ttf"));
//            PDFont font = PDType1Font.ZAPF_DINGBATS;
            font.encode("utf8");
            int cursorX = 70;
            int cursorY = 50;
            //draw rectangle
//            content.setNonStrokingColor(Color.WHITE);
//            content.addRect(cursorX, cursorY, 100, cursorY + 100);
            content.fill();
            content.saveGraphicsState();
            //draw text
            content.setNonStrokingColor(Color.BLACK);
            content.beginText();
            content.setFont(font, 60);
            content.newLineAtOffset(cursorX + 10, cursorY + 100);
            content.showText("wo打算的");
            content.endText();
            content.close();
        }
        document.save(new File("/Users/ligang/Desktop/1.pdf"));
        /**以流的方式输出*/
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        document.save(byteArrayOutputStream);
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        byteArrayOutputStream.close();
        document.close();
    }
}
