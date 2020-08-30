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

    private final int row = 10;
    private final int col = 10;
    private Random rnd = new Random();



    public SubMarine() {
        mat = new int[row][col];
        initMat();
        int[][] savedMat = new int[mat.length][mat[0].length];
        copyMat(mat,savedMat,row, col);
        printMat(mat);
        System.out.println();
        List<List<Index>> result = searchForSubMarines();

        int counter =0;
        for (List<Index> li : result) {
            for (Index item : li) {
                System.out.print(item.toString() + ",");

            }
            System.out.println();
            counter++;
        }

        System.out.println("there are " + counter + " submarines ");
        printMat(savedMat);

    }
    private void initMat()
    {
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++){
                mat[i][j] = Math.abs(i + rnd.nextInt() + j * rnd.nextInt()) %2;
            }
        }
    }
    private void copyMat(int[][] source, int[][] dest , int row, int col){
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                dest[i][j] = source[i][j];
            }
        }
    }
    private void printMat(int[][] matToPrint)
    {

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                System.out.print((matToPrint[i][j] + " "));
            }
            System.out.println();
        }
    }

    private List<List<Index>> searchForSubMarines()
    {
        List<List<Index>> subMarines = new ArrayList<>();
        List<Index> currentSub = new ArrayList<>();
        eDirection currentSearchDirection = eDirection.NONE;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (mat[i][j] == 1)
                {
                    if (currentSearchDirection == eDirection.NONE)
                    {
                        currentSearchDirection = checkForNeighborsDirection(i, j);
                    }
                    int tempI = i;
                    int tempJ = j;
                    searchForCurrentSub(subMarines,  currentSub, currentSearchDirection,  tempI,  tempJ);
                    currentSearchDirection = eDirection.NONE;
                    currentSub = new ArrayList<>();
                    mat[i][j] = markedCell;
                }
            }
        }
        return subMarines;
    }
    private void searchForCurrentSub(List<List<Index>> subMarines,  List<Index> currentSub,  eDirection currentSearchDirection,  int tempI,  int tempJ)
    {
        while (currentSearchDirection != eDirection.NONE)
        {
            currentSub.add(new Index(tempI,tempJ));
            mat[tempI][tempJ] = markedCell;
            if (currentSearchDirection == eDirection.RIGHT) { tempJ++; }
            if (currentSearchDirection == eDirection.LEFT) { tempJ--; }
            if (currentSearchDirection == eDirection.UP) { tempI--; }
            if (currentSearchDirection == eDirection.DOWN) { tempI++; }
            boolean continueDirection = checkForSpecificDirection(currentSearchDirection, tempI, tempJ);
            if (!continueDirection)
            {
                if (checkCordLimit(tempI, row) && checkCordLimit(tempJ, col))
                {
                    currentSub.add(new Index(tempI,tempJ));
                    mat[tempI][tempJ] = markedCell;
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

    private boolean checkForSpecificDirection(eDirection i_DirectionToLook ,int i_I,int i_J)
    {
        switch (i_DirectionToLook)
        {
            case UP:
                return checkCordLimit(i_I - 1, row) && mat[i_I - 1][i_J] == 1 && mat[i_I - 1][i_J]!= markedCell;
            case DOWN:
                return checkCordLimit(i_I + 1, row) && mat[i_I + 1][i_J] == 1 && mat[i_I + 1][i_J]!= markedCell;
            case RIGHT:
                return checkCordLimit(i_J + 1, col) && mat[i_I][i_J + 1] == 1 && mat[i_I][i_J + 1]!= markedCell;
            case LEFT:
                return checkCordLimit(i_J - 1, col) && mat[i_I][i_J - 1] == 1 && mat[i_I][i_J - 1]!= markedCell;
            default:
                return false;
        }
    }

    private eDirection checkForNeighborsDirection(int i_I, int i_J)
    {
        if ((checkCordLimit(i_J + 1, col) && mat[i_I][i_J + 1] == 1))
        {
            return eDirection.RIGHT;
        }
        if ((checkCordLimit(i_I+1,row) && mat[i_I + 1][i_J] == 1))
        {
            return eDirection.DOWN;
        }
        if ((checkCordLimit(i_J - 1, col) && mat[i_I][i_J - 1] == 1))
        {
            return eDirection.LEFT;
        }
        if ((checkCordLimit(i_I - 1, row) && mat[i_I - 1][i_J] == 1))
        {
            return eDirection.UP;
        }
        return eDirection.NONE;
    }
    private boolean checkCordLimit(int i_Cord, int i_Limit)
    {
        return i_Cord >= 0 && i_Cord < i_Limit;
    }



}


