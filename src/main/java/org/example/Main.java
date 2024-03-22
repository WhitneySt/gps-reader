package org.example;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();
        /*for (SerialPort port : ports) {
            System.out.println(port.getSystemPortName());
        }*/

        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        InputStream in = comPort.getInputStream();
        try
        {
            for (int j = 0; j < 1000; ++j) {
                System.out.println("reading "+ j);
                System.out.print((char)in.read());
            }
            in.close();
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
    }
}