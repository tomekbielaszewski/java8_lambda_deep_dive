package pl.grizwold.java8_lambda_deep_dive;

import org.jooq.lambda.function.Consumer3;
import org.jooq.lambda.function.Function3;

import java.util.function.*;

public class Example2_2_MethodReference_JOOL {
    public static void main(String[] args) {
        new Example2_2_MethodReference_JOOL().run();
    }

    private String someString = "Lubie placki";

    private void run() {

        // https://github.com/jOOQ/jOOL

        Supplier<String> getter = this::getSomeString;
        Function<Example2_2_MethodReference_JOOL, String> detached_getter = Example2_2_MethodReference_JOOL::getSomeString;

        String bravo = getter.get();
        String attachedBravo = detached_getter.apply(this);


        Consumer<String> setSomeString = this::setSomeString;
        BiConsumer<Example2_2_MethodReference_JOOL, String> detached_setSomeString = Example2_2_MethodReference_JOOL::setSomeString;


        BiConsumer<String, String> printStrings = this::printStrings;
        Consumer3<Example2_2_MethodReference_JOOL, String, String> detached_printStrings = Example2_2_MethodReference_JOOL::printStrings;


        BiFunction<String, String, String> concat = this::concat;
        Function3<Example2_2_MethodReference_JOOL, String, String, String> detached_concat = Example2_2_MethodReference_JOOL::concat;
    }

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
}
