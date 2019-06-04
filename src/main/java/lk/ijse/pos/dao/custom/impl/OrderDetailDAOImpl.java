package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.entity.OrderDetailPK;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Autowired
    private SessionFactory sf;

    @Override
    public Session getSession() {
        return sf.getCurrentSession();
    }

    public void save(OrderDetail orderDetail) throws Exception {
        getSession().save(orderDetail);
    }

    public void update(OrderDetail orderDetail) throws Exception {
        getSession().merge(orderDetail);
    }

    public void delete(OrderDetailPK id) throws Exception {
        getSession().delete(getSession().load(OrderDetail.class, id));
    }

    public OrderDetail find(OrderDetailPK id) throws Exception {
        return getSession().find(OrderDetail.class, id);
    }

    public List<OrderDetail> findAll() throws Exception {
        return getSession().createQuery("FROM OrderDetail", OrderDetail.class).list();
    }

}
