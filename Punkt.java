public class Punkt implements Comparable<Punkt>{
    private double pozycjaX;
    private double pozycjaY;

    //konstruktory
    Punkt(){
        pozycjaX = 0;
        pozycjaY = 0;
    }
    Punkt(double _pozycjaX, double _pozycjaY){
        pozycjaX = _pozycjaX;
        pozycjaY = _pozycjaY;
    }
    //settery
    public void setPozycjaX(double _pozycjaX){
        pozycjaX = _pozycjaX;
    }
    public  void setPozycjaY(double _pozycjaY){
        pozycjaY = _pozycjaY;
    }
    //gettery
    public double getPozycjaX(){
        return pozycjaX;
    }
    public double getPozycjaY(){
        return pozycjaY;
    }
    @Override
    public int compareTo(Punkt p){
        if (pozycjaX == p.pozycjaX && pozycjaY == p.pozycjaY){
            return 0;
        }
        else {
            return 1;
        }
    }
    @Override
    public  boolean equals(Object obiekt){
        if (obiekt instanceof Punkt){
            if (pozycjaX == ((Punkt) obiekt).pozycjaX){
                if (pozycjaY == ((Punkt) obiekt).pozycjaY){
                    return true;
                }
            }
        }
        return false;
    }
}
