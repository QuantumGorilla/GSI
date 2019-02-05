package GSI;

import java.util.Scanner;

/**
 *
 * @author Alejandro
 * @version 2.0
 * @since 4/2/2019
 */
public class GSI {

    public static void main(String[] args) {
        Long ts = System.nanoTime();

        RandomGenerator rg = new RandomGenerator();

        Scanner sc = new Scanner(System.in);

        System.out.print("Dígite el número de registros: ");
        int registers = sc.nextInt();
        Checker.checkRegisters(registers);

        System.out.print("Dígite la cantidad de campos: ");
        int fields = sc.nextInt();
        Checker.checkFields(fields);

        int[] lengthFields = rg.lengthFields(fields);

        String[][] information = rg.generateRegisters(registers, fields, lengthFields);

        System.out.print("Dígite el campo que desea consultar: ");
        int checkField = sc.nextInt();
        System.out.print("Dígite el registro exacto que desea consultar del campo " + checkField + "(Sin corchetes):");
        String lookInfo = sc.next();
        rg.showMeTheField(checkField, registers, fields, lookInfo);

        System.out.print("Dígite el campo del cual desea consultar el valor máximo (Los campos númericos son pares): ");
        checkField = sc.nextInt();
        Checker.maximumValue(information, lengthFields[checkField], checkField);

        System.out.print("Dígite el campo del cual desea consultar el valor mínimo (Los campos númericos son pares): ");
        checkField = sc.nextInt();
        Checker.minimumValue(information, lengthFields[checkField], checkField);

        System.out.print("Dígite el campo del cual desea consultar el promedio (Los campos númericos son pares): ");
        checkField = sc.nextInt();
        Checker.fieldAverage(information, lengthFields[checkField], checkField);

        System.out.print("Dígite el campo del cual desea consultar la moda: ");
        checkField = sc.nextInt();
        Checker.checkTrend(information, lengthFields[checkField], checkField);

        Long st = System.nanoTime();
        Checker.quickSort(information, 0, information.length - 1, 0, fields);
        System.out.println("Tiempo de ejecuciòn del ordenamiento: " + (System.nanoTime() - st));

        System.out.println("Tiempo de ejecución del programa para " + registers + " registros y " + fields
                + " campos: " + (System.nanoTime() - ts));
    }

}
