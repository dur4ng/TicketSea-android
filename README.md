# TicketSea v0.7

## 1 Diseño
- Modificación de event_item.xml del fragmento showCurrentEvents: He borrado el botón de navigación del item para añadir la funcionalida en el itemView directamente.
- Modificación de eventlot_item.xml del fragment createEvent: He borrado el botón de edición para añadir la funcionalida en el itemView directamente.
- Añadido Toast para que el usuario tenga información de los procesos de la aplicación.

## 2 Clases POJO
- Clase Event y EventComparator: métodos personalizados compareTo que ordena por descripción y nombre del evento.

## 3 Login
- No realizado

## 4 Registro
- No realizado

## 5 RecyclerView
### showCurrentEventsFragment
- Punto 1: Se utiliza un RecyclerView de forma eficiente para mostrar los direntes eventos.
- Punto 2: Se puede ordenar de dos criterios alfabéticamente y su inverso | Sigue la arquitectura MVP.
- Punto 3: Se puede navegar a la actividad de un evento, creación de evento, conectar cartera y información del usuario.
### createEventFragment
- Punto 1: Se utiliza un RecyclerView de forma eficiente para mostrar los direntes tickets.
- Punto 2: No ordena | Sigue la arquitectura MVP.
- Punto 3: Se puede navegar a la actividad de creación de tickets, conectar cartera y información del usuario.
- Se ha controlado posibles errores mostrándolos en la vista

## 6 Implementar DOS Activity que añada y modifique a DOS clases POJO
- createEventFragment: se puede crear eventos, eliminar tickets(pulsación larga), navegar a edición de tickets(pulsación corta) y navegar a creación de tickets.
- createTicketFragment: se puede editar y crear ticktes.
- Se ha implementado el control de errores

## 7 Activity "acerca de"
- Se ha implementado como preferencia

## 8 configuración LandScape
- No realizado

## 9 Preferencias
- Se ha implementado un botón en el boton navegation: aboutUs y cambiar idioma
## 10 Menu
- Personalizado menu para showCurrentEvents: se puede ordenar eventos

## Ejercicio 11 Internacionalización
- Añadido idiomas: inglés y chino

# TicketSea v0.8

## Sistema de navegación
Se ha implementado un BottonNavigation el cual tiene 4 childs
- Mostrar Eventos
- Crear evento
- Mostrar el porfolio del usurio
- preferencias
Se ha implementado en los diferentes hijos de cada opción un argumento para que el bottom navigation carge el fragmento correspondiente.
## Implementación de preferencias
- Cambiar idioma
- About us
## Bases de datos
- Implementación de BD para la inserción y consulta de eventos y tickets
## Notificaciones
- Notificación con icono personalizado de: creación de tickets y eventos

# TicketSea v 1 Tarea Final

## Registro
- Se ha implementado un pojo y repositorio de usuarios a registrar.
- SignUp activity para el registro de un nuevo usuario.

## Login
- Se ha implementado un pojo, repositorio y dao de usuarios registrados en la bd
- SignIn activity para el logue de usuarios.

## Implementación
- se ha añadido la opciones de edición/borrado de eventos

# TODOs
## edición/borrado eventos
- modificar eventos rv de cada evento a navegar al createEvent fragment con un arg(evento)
- repositorio event select from where nombreEvento=x
- vista del createEvent databinding
- cargar repositorio ticketTMP con los ticket del evento | vaciar cuando se salga