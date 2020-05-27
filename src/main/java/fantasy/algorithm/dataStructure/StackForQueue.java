package fantasy.algorithm.dataStructure;


import java.util.Stack;

/**
 * 栈实现队列
 *
 *  实现一
 *     s1是入栈的，s2是出栈的。
 *
 * 入队列，直接压到s1是就行了
 * 出队列，先把s1中的元素全部出栈压入到s2中，弹出s2中的栈顶元素；再把s2的所有元素全部压回s1中
 *
 * 实现二
 *
 *     s1是入栈的，s2是出栈的。保证所有元素都在一个栈里面
 *
 * 入队列时：如果s1为空，把s2中所有的元素倒出压到s1中；否则直接压入s1
 * 出队列时：如果s2不为空，把s2中的栈顶元素直接弹出；否则，把s1的所有元素全部弹出压入s2中，再弹出s2的栈顶元素
 * 比较：与实现一相比较，出队列时不必每次都捣鼓了。
 *
 * 实现三
 *     s1是入栈的，s2是出栈的。
 *
 * 入队列：直接压入s1即可
 * 出队列：如果s2不为空，把s2中的栈顶元素直接弹出；否则，把s1的所有元素全部弹出压入s2中，再弹出s2的栈顶元素
 *
 * 比较
 * 与实现二相比较，入队直接入即可，感觉此时已是最优。
 */
public class StackForQueue {
    // 存储数据
    private Stack<Integer> stack1 = new Stack<>();
    // 头删时用
    private Stack<Integer> stack2 = new Stack<>();

    /**
     * offer操作 入队列
     * @param data
     */
    public void offer(Integer data) {
        stack1.push(data);
    }

    /**
     * poll操作 头删 出队列
     * @return
     */
    public Integer poll() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StackForQueue temp = new StackForQueue();
        temp.offer(1);
        temp.offer(2);
        temp.offer(3);
        System.out.println(temp.poll());
        System.out.println(temp.poll());
        System.out.println(temp.poll());
    }
}
