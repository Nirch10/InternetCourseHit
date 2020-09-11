package Part2.Utils;

import Part2.Ex1.CliqueFinder;
import Part2.Index;

import java.util.*;

import static Part2.Ex1.CliqueFinder.getAllCliques;
import static Part2.Ex2and3.PathFinder.dfs;
import static Part2.Ex4.Marines.areCliquesSquared;

public class PrintUtils {

    //Ex1 printer
    public static void printAllCliques(Integer[][] matrix){
        HashSet<Collection<Index>> cliques = getAllCliques(matrix);
        cliques.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(clique -> System.out.println(clique));
    }
    //Ex 2 printer
    public static void printAllPaths(Index src, Index dst, Integer[][] matrix){
        LinkedHashSet<Collection<Index>> paths = new LinkedHashSet<>();
        //executor =  Executors.newFixedThreadPool(10);
        paths = dfs(src,dst,matrix,paths,new LinkedList<>());
        //executor.shutdown();
        paths.stream().sorted(Comparator.comparingInt(Collection::size)).forEach(System.out::println);
    }
    //Ex 3 printer
    public static void printShortestPaths(Index src, Index dst, Integer[][] matrix){
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
    //Ex 4 printer
    public static void printSubMarinesCounter(Integer[][] matrix){
        HashSet<Collection<Index>> cliques = CliqueFinder.getAllCliques(matrix);
        if(areCliquesSquared(cliques))
            System.out.println("There are " + cliques.size() + " sub-Marines");
        else
            System.out.println("Invalid matrix");
    }


}
