package protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/25
 */
public class ProtoStuffUtil {

    // 因为构建schema的过程可能会比较耗时，因此希望使用过的类对应的schema能被缓存起来
    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();
    // 绕开类的构造器实例化类对象
    private static Objenesis objenesis = new ObjenesisStd();

    private ProtoStuffUtil() {
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) schemaCache.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            if (schema != null) {
                schemaCache.put(cls, schema);
            }
        }
        return schema;
    }

    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            System.out.println(e.getMessage()+":"+e);
            return null;
        } finally {
            buffer.clear();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> cls) {
        T message = objenesis.newInstance(cls);
        Schema<T> schema = getSchema(cls);
        ProtostuffIOUtil.mergeFrom(data, message, schema);
        return message;
    }


}
