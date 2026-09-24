import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    List<Booking> aktivtBokningsminne = new ArrayList<>(); //Aktiva databasen i RAM. Så länge programmet körs är den tillgänglig
    static String statFilnamn = "SparadeBokningar.txt";

    private final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Main program = new Main();
        //Metod för att läsa in bokningar från .txt till ArrayList booking
        program.startSavedTxtToList();
        //Kalla på meny
        program.meny();
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
                radAktivitet = (rad.substring(0, rad.indexOf(',')).trim());
                aktivitet = Booking.stringTillAktivitet(radAktivitet);
                String deltagareStr = rad.substring(
                        rad.indexOf(",") + 1,
                        rad.indexOf("deltagare")
                ).trim();

                deltagare = Integer.parseInt(deltagareStr);

                String prisStr = rad.substring(
                        rad.lastIndexOf(",") + 1,
                        rad.indexOf("kr")
                ).trim();

                pris = Integer.parseInt(prisStr);
                aktivtBokningsminne.add(new Booking(aktivitet, deltagare, pris)); //Konstruktorn anroppas och det sparade värdet lagras
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    //Metoden som visar menyn
    void meny(){
        byte menyVal = 0;
            //Skriv ut meny
            System.out.println("AKTIVITETSBOKAREN\n" + "1. Registrera bokning\n" + "2. Visa alla bokningar\n" + "3. Visa sammanställning\n" + "4. Avsluta");

            //Läs av menyval
            while (true) {

                if (scan.hasNextByte()) {
                    menyVal = scan.nextByte();

                    if (menyVal == 4) {
                        break;
                    } else if (menyVal == 1) {
                        registreraBokning();
                    } else if (menyVal == 2) {
                        visaAllaBokningar();
                    } else if (menyVal == 3) {
                        visaSammanställning();
                    } else {
                        System.out.println("Ogiltigt alternativ. Välj 1-4.");
                    }

                } else {
                    System.out.println("Ogiltig inmatning. Ange ett nummer mellan 1 och 4.");
                    scan.next();
                }
            }

            {
                System.out.println("\nOgiltigt alternativ, försök igen");
            }

    }

    //Metoden som beräkna priset för en bokning med ett antal deltagare
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

    //Metoden som registrerar en bokning
    private void registreraBokning(){   //Ta emot bokningsinforrmation
        byte aktivitet;
        int deltagare;
        int pris;
        String aktivitetStr;
        String s;

        //Aktivitetsnummer
        System.out.println("Vilken aktivitet gäller registreringen?\n1. Programmeringsworkshop\n2. Matlagningskurs\n3. Träningspass");
        while (true) {

            if (scan.hasNextByte()) {

                aktivitet = scan.nextByte();

                if (aktivitet >= 1 && aktivitet <= 3) {
                    break;
                } else {
                    System.out.println("Aktiviteten finns inte. Välj 1, 2 eller 3.");
                }

            } else {

                System.out.println("Ogiltig inmatning. Ange ett nummer mellan 1 och 3.");
                scan.next();
            }
        }

        //Deltagare
        System.out.print("Hur många deltagare vill du registrera för aktiviteten?\nAntal: ");
        while (true) {

            if (scan.hasNextInt()) {

                deltagare = scan.nextInt();

                if (deltagare > 0) {
                    break;
                } else {
                    System.out.println(
                            "Fel värde för antal deltagare. Ange ett positivt antal."
                    );
                }

            } else {

                System.out.println(
                        "Ogiltig inmatning. Ange ett heltal."
                );

                scan.next();
            }
        }

        //Beräkna bokningens slutpris.
        pris = prisBeräkning(aktivitet, deltagare);

        //Skriv ut aktivitet, antal deltagare och slutpris när bokningen registreras.
        aktivitetStr = Booking.aktivitetTillString(aktivitet);

        s = aktivitetStr + ", " + deltagare + " deltagare, " + pris + " kr"; //Registreringen i Sträng
        System.out.println(s);

        //Anropa konstruktorn för Booking
        aktivtBokningsminne.add(new Booking(aktivitet, deltagare, pris)); //Konstruktorn anroppas

        //Spara registreringen i textfilen
        skrivLnTillFil(statFilnamn, s);
    }

    //Metoden som visar alla registrerade bokningar
    private void visaAllaBokningar() {

        int bokningNummer = 1;

        for (Booking i : aktivtBokningsminne) {

            int d = i.getAntalDeltagare();
            int p = i.getSlutpris();

            String aktivitetStr = i.getAktivitetsnamn();

            System.out.println(
                    "Bokning " + bokningNummer + ": " +
                            aktivitetStr + ", " +
                            d + " deltagare, " +
                            p + " kr"
            );

            bokningNummer++;
        }
    }

    // Metoden som visar sammanställningen
    private void visaSammanställning() {

        // Totalt antal bokningar
        int totaltAntalBokningar = aktivtBokningsminne.size();

        // Totalt värde av alla bokningar
        int totaltBokningsvärde = 0;

        // Antal bokningar per aktivitet
        int antalProgrammeringsworkshop = 0;
        int antalMatlagningskurs = 0;
        int antalTräningspass = 0;

        // Gå igenom alla bokningar
        for (Booking bokning : aktivtBokningsminne) {

            // Lägg till priset för denna bokning
            totaltBokningsvärde += bokning.getSlutpris();

            // Räkna själva bokningen, inte deltagarna
            if (bokning.getAktivitetsnummer() == 1) {
                antalProgrammeringsworkshop++;
            } else if (bokning.getAktivitetsnummer() == 2) {
                antalMatlagningskurs++;
            } else if (bokning.getAktivitetsnummer() == 3) {
                antalTräningspass++;
            }
        }

        // Skriv ut sammanställningen
        System.out.println("\nSAMMANSTÄLLNING");
        System.out.println("Totalt antal bokningar: " + totaltAntalBokningar);
        System.out.format("Totalt bokningsvärde: %,3d kr\n", totaltBokningsvärde);
        System.out.println("Programmeringsworkshop: " + antalProgrammeringsworkshop + " bokningar");
        System.out.println("Matlagningskurs: " + antalMatlagningskurs + " bokningar");
        System.out.println("Träningspass: " + antalTräningspass + " bokningar");
    }

    //Skriver en rad text till en fil
    public static void skrivLnTillFil(String filnamn, String text){
        try {
            PrintWriter skriv = new PrintWriter(new BufferedWriter(new FileWriter(filnamn, true)));
            skriv.println(text);
            skriv.close();
        }catch (IOException e){
            System.out.println("Error");
        }
    }
}