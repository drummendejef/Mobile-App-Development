package be.howest.nmct;

import java.util.Scanner;

/**
 * Created by Joren on 13/02/2015.
 */
public class TestStopAfstand {

    //shortcut voor main te maken: psvm en dan tab
    public static void main(String[] args) {
        //new StopAfstandInfo(89,1, StopAfstandInfo.WegType.WEGDEK_NAT);


        //Aanmaken scanner
        Scanner sc = new Scanner(System.in);
        System.out.println("Geef snelheid op (in km/u):");
        int snelheid = sc.nextInt();
        System.out.println("Geef reactietijd op in sec, bv: 1,2)");
        float reactietijd = sc.nextFloat();
        System.out.println("Welk wegtype selecteer je: NAT of DROOG");
        String wegtypetekst = sc.next();

        //Wegdek alvast op droog zetten, zo meteen controleren of dat wel juist is.
        StopAfstandInfo.WegType wegtype = StopAfstandInfo.WegType.WEGDEK_DROOG;

        //Stopafstand berekenen
        StopAfstandInfo afstand = new StopAfstandInfo(snelheid,reactietijd,wegtype);

        //Kijken ofdat het wegdek toch nat is
        if(wegtypetekst.equals("NAT"))
            afstand.setWegtype(StopAfstandInfo.WegType.WEGDEK_NAT);


        //Stopafstand afdrukken
        System.out.println("De stopafstand is: " + afstand.getStopafstand());

    }
}
