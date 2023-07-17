package ifrat.net.lucene.examples;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class LookupWithMethodFind extends AbstractApp{

    public static void hello(String message){
        // ALOAD: 将 指定的引用类型本地变量推送值栈顶。这里的引用类型本地变量指的是 message
        message.toCharArray();
        // 这个无效的代码行会自动的被 jvm 优化掉
        char ch = 'A';
    }

    public static void main(String[] args) throws Throwable{

        Method method = LookupWithMethodFind.class.getMethod("hello", String.class);

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(method);

        method.invoke(null,"Reflect");
        methodHandle.invokeExact("Method Handler!");

        int count = 1000000;
        final String message = "Hello Word Hello Word";
        perform(count,"Method Handler: ",() -> {
            try {
                methodHandle.invokeExact(message);
            }catch (Throwable throwable){
                // nothing
            }
        });

        perform(count,"Reflect: ", () -> {
            try {
                method.invoke(null,message);
            }catch (Throwable throwable){
                // nothing
            }
        });

    }
}
