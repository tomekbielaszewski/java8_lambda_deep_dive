package pl.grizwold.java8_lambda_deep_dive;

import java.lang.invoke.*;
import java.util.function.Supplier;

public class Example4_3_LambdaMetafactory {
    public static void main(String[] args) throws Throwable {
        new Example4_3_LambdaMetafactory().run();
    }

//    private void run() throws Throwable {
//        Supplier<String> getString = () -> "foo";
//
//        System.out.println(getString.get());
//    }

    private void run() throws Throwable {
        MethodHandles.Lookup caller = MethodHandles.lookup();

        MethodType supplierSignature = MethodType.methodType(Object.class);
        MethodType actualMethodSignature = MethodType.methodType(String.class);
        MethodType functionalInterfaceUsed = MethodType.methodType(Supplier.class);

        String functionalInterfaceMethodName = "get";

        MethodHandle lambdaBodyHandle = caller.findStatic(Example4_3_LambdaMetafactory.class, "getString", actualMethodSignature);

        CallSite metafactory = LambdaMetafactory.metafactory(
                caller,
                functionalInterfaceMethodName,
                functionalInterfaceUsed,
                supplierSignature,
                lambdaBodyHandle,
                supplierSignature);

        MethodHandle factory = metafactory.getTarget();

        Supplier<String> supplierLambda = (Supplier<String>) factory.invoke();
        System.out.println(supplierLambda.get());
    }

    static String getString() {
        return "foo";
    }
}