
import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Student student = new Student("Koce");

        em.persist(student);

        em.getTransaction().commit();

        em.close();

    }
}
