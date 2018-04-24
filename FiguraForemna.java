public abstract class FiguraForemna implements Figura, Comparable<Figura> {
    protected double bok;
    protected String nazwa;
    protected Punkt punkt;
    FiguraForemna(){
        bok = 0;
        punkt = new Punkt(0,0);
    }
    FiguraForemna(double _bok, double _pozycjaX, double _pozycjaY){
        bok = _bok;
        punkt = new Punkt(_pozycjaX, _pozycjaY);
    }
}
