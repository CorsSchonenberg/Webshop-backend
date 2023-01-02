package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.PromoCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PromoCodeDao {
    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeDao(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public void saveToDatabase(PromoCode promoCode) {
        this.promoCodeRepository.save(promoCode);
    }
    public ArrayList<PromoCode> getAllCodes() {
        return (ArrayList<PromoCode>) this.promoCodeRepository.findAll();
    }
    public void deleteCodeFromDatabase(Integer id) {
        this.promoCodeRepository.deleteById(id);
    }
}
