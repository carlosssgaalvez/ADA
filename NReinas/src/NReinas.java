import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class NReinas {
    public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    int n = Integer.parseInt(sc.next());
    	    
    	    List<Integer> k = nReinas(n);
    	    
    	    System.out.println(k.toString());
	    }
	}
	
	public static List<Integer> nReinas(int n){
		List<Integer> T = new ArrayList<>();
		
		if (nReinas(T,n)) return T;  // si encontramos solución devolvemos el array
		return null;
	}
	
	public static boolean nReinas(List<Integer> T, int n){
	    if (T.size() == n) return true;  // en el momento que ocurra habremos colocado todas las reinas
	    else{
	        for (int k = 0 ; k < n; k++){ // vemos en que posicion estará la reina a colocar
	            if (valida (T, k)){
	                T.add(k);
	                if (nReinas(T,n)) return true;
	                T.remove(T.size()-1);
	            }
	        }   
	        return false;
	    }
	}
	
	
	public static boolean valida (List<Integer> T, int k){
	    boolean ok = !T.contains(k); // esa posición no está contenida (sino se matarían al estar en la misma fila)
	    if (ok){
	        int i = 0;
	        while (i < T.size() && ok){		// comprobamos que las reinas ya puestas no maten nuestra propuesta en diagonal
	            ok = T.get(i)-i != k - T.size();				// ver si mata en diagonal que va hacia abajo
	            if (ok) ok = T.get(i) + i != k + T.size(); 		// ver si mata en diagonal que va hacia arriba
	            
	            i++;
	        }
	    }
	    return ok;
	}
}//end class 