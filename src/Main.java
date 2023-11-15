import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fieldSize = 7;
        int empty = 0;
        int ship = 1;
        int ship2 = 2;
        int ship3 = 3;
        int[][] field = new int[fieldSize][fieldSize];
        String[][] hiddenField = new String[fieldSize][fieldSize];
        ArrayList<String> playersName = new ArrayList<>();
        ArrayList<Integer> playersShots = new ArrayList<>();


        boolean restartGame = true;
        while(restartGame) {
            System.out.println("Welcome to Sea Battle!");
            System.out.print("Please enter your name: ");
            String playerName = scanner.nextLine();

            for(int i = 0; i < fieldSize; i++){
                for(int j = 0; j < fieldSize; j++){
                    field[i][j] = empty;
                    hiddenField[i][j] = "⬛";
                }
            }
            finalField(field, empty, ship, ship2, ship3);
            //displayField(field);
            System.out.println(playerName + ", the game is starting. Are you ready?");
            String readyAnswer = scanner.nextLine();
            if(readyAnswer.equals("yes") || readyAnswer.equals("Yes")){
                clean();
            }

            int shots = 0;
            while(!isAllShipSunked(hiddenField)){

                displayHiddenField(hiddenField);


                System.out.print("Please, enter the coordinates: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                shots++;
                clean();


                if(field[x-1][y-1] != empty){
                    if(field[x-1][y-1] == ship){
                        System.out.println("Congratulations! You sunk the ship!");
                        hiddenField[x-1][y-1] = "✅";
                    }
                    else{
                        System.out.println("Wooww, super😍. You found a part of ship correctly!!! ");
                        hiddenField[x-1][y-1] = "⬜";
                    }
                    if(field[x-1][y-1]==2){
                        if (isShipSunk(hiddenField, x - 1, y - 1, ship2)) {
                            System.out.println("Congratulations! You sunk the ship!");
                            markSunkShip(hiddenField, x - 1, y - 1);
                        }
                    }else if(field[x-1][y-1]==3){
                        if (isShipSunk(hiddenField, x - 1, y - 1, ship3)) {
                            System.out.println("Congratulations! You sunk the ship!");
                            markSunkShip(hiddenField, x - 1, y - 1);
                        }
                    }

                }else if(field[x-1][y-1] == empty){
                    System.out.println("Unfortunately, you miss😥");
                    hiddenField[x-1][y-1] = "❌";
                }
            }
            System.out.println("Congratulations!!! You have won with " + shots + " shots. Great job!");
            playersName.add(playerName);
            playersShots.add(shots);
            System.out.print("Do you want to start another game? ");

            scanner.nextLine();
            String restartGame2 = scanner.nextLine();
            if(!restartGame2.equals("yes")){
                System.out.println("Thank you for playing! Here is the final scoreboard:");
                for(int i = 0; i < playersName.size(); i++){
                    System.out.print(playersName.get(i) + " - ");
                    System.out.println(playersShots.get(i));
                }
                restartGame = false;
            }
            if(!restartGame){
                break;
            }

        }





    }
    public static boolean isShipSunk(String[][] hiddenField, int x, int y, int shipSize) {
        if (shipSize == 2) {
            if (y + 1 < 7 && hiddenField[x][y + 1].equals("⬜")) {
                return true;
            }
            else if (y - 1 >= 0 && hiddenField[x][y - 1].equals("⬜")) {
                return true;
            }
            else if (x + 1 < 7 && hiddenField[x + 1][y].equals("⬜")) {
                return true;
            }
            else{
                return x - 1 >= 0 && hiddenField[x - 1][y].equals("⬜");
            }
        } else if (shipSize == 3) {

            if (y + 2 < 7 && hiddenField[x][y + 1].equals("⬜") && hiddenField[x][y + 2].equals("⬜")) {
                return true;
            }
            else if (y - 2 >= 0 && hiddenField[x][y - 1].equals("⬜") && hiddenField[x][y - 2].equals("⬜")) {
                return true;
            }

            else if (x + 2 < 7 && hiddenField[x + 1][y].equals("⬜") && hiddenField[x + 2][y].equals("⬜")) {
                return true;
            } else{
                return x - 2 >= 0 && hiddenField[x - 1][y].equals("⬜") && hiddenField[x - 2][y].equals("⬜");
            }
        }
        return false;
    }


    public static void markSunkShip(String[][] hiddenField, int x, int y) {
        for (int j = y; j < 7 && (hiddenField[x][j].equals("⬜")); j++) {
            hiddenField[x][j] = "✅";
        }
        for (int j = y - 1; j >= 0 && (hiddenField[x][j].equals("⬜")); j--) {
            hiddenField[x][j] = "✅";
        }

        for (int i = x; i < 7 && (hiddenField[i][y].equals("⬜")); i++) {
            hiddenField[i][y] = "✅";
        }
        for (int i = x - 1; i >= 0 && (hiddenField[i][y].equals("⬜")); i--) {
            hiddenField[i][y] = "✅";
        }
    }


    public static boolean isAllShipSunked(String[][] hiddenField){
        int count = 0;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                if(hiddenField[i][j].equals("✅") || hiddenField[i][j].equals("⬜") ){
                    count++;
                }

            }
        }
        return count == 10;
    }


    public static void displayField(int[][] field) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void displayHiddenField(String[][] hiddenField) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(hiddenField[i][j] + " ");
            }
            System.out.println();
        }
    }




    public static void finalField(int[][] field, int empty, int ship, int ship2, int ship3){
        placeShipsRandomly(field, 1, 3, empty, ship);
        placeShipsRandomly(field, 2, 2, empty, ship2);
        placeShipsRandomly(field, 3, 1, empty, ship3);
    }
    public static void placeShipsRandomly(int[][] field, int shipSize, int shipsNumber, int empty, int ship){
        Random random = new Random();
        for(int i = 0; i < shipsNumber; i++){
            boolean shipPlaced = false;
            while(!shipPlaced){
                int x = random.nextInt(7);
                int y = random.nextInt(7);
                boolean isHorizontal = random.nextBoolean();
                if(canPlaceShip(field, x, y, shipSize, 7, isHorizontal, empty)){
                    placeShip(field, x, y, isHorizontal, shipSize, ship);
                    shipPlaced = true;
                }

            }
        }

    }

    public static void placeShip(int[][] field, int x, int y, boolean isHorizontal, int shipSize, int ship){
        if(isHorizontal){
            for(int j = y; j < y + shipSize; j++){
                field[x][j]=ship;
            }
        }else{
            for(int i = x; i < x + shipSize; i++){
                field[i][y] = ship;
            }
        }
    }
    public static boolean checkCorner(int[][] field, int x, int y, int fieldSize, int empty) {
        return x >= 0 && x < fieldSize && y >= 0 && y < fieldSize && field[x][y] != empty;
    }
    public static boolean canPlaceShip(int[][] field, int x, int y, int shipSize, int fieldSize, boolean isHorizontal, int empty){
        if(isHorizontal){
            if(y + shipSize > fieldSize){
                return false;
            }
            for(int j = y; j < y + shipSize; j++){
                if(field[x][j] != empty) {
                    return false;
                }
                if (x > 0 &&
                        field[x - 1][j] != empty) {
                    return false;
                }
                else if (x < fieldSize - 1 && field[x + 1][j] != empty) {
                    return false;
                }
                else if (j > 0 && field[x][j - 1] != empty) {
                    return false;
                }
                else if (j < fieldSize - 1 && field[x][j + 1] != empty) {
                    return false;
                }

                if (checkCorner(field, x - 1, j - 1, fieldSize, empty)) {
                    return false;
                }
                else if (checkCorner(field, x - 1, j + 1, fieldSize, empty)) {
                    return false;
                }
                else if (checkCorner(field, x + 1, j - 1, fieldSize, empty)) {
                    return false;
                }
                else if (checkCorner(field, x + 1, j + 1, fieldSize, empty)) {
                    return false;
                }
            }

        }else{
            if(x + shipSize > fieldSize){
                return false;
            }
            for(int i = x; i < x + shipSize; i++){
                if(field[i][y] != empty){
                    return false;
                }
                else if (i > 0 && field[i - 1][y] != empty) {
                    return false;
                }
                else if (i < fieldSize - 1 && field[i + 1][y] != empty) {
                    return false;
                }
                else if (y > 0 && field[i][y - 1] != empty) {
                    return false;
                }
                else if (y < fieldSize - 1 && field[i][y + 1] != empty) {
                    return false;
                }

                if (checkCorner(field, i - 1, y - 1, fieldSize, empty)) {
                    return false;
                }
                else if (checkCorner(field, i - 1, y + 1, fieldSize, empty)) {
                    return false;
                }
                else if (checkCorner(field, i + 1, y - 1, fieldSize, empty)) {
                    return false;
                }
                else if (checkCorner(field, i + 1, y + 1, fieldSize, empty)) {
                    return false;
                }
            }
        }
        return true;
    }



    public static void clean(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}