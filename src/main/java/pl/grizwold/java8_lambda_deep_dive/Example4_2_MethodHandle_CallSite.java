package pl.grizwold.java8_lambda_deep_dive;

import java.lang.invoke.*;

public class Example4_2_MethodHandle_CallSite {
    public static void main(String[] args) throws Throwable {
        new Example4_2_MethodHandle_CallSite().run();
    }

    private void run() throws Throwable {
        MethodHandle target = bootstrap().getTarget();

        int foo = (int) target.invokeExact("foo");
        System.out.println(foo);
    }

    private static CallSite bootstrap() throws Throwable {
        MethodType type = MethodType.methodType(int.class, new Class<?>[] {String.class});
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle countHandle = lookup.findStatic(Example4_2_MethodHandle_CallSite.class, "count", type);
        return new ConstantCallSite(countHandle);
    }

    static int count(String string) {
        return string.length();
    }
}