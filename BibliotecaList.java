
import java.util.ArrayList;
import java.util.LinkedList;

public class BibliotecaList extends Biblioteca{
	static int contador;
	public static void main(String[] args) {
		int numeroDataSet = obtenerNumero();
		ArrayList<Indice> indice;
		
		MySimpleLinkedList archivoDataset = input(numeroDataSet);

//		 /*ARRANCA EL TIMER*/
//		 Timer timer = new Timer();
//		 timer.start(); 
		indice = crearIndice(archivoDataset);
//		 timer.stop();
//		
//		 double elapsedTime = timer.stop();
//		 System.out.println("Tiempo ArrayList: " + elapsedTime);
//		 /*TERMINA EL TIMER*/
//		 
//		 /*ARRANCA EL TIMER*/
//		 Timer timer2 = new Timer();
//		 timer2.start();
		
//		 timer2.stop();
//		
//		 double elapsedTime2 = timer2.stop();
//		 System.out.println("Tiempo arbol: " + elapsedTime2);
//		 /*TERMINA EL TIMER*/

		/* ARRANCA EL TIMER */
		Timer timer = new Timer();
		timer.start();
		buscarGeneroEnLista(indice, "terror");
		timer.stop();

		double elapsedTime = timer.stop();
		System.out.println("Tiempo ArrayList: " + elapsedTime);
		/* TERMINA EL TIMER */
		// output(buscarGenero(indice, "terror"));

	}
	
	/**
	 * @param libros
	 * @return
	 * Este metodo genera el conjunto de indices que va a contener el ArryLIst
	 */
	public static ArrayList<Indice> crearIndice(MySimpleLinkedList libros) {
		ArrayList<Indice> retorno = new ArrayList<Indice>();
		String[] arrGeneros;
		for (int i = 0; i < libros.size(); i++) {
			Libro aux = libros.getNodo();
			arrGeneros= aux.getGeneros();
			cargarListaIndices(retorno, arrGeneros);/*este metodo agrega generos de un libro al array de indices*/
			System.out.println("arrayList "+contador+" "+aux.getNombre());
			agregarLibroAlIndice(retorno, arrGeneros, aux);
		}
		libros.resetCursor();
		return retorno; 
	}  
	

	private static void printSearch(ArrayList<Libro> libros) {
		for (int i = 0; i < libros.size(); i++) {
			System.out.print(libros.get(i).getNombre() + " ");
			System.out.println(" ");
		}
	}

	public static ArrayList<Libro> buscarGeneroEnLista(ArrayList<Indice> indice, String genero) {
		ArrayList<Libro> retorno = new ArrayList<Libro>();
		for (int i = 0; i < indice.size(); i++) {
			if (genero.equals(indice.get(i).getGenero())) {
				retorno = indice.get(i).getLibros();
			}
		}
		return retorno;
	}
	
	/**
	 * @param retorno
	 * @param arrGeneros
	 * @param libro
	 * agrega a cada genero su respectivo libro
	 */
	
	private static void agregarLibroAlIndice(ArrayList<Indice> retorno, String[] arrGeneros, Libro libro) {
		boolean exit = false;
		for (int i = 0; i < arrGeneros.length; i++) {
			int j = 0;
			while(j < retorno.size()&&(!exit)) {
				
				if (retorno.get(j).getGenero().equals(arrGeneros[i])) {
					retorno.get(j).addLibro(libro);
					exit = true;
				}
				j++;
			}
		}
	}
	
	/**
	 * @param retorno
	 * @param arrGeneros
	 * Este metodo carga a un arraylist los indices correspondientes
	 */ 
	
	private static void cargarListaIndices(ArrayList<Indice> retorno, String[] arrGeneros) {/* carlos , maxi,silvina */
		Indice k;
		if (retorno.isEmpty()) {/*solo en el primer libro que llega*/
			k = new Indice(arrGeneros[0]);
			retorno.add(k);
		}
		int tamAux = retorno.size();
		for (int i = 0; i < arrGeneros.length; i++) {
			int j = 0;
			boolean hasGenero = false;
			while ((j < tamAux) && (!hasGenero)) {
				contador++;
				if ((retorno.get(j).getGenero().equals(arrGeneros[i]))) {
					hasGenero = true;
				}
				j++;
			}
			if (!hasGenero) {
				k = new Indice(arrGeneros[i]);
				retorno.add(k);
			}
		}
	}

}