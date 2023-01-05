package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.model.PromoCode;
import IPRWC.Webshop.service.ReturnNewIdService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PromoCodeDao {
    private final PromoCodeRepository promoCodeRepository;
    private final ReturnNewIdService returnNewIdService;

    public PromoCodeDao(PromoCodeRepository promoCodeRepository, ReturnNewIdService returnNewIdService) {
        this.promoCodeRepository = promoCodeRepository;
        this.returnNewIdService = returnNewIdService;
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

    public int givePromoCodeNewId() {
        ArrayList<PromoCode> codes =
                (ArrayList<PromoCode>) this.promoCodeRepository.findAll();
        return this.returnNewIdService.returnNewPromoCodeId(codes);
    }
}
