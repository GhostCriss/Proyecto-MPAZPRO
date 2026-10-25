# MPAZ PRO

**Aplicación móvil de apoyo didáctico y seguimiento del aprendizaje**

**Desarrollado por:** Josefa Lagos y Cristóbal Ruiz

Proyecto académico de la asignatura **DSY1105 - Desarrollo de Aplicaciones Móviles**, desarrollado en el contexto de la Escuela Escritora Marcela Paz, San Bernardo.

## 1. Descripción del proyecto

MPAZ PRO es una aplicación móvil educativa orientada a estudiantes de Tercero Básico. Su propósito es complementar el trabajo pedagógico mediante contenidos, actividades y desafíos enfocados en objetivos de aprendizaje específicos (como matemáticas).

La aplicación contempla funcionalidades aisladas y diferenciadas para estudiantes y docentes, integrando mecanismos de registro del progreso local, validación de estado y generación de reportes nativos.

El proyecto corresponde a un Producto Mínimo Viable (MVP 1.0) académico finalizado.

## 2. Objetivos Alcanzados

- Facilitar la realización de desafíos progresivos adaptados visualmente a niños (UX/UI).
- Proporcionar retroalimentación inmediata sobre las respuestas de los estudiantes.
- Registrar el avance y los puntajes mediante persistencia de datos locales.
- Facilitar al docente la revisión de resultados mediante un panel restringido.
- Aplicar principios de ingeniería de software: arquitectura MVVM, modularidad y separación de responsabilidades.
- Integrar recursos nativos del dispositivo para la exportación de informes físicos.

## 3. Tecnologías Utilizadas

- **Lenguaje:** Kotlin.
- **Entorno:** Android Studio.
- **Interfaz (UI):** Jetpack Compose y Material Design 3.
- **Arquitectura:** MVVM (Model-View-ViewModel).
- **Reactividad:** StateFlow y Corrutinas.
- **Base de Datos:** Room Database (Patrón DAO y Repository).
- **Control de Versiones:** Git y GitHub.

## 4. Perfiles de Usuario (Flujos Aislados)

### Estudiante
- Acceso directo sin contraseña orientado a la usabilidad infantil.
- Interfaz gráfica adaptada con componentes visuales amigables, tarjetas (Cards) y emojis.
- Desarrollo de desafíos matemáticos.
- Consulta de progreso, puntaje acumulado y medallas obtenidas.

### Docente
- Acceso restringido mediante validación de credenciales.
- Panel de control para monitorear el registro histórico de actividades de los estudiantes.
- Exportación del informe de rendimiento a los archivos locales del dispositivo.

## 5. Funcionalidades Implementadas (MVP 1.0)

- **Arquitectura Estructurada:** Patrón MVVM implementado en su totalidad (`data`, `ui`, `viewmodel`).
- **Persistencia de Datos (Room):** Interacción en tiempo real con la base de datos local usando entidades relacionales (`Usuario`, `Actividad`).
- **Recurso Nativo (Almacenamiento):** Exportación de datos de Room hacia un archivo físico `.txt` en la carpeta de descargas del dispositivo.
- **Gestión de Estados e Interfaz (UX/UI):** 
  - Manejo de estados vacíos (Empty States).
  - Prevención de solapamiento del teclado virtual (`imePadding`, `verticalScroll`).
  - Orientación de pantalla bloqueada en modo vertical (`portrait`) para prevenir destrucción de actividades y pérdida de estado.
  - Navegación segura entre pantallas (Jetpack Navigation).

## 6. Arquitectura del Proyecto

El proyecto está estructurado bajo el estándar MVVM para Android moderno:

- **Model (`data`):** Contiene las entidades de la base de datos (`Usuario`, `Actividad`), las interfaces DAO, la configuración de Room y el patrón `Repository` para centralizar el acceso a los datos.
- **ViewModel (`viewmodel`):** Administra el estado de la interfaz de forma reactiva (`StateFlow`) y coordina la lógica de negocio sin tocar la vista.
- **View (`ui/screens`):** Construida íntegramente con funciones `@Composable` de Jetpack Compose, encargada únicamente de renderizar la interfaz y capturar eventos del usuario.

## 7. Almacenamiento Local (Room)

Se implementó Room Database para garantizar la persistencia de los datos entre sesiones. La base de datos guarda la información de los usuarios (docentes/estudiantes) y el registro histórico de los puntajes obtenidos en los desafíos, permitiendo su recuperación inmediata al abrir la aplicación.

## 8. Recursos Nativos

Se integró con éxito la escritura en el almacenamiento externo del sistema operativo. Mediante los permisos adecuados (`WRITE_EXTERNAL_STORAGE`) y el uso de la clase `Environment`, la aplicación genera y guarda un archivo `Reporte_Estudiantes_MPAZPRO.txt` directamente en el dispositivo físico o emulador.

## 9. Requisitos de Ejecución

- Android Studio (versión reciente recomendada).
- JDK compatible con la versión de Gradle utilizada (Java 17+).
- Emulador Android o dispositivo físico compatible.

## 10. Instalación y Ejecución

1. Clonar el repositorio.
2. Abrir la carpeta raíz del proyecto en Android Studio.
3. Esperar a que finalice la sincronización de Gradle.
4. Configurar un emulador Android o conectar un dispositivo físico.
5. Ejecutar la configuración `app` mediante la opción Run.

## 11. Privacidad y Restricciones

- Todos los usuarios y resultados gestionados en esta versión son ficticios o de prueba.
- No se realizan conexiones con sistemas institucionales reales.
- La aplicación respeta el aislamiento de datos; el informe pedagógico está estrictamente reservado al perfil docente.

## 12. Gestión y Planificación del Proyecto

La planificación, asignación de tareas y el respaldo de las evidencias técnicas del desarrollo (capturas de pantalla y cumplimiento de rúbricas) se encuentran documentadas en nuestro tablero ágil:
- **Tablero de Trello:** [MPAZ PRO - Desarrollo de Aplicación Móvil](https://trello.com/b/B0tAOt8l/mpaz-pro-desarrollo-de-aplicacion-movil)

## 13. Contexto Académico

- **Asignatura:** DSY1105 - Desarrollo de Aplicaciones Móviles.
- **Proyecto:** MPAZ PRO.
- **Institución:** Duoc UC.
- **Caso:** Escuela Escritora Marcela Paz.
