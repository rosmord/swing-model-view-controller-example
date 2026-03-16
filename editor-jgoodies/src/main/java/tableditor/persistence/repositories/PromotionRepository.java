package tableditor.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tableditor.persistence.entities.Promotion;
import tableditor.persistence.facade.dto.PromotionDTO;

public interface PromotionRepository extends JpaRepository<Promotion, String> {
    List<PromotionDTO> findDTOBy();
}
