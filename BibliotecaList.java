package tpe;
import java.util.ArrayList;

public class BibliotecaList extends Biblioteca{
	static int contador;
	static int contadorBusqueda;
	public static void main(String[] args) {
		int numeroDataSet = obtenerNumero();
		ArrayList<Indice> indice;
		
		MySimpleLinkedList archivoDataset = input(numeroDataSet);
		indice = crearIndice(archivoDataset);
		buscarGeneroEnLista(indice, "tecnología").print();
		System.out.println("Cantidad de consultas para la busqueda "+ contadorBusqueda);
		System.out.println("arrayList "+contador);
		print(indice);
		
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
			cargarListaIndices(retorno, arrGeneros, aux);/*este metodo agrega generos de un libro al array de indices*/		
		}
		libros.resetCursor();
		return retorno; 
	}  
	
	private static void print(ArrayList<Indice> libros) {
		for (int i = 0; i < libros.size(); i++) {
			System.out.print(libros.get(i).getGenero() + " ");
			System.out.println(" ");
		}
	}

	public static MySimpleLinkedList buscarGeneroEnLista(ArrayList<Indice> indice, String genero) {
		MySimpleLinkedList retorno = new MySimpleLinkedList();
		boolean exit = false;
		int i = 0;
		while ( i < indice.size()&&(!exit)) {
			contadorBusqueda++;
			if (genero.equals(indice.get(i).getGenero())) {
				retorno = indice.get(i).getLibros();
				exit=true;
			}
			i++;
		}
		return retorno;
	}
	
	/**
	 * @param retorno
	 * @param arrGeneros
	 * Este metodo carga a un arraylist los indices correspondientes
	 */ 
	
	private static void cargarListaIndices(ArrayList<Indice> retorno, String[] arrGeneros,Libro l) {/* carlos , maxi,silvina */
		Indice k;
		if (retorno.isEmpty()) {/*solo en el primer libro que llega*/
			k = new Indice(arrGeneros[0]);
			k.addLibro(l);
			retorno.add(k);
		}
		int tamAux = retorno.size();
		for (int i = 0; i < arrGeneros.length; i++) {
			int j = 0;
			boolean hasGenero = false;
			while ((j < tamAux) && (!hasGenero)) {
				contador++;
				if ((retorno.get(j).getGenero().equals(arrGeneros[i]))) {
					retorno.get(j).addLibro(l);
					hasGenero = true;
				}
				j++;
			}
			if (!hasGenero) {
				k = new Indice(arrGeneros[i]);
				k.addLibro(l);
				retorno.add(k);
			}
		}
	}

}