public class Booking {
        private byte aktivitetsnummer;
        private int antalDeltagare;
        private int slutpris;

<<<<<<< Updated upstream
        public Booking(byte a, int d, int p){
=======

        public Booking(int a, int d, int p){
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        //- En metod som returnerar aktivitetsnumret.
        public byte getAktivitetsnummer() {
=======


    public int getAktivitetsnummer() {
>>>>>>> Stashed changes
        return aktivitetsnummer;
        }

        //- En metod som returnerar antal deltagare.
        public int getAntalDeltagare() {
        return antalDeltagare;
        }

        //- En metod som returnerar slutpriset.
        public int getSlutpris() {
        return slutpris;
<<<<<<< Updated upstream
        }
=======
    }


>>>>>>> Stashed changes
}
