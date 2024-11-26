import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/*
 * Dado un vector ordenado V de n enteros distintos, 
 * escribir un algoritmo que en tiempo O(logn) encuentre 
 * un número i tal que 1 ≤ i ≤ n y V[i] = i, 
 * siempre que exista. 
 * 
 * En caso de que no exista, debera devolver -1
 * */
public class Ejer1 {
	public static void main( String[] args){
	    try(Scanner sc = new Scanner(System.in)){
    	    List<Integer> l = new ArrayList<>();
    	    while(sc.hasNext()) l.add(sc.nextInt());
    	    
    	    Integer[] v = new Integer[l.size()];
            l.toArray(v);
    	    
    	    int k = ejer1(v);
    	    
    	    System.out.println(Integer.toString(k));
	    }
	}
	
	public static int ejer1(Integer[] v) {
		return ejer1REC(v,0,v.length-1);
	}

	private static int ejer1REC(Integer[] v, int i, int j) {
		int indice = -1;
		if(i <= j) { 						// PRECONDICION
			if(j-i <= 1) { 					// caso en el que llegamos a solo 2 elementos
				if(v[i] == i) { 			// si hay solucion nos quedamos con el menor, sino devolvemos indice -1
					indice = i;
				} else if(v[j] == j) {
					indice = j;
				}
			} else { 							// todavía no hemos encontrado solución
				int aux = (i+j+1)/2; 			// encontrarnos en mitad del array
				if (v[aux] >= aux) {
					indice = ejer1REC(v,i,aux);
				} else {
					indice = ejer1REC(v,aux+1,j);
				}		
			}
		}
		return indice;	
	}
}

