package Part2.Ex4;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubMarine {


    public static void main(String[] args) {
        SubMarine sb = new SubMarine();
    }

    public int[][] mat;
    final int markedCell = 2;

    private final int row = 270;
    private final int col = 170;
    private Random rnd = new Random();



    public SubMarine() {}

    public static List<List<Index>> searchForSubMarines(int[][] seaMatrix, int row, int col)
    {
        List<List<Index>> subMarines = new ArrayList<>();
        List<Index> currentSub = new ArrayList<>();
        eDirection currentSearchDirection = eDirection.NONE;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (seaMatrix[i][j] == 1)
                {
                    if (currentSearchDirection == eDirection.NONE)
                    {
                        currentSearchDirection = checkForNeighborsDirection(seaMatrix,i, j , row,  col);
                    }
                    int tempI = i;
                    int tempJ = j;
                    searchForCurrentSub(seaMatrix,subMarines,  currentSub, currentSearchDirection,  tempI,  tempJ, row,  col);
                    currentSearchDirection = eDirection.NONE;
                    currentSub = new ArrayList<>();
                    seaMatrix[i][j] = 2;
                }
            }
        }
        return subMarines;
    }
    private static void  searchForCurrentSub(int[][] seaMatrix, List<List<Index>> subMarines,  List<Index> currentSub,  eDirection currentSearchDirection,  int tempI,  int tempJ, int row, int col)
    {
        while (currentSearchDirection != eDirection.NONE)
        {
            currentSub.add(new Index(tempI,tempJ));
            seaMatrix[tempI][tempJ] = 2;
            if (currentSearchDirection == eDirection.RIGHT) { tempJ++; }
            if (currentSearchDirection == eDirection.LEFT) { tempJ--; }
            if (currentSearchDirection == eDirection.UP) { tempI--; }
            if (currentSearchDirection == eDirection.DOWN) { tempI++; }
            boolean continueDirection = checkForSpecificDirection(seaMatrix,currentSearchDirection, tempI, tempJ,  row, col);
            if (!continueDirection)
            {
                if (checkCordLimit(tempI, row) && checkCordLimit(tempJ, col))
                {
                    currentSub.add(new Index(tempI,tempJ));
                    seaMatrix[tempI][tempJ] = 2;
                }
                currentSearchDirection = eDirection.NONE;
            }
        }
        if (currentSub.size() > 1)
        {
            subMarines.add(currentSub);
        }
        currentSub = new ArrayList<>();
    }

    private static boolean checkForSpecificDirection(int [][] seaMatrix, eDirection i_DirectionToLook ,int i_I,int i_J ,int row, int col)
    {
        switch (i_DirectionToLook)
        {
            case UP:
                return checkCordLimit(i_I - 1, row) && seaMatrix[i_I - 1][i_J] == 1 && seaMatrix[i_I - 1][i_J]!= 2;
            case DOWN:
                return checkCordLimit(i_I + 1, row) && seaMatrix[i_I + 1][i_J] == 1 && seaMatrix[i_I + 1][i_J]!= 2;
            case RIGHT:
                return checkCordLimit(i_J + 1, col) && seaMatrix[i_I][i_J + 1] == 1 && seaMatrix[i_I][i_J + 1]!= 2;
            case LEFT:
                return checkCordLimit(i_J - 1, col) && seaMatrix[i_I][i_J - 1] == 1 && seaMatrix[i_I][i_J - 1]!= 2;
            default:
                return false;
        }
    }

    private static eDirection checkForNeighborsDirection(int [][]seaMatrix, int i_I, int i_J, int row, int col)
    {
        if ((checkCordLimit(i_J + 1, col) && seaMatrix[i_I][i_J + 1] == 1))
        {
            return eDirection.RIGHT;
        }
        if ((checkCordLimit(i_I+1,row) && seaMatrix[i_I + 1][i_J] == 1))
        {
            return eDirection.DOWN;
        }
        if ((checkCordLimit(i_J - 1, col) && seaMatrix[i_I][i_J - 1] == 1))
        {
            return eDirection.LEFT;
        }
        if ((checkCordLimit(i_I - 1, row) && seaMatrix[i_I - 1][i_J] == 1))
        {
            return eDirection.UP;
        }
        return eDirection.NONE;
    }
    private static  boolean checkCordLimit(int i_Cord, int i_Limit)
    {
        return i_Cord >= 0 && i_Cord < i_Limit;
    }

    public static void printMat(int[][] matToPrint)
    {

        for (int i = 0; i < matToPrint.length; i++)
        {
            for (int j = 0; j < matToPrint[0].length; j++)
            {
                System.out.print((matToPrint[i][j] + " "));
            }
            System.out.println();
        }
    }

}


