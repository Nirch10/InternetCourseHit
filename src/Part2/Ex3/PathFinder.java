package Part2.Ex3;

import Part2.Index;
import Part2.Utils.MatrixUtils;
import Part2.eCellStatus;

import java.util.*;

import static Part2.Utils.MatrixUtils.getIndex;
import static Part2.Utils.MatrixUtils.getReachables;


public class PathFinder {


    public static void printAllShortestPaths(Index src, Index dst, int[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        paths = dfs(src, dst, matrix, paths,new LinkedList<>());
        // sort all paths -> from the shortest to the largest
        Iterator<Collection<Index>> iterator = paths.stream().sorted(Comparator.comparingInt(Collection::size))
        .iterator();
        Collection<Index> collection;
        if(iterator.hasNext()) {
            collection = iterator.next();
            // take the size of the first path after the sorting - will be the shortest
            int minSize = collection.size();
            // print all paths with the size of the first path --> print all the shortest paths.
            paths.stream().filter(s -> s.size() == minSize).forEach(System.out::println);
        }
        else{
            System.out.println("No such route exists...");
        }
    }

    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, int[][] mat
            , LinkedHashSet<Collection<Index>> paths, Collection<Index> parentPath) {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        int[][] clonedMat =  mat.clone();
        // mark the source index as visited
        clonedMat[src.getRow()][src.getColumn()] = eCellStatus.VISITED.getStatus();

        // if source is the destination index - we reach to proper path.
        if(src.equals(dst)){
            paths.add(parentPath);
            return paths;
        }
        // get all the source index neighbors
        Collection<Index> srcNeighbors = getReachables(mat, src);
        if (srcNeighbors.size() == 0) return paths;
        // push all the neighbors (if there are) to stack
        Stack<Index> neighbors = new Stack<>();
        srcNeighbors.forEach(neighbors::push);
        // until the stack is empty, pop one item, run Dfs where the item is the source index
        while (!neighbors.empty()){
            Index newSrc = neighbors.pop();
            paths = dfs(newSrc, dst,clonedMat,paths, newParentPath);
            newParentPath = new LinkedList<>(parentPath);
            clonedMat = mat.clone();
            mat[newSrc.getRow()][newSrc.getColumn()] = eCellStatus.EDGE.getStatus();
        }
        return paths;
    }



    public static void main(String[] args){
        int[][] mat = { {1,1,0,1,0},
                            {0,1,1,0,1},
                            {1,0,1,1,1}};
        mat = MatrixUtils.InitMatrix(30);
        HashSet<Collection<Index>> paths = new HashSet<>();
        MatrixUtils.printMat(mat);
        // get index from user for source
        Index src = getIndex("Source",mat);
        // get index from user for destination
        Index dst = getIndex("Destination",mat);
        printAllShortestPaths(src, dst, mat);

    }

}