import java.util.Scanner;

class FibonacciCalculator {

  // Step count variable
  static int stepCountIterative = 0;
  static int stepCountRecursive = 0;

  // Iterative approach for Fibonacci
  public static int fibonacciIterative(int n) {
    if (n <= 1)
      return n;

    int a = 0, b = 1, c = 0;
    for (int i = 2; i <= n; i++) {
      stepCountIterative++;
      c = a + b;
      a = b;
      b = c;
    }
    return c;
  }

  // Recursive approach with memoization
  public static int fibonacciRecursive(int n, int[] memo) {
    stepCountRecursive++;
    if (n <= 1)
      return n;
    if (memo[n] != -1)
      return memo[n];

    memo[n] = fibonacciRecursive(n - 1, memo) + fibonacciRecursive(n - 2, memo);
    return memo[n];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the Fibonacci index (n): ");
    int n = scanner.nextInt();

    // Iterative approach
    long startIterative = System.nanoTime();
    int fibIterative = fibonacciIterative(n);
    long endIterative = System.nanoTime();

    System.out.println("\nIterative Approach:");
    System.out.println("Fibonacci(" + n + ") = " + fibIterative);
    System.out.println("Step Count (Iterative): " + stepCountIterative);
    System.out.println("Time taken (Iterative): " + (endIterative - startIterative) + " ns");
    System.out.println("Space Complexity (Iterative): O(1)");

    // Recursive approach with memoization
    int[] memo = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      memo[i] = -1;
    }

    long startRecursive = System.nanoTime();
    int fibRecursive = fibonacciRecursive(n, memo);
    long endRecursive = System.nanoTime();

    System.out.println("\nRecursive Approach with Memoization:");
    System.out.println("Fibonacci(" + n + ") = " + fibRecursive);
    System.out.println("Step Count (Recursive): " + stepCountRecursive);
    System.out.println("Time taken (Recursive): " + (endRecursive - startRecursive) + " ns");
    System.out.println("Space Complexity (Recursive): O(n)");

    scanner.close();
  }
}
