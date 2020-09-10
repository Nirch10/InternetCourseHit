package Part2.Ex1;

import Part2.Index;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static Part2.Utils.MatrixUtils.getReachables;

public class CliqueFinder {

    private static final int visited = 2;

    public static void printAllCliques(Integer[][] matrix){
        HashSet<Collection<Index>> cliques = new HashSet<>();
        Integer[][] dupMatrix = matrix.clone();

        for (int i = 0; i < dupMatrix.length; i ++) {
            for (int j = 0; j < dupMatrix[i].length; j++) {
               cliques.add(findCliqueByIndex(dupMatrix,new Index(i,j), new LinkedList<>()));
            }
        }
        cliques.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(clique -> {
            if(clique.size() != 0)
                System.out.println(clique);
        });
    }


    private static Collection<Index> findCliqueByIndex(Integer[][] matrix, Index index, Collection<Index> clique){

        if(matrix[index.getRow()][index.getColumn()] != 1)return clique;
        clique.add(index);
        matrix[index.getRow()][index.getColumn()] = visited;
        Collection<Index> indexNeighbors = getReachables(matrix, index);
        if (indexNeighbors.size() == 0) return clique;

        Stack<Index> neighbors = new Stack<>();
        indexNeighbors.forEach(neighbor -> neighbors.push(neighbor));

        while (neighbors.empty() == false){
            Index newIndex = neighbors.pop();
            Executor executor =  Executors.newFixedThreadPool(10);
            Integer[][] newThreadClonedMat = matrix;
            Collection<Index> newThreadPaths = clique ;
            CompletableFuture<Collection<Index>> completableFuture = CompletableFuture.runAsync(()->{})
                    .thenApplyAsync(result -> {
                        try {
                            return findCliqueByIndex(newThreadClonedMat,newIndex, newThreadPaths);
                        }
                        catch
                        (Exception e){ }
                        return newThreadPaths;
                    },executor);
            try {
                clique = completableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return clique;
    }

    public static void main(String[] args){
        Integer[][] mat = { {1,1,1,1,1},
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
