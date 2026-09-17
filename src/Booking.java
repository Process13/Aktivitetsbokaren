public class Booking {
        private int aktivitetsnummer;
        private int antalDeltagare;
        private int slutpris;

        public Booking(int a, int d, int p){
            setAktivitetsnummer(a);
            setAntalDeltagare(d);
            setSlutpris(p);
        }

        private void setAktivitetsnummer(int a){
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

    public int getAktivitetsnummer() {
        return aktivitetsnummer;
    }

    public int getAntalDeltagare() {
        return antalDeltagare;
    }

    public int getSlutpris() {
        return slutpris;
    }
}
