import java.util.Scanner;
import java.util.Arrays;

public class AsignacionTareas {

	// PROPIEDADES DEL PROBLEMA
	int n; // tamaño de la matriz
	int [] solucion;
	int [][]matriz;
	int valor;
    

public AsignacionTareas (int tam) {
	n = tam; 	solucion = new int [n]; matriz = new int [n][n];
	valor=Integer.MAX_VALUE;	
}	



	void vueltaAtras() {
		// A IMPLEMENTAR POR EL ALUMNO. PUEDE IMPLEMENTAR TANTOS M�TODOS AUXILIARES COMO CREA CONVENIENTE.
	}
	
	private static int[][] readMatrix(Scanner scanner) {				
	
			String[] temp = scanner.nextLine().split(" ");
			int nMatrix = temp.length;
		    int [][]matrix = new int[nMatrix][nMatrix];
		    for (int j = 0; j < nMatrix; j++) {
	            matrix[0][j] = Integer.parseInt(temp[j]);
	        }
		    for (int i = 1; i < nMatrix; i++) {
		        String[] numbers = scanner.nextLine().split(" ");
		        for (int j = 0; j < nMatrix; j++) {
		            matrix[i][j] = Integer.parseInt(numbers[j]);
		        }
		    }
		    scanner.close();
			return matrix;	
	}

	public static void main(String [] args){
		int [][]entrada=readMatrix(new Scanner(System.in));
		AsignacionTareas r=new AsignacionTareas(entrada.length);
		
		r.matriz=entrada.clone();
		r.vueltaAtras();
		System.out.println("Solucion: "+Arrays.toString(r.solucion));
		System.out.print("Coste: "+r.valor);
	}
}