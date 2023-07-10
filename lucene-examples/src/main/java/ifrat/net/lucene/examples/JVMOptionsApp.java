package ifrat.net.lucene.examples;

import java.lang.reflect.Method;

/**
 * 这个是检测当前 Java 应用有没有配置对象指针压缩技术和对象对齐。
 * - 在 jdk 7 及以后，堆内存大小小于 32G ，默认开启普通对象指针压缩技术，配置方式是:
 * <pre>
 *     -XX:+UseCompressedOops -XX:ObjectAlignmentInBytes=8
 * </pre>
 */
public class JVMOptionsApp {

    static final String MANAGEMENT_FACTORY_CLASS = "java.lang.management.ManagementFactory";
    static final String HOTSPOT_BEAN_CLASS = "com.sun.management.HotSpotDiagnosticMXBean";

    public static void main(String[] args) {

        boolean isHotspot = false , compressedOops = false;
        int objectAlignment = 4;
        try {
            final Class<?> beanClazz = Class.forName(HOTSPOT_BEAN_CLASS);
            // we use reflection for this, because the management factory is not part
            // of Java 8's compact profile:
            final Object hotSpotBean = Class.forName(MANAGEMENT_FACTORY_CLASS)
                    .getMethod("getPlatformMXBean", Class.class)
                    .invoke(null, beanClazz);
            if (hotSpotBean != null) {
                isHotspot = true;
                final Method getVMOptionMethod = beanClazz.getMethod("getVMOption", String.class);
                try {
                    final Object vmOption = getVMOptionMethod.invoke(hotSpotBean, "UseCompressedOops");
                    compressedOops = Boolean.parseBoolean(
                            vmOption.getClass().getMethod("getValue").invoke(vmOption).toString()
                    );
                } catch (ReflectiveOperationException | RuntimeException e) {
                    isHotspot = false;
                }
                try {
                    final Object vmOption = getVMOptionMethod.invoke(hotSpotBean, "ObjectAlignmentInBytes");
                    objectAlignment = Integer.parseInt(
                            vmOption.getClass().getMethod("getValue").invoke(vmOption).toString()
                    );
                } catch (ReflectiveOperationException | RuntimeException e) {
                    isHotspot = false;
                }
            }
        } catch (ReflectiveOperationException | RuntimeException e) {
            isHotspot = false;
        }

        System.err.println("is hot spot: "+ isHotspot);
        System.err.println("is compressed oops: "+ compressedOops);
        System.err.println("objectAlignment: "+ objectAlignment);
    }
}
