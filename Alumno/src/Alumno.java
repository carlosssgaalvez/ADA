import java.util.Scanner;
import java.util.Arrays;

public class Alumno {
    public static class Res{
		public int sumaNotas;
		public int[] sol;

		public Res(int sumaNotas, int[] sol) {
			this.sumaNotas = sumaNotas;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return sumaNotas +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.sumaNotas == aux.sumaNotas;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.sumaNotas);
		}
	}
    
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
	        int NumAsig = Integer.parseInt(sc.next());
    	    int NumHoras = Integer.parseInt(sc.next());
    	    int[][] C = new int[NumAsig][NumHoras+1];
    	    for (int i = 0; i < NumAsig; i++){
    	        String[] datosAsig = sc.next().split(",");
    	        for (int j = 0; j <= NumHoras; j++){
    	            C[i][j] = Integer.parseInt(datosAsig[j]);
    	        }   
    	    }
    	    
    	    Res k = nota(NumAsig,NumHoras,C);
    	    
    	    System.out.println(k.toString());
	    }
	}
	
	public static Res nota(int n, int H, int[][] C) {

	    int[][] matriz = new int[n + 1][H + 1];

	    for (int i = 1; i <= n; i++) {
	        for (int j = 0; j <= H; j++) {
	            for (int k = 0; k <= j; k++) {	       
	                matriz[i][j] = Math.max(matriz[i][j], matriz[i - 1][j - k] + C[i - 1][k]);
	            }
	        }
	    }

	    int[] horasAsignatura = new int[n];
	    int horasRestantes = H;

	    for (int i = n; i > 0; i--) {
	        for (int k = 0; k <= horasRestantes; k++) {
	            if (matriz[i][horasRestantes] == matriz[i - 1][horasRestantes - k] + C[i - 1][k]) {
	                horasAsignatura[i - 1] = k;
	                horasRestantes -= k;
	                break;
	            }
	        }
	    }

	    return new Res(matriz[n][H], horasAsignatura);

	}
}//end