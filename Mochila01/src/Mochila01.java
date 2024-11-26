import java.util.Scanner;
import java.util.Arrays;
/*
 * Se dispone de n objetos, cada uno con un peso (wi ) y un beneficio (vi ). 
 * También se dispone de una mochila en la que se pueden meter dichos objetos, con una capacidad de peso máximo M. 
 * Sin perdida de generalidad se supondrá que todos los valores son > 0. 
 * El objetivo consiste en maximizar el valor de los objetos transportados y respetando la limitación de capacidad máxima M
 * 
 * */
public class Mochila01 {
    public static class Res{
		public int beneficio;
		public int[] sol;

		public Res(int beneficio, int[] sol) {
			this.beneficio = beneficio;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return beneficio +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.beneficio == aux.beneficio;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.beneficio);
		}
	}
    
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    String[] speso = sc.next().split(",");
    	    String[] sbeneficio = sc.next().split(",");
    	    int W = Integer.parseInt(sc.next());
    	    
    	    Integer[] peso = new Integer[speso.length];
    	    Integer[] beneficio = new Integer[sbeneficio.length];
    	    
    	    for (int i = 0 ; i < peso.length; i++) peso[i] = Integer.parseInt(speso[i]);
    	    for (int i = 0 ; i < beneficio.length; i++) beneficio[i] = Integer.parseInt(sbeneficio[i]);
    	    
    	    Res k = mochila01(peso,beneficio,W);
    	    
    	    System.out.println(k.toString());
	    }
	}
	
	public static Res mochila01(Integer[] peso, Integer[] beneficio, int W) {
		int[][] matriz = new int[peso.length+1][W+1];
		
		for(int i = 0; i <= peso.length; i++) {
			for(int j = 0; j <= W; j++) { 			// rellenamos la primera fila y columna a 0
				if(i==0 || j ==0) {
					matriz[i][j] = 0;
				} else if(peso[i-1] > j){			// es un peso superios cogeremos la fila anterior
					matriz[i][j] = matriz[i-1][j];
				} else {
					matriz[i][j] = Math.max(matriz[i-1][j], beneficio[i-1] + matriz[i-1][j-peso[i-1]]);
				}
			}
		}
		
		int ben = matriz[peso.length][W];
		int[] resultado = new int[peso.length];
		int j = W;
		
		for(int i = peso.length; i > 0 &&  ben > 0; i--) {
			if(matriz[i-1][j] != ben) {
				resultado[i-1] = 1;		// hemos utilizado este objeto
				j = j-peso[i-1];
				ben = matriz[i-1][j];
			}
		}
		
		return new Res(matriz[peso.length][W], resultado);
	}
	
	/* SOLUCION BUENA
	public static Res mochila01(Integer[] peso, Integer[] beneficio, int W) {
	    int n = peso.length;
	    int[][] dp = new int[n+1][W+1];

	    for (int i = 0; i <= n; i++) {
	        for (int w = 0; w <= W; w++) {
	            if (i == 0 || w == 0)
	                dp[i][w] = 0;
	            else if (peso[i-1] <= w)
	                dp[i][w] = Math.max(beneficio[i-1] + dp[i-1][w-peso[i-1]], dp[i-1][w]);
	            else
	                dp[i][w] = dp[i-1][w];
	        }
	    }

	    int res = dp[n][W];
	    int w = W;
	    int[] sol = new int[n];

	    for (int i = n; i > 0 && res > 0; i--) {
	        if (res != dp[i-1][w]) {
	            sol[i-1] = 1;
	            res = res - beneficio[i-1];
	            w = w - peso[i-1];
	        }
	    }

	    return new Res(dp[n][W], sol);
	}*/
	
}//end class Mochila01

