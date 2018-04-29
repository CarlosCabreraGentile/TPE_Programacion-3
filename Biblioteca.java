import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
