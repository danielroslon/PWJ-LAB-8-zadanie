public class Kwadrat extends FiguraForemna {
    //konstruktory
    Kwadrat(){
        super();
        nazwa = "|kw|"+bok+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    Kwadrat(double _bok, double _pozycjaX, double _pozycjaY){
        super(_bok, _pozycjaX, _pozycjaY);
        nazwa = "|kw|"+bok+"|"+punkt.getPozycjaX()+"|"+punkt.getPozycjaY()+"|";
    }
    //metody
    @Override
    public double pole(){
        return bok*bok;
    }
    @Override
    public double obwod(){
        return 4 * bok;
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
        if (f instanceof Kwadrat){
            // skrajne przypadki
            if (punkt.getPozycjaX() == ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() == ((Kwadrat) f).punkt.getPozycjaY()) {
                return true;
            }
            // 1 ćwiartka
            if (punkt.getPozycjaX() >= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() < ((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok && punkt.getPozycjaY() < ((Kwadrat) f).punkt.getPozycjaY() + ((Kwadrat) f).bok){
                    return true;
                }
            }
            // 2 ćwiartka
            if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() >= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() + bok > ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() < ((Kwadrat) f).punkt.getPozycjaY() + ((Kwadrat) f).bok){
                    return true;
                }
            }
            // 3 ćwiartka
            if (punkt.getPozycjaX() <= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() + bok > ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() + bok > ((Kwadrat) f).punkt.getPozycjaY()){
                    return true;
                }
            }
            // 4 ćwiartka
            if (punkt.getPozycjaX() >= ((Kwadrat) f).punkt.getPozycjaX() && punkt.getPozycjaY() <= ((Kwadrat) f).punkt.getPozycjaY()){
                if (punkt.getPozycjaX() < ((Kwadrat) f).punkt.getPozycjaX() + ((Kwadrat) f).bok && punkt.getPozycjaY() + bok > ((Kwadrat) f).punkt.getPozycjaY()){
                    return true;
                }
            }
        }
         if (f instanceof Kolo){
            return ((Kolo) f).przecina(this);
        }
         if (f instanceof Prostokat){
            return ((Prostokat) f).przecina(this);
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
        napis += "rodzaj figury = "+"kwadrat"+"\n";
        return napis;
    }
    @Override
    public boolean equals(Object obiekt){
        if (obiekt instanceof Kwadrat){
            if (nazwa.equals(((Kwadrat) obiekt).nazwa))
            {
                if (punkt == ((Kwadrat) obiekt).punkt){
                    if (bok == ((Kwadrat) obiekt).bok){
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
                return punkt.compareTo(_figura.getPunkt());
            }
            else if(_figura.getClass().toString().equals("class Kolo")){
                return 1;
            }
            else if(_figura.getClass().toString().equals("class Prostokat")){
                return -1;
            }
        }
        return 0;
    }
}
