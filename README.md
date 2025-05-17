# Jakarta EE with Tomcat 9: JSF + Hot Reloading Setup

This guide walks you through setting up a Jakarta EE project with **JSF 2.2**, running on **Tomcat 9**, using **Docker**, **Maven**, and a **VS Code dev container**. The environment supports hot-reloading of frontend and backend code during development.

---

### 🐳 1. Dev Container Environment (Docker + VS Code)

This project uses a [dev container](.devcontainer/devcontainer.json) for a consistent and isolated development environment. The container includes:

* **Maven**
* **OpenJDK 8**
* **Apache Tomcat 9.0.105**
* **Watchexec** (for file monitoring)

**Dev container highlights:**

* See [.devcontainer/Dockerfile](.devcontainer/Dockerfile) for setup steps.
* Tomcat is installed at `/opt/tomcat` inside the container.
* Port **8080** is exposed for browser access.

**To get started:**

1. Open the project in **VS Code**.
2. When prompted, click **“Reopen in Container.”**

---

### 👤 2. Customizing Tomcat Users

To access the Tomcat manager:

1. Edit [`tomcat-users.xml`](tomcat-users.xml) as needed.
2. Copy it into the container:

   ```bash
   cp tomcat-users.xml /opt/tomcat/conf/tomcat-users.xml
   ```
3. Restart Tomcat:

   ```bash
   /opt/tomcat/bin/catalina.sh run
   ```

---

### 📦 3. Exploded WAR Deployment for Hot Reload

Maven is configured to deploy the application in exploded format directly into Tomcat’s `webapps` directory (`/opt/tomcat/webapps/hello-jakarta/`), enabling faster reload during development.

Key details:

* Configured via `maven-war-plugin` in [`pom.xml`](pom.xml)
* `web.xml` is required and included
* JSF `.xhtml` pages and Java beans are watched and recompiled

**To build and deploy:**

```bash
mvn clean compile war:exploded
```

---

### 🔁 4. Hot Reload with File Watcher

The project supports near-instant hot reloads using two scripts.

#### ✅ `watch.sh`

```bash
./watch.sh
```

This script runs [`watchexec`](https://github.com/watchexec/watchexec) to watch for file changes (`.java`, `.xhtml`, etc.). It triggers the `rebuild.sh` script when changes are detected.

#### ✅ `rebuild.sh`

```bash
#!/bin/bash
mvn compile war:exploded
touch /opt/tomcat/webapps/hello-jakarta/WEB-INF/web.xml
```

This script:

* Rebuilds and explodes the WAR into Tomcat’s `webapps` folder
* **Triggers Tomcat to reload the app** by updating the timestamp on `web.xml`

> ℹ️ `touch` does not change file contents — it only updates the file's modified time, which is enough for Tomcat to reload the app context and pick up updated `.class` files.

#### ✅ Start Tomcat in another terminal

```bash
/opt/tomcat/bin/catalina.sh run
```

Once both scripts are running:

* Edit `.xhtml` or `.java` files
* Save your changes
* Refresh the browser — no Tomcat restart needed

---

### 🌐 5. Accessing the App

Open in browser:

```
http://localhost:8080/hello-jakarta/index.xhtml
```

The page renders a JSF form with a text input and a checkbox, backed by a `@ManagedBean`. The form can only be submitted if:

* The text field is filled
* The checkbox is checked

---

### 📁 Project Structure

| Path                                           | Purpose                                  |
| ---------------------------------------------- | ---------------------------------------- |
| `src/main/java/com/nas/recovery/UserForm.java` | JSF backing bean (`@ManagedBean`)        |
| `src/main/webapp/index.xhtml`                  | JSF frontend form                        |
| `src/main/webapp/WEB-INF/web.xml`              | JSF servlet and navigation config        |
| `rebuild.sh`                                   | Rebuild + force Tomcat reload            |
| `watch.sh`                                     | Watches for changes and triggers rebuild |
| `tomcat-users.xml`                             | Tomcat manager access control            |

---

### 🛠️ Notes

* `watch.sh` + `rebuild.sh` together create a **smooth near–hot reload** experience.
* This setup uses **JSF 2.2** with core features (no external JSF component libraries yet).
* If needed, you can extend the frontend using **PrimeFaces**, **RichFaces**, or similar libraries.
* Tomcat is installed **inside the dev container**, with no need for Docker Compose.

---