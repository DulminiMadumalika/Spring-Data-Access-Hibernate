package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemBOImpl implements ItemBO {

    @Autowired
    private ItemDAO itemDAO;

    public List<ItemDTO> getAllItems() throws Exception {

            List<ItemDTO> collect = itemDAO.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
            return collect;

        //        itemDAO.findAll().stream().map(new Function<Item, ItemDTO>() {
//            @Override
//            public ItemDTO apply(Item item) {
//                return new ItemDTO(item.getCode(), item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
//            }
//        }).collect(Collectors.toList());

    }

    public boolean saveItem(ItemDTO item) throws Exception {

            itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            return true;

    }

    public boolean updateItem(ItemDTO item) throws Exception {

            itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            return true;

    }

    public boolean deleteItem(String code) throws Exception {

            itemDAO.delete(code);
            return true;

    }

}
