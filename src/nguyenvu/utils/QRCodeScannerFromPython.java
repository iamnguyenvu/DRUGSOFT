/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author HP
 */
public class QRCodeScannerFromPython {
    public static String getQrCodeFromPython() {
        String qrCode = null;
        
        try {
            String pythonPath = "python.exe";
            String scriptPath = "d:\\Download\\PythonVSCode\\QRCodeScanner.py";
            ProcessBuilder pb = new ProcessBuilder(pythonPath, "-u", scriptPath);
            Process process = pb.start();
            
//            String command = "python -u d:\\Download\\PythonVSCode\\QRCodeScanner.py";
//            Process process = Runtime.getRuntime().exec(command);
//            process.waitFor();

            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();

            // Capture standard error (stderr)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            errorReader.close();

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                qrCode = output.toString().trim(); // Return the QR code
            } else {
                System.err.println("Python script error: " + errorOutput.toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return qrCode;
    }
}
