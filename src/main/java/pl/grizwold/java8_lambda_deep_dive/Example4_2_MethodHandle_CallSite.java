package pl.grizwold.java8_lambda_deep_dive;

import java.lang.invoke.*;

public class Example4_2_MethodHandle_CallSite {
    public static void main(String[] args) throws Throwable {
        new Example4_2_MethodHandle_CallSite().run();
    }

    private void run() throws Throwable {
        int foo = (int) 0;
        System.out.println(foo);
    }

    static int count(String string) {
        return string.length();
    }
}