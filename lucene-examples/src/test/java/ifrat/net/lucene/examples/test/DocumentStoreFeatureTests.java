package ifrat.net.lucene.examples.test;

import org.apache.lucene.util.UnicodeUtil;
import org.junit.Test;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2023/7/12 0012 下午 20:55
 */
public class DocumentStoreFeatureTests {

    /**
     * GrowableByteArrayDataOutput#writeString
     */
    @Test
    public void testStringLength(){

        String val = "我是中国人";
        int maxLen = UnicodeUtil.maxUTF8Length(val.length());
        System.err.println("raw length: "+ val.length()+"; max utf8 length: "+ maxLen);

        String val01 = "Hello";
        // 计算原始字符转成字节来存储最大需要多少个字节。按 UTF-8 来算，一个字符最大占用的字节数是 3 个字节。
        // string#length
        System.out.println("raw length: "+ val01.length()+"; max utf8 length:"+ UnicodeUtil.maxUTF8Length(val01.length()));
    }
}
