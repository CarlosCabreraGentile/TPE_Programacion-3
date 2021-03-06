package tpe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Creamos la clase abstracta para no repetir el mismo codigo ya que creamos las clases
 * BibliotecaList y BibliotecaArbol, y ambas tienen codigo repetido */
public abstract class Biblioteca {
	
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
	
	/**
	 * @param numero
	 * @return 
	 * Esta funci�n lee los archivos csv y los carga en un arraylist
	 */
	
	public static MySimpleLinkedList input(int numero) {
		String csvFile = "C:\\Tudai\\2do a�o\\Programacion 3\\2018\\tpe\\dataset" + numero + ".csv";
		String line = "";
		String cvsSplitBy = ",";
		MySimpleLinkedList libros = new MySimpleLinkedList();
		String[] aux;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				if (!items[3].equals("Generos")) {
					aux = separadorGeneros(items[3]);
					Libro a = new Libro(items[0], items[1], items[2], aux);
					
					libros.insertAlFinal(a);
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
	 * @param listaLibros
	 * Esta funci�n escribe un archivo csv 
	 */
	
	public static void output(MySimpleLinkedList listaLibros) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Tudai\\2do a�o\\Programacion 3\\2018\\tpe/salida.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("Lista de libros encontrada: \n");

			for (int i = 0; i < listaLibros.size(); i++) {
				String contenidoLinea1 = listaLibros.getNodo().getNombre();
				bw.write(contenidoLinea1);
				bw.newLine();
			}
			listaLibros.resetCursor();
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
