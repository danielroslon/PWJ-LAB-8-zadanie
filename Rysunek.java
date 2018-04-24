import java.util.Scanner;
import java.util.TreeSet;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.HashSet;

class WartoscUjemna extends Exception{
    public WartoscUjemna() {}
}
class IstniejacyElement extends Exception{
    public IstniejacyElement() {}
}



public class Rysunek {
    public static void main(String[] args){
        //Wprowadzanie danych
        TreeSet<Figura> konten = new TreeSet<Figura>();
        wprowadzanieFigur(konten);
        //Wypisywanie zawartości kontenera
        wypiszZawartoscKontenera(konten);
        //Sprawdzanie przecinania figur oraz ich wypisanie
        przecinanieFigur(konten);
    }


    public static void wprowadzanieFigur(TreeSet<Figura> konten){
        try
        {
            Scanner in = new Scanner(System.in);
            in.useLocale(Locale.US);

            boolean wykonywaniePetli = true;
            while (wykonywaniePetli){
                wypisywanieMenu();
                int wybor = in.nextInt();
                switch(wybor){
                    case 1:{
                        double promien, x, y;
                        System.out.print("\nPodaj promień: ");
                        promien = in.nextDouble();
                        if (promien < 0)
                            throw new WartoscUjemna();
                        System.out.print("Podaj współrzędną X: ");
                        x = in.nextDouble();
                        System.out.print("Podaj współrzędną Y: ");
                        y = in.nextDouble();
                        Kolo nowyElement = new Kolo(promien,x,y);
                        if (konten.contains(nowyElement))
                            throw new IstniejacyElement();
                        konten.add(nowyElement);
                    }break;
                    case 2:{
                        double bok, x, y;
                        System.out.print("\nPodaj bok: ");
                        bok = in.nextDouble();
                        if (bok < 0)
                            throw new WartoscUjemna();
                        System.out.print("Podaj współrzędną X: ");
                        x = in.nextDouble();
                        System.out.print("Podaj współrzędną Y: ");
                        y = in.nextDouble();
                        Kwadrat nowyElement = new Kwadrat(bok,x,y);
                        if (konten.contains(nowyElement))
                            throw new IstniejacyElement();
                        konten.add(nowyElement);
                    }break;
                    case 3:{
                        double bokA, bokB,  x, y;
                        System.out.print("\nPodaj pierwszy bok: ");
                        bokA = in.nextDouble();
                        if (bokA < 0)
                            throw new WartoscUjemna();
                        System.out.print("Podaj drugi bok: ");
                        bokB = in.nextDouble();
                        if (bokB < 0)
                            throw new WartoscUjemna();
                        System.out.print("Podaj współrzędną X: ");
                        x = in.nextDouble();
                        System.out.print("Podaj współrzędną Y: ");
                        y = in.nextDouble();
                        Prostokat nowyElement = new Prostokat(bokA, bokB,x,y);
                        if (konten.contains(nowyElement))
                            throw new IstniejacyElement();
                        konten.add(nowyElement);
                    }break;
                    case 0:{
                        wykonywaniePetli = false;
                    }break;
                    default:{
                        System.out.println("Podano nieprawidłowe dane");
                    }break;
                }
            }

        }
        catch (WartoscUjemna a){
            System.out.println("\nPodano wartosc ujemna dla boku lub promienia");
            wprowadzanieFigur(konten);
        }
        catch (InputMismatchException a){
            System.out.println("\nPodano literę zamiast liczby");
            wprowadzanieFigur(konten);
        }
        catch (IstniejacyElement a){
            System.out.println("\nPodano istniejący element");
            wprowadzanieFigur(konten);
        }

    }
    public static void wypisywanieMenu(){
        System.out.println("1. Dodaj nowe koło");
        System.out.println("2. Dodaj nowy kwadrat");
        System.out.println("3. Dodaj nowy prostokąt");
        System.out.println("0. Zakoncz wprowadzanie figur");
        System.out.print("\nWybieram: ");
    }
    public static void wypiszZawartoscKontenera(TreeSet<Figura> konten){
        System.out.println("\n");
        for(Figura f : konten){
            System.out.println(f.toString());
        }
    }
    public static void przecinanieFigur(TreeSet<Figura> konten){
        //Tworzenie zbioru grup przecinajacych sie figur
        HashSet<TreeSet<Figura>> zbiorGrup = new HashSet<TreeSet<Figura>>();
        for (Figura a : konten){
            TreeSet<Figura> grupa = new TreeSet<Figura>();
            grupa.add(a);
            for (Figura b : konten){
                if (a.przecina(b) && a != b){
                    grupa.add(b);
                }
            }
            zbiorGrup.add(grupa);
        }
        //Tworzenie zbioru unikalnych grup przecinajacych figury
        HashSet<TreeSet<Figura>> zbiorUnikalnychGrup = new HashSet<TreeSet<Figura>>();
        for (TreeSet<Figura> a : zbiorGrup){
            if (a.size() > 1 && !zawieraZbior(zbiorUnikalnychGrup,a))
            {
                zbiorUnikalnychGrup.add(a);
            }
        }
        //Wypisywania zbioru grup
        int licznikGrup = 0;
        for (TreeSet<Figura> a : zbiorUnikalnychGrup){
            licznikGrup++;
            System.out.println(licznikGrup+" grupa");
            for (Figura b : a){
                System.out.println(b.getNazwa());
            }
            System.out.println("\n");
        }
    }
    public static boolean zawieraZbior(HashSet<TreeSet<Figura>> zbiorDoSprawdzenia, TreeSet<Figura> zbiorZElementami){
        boolean maTeSameElementy = false;
        int iloscElementowWZbiorze = 0;
        int iloscZnalezionychElementow = 0;

        for (TreeSet<Figura> a : zbiorDoSprawdzenia){
            iloscElementowWZbiorze = a.size();
            iloscZnalezionychElementow = 0;
            for (Figura c : zbiorZElementami){
                for (Figura b : a){
                    if (b.equals(c)){
                        iloscZnalezionychElementow++;
                    }
                }
            }
            if (iloscZnalezionychElementow == iloscElementowWZbiorze && iloscElementowWZbiorze == zbiorZElementami.size()){
                maTeSameElementy = true;
                break;
            }
        }
        return maTeSameElementy;
    }
    public static double obliczanieOdleglosciMiedzyPunktami(Punkt p1, Punkt p2){
        double odlegloscMiedzyPunktami;
       odlegloscMiedzyPunktami = (p1.getPozycjaX() - p2.getPozycjaX()) * (p1.getPozycjaX() - p2.getPozycjaX());
       odlegloscMiedzyPunktami += (p1.getPozycjaY() - p2.getPozycjaY()) * (p1.getPozycjaY() - p2.getPozycjaY());
       odlegloscMiedzyPunktami = Math.sqrt(odlegloscMiedzyPunktami);
       return  odlegloscMiedzyPunktami;
    }
}
