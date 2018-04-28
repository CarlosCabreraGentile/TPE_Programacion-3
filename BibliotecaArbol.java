import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BibliotecaArbol {

	public static void main(String[] args) {
		ArbolBinario indiceArbolBinario;
		int numeroDataSet = obtenerNumero();
		ArrayList<Libro> archivoDataset = input(numeroDataSet);
		indiceArbolBinario = crearArbolDeIndices(archivoDataset);
		/* ARRANCA EL TIMER */
//		Timer timer2 = new Timer();
//		timer2.start();
//		buscarGeneroEnArbol(indiceArbolBinario, "terror");
//		timer2.stop();
//
//		double elapsedTime2 = timer2.stop();
//		System.out.println("Tiempo arbol: " + elapsedTime2);
		/* TERMINA EL TIMER */
//		Indice a=new Indice("humor");
//		Indice b=new Indice("thriller");
//		Indice c=new Indice("investigacion");
////		Indice d=new Indice("infantil");
//		
//		indiceArbolBinario.insert(a);
//		indiceArbolBinario.insert(b);
//		indiceArbolBinario.insert(c);
//		indiceArbolBinario.insert(d);
		indiceArbolBinario.printPreOrder();
//		String str1  = "humor"; 
		String str2  = "thriller";
		String str3  = "investigacion";
//		System.out.println(str1.toString().compareTo(str2.toString()));
//		System.out.println(str2.compareTo(str1));
//		System.out.println(str2.compareTo(str3));
//		System.out.println(str3.compareTo(str2));
//		System.out.println(str2.compareTo(str2));
//		System.out.println(str1.compareTo(str3));
//	       String cadena1="moto";
//	        String cadena2="mota";
//	 
//	        System.out.println(cadena1.compareToIgnoreCase(cadena2));
	}public static int obtenerNumero() {
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

	public static ArbolBinario crearArbolDeIndices(ArrayList<Libro> libros) {
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
	public static ArrayList<Libro> buscarGeneroEnArbol(ArbolBinario indice, String genero) {
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
	 * @param numero
	 * @return 
	 * Esta función lee los archivos csv y los carga en un arraylist
	 */
	public static ArrayList<Libro> input(int numero) {
		String csvFile = "C:\\Users\\maxi\\Desktop\\tpe prog3\\dataset" + numero + ".csv";
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
