package tableditor.persistence.facade.dto;

public record StudentDTO(
    Integer id,
    String firstname,
    String lastname,
    String prom,
    Double grade
) {
    
}
