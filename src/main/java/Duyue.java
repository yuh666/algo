/**
 * @author yuh
 * @date 2019-04-15 13:32
 **/
public class Duyue {


    /**
     * @param delta 波动体重 减掉为负 增加为正
     */
    public static void calculate(double delta) {
        //四舍五入计算
        int d = (int) Math.round(delta);
        //减了5斤 白玩
        if (d == -5) {
            System.out.println("没输没赢,没有中间商赚差价");
            return;
        }
        //距离-5的绝对距离
        int n = Math.abs(d + 5);
        //每斤200基础价格
        int basePrice = n * 200;
        //每波动一金在上一斤基础上加上50块 Sn = na1 + n(n-1)*d /2 首项a1=0 n=绝对距离 d=50公差50
        int floatPrice = n * (n - 1) * 25;
        //总价
        int all = basePrice + floatPrice;
        //中间商赚的差价
        int mid = all / 10;
        if (d > -5) {
            System.out.println("杨玉娟向于昊支付人民币" + (all - mid) + "元,中间商应收取" + mid + "元");
        } else {
            System.out.println("于昊向杨玉娟支付人民币" + (all - mid) + "元,中间商应收取" + mid + "元");
        }

        int i = Math.abs((int) Math.round(delta) + 5) * 200 + Math.abs((int) Math.round(delta) + 5) * (Math.abs((int) Math.round(delta) + 5) - 1) * 25;
    }

    public static int pppp(int delta) {
        int n = Math.abs(delta + 5);
        int all = n * 200 + n * (n - 1) * 25;
        return delta>-5?all:-all;
    }


    public static void main(String[] args) {
//        calculate(0);
        System.out.println(pppp(0));
    }
}
