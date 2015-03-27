package be.howest.nmct.bmi;

/**
 * Created by Joren on 13/02/2015.
 */
public class BMIInfo {

    //enumeration
    public enum Category
    {
        GROOT_ONDERGEWICHT(0,15),
        ERNSTIG_ONDERGEWICHT(15,16),
        ONDERGEWICHT(16,18.5f),
        NORMAAL(18.5f,25),
        OVERGEWICHT(25,30),
        MATIG_OVERGEWICHT(30,35),
        ERNSTIG_OVERGEWICHT(35,40),
        ZEER_GROOT_OVERGEWICHT(40,100);

        private float lowerBoundary, upperBoundary;

        //Constructor
        Category(float lowerBoundary, float upperBoundary) {
            this.lowerBoundary = lowerBoundary;
            this.upperBoundary = upperBoundary;
        }

        //Getters (GEEN setters, want bij een enum liggen de waarden op voorhand vast en moeten deze niet meer aangepast worden)
        public float getLowerBoundary() {
            return lowerBoundary;
        }

        public float getUpperBoundary() {
            return upperBoundary;
        }

        //andere methodes
        //**************

        //Kijken of een getal ergens in de grenzen zit.
        public boolean isInBoundary(float index){
            //ligt de doorgegeven index tussen de beneden en bovengrens?
            if(index >= getLowerBoundary() && index < getUpperBoundary())
                return true;

            return false;
        }

        //In een enumeration kan je zelfs static methodes steken
        public static  Category getCategory(float index)
        {
            for(Category category : Category.values())
            {
                if(category.isInBoundary(index))
                    return category;
            }
            return null;
        }

    }

    private float height = 1.70f;
    private int mass = 70;
    private float bmiIndex;
    private Category category = Category.NORMAAL;

    //Constructor
    public BMIInfo(float height, int mass) {
        this.height = height;
        this.mass = mass;
    }

    //Getters en Setters

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setBmiIndex(float bmiIndex) {
        this.bmiIndex = bmiIndex;
    }

    //Bereken BMI
    public void recalculate()
    {
        //BMI uitrekenen
        bmiIndex = mass / (height * height);

        //Categorie ophalen
        category = category.getCategory(bmiIndex);


    }

    //BMI en category ophalen

    public float getBmiIndex() {
        return bmiIndex;
    }

    public Category getCategory() {
        return category;
    }
}


