package aod.lab2;

import java.util.Random;

public class BST_Time {

	private static final int[] SIZES = { 5000, 10000, 20000, 40000, 80000 };
	private static final int SEARCHES = 100000;
	private static final int MAX_RANDOM = 10000000;

	public static void main(String[] args) {
		System.out.println("Random integers");
		runRandomExperiment();

		System.out.println();
		System.out.println("Sorted integers");
		
		try {
	        runSortedExperiment();
	    } catch (StackOverflowError e) {
	        System.out.println("BST became too deep for recursion.");
	    }
	}

	private static void runRandomExperiment() {
		long previousTime = -1;

		System.out.printf("%10s %15s %15s%n", "n", "Tid (ms)", "T(2n)/T(n)");

		for (int n : SIZES) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<>();
			Random random = new Random(42);

			for (int i = 0; i < n; i++) {
				tree.add(random.nextInt(MAX_RANDOM));
			}

			int[] searchValues = new int[SEARCHES];
			for (int i = 0; i < SEARCHES; i++) {
				searchValues[i] = random.nextInt(MAX_RANDOM);
			}

			long start = System.nanoTime();

			int foundCount = 0;
			for (int value : searchValues) {
				if (tree.searchFor(value)) {
					foundCount++;
				}
			}

			long end = System.nanoTime();
			long elapsedNs = end - start;
			double elapsedMs = elapsedNs / 1_000_000.0;

			String quotient = "-";
			if (previousTime != -1) {
				quotient = String.format("%.3f", (double) elapsedNs / previousTime);
			}

			System.out.printf("%10d %15.3f %15s%n", n, elapsedMs, quotient);
			previousTime = elapsedNs;

			// Bara för att undvika att kompilatorn optimerar bort något
			if (foundCount == -1) {
				System.out.println("Impossible");
			}
		}
	}

	private static void runSortedExperiment() {
		long previousTime = -1;

		System.out.printf("%10s %15s %15s%n", "n", "Tid (ms)", "T(2n)/T(n)");

		for (int n : SIZES) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<>();

			for (int i = 0; i < n; i++) {
				tree.add(i);
			}

			Random random = new Random(42);
			int[] searchValues = new int[SEARCHES];
			for (int i = 0; i < SEARCHES; i++) {
				searchValues[i] = random.nextInt(n * 2);
			}

			long start = System.nanoTime();

			int foundCount = 0;
			for (int value : searchValues) {
				if (tree.searchFor(value)) {
					foundCount++;
				}
			}

			long end = System.nanoTime();
			long elapsedNs = end - start;
			double elapsedMs = elapsedNs / 1_000_000.0;

			String quotient = "-";
			if (previousTime != -1) {
				quotient = String.format("%.3f", (double) elapsedNs / previousTime);
			}

			System.out.printf("%10d %15.3f %15s%n", n, elapsedMs, quotient);
			previousTime = elapsedNs;

			if (foundCount == -1) {
				System.out.println("Impossible");
			}
		}
	}
}
