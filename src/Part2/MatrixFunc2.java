package Part2;

import com.sun.istack.internal.NotNull;
import sun.misc.Queue;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;
//
//public class MatrixFunc2 implements Traversable<Index>  {
//        private final Matrix matrix;
//        private Index index;
//
//        public MatrixFunc2(@NotNull final Matrix matrix) {
//           this.matrix = matrix;
//        }
//
//        public Index getIndex() {
//            return index;
//        }
//
//        public void setIndex(@NotNull final Index index) {
//            this.index = index;
//        }
//
//        @NotNull
//        @Override
//        public GraphNode<Index> getOrigin() throws NullPointerException {
//            if (this.index == null) throw new NullPointerException("initIndex is not initialized");
//            return new GraphNode<>(this.index);
//        }
//
//        @NotNull
//        @Override
//        public Collection<GraphNode<Index>> getReachableNodes(@NotNull final GraphNode<Index> s) {
//            return this.matrix.getNeighbors(s.getData()).stream().filter(index -> matrix.getValue(index) == 1)
//                    .map(neighbor -> new GraphNode<>(neighbor, s)).collect(Collectors.toList());
//        }
//
//        public static void main(String[] args) {
//            GenericService genericService = new GenericService();
//            try {
//                final Future<List<HashSet<Index>>> futureList = genericService.apply(() -> {
//                    // Each component scan is requires ThreadLocal collections
//                    // However, performing multiple scans and adding each group of 1's list requires a synchronized collection
//                    List<HashSet<Index>> indexList = Collections.synchronizedList(new ArrayList<>());
//                    HashSet<Index> seenIndexes = new HashSet<>();
//
//                    Traverse<Index> algorithm = new TraverseLogic<>();
//                    final Matrix matrix = new Matrix();
//                    MatrixAsGraph graph = new MatrixAsGraph(matrix);
//                    for (int i = 0; i < matrix.primitiveMatrix.length; i++) {
//                        for (int j = 0; j < matrix.primitiveMatrix[0].length; j++) {
//                            final Index index = new Index(i, j);
//                            if (matrix.getValue(index) == 1 && !seenIndexes.contains(index)) {
//                                graph.setIndex(index);
//                                final AbstractList<Index> list = algorithm.traverse(graph);
//
//                                HashSet<Index> hashSet = new HashSet<>(list);
//                                indexList.add(hashSet);
//                                seenIndexes.addAll(hashSet);
//                            }
//                        }
//                    }
//                    return indexList;
//                }, FutureTask::new);
//
//                final List<HashSet<Index>> hashSets = futureList.get();
//                hashSets.sort((Comparator.comparingInt(HashSet::size)));
//                hashSets.forEach(System.out::println);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                genericService.stop(true);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("done!");
//        }
//    }
//}


public class MatrixFunc2{

     private static final int marked_cell = 2;

    public void printAllPathsAscending(Index src, Index dst, Integer[][] matrix){
        HashSet<Collection<Index>> paths = new HashSet<>();



    }

    private static HashSet<Collection<Index>> getAllPaths(Index src, Index dst, HashSet<Collection<Index>> paths,
                                                   Integer[][] matrix, Deque<Index> neighborsQueue){
        matrix[src.row][src.column] = marked_cell;
        //finds the end of paths..
        if (src.equals(dst)) return paths;
        //get all current src neighbors.
        Collection<Index> neighbors = getReachables(matrix, src);
        if(neighbors == null) return paths;
        HashSet<Collection<Index>> tmp = paths;
        HashSet<Collection<Index>> finalPaths = paths;
        neighbors.forEach(neighbor -> {
            matrix[neighbor.row][neighbor.column] = marked_cell;
            neighborsQueue.add(neighbor);
            finalPaths.forEach(path -> {
                int pathSize = path.toArray().length;
                if (path.toArray()[pathSize - 1].equals(src))
                {
                    LinkedList list = new LinkedList(path);
                    list.add(neighbor);
                    tmp.add(list);
                }
            });
        });
        paths = tmp;
        Index newSrc = neighborsQueue.remove();
        return getAllPaths(newSrc, dst, paths, matrix, neighborsQueue);
    }


    public static Collection<Index> getAdjacentIndices(Integer[][] matrix, final Index index) {
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try {
            extracted = matrix[index.row + 1][index.column];
            list.add(new Index(index.row + 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.row][index.column + 1];
            list.add(new Index(index.row, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.row - 1][index.column];
            list.add(new Index(index.row - 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        try {
            extracted = matrix[index.row][index.column - 1];
            list.add(new Index(index.row, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.row - 1][index.column - 1];
            list.add(new Index(index.row - 1, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.row + 1][index.column - 1];
            list.add(new Index(index.row + 1, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.row + 1][index.column + 1];
            list.add(new Index(index.row + 1, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            extracted = matrix[index.row - 1][index.column + 1];
            list.add(new Index(index.row - 1, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return list;
    }

    public static int getValue(Integer[][] matrix, Index index) {
        return matrix[index.row][index.column];
    }

    public static Collection<Index> getReachables(Integer[][] matrix, Index index) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        getAdjacentIndices(matrix, index).stream().filter(i-> getValue(matrix, i)==1)
                .map(neighbor->filteredIndices.add(neighbor)).collect(Collectors.toList());
        return filteredIndices;
    }


    public static void main(String[] args){
        Integer[][] mat = {{1,0,0,1, 0},{0,1,0,0,1},{1,0,1,1,1}};
        HashSet<Collection<Index>> hs = new HashSet<>();
        Index src = new Index(0, 0);
        hs.add(new LinkedList<Index>(Collections.singleton(src)));
        getAllPaths(src, new Index(3,4),hs, mat, new ArrayDeque<>());


    }

}