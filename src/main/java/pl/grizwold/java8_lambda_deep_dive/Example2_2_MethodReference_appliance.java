package pl.grizwold.java8_lambda_deep_dive;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Example2_2_MethodReference_appliance {
    public static void main(String[] args) {
        new Example2_2_MethodReference_appliance().run();
    }

    class Holder {
        private String holded;

        public Holder(String holded) {
            this.holded = holded;
        }

        public String getHolded() {
            return holded;
        }
    }

    private void run() {
        Function<Holder, String> getHoldedFunction = Holder::getHolded;

        List<Holder> holders = Arrays.asList(
                new Holder("1"),
                new Holder("2"),
                new Holder("3"));
    }
}
