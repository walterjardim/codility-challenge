package com.codility.challenge;

import java.util.Arrays;

public class Task2 {

	public static void main(String[] args) {
		System.out.println(solution(4, "1B 2C, 2D 4D", "2B 2D 3D 4D 4A"));
	}
	
	public static String solution(int N, String S, String T) {
		String[][] map = new String[N][N];
		String[] ships = S.split(",");
		String cells = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[] hits = T.split(" ");
		int hitCount = 0;
		int sunks = 0;
		int[] originalShipParts = new int[ships.length];
		
		for (int i = 0; i < ships.length; i++) {
			int part = 0;
			String ship = ships[i].trim();
			
			String[] positions = ship.split(" ");
			String initialPosition = positions[0];
			String finalPosition = positions[1];
			
			int initialRow = Integer.parseInt(initialPosition.substring(0, initialPosition.length() - 1)) - 1;
			int finalRow = Integer.parseInt(finalPosition.substring(0, finalPosition.length() - 1)) - 1;
			
			String strInitiCell = initialPosition.substring(initialPosition.length() - 1, initialPosition.length());
			String strFinalCell = finalPosition.substring(finalPosition.length() - 1, finalPosition.length());
			
			int initialCell = cells.indexOf(strInitiCell);
			int finalCell = cells.indexOf(strFinalCell);
			
			for (int k = initialRow; k <= finalRow; k++) {
				for (int j = initialCell; j <= finalCell; j++) {
					map[k][j] = String.valueOf(i);
					originalShipParts[i] = ++part;
				}
			}
			
		}
		
		int[] removedShipParts = Arrays.copyOf(originalShipParts, originalShipParts.length);
		
		for (int i = 0; i < hits.length; i++) {
			String hitPosition = hits[i].trim();
			
			int row = Integer.parseInt(hitPosition.substring(0, hitPosition.length() - 1)) - 1;
			String strCell = hitPosition.substring(hitPosition.length() - 1, hitPosition.length());
			int cell = cells.indexOf(strCell);
			String ship = map[row][cell];
			
			if (ship != null) {
				int shipIndex = Integer.parseInt(ship);
				int part = removedShipParts[shipIndex];
				removedShipParts[shipIndex] = part - 1;				
			}
			
		}
		
		for (int i = 0; i < removedShipParts.length; i++) {
			if (removedShipParts[i] == 0) {
				sunks++;
			} else if (removedShipParts[i] < originalShipParts[i]) {
				hitCount++;
			}
		}
		
		return sunks + "," + hitCount;
    }
}
