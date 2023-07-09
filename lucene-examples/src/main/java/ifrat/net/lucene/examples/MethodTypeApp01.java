package ifrat.net.lucene.examples;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodTypeApp01 {

    static class ClassA{

        public void println(String info){
            System.out.println("Method Handle: "+info);
        }
    }

    public static void main(String[] args) throws Throwable{

        Object classA = new ClassA();
        Object out = System.out;

        //  invokeExact 指令用于调用静态方法或特定类型的实例方法。
        getPrintlnMH(classA).invokeExact("Hello Word");
        getPrintlnMH(out).invokeExact("Hello Word");

    }

    /**
     * - 这里面可以引出一个面试问题: 方法的调用通常是在编译过程中就已经固化在 class 文件里面了，那请问有什么可以打破
     * 方法的调用和对象之间的关系吗，使得一个方法的调用可以动态的和具体的对象进行绑定调用。
     *
     * - 从指定的对象上面找到指定的 method 处理, 实现方法到对象的动态绑定，也就是打破之前 Java 语言强类型变量的限制。
     * - 这段代码的含义是在指定的对象上只要有自定的 MethodType 类型的方法就可以。
     * 因此在运行时可以动态的绑定到 ClassA 这个对象上，也可以绑定到 System#out 这个对象上
     * @param receiver
     * @return
     */
    private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException {

        //  // MethodType：代表“方法类型”，包含了方法的返回值（methodType()
        //  的第一个参数）和具体参数（methodType() 第二个及以后的参数）。
        MethodType methodType = MethodType.methodType(void.class,String.class);
        // lookup() 方法来自于 MethodHandles.lookup，这句的作用是在指定类中查找符合给定的方法名称、方法类型，
        // 并且符合调用权限的方法句柄。
        // 因为这里调用的是一个虚方法，按照 Java 语言的规则，方法第一个参数是隐式的，代表该方法的接收者，也即是
        // this 指向的对象，这个参数以前是放在参数列表中进行传递，现在提供了 bindTo() 方法来完成这件事情。
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
    }
}
