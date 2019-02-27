package GSI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class ArchiveManager {

    private static Scanner sc = new Scanner(System.in);
    private static File archive;
    private static String path;

    /**
     * Función que sirve para crear el archivo GSI a partir de una matriz no
     * organizada de i registros y j campos. El archivo es creado donde el
     * usuario desee
     *
     * @param information
     * @param registers
     * @param fields
     * @throws IOException
     */
    public static void createFile(String[][] information, int registers, int fields) throws IOException {
        System.out.print("Digite la dirección donde se creara el archivo no organizado: ");
        String path = sc.next();
        archive = new File(path, "GSI no organizado.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archive))) {
            for (int i = 0; i < registers; i++) {
                for (int j = 0; j < fields; j++) {
                    bw.write(information[i][j] + ",");
                }
                bw.newLine();
            }
        }
    }

    /**
     * Función que sirve para crear un archivo ya organizado de GSI con i
     * registros y j columnas. El archivo es creado donde el usuario desee
     *
     * @param information
     * @param registers
     * @param fields
     */
    public static void createOrganizedFile(String[][] information, int registers, int fields) {
        System.out.print("Digite la dirección donde se creara el archivo organizado: ");
        String ruta = sc.next();
        archive = new File(ruta, "GSI organizado.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archive))) {
            for (int i = 0; i < registers; i++) {
                for (int j = 0; j < fields; j++) {
                    bw.write(information[i][j] + ",");
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Función que pregunta la ubicación del archivo de donde se obtendran los
     * datos y los devuelve en una matriz
     * @param registers
     * @param fields
     * @return info
     * @throws IOException 
     */
    public static String[][] readFromArchive(int registers, int fields) throws IOException {
        System.out.print("Digite la ruta donde se buscará el archivo: ");
        path = sc.next();
        BufferedReader br = findFile(path);
        String line = br.readLine();
        String[][] info = new String[registers][fields];
        int counter = 0;
        while (line != null) {
            String[] information = line.split(",");
            for (int i = 0; i < information.length; i++) {
                info[counter][i] = information[i];
            }
            counter++;
            line = br.readLine();
        }
        return info;
    }

    /**
     * Función que obtiene el archivo de donde se extraeran los datos
     * unicamente usada por la función readFromArchive
     * @param path
     * @see readFromArchive
     * @return br
     */
    private static BufferedReader findFile(String path) {
        BufferedReader br = null;
        try {
            File file = new File(path, "GSI no organizado.txt");
            if (!file.exists()) {
                System.out.println("No existe el archivo");
            } else {
                path = path + "\\GSI no organizado.txt";
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return br;
    }
}