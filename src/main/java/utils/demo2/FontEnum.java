package utils.demo2;


/**
 * @description:字体枚举
 * @author: lee
 * @create: 2020-07-15
 **/
public enum FontEnum {
    /**
     * 黑体
     */
    SIM_HEI("simhei","simhei.ttf"),
    /**
     * 宋体
     */
    SIM_SUN("simsun","simsun.ttf"),
    /**
     * 楷体
     */
    KT_GB2312("ktgb2312","ktgb2312.ttf");


    private String code;

    private String value;

    FontEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setVame(String value) {
        this.value = value;
    }

    public static String getValue(String code) {
        FontEnum[] carTypeEnums = values();
        for (FontEnum carTypeEnum : carTypeEnums) {
            if (carTypeEnum.getCode().equals(code)) {
                return carTypeEnum.getValue();
            }
        }
        return null;
    }

}
