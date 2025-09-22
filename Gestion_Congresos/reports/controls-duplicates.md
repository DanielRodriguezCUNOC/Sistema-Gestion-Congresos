# Informe de métodos duplicados en `Backend/db/controls`

Fecha: 21 de septiembre de 2025

Este informe lista las clases del paquete `com.gestion.congresos.Backend.db.controls`, las firmas de los métodos relevantes y observaciones sobre duplicaciones funcionales.

---

## Resumen rápido

Se detectaron varias áreas con funcionalidad repetida entre controles:

- Búsqueda de id por nombre (institución, salón, tipo actividad, congreso, rol).
- Verificación de existencia usando COUNT(\*) → boolean.
- Listados planos (construcción de List<String[]> desde ResultSet).
- Patrón CRUD repetido (insert/update/delete con commit/rollback).
- Utilidades de usuario solapadas (consultas por username/id/email/rol).

Se sugiere consolidar y/o extraer helpers/DAOs para estos patrones.

---

## Archivo por archivo

### controls/room/ControlRoomActivity.java

- Métodos:
  - boolean isRoomAvailable(int idRoom, String dateActivity, String timeStarting, String timeEnding)
- Observación: Funcionalidad específica para comprobar solapamiento de actividades en un salón. No duplicado directo.

### controls/room/ControlRoomCRUD.java

- Métodos:
  - boolean editRoom(int idRoom, int idInstitution, String nameRoom, int capacity, String address)
  - RoomModel getRoomById(int idRoom)
  - boolean deleteRoom(int idRoom)
- Observación: CRUD específico de salón; patrón similar a otros CRUDs.

### controls/room/ControlRoom.java

- Métodos:
  - boolean insertRoom(RoomModel salon)
  - boolean existsRoom(String nameRoom, int idInstitucion, String ubicacion)
  - List<String[]> getAllRooms()
  - int getIdRoomByName(String nameRoom)
- Observación: `getIdRoomByName` es conceptualmente igual a otros "getIdByName" en el repo.

### controls/activity/ControlActivityCRUD.java

- Métodos:
  - ActivityModel getGetActivityById(int idActivity)
  - boolean deleteActivity(int idActivity)
  - boolean updateActivity(ActivityModel activity)
- Observación: CRUD de actividad; `getGetActivityById` tiene nombre inconsistente.

### controls/activity/ControlActivity.java

- Métodos:
  - boolean insertActivity(ActivityModel activity)
  - boolean existsActivityByName(String nameActivity)
- Observación: `existsActivityByName` repite patrón COUNT(\*) → boolean.

### controls/congress/ControlCongress.java

- Métodos:
  - boolean insertCongress(CongressModel congress)
  - boolean existsCongressByName(String nameCongress)
  - int getIdCongressByName(String nameCongress)
  - CongressModel getCongressById(int idCongress)
- Observación: Métodos getId/getById duplican la idea de buscar entidad por nombre/id.

### controls/congress/ControlCongressCRUD.java

- Métodos:
  - boolean editCongress(int idCongress, String nombre, String descripcion, Date fechaInicio, Date fechaFin, String costo, boolean aceptaConvocatoria)
- Observación: Update de congreso — patrón CRUD repetido.

### controls/institution/ControlInstitution.java

- Métodos:
  - boolean insertInstitution(InstitutionModel institution)
  - boolean existsInstitution(String name_institution)
  - int getIdInstitutionByName(String name_institution)
- Observación: `getIdInstitutionByName` duplica `ControlConferenceAdmin.getIdInstitutionByName`.

### controls/tipo_actividad/ControlTipoActividad.java

- Métodos:
  - int getIdTipeActivityByName(String tipoActividad)
- Observación: Funcionalmente equivalente a `ControlConferenceAdmin.getIdTypeActivityByName`.

### controls/guest_speaker/ControlGuestSpeaker.java

- Clase vacía (sin métodos implementados).

### controls/user/UserControl.java

- Métodos:
  - boolean insertUser(UserModel user)
  - boolean validateUser(String username)
  - String getEmailByUsername(String username)
  - int getUserIdByUsername(String username)
  - int getUserRolIdByIdUser(int userId)
  - UserModel getUserById(int userId)
- Observación: Contiene las utilidades principales de Usuario; otras clases reconstruyen consultas similares para listados.

### controls/rol/RolControl.java

- Métodos:
  - int getIdRol(String rol)
  - List<String> getAllRol()
- Observación: getIdRol coincide conceptualmente con otros getIdByName.

### controls/login/ControlLogin.java

- Métodos:
  - boolean userExist(String username, String password)
- Observación: Específico para autenticación.

### controls/admin/ControlSysAdminCRUD.java

- Métodos:
  - boolean updateAdmin(int userId, String username, String phone, String organization, byte[] photo)
  - boolean activateUser(int userId)
  - boolean deactivateUser(int userId)
- Observación: Operaciones sobre Usuario relacionadas con administración.

### controls/admin/ControlConferenceAdmin.java

- Métodos:
  - int getIdInstitutionByName(String)
  - int getIdTypeActivityByName(String)
  - List<String[]> getAllGuestsSpeakers()
  - List<String[]> getAllCongress()
  - List<String[]> getAllActivities()
- Observación: Duplica `getIdInstitutionByName` y `getIdTypeActivityByName` además de construir listados planos como otras clases.

### controls/admin/ControlSysAdmin.java

- Métodos:
  - List<String[]> getAllConferenceAdmins()
  - List<InstitutionModel> getAllInstitutions()
  - List<String[]> getAllAdmins()
- Observación: getAllInstitutions y getAllAdmins solapan con otras funciones similares en repo.

---

## Recomendaciones (sin modificar código)

1. Consolidar la búsqueda de ID por nombre en una utilidad o DAO común (decidir devolver -1 o lanzar excepción y aplicarlo consistentemente).
2. Extraer un helper `existsByQuery(query, params...)` para el patrón COUNT(\*) → boolean.
3. Reemplazar List<String[]> por DTOs/Models o, al menos, centralizar el mapeo ResultSet→String[].
4. Extraer utilidades para transacciones (commit/rollback) y ejecución de updates para reducir boilerplate.
5. Normalizar nombres de métodos (`getGetActivityById` → `getActivityById`).

---

Si quieres que también cree un archivo CSV con una línea por método (clase, firma, observación, grado de duplicación) lo puedo generar ahora.
