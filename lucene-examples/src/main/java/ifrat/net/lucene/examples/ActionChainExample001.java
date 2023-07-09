package ifrat.net.lucene.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ActionChainExample001 {

    private static final List<Action> actionList = new ArrayList<>();
    private static Node head ;
    public static class Action implements Runnable{
        private String index;
        public Action(String index){
            this.index = index;
        }
        @Override
        public void run() {
            System.err.println(this.index+"; current date: "+ new Date());
        }
    }

    public static void main(String[] args) throws Exception{

        // list
        actionList.add(new Action("list - 1"));
        actionList.add(new Action("list - 2"));
        actionList.add(new Action("list - 3"));
        actionList.add(new Action("list - 4"));

        // simple linked
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

        Properties properties = System.getProperties();

        for (Object key : properties.keySet()){
            System.err.println(key+" : "+ properties.get(key));

        }

        System.in.read();
    }


    private static class Node implements Runnable{
        private Runnable current;
        private Runnable next;
        @Override
        public void run() {

            if (current != null){
                current.run();
            }
            if (next != null){
                next.run();
            }
        }
    }
}
