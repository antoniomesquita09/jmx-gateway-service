package org.jmx.gateway.service.com.helper;

public class MainFibonacci {
    /**
     * @param args command line args not used
     */
    public static void main(String[] args) {
        while(true) {
            for(int i=0; i< 100; i++) {
                System.out.println("Fibonacci(" + i + ") = " + fibonacci(i));
            }
        }

    }

    private static long fibonacci(int n) {
        if(n<2) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
