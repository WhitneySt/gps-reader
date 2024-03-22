package org.example;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FechaHora {
    private String TiempoUniversal;
    private String Dia;
    private String Mes;
    private String Anio;
    private String DesplazamientoHoras;
    private String DesplazamientoMinutos;
    private LocalTime HoraLocal;

    public FechaHora() {
    }

    public FechaHora(String tiempoUniversal, String dia, String mes, String anio, String desplazamientoHoras, String desplazamientoMinutos, LocalTime horaLocal) {
        TiempoUniversal = tiempoUniversal;
        Dia = dia;
        Mes = mes;
        Anio = anio;
        DesplazamientoHoras = desplazamientoHoras;
        DesplazamientoMinutos = desplazamientoMinutos;
        HoraLocal = horaLocal;
    }

    public String getTiempoUniversal() {
        return TiempoUniversal;
    }

    public void setTiempoUniversal(String tiempoUniversal) {
        TiempoUniversal = tiempoUniversal;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String anio) {
        Anio = anio;
    }

    public String getDesplazamientoHoras() {
        return DesplazamientoHoras;
    }

    public void setDesplazamientoHoras(String desplazamientoHoras) {
        DesplazamientoHoras = desplazamientoHoras;
    }

    public String getDesplazamientoMinutos() {
        return DesplazamientoMinutos;
    }

    public void setDesplazamientoMinutos(String desplazamientoMinutos) {
        DesplazamientoMinutos = desplazamientoMinutos;
    }

    public LocalTime getHoraLocal() {
        return HoraLocal;
    }

    public void setHoraLocal(LocalTime horaLocal) {
        HoraLocal = horaLocal;
    }

    @Override
    public String toString() {
        return "FechaHora{" +
                "TiempoUniversal='" + TiempoUniversal + '\'' +
                ", Dia='" + Dia + '\'' +
                ", Mes='" + Mes + '\'' +
                ", Anio='" + Anio + '\'' +
                ", DesplazamientoHoras='" + DesplazamientoHoras + '\'' +
                ", DesplazamientoMinutos='" + DesplazamientoMinutos + '\'' +
                ", HoraLocal=" + HoraLocal +
                '}';
    }
}
