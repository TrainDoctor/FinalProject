package main;

import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	private static void clearScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

	private static void clear(Board boardIn) {
		try {
			clearScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int parseForNum(String in) {
		int parsed = -5;
		try {
			parsed = Integer.parseInt(in);
		} catch (Exception e) {
			System.out.println("Input was not a number.");
		}
		return parsed;
	}
	
	public static boolean isSolved(Board boardIn) {
		boolean out = boardIn.checkSolved();
		return out;
	}

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
			clear(boardIn);
			System.out.println(boardIn.toString());
			System.out.println("Select a row: ");
			System.out.println("Exit any of these prompts by inputting: -2");
			toParse = in.nextLine();
			parsed = parseForNum(toParse);
			if (parsed == -2) {
				clear(boardIn);
				return;
			}
			if (parsed > 0 && parsed <= 9) {
				System.out.println("Row Selected: " + Integer.toString(parsed));
				selRow = parsed;
			}
			while ((parsed < 1 || parsed > 9) && !moveNext) {
				if (parsed == -2) {
					clear(boardIn);
					return;
				}
				clear(boardIn);
				System.out.println(boardIn.toString());
				System.out.println("Invalid row selection.");
				System.out.println("Select a row: ");
				System.out.println("Exit any of these prompts by inputting: -2");
				toParse = in.nextLine();
				parsed = parseForNum(toParse);
				if (parsed == -2) {
					clear(boardIn);
					return;
				}
				if (parsed > 0 && parsed <= 9) {
					System.out.println("Row Selected: " + Integer.toString(parsed));
					moveNext = true;
				}
				selRow = parsed;
			}
			moveNext = false;
			clear(boardIn);
			System.out.println(boardIn.toString());
			System.out.println("Select a column: ");
			System.out.println("Exit any of these prompts by inputting: -2");
			toParse = in.nextLine();
			parsed = parseForNum(toParse);
			if (parsed == -2) {
				clear(boardIn);
				return;
			}
			if (parsed > 0 && parsed <= 9) {
				System.out.println("Column Selected: " + Integer.toString(parsed));
				selCol = parsed;
			}
			while ((parsed < 1 || parsed > 9) && !moveNext) {
				clear(boardIn);
				System.out.println("Invalid column selection.");
				System.out.println("Select a column: ");
				System.out.println("Exit any of these prompts by inputting: -2");
				toParse = in.nextLine();
				parsed = parseForNum(toParse);
				if (parsed > 0 && parsed <= 9) {
					System.out.println("Column Selected: " + Integer.toString(parsed));
					moveNext = true;
				}
				selCol = parsed;
			}
			moveNext = false;
			clear(boardIn);
			//System.out.println(boardIn.toString());
			if (!boardIn.isModable(selRow, selCol)) {
				System.out.println("This value has been set.");
			} else {
				System.out.println(boardIn.toStringSelected(selRow, selCol));
				System.out.println("Input a value for this square.");
				System.out.println("Exit any of these prompts by inputting: -2");
				toParse = in.nextLine();
				parsed = parseForNum(toParse);
				if (parsed == -2) {
					clear(boardIn);
					return;
				}
				if (parsed > 0 && parsed <= 9) {
					value = parsed;
					System.out.println("Value Input: " + Integer.toString(parsed));
					boardIn.setValue(value, selRow, selCol);
					//System.out.println(boardIn.toString());
					System.out.println(
							"Selected Positon: (" + Integer.toString(selRow) + "," + Integer.toString(selCol) + ")");
					isParsed = true;
				}
				while ((parsed < 1 || parsed > 9) && !moveNext) {
					System.out.println("Invalid value, must be 1 to 9.");
					System.out.println("Input a value: ");
					toParse = in.nextLine();
					parsed = parseForNum(toParse);
					if (parsed == -2) {
						clear(boardIn);
						return;
					}
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
			System.out.println("Press any key to proceed.");
			String holdOpen = in.nextLine();
		}
	}

	public static int showOptions(Board boardIn) {
		boolean parsed = false;
		Scanner in = new Scanner(System.in);
		String toParseOptions;
		System.out.println("Input the letters between the parentheseis for each option.\n"
				+ "Display the current board (d), \nrepeat the rules (r) or change a value (c).");
		System.out.println("Believe you've solved the board? Input (s)");
		System.out.println("To exit the game, enter (e)");
		toParseOptions = in.nextLine();
		if (!(toParseOptions.length() > 1)) {
			if (toParseOptions.contains("d")) {
				return 0;
			} else if (toParseOptions.contains("c")) {
				return 1;
			} else if (toParseOptions.contains("r")) {
				return 2;
			} else if (toParseOptions.contains("s")) {
				return 3;
			} else if (toParseOptions.contains("n")) {
				return 4;
			} else if (toParseOptions.contains("o")) {
				return -1;
			} else if (toParseOptions.contains("e")) {
				return -2;
			} else {
				System.out.println("Unknown option.");
			}

		} else {
			while (!parsed) {
				System.out.println("Unknown option.");
				System.out.println("Input values in parentheseis for each option.\n"
						+ "Display the current board (d) or change a value (c).");
				System.out.println("Believe you've solved the board? Input (s)");
				System.out.println("To exit the game, enter (e)");
				toParseOptions = in.nextLine();
				if (!(toParseOptions.length() > 1)) {
					if (toParseOptions.contains("d")) {
						return 0;
					} else if (toParseOptions.contains("c")) {
						return 1;
					} else if (toParseOptions.contains("r")) {
						return 2;
					} else if (toParseOptions.contains("s")) {
						return 3;
					} else if (toParseOptions.contains("n")) {
						return 4;
					} else if (toParseOptions.contains("o")) {
						return -1;
					} else if (toParseOptions.contains("e")) {
						return -2;
					} else {
						System.out.println("Unknown option.");
					}
				}
			}
		}
		return -1;
	}

	public static void boardThree() {
		boolean solved = false;
		int selectedOption = -1;
		int[][] exampleValues = new int[][] { { 0, 9, 0, 7, 0, 0, 8, 6, 0 }, { 0, 3, 1, 0, 0, 5, 0, 2, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 7, 0, 5, 0, 0, 0, 6 }, { 0, 0, 0, 3, 0, 7, 0, 0, 0 },
				{ 5, 0, 0, 0, 1, 0, 7, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 9 }, { 0, 2, 0, 6, 0, 0, 3, 5, 0 },
				{ 0, 5, 4, 0, 0, 8, 0, 7, 0 }, };
		int[][] solvedValues = new int[][] { { 2, 7, 1, 9, 5, 4, 6, 8, 3 }, { 5, 9, 3, 6, 2, 8, 1, 4, 7 },
				{ 4, 6, 8, 1, 3, 7, 2, 5, 9 },

				{ 7, 3, 6, 4, 1, 5, 8, 9, 2 }, { 1, 5, 9, 8, 6, 2, 3, 7, 4 }, { 8, 4, 2, 3, 7, 9, 5, 6, 1 },

				{ 9, 8, 5, 2, 4, 1, 7, 3, 6 }, { 6, 1, 7, 5, 9, 3, 4, 2, 8 }, { 3, 2, 4, 7, 8, 6, 9, 1, 5 } };
		Board example = new Board(exampleValues);
		System.out.println(example.toString());
		System.out.println("Sudoku: Each square must contain the numbers 1-9.");
		System.out.println("No numbers can repeat within a single column or row.\n");
		while (!solved) {
			selectedOption = showOptions(example);
			switch (selectedOption) {
			case -1:
				example.runTest();
				break;
			case 0:
				clear(example);
				System.out.println(example.toString());
				break;
			case 1:
				System.out.println(example.toString());
				parseInfo(example);
				break;
			case 2:
				System.out.println("Sudoku: Each square must contain the numbers 1-9.");
				System.out.println("No numbers can repeat within a single column or row.\n");
				break;
			case 3:
				solved = isSolved(example);
				break;
			default:
				break;
			case -2:
				System.out.println("Are you sure you want to exit?\nEnter (e) to exit, or (c) to continue.");
				Scanner in = new Scanner(System.in);
				while (true) {
					String toParse = in.nextLine();
					if (!(toParse.length() > 1)) {
						if (toParse.contains("e")) {
							return;
						} else if (toParse.contains("c")) {
							System.out.println("Continuing.");
							break;
						} else {
							System.out.println("Unknown option");
						}
					} else {
						System.out.println("Unknown option");
					}
				}
			}
		}
		if (solved) {
			System.out.println("Puzzle solved!\nWell done.");
		}
	}

	public static void boardTwo() {
		boolean solved = false;
		int selectedOption = -1;
		int[][] boardValues = new int[][] { { 0, 4, 0, 8, 0, 5, 2, 0, 0 }, { 0, 2, 0, 0, 4, 0, 0, 5, 0 },
				{ 5, 0, 0, 0, 0, 0, 0, 0, 4 }, { 0, 9, 0, 0, 0, 3, 1, 2, 0 }, { 1, 0, 6, 0, 7, 8, 0, 0, 3 },
				{ 3, 7, 0, 9, 0, 4, 0, 8, 0 }, { 0, 0, 0, 0, 0, 6, 7, 0, 0 }, { 0, 0, 8, 3, 5, 9, 0, 1, 0 },
				{ 0, 1, 9, 0, 0, 7, 6, 0, 0 }, };
		Board example = new Board(boardValues);
		System.out.println(example.toString());
		System.out.println("Sudoku: Each square must contain the numbers 1-9.");
		System.out.println("No numbers can repeat within a single column or row.\n");
		while (!solved) {
			selectedOption = showOptions(example);
			switch (selectedOption) {
			case -1:
				example.runTest();
				break;
			case 0:
				clear(example);
				System.out.println(example.toString());
				break;
			case 1:
				System.out.println(example.toString());
				parseInfo(example);
				break;
			case 2:
				clear(example);
				System.out.println("\nSudoku: Each square must contain the numbers 1-9.");
				System.out.println("No numbers can repeat within a single column or row.\n");
				break;
			case 3:
				clear(example);
				System.out.println(example.toString());
				solved = isSolved(example);
				break;
			default:
				break;
			case -2:
				Scanner in = new Scanner(System.in);
				while (true) {
					System.out.println("Are you sure you want to exit?\nEnter (e) to exit, or (c) to continue.");
					String toParse = in.nextLine();
					if (!(toParse.length() > 1)) {
						if (toParse.contains("e")) {
							return;
						} else if (toParse.contains("c")) {
							System.out.println("Continuing.");
							clear(example);
							break;
						} else {
							System.out.println("Unknown option");
						}
					} else {
						System.out.println("Unknown option");
					}
				}
			}
		}
		if (solved) {
			System.out.println("Puzzle solved!\nWell done.");
		}
	}

	
	public static void main(String args[]) throws InterruptedException {
		// boardOne();
		boardTwo();
		// boardThree();
	}
}
