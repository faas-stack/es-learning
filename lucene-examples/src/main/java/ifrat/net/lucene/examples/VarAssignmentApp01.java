package ifrat.net.lucene.examples;

/**
 * TODO 测试变量赋值。其中一个对象 A 中的变量已经通过构造方法传递给另外一个对象 B，在 A 中修改变量的值，B 中是否会跟着一起变化
 *
 * @Author Administrator
 * @Date 2023/7/15 0015 上午 8:55
 */
public class VarAssignmentApp01 {

    public static class Person{
        String name;
    }

    static class A {
        Person val = defaultVal;
        B b;
        public A(){
            b = new B(val);
        }

        public void setVal(Person val) {
            this.val = val;
        }
    }

    static class B {
        Person val;
        public B(Person val){
            this.val = val;
        }
    }

    static final Person defaultVal = new Person();

    public static void main(String[] args) {

        A aVal = new A();
        aVal.setVal(new Person());

        System.err.println(aVal.b.val.hashCode()+"; "+ aVal.val.hashCode());
    }
}
