package org.example;

import com.fazecast.jSerialComm.SerialPort;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static LocalTime calcularHoraLocal(String timeString, String desplazamientoHorarioStr) {
        try {
            // Convertir la hora UTC a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss.SS");
            LocalTime horaUTC = LocalTime.parse(timeString, formatter);

            // Desplazamiento horario en formato string
            int desplazamientoHorario = Integer.parseInt(desplazamientoHorarioStr);

            // Calcular la hora local sumando el desplazamiento horario
            LocalTime horaLocal = horaUTC.plusHours(desplazamientoHorario);

            return horaLocal;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static void main(String[] args) {
        // Cargar el archivo YAML
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Config.yml");
        Yaml yaml = new Yaml();
        Config config = yaml.loadAs(inputStream, Config.class);

        SerialPort serialPort = SerialPort.getCommPort(config.getPortName());
        serialPort.setBaudRate(config.getBaudRate());
        serialPort.openPort();

        Thread thread = new Thread(() -> {
           while (true) {
               if(serialPort.bytesAvailable() > 0) {
                   byte[] buffer = new byte[serialPort.bytesAvailable()];
                   serialPort.readBytes(buffer, buffer.length);
                   String data = new String(buffer);
                   //System.out.println(data);
                   String[] items = data.split(",");

                   //ignorar el mensaje si no tiene el formato correcto separado por comas
                   if(items.length == 0) continue;

                   String codigo = items[0];
                   if(codigo.equalsIgnoreCase(config.getCodigoCoordenadas())) {
                       String region = (items.length >= 4) ? items[4].split("\\*")[0] : "";
                       Coordenadas coordenadas = new Coordenadas();
                       coordenadas.setLatitud(items[1] + " " + items[2]);
                       coordenadas.setLongitud(items[3] + " " + region);
                       System.out.println(coordenadas.toString());
                   } else if(codigo.equalsIgnoreCase(config.getCodigoRumbo())) {
                       Rumbo rumbo = new Rumbo();
                       rumbo.setRumboVerdadero(items[1] + "," + items[2] + ": Rumbo verdadero de " + Double.parseDouble(items[1]) + " grados.");
                       rumbo.setRumboMagnetico(items[3] + "," + items[4] + ": Rumbo magnético de "+ Double.parseDouble(items[3]) + " grados.");
                       rumbo.setVelocidadNudos(items[5] + "," + items[6] + ": Velocidad sobre tierra en nudos (knots).");
                       rumbo.setVelocidadKilometrosHora(items[7] + "," + items[8] + ": Velocidad sobre tierra en kilómetros por hora.");
                       System.out.println(rumbo.toString());
                   } else if(codigo.equalsIgnoreCase(config.getCodigoFechaHora())) {
                       FechaHora fechaHora = new FechaHora();
                       fechaHora.setTiempoUniversal(items[1] + ": Hora UTC (Coordinated Universal Time) en formato HHMMSS.SS (horas, minutos, segundos y fracciones de segundo).");
                       fechaHora.setDia(items[2] + ": Día del mes.");
                       fechaHora.setMes(items[3] + ": Mes.");
                       fechaHora.setAnio(items[4] + ": Año.");
                       fechaHora.setDesplazamientoHoras(items[5] + ": Desplazamiento horario de la zona horaria en horas. En este caso, -1 hora.");
                       fechaHora.setDesplazamientoMinutos(items[6] + ": Desplazamiento horario de la zona horaria en minutos. En este caso, 0 minutos.");

                       LocalTime horaLocal = calcularHoraLocal(items[1], items[5]);
                       fechaHora.setHoraLocal(horaLocal);
                       System.out.println(fechaHora.toString());
                   }
               }
           }
        });

        thread.start();
    }
}