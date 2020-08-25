package Part2.Ex2;

import Part2.Index;
import java.util.*;
import java.util.stream.Collectors;


public class MatrixFunc2{

     private static final int marked_cell = 2;
     private static final int unmarked_cell = 1;

    public static void printAllPathsAscending(Index src, Index dst, Integer[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        paths = dfs(src, dst, matrix,new Stack<>(), paths,new LinkedList<>());
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
    }

    private static LinkedHashSet<Collection<Index>> dfs(Index src, Index dst, Integer[][] mat,
                                                        Stack<Index> neighbors, LinkedHashSet<Collection<Index>> paths,
                                                        Collection<Index> parentPath) {
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

        srcNeighbors.forEach(neighbor -> neighbors.push(neighbor));
        while (neighbors.empty() == false){
            Index newSrc = neighbors.pop();
            paths = dfs(newSrc, dst,clonedMat,new Stack<>(), paths, newParentPath);
            newParentPath = new LinkedList<>(parentPath);
            clonedMat = mat.clone();
            mat[newSrc.getRow()][newSrc.getColumn()] = unmarked_cell;
        }
        return paths;
    }


    private static Collection<Index> getAdjacentIndices(Integer[][] matrix, final Index index) {
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try {
            extracted = matrix[index.getRow() + 1][index.getColumn()];
            list.add(new Index(index.getRow() + 1, index.getColumn()));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.getRow()][index.getColumn() +  1];
            list.add(new Index(index.getRow(), index.getColumn() + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.getRow() - 1][index.getColumn()];
            list.add(new Index(index.getRow() - 1, index.getColumn()));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.getRow()][index.getColumn() - 1];
            list.add(new Index(index.getRow(), index.getColumn() - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() - 1][index.getColumn() - 1];
            list.add(new Index(index.getRow() - 1, index.getColumn() - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() + 1][index.getColumn() - 1];
            list.add(new Index(index.getRow() + 1, index.getColumn() - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() + 1][index.getColumn() + 1];
            list.add(new Index(index.getRow() + 1, index.getColumn() + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.getRow() - 1][index.getColumn() + 1];
            list.add(new Index(index.getRow() - 1, index.getColumn() + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return list;
    }

    private static int getValue(Integer[][] matrix, Index index) {
        return matrix[index.getRow()][index.getColumn()];
    }

    private static Collection<Index> getReachables(Integer[][] matrix, Index index) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        try{

            getAdjacentIndices(matrix, index).stream().filter(i-> getValue(matrix, i)==1)
                    .map(neighbor->filteredIndices.add(neighbor)).collect(Collectors.toList());
            return filteredIndices;
        }catch (Exception e) {
            return filteredIndices;
        }
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