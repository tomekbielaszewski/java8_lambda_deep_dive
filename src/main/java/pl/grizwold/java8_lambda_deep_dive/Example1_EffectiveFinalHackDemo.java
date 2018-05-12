package pl.grizwold.java8_lambda_deep_dive;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Example1_EffectiveFinalHackDemo {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            new Example1_EffectiveFinalHackDemo().run();
        }
    }

    private void run() throws Exception {
        int[] sum = {0};

        IntStream.range(1,1000)
//                .parallel()
                .forEach(i -> sum[0]+=i);

        Thread.sleep(20);
        System.out.println(sum[0]);
    }
}
