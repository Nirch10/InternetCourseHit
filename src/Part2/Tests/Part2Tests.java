package Part2.Tests;

import Part2.Ex3.PathFinder;
import Part2.Index;
import Part2.Utils.MatrixUtils;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class Part2Tests {
    static Integer[][] matrix;
    @BeforeClass
    public static void testSetup() throws IOException {

    }
    @AfterClass
    public static void testCleanup() {
        // Do your cleanup here like close URL connection , releasing resources etc
    }

    @Test
    public void testEx3(){
       // for(int j = 0;j < 5;j++) {
            for (int i = 6; i > 0; i--) {
                matrix = MatrixUtils.addContentToMat(6,6);
                matrix[0][0] = 1;
                matrix[i - 1][i - 1] = 1;
                MatrixUtils.printMat(matrix);
                PathFinder.printAllPathsAscending(new Index(0, 0), new Index(i - 1, i - 1), matrix);

                System.out.println("number" + i + "---------------");
            }
       // }
    }

    @Test
    public void testEx3SpecMatrix(){
        for(int j = 0;j < 3;j++) {
            for (int i = 4; i > 0; i--) {
                matrix = new Integer[][]{{1, 1, 0, 0, 1},{1, 1, 1 ,0 ,0},{0 ,1, 1 ,1, 0 },{1, 1, 0, 0 ,1},{0 ,1, 0, 0, 1}};
                MatrixUtils.printMat(matrix);
                PathFinder.printAllPathsAscending(new Index(0, 0), new Index(1,1), matrix);

                System.out.println("number" + i + "---------------");
            }
        }
    }

    @Test
    public void testEx2SpecMatrix(){
      //  for(int j = 0;j < 3;j++) {
            for (int i = 4; i > 0; i--) {
                matrix = new Integer[][]{{1, 1, 0, 0, 1},{1, 1, 1 ,0 ,0},{0 ,1, 1 ,1, 0 },{1, 1, 0, 0 ,1},{0 ,1, 0, 0, 1}};
                MatrixUtils.printMat(matrix);
                Part2.Ex2.PathFinder.printAllPathsAscending(new Index(0, 0), new Index(1, 1), matrix);

                System.out.println("number" + i + "---------------");
            }
        //}
    }

    @Test
    public void testEx2(){
        //for(int j = 0;j < 5;j++) {
            for (int i = 4; i > 0; i--) {
                matrix = MatrixUtils.addContentToMat(50,50);
                matrix[0][0] = 1;
                matrix[i*10 - 1][i*10 - 1] = 1;
                MatrixUtils.printMat(matrix);
                Part2.Ex2.PathFinder.printAllPathsAscending(new Index(0, 0), new Index(i - 1, i - 1), matrix);

                System.out.println("number" + i + "---------------");
            }
       // }
    }



}