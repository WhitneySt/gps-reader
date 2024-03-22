package org.example;

public class Coordenadas {
    private String Latitud;
    private String Longitud;

    public Coordenadas() {
    }

    public Coordenadas(String latitud, String longitud) {
        Latitud = latitud;
        Longitud = longitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    @Override
    public String toString() {
        return "Coordenadas{" +
                "Latitud='" + Latitud + '\'' +
                ", Longitud='" + Longitud + '\'' +
                '}';
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }
}
