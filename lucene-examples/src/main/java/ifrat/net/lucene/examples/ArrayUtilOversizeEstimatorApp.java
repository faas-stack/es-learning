package ifrat.net.lucene.examples;

import org.apache.lucene.util.ArrayUtil;
import org.apache.lucene.util.RamUsageEstimator;

public class ArrayUtilOversizeEstimatorApp {

    public static void main(String[] args) {

        int result = ArrayUtil.oversize(8, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
        System.err.println(result);
        System.out.println("NUM_BYTES_OBJECT_REF: "+RamUsageEstimator.NUM_BYTES_OBJECT_REF);
        System.err.println("NUM_BYTES_OBJECT_HEADER: "+RamUsageEstimator.NUM_BYTES_OBJECT_HEADER);
    }
}
