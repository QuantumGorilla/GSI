package GSI;

import java.util.Scanner;

/**
 *
 * @author Alejandro
 * @version 2.0
 * @since 4/2/2019
 */
public class RandomGenerator {

    private Scanner sc = new Scanner(System.in);
    private String[][] information;

    /**
     * Función que genera números aleatorios de un campo con longitud K digitada
     * previamente por el usuario
     *
     * @param fieldLength
     * @return numericValue
     */
    public String generateRandomNumber(int fieldLength) {
        String numericValue = "";
        for (int i = 0; i < fieldLength; i++) {
            numericValue = numericValue + (int) (Math.random() * 9);
        }
        return numericValue;
    }

    /**
     * Función que genera Strings aleatorios de un campo con longitud K digitada
     * previamente por el usuario
     *
     * @param fieldLength
     * @return
     */
    public String generateRandomString(int fieldLength) {
        String abc = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvWwXxYyZz";
        String alphabeticValue = "";
        for (int i = 0; i < fieldLength; i++) {
            alphabeticValue = alphabeticValue + abc.charAt((int) (Math.random() * 54));
        }
        return alphabeticValue;
    }

    /**
     * Función que se encarga de preguntar al usuario la longitud de todos los
     * campos en todos los registros, donde cada campo puede tener o no la misma
     * longitud que cualquier otro
     *
     * @param fields
     * @return fieldLength
     */
    public int[] lengthFields(int fields) {
        int fieldLength[] = new int[fields];
        for (int i = 0; i < fields; i++) {
            System.out.print("Dígite la longitud del campo " + i + ": ");
            fieldLength[i] = sc.nextInt();
            if (fieldLength[i] < 1) {
                fieldLength[i] = Checker.checkLengthField(fieldLength[i]);
            }
        }
        return fieldLength;
    }

    /**
     * Función que crea registros, donde cada registro dpendiendo si es un
     * registro par o impar tendrá campo númerico o campo alfabetico
     *
     * @param registers
     * @param fields
     * @param lengthFields
     * @return information
     */
    public String[][] generateRegisters(int registers, int fields, int[] lengthFields) {
        Long ts = System.nanoTime();
        this.information = new String[registers][fields];
        for (int i = 0; i < registers; i++) {
            for (int j = 0; j < fields; j++) {
                if (j % 2 == 0) {
                    this.information[i][j] = generateRandomNumber(lengthFields[j]);
                } else {
                    this.information[i][j] = generateRandomString(lengthFields[j]);
                }
            }
        }
        showEverything(this.information, registers, fields);
        System.out.println("Tiempo de direccionamiento a la estructura de datos: " + (System.nanoTime() - ts));
        return information;
    }

    /**
     * Función que muestra el resultado final después de haber sido llenada
     * completamente la matriz con la información correspondiente
     *
     * @param information
     * @param registers
     * @param fields
     */
    public void showEverything(String[][] information, int registers, int fields) {
        for (int i = 0; i < registers; i++) {
            for (int j = 0; j < fields; j++) {
                System.out.print("[" + information[i][j] + "]");
            }
            System.out.println("");
        }
    }

    /**
     * Función que mostrará el campo especifico que quiera consultar el usuario
     * (Solo se consultará una vez por ejecución)
     *
     * @param checkField
     * @param registers
     * @param fields
     */
    public void showMeTheField(int checkField, int registers, int fields) {
        for (int i = 0; i < registers; i++) {
            for (int j = 0; j < fields; j++) {
                if (checkField == j) {
                    System.out.print("[" + this.information[i][j] + "]");
                }
            }
            System.out.println("");
        }
    }

    public void findTheRegister(String lookInfo, int checkField) {
        Long ts = System.nanoTime();
        for (int i = 0; i < this.information.length; i++) {
            if (lookInfo.equals(information[i][checkField])) {
                System.out.println("El registro: " + lookInfo + " sí existe");
            } 
        }
        System.out.println("Tiempo de busqueda de los campos Ci: " + (System.nanoTime() - ts));
    }
}
