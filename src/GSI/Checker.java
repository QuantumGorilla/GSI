package GSI;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 * @version 2.5
 * @since 4/2/2019
 */
public class Checker {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Función que sirve para comprobar que no se digite una cantidad de
     * registros menor que 0
     *
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
     *
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
     *
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
     * Función que calcula el promedio de todos los registros en el campo
     * númerico Ci, digitado por el usuario
     *
     * @param information
     * @param registers
     * @param checkAverageField
     */
    public static void fieldAverage(String[][] information, int registers, int checkAverageField) {
        Long ts = System.nanoTime();
        BigDecimal prom = BigDecimal.ZERO;
        for (String[] info : information) {
            prom = prom.add(new BigDecimal(info[checkAverageField]));
        }
        prom = prom.divide(BigDecimal.valueOf(information.length));
        System.out.println("El promedio de los campos " + checkAverageField + " es: " + prom);
        System.out.println("Tiempo de ejecución de cálculo de promedio: " + (System.nanoTime() - ts));
    }

    /**
     * Función que sirve para sacar el valor máximo de todos los registros en el
     * campo númerico Ci, digitado por el usuario
     *
     * @param information
     * @param registers
     * @param field
     */
    public static void maximumValue(String[][] information, int registers, int field) {
        Long ts = System.nanoTime();
        BigInteger maximum = BigInteger.ZERO;
        for (int i = 0; i < information.length; i++) {
            if (maximum.compareTo(new BigInteger(information[i][field])) == -1) {
                maximum = new BigInteger(information[i][field]);
            }
        }
        System.out.println("El valor máximo de los campos " + field + " es: " + maximum);
        System.out.println("Tiempo de ejecución de búsqueda del valor máximo: " + (System.nanoTime() - ts));
    }

    /**
     * Función que calcula el valor mínimo de todos los registros en el campo
     * númerico Ci, digitados por el usuario
     *
     * @param information
     * @param registers
     * @param field
     * @param pivot
     */
    public static void minimumValue(String[][] information, int registers, int field, String pivot) {
        Long ts = System.nanoTime();
        BigInteger minimum = new BigInteger(pivot).multiply(BigInteger.TEN);
        for (int i = 0; i < information.length; i++) {
            if (minimum.compareTo(new BigInteger(information[i][field])) == 1) {
                minimum = new BigInteger(information[i][field]);
            }
        }
        System.out.println("El valor mínimo de los campos " + field + " es: " + minimum);
        System.out.println("Tiempo de ejecución de búsqueda del valor mínimo: " + (System.nanoTime() - ts));
    }

    /**
     * Función que sirve para calcular la moda de todos los registros en el
     * campo Ci, digitado por el usuario
     *
     * @param information
     * @param field
     */
    public static void checkTrend(String[][] information, int field) {
        Long ts = System.nanoTime();
        int maxRepetition = 0, repetition;
        String trend = "";
        String temp = "";
        for (int i = 0; i < information.length; i++) {
            repetition = 0;
            for (int j = i; j < information.length; j++) {
                if (information[i][field].equals(information[j][field])) {
                    repetition++;
                    temp = information[i][field];
                }
            }
            if (repetition > maxRepetition) {
                trend = temp;
                maxRepetition = repetition;
            }
        }
        System.out.println("La moda es: " + trend);
        System.out.println("Tiempo de ejecución de búsqueda de la moda: " + (System.nanoTime() - ts));
    }

    /**
     * Función que hace un llamado recursivo para ordenar la matriz (QuickSort)
     *
     * @param information
     * @param left
     * @param right
     * @param field
     * @param fields
     * @return
     * @see sortKeys(String[][] everything, int left, int right, int field)
     */
    public static String[][] quickSort(String[][] information, int left, int right, int field, int fields) {
        if (left < right) {
            int partition = sortKeys(information, left, right, field);
            information = quickSort(information, left, partition - 1, field, fields);
            information = quickSort(information, partition + 1, right, field, fields);
        }
        return information;
    }

    /**
     * Función que hace las particiones respectivas del quicksort
     *
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
     * Función que sirve para ir mostrando como va el ordenamiento y el
     * resultado final
     *
     * @param information
     * @param fields
     */
    public static void showMeTheSort(String[][] information, int fields) {
        System.out.println("La estructura ordenada: ");
        for (String[] info : information) {
            for (int j = 0; j < fields; j++) {
                System.out.print("[" + info[j] + "]");
            }
            System.out.println("");
        }
        ArchiveManager.createOrganizedFile(information, information.length, fields);
    }
    /*
    public static void registersCounter(String[][] info){
        System.out.println("///////////////////////////////////////////");
        System.out.println("Tiene " + info.length + " registros");
        System.out.println("///////////////////////////////////////////");
    }
    
    public static void fieldsCounter(String[][] info){
        System.out.println("////////////////////////////////////////////");
        System.out.println("Tiene " + info[0].length + " campos");
        System.out.println("////////////////////////////////////////////");
    }*/
}
