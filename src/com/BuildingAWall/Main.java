package com.BuildingAWall;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int row, col, indexOfRow = 0;

        Scanner input = new Scanner(System.in);

        // checking the input
        while(true){
            try{
                System.out.print("Enter values for row and col");
                row = input.nextInt();
                col = input.nextInt();
                if( (row >= 2 && row <= 100 && row % 2 == 0) && (col >= 4 && col <= 100) ){
                    break;
                }
                System.out.println("invalid values");
            } catch(Exception e){
                System.out.println("You did not enter a Integer.");
                input.nextLine();
            }
        }

        int[][] inputArray = new int[row][col];
        int[][] result = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                inputArray[i][j] = input.nextInt();
            }
        }

        while(indexOfRow < row){
            for(int i = 0; i < col; i += 4) {
                if (col - i < 4) {
                    if(col - i == 3 || col - i == 2) {
                        if (inputArray[indexOfRow][i] == inputArray[indexOfRow][i + 1]) {
                            buildingWallType7(indexOfRow, i, result, inputArray);
                            i -= 2;
                            continue;
                        } else{
                            buildingWallType8(indexOfRow, i, result, inputArray);
                            i -= 2;
                            continue;
                        }
                    } else{
                        buildingWallType9(indexOfRow, i, result, inputArray);
                        continue;
                    }
                } else {
                    if (inputArray[indexOfRow][i] == inputArray[indexOfRow][i + 1]) {
                        if (inputArray[indexOfRow][i + 2] == inputArray[indexOfRow][i + 3]) {
                            buildingWallType1(indexOfRow, i, result, inputArray);
                            continue;
                        }
                        if (col - i > 4 && inputArray[indexOfRow][i + 3] == inputArray[indexOfRow][i + 4]) {
                            buildingWallType2(indexOfRow, i, result, inputArray);
                            continue;
                        } else {
                            buildingWallType3(indexOfRow, i, result, inputArray);
                            continue;
                        }

                    } else {
                        if (inputArray[indexOfRow][i + 2] == inputArray[indexOfRow][i + 3]) {
                            buildingWallType4(indexOfRow, i, result, inputArray);
                            continue;
                        }
                        if (col - i > 4 && inputArray[indexOfRow][i + 3] == inputArray[indexOfRow][i + 4]) {
                            buildingWallType5(indexOfRow, i, result, inputArray);
                            continue;
                        } else {
                            buildingWallType6(indexOfRow, i, result, inputArray);
                            continue;
                        }
                    }
                }
            }
            indexOfRow += 2;
        }

        // Printing the solution
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(result[i][j] > 9) System.out.print(result[i][j] + " ");
                else System.out.print(result[i][j] + "  ");
            }
            System.out.println(" ");
        }


    }
    /*
    turning this   1 1 3 3
                   2 2 4 4

       into this   2 1 1 4
                   2 3 3 4
     */
    public static void buildingWallType1(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col + 2];
        result[row + 1][col] = inputArray[row][col + 2];
        result[row][col + 1] = inputArray[row][col];
        result[row][col + 2] = inputArray[row][col];
        result[row + 1][col + 1] = inputArray[row + 1][col];
        result[row + 1][col + 2] = inputArray[row + 1][col];
        result[row][col + 3] = inputArray[row + 1][col + 3];
        result[row + 1][col + 3] = inputArray[row + 1][col + 3];
    }

    /*
    turning this   1 1 3 4 4
                   2 2 3 5 5

       into this   1 2 2 4   and switching the 4 into 5 in the unsorted array
                   1 3 3 4
     */
    public static void buildingWallType2(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col];
        result[row + 1][col] = inputArray[row][col];
        result[row][col + 1] = inputArray[row + 1][col];
        result[row][col + 2] = inputArray[row + 1][col];
        result[row + 1][col + 1] = inputArray[row][col + 2];
        result[row + 1][col + 2] = inputArray[row][col + 2];
        result[row][col + 3] = inputArray[row][col + 3];
        result[row + 1][col + 3] = inputArray[row][col + 3];
        inputArray[row][col + 4] = inputArray[row + 1][col + 4];
    }

    /*
    turning this   1 1 3 4
                   2 2 3 4

       into this   4 2 2 1
                   4 3 3 1
     */
    public static void buildingWallType3(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col + 3];
        result[row + 1][col] = inputArray[row][col + 3];
        result[row][col + 1] = inputArray[row + 1][col];
        result[row][col + 2] = inputArray[row + 1][col];
        result[row + 1][col + 1] = inputArray[row][col + 2];
        result[row + 1][col + 2] = inputArray[row][col + 2];
        result[row][col + 3] = inputArray[row][col];
        result[row + 1][col + 3] = inputArray[row][col];
    }

    /*
    turning this   1 2 3 3
                   1 2 4 4

       into this   3 1 1 4
                   3 2 2 4
     */
    public static void buildingWallType4(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col + 3];
        result[row + 1][col] = inputArray[row][col + 3];
        result[row][col + 1] = inputArray[row][col];
        result[row][col + 2] = inputArray[row][col];
        result[row + 1][col + 1] = inputArray[row + 1][col + 1];
        result[row + 1][col + 2] = inputArray[row + 1][col + 1];
        result[row][col + 3] = inputArray[row + 1][col + 3];
        result[row + 1][col + 3] = inputArray[row + 1][col + 3];
    }

    /*
    turning this   1 2 2 4 4
                   1 3 3 5 5

       into this   1 1 3 3   and switching the 4 into 5 in the unsorted array
                   2 2 4 4
     */
    public static void buildingWallType5(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col];
        result[row][col + 1] = inputArray[row][col];
        result[row + 1][col] = inputArray[row][col + 1];
        result[row + 1][col + 1] = inputArray[row][col + 1];
        result[row][col + 2] = inputArray[row + 1][col + 1];
        result[row][col + 3] = inputArray[row + 1][col + 1];
        result[row + 1][col + 2] = inputArray[row][col + 3];
        result[row + 1][col + 3] = inputArray[row][col + 3];
        inputArray[row][col + 4] = inputArray[row + 1][col + 4];
    }

    /*
    turning this   1 2 2 4
                   1 3 3 4

       into this   1 1 2 2
                   3 3 4 4
     */
    public static void buildingWallType6(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col];
        result[row][col + 1] = inputArray[row][col];
        result[row + 1][col] = inputArray[row][col + 1];
        result[row + 1][col + 1] = inputArray[row][col + 1];
        result[row][col + 2] = inputArray[row + 1][col + 2];
        result[row][col + 3] = inputArray[row + 1][col + 2];
        result[row + 1][col + 3] = inputArray[row][col + 3];
        result[row + 1][col + 2] = inputArray[row][col + 3];
    }

    /*
    turning this   1 1
                   2 2

       into this   2 1
                   2 1
     */
    public static void buildingWallType7(int row, int col, int result[][], int inputArray[][]) {
        result[row][col] = inputArray[row + 1][col];
        result[row + 1][col] = inputArray[row + 1][col];
        result[row][col + 1] = inputArray[row][col];
        result[row + 1][col + 1] = inputArray[row][col];
    }
    /*
    turning this   1 2
                   1 2

       into this   1 1
                   2 2
     */
    public static void buildingWallType8(int row, int col, int result[][], int inputArray[][]) {
        result[row][col] = inputArray[row][col];
        result[row][col + 1] = inputArray[row][col];
        result[row + 1][col] = inputArray[row][col + 1];
        result[row + 1][col + 1] = inputArray[row][col + 1];
    }

    // just adding the last  1  if there is a need of it
    //                       1
    public static void buildingWallType9(int row, int col, int result[][], int inputArray[][]){
        result[row][col] = inputArray[row][col];
        result[row + 1][col] = inputArray[row][col];
    }
}
