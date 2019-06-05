package main;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private int[][] values = new int[9][9];
	private boolean[][] modifiable = new boolean[9][9];

	public Board(int[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				values[row][col] = board[row][col];
				if (values[row][col] == 0) {
					modifiable[row][col] = true;
				} else {
					modifiable[row][col] = false;
				}
			}
		}
	}

	public int getValue(int row, int col) {
		return values[row - 1][col - 1];
	}

	public boolean isModable(int row, int col) {
		return modifiable[row - 1][col - 1];
	}

	public void setValue(int value, int row, int col) {
		if (value <= 0 || value > 9)
			System.out.println("ERROR: INVALID VALUE PASSED!");
		else
			values[row - 1][col - 1] = value;
	}

	// returns true if row has no duplicate numbers
	private boolean checkRow(int rowNum) {
		boolean out = true;
		int[] row = new int[9];
		for (int x = 0; x < 9; x++) {
			row[x] = values[rowNum][x];
		}
		for (int x = 0; x < row.length - 1; x++) {
			int indMin = x;
			for (int a = x + 1; a < row.length; a++) {
				if (row[a] < row[indMin]) {
					indMin = a;
				}
			}
			int temp = row[indMin];
			row[indMin] = row[x];
			row[x] = temp;
		}
		for (int x = 1; x < 8; x++) {
			if (row[x] == row[x - 1]) {
				out = false;
			}
			if (!out) {
				return out;
			}
		}
		return out;
	}

	private boolean checkColumn(int colNum) {
		boolean out = true;
		int[] col = new int[9];
		for (int x = 0; x < 9; x++) {
			col[x] = values[x][colNum];
		}
		for (int x = 0; x < col.length - 1; x++) {
			int indMin = x;
			for (int a = x + 1; a < col.length; a++) {
				if (col[a] < col[indMin]) {
					indMin = a;
				}
			}
			int temp = col[indMin];
			col[indMin] = col[x];
			col[x] = temp;
		}
		for (int x = 1; x < 8; x++) {
			if (col[x] == col[x - 1]) {
				out = false;
			}
			if (!out) {
				return out;
			}
		}
		return out;
	}

	private boolean checkBox(int boxNum) {
		boolean out = true;
		int[] boxValues = new int[9];
		int minCol = -1, minRow = -1, maxCol = -1, maxRow = -1;
		int index = 0;
		switch (boxNum) {
		case 0:
			minRow = 0;
			maxRow = 2;
			minCol = 0;
			maxCol = 2;
			break;
		case 1:
			minRow = 0;
			maxRow = 2;
			minCol = 3;
			maxCol = 5;
			break;
		case 2:
			minRow = 0;
			maxRow = 2;
			minCol = 6;
			maxCol = 8;
			break;
		case 3:
			minRow = 3;
			maxRow = 5;
			minCol = 0;
			maxCol = 2;
			break;
		case 4:
			minRow = 3;
			maxRow = 5;
			minCol = 3;
			maxCol = 5;
			break;
		case 5:
			minRow = 3;
			maxRow = 5;
			minCol = 6;
			maxCol = 8;
			break;
		case 6:
			minRow = 6;
			maxRow = 8;
			minCol = 0;
			maxCol = 2;
			break;
		case 7:
			minRow = 6;
			maxRow = 8;
			minCol = 3;
			maxCol = 5;
			break;
		case 8:
			minRow = 6;
			maxRow = 8;
			minCol = 6;
			maxCol = 8;
			break;
		}
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				boxValues[index] = values[row][col];
				index++;
			}
		}
		for (int x = 0; x < boxValues.length - 1; x++) {
			int indMin = x;
			for (int a = x + 1; a < boxValues.length; a++) {
				if (boxValues[a] < boxValues[indMin]) {
					indMin = a;
				}
			}
			int temp = boxValues[indMin];
			boxValues[indMin] = boxValues[x];
			boxValues[x] = temp;
		}
		for (int x = 1; x < 8; x++) {
			if (boxValues[x] == boxValues[x - 1]) {
				out = false;
			}
			if (!out) {
				return out;
			}
		}
		return out;
	}

	public void runTest() {
		checkRow(0);
		checkColumn(0);
		checkBox(0);
	}

	private ArrayList<Integer> checkRows() {
		ArrayList<Integer> rowsWithDupes = new ArrayList<Integer>();
		boolean[] rowState = new boolean[9];
		for (int i = 0; i < 9; i++) {
			rowState[i] = checkColumn(i);
		}
		for (int i = 0; i < 9; i++) {
			if (!rowState[i]) {
				rowsWithDupes.add(i);
			}
		}
		return rowsWithDupes;
	}

	private ArrayList<Integer> checkColumns() {
		ArrayList<Integer> colsWithDupes = new ArrayList<Integer>();
		boolean[] colState = new boolean[9];
		for (int i = 0; i < 9; i++) {
			colState[i] = checkColumn(i);
		}
		for (int i = 0; i < 9; i++) {
			if (!colState[i]) {
				colsWithDupes.add(i);
			}
		}
		return colsWithDupes;
	}

	private ArrayList<Integer> checkBoxes() {
		ArrayList<Integer> boxesWithDupes = new ArrayList<Integer>();
		boolean[] boxState = new boolean[9];
		for (int i = 0; i < 9; i++) {
			boxState[i] = checkBox(i);
		}
		for (int i = 0; i < 9; i++) {
			if (!boxState[i]) {
				boxesWithDupes.add(i);
			}
		}
		return boxesWithDupes;
	}

	private ArrayList<Integer>[] checkBoard() {
		List<Integer>[] checks = new ArrayList[3];
		//ArrayList<Integer>[] checks = new ArrayList<Integer>[3];
		ArrayList<Integer> boxDupe = checkBoxes();
		checks[0] = boxDupe;
		ArrayList<Integer> rowDupe = checkRows();
		checks[1] = rowDupe;
		ArrayList<Integer> colDupe = checkColumns();
		checks[2] = colDupe;
		return (ArrayList<Integer>[]) checks;
	}

	public boolean checkSolved() {
		ArrayList<Integer>[] boardStates = checkBoard();
		String temp = "";
		boolean out = true;
		boolean[] dupeValuesFound = { true, true, true };
		// provide responses for box states
		if (!(boardStates[0].size() < 1)) {
			dupeValuesFound[0] = false;
			temp = "Boxes: ";
			for (int x = 0; x < boardStates[0].size(); x++) {
				if (x < 8)
					temp += Integer.toString((boardStates[0].get(x) + 1)) + ", ";
				else
					temp += "and " + Integer.toString((boardStates[0].get(x) + 1));
			}
			temp += " have duplicate values.";
			System.out.println(temp);
		} else {
			dupeValuesFound[0] = true;
		}
		if (!(boardStates[1].size() < 1)) {
			dupeValuesFound[1] = false;
			temp = "Rows: ";
			for (int x = 0; x < boardStates[1].size(); x++) {
				if (x < 8)
					temp += Integer.toString((boardStates[1].get(x) + 1)) + ", ";
				else
					temp += "and " + Integer.toString((boardStates[1].get(x) + 1));
			}
			temp += " have duplicate values.";
			System.out.println(temp);
		} else {
			dupeValuesFound[1] = true;
		}
		if (!(boardStates[2].size() < 1)) {
			dupeValuesFound[2] = false;
			temp = "Columns: ";
			for (int x = 0; x < boardStates[2].size(); x++) {
				if (x < 8)
					temp += Integer.toString((boardStates[2].get(x) + 1)) + ", ";
				else
					temp += "and " + Integer.toString((boardStates[2].get(x) + 1));
			}
			temp += " have duplicate values.";
			System.out.println(temp);
		} else {
			dupeValuesFound[2] = true;
		}
		for (int i = 0; i < 3; i++) {
			if (dupeValuesFound[i] != true) {
				out = false;
			}
		}
		System.out.println("");
		return out;
	}

	public String toString() {
		String out = "";
		out += "|---|---|---||---|---|---||---|---|---|\n";
		out += "|---|---|---||---|---|---||---|---|---|\n";
		for (int row = 0; row < 9; row++) {
			out += "| " + Integer.toString(values[row][0]) + " | " + Integer.toString(values[row][1]) + " | "
					+ Integer.toString(values[row][2]) + " ||";
			out += " " + Integer.toString(values[row][3]) + " | " + Integer.toString(values[row][4]) + " | "
					+ Integer.toString(values[row][5]) + " ||";
			out += " " + Integer.toString(values[row][6]) + " | " + Integer.toString(values[row][7]) + " | "
					+ Integer.toString(values[row][8]) + " |\n";
			out += "|---|---|---||---|---|---||---|---|---|\n";
			if (row == 2 || row == 5 || row == 8) {
				out += "|---|---|---||---|---|---||---|---|---|\n";
			}
		}
		//out += "";

		return out;
	}

	public String toStringSelected(int row, int col) {
		int rowOut = row - 1;
		int colOut = col - 1;
		String out = "";
		String replacement = "?";
		out += "|---|---|---||---|---|---||---|---|---|\n";
		out += "|---|---|---||---|---|---||---|---|---|\n";
		for (int x = 0; x < 9; x++) {
			String[] outputs = { Integer.toString(values[x][0]), Integer.toString(values[x][1]),
					Integer.toString(values[x][2]), Integer.toString(values[x][3]), Integer.toString(values[x][4]),
					Integer.toString(values[x][5]), Integer.toString(values[x][6]), Integer.toString(values[x][7]),
					Integer.toString(values[x][8]) };
			if (x == rowOut) {
				switch (colOut) {
				case 0:
					outputs[0] = replacement;
					break;
				case 1:
					outputs[1] = replacement;
					break;
				case 2:
					outputs[2] = replacement;
					break;
				case 3:
					outputs[3] = replacement;
					break;
				case 4:
					outputs[4] = replacement;
					break;
				case 5:
					outputs[5] = replacement;
					break;
				case 6:
					outputs[6] = replacement;
					break;
				case 7:
					outputs[7] = replacement;
					break;
				case 8:
					outputs[8] = replacement;
					break;
				}
			}
			out += "| " + outputs[0] + " | " + outputs[1] + " | " + outputs[2] + " ||";
			out += " " + outputs[3] + " | " + outputs[4] + " | " + outputs[5] + " ||";
			out += " " + outputs[6] + " | " + outputs[7] + " | " + outputs[8] + " |\n";
			out += "|---|---|---||---|---|---||---|---|---|\n";
			if (x == 2 || x == 5 || x == 8) {
				out += "|---|---|---||---|---|---||---|---|---|\n";
			}
		}
		out += "\n";
		return out;
	}

}
