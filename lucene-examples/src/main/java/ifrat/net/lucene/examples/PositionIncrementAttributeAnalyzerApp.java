package ifrat.net.lucene.examples;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttribute;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2023/7/16 0016 下午 21:27
 */
public class PositionIncrementAttributeAnalyzerApp extends AbstractApp{

    public static void main(String[] args) throws Exception{

        StandardTokenizer tokenizer = new StandardTokenizer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStreamWithClassLoader("index.txt")));

        tokenizer.setReader(reader);
        tokenizer.reset();

        while (tokenizer.incrementToken()){

            String term = getTerm(tokenizer);

            PositionIncrementAttribute positionIncrementAttribute = tokenizer.getAttribute(PositionIncrementAttribute.class);
            PositionLengthAttribute positionLengthAttribute = tokenizer.getAttribute(PositionLengthAttribute.class);
            OffsetAttribute offsetAttribute = tokenizer.getAttribute(OffsetAttribute.class);
            TermFrequencyAttribute termFrequencyAttribute = tokenizer.getAttribute(TermFrequencyAttribute.class);

            System.err.println("Term: "+term+"; Freq: "+termFrequencyAttribute.getTermFrequency()+"; Position Increment:"+positionIncrementAttribute.getPositionIncrement()
                    +"\t; length: "+positionLengthAttribute.getPositionLength()+";\t offset: "+offsetAttribute.startOffset()+"->"+offsetAttribute.endOffset());
            TimeUnit.MILLISECONDS.sleep(1);
        }

    }
}
