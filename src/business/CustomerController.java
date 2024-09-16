package business;

import core.Helper;
import dao.CustomerDao;
import entity.Customer;
import java.util.ArrayList;
public class CustomerController {
    private final CustomerDao customerDAO = new CustomerDao();

    public ArrayList<Customer> findAll(){
        return this.customerDAO.findAll();
    }
    public boolean save(Customer customer){
        return this.customerDAO.save(customer);
    }
    public Customer getById(int id){
        return this.customerDAO.getById(id);
    }
    public boolean update(Customer customer){
        if (this.getById(customer.getId()) == null){
            Helper.showMessage("Record with ID" + customer.getId() + "could not be found");
            return false;
        }
        return this.customerDAO.update(customer);
    }
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMessage("Record with ID "+ id + "could not be found");
            return false;
        }
        return this.customerDAO.delete(id);
    }

    public ArrayList<Customer> filter(String name, Customer.TYPE type){
        String query = "SELECT * FROM public.customer";

        ArrayList<String> whereList = new ArrayList<>();

        if (name.length() > 0){
            whereList.add("name LIKE '%" + name + "%'");
        }

        if (type != null){
            whereList.add("type = '" + type + "'");
        }

        if (whereList.size() > 0){
            String whereQuery = String.join(" AND ", whereList);
            query += " WHERE " + whereQuery;
        }
        return this.customerDAO.query(query);
    }
}
