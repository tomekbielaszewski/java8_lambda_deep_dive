package pl.grizwold.java8_lambda_deep_dive;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

public class Example3_2_LambdaGeneratedCode {

    public static void main(String[] args) {
        new Example3_2_LambdaGeneratedCode().run();
    }

//    private static String lambda$run$0() {
//        return "ala ma kota";
//    }

    public void run() {
        execute(() -> "Lubie placki");

        Method[] declaredMethods = Example3_2_LambdaGeneratedCode.class.getDeclaredMethods();
        Arrays.asList(declaredMethods)
                .forEach(m -> System.out.println(m));
    }

    private void execute(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }
}
