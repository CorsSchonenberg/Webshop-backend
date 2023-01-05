package IPRWC.Webshop.service;

import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.model.PromoCode;
import IPRWC.Webshop.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class ReturnNewIdService {

    public int returnNewUserId(ArrayList<User> users) {
        ArrayList<Long> idList = new ArrayList();
        for (int i = 0; i < users.size(); i++) {
            idList.add(Long.valueOf(users.get(i).getId()));
        }
        if (idList.isEmpty()) {
            return 1;
        }
        Collections.sort(idList);
        for (int i = 0; i < idList.size() + 1; i++) {
            if (idList.get(0) != 1) {
                return i + 1;
            }
            if (i + 1 < idList.size()) {
                int num = (int) (idList.get(i + 1) - idList.get(i));
                if (num != 1) {
                    return i + 2;
                }
            } else if (idList.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }

    public int returnNewPromoCodeId(ArrayList<PromoCode> promoCodes) {
        ArrayList<Long> idList = new ArrayList();
        for (int i = 0; i < promoCodes.size(); i++) {
            idList.add(Long.valueOf(promoCodes.get(i).getId()));
        }
        if (idList.isEmpty()) {
            return 1;
        }
        Collections.sort(idList);
        for (int i = 0; i < idList.size() + 1; i++) {
            if (idList.get(0) != 1) {
                return i + 1;
            }
            if (i + 1 < idList.size()) {
                int num = (int) (idList.get(i + 1) - idList.get(i));
                if (num != 1) {
                    return i + 2;
                }
            } else if (idList.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }

    public int returnNewProductId(ArrayList<Product> products) {
        ArrayList<Long> idList = new ArrayList();
        for (int i = 0; i < products.size(); i++) {
            idList.add(Long.valueOf(products.get(i).getId()));
        }
        if (idList.isEmpty()) {
            return 1;
        }
        Collections.sort(idList);
        for (int i = 0; i < idList.size() + 1; i++) {
            if (idList.get(0) != 1) {
                return i + 1;
            }
            if (i + 1 < idList.size()) {
                int num = (int) (idList.get(i + 1) - idList.get(i));
                if (num != 1) {
                    return i + 2;
                }
            } else if (idList.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }

    public int returnNewOrderId(ArrayList<Order> orders) {
        ArrayList<Long> idList = new ArrayList();
        for (int i = 0; i < orders.size(); i++) {
            idList.add(Long.valueOf(orders.get(i).getId()));
        }
        if (idList.isEmpty()) {
            return 1;
        }
        Collections.sort(idList);
        for (int i = 0; i < idList.size() + 1; i++) {
            if (idList.get(0) != 1) {
                return i + 1;
            }
            if (i + 1 < idList.size()) {
                int num = (int) (idList.get(i + 1) - idList.get(i));
                if (num != 1) {
                    return i + 2;
                }
            } else if (idList.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }
}
