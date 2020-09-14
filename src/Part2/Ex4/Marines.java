package Part2.Ex4;

import Part2.Ex1.CliqueFinder;
import Part2.Index;
import java.util.*;

public class Marines {

    public static void printAllSubMarines(int[][] matrix) {
        int goodSubs = 0;

        // find all cliques in the matrix
        HashSet<Collection<Index>> cliques = CliqueFinder.getAllCliques(matrix, 1);

        // count good cliques that can represnt good subs on the matrix
        for (Collection<Index> clique : cliques) {
            goodSubs += isCliqueIsSubCTR(clique);
        }
        System.out.println("Number of subs on grid is : " + goodSubs);
    }
    /*
     * get the left top Index (row, col) and the size of the waana-be submarine
     * passes on all the indexes in the waana-be submarine.
     * if it is a perfect recthangle (it is a subMarine) return 1
     * else return 0
     */
    private static int isCliqueIsSubCTR(Collection<Index> clique) {
        IntSummaryStatistics intSummaryStatistics = clique.stream().map(Index::getRow).mapToInt(Integer::intValue).summaryStatistics();
        int minRow = intSummaryStatistics.getMin();
        int maxRow = intSummaryStatistics.getMax();
        intSummaryStatistics = clique.stream().map(Index::getColumn).mapToInt(Integer::intValue).summaryStatistics();
        int minCol = intSummaryStatistics.getMin();
        int maxCol = intSummaryStatistics.getMax();
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (!clique.contains(new Index(i, j))) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args){
        int[][] mat = { {1,1,0,1,1},
                        {0,1,0,1,1},
                        {1,1,0,1,1}
                        };
        printAllSubMarines(mat);
    }

}
