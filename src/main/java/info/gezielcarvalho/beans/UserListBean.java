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
    private UserFormEntity selectedUser = new UserFormEntity();

    @PostConstruct
    public void init() {
        UserRepository repo = new UserRepository();
        users = repo.findAll();
        newUser = new UserFormEntity(); // Initialize empty user
    }

    public UserFormEntity getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserFormEntity selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isAnyUserDisagreed() {
        return users.stream().anyMatch(user -> !Boolean.TRUE.equals(user.isTermsAccepted()));
    }

    public void save() {
        UserRepository repo = new UserRepository();
        if (selectedUser.getId() == null) {
            repo.save(selectedUser); // insert
        } else {
            repo.update(selectedUser); // update
        }
        selectedUser = new UserFormEntity(); // clear form
        users = repo.findAll(); // refresh table
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

    public void deleteUser(Long id) {
        UserRepository repo = new UserRepository();
        repo.delete(id);
        users = repo.findAll(); // Refresh list
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

