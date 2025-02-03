package Task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class MatrixMultiplier 
{
    private int[][] result;
    private int[][] matrixA;
    private int[][] matrixB;
    private int rows;
    private int cols;
    public MatrixMultiplier(int[][] matrixA, int[][] matrixB)
    {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.rows = matrixA.length;
        this.cols = matrixB[0].length;
        this.result = new int[rows][cols];
    }
    public int[][] multiplyMatrices()
    {
        ExecutorService executor = Executors.newFixedThreadPool(rows * cols);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                int row = i;
                int col = j;
                executor.execute(() -> computeElement(row, col));
            }
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }
        return result;
    }
    private void computeElement(int row, int col)
    {
        int sum = 0;
        for (int k = 0; k < matrixA[0].length; k++)
        {
            sum += matrixA[row][k] * matrixB[k][col];
        }
        result[row][col] = sum;
    }
}

class MultiThreadingExer
{
    public static void main(String[] args)
    {
        int[][] matrixA = {{6, 2}, {7, 4}};
        int[][] matrixB = {{2, 0}, {2, 5}}; 
        MatrixMultiplier multiplier = new MatrixMultiplier(matrixA, matrixB);
        int[][] result = multiplier.multiplyMatrices();
        
        System.out.println("Result of the multiplication:");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
