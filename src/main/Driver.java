package main;

import java.util.Scanner;

public class Driver {

	public static void parseInfo(Board boardIn) {
		boolean isParsed = false;
		boolean moveNext = false;
		System.out.println("");
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
					System.out.println(boardIn.toString());
					System.out.println(
							"Selected Positon: (" + Integer.toString(selRow) + "," + Integer.toString(selCol) + ")");
					isParsed = true;
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
	}

	public static int showOptions(Board boardIn) {
		boolean parsed = false;
		Scanner in = new Scanner(System.in);
		String toParseOptions;
		System.out.println("Input values in parentheseis for each option.\n"
				+ "Display the current board (d) or change a value (c).");
		System.out.println("Believe you've solved the board? Input (s)");
		toParseOptions = in.nextLine();
		if (!(toParseOptions.length() > 1)) {
			if (toParseOptions.contains("d")) {
				boardIn.toString();
				return 3;
			} else if (toParseOptions.contains("c")) {
				return 0;
			} else if (toParseOptions.contains("s")) {
				// in.close();
				// return 1;
				// checkSolved(boardIn);
			} else {
				System.out.println("Unknown option.");
			}

		} else {
			while (!parsed) {
				System.out.println("Unknown option.");
				System.out.println("Input values in parentheseis for each option.\n"
						+ "Display the current board (d) or change a value (c).");
				System.out.println("Believe you've solved the board? Input (s)");
				toParseOptions = in.nextLine();
				if (!(toParseOptions.length() > 1)) {
					if (toParseOptions.contains("d")) {
						boardIn.toString();
					} else if (toParseOptions.contains("c")) {
						return 0;
					} else if(toParseOptions.contains("s")) {
						return 1;
					} else {					
						System.out.println("Unknown option.");
					}
				}
			}
		}
		return -1;
	}

	public static void main(String args[]) throws InterruptedException {
		boolean solved = false;
		boolean firstRun = true;
		int selectedOption = -1;
		int[][] exampleValues = new int[][] { { 0, 9, 0, 7, 0, 0, 8, 6, 0 }, { 0, 3, 1, 0, 0, 5, 0, 2, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 7, 0, 5, 0, 0, 0, 6 }, { 0, 0, 0, 3, 0, 7, 0, 0, 0 },
				{ 5, 0, 0, 0, 1, 0, 7, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 9 }, { 0, 2, 0, 6, 0, 0, 3, 5, 0 },
				{ 0, 5, 4, 0, 0, 8, 0, 7, 0 }, };
		Board example = new Board(exampleValues);
		example.whatever = 0;
		System.out.println(example.toString());
		while (!solved) {
			selectedOption = showOptions(example);
			if(selectedOption == 0) {
				parseInfo(example);
				selectedOption = showOptions(example);
			}
			if(selectedOption == 1) {
				selectedOption = showOptions(example);
			}
			
		}
	}
}
