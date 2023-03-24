package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.PromoCodeDao;
import IPRWC.Webshop.dao.UserDao;
import IPRWC.Webshop.model.*;
import IPRWC.Webshop.service.CheckIfPromoCodeISValid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserDao userDao;

    public PromoCodeController(PromoCodeDao promoCodeDao, CheckIfPromoCodeISValid checkIfPromoCodeISValid, UserDao userDao) {
        this.promoCodeDao = promoCodeDao;
        this.checkIfPromoCodeISValid = checkIfPromoCodeISValid;
        this.userDao = userDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Order>> promoCodes() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to get all promocodes");
        }
        ArrayList<PromoCode> promoCodes = this.promoCodeDao.getAllCodes();
        return new ApiResponse(HttpStatus.ACCEPTED, promoCodes);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postPromoCodes(@RequestBody PromoCode promoCode) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to add new promocodes");
        }
        this.promoCodeDao.saveToDatabase(promoCode);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse deletePromoCode(@PathVariable Integer id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to delete promocodes");
        }
        this.promoCodeDao.deleteCodeFromDatabase(id);
        return new ApiResponse(HttpStatus.ACCEPTED, "You deleted some data!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updatePromoCode(@RequestBody PromoCode promoCode) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to update promocodes");
        }
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
