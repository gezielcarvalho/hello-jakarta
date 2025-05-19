package info.gezielcarvalho.repositories;

import info.gezielcarvalho.entities.UserFormEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    public List<UserFormEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM UserFormEntity u", UserFormEntity.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public void save(UserFormEntity user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            UserFormEntity u = em.find(UserFormEntity.class, id);
            if (u != null) em.remove(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
