package utils.demo2;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 替换PDF文件某个区域内的文本工具
 *
 * @author lee
 * @date : 2020年08月22日
 */

public class PdfboxReplaceUtil {

    /**
     * 输出流
     */
    private ByteArrayOutputStream output;
    /**
     * pdf文本
     */
    private PDDocument document;
    /**
     * 文本流
     */
    private PDPageContentStream contentStream;
    /**
     * 从第0页开始算
     */
    private static final Integer DECREASE_ONE = 1;
    /**
     * 设置字体默认字号
     */
    private int FONT_SIZE = 12;

    private PdfboxReplaceUtil(byte[] pdfBytes) throws IOException {
        init(pdfBytes);
    }

    private void init(byte[] pdfBytes) throws IOException {
        document = PDDocument.load(pdfBytes);
        output = new ByteArrayOutputStream();
    }

    private void close() throws IOException {
        if (output != null) {
            output.close();
        }
        output = null;
    }


    /**
     * 根据自定区域替换文本
     *
     * @throws IOException
     * @throws
     */
    private void process(Map<Integer, List<ReplaceRegion>> replaceRegionMap) throws IOException {
        try {
            for (Entry<Integer, List<ReplaceRegion>> entry : replaceRegionMap.entrySet()) {
                //设置当前操作页码，从第0页开始算
                PDPage page = document.getPage(entry.getKey() - DECREASE_ONE);
                contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
                for (ReplaceRegion region : entry.getValue()) {
                    Float cursorX = region.getX();
                    Float cursorY = region.getY();
                    //画矩形，作为背景覆盖，暂时不用
                    //content.setNonStrokingColor(Color.WHITE);
                    //content.addRect(cursorX, cursorY, 100, cursorY + 100);
                    //content.fill();
                    //content.saveGraphicsState();
                    /**添加文字*/
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.beginText();
                    //设置文字属性
                    InputStream fontInfo = getFontInfo(region.getFontValue());
                    PDType0Font font = PDType0Font.load(document, fontInfo);
                    //设置字号和字体
                    contentStream.setFont(font, region.getFontValue().getSize() != null ? region.getFontValue().getSize() : FONT_SIZE);
                    font.encode("utf8");
                    contentStream.newLineAtOffset(cursorX, cursorY + 3);
                    contentStream.showText(region.getReplaceText());
                    contentStream.saveGraphicsState();
                    contentStream.endText();
                }
                contentStream.close();
            }
            document.save(output);
        } catch (Exception e) {
        } finally {
            if (contentStream != null) {
                contentStream.close();
            }
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * 设置参数
     *
     * @param x
     * @param y
     * @param text 替换文字
     */
    public ReplaceRegion replaceText(float x, float y, float w, float h, String text, FontValue fontValue) {
        //用文本作为别名
        ReplaceRegion region = new ReplaceRegion(text);
        region.setH(h);
        region.setW(w);
        region.setX(x);
        region.setY(y);
        region.setFontValue(this.getFontVale(fontValue));
        return region;
    }

    /**
     * 获取字体属性
     *
     * @param fontValue
     * @return
     */
    public FontValue getFontVale(FontValue fontValue) {
        if (fontValue != null) {
            fontValue.setSize(fontValue.getSize() == null ? FONT_SIZE : fontValue.getSize());
            fontValue.setFontStyle(StringUtils.isBlank(fontValue.getFontStyle()) ? FontEnum.SIM_SUN.getCode() : fontValue.getFontStyle());
        } else {
            fontValue = new FontValue();
            fontValue.setSize(FONT_SIZE);
            fontValue.setFontStyle(FontEnum.SIM_SUN.getCode());
        }
        return fontValue;
    }

    /**
     * 替换pdf文本区域
     *
     * @param regions  区域参数 key:页码， value:区域参数
     * @param pdfBytes 源文件字节码
     */

    public static byte[] PdfReplaceRegion(Map<Integer, List<ReplaceRegion>> regions, byte[] pdfBytes) throws Exception {
        Map<Integer, List<ReplaceRegion>> replaceRegionMap = new ConcurrentHashMap<>();
        PdfboxReplaceUtil pdPlacer = null;
        try {
            pdPlacer = new PdfboxReplaceUtil(pdfBytes);
        } catch (IOException e) {

        }
        for (Entry<Integer, List<ReplaceRegion>> mapEntry : regions.entrySet()) {
            List<ReplaceRegion> replaceRegionList = new ArrayList<>();
            if (regions != null) {
                for (ReplaceRegion region : mapEntry.getValue()) {
                    replaceRegionList.add(pdPlacer.replaceText(region.getX(),
                            region.getY(),
                            region.getW(),
                            region.getH(),
                            region.getReplaceText(),
                            region.getFontValue()));
                }
            }
            replaceRegionMap.put(mapEntry.getKey(), replaceRegionList);
        }
        try {
            return pdPlacer.toPdf(replaceRegionMap);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 获取字体流
     *
     * @param fontValue
     * @return
     */
    private InputStream getFontInfo(FontValue fontValue) {
        InputStream resourceAsStream = null;
        //自定义字体
        if (fontValue != null && StringUtils.isNotBlank(fontValue.getFontStyle())) {
            resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FontEnum.getValue(fontValue.getFontStyle()));
            return resourceAsStream;
        }
        //默认宋体
        resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FontEnum.getValue(FontEnum.SIM_SUN.getValue()));
        return resourceAsStream;
    }

    /**
     * 生成新的PDF文件
     */
    public byte[] toPdf(Map<Integer, List<ReplaceRegion>> replaceRegionMap) throws IOException {
        try {
            this.process(replaceRegionMap);

            return output.toByteArray();
        } catch (IOException e) {
            throw e;
        } finally {
            close();
        }
    }
}
