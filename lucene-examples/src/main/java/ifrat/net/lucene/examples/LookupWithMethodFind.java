package ifrat.net.lucene.examples;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class LookupWithMethodFind {

    public static void hello(String message){

        System.err.println("Hello : "+ message);
    }

    public static void main(String[] args) throws Throwable{

        Method method = LookupWithMethodFind.class.getMethod("hello", String.class);

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(method);
    }
}
