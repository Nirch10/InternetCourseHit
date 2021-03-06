package Part2.Ex1;

import Part2.CELLSTATUS;
import Part2.Index;
import java.util.*;
import java.util.concurrent.*;

import static Part2.Utils.MatrixUtils.getReachables;

public class CliqueFinder {

    //In case of parallel usage - executor will be used as our threadpool, and run wanted methods on different thread.
    private static ExecutorService executor =  Executors.newFixedThreadPool(10);


    /**
     * @param matrix - matrix to search cliques in
     * @return HashSet of all the cliques found
     */
    public static HashSet<Collection<Index>> getAllCliques(Integer[][] matrix){ return getAllCliques(matrix, 0); }
    public static HashSet<Collection<Index>> getAllCliques(Integer[][] matrix,int minimumCliqueSize){
        HashSet<Collection<Index>> cliques = new HashSet<>();
        Integer[][] dupMatrix = matrix.clone();
         Collection<Index> cliqueByIndex;
        for (int i = 0; i < dupMatrix.length; i ++) {
            for (int j = 0; j < dupMatrix[i].length; j++) {
                cliqueByIndex= findCliqueByIndex(dupMatrix, new Index(i, j), new LinkedList<>());
                if(cliqueByIndex.size() > minimumCliqueSize)
                    cliques.add(cliqueByIndex);
            }
        }
        return cliques;
    }


    /**
     * @param matrix - matrix to search clique in
     * @param index - Index (i,j) in matrix to indicate from what index on the matrix we search clique
     * @param clique - current clique found (when function called should be new kind of collection)
     * @return
     */
    public static Collection<Index> findCliqueByIndex(Integer[][] matrix, Index index, Collection<Index> clique){
        if(matrix[index.getRow()][index.getColumn()] != 1)
            return clique;
        clique.add(index);
        matrix[index.getRow()][index.getColumn()] = (CELLSTATUS.VISITED.getStatus());
        Collection<Index> indexNeighbors = getReachables(matrix, index);
        if (indexNeighbors.size() == 0)
            return clique;
        Stack<Index> neighbors = new Stack<>();
        indexNeighbors.forEach(neighbor -> neighbors.push(neighbor));
        while (neighbors.empty() == false){
            Index newIndex = neighbors.pop();
            Integer[][] newThreadClonedMat = matrix;
            Collection<Index> newThreadPaths = clique ;
            //Used CompletableFuture with its default forkJoinPool runner.
            CompletableFuture<Collection<Index>> completableFuture = CompletableFuture.runAsync(()->{})
                    .thenApplyAsync(result -> {
                        try { return findCliqueByIndex(newThreadClonedMat,newIndex, newThreadPaths); }
                        catch (Exception e){ }
                        return newThreadPaths;
                    });
            try { clique = completableFuture.get(); }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ExecutionException e) { e.printStackTrace(); }
        }
        return clique;
    }

    /** main runner
//    public static void main(String[] args){
//        Integer[][] mat = { {1,1,1,1,1},
//                            {0,0,0,0,0},
//                            {1,1,1,1,1}
//                            ,{1,1,1,1,1},
//                            {1,1,1,1,1},
//                            {0,0,0,0,0},
//                            {0,0,0,0,0}};
//        Index src = new Index(0, 0);
//        Index dst = new Index(2, 4);
//        HashSet<Collection<Index>> paths = new HashSet<>();
//        printAllCliques(mat);
//    }**/

}
