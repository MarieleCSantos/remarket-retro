package com.remarketretro.service;

import com.remarketretro.configuration.JwtRequestFilter;
import com.remarketretro.dao.CartDao;
import com.remarketretro.dao.ProductDao;
import com.remarketretro.dao.UserDao;
import com.remarketretro.entity.Cart;
import com.remarketretro.entity.Product;
import com.remarketretro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).get();
        String username = JwtRequestFilter.CURRENT_USER;

        User user = null;
        if (!username.equals(null)) {
            user = userDao.findById(username).get();
        }

        if (!product.equals(null) && !user.equals(null)) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }
}
