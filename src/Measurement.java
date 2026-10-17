public class Measurement {

    /*
     * Doubles n = 1, 2, 4, ... and returns the last n for which pushing then popping
     * n elements on an LLStack took at most t milliseconds. Returns 0 if even n = 1 was too slow.
     */
    static long largestNLL(long t) {
        long prior = 0;
        for (long n = 1; n <= (1L << 30); n *= 2) {
            LLStack s = LLStack.emptyStack();
            long startTime = System.nanoTime();
            for (long i = 0; i < n; i++) {
                s.push("x");
            }
            for (long i = 0; i < n; i++) {
                s.pop();
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            if (duration > t) {
                return prior;
            }
            prior = n;
        }
        return prior;
    }

    // Same as largestNLL, but for an AStack.
    static long largestNA(long t) {
        long prior = 0;
        for (long n = 1; n <= (1L << 30); n *= 2) {
            AStack s = AStack.emptyStack();
            long startTime = System.nanoTime();
            for (long i = 0; i < n; i++) {
                s.push("x");
            }
            for (long i = 0; i < n; i++) {
                s.pop();
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            if (duration > t) {
                return prior;
            }
            prior = n;
        }
        return prior;
    }

    public static void main(String[] args) {
        largestNLL(50);
        largestNA(50);

        System.out.println("LLStack (tenths_of_second largest_n)");
        for (int tenths = 1; tenths <= 10; tenths++) {
            System.out.println(tenths + " " + largestNLL(tenths * 100L));
        }
        System.out.println("AStack (tenths_of_second largest_n)");
        for (int tenths = 1; tenths <= 10; tenths++) {
            System.out.println(tenths + " " + largestNA(tenths * 100L));
        }
    }

}
