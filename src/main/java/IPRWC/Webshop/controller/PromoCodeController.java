package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.ProductDao;
import IPRWC.Webshop.dao.ProductRepository;
import IPRWC.Webshop.dao.PromoCodeDao;
import IPRWC.Webshop.model.ApiResponse;
import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.model.PromoCode;
import IPRWC.Webshop.service.CheckIfPromoCodeISValid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v1/promocode")
@CrossOrigin("*")
public class PromoCodeController {
    private final PromoCodeDao promoCodeDao;
    private final CheckIfPromoCodeISValid checkIfPromoCodeISValid;

    public PromoCodeController(PromoCodeDao promoCodeDao,
                               CheckIfPromoCodeISValid checkIfPromoCodeISValid) {
        this.promoCodeDao = promoCodeDao;
        this.checkIfPromoCodeISValid = checkIfPromoCodeISValid;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Order>> promoCodes() {
        ArrayList<PromoCode> promoCodes = this.promoCodeDao.getAllCodes();
        return new ApiResponse(HttpStatus.ACCEPTED, promoCodes);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postPromoCodes(@RequestBody PromoCode promoCode) {
        this.promoCodeDao.saveToDatabase(promoCode);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse deletePromoCode(@PathVariable Integer id) {
        this.promoCodeDao.deleteCodeFromDatabase(id);
        return new ApiResponse(HttpStatus.ACCEPTED, "You deleted some data!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updatePromoCode(@RequestBody PromoCode promoCode) {
        this.promoCodeDao.saveToDatabase(promoCode);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated some data!");
    }

    @RequestMapping(value = "/checker", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse individualCode(@RequestBody String code){
        Optional<PromoCode> validPromoCode = this.checkIfPromoCodeISValid.checkIfPromoCodeIsValid(code);
        if (validPromoCode.isPresent()) {
            return new ApiResponse(HttpStatus.ACCEPTED, validPromoCode);
        } else {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid promocode!");
        }

    }
}
