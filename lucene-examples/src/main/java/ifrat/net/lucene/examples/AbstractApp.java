package ifrat.net.lucene.examples;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttribute;

import java.io.InputStream;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2023/7/10 0010 下午 22:54
 */
public abstract class AbstractApp {

    protected static final String directory = "E:\\_toava\\_github\\es-learning\\lucene-examples\\indexs\\log";

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

    public static InputStream getInputStreamWithClassLoader(String file){

        return AbstractApp.class.getClassLoader().getResourceAsStream(file);
    }

    public static String getTerm(StandardTokenizer tokenizer){
        CharTermAttribute charTermAttribute = tokenizer.getAttribute(CharTermAttribute.class);
        TermFrequencyAttribute termFrequencyAttribute = tokenizer.getAttribute(TermFrequencyAttribute.class);

        char[] src = charTermAttribute.buffer();
        char[] dest = new char[charTermAttribute.length()];
        System.arraycopy(src,0,dest,0,charTermAttribute.length());

        return new String(dest);
    }
}
