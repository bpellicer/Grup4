package Main;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

public class Descomprimir {

    public static void descomprimir() {

        //Obtenim el directori actual
        Path pathActual = Paths.get(System.getProperty("user.dir"));

        //Concatenem el directori actual amb un subdirectori "dades" i afegim el fitxer "prova.zip"
        String nomFitxer = "02_201911_1.zip";

        String unzipDir = "output";

        Path pathFitxer = Paths.get(pathActual.toString(), nomFitxer );
        Path pathUnzipDir = Paths.get(pathActual.toString(), unzipDir);

        // Create zip file stream.
        try (ZipArchiveInputStream fitxerZip = new ZipArchiveInputStream(
                new BufferedInputStream(new FileInputStream(pathFitxer.toString())))) {

            ZipArchiveEntry entrada;
            while ((entrada = fitxerZip.getNextZipEntry()) != null) {
                // Print values from entry.
                System.out.println(entrada.getName());
                System.out.println(entrada.getMethod()); // ZipEntry.DEFLATED is int 8

                File file = new File(Paths.get(pathActual.toString(), unzipDir, entrada.getName()).toString());
                System.out.println("Fitxer descomprimit:" + file);

                Files.createDirectories(pathUnzipDir);

                // copiem el contingu del fitxer.
                IOUtils.copy(fitxerZip, new FileOutputStream(file));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
