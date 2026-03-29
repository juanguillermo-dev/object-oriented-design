# Fantasy Football Manager

A desktop application for managing a fantasy football league, built with Java and Swing as a final project for an Object-Oriented Design course.

## Overview

The application allows users to create and manage fantasy football teams by selecting real players from the 2010 FIFA World Cup. It features separate interfaces for administrators and regular users, a virtual store for purchasing players, historical tracking of user activity, and statistical reports per season.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java |
| GUI Framework | Java Swing |
| Data Persistence | Text files (.txt) |
| Build | Standard Java (Eclipse/IntelliJ) |

## Project Structure

```
object-oriented-design/
└── proyecto/
    ├── src/
    │   ├── clasesAdmin/            # Administrator logic
    │   │   ├── Administrador.java
    │   │   ├── Reporte.java
    │   │   └── Temporada.java
    │   ├── clasesAplicación/       # Core application logic
    │   │   ├── Aplicación.java
    │   │   ├── AppAdmin.java
    │   │   ├── AppUsuario.java
    │   │   ├── Historial.java
    │   │   └── Tienda.java
    │   ├── clasesUsuario/          # User and team logic
    │   │   ├── Usuario.java
    │   │   ├── EquipoFantasia.java
    │   │   └── Jugador.java
    │   └── interfazGráficaAp/      # Swing GUI views
    │       ├── controlador.java
    │       ├── ntrfzAppGeneral.java
    │       ├── ntrfzAppAdministrador.java
    │       ├── ntrfzAppUsuario.java
    │       ├── ntrfzEditar.java
    │       ├── ntrfzEstadísticas.java
    │       └── ntrfzTienda.java
    ├── datosTemporadas/            # Season data (World Cup 2010)
    │   └── mundial2010/
    │       ├── Equipos/            # Real team rosters
    │       ├── Fechas/             # Match schedule & results
    │       └── InfoAdicional/      # Standings & podium
    └── bin/                        # Compiled .class files
```

## Features

- **Dual role system** — separate Admin and User modes with different permissions
- **Fantasy team builder** — select real World Cup 2010 players for your squad
- **Virtual store** — buy and sell players using in-app currency
- **Activity history** — track all user transactions and team changes
- **Season reports** — administrators can generate statistical reports per season
- **Persistent data** — all data is saved and loaded from structured text files

## Design Patterns Applied

- **MVC (Model-View-Controller)** — clear separation between data, UI, and application logic
- **Layered architecture** — Admin layer, Application layer, User layer, and GUI layer are independently organized

## How to Run

1. Open the project in an IDE (Eclipse or IntelliJ)
2. Set `Aplicación.java` as the main entry point
3. Run the project — the main GUI window will launch

## Course

Object-Oriented Design — Universidad de los Andes