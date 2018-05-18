package pl.grizwold.java8_lambda_deep_dive;

import java.util.Arrays;
import java.util.function.Supplier;

public class Example3_2_LambdaGeneratedCode {

    public static void main(String[] args) {
        new Example3_2_LambdaGeneratedCode().run();
    }

//    private static String lambda$run$0() { //compile error
//        return "foo";
//    }

    public void run() {
        execute(() -> "my sweet string");

        Arrays.asList(Example3_2_LambdaGeneratedCode.class.getDeclaredMethods())
                .forEach(System.out::println);
    }

    private void execute(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }
}
