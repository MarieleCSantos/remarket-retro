package com.remarketretro.service;

import com.remarketretro.configuration.JwtRequestFilter;
import com.remarketretro.dao.OrderDetailDao;
import com.remarketretro.dao.ProductDao;
import com.remarketretro.dao.UserDao;
import com.remarketretro.entity.OrderDetail;
import com.remarketretro.entity.OrderInput;
import com.remarketretro.entity.OrderProductQuantity;
import com.remarketretro.entity.Product;
import com.remarketretro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public void placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity orderProductQuantity : productQuantityList) {
            Product product = null;
            if (productDao.findById(orderProductQuantity.getProductId()).isPresent()) {
                product = productDao.findById(orderProductQuantity.getProductId()).get();
            }

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = null;
            if (userDao.findById(currentUser).isPresent()) {
                user = userDao.findById(currentUser).get();

            }

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    Objects.requireNonNull(product).getProductDiscountedPrice() * orderProductQuantity.getQuantity(),
                    product,
                    user
            );

            orderDetailDao.save(orderDetail);
        }
    }
}
