## ✅ How `#{userForm}` Works in JSF

When you use `#{userForm}` in `index.xhtml`, you're referencing a **Managed Bean** named `userForm`. JSF resolves that expression via **EL (Expression Language)** and the **JSF Managed Bean Facility**.

---

### ✅ Behind the Scenes

When JSF processes the page, it goes through these steps:

1. **EL Resolver** sees `#{userForm}` in the XHTML.
2. It looks for a bean named `userForm` in one of the configured scopes (`Request`, `Session`, `View`, etc.).
3. If no existing instance is found:

   * It checks for a matching class annotated with `@ManagedBean(name="userForm")` *(or infers the name from the class name)*.
   * It instantiates the class and stores it in the correct scope.
4. Now it binds the field values (`#{userForm.name}`, `#{userForm.agreed}`) to the bean properties.

---

### ✅ Your Code (Implied Instantiation)

#### Java Bean:

```java
@ManagedBean
@RequestScoped
public class UserForm {
    private String name;
    private boolean agreed;

    // getters/setters...
}
```

#### XHTML View:

```xhtml
<h:inputText value="#{userForm.name}" />
<h:selectBooleanCheckbox value="#{userForm.agreed}" />
```

✅ The `@ManagedBean` annotation **registers** this bean **with the name `userForm`**, and `@RequestScoped` tells JSF to **create a new instance for each request**.

> 💡 If no `name` is explicitly given in `@ManagedBean(name = "...")`, JSF uses the uncapitalized class name: `UserForm` → `userForm`.

---

## 🔍 Where the Bean Lives

The bean is stored in a scope:

* `@RequestScoped` → one per HTTP request
* `@SessionScoped` → one per user session
* `@ViewScoped` → one per page view (including AJAX postbacks)
* `@ApplicationScoped` → one per app lifetime

---

## ✅ Summary

| Mechanism             | Behavior                                             |
| --------------------- | ---------------------------------------------------- |
| `@ManagedBean`        | Registers the class for use in EL (`#{userForm}`)    |
| EL expression         | Resolves to bean, invokes getters/setters            |
| Bean scope annotation | Controls bean lifecycle (per request, session, etc.) |
| Instantiation trigger | Happens automatically when the page is rendered      |
