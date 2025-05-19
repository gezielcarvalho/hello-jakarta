package info.gezielcarvalho.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_form")
public class UserFormEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "terms_accepted")
    private boolean termsAccepted;

    @Column(name = "created_at", updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "UserFormEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", termsAccepted=" + termsAccepted +
                ", createdAt=" + createdAt +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFormEntity)) return false;

        UserFormEntity that = (UserFormEntity) o;

        if (termsAccepted != that.termsAccepted) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return createdAt.equals(that.createdAt);
    }
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (termsAccepted ? 1 : 0);
        result = 31 * result + createdAt.hashCode();
        return result;
    }
    public UserFormEntity() {
    }
    public UserFormEntity(String name, boolean termsAccepted) {
        this.name = name;
        this.termsAccepted = termsAccepted;
    }
    public UserFormEntity(Long id, String name, boolean termsAccepted) {
        this.id = id;
        this.name = name;
        this.termsAccepted = termsAccepted;
    }
    public UserFormEntity(Long id, String name, boolean termsAccepted, java.sql.Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.termsAccepted = termsAccepted;
        this.createdAt = createdAt;
    }
    public UserFormEntity(String name, boolean termsAccepted, java.sql.Timestamp createdAt) {
        this.name = name;
        this.termsAccepted = termsAccepted;
        this.createdAt = createdAt;
    }
    public UserFormEntity(Long id) {
        this.id = id;
    }
    public UserFormEntity(String name) {
        this.name = name;
    }
    public UserFormEntity(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }
    public UserFormEntity(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public UserFormEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public UserFormEntity(Long id, boolean termsAccepted) {
        this.id = id;
        this.termsAccepted = termsAccepted;
    }
    public UserFormEntity(String name, java.sql.Timestamp createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }
    public UserFormEntity(boolean termsAccepted, java.sql.Timestamp createdAt) {
        this.termsAccepted = termsAccepted;
        this.createdAt = createdAt;
    }

}
