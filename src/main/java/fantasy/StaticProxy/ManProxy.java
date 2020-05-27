package fantasy.StaticProxy;

public class ManProxy implements IPerson{

    private IPerson target;

    public IPerson getTarget() {
        return target;
    }

    public ManProxy setTarget(IPerson target) {
        this.target = target;
        return this;
    }

    @Override
    public void say() {
        if (target != null) {
            System.out.println("man say invoked at : " + System.currentTimeMillis());
            target.say();
        }
    }

    public static void main(String[] args) {
        ManProxy proxy = new ManProxy();
        proxy.say();
    }
}