
public class Analizador {
	
	public static String masCercano(double ratio) {
			if (ratio == -1) {
				return "ERROR";
			} else if (ratio < 1.0005) {                     // aprox 1.0 *DIFERENCIAR* 		
					return "1";
			} else if (ratio >= 1.005 && ratio < 1.5){
					return "LOGN";												
			} else if (1.5 <= ratio && ratio < 3.0) { 		// aprox 2.0 *DIFERENCIAR*
				ratio = calculaRatioPequeño();
				if (ratio < 2.5) {
					return "N";
				} else {
					return "NLOGN";
				}			
			} else if (3 <= ratio && ratio < 6.0) { 		// aprox 4.0
				return "N2";
			} else if (6 <= ratio && ratio < 10.0) { 		// aprox 8.0
				return "N3";
			} else { 										// aprox inf. *DIFERENCIAR*
				ratio = calculaRatioPequeño();
				if (ratio < 2000) {
					return "2N";
				} else {									// si peta o hacer overflow es NF
					return "NF";
				}
			}
	}
	
	public static double calculaRatioPequeño() {
		Temporizador t = new Temporizador(2);
		double tiempo2N, tiempoN;
		t.iniciar();
		Algoritmo.f(20);
		t.parar();
		tiempo2N = t.tiempoPasado();
		t.reiniciar();
		t.iniciar();
		Algoritmo.f(10);
		t.parar();
		tiempoN = t.tiempoPasado();
		double ratio = tiempo2N/tiempoN;
		return ratio;
	}
	
	public static void main(String arg[]) {
		long [] tamañoEntrada = {1,2,3,4,5,6,7,8,9}; 		// cuando el algoritmo los compile todos los multiplicaremos por 10
		double tiempoN = 0, tiempo2N = 0, ratio = -1;
		int indice = 0;
		Temporizador t = new Temporizador(2); 				// en nanosegudos para un algoritmo en concreto
		Temporizador ejecucion = new Temporizador(1); 		// con milisegundos
		ejecucion.iniciar(); 								//temporizador de 7 segundos mientras ejecutamos el algoritmo
		try{
			while (ejecucion.tiempoPasado() < 3500) { 		// 7 segundos ideal, pero si lo pongo peta			
				for(int i=0; i<5; i++) { 					// iteracion de 5 veces siempre
					t.reiniciar(); 							//ALGORITMO N
					t.iniciar();
					Algoritmo.f(tamañoEntrada[indice]);
					t.parar();
					if(tiempoN == 0) { 						// obtenemos el tiempo si el primero de la serie
						tiempoN = t.tiempoPasado();
					} else if(t.tiempoPasado() < tiempoN) { // si el tiempoPasado es menor al que teniamos nos quedamos con ese
						tiempoN = t.tiempoPasado();
					}
					t.reiniciar();							//ALGORITMO 2N
					t.iniciar();
					long n = tamañoEntrada[indice];
					Algoritmo.f(2*n);    					// EL TAMAÑO DEBE SER EL DOBLE PERO DA ERROR
					t.parar();
					if(tiempo2N == 0) { 
						tiempo2N = t.tiempoPasado();
					} else if(t.tiempoPasado() < tiempo2N) { 
						tiempo2N = t.tiempoPasado();
					}
				}
			
				ratio = tiempo2N/tiempoN;
				tiempoN = 0; 								// preparamos datos para otras 5 iteraciones
				tiempo2N = 0;
				indice ++;
				if (indice >= tamañoEntrada.length) { 		// si recorremos entero el array aumentos los valores de cada uno
					indice = 0;								// recorreremos el nuevo array de nuevo
					for (int j = 0; j < tamañoEntrada.length; j++) {
						tamañoEntrada[j] = tamañoEntrada[j] * 10;
					}
				}
			}
		} catch (OutOfMemoryError e) {
			// si eclipse se queda sin memoria capturamos excepcion y evaluamos con lo que llevemos hata el momento
		}
		System.out.println(masCercano(ratio));
		
	}

}
