package be.howest.nmct.android.stopafstand;

import java.lang.Override;import java.lang.String; /**
 * Created by Joren on 13/02/2015.
 */

public class StopAfstandInfo {

    public enum WegType {
        WEGDEK_DROOG {
            public int getValue()
            {
                return 8;
            }
        },

        WEGDEK_NAT {
            public int getValue()
            {
                return 5;
            }
        };

        public abstract int getValue();


    }

    private int snelheid;
    private float reactietijd, stopafstand;
    private WegType wegtype;

    //Constructor
    public StopAfstandInfo(int snelheid, float reactietijd, WegType wegtype) {
        this.snelheid = snelheid;
        this.reactietijd = reactietijd;
        this.wegtype = wegtype;
    }

    //Getters en Setters
    public int getSnelheid() {
        return snelheid;
    }

    public void setSnelheid(int snelheid) {
        this.snelheid = snelheid;
    }

    public float getReactietijd() {
        return reactietijd;
    }

    public void setReactietijd(float reactietijd) {
        this.reactietijd = reactietijd;
    }

    //getStopAfstand berekenen
    public float getStopafstand() {

        stopafstand = (snelheid /3.6f) * reactietijd + ((snelheid /3.6f) * (snelheid /3.6f)) /(2 * wegtype.getValue());

        return stopafstand;
    }

    public void setStopafstand(float stopafstand) {
        this.stopafstand = stopafstand;
    }

    public WegType getWegtype() {
        return wegtype;
    }

    public void setWegtype(WegType wegtype) {
        this.wegtype = wegtype;
    }

    //Tostring
    @Override
    public String toString() {
        return "StopAfstandInfo{" +
                "snelheid=" + snelheid +
                ", reactietijd=" + reactietijd +
                ", stopafstand=" + stopafstand +
                ", wegtype=" + wegtype +
                '}';
    }
}
