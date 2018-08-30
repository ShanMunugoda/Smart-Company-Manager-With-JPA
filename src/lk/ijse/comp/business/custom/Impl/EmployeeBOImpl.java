/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.comp.business.custom.Impl;

import lk.ijse.comp.business.custom.EmployeeBO;
import lk.ijse.comp.dao.DAOFactory;
import lk.ijse.comp.dao.custom.BranchDAO;
import lk.ijse.comp.dao.custom.EmployeeDAO;
import lk.ijse.comp.db.JpaUtil;
import lk.ijse.comp.dto.BranchDTO;
import lk.ijse.comp.dto.EmployeeDTO;
import lk.ijse.comp.entity.Branch;
import lk.ijse.comp.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDMROX
 */
public class EmployeeBOImpl implements EmployeeBO {
    
    private EmployeeDAO employeeDAO;
    private BranchDAO branchDAOImpl;
    private EntityManagerFactory entityManagerFactory;

    public EmployeeBOImpl() {
        this.employeeDAO=(EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.employee);
        this.branchDAOImpl=(BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.branch);
        entityManagerFactory =JpaUtil.getInstance().getEntityManagerFactory();

    }
    
    

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
            Employee emp = new Employee(dto.getEid(), dto.getEname(), dto.getEaddress(), new Branch(dto.getBid()));
            employeeDAO.Save(emp);
        entityManager.getTransaction().commit();
        entityManager.close();

            return true;

    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
//       Employee emp=new Employee(dto.getEid(), dto.getEname(),dto.getEaddress(),new Branch(dto.getBid()));
//        boolean rst = employeeDAO.Update(emp);
//        return rst;
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
            employeeDAO.Delete(id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return true;


    }

    @Override
    public ArrayList<EmployeeDTO> allEmployee() throws Exception {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

            ArrayList<EmployeeDTO> arr = new ArrayList<>();
            List<Employee> allemp = employeeDAO.getAll();

            for (Employee employee : allemp) {
                EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEid(), employee.getEname(), employee.getEaddress(), employee.getBranch().getBid());
                arr.add(employeeDTO);

            }
            entityManager.getTransaction().commit();
            entityManager.close();


            return arr;

        }




    @Override
    public EmployeeDTO findById(String eid) throws Exception {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

            Employee find = employeeDAO.findById(eid);
            EmployeeDTO employeedto = new EmployeeDTO(find.getEid(), find.getEname(), find.getEaddress(), find.getBranch().getBid());
            entityManager.getTransaction().commit();
        entityManager.close();

            return employeedto;
        }


    @Override
    public ArrayList<BranchDTO> allBids() throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

            List<Branch> branch = branchDAOImpl.getAll();
            ArrayList<BranchDTO> arr = new ArrayList<>();
            for (Branch branch1 : branch) {
                BranchDTO b = new BranchDTO(branch1.getBid());
                arr.add(b);

            }
        entityManager.getTransaction().commit();
        entityManager.close();
            return arr;

    }
}
