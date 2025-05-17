# Jakarta EE with Tomcat 9: JSF + Hot Reloading + MySQL Setup

This guide walks you through setting up a Jakarta EE project with **JSF 2.2**, running on **Tomcat 9**, using **Docker**, **Maven**, and a **VS Code dev container**. It includes **MySQL** and **phpMyAdmin** as supporting services and supports hot-reloading of frontend and backend code during development.

---

### 🐳 1. Dev Container Setup (Docker + VS Code)

This project uses a [dev container](.devcontainer/devcontainer.json) for a consistent and isolated development environment. It includes:

* **Maven**
* **OpenJDK 8**
* **Apache Tomcat 9.0.105**
* **Watchexec** (for file watching)
* **MySQL 8.0** (as a separate service)
* **phpMyAdmin**

**Highlights:**

* Defined with Docker Compose (`docker-compose.yml`)
* Workspace mounted at `/workspaces/hello-jakarta`
* Tomcat installed at `/opt/tomcat`
* Ports:

  * `8080`: Tomcat app
  * `8081`: phpMyAdmin UI

**To get started:**

1. Open the project in **VS Code**.
2. VS Code will detect `.devcontainer/devcontainer.json` and prompt you to **“Reopen in Container.”**

---

### ⚙️ 2. Tomcat User and Manager Access

**Preconfigured in the image:**

* [`overrides/tomcat-users.xml`](overrides/tomcat-users.xml) is copied automatically to `/opt/tomcat/conf/tomcat-users.xml`
* To enable remote access to the manager, [`overrides/context.xml`](overrides/context.xml) is copied to the correct location inside Tomcat.

**Default Credentials:**

```xml
<user username="admin" password="A!234567a" roles="manager-gui"/>
```

Access the manager at:

```
http://localhost:8080/manager/html
```

---

### 📦 3. Exploded WAR Deployment for Hot Reload

Maven is configured to output the WAR in **exploded format** directly to Tomcat’s `webapps` folder (`/opt/tomcat/webapps/hello-jakarta/`), enabling fast reload.

**To build and deploy:**

```bash
mvn clean compile war:exploded
```

---

### 🔁 4. Hot Reload with File Watcher

Two scripts make reload seamless:

#### ✅ `watch.sh`

```bash
./watch.sh
```

Watches for changes (`.java`, `.xhtml`, etc.) and triggers `rebuild.sh`.

#### ✅ `rebuild.sh`

```bash
#!/bin/bash
mvn compile war:exploded
touch /opt/tomcat/webapps/hello-jakarta/WEB-INF/web.xml
```

* Rebuilds the app
* Touches `web.xml` to trigger Tomcat context reload

#### ✅ Run Tomcat

```bash
/opt/tomcat/bin/catalina.sh run
```

> ℹ️ To keep the container running without auto-starting Tomcat, the image uses `tail -f /dev/null` as entrypoint. This allows manual Tomcat control.

---

### 🧩 5. MySQL & phpMyAdmin

**MySQL**

* Hostname: `db`
* Port: `3306` (not exposed externally)
* User: `root`
* Password: `secret`
* Default database: `jakartadb`

**phpMyAdmin**

* URL: [http://localhost:8081](http://localhost:8081)
* Login:

  * Server: `db`
  * User: `root`
  * Password: `secret`

Defined in [`docker-compose.yml`](docker-compose.yml).

---

### 🌐 6. Accessing the App

Visit the JSF form at:

```
http://localhost:8080/hello-jakarta/index.xhtml
```

* Text field is **required**
* Checkbox must be **checked**
* Submit is enabled only if both are valid

---

### 📁 Project Structure

| Path                                           | Purpose                                  |
| ---------------------------------------------- | ---------------------------------------- |
| `src/main/java/com/nas/recovery/UserForm.java` | JSF backing bean (`@ManagedBean`)        |
| `src/main/webapp/index.xhtml`                  | JSF form view                            |
| `src/main/webapp/WEB-INF/web.xml`              | JSF config and servlet mapping           |
| `rebuild.sh`                                   | Rebuild WAR + trigger reload             |
| `watch.sh`                                     | Monitors source for changes              |
| `overrides/context.xml`                        | Enables remote access to `/manager`      |
| `overrides/tomcat-users.xml`                   | Defines manager user                     |
| `.devcontainer/Dockerfile`                     | Container setup (Maven, Tomcat, watcher) |
| `docker-compose.yml`                           | Defines app, MySQL, and phpMyAdmin       |
