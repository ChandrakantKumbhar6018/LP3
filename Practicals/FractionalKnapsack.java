import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    double weight, value, ratio;

    Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
        this.ratio = value / weight; // value-to-weight ratio
    }
}

public class FractionalKnapsack {

    public static double getMaxValue(Item[] items, double capacity) {
        // Using an array to store step count as it can be modified within the
        // Comparator
        int[] stepCount = { 0 };

        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                stepCount[0]++;
                return Double.compare(b.ratio, a.ratio);
            }
        });

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity == 0)
                break;

            // If item can be fully added
            if (item.weight <= capacity) {
                stepCount[0]++;
                capacity -= item.weight;
                totalValue += item.value;
            }
            // If only a fraction of the item can be added
            else {
                stepCount[0]++;
                totalValue += item.ratio * capacity;
                capacity = 0; // knapsack is now full
            }
        }

        System.out.println("Step Count: " + stepCount[0]);
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];

        System.out.println("Enter the weight and value of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " Weight: ");
            double weight = scanner.nextDouble();
            System.out.print("Item " + (i + 1) + " Value: ");
            double value = scanner.nextDouble();
            items[i] = new Item(weight, value);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        double capacity = scanner.nextDouble();

        long startTime = System.nanoTime();
        double maxValue = getMaxValue(items, capacity);
        long endTime = System.nanoTime();

        System.out.println("\nMaximum value in the knapsack = " + maxValue);
        System.out.println("Time taken (in nanoseconds): " + (endTime - startTime));
        System.out.println("Time Complexity: O(n log n) due to sorting");
        System.out.println("Space Complexity: O(n) for storing items array");

        scanner.close();
    }
}
