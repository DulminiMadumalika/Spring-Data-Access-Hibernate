package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers() throws Exception {

            List<CustomerDTO> collect = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
            return collect;

    }

    public boolean saveCustomer(CustomerDTO dto) throws Exception {

            customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
            return true;

    }

    public boolean updateCustomer(CustomerDTO dto) throws Exception {

            customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
            return true;

    }

    public boolean removeCustomer(String id) throws Exception {

            customerDAO.delete(id);
            return true;

    }

}
