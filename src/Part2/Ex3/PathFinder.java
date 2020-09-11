package Part2.Ex3;

import Part2.Index;
import Part2.Utils.MatrixUtils;

import java.util.*;

import static Part2.Utils.MatrixUtils.getReachables;


public class PathFinder {

     private static final int marked_cell = 2;
     private static final int unmarked_cell = 1;

    public static void printAllPathsAscending(Index src, Index dst, Integer[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        paths = dfs(src, dst, matrix, paths,new LinkedList<>());

        Iterator<Collection<Index>> iterator = paths.stream().sorted(Comparator.comparingInt(Collection::size))
        .iterator();
        Collection<Index> collection;
        if(iterator.hasNext()) {
            collection = iterator.next();
            int minSize = collection.size();
            paths.stream().filter(s -> s.size() == minSize).forEach(System.out::println);
        }
        else{
            System.out.println("No such route exists...");
        }
    }

    public static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, Integer[][] mat
            , LinkedHashSet<Collection<Index>> paths,
                                                       Collection<Index> parentPath) {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        Integer[][] clonedMat =  mat.clone();
        clonedMat[src.getRow()][src.getColumn()] = marked_cell;

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
            mat[newSrc.getRow()][newSrc.getColumn()] = unmarked_cell;
        }
        return paths;
    }



    public static void main(String[] args){
        Integer[][] mat = { {1,1,0,1,0},
                            {0,1,1,0,1},
                            {1,0,1,1,1}};
        mat = MatrixUtils.InitMatrix(30);
        Index src = new Index(0, 0);
        Index dst = new Index(16, 23);
        HashSet<Collection<Index>> paths = new HashSet<>();
        printAllPathsAscending(src, new Index(2, 4), mat);
    }

}