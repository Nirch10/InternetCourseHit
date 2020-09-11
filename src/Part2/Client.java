package Part2;

import Part2.Utils.MatrixUtils;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
        static final int dimensionLimit = 500;
        static final int port = 8010;
        static final String serverIp = "127.0.0.1";
        static Integer[][] clientMatrix;
        static Index source;
        static Index dest;
        static Scanner scanner;
        private static InputStream inputStream;
        private static OutputStream outputStream;
        private static  ObjectOutputStream toServer ;
        private static ObjectInputStream fromServer;
        private static Socket socket;

        private static void continueProgram(){
                continueProgram("Type enter btn to continue");
        }
        private static void continueProgram(String msg){
                System.out.println(msg);
                scanner.nextLine();
        }
        private static void Ex1Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #1");
                clientMatrix = MatrixUtils.InitMatrix(dimensionLimit);
                toServer.writeObject("Task1");
                toServer.writeObject(clientMatrix);
                System.out.println("Head to your server for the result");

        }
        private static void Ex2Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #2");
                clientMatrix = MatrixUtils.InitMatrix(dimensionLimit);
                source = MatrixUtils.getIndex("source", clientMatrix);
                dest = MatrixUtils.getIndex("destination", clientMatrix);
                toServer.writeObject("Task2");
                toServer.writeObject(clientMatrix);
                toServer.writeObject(source);
                toServer.writeObject(dest);
                System.out.println("Head to your server for the result");

        }
        private static void Ex3Runner(ObjectOutputStream toServer){
                System.out.println("Starting Task #3");
                clientMatrix = MatrixUtils.InitMatrix(dimensionLimit);
                source = MatrixUtils.getIndex("source", clientMatrix);
                dest = MatrixUtils.getIndex("destination", clientMatrix);
                try {
                        toServer.writeObject("Task3");
                        toServer.writeObject(clientMatrix);
                        toServer.writeObject(source);
                        toServer.writeObject(dest);
                } catch (Exception ex) {
                        System.out.println("problem");
                }
                System.out.println("Head to your server for the result");

        }
        private static void Ex4Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #4");
                clientMatrix = MatrixUtils.InitMatrix(dimensionLimit,0);
                clientMatrix = addSubMarines(clientMatrix);
                toServer.writeObject("Task4");
                toServer.writeObject(clientMatrix);
                System.out.println("Head to your server for the result");
        }

        private static Integer[][] addSubMarines(Integer[][] matrix){
                int startRow = 0,startCol = 0,endRow = 0,endCol = 0;
                while (startRow != -1 && startCol != -1 && endCol != -1 && endRow != -1){
                        System.out.println("Type your marine start row index (to finish type -1)");
                        startRow = scanner.nextInt();
                        if(startRow == -1)break;
                        System.out.println("Type your marine start col index (to finish type -1)");
                        startCol = scanner.nextInt();
                        if(startCol == -1)break;
                        System.out.println("Type your marine end row index (to finish type -1)");
                        endRow = scanner.nextInt();
                        if(endRow == -1)break;
                        System.out.println("Type your marine end col index (to finish type -1)");
                        endCol = scanner.nextInt();
                        if(tryAddSubmarine(startRow,startCol,endRow,endCol,matrix))
                                System.out.println("Added marine successfully");
                        else
                                System.out.println("Wrong inputs..");
                }
                return matrix;
        }
        private static boolean tryAddSubmarine(int startRow, int startCol, int endRow, int endCol, Integer[][] matrix) {
                if(startCol < 0 || startRow < 0 || endCol < 0 || endRow < 0)
                        return false;
                if(startRow >= matrix.length || endRow >= matrix.length || startCol >= matrix[0].length || endCol >= matrix[0].length)
                        return false;
                for(int i =startRow; i <= endRow; i++)
                        for (int j = startCol; j <= endCol; j++)
                                matrix[i][j] = 1;
                return true;
        }

        private static void connectToServer(String serverIp, int port) throws IOException {
                socket = new Socket(serverIp, port);
                System.out.println("client::Socket");
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                toServer = new ObjectOutputStream(outputStream);
                fromServer = new ObjectInputStream(inputStream);
        }

        public static void main(String[] args) throws IOException{
                scanner = new Scanner((System.in));
                connectToServer(serverIp, port);
                Ex1Runner(toServer);
                continueProgram();
                Ex2Runner(toServer);
                continueProgram();
                Ex3Runner(toServer);
                continueProgram();
                Ex4Runner(toServer);

                continueProgram("Type enter to close connection");
                toServer.writeObject("stop");

                System.out.println("client::Closing all streams!!!!");
                fromServer.close();
                toServer.close();
                socket.close();
                System.out.println("client::Closing socket!!!!");
        }

}