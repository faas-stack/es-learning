package ifrat.net.lucene.examples;

/**
 * TODO Lucene 中对于非 String 类型的字段，会统一调用 toSting 方法来生成字符串，统一进行存储。
 * 因此这里要测试以下 Float 类型字符串表示和原生基础数据类型表示中间的一个存储性能。
 *
 * 1. 从测试结果来看，如果仅仅是用于搜索查询展示，建议所有的字段声明为 String 类型，只是搜索的时候所否需要分词进行
 * text 、keyword 进行区分。
 *
 * 2. 用 ES 存储 Metrics 类型的字段，数值类型如果统一用 String 类型来存储，不知道是否会影响聚合计算的能力，这个需要在进行验证。
 *
 * 说明: 在提升性能的同时一定会把另外一个维度的可用性或者其他的特点给降低，例如这里你如果要追求高性能的写入，就需要牺牲一部分 index mapping 上的 schema 的可读性。
 *
 *
 * @Author Administrator
 * @Date 2023/7/10 0010 下午 22:52
 */
public class ToStringPerformanceApp extends AbstractApp{

    public static void main(String[] args) {

        int count = 1000000;
        Float value = 2.34F;
        String floatVal = "2.34";

        perform(count,() -> floatVal.toString());

        perform(count,() -> {
            value.toString();
        });
    }
}
