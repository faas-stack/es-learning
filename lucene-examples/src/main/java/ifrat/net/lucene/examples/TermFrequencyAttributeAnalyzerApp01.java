package ifrat.net.lucene.examples;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttribute;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * TODO 分析查看 position increment attribute 是什么
 *
 * @Author Administrator
 * @Date 2023/7/16 0016 下午 15:55
 */
public class TermFrequencyAttributeAnalyzerApp01 {

    public static void main(String[] args) throws Exception{

        StandardTokenizer standardTokenizer = new StandardTokenizer();

        InputStream inputStream = TermFrequencyAttributeAnalyzerApp01.class.getClassLoader().getResourceAsStream("index.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        standardTokenizer.setReader(reader);
        standardTokenizer.reset();

        while (standardTokenizer.incrementToken()){

            CharTermAttribute charTermAttribute = standardTokenizer.getAttribute(CharTermAttribute.class);
            TermFrequencyAttribute termFrequencyAttribute = standardTokenizer.getAttribute(TermFrequencyAttribute.class);

            char[] src = charTermAttribute.buffer();
            char[] dest = new char[charTermAttribute.length()];
            System.arraycopy(src,0,dest,0,charTermAttribute.length());

            System.err.println("Term: "+ new String(dest)+"; 词频: "+ termFrequencyAttribute.getTermFrequency());
        }
    }
}
