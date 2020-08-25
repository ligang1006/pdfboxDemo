package utils;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static utils.Constant.PATH;

/**
 * @description: 拆分PDF
 * @author: lee
 * @create: 2020-08-25 09:29
 **/
public class Split {
    public static void main(String[] args) throws IOException {

        //Loading an existing PDF document
        File file = new File(PATH + "xx.pdf");
        PDDocument document = PDDocument.load(file);

        int nums = document.getNumberOfPages();
        //Instantiating Splitter class
        Splitter splitter = new Splitter();

        splitter.setEndPage(nums);
        splitter.setStartPage(1);
        //      每个文档包含的页数
        splitter.setSplitAtPage(nums / 2);

        //splitting the pages of a PDF document
        List<PDDocument> Pages = splitter.split(document);

//        Pages.add()
        //Creating an iterator
        Iterator<PDDocument> iterator = Pages.listIterator();

        //Saving each page as an individual document
        int i = 1;
        while (iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save(PATH + "sample" + i + ".pdf");
            i = i + 1;
        }

        System.out.println("Multiple PDF’s created");
        document.close();
    }

}
