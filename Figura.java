public interface Figura  {
    public double pole();
    public double obwod();
    public Punkt getPunkt();
    public double getBok();
    public String getNazwa();
    public boolean przecina(Object obiekt);
    @Override
    public boolean equals(Object obiekt);
}
