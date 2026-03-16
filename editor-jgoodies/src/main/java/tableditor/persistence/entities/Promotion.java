package tableditor.persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Promotion {
    @Id
    private String prom;

    public Promotion() {
    }

    public Promotion(String prom) {
        this.prom = prom;
    }

    public String getProm() {
        return prom;
    }

}
