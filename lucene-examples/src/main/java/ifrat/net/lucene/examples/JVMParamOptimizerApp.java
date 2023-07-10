package ifrat.net.lucene.examples;

import java.lang.management.ManagementFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 此类用用于模拟运行时 Java 应用程序对象创建和回收这个过程对 jvm 负载、GC 频次、内存等的影响
 * <pre>
 *     -Xmx2048m -Xms2048m -Xmn1638m -XX:+UseG1GC -XX:+UseCompressedOops
 * </pre>
 */
public class JVMParamOptimizerApp {

    public static void main(String[] args) throws Exception{

        final int factor = 2;
        /**
         * 模拟一个线程不断的的创建对象，通过设置不同的是 GC 策略和 jvm 参数来观察 GC 次数以及耗时的一个情况和现象
         */
        Thread thread = new Thread(() -> {
            while (true) {
                final List<byte[]> allocationData = new LinkedList<>();
                final List<String> strInfo = new LinkedList<>();
                for (int i = 0; i < 2000; i++) {
                    // 模拟创建一个1K, 5K,15K,50K,100K,200K,300K 尺寸的大小对 GC 的影响
                    // 控制
                    byte[] data = new byte[4096 * factor * Math.abs(new Random().nextInt(10))];
                    allocationData.add(data);
                    strInfo.add("A");
                }
                try {
                    System.err.println("size: " + allocationData.size());
                    // 设置创建速度 10 ms
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
            }
        });

        thread.start();
        thread.join();
    }
}
