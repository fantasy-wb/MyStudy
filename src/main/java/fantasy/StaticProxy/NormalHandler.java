package fantasy.StaticProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class NormalHandler implements InvocationHandler {

    private Object target;

    public NormalHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("man say invoked at : " + System.currentTimeMillis());
        method.invoke(target, args);
        return null;
    }

    public static void main(String[] args) {
        Man man = new Man();
        NormalHandler normalHandler = new NormalHandler(man);
        IPerson iPerson = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(),
                new Class[] {IPerson.class}, normalHandler);
        iPerson.say();
    }
}