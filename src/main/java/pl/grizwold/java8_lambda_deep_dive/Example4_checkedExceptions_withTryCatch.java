package pl.grizwold.java8_lambda_deep_dive;

import pl.grizwold.java8_lambda_deep_dive.exception.PancakeException;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example4_checkedExceptions_withTryCatch {
    public static void main(String[] args) {
        new Example4_checkedExceptions_withTryCatch().run();
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

    private <T> T uncheck(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            return sneakyThrow(e);
        }
    }

    private <E extends Throwable, T> T sneakyThrow(Exception exception) throws E {
        throw (E) exception;
    }
}

