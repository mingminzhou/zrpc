package reqandresp;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class ZrpcRequest<T> {
    private String seqId;
    private String interfaceName;
    private String methodName;
    private T consumeParam;

}
