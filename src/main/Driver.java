package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {

	public static void parseInfo(Board boardIn) throws InterruptedException {
		boolean isParsed = false;
		boolean moveNext = false;
		Scanner in = new Scanner(System.in);
		int selRow = -1, selCol = -1;
		int value = -1;
		while (!isParsed) {
			String toParse;
			int parsed = -1;
			System.out.println("Select a row: ");
			toParse = in.nextLine();
			parsed = Integer.parseInt(toParse);
			if (parsed > 0 && parsed <= 9) {
				System.out.println("Row Selected: " + Integer.toString(parsed));
				selRow = parsed;
			}
			while ((parsed < 1 || parsed > 9) && !moveNext) {
				System.out.println("Invalid row selection.");
				System.out.println("Select a row: ");
				toParse = in.nextLine();
				parsed = Integer.parseInt(toParse);
				if (parsed > 0 && parsed <= 9) {
					System.out.println("Row Selected: " + Integer.toString(parsed));
					moveNext = true;
				}
				selRow = parsed;
			}
			moveNext = false;
			System.out.println("Select a column: ");
			toParse = in.nextLine();
			parsed = Integer.parseInt(toParse);
			if (parsed > 0 && parsed <= 9) {
				System.out.println("Column Selected: " + Integer.toString(parsed));
				selCol = parsed;
			}
			while ((parsed < 1 || parsed > 9) && !moveNext) {
				System.out.println("Invalid column selection.");
				System.out.println("Select a column: ");
				parsed = Integer.parseInt(toParse);
				if (parsed > 0 && parsed <= 9) {
					System.out.println("Column Selected: " + Integer.toString(parsed));
					moveNext = true;
				}
				selCol = parsed;
			}
			moveNext = false;
			if (!boardIn.isModable(selRow, selCol)) {
				System.out.println("This value has been set.");
			} else {
				System.out.println(boardIn.toStringSelected(selRow, selCol));
				System.out.println("Input a value for this square.");
				toParse = in.nextLine();
				parsed = Integer.parseInt(toParse);
				if (parsed > 0 && parsed <= 9) {
					value = parsed;
					System.out.println("Value Input: " + Integer.toString(parsed));
					boardIn.setValue(value, selRow, selCol);
					TimeUnit.SECONDS.sleep(5);
					System.out.println(boardIn.toString());
					System.out.println(
							"Selected Positon: (" + Integer.toString(selRow) + "," + Integer.toString(selCol) + ")");
				}
				while ((parsed < 1 || parsed > 9) && !moveNext) {
					System.out.println("Invalid value, must be 1 to 9.");
					System.out.println("Input a value: ");
					toParse = in.nextLine();
					parsed = Integer.parseInt(toParse);
					if (parsed > 0 && parsed <= 9) {
						moveNext = true;
						System.out.println("Value Input: " + Integer.toString(parsed));
						boardIn.setValue(value, selRow, selCol);
						// TimeUnit.SECONDS.sleep(2);
						System.out.println(boardIn.toString());
						System.out.println("Selected Positon: (" + Integer.toString(selRow) + ","
								+ Integer.toString(selCol) + ")");
						isParsed = true;
					}
				}
				moveNext = false;
			}
		}
		in.close();
	}

	public static int showOptions(Board boardIn) {
		Scanner in = new Scanner(System.in);
		System.out.println("Input values in parentheseis for each option.\n" 
				+ "Display the current board (d) or change a value (c).");
		System.out.println("Believe you've solved the board? Input (s)");
		in.nextLine();
		return -1;
	}

	public static void main(String args[]) throws InterruptedException {
		boolean solved = false;
		int[][] exampleValues = new int[][] { { 0, 9, 0, 7, 0, 0, 8, 6, 0 }, { 0, 3, 1, 0, 0, 5, 0, 2, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 7, 0, 5, 0, 0, 0, 6 }, { 0, 0, 0, 3, 0, 7, 0, 0, 0 },
				{ 5, 0, 0, 0, 1, 0, 7, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 9 }, { 0, 2, 0, 6, 0, 0, 3, 5, 0 },
				{ 0, 5, 4, 0, 0, 8, 0, 7, 0 }, };
		Board example = new Board(exampleValues);
		System.out.println(example.toString());
		while (!solved) {
			showOptions(example);
			parseInfo(example);
		}
	}
}
