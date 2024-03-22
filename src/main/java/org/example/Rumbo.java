package org.example;

public class Rumbo {
    private String RumboVerdadero;
    private String RumboMagnetico;
    private String VelocidadNudos;
    private String VelocidadKilometrosHora;

    public Rumbo() {
    }

    public Rumbo(String rumboVerdadero, String rumboMagnetico, String velocidadNudos, String velocidadKilometrosHora) {
        RumboVerdadero = rumboVerdadero;
        RumboMagnetico = rumboMagnetico;
        VelocidadNudos = velocidadNudos;
        VelocidadKilometrosHora = velocidadKilometrosHora;
    }

    public String getRumboVerdadero() {
        return RumboVerdadero;
    }

    public void setRumboVerdadero(String rumboVerdadero) {
        RumboVerdadero = rumboVerdadero;
    }

    public String getRumboMagnetico() {
        return RumboMagnetico;
    }

    public void setRumboMagnetico(String rumboMagnetico) {
        RumboMagnetico = rumboMagnetico;
    }

    public String getVelocidadNudos() {
        return VelocidadNudos;
    }

    public void setVelocidadNudos(String velocidadNudos) {
        VelocidadNudos = velocidadNudos;
    }

    public String getVelocidadKilometrosHora() {
        return VelocidadKilometrosHora;
    }

    public void setVelocidadKilometrosHora(String velocidadKilometrosHora) {
        VelocidadKilometrosHora = velocidadKilometrosHora;
    }

    @Override
    public String toString() {
        return "Rumbo{" +
                "RumboVerdadero='" + RumboVerdadero + '\'' +
                ", RumboMagnetico='" + RumboMagnetico + '\'' +
                ", VelocidadNudos='" + VelocidadNudos + '\'' +
                ", VelocidadKilometrosHora='" + VelocidadKilometrosHora + '\'' +
                '}';
    }
}
