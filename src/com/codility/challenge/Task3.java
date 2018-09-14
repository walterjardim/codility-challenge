package com.codility.challenge;

public class Task3 {

	public static void main(String[] args) {
		int[] A = {3, 5, 6, 3, 3, 5};
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		int count = 0;
		
		for (int i = 0; i < A.length; i++) {
			int value = A[i];
			
			
			for (int k = i + 1; k < A.length; k++) {
				int otherValue = A[k];
				
				if (value == otherValue) {
					count++;
				}
			}
			
		}
		
		if (count > 1000000000) {
			count = 1000000000;
		}
		
		return count;
    }
}
