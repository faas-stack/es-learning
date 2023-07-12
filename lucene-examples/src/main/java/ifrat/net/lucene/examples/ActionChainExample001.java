package ifrat.net.lucene.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 本案例给大家介绍责任链设计模式的另外一种实现，使用场景在高性能、核心链路、热点代码区的位置用此方式来实现
 *       将给你的应用程序带来 5 倍以上的性能提升。
 * - 用 jdk 提供的 List 数据结构来实现。
 * - 自定义的简单链表来实现。
 * 下面是在 window 上测试的一个结果，带来 2 倍左右的一个性能提升，在 Linux 系统上性能提升效果更明显。
 * <pre>
 *     简单链表数据结构实现:  cost time: 11
 *     List 数据结构实现:  cost time: 23
 * </pre>
 * 说明:
 *
 *  1. 这里没有使用更专业的 Java 微基准测试 (JMH)，感兴趣的朋友可以使用 JMH 来测试下。
 *  2. 如果你的应用是一个后台管控类或者对性能要求不是很高，建议用常规的 List 数据类型来实现就可以满足大多数场景。
 *  3. 在面试的时候你说这个优化点，肯定是一个加分项，将加深面试官对你的印象。
 *
 */
public class ActionChainExample001 extends AbstractApp{

    private static final List<Action> actionList = new ArrayList<>();
    private static Node head ;
    public static class Action implements Runnable{
        private String index;
        public Action(String index){
            this.index = index;
        }
        @Override
        public void run() {
            // 模拟简单的逻辑，可用控制变量法来推理，不影响真实场景下的一个性能表现
            int i = 0;
            i++;
        }
    }

    private static class Node implements Runnable{
        private Runnable current;
        private Runnable next;
        @Override
        public void run() {

            Runnable current  = this.current;
            Runnable next = this.next;

            if (current != null){
                current.run();
            }
            if (next != null){
                next.run();
            }
        }
    }

    public static void main(String[] args) throws Exception{

        // 1. list 数据结果封装
        actionList.add(new Action("list - 1"));
        actionList.add(new Action("list - 2"));
        actionList.add(new Action("list - 3"));
        actionList.add(new Action("list - 4"));

        // 2. simple linked
        head = new Node();
        head.current = new Action("linked - 1");
        Node next = new Node();
        next.current = new Action("linked - 2");
        head.next = next;
        Node next01 = new Node();
        next01.current = new Action("linked - 3");
        next.next = next01;
        Node next02 = new Node();
        next02.current = new Action("linked - 4");
        next01.next = next02;

        for (Action action : actionList){
            action.run();
        }

        head.run();

        // 3. 模拟执行 100w 次
        int count = 1000000;
        // 3.1 基于简单链表实现的责任链设计模式
        perform(count,"简单链表数据结构实现: ", ()->{
            head.run();
        });

        // 3.2 list 数据结构实现的责任链设计模式
        perform(count,"List 数据结构实现: ", ()->{
            for (Action action : actionList){
                action.run();
            }
        });
    }
}
