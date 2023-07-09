package ifrat.net.lucene.examples.test;

import jdk.internal.reflect.Reflection;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.nio.file.Paths;

public class MMapDirectoryTests {

    @Test
    public void testMMapDirectory() throws Exception{

        System.err.println("Hello Word Lucene");

        // 1. 第一步，初始化一个文件系统目录
        FSDirectory directory = FSDirectory.open(Paths.get("E:\\_toava\\_github\\es-learning\\lucene-examples\\indexs\\log"));
    }

    @Test
    public void testCallerClass(){

        Class clazz = Reflection.getCallerClass();

        System.err.println(clazz.getCanonicalName());
    }

    @Test
    public void lookup(){

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        System.err.println(lookup.lookupModes());

        // 通过 lookup
    }
}
