package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sf;

    @Override
    public Session getSession() {
        return sf.getCurrentSession();
    }

    public void save(Order order) throws Exception{
        getSession().save(order);
    }

    public void update(Order order)throws Exception{
        getSession().merge(order);
    }

    public void delete(Integer id)throws Exception{
        getSession().delete(getSession().load(Order.class, id));
    }

    public Order find(Integer id) throws Exception{
        return getSession().find(Order.class, id);
    }

    public List<Order> findAll() throws Exception{
        return getSession().createQuery("FROM lk.ijse.pos.entity.Order",Order.class).list();
    }

    @Override
    public int getLastOrderId() throws Exception {
        Integer integer = getSession().createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1", Integer.class).uniqueResult();
        return integer;
    }
}
