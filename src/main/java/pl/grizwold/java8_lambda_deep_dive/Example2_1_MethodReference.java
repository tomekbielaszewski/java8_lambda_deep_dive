package pl.grizwold.java8_lambda_deep_dive;

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
//        this::getSomeString;
//        Example2_1_MethodReference::getSomeString;

//        this::setSomeString;
//        Example2_1_MethodReference::setSomeString;

//        this::printStrings;
//        Example2_1_MethodReference::printStrings;

//        this::concat;
//        Example2_1_MethodReference::concat;
    }
}
