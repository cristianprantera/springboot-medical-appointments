# 🏥 Sistema de Turnos Médicos – Spring Boot

Aplicación web desarrollada con **Spring Boot**, **Thymeleaf** y **PostgreSQL** para la gestión de turnos médicos, doctores y pacientes.  
Permite realizar operaciones CRUD, manejar roles con acceso diferenciado (Administrador, Doctor y Paciente) y persistir datos en una base PostgreSQL.

---

## 🚀 Tecnologías utilizadas

- **Java 23**
- **Spring Boot 3.5.x**
- **PostgreSQL**
- **Thymeleaf**
- **Hibernate / JPA**
- **Maven**

---

## ⚙️ Configuración del entorno

Antes de ejecutar el proyecto, configurá las siguientes **variables de entorno** (Environment Variables):

| Variable        | Descripción                                     | Ejemplo                                           |
|----------------|--------------------------------------------------|--------------------------------------------------|
| `DB_URL`       | URL JDBC de conexión a PostgreSQL               | `jdbc:postgresql://localhost:5432/turnos_db`     |
| `DB_USERNAME`  | Usuario de la base de datos                     | `postgres`                                        |
| `DB_PASSWORD`  | Contraseña del usuario de la base de datos     | `tu_contraseña_postgres`                         |

### 🔧 En Eclipse / IntelliJ

1. Ir a **Run → Run Configurations → Environment (Variables)**  
2. Agregar las variables con sus correspondientes valores.  
3. Ejecutar el proyecto.

---

### 💻 En consola (Linux/Mac)

```bash
export DB_URL=jdbc:postgresql://localhost:5432/turnos_db
export DB_USERNAME=tu_usuario_postgres
export DB_PASSWORD=tu_contraseña_postgres
mvn spring-boot:run
````

### 💻 En Windows (CMD o PowerShell)

```cmd
set DB_URL=jdbc:postgresql://localhost:5432/turnos_db
set DB_USERNAME=tu_usuario_postgres
set DB_PASSWORD=tu_contraseña_postgres
mvn spring-boot:run
```

---

## 🧠 Cómo ejecutar el proyecto

1. Cloná el repositorio:

```bash
git clone https://github.com/cristianprantera/springboot-medical-appointments.git
```

2. Configurá las variables de entorno como se indica arriba.
3. Creá la base de datos en PostgreSQL:

```sql
CREATE DATABASE turnos_db;
```

4. Ejecutá la aplicación:

```bash
mvn spring-boot:run
```

5. Accedé desde el navegador a:
   👉 **[http://localhost:8080](http://localhost:8080)**

---

## 🧩 Funcionalidades principales

✅ CRUD de **Doctores**, **Pacientes**, **Turnos**, **Especialidades** y **Servicios**
🔐 Sistema de **roles**: Administrador, Doctor y Paciente
🧾 Asignación de turnos médicos con validaciones
📊 Persistencia de datos con PostgreSQL y JPA/Hibernate
💻 Interfaz web con Thymeleaf y Bootstrap

---

## ⚠️ Nota sobre la estructura de turnos (Appointments)

Actualmente, el sistema tiene una clase abstracta **`Appointment`** de la cual solamente hereda **`MedicalAppointment`**.
Está planificado implementar otro tipo de turnos administrativos (trámites, gestiones internas, etc.) que también heredará de `Appointment`.

---

## 🧑‍💻 Roles del sistema

| Rol               | Permisos principales                                        |
| ----------------- | ----------------------------------------------------------- |
| **Administrador** | Gestiona doctores, pacientes, servicios y todos los turnos. |
| **Doctor**        | Visualiza y administra sus turnos.                          |
| **Paciente**      | Solicita, consulta y cancela sus propios turnos.            |

---

## 🗃️ Base de datos

Asegurate de tener PostgreSQL corriendo y una base de datos llamada `turnos_db`:

```sql
CREATE DATABASE turnos_db;
```

---

## 👨‍💻 Autor

**Cristian Prantera**
Estudiante de **Licenciatura en Sistemas (UNLa)** – 3er año.
Enfocado en desarrollo **backend con Java y Spring Boot**, aplicando MVC, JPA/Hibernate y buenas prácticas.
🔗 [LinkedIn](https://www.linkedin.com/in/cristianprantera/) · [GitHub](https://github.com/cristianprantera)

---

💬 *Proyecto desarrollado como práctica personal para fortalecer conocimientos en backend con Java y Spring Boot.*
