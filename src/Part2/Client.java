package Part2;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
        static final int dimensionLimit = 500;
        static int [][] matToPreform;
        static Index sourceIndex;
        static Index destinationIndex;
        public static void main(String[] args) throws IOException, ClassNotFoundException {
                Socket socket = new Socket("127.0.0.1", 8010);
                System.out.println("client::Socket");

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream toServer = new ObjectOutputStream(outputStream);
                ObjectInputStream fromServer = new ObjectInputStream(inputStream);

                // sending commands for task #1
                System.out.println("Starting Task #1");
                System.out.println("****************");
                matToPreform = InitMatrix();
                toServer.writeObject("Task1");
                toServer.writeObject(matToPreform);

                // sending commands for task #2
                System.out.println("Starting Task #2");
                System.out.println("****************");
                matToPreform = InitMatrix();
                sourceIndex = getIndex("source");
                destinationIndex = getIndex("destination");
                toServer.writeObject("Task2");
                toServer.writeObject(matToPreform);
                toServer.writeObject(sourceIndex);
                toServer.writeObject(destinationIndex);

                // sending commands for task #3
                System.out.println("Starting Task #3");
                System.out.println("****************");
                matToPreform = addContentToMat(50,50);
                sourceIndex = getIndex("source");
                destinationIndex = getIndex("destination");
                toServer.writeObject("Task3");
                toServer.writeObject(matToPreform);
                toServer.writeObject(sourceIndex);
                toServer.writeObject(destinationIndex);

                // sending commands for task #4
                System.out.println("Starting Task #4");
                System.out.println("****************");
                matToPreform = InitMatrix();
                toServer.writeObject("Task4");
                toServer.writeObject(matToPreform);




                // sending #3 index for getAdjacentIndices
                toServer.writeObject("AdjacentIndices");
                toServer.writeObject(new Index(1, 1));
                // receiving #1 getAdjacentIndices
                Collection<Index> AdjacentIndices = new ArrayList<Index>((Collection<Index>) fromServer.readObject());
                System.out.println("client::getAdjacentIndices:: " + AdjacentIndices);

                // sending #4 index for getReachables
                toServer.writeObject("Reachables");

                toServer.writeObject(new Index(1, 1));
                // receiving #2 getReachables
                Collection<Index> ReachablesIndices = new ArrayList<Index>((Collection<Index>) fromServer.readObject());
                System.out.println("client::ReachablesIndices:: " + ReachablesIndices);

                toServer.writeObject("stop");

                System.out.println("client::Close all streams!!!!");
                fromServer.close();
                toServer.close();
                socket.close();
                System.out.println("client::Close socket!!!!");
        }

        private static Index getIndex(String source) {
                int x = getNumberInLimits(matToPreform.length,"x cord");
                int y = getNumberInLimits(matToPreform[0].length,"y cord");
                return new Index(x,y);
        }

        private static int[][] InitMatrix() {
                int row = getNumberInLimits(dimensionLimit,"rows number : ");
                int col = getNumberInLimits(dimensionLimit,"columns number : ");
                return addContentToMat(row,col);
        }

        private static int[][] addContentToMat(int row, int col) {
                int[][] returnMat = new int[row][col];
                Random rnd = new Random();
                for (int i=0;i<row;i++)
                {
                        for (int j=0;j<col;j++){
                                returnMat[i][j] = Math.abs(i + rnd.nextInt() + j * rnd.nextInt()) %2;
                        }
                }
                return returnMat;
        }



        private static int getNumberInLimits(int limit, String limitName){
                Scanner scn = new Scanner((System.in));
                System.out.println("Please enter " + limitName  );
                int dimensionSize = scn.nextInt();
                while ((dimensionSize < 0) || (dimensionSize > limit))
                {
                        System.out.println("You entered invalid input. Please enter valid input: ");
                        dimensionSize = scn.nextInt();
                }
                return dimensionSize;
        }
}

