package pl.grizwold.java8_lambda_deep_dive;

import java.lang.invoke.*;
import java.util.Arrays;
import java.util.function.Supplier;

public class Example4_3_LambdaMetafactory {
    public static void main(String[] args) throws Throwable {
        new Example4_3_LambdaMetafactory().run();
    }

    private void run() throws Throwable {
        Supplier<String> getString = () -> "foo";

        System.out.println(getString.get());
    }

//    private void run() throws Throwable {
//        MethodHandles.Lookup caller = MethodHandles.lookup();
//
//
//
//        Supplier<String> supplierLambda = (Supplier<String>) null;
//        System.out.println(supplierLambda.get());
//    }

    static String getString() {
        return "foo";
    }
}