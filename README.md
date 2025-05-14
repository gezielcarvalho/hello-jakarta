# Jakarta EE 10 with Tomcat 9: Hot Reloading Setup

This guide helps you set up a Jakarta EE 10 project with Tomcat 9 for hot reloading.

### 🐳 1. **Docker-Based Dev Environment**

#### **`Dockerfile`**

```Dockerfile
FROM tomcat:9.0-jdk8

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy exploded WAR for hot reload
COPY target/hello-jakarta /usr/local/tomcat/webapps/hello-jakarta

EXPOSE 8080
CMD ["catalina.sh", "run"]
```

---

### 📁 2. **Enable Hot Reload (Exploded Deployment)**

Use Maven to generate an **exploded WAR directory**, not a `.war` file. Add this to `pom.xml`:

```xml
<build>
  ...
  <plugins>
    ...
    <plugin>
      <artifactId>maven-war-plugin</artifactId>
      <version>3.3.1</version>
      <configuration>
        <failOnMissingWebXml>false</failOnMissingWebXml>
        <warName>hello-jakarta</warName>
        <packagingExcludes>WEB-INF/web.xml</packagingExcludes>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Then build using:

```bash
mvn clean compile war:exploded
```

This creates `target/hello-jakarta/` instead of a `.war`, ready for hot reload in Tomcat.

---

### 🔁 4. **Auto-Rebuild with File Watcher**

Use a file watcher (like `mvn compile` running continuously) or an IDE like IntelliJ IDEA with **automatic build on save**. To automate rebuild:

#### Option A: Maven-based (good for simple cases)

```bash
mvn war:exploded -Dwatch
```

#### Option B: Use `docker-compose` for a bind mount

**`docker-compose.yml`**

```yaml
version: "3.8"
services:
  tomcat:
    build: .
    ports:
      - "8080:8080"
    volumes:
      - ./target/hello-jakarta:/usr/local/tomcat/webapps/hello-jakarta
```

Rebuild the app in Maven on the host, and Tomcat reloads files without restart.

---

### 🧪 5. **Development Workflow**

1. Run Tomcat in Docker:

   ```bash
   docker-compose up --build
   ```

2. In a separate terminal or via IDE:

   ```bash
   mvn clean compile war:exploded
   ```

3. Edit Java/JSP files → Save → Maven rebuilds → Docker auto-syncs → Tomcat reflects changes instantly.
