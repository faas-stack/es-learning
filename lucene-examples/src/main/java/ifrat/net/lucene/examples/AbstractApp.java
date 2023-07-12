package ifrat.net.lucene.examples;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2023/7/10 0010 下午 22:54
 */
public abstract class AbstractApp {

    public static void perform(int count,Runnable runnable){
        long start = System.currentTimeMillis();
        for (int i=0;i<count;i++)
            runnable.run();

        System.err.println("cost time: "+ (System.currentTimeMillis() - start));
    }

    public static void perform(int count,String tips, Runnable runnable){
        long start = System.currentTimeMillis();
        for (int i=0;i<count;i++)
            runnable.run();

        System.err.println(tips+" cost time: "+ (System.currentTimeMillis() - start));
    }
}
