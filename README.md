# Jakarta EE with Tomcat 9: Hot Reloading Setup

This guide walks you through setting up a Jakarta EE project with **Tomcat 9**, using **Docker**, **Maven**, and a **VS Code dev container** for near hot-reloading support.

---

### 🐳 1. Dev Container Environment (Docker + VS Code)

This project uses a [dev container](.devcontainer/devcontainer.json) for a consistent and isolated development environment. The container includes:

* **Maven**
* **OpenJDK 8**
* **Apache Tomcat 9.0.105**

**Dev container highlights:**

* See [.devcontainer/Dockerfile](.devcontainer/Dockerfile) for the setup.
* Tomcat is installed at `/opt/tomcat` inside the container.
* Port **8080** is exposed for access via your browser.

**To get started:**

1. Open the project in **VS Code**.
2. When prompted, click **“Reopen in Container.”**

---

### 👤 2. Customizing Tomcat Users

To add or update Tomcat manager users:

1. Edit [`tomcat-users.xml`](tomcat-users.xml) in the project.
2. Copy it into the container:

   ```bash
   cp tomcat-users.xml /opt/tomcat/conf/tomcat-users.xml
   ```
3. Restart Tomcat for changes to take effect.

---

### 📦 3. Exploded WAR Deployment for Hot Reload

Maven is configured to output an **exploded WAR** directly to Tomcat’s deployment folder (`/opt/tomcat/webapps/hello-jakarta/`) for faster redeploys.

Key config:

* The `maven-war-plugin` in [`pom.xml`](pom.xml) handles this.
* `web.xml` is required and included in `WEB-INF`.

**Build and deploy with:**

```bash
mvn clean compile war:exploded
```

---

### 🔁 4. Development Workflow (Manual Hot Reload)

After starting the container:

1. **Start the file watcher:**

   ```bash
   ./watch.sh
   ```

   This script uses `watchexec` to monitor source files and automatically recompile and redeploy on changes.

2. **Start Tomcat in another terminal:**

   ```bash
   catalina.sh run
   ```

   or

   ```bash
   /opt/tomcat/bin/catalina.sh run
   ```

3. **Workflow:**

   * Make changes to `.java`, `.jsp`, or static files.
   * Save the file — `watchexec` triggers a rebuild.
   * Refresh the browser — no need to restart Tomcat.

---

### 🌐 5. Accessing the App

Visit the app at:

```
http://localhost:8080/hello-jakarta/
```

The landing page (`index.jsp`) links to a simple servlet at `/hello`.

---

### 📁 Project Structure Overview

| Path                                               | Purpose                        |
| -------------------------------------------------- | ------------------------------ |
| `src/main/java/com/nas/recovery/HelloServlet.java` | Example Servlet implementation |
| `src/main/webapp/index.jsp`                        | JSP welcome page               |
| `src/main/webapp/WEB-INF/web.xml`                  | Servlet mapping config         |
| `tomcat-users.xml`                                 | Tomcat manager access control  |

---

### 🛠️ Notes

* `watch.sh` enables a development flow **very close to hot reload** — no Tomcat restart needed.
* `tomcat-users.xml` must be manually copied and applied.
* **Tomcat runs inside the dev container**; no need for Docker Compose unless you want external orchestration.
* If using Docker Compose, mount your WAR or exploded directory manually.
