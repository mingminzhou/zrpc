import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2019/9/8
 */
public class TestArthas {
    private static Random random = new Random();

    public int illegalArgumentCount = 0;

    public static void main(String[] args) throws InterruptedException {
        TestArthas game = new TestArthas();
        while (true) {
            game.run();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void run() throws InterruptedException {
        try {
            int number = random.nextInt()/10000;
            List<Integer> primeFactors = primeFactors(number);
            print(number, primeFactors);

        } catch (Exception e) {
            System.out.println(String.format("illegalArgumentCount:%3d, ", illegalArgumentCount) + e.getMessage());
        }
    }

    public static void print(int number, List<Integer> primeFactors) {
        StringBuffer sb = new StringBuffer(number + "=");
        for (int factor : primeFactors) {
            sb.append(factor).append('*');
        }
        if (sb.charAt(sb.length() - 1) == '*') {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

    public List<Integer> primeFactors(int number) {
        if (number < 2) {
            illegalArgumentCount++;
            throw new IllegalArgumentException("number is: " + number + ", need >= 2");
        }

        List<Integer> result = new ArrayList<Integer>();
        int i = 2;
        while (i <= number) {
            if (number % i == 0) {
                result.add(i);
                number = number / i;
                i = 2;
            } else {
                i++;
            }
        }

        return result;
    }
}
