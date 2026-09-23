public class Booking {
    private byte aktivitetsnummer;
    private int antalDeltagare;
    private int slutpris;

    public Booking(byte a, int d, int p) {
        setAktivitetsnummer(a);
        setAntalDeltagare(d);
        setSlutpris(p);
    }

    public static String aktivitetTillString(byte aktivitet) {
        if (aktivitet == 1) {
            return "Programmeringsworkshop";
        } else if (aktivitet == 2) {
            return "Matlagningskurs";
        } else if (aktivitet == 3) {
            return "Träningspass";
        } else {
            throw new IllegalArgumentException(
                    "Aktiviteten finns inte"
            );
        }
    }

    public static byte stringTillAktivitet(String aktivitet) {
        if (aktivitet.equals("Programmeringsworkshop")) {
            return 1;
        } else if (aktivitet.equals("Matlagningskurs")) {
            return 2;
        } else if (aktivitet.equals("Träningspass")) {
            return 3;
        } else {
            throw new IllegalArgumentException(
                    "Aktiviteten finns inte: " + aktivitet
            );
        }
    }

    public String getAktivitetsnamn() {
        return aktivitetTillString(aktivitetsnummer);
    }

    private void setAktivitetsnummer(byte a){
        if (a == 1 || a == 2 || a == 3) {
            this.aktivitetsnummer = a;
        } else {
            throw new IllegalArgumentException(
                    "Aktiviteten måste vara 1, 2 eller 3."
            );
        }
    }

    private void setAntalDeltagare(int d) {
        if (d > 0) {
            this.antalDeltagare = d;
        } else {
            throw new IllegalArgumentException(
                    "Antalet deltagare måste vara större än 0."
            );
        }
    }

    private void setSlutpris(int p){
        if (p >= 0) {
            this.slutpris = p;
        }
        else {
            throw new IllegalArgumentException("Priset kan inte vara negativt");
        }
    }

    //- En metod som returnerar aktivitetsnumret.
    public byte getAktivitetsnummer() {
        return aktivitetsnummer;
    }

    //- En metod som returnerar antal deltagare.
    public int getAntalDeltagare() {
        return antalDeltagare;
    }

    //- En metod som returnerar slutpriset.
    public int getSlutpris() {
        return slutpris;
    }
}