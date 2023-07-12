package ifrat.net.lucene.examples;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableFieldType;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2023/7/12 0012 下午 23:35
 */
public class IndexStoreLongFieldApp extends AbstractApp{

    public static void main(String[] args) throws Exception{

        FSDirectory fsDirectory = FSDirectory.open(Paths.get(directory));

        IndexWriterConfig writerConfig = new IndexWriterConfig();
        writerConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        IndexWriter indexWriter = new IndexWriter(fsDirectory,writerConfig);

        Document document = new org.apache.lucene.document.Document();

        FieldType indexableFieldType = new FieldType();
        indexableFieldType.setStored(true);
        indexableFieldType.setOmitNorms(true);
        // the default
        indexableFieldType.setIndexOptions(IndexOptions.NONE);
        indexableFieldType.setTokenized(false);
        // 后面不能在更改了
        indexableFieldType.freeze();

        byte[] value = new byte[Long.BYTES];

        LongPoint.encodeDimension(System.currentTimeMillis(),value,0);

        Field timestampField = new Field("timestamp",value,indexableFieldType);
        document.add(timestampField);

    }
}
