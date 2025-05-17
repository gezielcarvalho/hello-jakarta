## ✅ On the **Browser Side** (Client)

JSF uses **hidden fields**, **cookies**, and the **view state mechanism** to maintain state across requests.

### 🔹 1. **JSF ViewState (Hidden Field)**

When a JSF page (like `index.xhtml`) is rendered, you’ll see something like:

```html
<input type="hidden" name="javax.faces.ViewState" id="javax.faces.ViewState" value="..."/>
```

This hidden field holds a token (often Base64 encoded or a key into a server-side store) that maps to the **JSF component tree** at the moment the page was rendered.

> 💡 This allows JSF to track which button was clicked, what inputs were filled, and re-bind them to the server-side bean (`@ManagedBean`) upon postback.

---

### 🔹 2. **HTTP Cookies**

If the JSF implementation is configured to use **client-side state saving**, then all component tree info is stored in the page (in the `ViewState` field). Otherwise, with **server-side state saving** (default for most modern apps), JSF uses the `ViewState` value as a key to retrieve state stored in the session.

Cookies like `JSESSIONID` are used to bind the client to a server session.

---

## ✅ On the **Server Side** (API / Backend)

### 🔸 1. **JSF Managed Bean Scope**

Your `UserForm.java` is annotated with:

```java
@ManagedBean
@RequestScoped
```

So:

* ✅ A new instance of `UserForm` is created **on each HTTP request**
* ❌ Data in the bean does not persist beyond the request

> You can use other scopes like `@SessionScoped`, `@ViewScoped`, or `@ApplicationScoped` depending on your need.

| Scope                | Lifespan                                 |
| -------------------- | ---------------------------------------- |
| `@RequestScoped`     | New bean per request                     |
| `@ViewScoped`        | Lives across AJAX postbacks on same page |
| `@SessionScoped`     | Lives for the entire HTTP session        |
| `@ApplicationScoped` | Shared globally (rare for user data)     |

---

### 🔸 2. **HTTP Session**

Tomcat tracks sessions using the `HttpSession` object. JSF and servlets can store and retrieve data via:

```java
HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(true);
session.setAttribute("user", someObject);
```

JSF can also manage session state transparently when you use `@SessionScoped` beans.

---

## ✅ Summary

| Layer         | State Mechanism                            |
| ------------- | ------------------------------------------ |
| **Browser**   | Hidden `ViewState` field, Cookies          |
| **JSF Bean**  | Scope-based state (`@RequestScoped`, etc.) |
| **API Layer** | `HttpSession` + JSF context binding        |
| **Tomcat**    | Servlet session tracking (`JSESSIONID`)    |

