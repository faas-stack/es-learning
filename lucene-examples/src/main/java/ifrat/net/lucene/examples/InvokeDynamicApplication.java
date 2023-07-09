package ifrat.net.lucene.examples;

import java.lang.invoke.*;

/**
 *
 */
public class InvokeDynamicApplication {

    public static void main(String[] args) throws Throwable{

        String message = "invoke dynamic hello word!";

        int i = 100000;


        perform(i,() -> {
            try {
                invokeDynamicBootstrapMethod().invokeExact(message);
            }catch (Throwable throwable){
            }
        });

        perform(i,()->{
            try {
                testMethod(message);
            }catch (Throwable throwable){}
        });
    }

    public static void perform(int count,Runnable runnable){
        long start = System.currentTimeMillis();
        for (int i=0;i<count;i++)
            runnable.run();

        System.err.println("cost time: "+ (System.currentTimeMillis() - start));
    }

    public static void testMethod(String input){

//        System.err.println("test method: "+ input);
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType methodType) throws Exception{

        return new ConstantCallSite(lookup.findStatic(InvokeDynamicApplication.class,name,methodType));
    }

    public static MethodType MT_BootstrapMethod(){
        return MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
    }

    private static MethodHandle MH_BootstrapMethod() throws Exception{
        return MethodHandles.lookup().findStatic(InvokeDynamicApplication.class,"BootstrapMethod",MT_BootstrapMethod());
    }

     private static MethodHandle invokeDynamicBootstrapMethod() throws Throwable{

        CallSite callSite =  (CallSite) MH_BootstrapMethod().invokeWithArguments(MethodHandles.lookup(),"testMethod",MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return callSite.dynamicInvoker();
    }
}
