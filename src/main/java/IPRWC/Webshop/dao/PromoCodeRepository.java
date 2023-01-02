package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {
}