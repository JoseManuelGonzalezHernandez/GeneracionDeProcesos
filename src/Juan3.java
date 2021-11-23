import java.io.File;
import java.io.IOException;

public class Juan3 {
    public static void main(String[] args) throws IOException {
        String url = args[0];
        ProcessBuilder processBuilder = new ProcessBuilder();
        String rutaFile = args[0];
        File file = new File(rutaFile);

        if (System.getProperty("os.name").equals("Linux")) {
            if (!file.exists()) {
                System.out.println("ERROR: El archivo indicado no existe.");
            } else {
                processBuilder.command("cat", args[0], "|", "wc");

                Process process = processBuilder.start();
                System.out.println(process.getInputStream());
            }
        } else {
            System.out.println("Sistema Operativo no soportado para el programa, este programa solo funciona en Linux.");
        }
    }
}
