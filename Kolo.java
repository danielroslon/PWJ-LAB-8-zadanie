public class Kolo implements Figura, Comparable<Figura> {
    Punkt punkt;
    private String nazwa;
    private double bok;
    private final double PI = 3.14159265359;
    //konstruktory
    Kolo(){
        punkt = new Punkt(0, 0);
        bok = 0;
        nazwa = "|ko|"+bok+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    Kolo(double _bok, double _pozycjaX, double _pozycjaY){
        punkt = new Punkt(_pozycjaX, _pozycjaY);
        bok = _bok;
        nazwa = "|ko|"+bok+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    //metody
    @Override
    public double pole(){
        return PI * bok * bok;
    }
    @Override
    public double obwod(){
        return 2 * PI * bok;
    }
    @Override
    public Punkt getPunkt(){
        return punkt;
    }
    @Override
    public double getBok(){
        return bok;
    }
    @Override
    public String getNazwa(){
        return nazwa;
    }
    @Override
    public boolean przecina(Object f){
        // Przecinanie z kołem
        if (f instanceof Kolo){
            double odlegloscMiedzyPunktami = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, ((Kolo) f).punkt);
            if (odlegloscMiedzyPunktami < bok + ((Kolo) f).bok){
                return true;
            }
        }
        //Przecinanie z kwadratem
        if (f instanceof  Kwadrat){
            double odlegloscMiedzyPunktami = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, ((Kwadrat) f).punkt);
            double odlegloscMiedzyX = Math.abs(punkt.getPozycjaX() - ((Kwadrat) f).punkt.getPozycjaX());
            double odlegloscMiedzyY = Math.abs(punkt.getPozycjaY() - ((Kwadrat) f).punkt.getPozycjaY());
            // skrajne przypadki
            if (punkt.getPozycjaX() == ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() == ((Kwadrat) f).punkt.getPozycjaY()) {
                return true;
            }
            // 1 ćwiartka
            if (punkt.getPozycjaX() >= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok){
                    if (punkt.getPozycjaY() - bok < ((Kwadrat) f).punkt.getPozycjaY() + ((Kwadrat) f).bok){
                        return true;
                    }
                }
                else if (punkt.getPozycjaY() <= ((Kwadrat) f).getPunkt().getPozycjaY() + ((Kwadrat) f).bok){
                    if (punkt.getPozycjaX() - bok < ((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok){
                        return true;
                    }
                }
                else {
                    double odlegloscOdPrawegoGornegoPunktu;
                    odlegloscOdPrawegoGornegoPunktu = Rysunek.obliczanieOdleglosciMiedzyPunktami(new Punkt(((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok, ((Kwadrat) f).punkt.getPozycjaY()+ ((Kwadrat) f).bok),punkt);
                    if (odlegloscOdPrawegoGornegoPunktu < bok){
                        return true;
                    }
                }
            }
            // 2 ćwiartka
            if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaY() > ((Kwadrat) f).punkt.getPozycjaY()+((Kwadrat) f).bok){
                    double odlegloscOdLewegoGornegoPunktu = 0;
                    odlegloscOdLewegoGornegoPunktu = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, new Punkt(((Kwadrat) f).punkt.getPozycjaX(), ((Kwadrat) f).punkt.getPozycjaY()+((Kwadrat) f).bok));
                    if (odlegloscOdLewegoGornegoPunktu < bok){
                        return true;
                    }
                }
                else if (Math.abs(punkt.getPozycjaX() - ((Kwadrat) f).punkt.getPozycjaX()) < bok){
                    return true;
                }
            }
            // 3 ćwiartka
            if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Kwadrat) f).punkt.getPozycjaY()){
                if (odlegloscMiedzyPunktami < bok){
                    return true;
                }
            }
            // 4 cwiartka
            if (punkt.getPozycjaX() >= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() > ((Kwadrat) f).punkt.getPozycjaX()+((Kwadrat) f).bok){
                    double odlegloscOdPrawegoDolnegoPunktu = 0;
                    odlegloscOdPrawegoDolnegoPunktu = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, new Punkt(((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok, ((Kwadrat) f).punkt.getPozycjaY()));
                    if (odlegloscOdPrawegoDolnegoPunktu < bok){
                        return true;
                    }
                }
                else if (Math.abs(punkt.getPozycjaY() - ((Kwadrat) f).punkt.getPozycjaY()) < bok){
                    return true;
                }
            }
        }
        //Przecinanie z prostokątem
        if (f instanceof Prostokat){
            double odlegloscMiedzyPunktami = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, ((Prostokat) f).getPunkt());
            double odlegloscMiedzyX = Math.abs(punkt.getPozycjaX() - ((Prostokat) f).punkt.getPozycjaX());
            double odlegloscMiedzyY = Math.abs(punkt.getPozycjaY() - ((Prostokat) f).punkt.getPozycjaY());
            // skrajne przypadki
            if (punkt.getPozycjaX() == ((Prostokat) f).getPunkt().getPozycjaX() && punkt.getPozycjaY() == ((Prostokat) f).getPunkt().getPozycjaY()) {
                return true;
            }
            // 1 ćwiartka
            if (punkt.getPozycjaX() >= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Prostokat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() <= ((Prostokat) f).punkt.getPozycjaX() + ((Prostokat) f).bokA){
                    if (punkt.getPozycjaY() - bok < ((Prostokat) f).punkt.getPozycjaY() + ((Prostokat) f).bokB){
                        return true;
                    }
                }
                else if (punkt.getPozycjaY() <= ((Prostokat) f).getPunkt().getPozycjaY() + ((Prostokat) f).bokB){
                    if (punkt.getPozycjaX() - bok < ((Prostokat) f).punkt.getPozycjaX() + ((Prostokat) f).bokA){
                        return true;
                    }
                }
                else {
                    double odlegloscOdPrawegoGornegoPunktu;
                    odlegloscOdPrawegoGornegoPunktu = Rysunek.obliczanieOdleglosciMiedzyPunktami(new Punkt(((Prostokat) f).punkt.getPozycjaX() + ((Prostokat) f).bokA, ((Prostokat) f).punkt.getPozycjaY()+ ((Prostokat) f).bokB),punkt);
                    if (odlegloscOdPrawegoGornegoPunktu < bok){
                        return true;
                    }
                }
            }
            // 2 ćwiartka
            if (punkt.getPozycjaX() <= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Prostokat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaY() > ((Prostokat) f).punkt.getPozycjaY()+((Prostokat) f).bokB){
                    double odlegloscOdLewegoGornegoPunktu = 0;
                    odlegloscOdLewegoGornegoPunktu = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, new Punkt(((Prostokat) f).punkt.getPozycjaX(), ((Prostokat) f).punkt.getPozycjaY()+((Prostokat) f).bokB));
                    if (odlegloscOdLewegoGornegoPunktu < bok){
                        return true;
                    }
                }
                else if (Math.abs(punkt.getPozycjaX() - ((Prostokat) f).punkt.getPozycjaX()) < bok){
                    return true;
                }
            }
            // 3 ćwiartka
            if (punkt.getPozycjaX() <= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Prostokat) f).punkt.getPozycjaY()){
                if (odlegloscMiedzyPunktami < bok){
                    return true;
                }
            }
            // 4 cwiartka
            if (punkt.getPozycjaX() >= ((Prostokat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Prostokat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() > ((Prostokat) f).punkt.getPozycjaX()+((Prostokat) f).bokA){
                    double odlegloscOdPrawegoDolnegoPunktu = 0;
                    odlegloscOdPrawegoDolnegoPunktu = Rysunek.obliczanieOdleglosciMiedzyPunktami(punkt, new Punkt(((Prostokat) f).punkt.getPozycjaX() + ((Prostokat) f).bokA, ((Prostokat) f).punkt.getPozycjaY()));
                    if (odlegloscOdPrawegoDolnegoPunktu < bok){
                        return true;
                    }
                }
                else if (Math.abs(punkt.getPozycjaY() - ((Prostokat) f).punkt.getPozycjaY()) < bok){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String toString(){
        String napis = "";
        napis += "nazwa = "+nazwa+"\n";
        napis += "obwod = "+obwod()+"  ";
        napis += "pole = "+pole()+"  ";
        napis += "wspolrzedne punktu = "+"("+getPunkt().getPozycjaX()+", "+getPunkt().getPozycjaY()+")"+"\n";
        napis += "rodzaj figury = "+"kolo"+"\n";
        return napis;
    }
    @Override
    public boolean equals(Object obiekt){
        if (obiekt instanceof Kolo){
            if (nazwa.equals(((Kolo) obiekt).nazwa))
            {
                if (punkt == ((Kolo) obiekt).punkt){
                    if (bok == ((Kolo) obiekt).bok){
                        return true;
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
                return -1;
            }
            else if(_figura.getClass().toString().equals("class Kolo")){
                return punkt.compareTo(_figura.getPunkt());
            }
            else if(_figura.getClass().toString().equals("class Prostokat")){
                return -1;
            }
        }
        return 0;
    }
}
