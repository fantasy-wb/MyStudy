package fantasy.algorithm.dataStructure;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现栈
 *
 *
 * 实现一
 *     q1是专职进出栈的，q2只是个中转站
 *
 * 入栈：直接入队列q1即可
 * 出栈：把q1的除最后一个元素外全部转移到队q2中,然后把刚才剩下q1中的那个元素出队列。之后把q2中的全部元素转移回q1中
 *
 * 实现二
 *
 *     q1是专职进出栈的，q2只是个中转站。元素集中存放在一个栈中，但不是指定(q1 或 q2)。
 *
 *     定义两个指针：pushtmp:指向专门进栈的队列q1； tmp：指向临时作为中转站的另一个栈q2
 *
 * 入栈：直接入push tmp所指队列即可
 * 出栈：把push tmp的除最后一个元素外全部转移到队列tmp中,然后把刚才剩下q1中的那个元素出队列
 * 比较
 *
 *     实现二，出栈后就不用转移回原来的栈了（图示最后一步），这样减少了转移的次数。
 */
public class QueueForStack {
    // 存储数据
    private Queue<Integer> queue1 = new LinkedList<>();

    private Queue<Integer> queue2 = new LinkedList<>();
    /**
     * push操作 入栈
     * @param data
     */
    public void push(Integer data) {
        queue1.offer(data);
    }

    /**
     * pop操作 头删 出队列
     * @return
     */
    public Integer pop() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        Integer result = queue1.poll();
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        QueueForStack temp = new QueueForStack();
        temp.push(1);
        temp.push(2);
        temp.push(3);
        System.out.println(temp.pop());
        System.out.println(temp.pop());
        System.out.println(temp.pop());
    }
}
