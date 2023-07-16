 package ifrat.net.lucene.examples;

import java.io.InputStream;

/**
 * TODO 验证 class 和 classloader 的 getResourceAsStream 的区别
 *
 * @Author Administrator
 * @Date 2023/7/16 0016 下午 16:02
 */
public class GetResourceAsStreamApp {

    public static void main(String[] args) {


        InputStream inputStream = GetResourceAsStreamApp.class.getResourceAsStream("class.txt");
        InputStream clStream = GetResourceAsStreamApp.class.getClassLoader().getResourceAsStream("index.txt");

        System.err.println("class stream: "+ inputStream+"; loader stream: "+ clStream);
        GetResourceAsStreamApp obj = new GetResourceAsStreamApp();

        System.out.println("obj class:"+obj.getClass().getResourceAsStream("class.txt")+"; loader:"+ obj.getClass().getClassLoader().getResourceAsStream("index.txt"));
    }
}
