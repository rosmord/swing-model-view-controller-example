package tableditor.persistence.facade.dto;

public record PromotionDTO(String prom) {

    @Override
    public final String toString() {
        return prom;
    }
}
