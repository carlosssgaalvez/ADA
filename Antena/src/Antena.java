import java.util.Scanner;
import java.util.Arrays;
/*
 * Se desea construir una antena de telefonía de M metros de altura. 
 * Para ello se dispone de un suministro finito de bloques de armazón de n tipos distintos, cada uno de altura a[i] y peso p[i] . 
 * El objetivo es determinar cuántos bloques hay que emplear de cada tipo, de manera que la antena mida exactamente M metros y tenga el peso mínimo. 
 * 
 * */
public class Antena {
    public static class Res{
		public int peso;
		public int[] sol;

		public Res(int peso, int[] sol) {
			this.peso = peso;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return peso +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.peso == aux.peso;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.peso);
		}
	}
    
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
	        String[] speso = sc.next().split(",");
    	    String[] saltura = sc.next().split(",");
    	    String[] scantidad = sc.next().split(",");
    	    int M = Integer.parseInt(sc.next());
    	    
    	    int[] peso = new int[speso.length];
    	    int[] altura = new int[saltura.length];
    	    int[] cantidad = new int[scantidad.length];
    	    
    	    for (int i = 0 ; i < peso.length; i++) peso[i] = Integer.parseInt(speso[i]);
    	    for (int i = 0 ; i < altura.length; i++) altura[i] = Integer.parseInt(saltura[i]);
    	    for (int i = 0 ; i < cantidad.length; i++) cantidad[i] = Integer.parseInt(scantidad[i]);
    	    
    	    Res k = antena(peso,altura,cantidad,M);
    	    
    	    System.out.println(k.toString());
	    }
	}
	
	public static Res antena(int[]p, int [] a, int[] q, int H){
		int n = p.length;
        int[][] PM = new int[n][H+1];
        
        for (int i = 0; i < n; i++){
        	for(int h = 0; h <= H; h++ ){			// Recorremos todo el array
                 //Copiar la ecuacion de Bellman
        		if (i==0 && (h % a[0] != 0 || (h/a[0])>q[0])) { 	// Fila 0 cuando no queda alturia altura exacta o no tengo suficiente cantidad
           			PM[0][h]= Integer.MAX_VALUE /2;
                } else if (i==0) {					// evaluo los que son exactos y tengo suficiente cantidad
                	PM[i][h]= (h/a[0])*p[0];
                } else{
                	PM[i][h] = PM[i-1][h]; 			// k==0
                	for (int k = 1; k <= Math.min(q[i],h/a[i]); k++){		// vamos utilizando este nuevo bloque y nos quedamos con el minimo
                		PM[i][h] = Math.min(PM[i][h], k*p[i] + PM[i-1][h-k*a[i]]);
                	}
                }
        	}
        }
         
        int [] sol = new int[n];
        int i = n-1;
        int h = H;
        while (i>0){
        	int k = 0;
        	while (PM[i][h]!= k*p[i] + PM[i-1][h-k*a[i]]) {
        		k++;
        	}
             sol[i] = k;
             h -= k*a[i];
             i--;
        }
        sol[0] = PM[0][h] / p[0];
         
        return new Res(PM[n-1][H],sol);				// esquina abajo derecha, numero de bloques de cada tipo
      }
}//end Antena

