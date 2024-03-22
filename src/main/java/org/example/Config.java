package org.example;

public class Config {
    private String portName;
    private int baudRate;
    private String codigoCoordenadas;
    private String codigoRumbo;
    private String codigoFechaHora;

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public String getCodigoCoordenadas() {
        return codigoCoordenadas;
    }

    public void setCodigoCoordenadas(String codigoCoordenadas) {
        this.codigoCoordenadas = codigoCoordenadas;
    }

    public String getCodigoRumbo() {
        return codigoRumbo;
    }

    public void setCodigoRumbo(String codigoRumbo) {
        this.codigoRumbo = codigoRumbo;
    }

    public String getCodigoFechaHora() {
        return codigoFechaHora;
    }

    public void setCodigoFechaHora(String codigoFechaHora) {
        this.codigoFechaHora = codigoFechaHora;
    }
}
