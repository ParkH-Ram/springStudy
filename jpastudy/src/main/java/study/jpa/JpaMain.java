package study.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = emf.createEntityManager();

        //실제 끝나면 닫아줘야 한다.
        entityManager.close();
        emf.close();
    }
}