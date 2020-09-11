package Part2.Ex4;

import Part2.Ex1.CliqueFinder;
import Part2.Index;
import java.util.*;

public class Marines {

    public static void printAllSubMarines(Integer[][] matrix){
        HashSet<Collection<Index>> cliques = CliqueFinder.getAllCliques(matrix,1);
        if(areCliquesSquared(cliques))
            System.out.println("There are " + cliques.size() + " sub-Marines");
        else
            System.out.println("Invalid matrix");
    }

    private static Boolean areCliquesSquared(HashSet<Collection<Index>>  cliques){
        for (Collection clique : cliques) {
            if(isCliqueSquared(clique) == false)
                return false;
        }
        return true;
    }

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


    public static void main(String[] args){
        Integer[][] mat = { {1,1,1,1,1},
                {0,0,0,0,0},
                {1,1,0,1,1}
                ,{1,1,0,1,1},
                {1,1,0,0,0},
                {0,0,0,0,1},
                {0,0,0,0,0}};
        HashSet<Collection<Index>> paths = new HashSet<>();
        printAllSubMarines(mat);
    }

}
