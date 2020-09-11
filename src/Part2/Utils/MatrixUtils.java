package Part2.Utils;

import Part2.Index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatrixUtils {

    private static Collection<Index> getAdjacentIndices(Integer[][] matrix, final Index index) {
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

    private static int getValue(Integer[][] matrix, Index index) {
        return matrix[index.getRow()][index.getColumn()];
    }

    public static Collection<Index> getReachables(Integer[][] matrix, Index index) {
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
        while ((dimensionSize < 0) || (dimensionSize > limit))
        {
            System.out.print("You entered invalid input. Please enter valid input: ");
            dimensionSize = scn.nextInt();
        }
        return dimensionSize;
    }

    public static Integer[][] addContentToMat(int row, int col) {
        Integer[][] returnMat = new Integer[row][col];
        Random rnd = new Random();
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++){
                returnMat[i][j] = Math.abs(i + rnd.nextInt() + j * rnd.nextInt()) %2;
            }
        }
        return returnMat;
    }
    public static Integer[][] addContentToMat(int row, int col,int val) {
        Integer[][] returnMat = new Integer[row][col];
        for (int i=0;i<row;i++)
            for (int j=0;j<col;j++)
                returnMat[i][j] = val;
        return returnMat;
    }
    public static Index getIndex(String indexSTR, Integer[][] matrix) {
        int x = MatrixUtils.getNumberInLimits(matrix.length,"x cord for "+ indexSTR + " index: ");
        int y = MatrixUtils.getNumberInLimits(matrix[0].length,"y cord for "+ indexSTR + " index: ");
        return new Index(x,y);
    }
    public static Integer[][] InitMatrix(int limit) {
        int row = MatrixUtils.getNumberInLimits(limit,"rows number: ");
        int col = MatrixUtils.getNumberInLimits(limit,"columns number: ");
        return MatrixUtils.addContentToMat(row,col);
    }
    public static Integer[][] InitMatrix(int limit,int val) {
        int row = MatrixUtils.getNumberInLimits(limit,"rows number: ");
        int col = MatrixUtils.getNumberInLimits(limit,"columns number: ");
        return MatrixUtils.addContentToMat(row,col,val);
    }
    public static void printMat(Integer[][] matrix)
    {
        for (int i=0; i<matrix.length; i++)
        {
            for (int j=0; j<matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
