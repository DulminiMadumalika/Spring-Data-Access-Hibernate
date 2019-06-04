package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
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
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sf;

    @Override
    public Session getSession() {
        return sf.getCurrentSession();
    }

    public void save(Item item) throws Exception{
        getSession().save(item);
    }

    public void update(Item item)throws Exception{
        getSession().merge(item);
    }

    public void delete(String code)throws Exception{
        getSession().delete(getSession().load(Item.class, code));
    }

    public Item find(String code) throws Exception{
        return getSession().find(Item.class, code);
    }

    public List<Item> findAll() throws Exception{
        return getSession().createQuery("FROM Item", Item.class).list();
    }

}
