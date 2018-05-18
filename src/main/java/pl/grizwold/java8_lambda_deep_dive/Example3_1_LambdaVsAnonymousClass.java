package pl.grizwold.java8_lambda_deep_dive;

import java.util.function.*;

public class Example3_1_LambdaVsAnonymousClass {
//    public void run() {
//        execute(new Supplier<String>() {
//            @Override
//            public String get() {
//                return "jestem klasa";
//            }
//        });
//    }

    public void run() {
        execute(() -> "jestem lambda");
    }

    private void execute(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }
}
