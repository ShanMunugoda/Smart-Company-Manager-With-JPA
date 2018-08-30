/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.comp.business.custom.Impl;

import lk.ijse.comp.business.custom.BranchBO;
import lk.ijse.comp.dao.DAOFactory;
import lk.ijse.comp.dao.custom.BranchDAO;
import lk.ijse.comp.db.JpaUtil;
import lk.ijse.comp.dto.BranchDTO;
import lk.ijse.comp.entity.Branch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

/**
 *
 * @author SDMROX
 */
public class BranchBOImpl implements BranchBO {
    
    private BranchDAO branchDAO;
    private SessionFactory sessionFactory;
    private EntityManagerFactory entityManagerFactory;
    public BranchBOImpl() {
        
        this.branchDAO=(BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DaoTypes.branch);
        entityManagerFactory = JpaUtil.getInstance().getEntityManagerFactory();
    }
    
    

    @Override
    public boolean saveBranch(BranchDTO dto) throws Exception {
        Branch brnh=new Branch(dto.getBid(), dto.getBname(), dto.getBaddress());
        EntityManager entityManager=entityManagerFactory.createEntityManager();
            branchDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            branchDAO.Save(brnh);
            entityManager.getTransaction().commit();
        entityManager.close();

       return true;
        
    }

    @Override
    public boolean updateBranch(BranchDTO dto) throws Exception {

            Branch brnh = new Branch(dto.getBid(), dto.getBname(), dto.getBaddress());
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
            branchDAO.Update(brnh);

        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean deleteBranch(String id) throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

            branchDAO.Delete(id);

            entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    public ArrayList<BranchDTO> allBranch() throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
            branchDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            ArrayList<BranchDTO> arr = new ArrayList<>();
            List<Branch> allbranch = branchDAO.getAll();

            for (Branch branch : allbranch) {

                BranchDTO brnh = new BranchDTO(branch.getBid(), branch.getBname(), branch.getBaddress());
                arr.add(brnh);

            }
            entityManager.getTransaction().commit();
        entityManager.close();
            return arr;

    }

    @Override
    public BranchDTO findById(String bid) throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
            branchDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();

            Branch find = branchDAO.findById(bid);
            BranchDTO brnhdto = new BranchDTO(find.getBid(), find.getBname(), find.getBaddress());

        entityManager.getTransaction().commit();
        entityManager.close();
            return brnhdto;

    }
  
}
