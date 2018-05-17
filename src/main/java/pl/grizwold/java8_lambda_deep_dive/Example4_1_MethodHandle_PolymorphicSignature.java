package pl.grizwold.java8_lambda_deep_dive;

import java.lang.invoke.*;

public class Example4_1_MethodHandle_PolymorphicSignature {
    public static void main(String[] args) throws Throwable {
        new Example4_1_MethodHandle_PolymorphicSignature().run();
    }

    private void run() throws Throwable {
    }

    static int count(String string) {
        return string.length();
    }
}
