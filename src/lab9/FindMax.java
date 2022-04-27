package lab9;

/**
 *
 * COMP 3021
 *
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29]
another thread the max among the cells [30,59]
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 *
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		Max max1 = new Max(0, 29);
		Max max2 = new Max(30, 59);
		Max max3 = new Max(60, 89);

		Thread thread1 = new Thread(max1);
		Thread thread2 = new Thread(max2);
		Thread thread3 = new Thread(max3);

		// Start threads
		thread1.start();
		thread2.start();
		thread3.start();

		try {
			thread1.join(); // wait until t1,t2,t3 is done
			thread2.join();
			thread3.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}


		int max_result = Math.max(max1.result, max2.result);
		max_result = Math.max(max_result, max3.result);
		System.out.println("the max value is " + max_result);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	class Max implements Runnable {
		private int begin;
		private int end;
		private int result;

		public Max(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}

		@Override
		public void run(){
			result = findMax(begin, end);
		}
	}

}
