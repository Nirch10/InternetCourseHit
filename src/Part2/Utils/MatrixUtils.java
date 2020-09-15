package Part2.Utils;

import Part2.Index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatrixUtils {


    /**
     * @param matrix - matrix to search in
     * @param index - index (i,j) to find neighbors around.
     * @return - Collection of indexes which represents neighbors
     */
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

    /**
     * @param matrix - matrix to search in
     * @param index - index to find value in matrix
     * @return
     */
    private static int getValue(Integer[][] matrix, Index index) {
        return matrix[index.getRow()][index.getColumn()];
    }

    /**
     * @param matrix - matrix graph to search in
     * @param index - index to which the method will search neighbors who has value of value (default is 1)
     * @return - Collection of indexes which were valid for the criteria
     */
    public static Collection<Index> getReachables(Integer[][] matrix, Index index) {
        return getReachables(matrix, index,1);
    }
    public static Collection<Index> getReachables(Integer[][] matrix, Index index, int value) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        try{

            getAdjacentIndices(matrix, index).stream().filter(i-> getValue(matrix, i)==value)
                    .map(neighbor->filteredIndices.add(neighbor)).collect(Collectors.toList());
            return filteredIndices;
        }catch (Exception e) {
            return filteredIndices;
        }
    }

    public static int getIntFromUser(int limit, String limitName){
        Scanner scn = new Scanner((System.in));
        System.out.print("Type " + limitName  );
        int dimensionSize = scn.nextInt();
        while ((dimensionSize < 0) || (dimensionSize >= limit))
        {
            System.out.print("Invalid input. type again please: ");
            dimensionSize = scn.nextInt();
        }
        return dimensionSize;
    }

    public static Integer[][] fillMatrix(int row, int col) {
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
    public static Integer[][] fillMatrix(int row, int col, int val) {
        Integer[][] returnMat = new Integer[row][col];
        for (int i=0;i<row;i++)
            for (int j=0;j<col;j++)
                returnMat[i][j] = val;
        return returnMat;
    }
    public static Index getIndex(String indexSTR, Integer[][] matrix) {
        int x = MatrixUtils.getIntFromUser(matrix.length,"x for"+ indexSTR + " index: ");
        int y = MatrixUtils.getIntFromUser(matrix[0].length,"y for"+ indexSTR + " index: ");
        return new Index(x,y);
    }
    public static Integer[][] initMatrix(int limit) {
        int row = MatrixUtils.getIntFromUser(limit,"rows number: ");
        int col = MatrixUtils.getIntFromUser(limit,"columns number: ");
        return MatrixUtils.fillMatrix(row,col);
    }
    public static Integer[][] initMatrix(int limit, int val) {
        int row = MatrixUtils.getIntFromUser(limit,"rows number: ");
        int col = MatrixUtils.getIntFromUser(limit,"columns number: ");
        return MatrixUtils.fillMatrix(row,col,val);
    }
    public static void printMatrix(Integer[][] matrix) {
        for (int i=0; i<matrix.length; i++)
        {
            for (int j=0; j<matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
