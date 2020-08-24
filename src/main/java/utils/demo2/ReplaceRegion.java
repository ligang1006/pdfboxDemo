/**********************************************************************
 * <pre>
 * FILE : ReplaceRegion.java
 * CLASS : ReplaceRegion
 *
 * AUTHOR : caoxu-yiyang@qq.com
 *
 * FUNCTION : TODO
 *
 *
 *======================================================================
 * CHANGE HISTORY LOG
 *----------------------------------------------------------------------
 * MOD. NO.|   DATE   |   NAME  | REASON  | CHANGE REQ.
 *----------------------------------------------------------------------
 * 		    |2016年11月9日|caoxu-yiyang@qq.com| Created |
 * DESCRIPTION:
 * </pre>
 ***********************************************************************/

package utils.demo2;

/**
 * 需要替换的区域
 * @author yhj
 * @date 2020-07-15
 */
public class ReplaceRegion {
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 替换内容
     */
    private String replaceText;
    /**
     * x坐标
     */
    private Float x;
    /**
     * y坐标
     */
    private Float y;
    /**
     * 宽度
     */
    private Float w;
    /**
     * 高度
     */
    private Float h;
    /**
     * 字体属性
     */
    private FontValue fontValue;

    public ReplaceRegion(){
    }
    public ReplaceRegion(String replaceText){
        this.replaceText = replaceText;
    }

    /**
     * 替换区域的别名
     * @user : caoxu-yiyang@qq.com
     * @date : 2016年11月9日
     * @return
     */
    public String getReplaceText() {
        return replaceText;
    }
    public void setReplaceText(String replaceText) {
        this.replaceText = replaceText;
    }
    public Float getX() {
        return x;
    }
    public void setX(Float x) {
        this.x = x;
    }
    public Float getY() {
        return y;
    }
    public void setY(Float y) {
        this.y = y;
    }
    public Float getW() {
        return w;
    }
    public void setW(Float w) {
        this.w = w;
    }
    public Float getH() {
        return h;
    }
    public void setH(Float h) {
        this.h = h;
    }

    public FontValue getFontValue() {
        return fontValue;
    }

    public void setFontValue(FontValue fontValue) {
        this.fontValue = fontValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}