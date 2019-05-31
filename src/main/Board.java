package main;

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
		return values[row][col];
	}

	public boolean isModable(int row, int col) {
		return modifiable[row][col];
	}

	public void setValue(int value, int row, int col) {
		if (value <= 0 || value > 9)
			System.out.println("ERROR: INVALID VALUE PASSED!");
		else
			values[row][col] = value;
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
		out += "\n";

		return out;
	}

	public String toStringSelected(int row, int col) {
		String out = "";
		String replacement = "?";
		out += "|---|---|---||---|---|---||---|---|---|\n";
		out += "|---|---|---||---|---|---||---|---|---|\n";
		for (int x = 0; x < 9; x++) {
			String[] outputs = { Integer.toString(values[x][0]), Integer.toString(values[x][1]),
					Integer.toString(values[x][2]), Integer.toString(values[x][3]), Integer.toString(values[x][4]),
					Integer.toString(values[x][5]), Integer.toString(values[x][6]), Integer.toString(values[x][7]),
					Integer.toString(values[x][8]) };
			if (x == row) {
				switch (col) {
				case 1:
					outputs[0] = replacement;
					break;
				case 2:
					outputs[1] = replacement;
					break;
				case 3:
					outputs[2] = replacement;
					break;
				case 4:
					outputs[3] = replacement;
					break;
				case 5:
					outputs[4] = replacement;
					break;
				case 6:
					outputs[5] = replacement;
					break;
				case 7:
					outputs[6] = replacement;
					break;
				case 8:
					outputs[7] = replacement;
					break;
				case 9:
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
