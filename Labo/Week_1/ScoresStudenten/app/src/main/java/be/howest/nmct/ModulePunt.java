package be.howest.nmct;

/**
 * Created by Joren on 13/02/2015.
 */
public class ModulePunt {
    private String moduleNaam;
    private int aantalStudiePunten;
    private double score;

    //Constructor

    public ModulePunt(String moduleNaam, int aantalStudiePunten, double score) {
        this.moduleNaam = moduleNaam;
        this.aantalStudiePunten = aantalStudiePunten;
        this.score = score;
    }

    public ModulePunt(double score, String moduleNaam) {
        this.score = score;
        this.moduleNaam = moduleNaam;
        this.aantalStudiePunten = 6;//Default, was een idee van de leerkracht.
    }
    //Getters en Setters

    //tostring
}
