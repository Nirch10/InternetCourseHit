package Part2.Ex2;

import Part2.Index;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static Part2.Utils.MatrixUtils.getReachables;


public class PathFinder {

     private static final int marked_cell = 2;
     private static final int unmarked_cell = 1;

    public static void printAllPathsAscending(Index src, Index dst, Integer[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        paths = dfs(src, dst, matrix, paths,new LinkedList<>());
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
    }

    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, Integer[][] mat,
                                                        LinkedHashSet<Collection<Index>> paths, Collection<Index> parentPath) {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        Integer[][] clonedMat = mat.clone();
        clonedMat[src.getRow()][src.getColumn()] = marked_cell;

        if(src.equals(dst)){
            paths.add(parentPath);
            return paths;
        }

        Collection<Index> srcNeighbors = getReachables(mat, src);
        if (srcNeighbors.size() == 0) return paths;
        Stack<Index> neighbors = new Stack<>();
        srcNeighbors.forEach(neighbor -> neighbors.push(neighbor));
        Executor executor =  Executors.newFixedThreadPool(10);
        while (neighbors.empty() == false){
            Index newSrc = neighbors.pop();

            Integer[][] newThreadClonedMat = clonedMat;
            LinkedHashSet<Collection<Index>> newThreadPaths = paths;
            Collection<Index> newThreadParentPath = newParentPath;
            CompletableFuture<LinkedHashSet<Collection<Index>>> completableFuture = CompletableFuture.runAsync(()->{})
                    .thenApplyAsync(result -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " : "+ Thread.currentThread().getId());
                    return dfs(newSrc, dst, newThreadClonedMat, newThreadPaths, newThreadParentPath);
                }
                catch
                (Exception e){ }
                return newThreadPaths;
            },executor);
            try {
                paths = completableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            newParentPath = new LinkedList<>(parentPath);
            clonedMat = mat.clone();
            mat[newSrc.getRow()][newSrc.getColumn()] = unmarked_cell;
        }
        return paths;
    }



    public static void main(String[] args){
        Integer[][] mat = { {1,1,0,1,0},
                            {0,1,1,0,1},
                            {1,0,1,1,1}};
        Index src = new Index(0, 0);
        Index dst = new Index(2, 4);
        HashSet<Collection<Index>> paths = new HashSet<>();
        printAllPathsAscending(src, new Index(2, 4), mat);
    }

}