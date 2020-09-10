package Part2.Ex4;

import java.util.Collection;
import java.util.LinkedList;
import Part2.Index;

import static Part2.Utils.MatrixUtils.getReachables;

public class FindSubMarines {

    private static final int visited = 2;

    public void printNumOfMarines(Integer[][] grid){
        Integer[][] dupMatrix = grid.clone();
        int marinesCount = 0;

        for (int i = 0; i < dupMatrix.length; i ++) {
            for (int j = 0; j < dupMatrix[i].length; j++) {
               marinesCount += countNumOfMarines(dupMatrix, new Index(0,0));
            }
        }
    }

    private int countNumOfMarines(Integer[][] matrix, Index index) {

        if(matrix[index.getRow()][index.getColumn()] != 1)return 0;

        matrix[index.getRow()][index.getColumn()] = visited;
        Collection<Index> indexNeighbors = getReachables(matrix, index);
        if (indexNeighbors.size() == 0)return 0;

        return 0;
    }


}
