import java.io.*;
import java.util.Scanner;

public class Main {

// - En metod som visar menyn.
// - En metod som beräknar priset för en bokning.
// - En metod som registrerar en bokning och lagrar den.
// - En metod som visar alla registrerade bokningar.
// - En metod som visar sammanställningen.

    public static void main(String[] args) {


        Booking test = new Booking(1, 14, 800);
        int i = test.getAktivitetsnummer();
        int j = test.getAntalDeltagare();
        int k = test.getSlutpris();
        System.out.println(i + " " + j + " " + k);


    }
    //Metod som visar menyn
    void meny(){
        byte menyVal = 0;
        while(true){
            //Skriv ut meny
            System.out.print("AKTIVITETSBOKAREN\n" + "1. Registrera bokning\n" + "2. Visa alla bokningar\n" + "3. Visa sammanställning\n" + "4. Avsluta");
            //Läs av menyval
            Scanner scan = new Scanner(System.in);
            //if 4 break

            //else if 1, skapa bokning

            //else if 2, visa alla bokningar

            //else if 3, visa sammanställning

            //else ogiltigt, försök igen.
        }

    }

    //Metod för att beräkna priset för en aktivitet med ett antal deltagare
    //Returnerar String för att förhindra att värdet ändras till något annat
    private String prisBeräkning(byte aktivitet, int deltagare){
        String prisTxt;
        if (aktivitet==1) { //Programmeringsworkshop
            return prisTxt = 200*deltagare + " kr"; //för 10 deltagare blir det "2000 kr"
        } else if (aktivitet==2) { //Matlagningskurs
            return prisTxt = 300*deltagare + " kr";
        } else if (aktivitet==3) { //Träningspass
            return prisTxt = 150*deltagare + " kr";
        } else {
            throw new IllegalArgumentException("Aktiviteten finns inte");
        }
    }

    //Metod för att registrera bokning
    private void registreraBokning(){
        //Ta emot bokningsinforrmation

        //anropa konstruktor i metod i booking
    }

    //Metod för att visa alla registrerade bokningar

        //Ta emot ArrayList med de sparade registreringarna

        //Sortera Listan

        //Skriv ut Listan


/*
    {3, 14, 653}
    {"1,"  "12" ":summa"}

var bokning = new Booking();
    {2, 17, ": Namn summa kr"}
    {1, 5, ": Namn summa kr"}
    {3, 14, ": Namn summa kr"}
    {3, 14, ": Namn summa kr"}

ArrayList(new Booking)


 */


    //Läser och retunerar en rad från en fil.     Blir null om inget hittades
    public static String läsRadFrånFil(String filnamn, int rad){
        try {
            String returRad = null;
            BufferedReader läsFil = new BufferedReader(new FileReader(filnamn));
            for (int i = 0; i<rad; i++){
                returRad = läsFil.readLine();
            }
            return returRad;
        } catch (IOException e) {
            System.out.println("Error");
            return null;
        }
    }

    //Skriver en rad text till en fil
    static void SkrivLnTillFil(String filnamn, String text){
        try {
            PrintWriter skriv = new PrintWriter(new BufferedWriter(new FileWriter(filnamn, true)));
            skriv.println(text);
        }catch (IOException e){
            System.out.println("Error");
        }
    }

    //Skriver text till en fil
    static void SkrivTillFil(String filnamn, String text){
        try {
            PrintWriter skriv = new PrintWriter(new BufferedWriter(new FileWriter(filnamn, true)));
            skriv.print(text);
            skriv.close();
        }catch (IOException e){
            System.out.println("Error");
        }
    }
}
