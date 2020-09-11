package Part2.Ex2;

import Part2.Index;

import sun.nio.ch.ThreadPool;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static Part2.Utils.MatrixUtils.getReachables;
import static java.util.concurrent.CompletableFuture.supplyAsync;


public class PathFinder {

     private static final int marked_cell = 2;
     private static final int unmarked_cell = 1;
     private static ExecutorService executor;
     private static ForkJoinPool pool = new ForkJoinPool(1);
     private  List<CompletableFuture<Collection<Index>>> completableFutureList;

    public static void printAllPathsAscending(Index src, Index dst, Integer[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        //executor =  Executors.newFixedThreadPool(10);
        paths = Part2.Ex3.PathFinder.dfs(src,dst,matrix,paths,new LinkedList<>());
        //executor.shutdown();
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
    }

    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, Integer[][] mat,
                                                        LinkedHashSet<Collection<Index>> paths, Collection<Index> parentPath) throws ExecutionException, InterruptedException {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        Integer[][] clonedMat = mat.clone();
        clonedMat[src.getRow()][src.getColumn()] = marked_cell;

        if(src.equals(dst)){
            paths.add(parentPath);
            //System.out.println(parentPath.toString());
            return paths;
        }

        Collection<Index> srcNeighbors = getReachables(mat, src);
        if (srcNeighbors.size() == 0) return paths;
        Stack<Index> neighbors = new Stack<>();
        srcNeighbors.forEach(neighbor -> neighbors.push(neighbor));

        while (neighbors.empty() == false){
            Index newSrc = neighbors.pop();
            Integer[][] newThreadClonedMat = clonedMat;
            LinkedHashSet<Collection<Index>> newThreadPaths = paths;
            Collection<Index> newThreadParentPath = newParentPath;
            executor = Executors.newFixedThreadPool(10);
            CompletableFuture<LinkedHashSet<Collection<Index>>> completableFuture = new CompletableFuture<>();
            LinkedHashSet<Collection<Index>> finalPaths = paths;
            paths = supplyAsync(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
                    return dfs(newSrc, dst, newThreadClonedMat, newThreadPaths, newThreadParentPath);
                } catch (Exception e) {
                }
                return newThreadPaths;
            }, executor).get();
           // executor.shutdown();
//            try {
//                //executor.execute(() -> {System.out.println( "exec : " + Thread.currentThread().getName() + " : "+ Thread.currentThread().getId());});
//                paths = completableFuture.get(60,TimeUnit.SECONDS);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
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