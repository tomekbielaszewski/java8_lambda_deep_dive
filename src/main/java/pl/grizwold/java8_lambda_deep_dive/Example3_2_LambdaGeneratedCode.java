package pl.grizwold.java8_lambda_deep_dive;

import java.util.function.Supplier;

public class Example3_2_LambdaGeneratedCode {

    public static void main(String[] args) {
        new Example3_2_LambdaGeneratedCode().run();
    }

    public void run() {
        execute(() -> "my sweet string");
    }

    private void execute(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }
}
