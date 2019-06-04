package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.OrderBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CustomerDAO customerDAO;

    public boolean placeOrder(OrderDTO order) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = false;

            Customer customer = customerDAO.find(order.getCustomerId());
            orderDAO.save(new Order(order.getOrderId(), order.getOrderDate(),customer));

//            if (!result) {
//                connection.rollback();
//                return false;
//            }


            for (OrderDetailDTO dto : order.getOrderDetails()) {

                orderDetailDAO.save(new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice()));

//                if (!result) {
//                    connection.rollback();
//                    return false;
//                }

                Item item = itemDAO.find(dto.getItemCode());
                int qty = item.getQtyOnHand() - dto.getQty();
                item.setQtyOnHand(qty);
                itemDAO.update(item);

//                if (!result) {
//                    connection.rollback();
//                    return false;
//                }
            }

            session.getTransaction().commit();
            return true;

    }

    public int generateOrderId() throws Exception {
        try {
            return orderDAO.getLastOrderId() + 1;
        } catch (NullPointerException e) {
            return 1;
        }
    }

}
