package tpe;
public class BibliotecaArbol extends Biblioteca{

	public static void main(String[] args) {
		ArbolBinario indiceArbolBinario;
		int numeroDataSet = obtenerNumero();
		MySimpleLinkedList archivoDataset = input(numeroDataSet);
		indiceArbolBinario = crearIndice(archivoDataset);

		buscarGenero(indiceArbolBinario, "tecnología").print();
		System.out.println(indiceArbolBinario.getContador());
		System.out.println("Cantidad de comparaciones "+indiceArbolBinario.getCantidadDeComparaciones());
		System.out.println("Cantidad de consultas para la busqueda "+indiceArbolBinario.getCantidadDeBusqueda());
		indiceArbolBinario.printPreOrder();

	}
	
	public static ArbolBinario crearIndice(MySimpleLinkedList libros) {
		ArbolBinario retorno = new ArbolBinario();
		String[] arregloGeneros;
		for (int i = 0; i < libros.size(); i++) {
			Libro aux = libros.getNodo();
			arregloGeneros = aux.getGeneros();
			ArmarArbolDeIndices(retorno, arregloGeneros,aux);
		}
		retorno.setCantidadDeComparaciones();
		libros.resetCursor();
		return retorno;
	}
	
	public static MySimpleLinkedList buscarGenero(ArbolBinario indice, String genero) {
		MySimpleLinkedList retorno;
		Indice aux;
		aux = indice.hasElement(genero);
		indice.setCantidadDeBusqueda();
	
		retorno = aux.getLibros();
		return retorno;
	}
	
	/**
	 * @param retorno
	 * @param arrGeneros
	 * Este metodo carga a un arbol los indices correspondientes
	 */
	
	private static void ArmarArbolDeIndices(ArbolBinario retorno, String[] arrGeneros,Libro l) {
		Indice g;
		for (int i = 0; i < arrGeneros.length; i++) {/*1 millon libros cada uno tiene x generos + comparaciones*/
			Indice k =retorno.hasElement(arrGeneros[i]);
			if ( k == null) {
				g = new Indice(arrGeneros[i]);
				g.addLibro(l);
				retorno.insert(g);
			}else {
				k.addLibro(l);
			}
		}
	}	


}
