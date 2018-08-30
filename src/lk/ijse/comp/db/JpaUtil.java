package lk.ijse.comp.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JpaUtil {
  private static EntityManagerFactory entityManagerFactory;
  private static JpaUtil jpaUtil;


  private JpaUtil(){
Properties jpaProperties=new Properties();

      try {
          File file = new File("application.properties");
          FileReader fileReader = new FileReader(file);
          jpaProperties.load(fileReader);

      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }


      entityManagerFactory=Persistence.createEntityManagerFactory("boot",jpaProperties);
  }


  public static JpaUtil getInstance(){
      if(jpaUtil==null){
          jpaUtil = new JpaUtil();
      }
      return jpaUtil;
  }

  public EntityManagerFactory getEntityManagerFactory(){
      return entityManagerFactory;
  }

};

