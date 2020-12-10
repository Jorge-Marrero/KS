package ks;

import java.util.List;

public class Tabulation {

    public static int[] tabulation(List<Item> items, int bag) {
        int[] taken = new int[items.size()];
        
        int[][] matrix = new int[items.size()+1][bag+1];
        
        int beneficio_i;
        int peso_i;
        for (int i = 1; i < items.size()+1; i++){
            beneficio_i = items.get(i-1).getValue();
            peso_i = items.get(i-1).getWeight();
            for (int w = 1; w < bag+1; w++){
                if(peso_i <= w){
                    if((beneficio_i + matrix[i-1][w-peso_i]) > matrix[i-1][w]){
                        matrix[i][w] = beneficio_i + matrix[i-1][w-peso_i];
                    }else{
                        matrix[i][w] = matrix[i-1][w];
                    }
                }else{
                    matrix[i][w] = matrix[i-1][w];
                }
            }
        }
        
        int i = items.size();
        int k = bag;
        while (i > 0 && k > 0){
            if (matrix[i][k] != matrix[i-1][k]){
                taken[i-1] = 1;
                i--;
                k = k - items.get(i).getWeight();
            }else{
                i--;
            }
        }
        return taken;
    }
    
    public static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}

