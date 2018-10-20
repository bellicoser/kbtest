package com.example.kb;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// original array compare sort array
		// if arr1[i] compareTo arr2[i] and differ = 2 return true otherwise return
		// false

		int[] A = { 2, 5, 3, 3, 7 };// true 2 3 3 5 7 (2)
		int[] B = { 1, 3, 5, 2, 4 };// false 1 2 3 4 5 (1)
		int[] C = { 1, 5, 3, 3, 7 };// true 1 3 3 5 7 (2)
		int[] D = { 1, 5, 3, 3, 4, 1, 10 }; // true 1 1 3 3 4 5 10

		//System.out.println(isNonDecreasing(A));
		//System.out.println(isNonDecreasing(B));
		//System.out.println(isNonDecreasing(C));
		//System.out.println(isNonDecreasing(D));
		
		System.out.println(answer(A));
		System.out.println(answer(B));
		System.out.println(answer(C));
		System.out.println(answer(D));
	}

	public static boolean answer(int[] A) {
		int[] B= A.clone();
		Arrays.sort(A);
		int count = 0;
		for (int i=0;i<A.length;i++) {
			System.out.print(B[i]+"-"+A[i]+" ");
			if (B[i] != A[i]) count++;
		}
		System.out.print("\ncount : "+count+" ");
		if (count == 2) return true;
		return false;
	}

	public static boolean isNonDecreasing(int[] A) {
		int ProbIndex = findProbIndex(A);
		int SmallIndex = findSmallIndex(A, ProbIndex);
		// System.out.println("ProbIndex "+ProbIndex);
		// System.out.println("tempProbValue "+A[ProbIndex]);
		// System.out.println("SmallIndex "+SmallIndex);
		// System.out.println("tempMinValue "+A[SmallIndex]);
		int tempProbValue = A[ProbIndex];
		int tempMinValue = A[SmallIndex];
		A[ProbIndex] = tempMinValue;
		A[SmallIndex] = tempProbValue;
		for (int a : A) {
			System.out.print(a + " ");
		}
		return isSorted(A, ProbIndex);
	}

	public static int findProbIndex(int[] A) {
		int tempIndex = 0;
		int prev = A[0];
		for (int i = 1; i < A.length; i++) {
			if (prev > A[i]) {
				tempIndex = i - 1;
				break;
			}
			prev = A[i];
		}
		return tempIndex;
	}

	public static int findSmallIndex(int[] A, int ProbIndex) {
		int smallIndex = A[ProbIndex];
		int prev = A[ProbIndex];
		for (int i = ProbIndex; i < A.length; i++) {
			if (prev >= A[i]) {
				smallIndex = i;
				prev = A[i];
			}
		}
		return smallIndex;
	}

	public static boolean isSorted(int[] A, int ProbIndex) {
		int prev = A[ProbIndex];
		for (int i = ProbIndex + 1; i < A.length; i++) {
			if (A[i] < prev) {
				return false;
			}
			prev = A[i];
		}
		return true;
	}
}
