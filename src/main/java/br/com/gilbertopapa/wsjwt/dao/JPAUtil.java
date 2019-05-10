package br.com.gilbertopapa.wsjwt.dao;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {

    public static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("imoveis");
        }
        return emf.createEntityManager();
    }

}
