package exception;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/26
 */
public class ZrpcException extends RuntimeException {
    private String exceptionDetail;

    public ZrpcException(String message, Throwable cause, String exceptionDetail) {
        super(message, cause);
        this.exceptionDetail = exceptionDetail;
    }
}
