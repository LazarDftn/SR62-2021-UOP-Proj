package domZdravlja;

public class Termin {
    private Lekar lekar;
    private Pacijent pacijent;
    private String datum;
    private Status status;
    private String opisTerapije;

    public Termin(Lekar lekar, Pacijent pacijent, String datum, Status status, String opisTerapije) {
        this.lekar = lekar;
        this.pacijent = pacijent;
        this.datum = datum;
        this.status = status;
        this.opisTerapije = opisTerapije;
    }
    

        public Lekar getLekar() {
            return lekar;
        }

        public void setLekar(Lekar lekar) {
            this.lekar = lekar;
        }

        public Pacijent getPacijent() {
            return pacijent;
        }

        public void setPacijent(Pacijent pacijent) {
            this.pacijent = pacijent;
        }

        public String getDatum() {
            return datum;
        }

        public void setDatum(String datum) {
            this.datum = datum;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getOpisTerapije() {
            return opisTerapije;
        }

        public void setOpisTerapije(String opisTerapije) {
            this.opisTerapije = opisTerapije;
        }
    }



