package ifrat.net.lucene.examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

/**
 * Java Object Layout . 这个可以用来测试下面两个 jvm 参数的行为
 * <pre>
 *     -XX:ObjectAlignmentInBytes=16 用于控制对象对齐的字节数，默认是 8 个字节
 *     -XX:+UseCompressedOops 开启对象压缩
 * </pre>
 */

public class JavaObjectLayoutApp {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LongVO{
        // 这是一个对象，在对象的数据结构里面，表示真实数据的填充前面还有这些字段信息:
        // 4: Object header
        // 4:

        private long val;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IntegerVO{
        private int val;
    }

    private static int i;
    private static String name;

    public static void main(String[] args) throws Exception{

        System.err.println(" vm detail ");
        System.out.println(VM.current().details());
        System.err.println(" object detail ");
        System.out.println(ClassLayout.parseInstance(new LongVO(1)).toPrintable());
        System.out.println(ClassLayout.parseInstance(new LongVO(2)).toPrintable());

        TimeUnit.SECONDS.sleep(1);
        IntegerVO lock = new IntegerVO(1);
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            System.err.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        TimeUnit.SECONDS.sleep(1);
        int systemHashcode = System.identityHashCode(lock);
        System.out.println(Integer.toHexString(systemHashcode)+"\n\t"+ClassLayout.parseInstance(lock).toPrintable());

    }

    public static void plus(){
        i++;
    }

    public static void setName(String val){
        JavaObjectLayoutApp.name = val;
    }
}
