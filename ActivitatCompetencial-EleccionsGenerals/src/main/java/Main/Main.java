package Main;
import java.util.Scanner;
import java.sql.*;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import java.nio.file.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    // FUNCIONS BASIQUES
    // entra per teclat
    public static Scanner sc = new Scanner(System.in);
    public static String teclatString() {
        return sc.next();
    }
    public static int teclatInt() {
        return sc.nextInt();
    }
    /**
     * Retorna el path actual
     * @return actual path
     */
    public static Path pathActual() {
        Path pathActual = Paths.get(System.getProperty("user.dir"));
        return pathActual;
    }

    public static void main (String[] args) {


        try {
            // CONNEXIO A LA BASE DE DADES
            Class.forName("com.mysql.cj.jdbc.Driver");

            String password = "pastanaga";
            String user = "perepi";
            String database = "CURSES_MUNTANYA";
            String ipServer = "192.168.56.102";

            Connection con = DriverManager.getConnection
            ("jdbc:mysql://" + ipServer + ":3306/" + database ,user,password);

            // CREAR STATEMENT

            //-------------------------//
            //       EXERCICI 1        //
            //-------------------------//

            // Descomprimir fitxer
            Descomprimir.descomprimir();

            // Importació de províncies i municipis OPCIO 2

            // Importació de partits polítics OPCIO 2

            // Importació de resultats OPCIO 2

            // TANCA LA CONNEXIO
            //con.close();

        } catch(Exception error){
            System.out.println(error);
        }

    }
}
