package ifrat.net.lucene.examples;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * TODO 每一个 field 都会关联自己的一个 StandardTokenizer .
 *
 * @Author Administrator
 * @Date 2023/7/14 0014 下午 20:18
 */
public class CharTermAttrAnalyzerApp01 {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = StandardAnalyzer.class.getClassLoader().getResourceAsStream("index.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


        StandardTokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(bufferedReader);
        tokenizer.reset();

        while (tokenizer.incrementToken()){

            CharTermAttribute charTermAttribute = tokenizer.getAttribute(CharTermAttribute.class);

            char[] chars = charTermAttribute.buffer();
            char[] result = new char[charTermAttribute.length()];

            System.arraycopy(chars,0,result,0,charTermAttribute.length());

            System.err.println(new String(result));
        }

    }
}
