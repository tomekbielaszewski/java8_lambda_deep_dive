package pl.grizwold.java8_lambda_deep_dive;

import java.util.function.*;

public class Example3_1_LambdaVsAnonymousClass {
//    public void run() {
//        execute(new Supplier<String>() {
//            @Override
//            public String get() {
//                return "Lubie placki";
//            }
//        });
//    }

    public void run() {
        execute(() -> "Lubie placki");
    }

    private void execute(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }
}
