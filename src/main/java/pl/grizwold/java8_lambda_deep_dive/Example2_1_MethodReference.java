package pl.grizwold.java8_lambda_deep_dive;

import java.util.function.*;

public class Example2_1_MethodReference {
    public static void main(String[] args) {
        new Example2_1_MethodReference().run();
    }

    private String someString = "my sweet string";

    private String getSomeString() {
        return someString;
    }

    private void setSomeString(String someString) {
        this.someString = someString;
    }

    private String concat(String someString1, String someString2) {
        return someString1+someString2;
    }

    private void printStrings(String someString1, String someString2) {
        System.out.println(concat(someString1, someString2));
    }

    private void run() {
        Supplier<String> bound_getter = this::getSomeString;
        Function<Example2_1_MethodReference, String> unbound_getter = Example2_1_MethodReference::getSomeString;

        String someString = bound_getter.get();
        String someString2 = unbound_getter.apply(this);


        Consumer<String> bound_setter = this::setSomeString;
        BiConsumer<Example2_1_MethodReference, String> unbound_setter = Example2_1_MethodReference::setSomeString;

        bound_setter.accept("foo");
        unbound_setter.accept(this, "foo");


        BiConsumer<String, String> bound_printStrings = this::printStrings;
        TriConsumer<Example2_1_MethodReference, String, String> unbound_printStrings = Example2_1_MethodReference::printStrings;

        bound_printStrings.accept("foo", "bar");
        unbound_printStrings.accept(this, "foo", "bar");


        BiFunction<String, String, String> bound_concat = this::concat;
        TriFunction<Example2_1_MethodReference, String, String, String> unbound_concat = Example2_1_MethodReference::concat;

        String concat = bound_concat.apply("foo", "bar");
        String concated2 = unbound_concat.apply(this, "foo", "bar");
    }

    @FunctionalInterface
    interface TriConsumer<IN1, IN2, IN3> {
        void accept(IN1 attribute1, IN2 attribute2, IN3 attribute3);
    }

    @FunctionalInterface
    interface TriFunction<CLASS, OUT, IN1, IN2> {
        OUT apply(CLASS clazz, IN1 attribute1, IN2 attribute2);
    }
}
