package info.gezielcarvalho.beans;

import info.gezielcarvalho.entities.UserFormEntity;
import info.gezielcarvalho.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userListBean")
@ViewScoped
public class UserListBean implements Serializable {
    private List<UserFormEntity> users;
    private UserFormEntity newUser;

    @PostConstruct
    public void init() {
        UserRepository repo = new UserRepository();
        users = repo.findAll();
        newUser = new UserFormEntity(); // Initialize empty user
    }

    public void addUser() {
        if (newUser.getName() == null || newUser.getName().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is required", null));
            return;
        }

        if (users.size() >= 20) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "User limit reached (20)", null));
            return;
        }
        UserRepository repo = new UserRepository();
        repo.save(newUser);
        users = repo.findAll();  // Refresh list
        newUser = new UserFormEntity();  // Reset form
    }

    public List<UserFormEntity> getUsers() {
        return users;
    }

    public UserFormEntity getNewUser() {
        return newUser;
    }

    public void setNewUser(UserFormEntity newUser) {
        this.newUser = newUser;
    }
}

