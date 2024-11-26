import java.util.Scanner;
import java.util.Arrays;
/*
 * Tenemos un suministro finito de monedas de n denominaciones con valores d1 , · · · , dn . 
 * Tenemos que pagar una cierta cantidad M. 
 * ¿Cuál es la forma de hacerlo empleando el número mínimo de monedas?
 * 
 * */
public class Cambio {
    public static class Res{
		public int numeroMonedas;
		public int[] sol;

		public Res(int numeroMonedas, int[] sol) {
			this.numeroMonedas = numeroMonedas;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return numeroMonedas +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.numeroMonedas == aux.numeroMonedas;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.numeroMonedas);
		}
	}//end Res
    
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    String[] svalor = sc.next().split(",");
    	    String[] scantidad = sc.next().split(",");
    	    int C = Integer.parseInt(sc.next());
    	    
    	    int[] valor = new int[svalor.length];
    	    int[] cantidad = new int[scantidad.length];
    	    
    	    for (int i = 0 ; i < valor.length; i++) valor[i] = Integer.parseInt(svalor[i]);
    	    for (int i = 0 ; i < cantidad.length; i++) cantidad[i] = Integer.parseInt(scantidad[i]);
    	    
    	    Res k = cambio(valor,cantidad,C);
    	    
    	    System.out.println(k.toString());
	    }
	}
	
	public static Res cambio(int[] v,int[] q, int C){
		int n = v.length+1;
		int [][] matriz = new int[n][C+1];
		
		for(int i = 0; i < n; i++) {
			for(int c = 0; c <= C; c++) {
				if(i == 0) {								//Fila 0
					matriz[i][c] = Integer.MAX_VALUE/2;
				} else if(i == 1 && (c%v[i-1] != 0 || c/v[i-1] > q[i-1])) {
					matriz[i][c] = Integer.MAX_VALUE/2;
				} else if(i == 1) {
					matriz[i][c] = c/v[i-1];
				} else {
					matriz [i][c] = matriz[i-1][c];		// k==0
					for(int k = 1; k <= Math.min(c/v[i-1], q[i-1]); k++) {
						matriz[i][c] = Math.min(matriz[i][c], k + matriz[i-1][c-k*v[i-1]]);
					}
				}
			}
		}
		
		 int[] res = new int[n-1];
		 int j = C;

		 for(int i=n-1; i>0 && j>0; i--){
			 while(matriz[i][j]!=matriz[i-1][j] && j>0 && res[i-1] < q[i-1]){
				 res[i-1]++;
				 j -= v[i-1];
		     }
		 }
	    	
		return new Res(matriz[n-1][C], res);
	}
}//end class Cambio