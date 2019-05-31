package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {

	public static void main(String args[]) throws InterruptedException {
		boolean solved = false;
		boolean moveNext = false;
		Scanner in = new Scanner(System.in);
		int[][] exampleValues = new int[][] {
			{0,9,0, 7,0,0, 8,6,0},
			{0,3,1, 0,0,5, 0,2,0},
			{0,0,0, 0,0,0, 0,0,0},
			{0,0,7, 0,5,0, 0,0,6},
			{0,0,0, 3,0,7, 0,0,0},
			{5,0,0, 0,1,0, 7,0,0},		
			{0,0,0, 0,0,0, 1,0,9},
			{0,2,0, 6,0,0, 3,5,0},
			{0,5,4, 0,0,8, 0,7,0},
		};
		Board example = new Board(exampleValues);
		System.out.println(example.toString());
		int selRow = -1, selCol = -1;
		while (!solved) {
			String toParse;
			int parsed = -1;
			System.out.println("Select a row: ");
			toParse = in.nextLine();
			parsed = Integer.parseInt(toParse);
			if (parsed > 0 && parsed <= 9)
				selRow = parsed;
			while (parsed <= 0 && parsed > 9 && !moveNext) {
				System.out.println("Invalid row selection.");
				System.out.println("Select a row: ");
				toParse = in.nextLine();
				parsed = Integer.parseInt(toParse);
				if (parsed > 0 && parsed <= 9)
					moveNext = true;
				selRow = parsed;
			}
			moveNext = false;
			System.out.println("Select a column: ");
			toParse = in.nextLine();
			parsed = Integer.parseInt(toParse);
			if (parsed > 0 && parsed <= 9)
				selCol = parsed;
			while (parsed <= 0 && parsed > 9 && !moveNext) {
				System.out.println("Invalid column selection.");
				System.out.println("Select a column: ");
				toParse = in.nextLine();
				parsed = Integer.parseInt(toParse);
				if (parsed > 0 && parsed <= 9)
					moveNext = true;
				selCol = parsed;
			}
			moveNext = false;
			if (!example.isModable(selRow, selCol)) {
				System.out.println("This value has been set.");
			} else {
				System.out.println(example.toStringSelected(selRow, selCol));
				System.out.println("Input a value for this square.");
				toParse = in.nextLine();
				parsed = Integer.parseInt(toParse);
				if (parsed <= 9)
					selCol = parsed;
				while (parsed <= 0 && parsed > 9 && !moveNext) {
					System.out.println("Invalid value, must be 1 to 9.");
					System.out.println("Input a value: ");
					toParse = in.nextLine();
					parsed = Integer.parseInt(toParse);
					if (parsed > 0 && parsed <= 9)
						moveNext = true;
					example.setValue(parsed, selRow, selCol);
					System.out.println(example.toStringSelected(selRow, selCol));
					TimeUnit.SECONDS.sleep(5);
					System.out.flush();
					System.out.println(example.toString());
				}
				moveNext = false;
			}
			// solved = true;
		}
		in.close();
	}
}
