package GSI;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 * @version 2.0
 * @since 4/2/2019
 */
public class Checker {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Función que sirve para comprobar que no se digite una cantidad de registros
     * menor que 0
     * @param registers
     * @return registers > 0
     */
    public static int checkRegisters(int registers) {
        while (registers < 1) {
            System.out.print("Dígite una cantidad de registros mayor que 0: ");
            registers = sc.nextInt();
        }
        return registers;
    }

    /**
     * Función que sirve para comprobar que no se digite una cantidad menor que
     * 0 en los campos
     * @param fields
     * @return fields > 0
     */
    public static int checkFields(int fields) {
        while (fields < 1) {
            System.out.print("Dígite una cantidad de campos mayor que 0: ");
            fields = sc.nextInt();
        }
        return fields;
    }

    /**
     * Función que sirve para comprobar que no se digite una cantidad menor que 
     * 0 en la longitud de los campos
     * @param length
     * @return length > 0
     */
    public static int checkLengthField(int length) {
        while (length < 1) {
            System.out.println("Dígite una longitud mayor que 0");
            length = sc.nextInt();
        }
        return length;
    }

    /**
     * Función que calcula el promedio de todos los registros en el campo númerico Ci, digitado
     * por el usuario
     * @param information
     * @param fieldLength
     * @param checkAverageField 
     */
    public static void fieldAverage(String[][] information, int fieldLength, int checkAverageField) {
        Long ts = System.nanoTime();
        long prom = 0;
        for (int i = 0; i < information.length; i++) {
            for (int j = 0; j < fieldLength; j++) {
                prom += Long.valueOf(String.valueOf(information[i][checkAverageField].charAt(j)));
            }
        }
        prom /= information.length;
        System.out.println("El promedio de los campos " + checkAverageField + " es: " + prom);
        System.out.println("Tiempo de ejecución de cálculo de promedio: " + (System.nanoTime() - ts));
    }

    /**
     * Función que sirve para sacar el valor máximo de todos los registros en el campo númerico Ci, digitado
     * por el usuario
     * @param information
     * @param fieldLength
     * @param field 
     */
    public static void maximumValue(String[][] information, int fieldLength, int field) {
        Long ts = System.nanoTime();
        long maximum = 0;
        for (int i = 0; i < information.length; i++) {
            for (int j = 0; j < fieldLength; j++) {
                if (maximum < Long.parseLong(String.valueOf(information[i][field].charAt(j)))) {
                    maximum = Long.parseLong(String.valueOf(information[i][field].charAt(j)));
                }
            }
        }
        System.out.println("El valor máximo de los campos " + field + " es: " + maximum);
        System.out.println("Tiempo de ejecución de búsqueda del valor máximo: " + (System.nanoTime() - ts));
    }

    /**
     * Función que calcula el valor mínimo de todos los registros en el campo númerico Ci, digitados por el 
     * usuario
     * @param information
     * @param fieldLength
     * @param field 
     */
    public static void minimumValue(String[][] information, int fieldLength, int field) {
        Long ts = System.nanoTime();
        long minimum = 10000;
        for (int i = 0; i < information.length; i++) {
            for (int j = 0; j < fieldLength; j++) {
                if (minimum > Long.parseLong(String.valueOf(information[i][field].charAt(j)))) {
                    minimum = Long.parseLong(String.valueOf(information[i][field].charAt(j)));
                }
            }
        }
        System.out.println("El valor mínimo de los campos " + field + " es: " + minimum);
        System.out.println("Tiempo de ejecución de búsqueda del valor mínimo: " + (System.nanoTime() - ts));
    }

    /**
     * Función que sirve para calcular la moda de todos los registros en el campo
     * Ci, digitado por el usuario
     * @param information
     * @param fieldLength
     * @param field 
     */
    public static void checkTrend(String[][] information, int fieldLength, int field) {
        Long ts = System.nanoTime();
        int maxRepetition = 0, repetition = 0;
        String trend = "";
        char temp = 0;
        for (int i = 0; i < information.length; i++) {
            repetition = 0;
            for (int j = 0; j < fieldLength; j++) {
                if (information[i][field].charAt(j) == information[i][field].charAt(j)) {
                    repetition++;
                    temp = information[i][field].charAt(j);
                }
            }
            if (repetition > maxRepetition) {
                trend = String.valueOf(temp);
                maxRepetition = repetition;
            }
        }
        System.out.println("La moda es: " + trend);
        System.out.println("Tiempo de ejecución de búsqueda de la moda: " + (System.nanoTime() - ts));
    }

    /**
     * Función que hace un llamado recursivo para ordenar la matriz (QuickSort)
     * @param information
     * @param left
     * @param right
     * @param field 
     * @see sortKeys(String[][] everything, int left, int right, int field)
     */
    public static void quickSort(String[][] information, int left, int right, int field) {
        if (left < right) {
            int partition = sortKeys(information, left, right, field);
            quickSort(information, left, partition - 1, field);
            quickSort(information, partition + 1, right, field);
        }
        showMeTheSort(information, right);
    }

    /**
     * Función que hace las particiones respectivas del quicksort
     * @param information
     * @param left
     * @param right
     * @param field
     * @return i + 1
     */
    private static int sortKeys(String[][] information, int left, int right, int field) {
        String pivot = information[right][field];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (new BigInteger(information[j][field]).compareTo(new BigInteger(pivot)) <= 0) {
                i++;
                String[] temp = information[i];
                information[i] = information[j];
                information[j] = temp;
            }
        }
        String[] temp = information[i + 1];
        information[i + 1] = information[right];
        information[right] = temp;
        return i + 1;
    }

    /**
     * Función que sirve para ir mostrando como va el ordenamiento y el resultado final
     * @param information
     * @param fields 
     */
    private static void showMeTheSort(String[][] information, int fields) {
        System.out.println("La estructura ordenada: ");
        for (int i = 0; i < information.length; i++) {
            for (int j = 0; j < fields; j++) {
                System.out.print("[" + information[i][j] + "]");
            }
            System.out.println("");
        }
    }
}
