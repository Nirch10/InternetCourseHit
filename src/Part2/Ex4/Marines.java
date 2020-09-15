package Part2.Ex4;

import Part2.Index;
import java.util.*;

public class Marines {

    /**
     * @param cliques - cliques to count squared cliques from
     * @return - number of squared shaped cliques from @param
     */
    public static int CountSquaredCliques(HashSet<Collection<Index>> cliques){
        int marinesCount = 0;
        for (Collection clique : cliques) {
            if(isCliqueSquared(clique) == true)
                marinesCount ++;
        }
        return marinesCount;
    }

    /**
     * @param clique - indexes Collection which represents a clique
     * @return true if they are connected in a square shape, else returns false.
     */
    private static boolean isCliqueSquared(Collection<Index> clique) {
        if(clique.size()<=1)
            return false;
        IntSummaryStatistics intSummaryStatistics = clique.stream().map(Index::getRow).mapToInt(Integer::intValue).summaryStatistics();
        int minRow = intSummaryStatistics.getMin();
        int maxRow = intSummaryStatistics.getMax();
        intSummaryStatistics = clique.stream().map(Index::getColumn).mapToInt(Integer::intValue).summaryStatistics();
        int minCol = intSummaryStatistics.getMin();
        int maxCol = intSummaryStatistics.getMax();
        for (int i = minRow; i<= maxRow;i++){
            for(int j = minCol; j <= maxCol; j++){
                if(clique.contains(new Index(i,j)) == false)
                    return false;
            }
        }
        return true;
    }

   /** main runner
    //    public static void main(String[] args){
//        Integer[][] mat = { {1,1,1,1,1},
//                {0,0,0,0,0},
//                {1,1,0,1,1}
//                ,{1,1,0,1,1},
//                {1,1,0,0,0},
//                {0,0,0,0,1},
//                {0,0,0,0,0}};
//        HashSet<Collection<Index>> paths = new HashSet<>();
//        printAllSubMarines(mat);
//    }**/

}
