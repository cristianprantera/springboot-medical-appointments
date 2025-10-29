
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

| Variable | Descripción | Ejemplo |
|-----------|--------------|----------|
| `DB_URL` | URL JDBC de conexión a la base de datos PostgreSQL | `jdbc:postgresql://localhost:5432/turnos` |
| `USERNAME` | Usuario de la base de datos | `postgres` |
| `PASSWORD` | Contraseña de la base de datos | `12345` |

### 🔧 En Eclipse
1. Ir a **Run → Run Configurations → Spring Boot App → TurnosApplication → Environment**
2. Agregar las variables indicadas arriba (Name / Value).
3. Ejecutar el proyecto con **Run ▶️**

### 💻 En consola (Linux/Mac)
```bash
export DB_URL=jdbc:postgresql://localhost:5432/turnos
export USERNAME=postgres
export PASSWORD=12345
mvn spring-boot:run
````

### 💻 En Windows (CMD o PowerShell)

```cmd
set DB_URL=jdbc:postgresql://localhost:5432/turnos
set USERNAME=postgres
set PASSWORD=12345
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
   CREATE DATABASE turnos;
   ```
4. Ejecutá el proyecto con:

   ```bash
   mvn spring-boot:run
   ```
5. Accedé desde el navegador a:
   👉 [http://localhost:8080](http://localhost:8080)

---

## 🧩 Funcionalidades principales

* ✅ CRUD de **Doctores**, **Pacientes**, **Turnos**, **Especialidades** y **Servicios**.
* 🔐 Sistema de **roles**: Administrador, Doctor y Paciente.
* 🧾 Gestión de turnos médicos con relación entre entidades.
* 📊 Integración con base de datos PostgreSQL mediante JPA/Hibernate.
* 💻 Interfaz web dinámica con **Thymeleaf** y **Bootstrap**.

---

## ⚠️ Nota sobre la estructura de turnos (Appointments)

Actualmente, el sistema cuenta con una clase abstracta **`Appointment`** de la cual **solo hereda `MedicalAppointment`**, ya que el proyecto está en desarrollo.
Está planificado agregar un nuevo tipo de cita administrativa (por ejemplo, trámites o gestiones internas del centro médico), que también heredará de `Appointment`.
Esto permitirá manejar distintos tipos de turnos dentro de una misma jerarquía, aplicando herencia y polimorfismo en la capa de dominio.

---

## 🧑‍💻 Roles del sistema

| Rol               | Descripción                                                    |
| ----------------- | -------------------------------------------------------------- |
| **Administrador** | Crea, edita y elimina doctores, pacientes, servicios y turnos. |
| **Doctor**        | Visualiza y administra sus propios turnos.                     |
| **Paciente**      | Solicita, consulta y cancela turnos médicos.                   |

---


## 🧩 Base de datos

Asegurate de tener PostgreSQL corriendo en el puerto **5432** y una base de datos llamada `turnos`:

```sql
CREATE DATABASE turnos;
```

---

## 👨‍💻 Autor

**Cristian Prantera**
Estudiante de **Licenciatura en Sistemas (UNLa)** – 3er año
Enfocado en el desarrollo **backend con Java y Spring Boot**, aprendiendo arquitectura MVC, JPA/Hibernate y buenas prácticas en desarrollo de software.
🔗 [LinkedIn](https://www.linkedin.com/in/cristianprantera/) · [GitHub](https://github.com/cristianprantera)

---

💬 *Proyecto desarrollado como práctica personal para fortalecer conocimientos en desarrollo backend con Java y Spring Boot.*

```
