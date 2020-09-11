package Part2;

import Part2.Utils.MatrixUtils;
import java.io.*;
import static Part2.Utils.PrintUtils.*;

public class MatrixIHandler implements IHandler {

    private Integer[][] matrix;
    private Index src;
    private Index dst;

    public MatrixIHandler() { }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {
        System.out.println("server::start handle");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);
        boolean dowork = true;
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop":{
                    dowork= false;
                    break;
                }
                case "Task1": {
                    matrix = (Integer[][]) objectInputStream.readObject();
                    if (matrix!=null){
                        MatrixUtils.printMat(matrix);
                        System.out.println("---------");
                        printAllCliques(matrix);
                        System.out.println("---------");
                    }
                    break;
                }
                case "Task2": {
                    matrix = (Integer[][])  objectInputStream.readObject();
                    src = (Index) objectInputStream.readObject();
                    dst = (Index) objectInputStream.readObject();
                    if (matrix!=null && src!=null && dst!=null){
                        MatrixUtils.printMat(matrix);
                        System.out.println("---------");
                        printAllPaths(src,dst,matrix);
                        System.out.println("---------");
                    }
                    break;
                }
                case "Task3": {
                    matrix = (Integer[][]) objectInputStream.readObject();
                    src = (Index) objectInputStream.readObject();
                    dst = (Index) objectInputStream.readObject();
                    if (matrix!=null && src!=null && dst!=null) {
                        MatrixUtils.printMat(matrix);
                        printShortestPaths(src, dst, matrix);
                        System.out.println("---------");
                    }break;}
                case "Task4": {
                    matrix = (Integer[][])  objectInputStream.readObject();
                    if (matrix != null) {
                        printSubMarinesCounter(matrix);
                        System.out.println("---------");
                    }
                    break;
                }
            }
        }
    }

}