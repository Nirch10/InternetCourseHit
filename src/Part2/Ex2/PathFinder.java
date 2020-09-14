package Part2.Ex2;

import Part2.Index;

import Part2.eCellStatus;
import sun.nio.ch.ThreadPool;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static Part2.Utils.MatrixUtils.getReachables;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PathFinder {

    private static ExecutorService executor;

    public static void printAllPathsAscending(Index src, Index dst, int[][] matrix) throws ExecutionException, InterruptedException {
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        paths = Part2.Ex2.PathFinder.dfs(src,dst,matrix,paths,new LinkedList<>());
        // prints all the paths sorted from the shortest to longest
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
    }

    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, int[][] mat,
                                                        LinkedHashSet<Collection<Index>> paths, Collection<Index> parentPath) throws ExecutionException, InterruptedException {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        int[][] clonedMat = mat.clone();
        // mark the source index as visited
        clonedMat[src.getRow()][src.getColumn()] = eCellStatus.VISITED.getStatus();
        // if source is the destination index - we reach to proper path.
        if(src.equals(dst)){
            paths.add(parentPath);
            return paths;
        }
        Collection<Index> srcNeighbors = getReachables(mat, src);
        if (srcNeighbors.size() == 0) return paths;
        // push all the neighbors (if there are) to stack
        Stack<Index> neighbors = new Stack<>();
        srcNeighbors.forEach(neighbors::push);
        // until the stack is empty, pop one item, run Dfs where the item is the source index
        while (!neighbors.empty()){
            Index newSrc = neighbors.pop();
            int[][] newThreadClonedMat = clonedMat;
            LinkedHashSet<Collection<Index>> newThreadPaths = paths;
            Collection<Index> newThreadParentPath = newParentPath;
            executor = Executors.newFixedThreadPool(10);
            // start a new thread with new source
            paths = supplyAsync(() -> {
                try {
                    return dfs(newSrc, dst, newThreadClonedMat, newThreadPaths, newThreadParentPath);
                } catch (Exception e) {
                }
                return newThreadPaths;
            }, executor).get();

            newParentPath = new LinkedList<>(parentPath);
            clonedMat = mat.clone();
            mat[newSrc.getRow()][newSrc.getColumn()] = eCellStatus.EDGE.getStatus();
        }
        return paths;
    }




    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] mat = { {1,1,0,1,0},
                            {0,1,1,0,1},
                            {1,0,1,1,1}};
        Index src = new Index(0, 0);
        Index dst = new Index(2, 4);
        HashSet<Collection<Index>> paths = new HashSet<>();
        printAllPathsAscending(src, new Index(2, 4), mat);
    }

}