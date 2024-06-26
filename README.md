# Lector GPS 
<div>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="java">
  <img src="https://img.shields.io/badge/JSerialComm-83D98C?style=for-the-badge&logo=java&logoColor=white" alt="jserialComm">
  <img src="https://img.shields.io/badge/SnakeYMAL-8871BC?style=for-the-badge&logo=java&logoColor=white" alt="ymal">
</div>

## Proceso de desarrollo
1. **Selección de una librería en Java para leer datos suministrados desde un dispositivo GPS**:
jSerialComm, es una librería moderna que proporciona soporte para la comunicación serial en plataformas con sistema operativo Windows, es fácil de usar y en comparación con RXTX la configuración en el entorno de desarrollo es más sencilla y es compatible con múltiples plataformas. <a href="https://philstories.medium.com/the-landscape-of-serial-communication-with-java-in-2021-com-ports-c5d9741aa263">Fuente</a>
2. **Creación y configuración del proyecto**: El IDE que utilizo es IntelliJ
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/8fdf227a-fb0e-4325-a263-27dc5cc223ba)
3. Se incluye jSerialComm como dependencia en el archivo  `build.gradle`
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/e4061862-697f-4ce5-9926-9897c12719e4)
   <a href="https://fazecast.github.io/jSerialComm/">Fuente</a>
4. Se realiza una prueba de conceptos de la librería `jSerialComm`. La prueba inicial se obtienen y muestra en consola los puertos habilitados por defecto en el ordenador en este caso mostró el puerto `COM3`. Esta prueba se realizó en PC con sistema operativo Windows.
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/daded2ee-1585-44ca-8604-d5e43fc8203f)
5. Se procede a buscar una herramienta que permita crear el puerto `COM2`virtual y que permita enviarle información a dicho puerto para emular el comportamiento del dipositivo GPS. La herramienta seleccionada `com0com` para Windows pero tuve problemas con mi Windows 10 por lo que me movi a un ubuntu que tenia a disposición, por tanto trabaje con `socat` que corre en plataforma `Linux`.
6. Se procede a instalar y realizar pruebas en el simulador. <a href="https://www.baeldung.com/linux/socat-command">Fuente</a>
7. Se crean dos puertos virtuales COM con `socat` en `ubuntu` ejecutando el siguiente comando <a href="https://www.baeldung.com/linux/make-virtual-serial-port#2-create-simple-virtual-serial-port">Fuente</a>:
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/b840ec7c-461b-4a1b-a90c-0a16ea72488b)
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/41ba8e4d-8c06-469e-866e-01045240e237)

9. Se envía información de un puerto a otro con el formato de los datos que se espera recibir del dispositivo GPS
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/a83dfc6f-50c9-4fcf-9026-b7937833a832)
10. Desde el editor se proceden a crear las clases `Coordenadas`, `FechaHora` y `Rumbo` los cuales van a recibir los datos tipo String y les dará el formato de salida de estos datos para mostrarlos en consola
    - Clase `Coordenadas`
      ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/b11b40a3-2f5b-4de9-a417-a76dcce19026)

    - Clase `FechaHora`
      ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/682083f3-971d-472f-9b8f-78fa522af5cd)

    - Clase `Rumbo`
      ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/356f8603-c18f-4a0c-81c5-91343cdf85c6)
11. En la clase `Main`, declaro una función que me permite calcular la hora local con base en la hora UTC y desplazamiento horario suministrado por el GPS en la trama `$GPZDA`
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/6762bbaa-da51-4237-826d-f7e15f17abec)
12. En la clase `Main` cargo el archivo `Config.yml` para cargar los parametros de configuración necesarios para la conexión con el puerto virtual usando la librería `SnakeYAML` para leer el archivo Config.yml (no sin antes haber cargado la libreria en el gradle):
  - Se instala la dependencia en `build.gradle`
    ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/36fa738f-90e7-4360-867d-ac432b9bfab3)
 - Se crea el archivo `Config.yml` con los datos de configuración del puerto:
   ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/90b88437-f457-4809-903a-c7d6fef8b28f)
 - Para leer y cargar el archivo `Config.yml` en la aplicación Java, primero creo una clase `Config` y en la función `main` se instancia esta clase para utilizar los valores y configurar `jSerialComm`
       Clase `Config`
         ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/0dbf3cf3-2d16-49df-8b71-f19c35534c6a)
Instancia de la clase en `main`
         ![image](https://github.com/WhitneySt/gps-reader/assets/98284374/9d459cdd-93c8-4bd3-8a10-6a082b27d502)
   
13. Se crea un nuevo hilo de ejecución para la conexión con el puerto serial virtual, siguiendo la recomendación de la libreria `jSerialComm`, hacemos un ciclo infinito para permanecer escuchando por las tramas que lleguen al puerto serial configurado, en dicho ciclo pregunto si hay bytes disponibles, en caso de que si, los extraigo usando la funcion bytesAvailable de la libreria y transformando dichos bytes en un string, primero usando la funcion readBytes de la libreria y luego pasando el buffer de bytes al constructor del objeto String, a este punto ya tengo la cadena de caracteres enviada desde el GPS a través del puerto, lo que sigue es trabajar el String.
![image](https://github.com/WhitneySt/gps-reader/assets/98284374/ad1c7fed-a7f8-4416-b666-86aa5acbf2c0)

14. Para tratar el String, lo que hago es hacer un split para obtener un array apartir del string separado por coma, asumiendo que el comando es correcto, procedo a extraer cada posicion del array como un dato especifico del comando, entonces, el codigo del comando seria la posicion 0, y asi sucesivamente con las otras posiciones y datos que llegan en la cadena de caracteres. Ya sabiendo el codigo del comando, con condicionales establezco una lógica de extracción especifica dependiendo del codigo de la trama y extrayendo asi los datos que luego paso a una clase especifica, para el codigo `$GPGLL` utilizo la clase Coordenadas para estructurar y mostrar los resultados, para el codigo `$GPVTG` utilizo la clase Rumbo, y para el codigo `$GPZDA` utilizo la clase FechaHora

![image](https://github.com/WhitneySt/gps-reader/assets/98284374/e6328df1-0341-4e1b-869e-bbaf357360c8)

15. **Resultados de tramas formateado**

**$GPGLL**
```
Coordenadas {
    Latitud='5133.81 N', 
    Longitud='00042.25 W'
}
```

**$GPVTG**
```
Rumbo {
    RumboVerdadero='054.7,T: Rumbo verdadero de 54.7 grados.', 
    RumboMagnetico='034.4,M: Rumbo magnético de 34.4 grados.', 
    VelocidadNudos='005.5,N: Velocidad sobre tierra en nudos (knots).', 
    VelocidadKilometrosHora='010.2,K: Velocidad sobre tierra en kilómetros por hora.'
}
```

**$GPZDA**
```
FechaHora {
    TiempoUniversal='160012.71: Hora UTC (Coordinated Universal Time) en formato HHMMSS.SS (horas, minutos, segundos y fracciones de segundo).', 
    Dia='11: Día del mes.', 
    Mes='03: Mes.', 
    Anio='2004: Año.', 
    DesplazamientoHoras='-1: Desplazamiento horario de la zona horaria en horas. En este caso, -1 hora.', 
    DesplazamientoMinutos='00: Desplazamiento horario de la zona horaria en minutos. En este caso, 0 minutos.',
    HoraLocal=15:00:12.710
}
```

