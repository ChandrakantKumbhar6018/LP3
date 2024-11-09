import java.util.Scanner;

class FibonacciSeries {
  public static void fibonacciIterative(int n) {
    int[] series = new int[n];
    if (n >= 1)
      series[0] = 0;
    if (n >= 2)
      series[1] = 1;

    for (int i = 2; i < n; i++) {
      series[i] = series[i - 1] + series[i - 2];
    }

    System.out.print("Fibonacci Series (Iterative): ");
    for (int i = 0; i < n; i++) {
      System.out.print(series[i] + " ");
    }
    System.out.println();
  }

  public static int fibonacciRecursive(int n) {
    if (n <= 1) {
      return n;
    }
    return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
  }

  public static void printFibonacciSeriesRecursive(int n) {
    System.out.print("Fibonacci Series (Recursive): ");
    for (int i = 0; i < n; i++) {
      System.out.print(fibonacciRecursive(i) + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the number of elements for the Fibonacci series: ");
    int n = scanner.nextInt();

    fibonacciIterative(n);
    printFibonacciSeriesRecursive(n);

    scanner.close();
  }
}
