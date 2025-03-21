# Cronómetro Concurrente en Android

Este proyecto implementa un cronómetro con diferentes modos de ejecución: secuencial, concurrente y sincronizado. Está diseñado para mostrar el uso de hilos en Android, ejecutando tareas de manera secuencial, concurrente, o con sincronización de recursos.

## Funcionalidades

- **Ejecución Secuencial**: Los hilos se ejecutan uno tras otro, lo que significa que solo un hilo está activo a la vez.
- **Ejecución Concurrente**: Los hilos se ejecutan de manera simultánea, sin bloquearse entre sí.
- **Sincronización de Hilos**: Usando mecanismos de sincronización, se garantiza que los hilos accedan a los recursos compartidos de manera ordenada.

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Android SDK**: Herramientas de desarrollo para crear aplicaciones móviles para Android.
- **RenderScript (opcional)**: Usado para aplicar un efecto de desenfoque a las imágenes de fondo (si decides usarlo).
- **Java Concurrency API**: Usada para manejar múltiples hilos.

## Requisitos

- **Android Studio**: IDE recomendado para desarrollo Android.
- **SDK de Android**: Asegúrate de tener el SDK adecuado instalado para poder compilar la aplicación.

## Instalación

1. Clona el repositorio a tu máquina local.

   ```bash
   git clone https://github.com/tu_usuario/cronometro-concurrente-android.git
# Cronómetro Concurrente en Android

Este proyecto implementa un cronómetro con diferentes modos de ejecución: secuencial, concurrente y sincronizado. Está diseñado para mostrar el uso de hilos en Android, ejecutando tareas de manera secuencial, concurrente, o con sincronización de recursos.

## Funcionalidades

- **Ejecución Secuencial**: Los hilos se ejecutan uno tras otro, lo que significa que solo un hilo está activo a la vez.
- **Ejecución Concurrente**: Los hilos se ejecutan de manera simultánea, sin bloquearse entre sí.
- **Sincronización de Hilos**: Usando mecanismos de sincronización, se garantiza que los hilos accedan a los recursos compartidos de manera ordenada.

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Android SDK**: Herramientas de desarrollo para crear aplicaciones móviles para Android.
- **RenderScript (opcional)**: Usado para aplicar un efecto de desenfoque a las imágenes de fondo (si decides usarlo).
- **Java Concurrency API**: Usada para manejar múltiples hilos.

## Requisitos

- **Android Studio**: IDE recomendado para desarrollo Android.
- **SDK de Android**: Asegúrate de tener el SDK adecuado instalado para poder compilar la aplicación.

## Instalación

1. Clona el repositorio a tu máquina local.

   ```bash
   git clone https://github.com/joabvb/Practica1.git

1. Abre el proyecto en **Android Studio**.
2. Asegúrate de tener configurados los SDKs necesarios.
3. Ejecuta la aplicación en un dispositivo o emulador Android.

## Uso

1. Al abrir la aplicación, podrás ver un cronómetro que indica el tiempo en segundos.
2. Puedes iniciar las actividades mediante los botones:
   - **Iniciar la Actividad 1 (Ejecución Secuencial)**: Inicia un cronómetro con ejecución secuencial.
   - **Iniciar la Actividad 2 (Ejecución Concurrente)**: Inicia un cronómetro con ejecución concurrente.
   - **Iniciar la Actividad 3 (Sincronización de Hilos)**: Inicia un cronómetro con sincronización de hilos.
3. Puedes detener el cronómetro en cualquier momento presionando el botón **Detener Cronómetro**.

## Pantallas

### Vista de la Aplicación

La interfaz de usuario se compone de los siguientes elementos:

- Un **TextView** que muestra el tiempo transcurrido.
- Cuatro **Botones** para iniciar las actividades (secuencial, concurrente, sincronizada) y para detener el cronómetro.

## Código

### MainActivity

En el archivo `MainActivity.kt` se define la lógica principal de la aplicación. Usamos **hilos** para manejar la ejecución de cada actividad de cronómetro y actualizamos la interfaz de usuario en el hilo principal con la ayuda de `Handler` y `Looper`.

### layout/activity_main.xml

El archivo XML describe la interfaz de usuario de la aplicación, que incluye un `TextView` para mostrar el tiempo y botones para controlar el cronómetro.
