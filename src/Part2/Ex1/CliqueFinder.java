package Part2.Ex1;

import Part2.eCellStatus;
import Part2.Index;
import java.util.*;
import java.util.concurrent.*;

import static Part2.Utils.MatrixUtils.getReachables;

public class CliqueFinder {

    public static void printAllCliques(int[][] matrix){
        HashSet<Collection<Index>> cliques = getAllCliques(matrix);
        cliques.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(clique -> System.out.println(clique));
    }
    public static HashSet<Collection<Index>> getAllCliques(int[][] matrix){
        return getAllCliques(matrix, 0);
    }
    public static HashSet<Collection<Index>> getAllCliques(int[][] matrix,int minimumCliqueSize){
        HashSet<Collection<Index>> cliques = new HashSet<>();
        int[][] dupMatrix = matrix.clone();
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

    private static Collection<Index> findCliqueByIndex(int[][] matrix, Index index, Collection<Index> clique){
        if(matrix[index.getRow()][index.getColumn()] != 1)return clique;
        clique.add(index);
        matrix[index.getRow()][index.getColumn()] = (eCellStatus.VISITED.getStatus());
        Collection<Index> indexNeighbors = getReachables(matrix, index);
        if (indexNeighbors.size() == 0) return clique;
        Stack<Index> neighbors = new Stack<>();
        indexNeighbors.forEach(neighbors::push);
        while (!neighbors.empty()){
            Index newIndex = neighbors.pop();
            int[][] newThreadClonedMat = matrix;
            Collection<Index> newThreadPaths = clique ;
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

    public static void main(String[] args){
        int[][] mat = { {1,1,1,1,1},
                            {0,0,0,0,0},
                            {1,1,1,1,1}
                            ,{1,1,1,1,1},
                            {1,1,1,1,1},
                            {0,0,0,0,0},
                            {0,0,0,0,0}};
        Index src = new Index(0, 0);
        Index dst = new Index(2, 4);
        HashSet<Collection<Index>> paths = new HashSet<>();
        printAllCliques(mat);
    }

}
