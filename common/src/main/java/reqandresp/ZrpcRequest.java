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
//    private T consumeParam;


    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        if(this.seqId == null){
            this.seqId = seqId;
        }
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
