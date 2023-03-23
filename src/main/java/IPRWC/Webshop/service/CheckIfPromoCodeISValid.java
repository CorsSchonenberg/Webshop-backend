package IPRWC.Webshop.service;

import IPRWC.Webshop.dao.PromoCodeDao;
import IPRWC.Webshop.model.PromoCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CheckIfPromoCodeISValid {
    private final PromoCodeDao promoCodeDao;

    public CheckIfPromoCodeISValid(PromoCodeDao promoCodeDao) {
        this.promoCodeDao = promoCodeDao;
    }

    public Optional<PromoCode> checkIfPromoCodeIsValid(String code) {
        ArrayList<PromoCode> promoCodes = this.promoCodeDao.getAllCodes();

        for (PromoCode promoCode : promoCodes) {
            if (promoCode.getCode().equals(code)) {
                return Optional.of(promoCode);
            }
        }
        return null;
    }
}
