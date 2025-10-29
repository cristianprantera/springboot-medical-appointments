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
