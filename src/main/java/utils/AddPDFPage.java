package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.xml.bind.annotation.XmlInlineBinaryData;
import java.io.File;
import java.io.IOException;

/**
 * @description: 添加PDF文本文件
 * @author: lee
 * @create: 2020-08-24 13:52
 **/
public class AddPDFPage {
    public static void main(String[] args) {
        PDDocument pdDocument = new PDDocument();
        try {

            for (int i = 0; i < 3; i++) {
                PDPage pdPage = new PDPage();
                pdDocument.addPage(pdPage);
            }
            pdDocument.save(new File("/Users/ligang/Desktop/addPage.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
