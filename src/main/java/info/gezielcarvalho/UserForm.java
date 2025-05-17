package info.gezielcarvalho;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*; // For EntityManager, etc.
import info.gezielcarvalho.entities.UserFormEntity; // Assuming this is the correct package for UserFormEntity

@ManagedBean
@SessionScoped
public class UserForm implements Serializable {

    private String name;
    private boolean termsAccepted;

    public String submit() {
        if (name != null && !name.isEmpty() && termsAccepted) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                UserFormEntity entity = new UserFormEntity();
                entity.setName(name);
                entity.setTermsAccepted(termsAccepted);
                em.persist(entity);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                e.printStackTrace();
            } finally {
                em.close();
                emf.close();
            }

            return "success.xhtml";
        }
        return "index.xhtml";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isTermsAccepted() {
        return termsAccepted;
    }
    public void setTermsAccepted(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }
}

