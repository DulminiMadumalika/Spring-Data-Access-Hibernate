package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sf;

    @Override
    public Session getSession() {
        return sf.getCurrentSession();
    }

    public void save(Customer customer) throws Exception {
        getSession().save(customer);
    }

    public void update(Customer customer) throws Exception {
        getSession().merge(customer);
    }

    public void delete(String id) throws Exception {
        getSession().delete(getSession().load(Customer.class, id));
    }

    public Customer find(String id) throws Exception {
        Customer customer = getSession().find(Customer.class, id);
        System.out.println(customer);
        return customer;
    }

    public List<Customer> findAll() throws Exception {
        return getSession().createQuery("FROM lk.ijse.pos.entity.Customer").list();
    }

    @Override
    public int count() throws Exception {
        return getSession().createNativeQuery("SELECT COUNT(*) FROM Customer", Integer.class)
                .uniqueResult();
    }

}
