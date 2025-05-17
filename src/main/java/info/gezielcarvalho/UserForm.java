package info.gezielcarvalho;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserForm {
    private String name;
    private boolean agreed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public String submit() {
        if (name != null && !name.trim().isEmpty() && agreed) {
            return "success"; // success.xhtml
        }
        return "failure"; // failure.xhtml
    }
}
