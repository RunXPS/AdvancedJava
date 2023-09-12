import java.lang.Math;

public class CreateField {
    public static void main(String[] args) {
        int size = 5, mines = 2;
        int[][] test = generateField(size, mines);
        displayField(size, test);
    }

    public static void displayField(int size, int[][] field) {
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                if (field[i][j] == -1) {
                    System.out.print("X ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println("|");
        }
    }

    private static int[][] generateField(int size, int mines) {
        int[][] field = new int[size][size];
        for (int i = 0; i < mines; i++) {
            int x = (int) (Math.random() * size), y = (int) (Math.random() * size);
            field[x][y] = -1;
            int k = x - 1, l = y - 1;
            for (; k <= x + 1; k++) {
                // if row is an end
                if (k < size && k >= 0) {
                    for (l = y - 1; l <= y + 1; l++) {
                        // if column is an end
                        if ((l < size && l >= 0)) {
                            if (field[k][l] != -1) {
                                field[k][l] += 1;
                            }
                        }
                    }
                }
            }
        }
        return field;
    }
}
