package Part2.Utils;

import Part2.Index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatrixUtils {

    private static Collection<Index> getAdjacentIndices(int[][] matrix, final Index index) {
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try {
            extracted = matrix[index.getRow() + 1][index.getColumn()];
            list.add(new Index(index.getRow() + 1, index.getColumn()));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.getRow()][index.getColumn() +  1];
            list.add(new Index(index.getRow(), index.getColumn() + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.getRow() - 1][index.getColumn()];
            list.add(new Index(index.getRow() - 1, index.getColumn()));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.getRow()][index.getColumn() - 1];
            list.add(new Index(index.getRow(), index.getColumn() - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() - 1][index.getColumn() - 1];
            list.add(new Index(index.getRow() - 1, index.getColumn() - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() + 1][index.getColumn() - 1];
            list.add(new Index(index.getRow() + 1, index.getColumn() - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() + 1][index.getColumn() + 1];
            list.add(new Index(index.getRow() + 1, index.getColumn() + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() - 1][index.getColumn() + 1];
            list.add(new Index(index.getRow() - 1, index.getColumn() + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return list;
    }

    private static int getValue(int[][] matrix, Index index) {
        return matrix[index.getRow()][index.getColumn()];
    }

    public static Collection<Index> getReachables(int[][] matrix, Index index) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        try{

            getAdjacentIndices(matrix, index).stream().filter(i-> getValue(matrix, i)==1)
                    .map(neighbor->filteredIndices.add(neighbor)).collect(Collectors.toList());
            return filteredIndices;
        }catch (Exception e) {
            return filteredIndices;
        }
    }
    public static int getNumberInLimits(int limit, String limitName){
        Scanner scn = new Scanner((System.in));
        System.out.print("Please enter " + limitName  );
        int dimensionSize = scn.nextInt();
        while ((dimensionSize < 0) || (dimensionSize >= limit))
        {
            System.out.print("You entered invalid input. Please enter valid input: ");
            dimensionSize = scn.nextInt();
        }
        return dimensionSize;
    }
    public static int[][] addContentToMat(int row, int col) {
        int[][] returnMat = new int[row][col];
        Random rnd = new Random();
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++){
                returnMat[i][j] = Math.abs(i + rnd.nextInt() + j * rnd.nextInt()) %2;
            }
        }
        return returnMat;
    }
    public static Index getIndex(String indexSTR, int[][] matrix) {
        int y = MatrixUtils.getNumberInLimits(matrix.length,"(Row)y cord for "+ indexSTR + " index: ");
        int x = MatrixUtils.getNumberInLimits(matrix[0].length,"(Col)x cord for "+ indexSTR + " index: ");
        while  (matrix[y][x] != 1)
        {
            System.out.println("The index doesnt have the value of 1. Please choose again :");
            y = MatrixUtils.getNumberInLimits(matrix.length,"(Row)y cord for "+ indexSTR + " index: ");
            x = MatrixUtils.getNumberInLimits(matrix[0].length,"(Col)x cord for "+ indexSTR + " index: ");
        }
        return new Index(y,x);
    }
    public static int[][] InitMatrix(int limit) {
        int row = MatrixUtils.getNumberInLimits(limit,"rows number: ");
        int col = MatrixUtils.getNumberInLimits(limit,"columns number: ");
        return MatrixUtils.addContentToMat(row,col);
    }
    public static void printMat(int[][] matrix)
    {
        for (int i=0; i<matrix.length; i++)
        {
            for (int j=0; j<matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public static int[][] restoreToBinaryMatrix(int[][] primitiveMatrix) {
        int[][] result = new int[primitiveMatrix.length][primitiveMatrix[0].length];
        for (int i=0; i< primitiveMatrix.length;i++){
            for (int j=0;j<primitiveMatrix[0].length;j++){
                result[i][j] = primitiveMatrix[i][j];
                if (primitiveMatrix[i][j] == 2)
                {
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }

    public static int[][] cloneMatrix(int [][] matrixToClone)
    {
        int[][] tempMatrix = new int[matrixToClone.length][matrixToClone[0].length];
        for (int i=0; i<matrixToClone.length;i++ )
        {
            for (int j=0; j<matrixToClone[0].length;j++ )
            {
                tempMatrix[i][j] = matrixToClone[i][j];
            }
        }
        return tempMatrix;
    }

    public static int[][] CreateMatrixForSubMarine(int row, int col, int numberOfTries)
    {
        int[][] matrix = new int[row][col];
        boolean isRowTurn = true;
        int dummyRow = 0;
        int dummyCol = 0;
        int tryis = 0;
        Random rnd = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = 1;
            }
        }
        for (int k = 0; k < numberOfTries; k++) {
            if (isRowTurn) {
                int nextTry = rnd.nextInt(row);
                if (nextTry == dummyRow){dummyRow++;}
                while (tryis < 7 && matrix[nextTry][dummyRow] == 1 && checkLimit(row,nextTry - 1)&&
                        matrix[nextTry - 1][dummyRow] != 1 && checkLimit(row,nextTry + 1)&&
                        matrix[nextTry + 1][dummyRow] != 1) {
                    nextTry = rnd.nextInt(row);
                    tryis++;
                }
                if (tryis >= 7) {
                    tryis = 0;
                } else {
                    tryis = 0;
                    isRowTurn = false;
                    for (int i = 0; i < col; i++) {
                        matrix[nextTry][i] = 0;
                    }
                }
            } else {
                int nextTry = rnd.nextInt(col);
                if (nextTry == dummyCol){dummyCol++;}
                while (tryis < 7 && matrix[dummyCol][nextTry] != 1 &&checkLimit(col,nextTry - 1) && checkLimit(col,nextTry + 1)&&
                        matrix[dummyCol][nextTry+1] != 1) {
                    nextTry = rnd.nextInt(col);
                    tryis++;
                }
                if (tryis >= 7) {
                    tryis = 0;
                } else {
                    tryis = 0;
                    isRowTurn = true;
                    for (int i = 0; i < col; i++) {
                        matrix[i][nextTry] = 0;
                    }
                }
            }
        }
        return matrix;
    }
    private static boolean checkLimit(int limit, int toTest)
    {
        return (toTest >=0 && toTest < limit);
    }

    public static void main(String[] args) {
        printMat(CreateMatrixForSubMarine(5,5,9));
    }


}
