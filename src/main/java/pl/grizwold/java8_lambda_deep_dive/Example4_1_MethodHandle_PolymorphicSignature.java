package pl.grizwold.java8_lambda_deep_dive;

import java.lang.invoke.*;

public class Example4_1_MethodHandle_PolymorphicSignature {
    public static void main(String[] args) throws Throwable {
        new Example4_1_MethodHandle_PolymorphicSignature().run();
    }

    private void run() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType signature = MethodType.methodType(int.class, new Class<?>[] { String.class});
        MethodHandle count = lookup.findStatic(Example4_1_MethodHandle_PolymorphicSignature.class,
                "count", signature);

        int foo = (int) count.invokeExact("foo");
        System.out.println(foo);

        int bar = (int) nonPolymorphicInvoke("foo");
        System.out.println(bar);
    }

    private Object nonPolymorphicInvoke(Object... args) {
        return args.length;
    }

    static int count(String string) {
        return string.length();
    }
}
