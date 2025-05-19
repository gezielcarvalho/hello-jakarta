package info.gezielcarvalho.beans;

import info.gezielcarvalho.entities.UserFormEntity;
import info.gezielcarvalho.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "userListBean")
@ViewScoped
public class UserListBean implements Serializable {
    private List<UserFormEntity> users;

    @PostConstruct
    public void init() {
        UserRepository repo = new UserRepository();
        users = repo.findAll();
    }

    public List<UserFormEntity> getUsers() {
        return users;
    }
}
