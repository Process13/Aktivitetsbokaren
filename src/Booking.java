public class Booking {
    private byte aktivitetsnummer;
    private int antalDeltagare;
    private int slutpris;

    public Booking(byte a, int d, int p) {
        setAktivitetsnummer(a);
        setAntalDeltagare(d);
        setSlutpris(p);
    }

    private void setAktivitetsnummer(byte a){
        if (a == 1 || a == 2 || a == 3) {
            this.aktivitetsnummer = a;
        }
        else {
            throw new IllegalArgumentException("Aktiviteten finns inte");
        }
    }

    private void setAntalDeltagare(int d){
        if (d>0 && d< 2000000000) {
            this.antalDeltagare = d;
        }
        else {
            throw new IllegalArgumentException("Ogiltigt antal deltagare");
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