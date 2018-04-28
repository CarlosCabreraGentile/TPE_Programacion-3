package TPE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Biblioteca {

	public static void main(String[] args) {
		int numeroDataSet = obtenerNumero();
		ArrayList<Indice> indice;
		ArbolBinario indiceArbolBinario;
		ArrayList<Libro> archivoDataset = input(numeroDataSet);

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
		indiceArbolBinario = crearIndiceArbol(archivoDataset);
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

		/* ARRANCA EL TIMER */
		Timer timer2 = new Timer();
		timer2.start();
		buscarGeneroEnArbol(indiceArbolBinario, "terror");
		timer2.stop();

		double elapsedTime2 = timer2.stop();
		System.out.println("Tiempo arbol: " + elapsedTime2);
		/* TERMINA EL TIMER */

		// output(buscarGenero(indice, "terror"));

	}

	public static ArrayList<Indice> crearIndice(ArrayList<Libro> libros) {
		ArrayList<Indice> retorno = new ArrayList<Indice>();
		String[] arrGeneros;

		for (int i = 0; i < libros.size(); i++) {
			arrGeneros = libros.get(i).getGeneros();

			cargarListaIndices(retorno, arrGeneros);
			agregarLibroAlIndice(retorno, arrGeneros, libros.get(i));
		}
		return retorno; 
	}  
 
	public static ArbolBinario crearIndiceArbol(ArrayList<Libro> libros) {
		ArbolBinario retorno = new ArbolBinario();
		String[] arregloGeneros;
		
		for (int i = 0; i < libros.size(); i++) {
			arregloGeneros = libros.get(i).getGeneros();
			if (retorno.isEmpty()) {
				retorno = (crearGeneroArbol(arregloGeneros));
			} else {
				cargarArbolIndice(retorno, arregloGeneros);
			}
			agregarLibroAlIndiceArbol(retorno, arregloGeneros, libros.get(i));
		}
		return retorno;
	}
	
	public static int obtenerNumero() {
		boolean exit = false;
		int valor = 0;

		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

		do {
			try {
				System.out.println("Ingrese numero");
				valor = new Integer(entrada.readLine());
				if ((valor > 0) && (valor < 5)) {
					exit = true;
				} else {
					System.out.println("Numero incorrecto");
					exit = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("Numero invalido");
				exit = false;
			} catch (Exception exc) {
				System.out.println(exc);
				exit = false;
			}
		} while (!exit);
		return valor;
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

	public static ArrayList<Libro> buscarGeneroEnArbol(ArbolBinario indice, String genero) {
		ArrayList<Libro> retorno;
		Indice aux;
		aux = indice.hasElement(genero);
		retorno = aux.getLibros();
		return retorno;
	}
 
	private static void agregarLibroAlIndice(ArrayList<Indice> retorno, String[] arrGeneros, Libro libro) {
		for (int i = 0; i < arrGeneros.length; i++) {
			for (int j = 0; j < retorno.size(); j++) {
				if (retorno.get(j).getGenero().equals(arrGeneros[i])) {
					retorno.get(j).addLibro(libro);
				}
			}
		}
	}

	private static void agregarLibroAlIndiceArbol(ArbolBinario retorno, String[] arrGeneros, Libro libro) {
		Indice aux;
		for (int i = 0; i < arrGeneros.length; i++) {
			aux = retorno.hasElement(arrGeneros[i]);
			aux.addLibro(libro);
		}
	} 

	/**
	 * @param retorno
	 * @param arrGeneros
	 * Este metodo carga a un arraylist los indices correspondientes
	 */ 
	private static void cargarListaIndices(ArrayList<Indice> retorno, String[] arrGeneros) {
		int tamAux = retorno.size();
 
		for (int i = 0; i < arrGeneros.length; i++) {
			int j = 0;
			boolean hasGenero = false;
			while ((j < tamAux) && (!hasGenero)) {
				if ((retorno.get(j).getGenero().equals(arrGeneros[i]))) {
					hasGenero = true;
				}
				j++;
			}
			if (!hasGenero) {
				Indice g = new Indice(arrGeneros[i]);
				retorno.add(g);
			}
		}
	}

	/**
	 * @param retorno
	 * @param arrGeneros
	 * Este metodo carga a un arbol los indices correspondientes
	 */
	private static void cargarArbolIndice(ArbolBinario retorno, String[] arrGeneros) {
		for (int i = 0; i < arrGeneros.length; i++) {
			if (retorno.hasElement(arrGeneros[i]) == null) {
				Indice g = new Indice(arrGeneros[i]);
				retorno.insert(g);
			}
		}
	}

//	private static ArrayList<Indice> crearGenero(String[] arrGeneros) {
//		ArrayList<Indice> retorno = new ArrayList<Indice>();
//		for (int i = 0; i < arrGeneros.length; i++) {
//			Indice g = new Indice(arrGeneros[i]);
//			retorno.add(g);
//		}
//		return retorno;
//	}

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

	/**
	 * @param numero
	 * @return 
	 * Esta función lee los archivos csv y los carga en un arraylist
	 */
	public static ArrayList<Libro> input(int numero) {
		String csvFile = "F:\\TUDAI\\2do año\\Primer Cuatrimestre\\Programacion 3\\2018\\TPE_Programacion-3\\dataset" + numero + ".csv";
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Libro> libros = new ArrayList<Libro>();

		String[] aux;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				if (!items[3].equals("Generos")) {
					aux = separadorGeneros(items[3]);
					Libro a = new Libro(items[0], items[1], items[2], aux);
					libros.add(a);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libros; 
	}
	
	/**
	 * @param generos
	 * @return
	 * Herramienta que utiliza el reader para separar
	 * el string de generos
	 */
	private static String[] separadorGeneros(String generos) {
		String[] arrGeneros = generos.split(" ");
		return arrGeneros;
	}

	/**
	 * @param generosLibro
	 * Esta función escribe un archivo csv 
	 */
	public static void output(ArrayList<Libro> generosLibro) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe/salida.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("Lista de libros encontrada: \n");

			for (int i = 0; i < generosLibro.size(); i++) {
				String contenidoLinea1 = generosLibro.get(i).nombre;
				bw.write(contenidoLinea1);
				bw.newLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

}
