package Part2.Ex3;

import Part2.Index;
import Part2.Utils.MatrixUtils;

import java.util.*;

import static Part2.Utils.MatrixUtils.getReachables;


public class PathFinder {

     private static final int marked_cell = 2;
     private static final int unmarked_cell = 1;

    public static LinkedHashSet<Collection<Index>> printAllPathsAscending(Index src, Index dst, int[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();

        /*for (int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix[0].length;j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }

*/
       // System.out.println(src.toString());

        //System.out.println(dst.toString());
        paths = dfs(src, dst, matrix, paths,new LinkedList<>());
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
       return paths;


    }

    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, int[][] mat
                                                        , LinkedHashSet<Collection<Index>> paths,
                                                        Collection<Index> parentPath) {
        parentPath.add(src);
        Collection<Index> newParentPath = new LinkedList<>(parentPath);
        //int[][] clonedMat = MatrixUtils.cloneMatrix(mat);
        int[][] clonedMat = mat.clone();
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
          //  clonedMat = MatrixUtils.cloneMatrix(mat);
            clonedMat = mat.clone();
            mat[newSrc.getRow()][newSrc.getColumn()] = unmarked_cell;
        }
        return paths;
    }



    public static void main(String[] args){
        int[][] mat = {     {1,1,0,1,0},
                            {0,1,1,0,1},
                            {1,0,1,1,1}};
        Index src = new Index(0, 0);
        Index dst = new Index(2, 4);
        HashSet<Collection<Index>> paths = new HashSet<>();
        paths = printAllPathsAscending(src, new Index(2, 4), mat);
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
    }

}