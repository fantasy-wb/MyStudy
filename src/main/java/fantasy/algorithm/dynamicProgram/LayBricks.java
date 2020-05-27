package fantasy.algorithm.dynamicProgram;

/**
 * 用1*3的瓷砖密铺3*20的地板有几种方式？
 * 想铺好3*n，
 *
 * 可以先铺3*(n-1)，最后一列竖着铺一块3*1；
 * 也可以先铺好f(n-3)，最后的三快横着铺。
 *
 * 那么f(n) = f(n-1)+f(n-3)。
 */
public class LayBricks {

    public static int task3(int n){
        if(n >= 3)
            return task3(n-1) + task3(n-3);
        else
            return 1;
    }

    public static void main(String[] args) {
        System.out.println(task3(20));
    }
}
