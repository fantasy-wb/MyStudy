package fantasy.Reflection;

import java.lang.reflect.Method;

public class TestClassLoad {
    public static void main(String[] args) throws Exception {  
        Class<?> clz = Class.forName("A");
        Object o = clz.newInstance();  
        Method m = clz.getDeclaredMethod("hello", null);
        m.invoke(o);
    }
    static class A{
        public void hello() {
            System.out.println("hello world");
        }
    }  
}