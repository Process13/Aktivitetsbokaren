import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

// - En metod som visar menyn.
// - En metod som beräknar priset för en bokning.
// - En metod som registrerar en bokning och lagrar den.
// - En metod som visar alla registrerade bokningar.
// - En metod som visar sammanställningen.

    static List<Booking> aktivtBokningsminne = new ArrayList<>(); //Aktiva databasen i RAM. Så länge programmet körs är den tillgänglig
    static String statFilnamn = "SparadeBokningar.txt";

    public void main(String[] args) {
        //Metod för att läsa in bokningar från .txt till ArrayList booking
        startSavedTxtToList();
        //Kalla på meny
        meny();

    }

    //Sparar allt innehåll i textfilen in i ArrayList för Booking
    private void startSavedTxtToList(){
        String rad;
        String radAktivitet;
        byte aktivitet;
        int deltagare;
        int pris;
        try {
            Scanner fil = new Scanner(new File(statFilnamn));
            while (fil.hasNext()) {     //Går igenom varige rad i filen och konverterar Sträng raderna så att konstruktorn kan ta emot dem
                rad = fil.nextLine();
                radAktivitet = (rad.substring(0, rad.indexOf(',')-1).trim());
                if (radAktivitet.equals("Programmeringsworkshop")) {    //Omvandlar Strängen "Programmeringsworkshop" till dess värde för att sparas
                    aktivitet = 1;
                } else if (radAktivitet.equals("Matlagningskurs")) {
                    aktivitet = 2;
                } else if (radAktivitet.equals("Träningspass")) {
                    aktivitet = 3;
                } else {
                    throw new IllegalArgumentException("Det är inte korrekt innehåll i filen på raden");
                }
                deltagare = Integer.parseInt(rad.substring(rad.indexOf(',')+1, rad.lastIndexOf(',')-9).trim()); //-9 då "deltagare" inte ska med
                pris = Integer.parseInt(rad.substring(rad.lastIndexOf(',')+1, rad.lastIndexOf('k')).trim());
                aktivtBokningsminne.add(new Booking(aktivitet, deltagare, pris)); //Konstruktorn anroppas och det sparade värdet lagras
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    //Metod som visar menyn
    void meny(){
        byte menyVal = 0;
        while(true){
            //Skriv ut meny
            System.out.print("AKTIVITETSBOKAREN\n" + "1. Registrera bokning\n" + "2. Visa alla bokningar\n" + "3. Visa sammanställning\n" + "4. Avsluta");

            //Läs av menyval
            while (true) {
                Scanner scan = new Scanner(System.in);
                if (scan.hasNextByte()) {
                    menyVal = scan.nextByte();
                    break;
                }
            }

            //if 4 break
            if (menyVal==4){
                break;
            }

            //else if 1, skapa bokning
            else if (menyVal==1) {
                registreraBokning();
            }

            //else if 2, visa alla bokningar
            else if (menyVal==2) {
                // ///////////////////////////////////TOM////////////////////////
            }

            //else if 3, visa sammanställning
            else if (menyVal==3) {
                // ///////////////////////////////////TOM////////////////////////
            }

            //else ogiltigt, försök igen.
            else {
                System.out.println("\nOgiltigt alternativ, försök igen");
            }
        }

    }

    //Metod för att beräkna priset för en aktivitet med ett antal deltagare
    private int prisBeräkning(byte aktivitet, int deltagare){
        int pris; //Int då inga priser innehåller decimaler
        if (aktivitet==1) { //Programmeringsworkshop
            return pris = 200*deltagare; //för 10 deltagare blir det "2000 kr"
        } else if (aktivitet==2) { //Matlagningskurs
            return pris = 300*deltagare;
        } else if (aktivitet==3) { //Träningspass
            return pris = 150*deltagare;
        } else {
            throw new IllegalArgumentException("Aktiviteten finns inte");
        }
    }

    //Metod för att registrera bokning
    private void registreraBokning(){   //Ta emot bokningsinforrmation
        byte aktivitet;
        int deltagare;
        int pris;
        String aktivitetStr;
        String s;

        //Aktivitetsnummer
        System.out.println("Vilken aktivitet gäller registreringen?\n1. Programmeringsworkshop\n2. Matlagningskurs\n3. Träningspass");
        while (true) {  //Läser in vilken aktivitet som ska registreras
            Scanner scanA = new Scanner(System.in);
            if (scanA.hasNextByte()) {
                aktivitet = scanA.nextByte();
                if (aktivitet > 0 && aktivitet <= 3) {  //Är nummret som angavs en aktivitet?
                    break;
                } else {
                    System.out.println("\nAktiviteten finns inte.");
                }
            } else {
                throw new IllegalArgumentException("Aktiviteten finns inte");
            }
        }

        //Deltagare
        System.out.print("Hur många deltagare vill du registrera för aktiviteten?\nAntal: ");
        while (true){   //Läser in antal deltagare
            Scanner scanD = new Scanner(System.in);
            if (scanD.hasNextInt()){
                deltagare = scanD.nextInt();
                if (deltagare>0){
                    break;
                } else {
                    System.out.println("\nFel värde för antal deltagare");
                }
            } else {
                throw new IllegalArgumentException("Fel värde för antal deltagare");
            }
        }

        //Beräkna bokningens slutpris.
        pris = prisBeräkning(aktivitet, deltagare);

        //Skriv ut aktivitet, antal deltagare och slutpris när bokningen registreras.
        if (aktivitet == 1) {    //Aktivitet    byte -> string
            aktivitetStr = "Programmeringsworkshop";
        } else if (aktivitet == 2) {
            aktivitetStr = "Matlagningskurs";
        } else {
            aktivitetStr = "Träningspass";
        }
        s = aktivitetStr + ", " + deltagare + " deltagare, " + pris + " kr"; //Registreringen i Sträng
        System.out.println(s);

        //Anropa konstruktorn för Booking
        aktivtBokningsminne.add(new Booking(aktivitet, deltagare, pris)); //Konstruktorn anroppas

        //Spara registreringen i textfilen
        skrivLnTillFil(statFilnamn, s);

    }

    //Metod för att visa alla registrerade bokningar

        //Ta emot ArrayList med de sparade registreringarna

        //Sortera Listan

        //Skriv ut Listan


    //Skriver en rad text till en fil
    public static void skrivLnTillFil(String filnamn, String text){
        try {
            PrintWriter skriv = new PrintWriter(new BufferedWriter(new FileWriter(filnamn, true)));
            skriv.println(text);
        }catch (IOException e){
            System.out.println("Error");
        }
    }
}
