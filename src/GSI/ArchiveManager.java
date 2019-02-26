package GSI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class ArchiveManager {

    private static Scanner sc = new Scanner(System.in);
    private static File archive;

    /**
     * Función que sirve para crear el archivo GSI a partir de una matriz no
     * organizada de i registros y j campos. El archivo es creado donde el 
     * usuario desee
     * @param information
     * @param registers
     * @param fields
     * @throws IOException 
     */
    public static void createFile(String[][] information, int registers, int fields) throws IOException {
        System.out.print("Digite la dirección donde se creara el archivo no organizado: ");
        String ruta = sc.next();
        archive = new File(ruta, "GSI no organizado.txt");
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
     * Función que sirve para crear un archivo ya organizado de GSI
     * con i registros y j columnas. El archivo es creado donde el usuario
     * desee
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
}
