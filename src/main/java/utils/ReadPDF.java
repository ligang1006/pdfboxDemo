package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * @description: 读取pdf文档的方法
 * @author: lee
 * @create: 2020-08-24 13:38
 **/
public class ReadPDF {
    public static void main(String[] args) {
        //Loading an existing document
        PDDocument document = null;
        try {
            File file = new File("/Users/ligang/Desktop/xx.pdf");
            document = PDDocument.load(file);
            //初始化读取器
            PDFTextStripper pdfStripper = new PDFTextStripper();
            //获取pdf中的文档信息
            String text = pdfStripper.getText(document);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (document != null) {
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
