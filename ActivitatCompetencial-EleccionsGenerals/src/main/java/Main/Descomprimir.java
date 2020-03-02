package Main;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

public class Descomprimir {


    /**
     * Descomprimeix tots fitxers ZIP que hi hagi dins la carpeta dades
     */
    public static void descomprimir(String[] fitxersZIP) {


        //Obtenim el directori actual
        Path pathActual = Main.pathActual();

        String unzipDir = "output";

        for (int i = 0; i < fitxersZIP.length; i++) {
            if(fitxersZIP[i].endsWith(".zip")) {
                //Concatenem el directori actual amb un subdirectori "dades" i afegim el fitxer "prova.zip"
                String nomFitxer = fitxersZIP[i];
                Path pathFitxer = Paths.get(pathActual.toString(), "dades",nomFitxer );
                Path pathUnzipDir = Paths.get(pathActual.toString(), unzipDir);

                // Create zip file stream.
                try (ZipArchiveInputStream fitxerZip = new ZipArchiveInputStream(
                        new BufferedInputStream(new FileInputStream(pathFitxer.toString())))) {

                    ZipArchiveEntry entrada;
                    while ((entrada = fitxerZip.getNextZipEntry()) != null) {
                        // Print values from entry.
                        //System.out.println(entrada.getName());
                        //System.out.println(entrada.getMethod()); // ZipEntry.DEFLATED is int 8

                        File file = new File(Paths.get(pathActual.toString(), unzipDir, entrada.getName()).toString());
                        //System.out.println("Fitxer descomprimit:" + file);

                        Files.createDirectories(pathUnzipDir);

                        // copiem el contingu del fitxer.
                        IOUtils.copy(fitxerZip, new FileOutputStream(file));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Guarda en un vector tots els noms dels fitxers
     * @return retorna el vector
     */
    public static String[] guardaNomsFitxers() {
        File carpetaDades = new File(Main.pathActual() + "/dades");

        String[] fitxersZIP = carpetaDades.list();

        return fitxersZIP;
    }

}
