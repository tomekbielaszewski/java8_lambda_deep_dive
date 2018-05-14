package pl.grizwold.java8_lambda_deep_dive;

import pl.grizwold.java8_lambda_deep_dive.exception.PancakeException;

import java.lang.invoke.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example5_checkedExceptions_withMetafactory {
    public static void main(String[] args) {
        new Example5_checkedExceptions_withMetafactory().run();
    }

    private class LikedPancakes {
        LikedPancakes(String likedPancakes) throws PancakeException {
            if (!"Lubie placki".equals(likedPancakes)) throw new PancakeException();
        }
    }

    private void run() {
//        List<LikedPancakes> likedPancakes1 = Stream.of("Nie lubie plackow")
//                .map(LikedPancakes::new)
//                .collect(Collectors.toList());

        List<LikedPancakes> likedPancakes2 = Stream.of("Nie lubie plackow")
                .map(pancakes -> uncheck(() -> new LikedPancakes(pancakes)))
                .collect(Collectors.toList());
    }

    private <V> V uncheck(final Callable<V> callable) /*no throws*/ {
        return getSilentInvoker().invoke(callable);
    }

    @FunctionalInterface
    interface SilentInvoker {
        <V> V invoke(final Callable<V> callable);
    }

    private static SilentInvoker getSilentInvoker() {
        try {
            MethodHandles.Lookup callerClass = MethodHandles.lookup();
            MethodType functionSignature = MethodType.methodType(Object.class, Callable.class);//signature of method INVOKE
            MethodType functionalInterface = MethodType.methodType(SilentInvoker.class);//signature of lambdas functional interface
            final CallSite site = LambdaMetafactory.metafactory(
                    callerClass,
                    "invoke",
                    functionalInterface,
                    functionSignature,
                    callerClass.findVirtual(Callable.class, "call", MethodType.methodType(Object.class)),
                    functionSignature);
            return (SilentInvoker) site.getTarget().invokeExact();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
