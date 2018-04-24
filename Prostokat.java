import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Prostokat implements Figura, Comparable<Figura> {
    protected Punkt punkt;
    protected String nazwa;
    protected double bokA;    //Długość dolnego/górnego boku
    protected double bokB;    //Długość lewego/prawego boku
    //konstruktory
    Prostokat(){
        punkt = new Punkt();
        bokA = 0;
        bokB = 0;
        nazwa = "|pr|"+bokA+"|"+bokB+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    Prostokat(double _bokA, double _bokB){
        punkt = new Punkt();
        bokA = _bokA;
        bokB = _bokB;
        nazwa = "|pr|"+bokA+"|"+bokB+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    Prostokat(double _bokA, double _bokB, double _pozycjaX, double _pozycjaY){
        punkt = new Punkt(_pozycjaX, _pozycjaY);
        bokA = _bokA;
        bokB = _bokB;
        nazwa = "|pr|"+bokA+"|"+bokB+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    //metody
    @Override
    public double pole(){
        return bokA*bokB;
    }
    @Override
    public double obwod(){
        return (2*bokA)+(2*bokB);
    }
    @Override
    public Punkt getPunkt(){
        return punkt;
    }
    @Override
    public double getBok(){
        return bokA;
    }
    @Override
    public String getNazwa(){
        return nazwa;
    }
    @Override
    public boolean przecina(Object f){
        if (f instanceof Prostokat){
            // skrajne przypadki
            if (punkt.getPozycjaX() == ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() == ((Prostokat) f).punkt.getPozycjaY()) {
                return true;
            }
            // 1 ćwiartka
            if (punkt.getPozycjaX() >= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Prostokat) f).punkt.getPozycjaY()){
                Punkt prawyGornyPunkt = new Punkt(((Prostokat) f).punkt.getPozycjaX() + ((Prostokat) f).bokA, ((Prostokat) f).punkt.getPozycjaY() + ((Prostokat) f).bokB);
                if (punkt.getPozycjaX() < prawyGornyPunkt.getPozycjaX() && punkt.getPozycjaY() < prawyGornyPunkt.getPozycjaY()){
                    return true;
                }
            }
            // 2 ćwiartka
            if (punkt.getPozycjaX() <= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Prostokat) f).punkt.getPozycjaY()){
                Punkt lewyGornyPunkt = new Punkt(((Prostokat) f).punkt.getPozycjaX(), ((Prostokat) f).punkt.getPozycjaY() + ((Prostokat) f).bokB);
                if (punkt.getPozycjaX() + bokA > lewyGornyPunkt.getPozycjaX() && punkt.getPozycjaY() <lewyGornyPunkt.getPozycjaY()){
                    return true;
                }
            }
            // 3 ćwiartka
            if (punkt.getPozycjaX() <= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Prostokat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX()+bokA > ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY()+bokB > ((Prostokat) f).punkt.getPozycjaY()){
                    return true;
                }
            }
            // 4 ćwiartka
            if (punkt.getPozycjaX() >= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Prostokat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() > ((Prostokat) f).punkt.getPozycjaX()+((Prostokat) f).bokA && punkt.getPozycjaY() + bokB > ((Prostokat) f).punkt.getPozycjaY()){
                    return true;
                }
            }
        }
        if (f instanceof Kwadrat){
            // skrajne przypadki
            if (punkt.getPozycjaX() == ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() == ((Kwadrat) f).punkt.getPozycjaY()) {
                return true;
            }
            // 1 ćwiartka
            if (punkt.getPozycjaX() >= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Kwadrat) f).punkt.getPozycjaY()){
                Punkt prawyGornyPunkt = new Punkt(((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok, ((Kwadrat) f).punkt.getPozycjaY() + ((Kwadrat) f).bok);
                if (punkt.getPozycjaX() < prawyGornyPunkt.getPozycjaX() && punkt.getPozycjaY() < prawyGornyPunkt.getPozycjaY()){
                    return true;
                }
            }
            // 2 ćwiartka
            if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Kwadrat) f).punkt.getPozycjaY()){
                Punkt lewyGornyPunkt = new Punkt(((Kwadrat) f).punkt.getPozycjaX(), ((Kwadrat) f).punkt.getPozycjaY() + ((Kwadrat) f).bok);
                if (punkt.getPozycjaX() + bokA > lewyGornyPunkt.getPozycjaX() && punkt.getPozycjaY() <lewyGornyPunkt.getPozycjaY()){
                    return true;
                }
            }
            // 3 ćwiartka
            if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX()+bokA > ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY()+bokB > ((Kwadrat) f).punkt.getPozycjaY()){
                    return true;
                }
            }
            // 4 ćwiartka
            if (punkt.getPozycjaX() >= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() > ((Kwadrat) f).punkt.getPozycjaX()+((Kwadrat) f).bok && punkt.getPozycjaY() + bokB > ((Kwadrat) f).punkt.getPozycjaY()){
                    return true;
                }
            }
        }

       // if (f instanceof Kolo){
            // zaimplementowane w kole
        //}
        return false;
    }
    @Override
    public String toString(){
        String napis = "";
        napis += "nazwa = "+nazwa+"\n";
        napis += "obwod = "+obwod()+"  ";
        napis += "pole = "+pole()+"  ";
        napis += "wspolrzedne punktu = "+"("+getPunkt().getPozycjaX()+", "+getPunkt().getPozycjaY()+")"+"\n";
        napis += "rodzaj figury = "+"prostokat"+"\n";
        return napis;
    }
    @Override
    public boolean equals(Object obiekt){
        if (obiekt instanceof Prostokat){
            if (nazwa.equals(((Prostokat) obiekt).nazwa))
            {
                if (punkt == ((Prostokat) obiekt).punkt){
                    if (bokA == ((Prostokat) obiekt).bokA){
                        if (bokB == ((Prostokat) obiekt).bokB){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    public int compareTo(Figura _figura){
        if (pole() > _figura.pole()){
            return 1;
        }
        else if (pole() < _figura.pole()){
            return -1;
        }
        else if (pole() == _figura.pole()){
            if(_figura.getClass().toString().equals("class Kwadrat")){
                return 1;
            }
            else if(_figura.getClass().toString().equals("class Kolo")){
                return 1;
            }
            else if(_figura.getClass().toString().equals("class Prostokat")){
                //Sprawdzanie czy prostokąt z bokami 2,4 jest różny od prostokąta z bokami 4,2
                if (_figura.getBok() == bokA){
                    return punkt.compareTo(_figura.getPunkt());
                }
                else{
                    return 1;
                }
            }
        }
        return 0;
    }
}
