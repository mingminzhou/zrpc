package exception;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/26
 */
public enum ZrpcExceptionEnum {
    NO_CHANNELS_INIT(002,"没有初始化channel数组");
    private int exceptionCode;
    private String exceptionDesc;

    private ZrpcExceptionEnum(int exceptionCode, String excetionDesc) {
        this.exceptionCode = exceptionCode;
        this.exceptionDesc = excetionDesc;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

}
