package ks;

import java.util.List;

public class Memoization {

    public static int[] memoization(List<Item> items, int bag) {
        int value = 0;
        int[] taken = new int[items.size()];
        int n = items.size();
        int[][] matrix = new int[n+1][bag+1];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = -1;
            }
        }
        
        value = calculateMatrix(items, bag, matrix, n);
        
        int i = matrix.length - 1;
        int k = matrix[0].length - 1;
        
        while (i > 0 && k > 0){
            if(matrix[i][k] != 0 && matrix[i][k] != matrix[i-1][k]){
                taken[items.get(i - 1).getIndex()] = 1;
                k = k - items.get(i - 1).getWeight();
            }
            i = i - 1;
        }
        
        return taken;
    }

    private static int calculateMatrix(List<Item> items, int bag, int[][] matrix, int n) {
        if (bag == 0 || n == 0){
            return 0;
        }
        
        if(matrix[n][bag] != -1){
            return matrix[n][bag];
        }
        
        if(items.get(n - 1).getWeight() > bag){
            matrix[n][bag] = calculateMatrix(items, bag, matrix, n-1);
        }else{
            matrix[n][bag] = Math.max(items.get(n - 1).getValue() + calculateMatrix(items, bag - items.get(n - 1).getWeight(), matrix, n - 1), calculateMatrix(items, bag, matrix, n-1));
        }
        return matrix[n][bag];
    }
    
}
