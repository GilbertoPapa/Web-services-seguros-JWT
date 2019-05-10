package br.com.gilbertopapa.wsjwt;

import br.com.gilbertopapa.wsjwt.dao.JPAUtil;
import br.com.gilbertopapa.wsjwt.dao.UsuarioDAO;
import javax.persistence.EntityManager;

public class Main {

    public static void main (String args[]){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
        entityManager.close();

     }
}
