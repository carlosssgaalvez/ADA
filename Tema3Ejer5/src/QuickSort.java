import java.util.Scanner;
import java.util.Arrays;

/*
 * Implementar el algoritmo de ordenación rápida
 * 
 * */
public class QuickSort {
    
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    String[] sv = sc.next().split(",");
    	    
    	    Integer[] v = new Integer[sv.length];
    	    
    	    for (int i = 0 ; i < sv.length; i++) v[i] = Integer.parseInt(sv[i]);
    	    
    	    sort(v);
    	    
    	    System.out.print(Arrays.toString(v));
    	    
    	    System.out.println();
	    }
	}
	
	public static void sort(Integer[] v) {
		sortR(v,0,v.length-1);
	}
	
	private static void sortR(Integer[] v, int i, int j) {
	    int pivote = v[i];
	    int izq = i;
	    int der = j;
	    int aux;
	    
	    while(izq < der) {										// mientras no se crucen izquierda y derecha
	    	while(v[izq] <= pivote && izq < der) izq++;		// mientras elementos de la izquierda sean menores que pivote movemos hacia la derecha
	    	while(v[der] > pivote) der--;					// mientras elementos de la derecha sean mayores que pivote movemos hacia la izquierda
	    	if(izq < der) {									// si no se han cruzado intercambiamos valores
	    		aux = v[izq];
	    		v[izq] = v [der];
	    		v[der] = aux;
	    	}
	    }
	    
	    v[i] = v[der];					// una vez que se crucen ponemos el pivote donde estaba el puntero der y viceversa
	    v[der] = pivote;
	    
	    if(i < der-1) {					// ordenamos la parte izquierda de donde hemos colocado el pivote
	    	sortR(v,i,der-1);
	    }
	    if(der+1 < j) {					// ordenamos la parte derecha de donde hemos colocado el pivote
	    	sortR(v,der+1,j);
	    }
	}
}

