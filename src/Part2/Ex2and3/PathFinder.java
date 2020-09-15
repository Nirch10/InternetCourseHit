package Part2.Ex2and3;

import Part2.CELLSTATUS;
import Part2.Index;
import Part2.Utils.MatrixUtils;
import sun.misc.Queue;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

import static Part2.Utils.MatrixUtils.getReachables;


public class PathFinder {

    private static LinkedHashSet<Collection<Index>> paths;
    //    private static ExecutorService executor;
//    private static ForkJoinPool pool = new ForkJoinPool(1);
//    private  List<CompletableFuture<Collection<Index>>> completableFutureList;


    /**
     * @param src -  Source Index (i,j) for start the DFS search from
     * @param dst - Destination Index (i,j) for the DFS search to end
     * @param mat - Matrix graph to search  DFS in
     * @param paths - existing paths found from src to dst in mat (should be given as new kind of collection at the start)
     * @param parentPath - current working path found (should be given as new kind of collection at the start)
     * @return
     */
    public static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, Integer[][] mat
            , LinkedHashSet<Collection<Index>> paths, Collection<Index> parentPath) {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        Integer[][] clonedMat =  mat.clone();
        clonedMat[src.getRow()][src.getColumn()] = CELLSTATUS.VISITED.getStatus();

        if(src.equals(dst)){
            paths.add(parentPath);
            return paths;
        }

        Collection<Index> srcNeighbors = getReachables(mat, src);
        if (srcNeighbors.size() == 0) return paths;
        Stack<Index> neighbors = new Stack<>();
        srcNeighbors.forEach(neighbor -> neighbors.push(neighbor));
        while (neighbors.empty() == false){
            Index newSrc = neighbors.pop();
            paths = dfs(newSrc, dst,clonedMat,paths, newParentPath);
            newParentPath = new LinkedList<>(parentPath);
            clonedMat = mat.clone();
            mat[newSrc.getRow()][newSrc.getColumn()] = CELLSTATUS.EDGE.getStatus();
        }
        return paths;
    }



    /*    parallel dfs - with  completablefuture
//    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, Integer[][] mat,
//                                                        LinkedHashSet<Collection<Index>> paths, Collection<Index> parentPath) throws ExecutionException, InterruptedException {
//        parentPath.add(src);
//        Collection<Index> newParentPath = new LinkedList<>(parentPath);
//        Integer[][] clonedMat = mat.clone();
//        clonedMat[src.getRow()][src.getColumn()] = marked_cell;
//
//        if(src.equals(dst)){
//            paths.add(parentPath);
//            //System.out.println(parentPath.toString());
//            return paths;
//        }
//
//        Collection<Index> srcNeighbors = getReachables(mat, src);
//        if (srcNeighbors.size() == 0) return paths;
//        Stack<Index> neighbors = new Stack<>();
//        srcNeighbors.forEach(neighbor -> neighbors.push(neighbor));
//
//        while (neighbors.empty() == false){
//            Index newSrc = neighbors.pop();
//            Integer[][] newThreadClonedMat = clonedMat;
//            LinkedHashSet<Collection<Index>> newThreadPaths = paths;
//            Collection<Index> newThreadParentPath = newParentPath;
//            executor = Executors.newFixedThreadPool(10);
//            CompletableFuture<LinkedHashSet<Collection<Index>>> completableFuture = new CompletableFuture<>();
//            LinkedHashSet<Collection<Index>> finalPaths = paths;
//            paths = supplyAsync(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
//                    return dfs(newSrc, dst, newThreadClonedMat, newThreadPaths, newThreadParentPath);
//                } catch (Exception e) {
//                }
//                return newThreadPaths;
//            }, executor).get();
//            // executor.shutdown();
////            try {
////                //executor.execute(() -> {System.out.println( "exec : " + Thread.currentThread().getName() + " : "+ Thread.currentThread().getId());});
////                paths = completableFuture.get(60,TimeUnit.SECONDS);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//            newParentPath = new LinkedList<>(parentPath);
//            clonedMat = mat.clone();
//            mat[newSrc.getRow()][newSrc.getColumn()] = unmarked_cell;
//        }
//        return paths;
//    } */



//    public static void main(String[] args){
//        Integer[][] mat = { {1,1,0,1,0},
//                            {0,1,1,0,1},
//                            {1,0,1,1,1}};
//        mat = MatrixUtils.InitMatrix(30);
//        Index src = new Index(0, 0);
//        Index dst = new Index(16, 23);
//        HashSet<Collection<Index>> paths = new HashSet<>();
//        printShortestPaths(src, new Index(2, 4), mat);
//    }

}