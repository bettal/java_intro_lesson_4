/**
 * Java.1.Lesson.4.Homework
 *
 * @autor Stanislav Lyashov
 */
import java.util.Random;
import java.util.Scanner;

class HwLesson4 {

    final int SIZE = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new HwLesson4().game();
    }

    void game() {
        initMap();
        while (true) {
            turnHuman();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW...");
                break;
            }
            turnAi();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("AI WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW...");
                break;
            }
        }
        System.out.println("GAME OVER!");
        printMap();
    }

    void initMap() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1.." + SIZE + "):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void turnAi() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    boolean checkWin(char dot) {
        boolean cols, rows, diag1, diag2;
        //по сколонкам
        for (int i=0; i<SIZE; i++) {
            //предполагаем совпадение в сравнении
            cols = true;
            rows = true;
            diag1 = true;
            diag2 = true;
            //по строкам
            for (int j=0; j<SIZE; j++) {
                //присваеваем результаты стравнения(true если все совпало)
                cols &= (map[i][j] == dot);
                rows &= (map[j][i] == dot);
                diag1 &= (map[j][j] == dot);
                diag2 &= (map[SIZE - j - 1][j] == dot);
            }
            // если совпмали символы
            if (cols || rows || diag1 || diag2) return true;
        }
        return false;
    }

    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        return map[y][x] == DOT_EMPTY;
    }
}