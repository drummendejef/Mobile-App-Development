package be.howest.nmct;

import java.util.Scanner;

/**
 * Created by Joren on 13/02/2015.
 */
public class RunBMI {
    public static void main(String[] args) {
        System.out.println("Demo BMI");

        //Alternatief: PrintWriter

        //Aanmaken scanner
        Scanner sc = new Scanner(System.in);

        //Vragen afdrukken
        System.out.println("Geef je hoogte in(in m, eg: 1,72)");
        float hoogte = sc.nextFloat();
        System.out.println("Geef je gewicht in");
        int gewicht = sc.nextInt();


        BMIInfo bmio = new BMIInfo(hoogte, gewicht);
        bmio.recalculate();

        System.out.println("Je BMI index is: " + bmio.getBmiIndex() + ", je zit in de categorie: " + bmio.getCategory());
    }
}
