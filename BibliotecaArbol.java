
import java.util.ArrayList;

public class BibliotecaArbol extends Biblioteca{

	public static void main(String[] args) {
		ArbolBinario indiceArbolBinario;
		int numeroDataSet = obtenerNumero();
		ArrayList<Libro> archivoDataset = input(numeroDataSet);
		indiceArbolBinario = crearIndice(archivoDataset);
		/* ARRANCA EL TIMER */
//		Timer timer2 = new Timer();
//		timer2.start();
//		buscarGeneroEnArbol(indiceArbolBinario, "terror");
//		timer2.stop();
//
//		double elapsedTime2 = timer2.stop();
//		System.out.println("Tiempo arbol: " + elapsedTime2);
		/* TERMINA EL TIMER */
		System.out.println(indiceArbolBinario.getContador());
		indiceArbolBinario.printPreOrder();

	}
	public static ArbolBinario crearIndice(ArrayList<Libro> libros) {
		ArbolBinario retorno = new ArbolBinario();
		String[] arregloGeneros;
		for (int i = 0; i < libros.size(); i++) {
			arregloGeneros = libros.get(i).getGeneros();
			cargarArbolIndice(retorno, arregloGeneros);
			System.out.println(retorno.getContador());
			agregarLibroAlIndiceArbol(retorno, arregloGeneros, libros.get(i));
		}
		return retorno;
	}
	
	public ArrayList<Libro> buscarGenero(ArbolBinario indice, String genero) {
		ArrayList<Libro> retorno;
		Indice aux;
		aux = indice.hasElement(genero);
		retorno = aux.getLibros();
		return retorno;
	}
	
	private static void agregarLibroAlIndiceArbol(ArbolBinario retorno, String[] arrGeneros, Libro libro) {
		for (int i = 0; i < arrGeneros.length; i++) {
			retorno.hasElement(arrGeneros[i]).addLibro(libro);
		}
	} 
	/**
	 * @param retorno
	 * @param arrGeneros
	 * Este metodo carga a un arbol los indices correspondientes
	 */
	
	private static void cargarArbolIndice(ArbolBinario retorno, String[] arrGeneros) {
		Indice g;
		for (int i = 0; i < arrGeneros.length; i++) {/*1 millon libros cada uno tiene x generos + comparaciones*/
			if (retorno.hasElement(arrGeneros[i]) == null) {
				g = new Indice(arrGeneros[i]);
				retorno.insert(g);
			}
		}
	}	
	/**
	 * @param arrGeneros
	 * @return  
	 */
	
	private static ArbolBinario crearGeneroArbol(String[] arrGeneros) {
		ArbolBinario retorno = new ArbolBinario();
		
		for (int i = 0; i < arrGeneros.length; i++) {
			Indice g = new Indice(arrGeneros[i]);
			retorno.insert(g); 
			;
		}
		return retorno;
	}

}
