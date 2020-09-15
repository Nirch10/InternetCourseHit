package Tests;

import Part2.Index;
import Part2.Utils.MatrixUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.IOException;

import static Part2.Utils.PrintUtils.*;

public class Part2Tests {
    static Integer[][] matrix;
    @BeforeClass
    public static void testSetup() {

    }
    @AfterClass
    public static void testCleanup() {
        // Do your cleanup here like close URL connection , releasing resources etc
    }

    @Test
    public void testEx1(){
        for(int j = 0;j < 5;j++) {
            for (int i = 4; i > 0; i--) {
                matrix = MatrixUtils.fillMatrix(50,50);
                matrix[0][0] = 1;
                matrix[i*10 - 1][i*10 - 1] = 1;
                MatrixUtils.printMatrix(matrix);
                printAllCliques( matrix);
                System.out.println("number" + i + "---------------");
            }
        }
    }

    @Test
    public void testEx3(){
        for(int j = 0;j < 5;j++) {
            for (int i = 6; i > 0; i--) {
                matrix = MatrixUtils.fillMatrix(6,6);
                matrix[0][0] = 1;
                matrix[i - 1][i - 1] = 1;
                MatrixUtils.printMatrix(matrix);
                printShortestPaths(new Index(0, 0), new Index(i - 1, i - 1), matrix);

                System.out.println("number" + i + "---------------");
            }
        }
    }

    @Test
    public void testEx3SpecMatrix(){
        for (int i = 4; i > 0; i--) {
            matrix = new Integer[][]{{1, 1, 0, 0, 1},{1, 1, 1 ,0 ,0},{0 ,1, 1 ,1, 0 },{1, 1, 0, 0 ,1},{0 ,1, 0, 0, 1}};
            MatrixUtils.printMatrix(matrix);
            printShortestPaths(new Index(0, 0), new Index(1,1), matrix);

            System.out.println("number" + i + "---------------");
        }
    }

    @Test
    public void testEx2SpecMatrix(){
        for (int i = 4; i > 0; i--) {
            matrix = new Integer[][]{{1, 1, 0, 0, 1},{1, 1, 1 ,0 ,0},{0 ,1, 1 ,1, 0 },{1, 1, 0, 0 ,1},{0 ,1, 0, 0, 1}};
            MatrixUtils.printMatrix(matrix);
            printAllPaths(new Index(0, 0), new Index(1, 1), matrix);
            System.out.println("number" + i + "---------------");
        }

    }

    @Test
    public void testEx2(){
        for(int j = 0;j < 5;j++) {
            for (int i = 4; i > 0; i--) {
                matrix = MatrixUtils.fillMatrix(50,50);
                matrix[0][0] = 1;
                matrix[i*10 - 1][i*10 - 1] = 1;
                MatrixUtils.printMatrix(matrix);
                printAllPaths(new Index(0, 0), new Index(i - 1, i - 1), matrix);

                System.out.println("number" + i + "---------------");
            }
        }
    }

    @Test
    public void testEx4(){
        for(int j = 0;j < 5;j++) {
            for (int i = 6; i > 0; i--) {
               Integer[][] matrix = matrix = new Integer[][]{{0,1,0,1,1,0,1,1,1,1,0,1,0},
                        {0,1,0,1,1,0,1,1,1,1,0,1,0},
                        {0,1,0,1,1,0,1,1,1,1,0,1,0},
                        {0,1,0,0,0,0,0,0,0,0,0,1,0},
                        {0,0,0,1,1,0,1,0,1,1,0,1,0},
                        {0,0,0,0,0,0,1,0,1,1,0,1,0},
                        {0,0,0,1,1,0,1,0,1,1,0,1,0},
                };
               matrix[i][i] = 0;
                MatrixUtils.printMatrix(matrix);
                printSubMarinesCounter(matrix);
                System.out.println("number" + i + "---------------");
            }
       }
    }





}