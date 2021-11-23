import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Juan2 {
    public static void main(String[] args) throws IOException {
        int numRepeat = Integer.parseInt(args[0]);
        String url = args[1];
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process[] process = new Process[numRepeat];
        Calendar calendario;

        int horaComienzo;
        int minutosComienzo;
        int segundosComienzo;

        int horaFin;
        int minutosFin;
        int segundosFin;

        if (System.getProperty("os.name").startsWith("Windows")) {
            String rutaNavigator = "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe";
            File navigator = new File(rutaNavigator);

            if (!navigator.exists()) {
                System.out.println("ERROR: Usted no tiene instalado el navegador Edge en el directorio habitual.");
            }

            processBuilder.command(rutaNavigator, "--new-window", url);


            for (int i = 0; i < numRepeat; i++) {
                process[i] = processBuilder.start();
                calendario = new GregorianCalendar();
                horaComienzo = calendario.get(Calendar.HOUR_OF_DAY);
                minutosComienzo = calendario.get(Calendar.MINUTE);
                segundosComienzo = calendario.get(Calendar.SECOND);

                System.out.println("Proceso iniciado a las " + horaComienzo + ":" + minutosComienzo + ":" + segundosComienzo);
            }
            try {



                for (int i = 0; i < numRepeat; i++) {
                    process[i].waitFor(1000, TimeUnit.MILLISECONDS);

                    calendario = new GregorianCalendar();
                    horaFin = calendario.get(Calendar.HOUR_OF_DAY);
                    minutosFin = calendario.get(Calendar.MINUTE);
                    segundosFin = calendario.get(Calendar.SECOND);

                    System.out.println("Proceso finalizado a las " + horaFin + ":" + minutosFin + ":" + segundosFin);
                    process[i].destroy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (System.getProperty("os.name").equals("Linux")) {
            String rutaNavigator = "chromium";
            File navigator = new File(rutaNavigator);

            if (!navigator.exists()) {
                System.out.println("ERROR: Usted no tiene instalado el navegador Chromium.");
            }

            processBuilder.command(rutaNavigator, "--new-window", url);

            for (int i = 0; i < numRepeat; i++) {
                process[i] = processBuilder.start();

                calendario = new GregorianCalendar();
                horaComienzo = calendario.get(Calendar.HOUR_OF_DAY);
                minutosComienzo = calendario.get(Calendar.MINUTE);
                segundosComienzo = calendario.get(Calendar.SECOND);

                System.out.println("Proceso iniciado a las " + horaComienzo + ":" + minutosComienzo + ":" + segundosComienzo);
            }
            try {
                for (int i = numRepeat-1; i >=0; i--) {
                    process[i].waitFor(500, TimeUnit.MILLISECONDS);

                    calendario = new GregorianCalendar();
                    horaFin = calendario.get(Calendar.HOUR_OF_DAY);
                    minutosFin = calendario.get(Calendar.MINUTE);
                    segundosFin = calendario.get(Calendar.SECOND);

                    System.out.println("Proceso finalizado a las " + horaFin + ":" + minutosFin + ":" + segundosFin);
                    process[i].destroy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (System.getProperty("os.name").startsWith("Mac")){
            processBuilder.command("open", "-a", "Google Chrome", args[1]);

            for (int i = 0; i < numRepeat; i++) {
                process[i] = processBuilder.start();

                calendario = new GregorianCalendar();
                horaComienzo = calendario.get(Calendar.HOUR_OF_DAY);
                minutosComienzo = calendario.get(Calendar.MINUTE);
                segundosComienzo = calendario.get(Calendar.SECOND);
            }
            try {
                for (int i = numRepeat-1; i >=0; i--) {
                    process[i].waitFor(500, TimeUnit.MILLISECONDS);

                    calendario = new GregorianCalendar();
                    horaFin = calendario.get(Calendar.HOUR_OF_DAY);
                    minutosFin = calendario.get(Calendar.MINUTE);
                    segundosFin = calendario.get(Calendar.SECOND);

                    System.out.println("Proceso finalizado a las " + horaFin + ":" + minutosFin + ":" + segundosFin);
                    process[i].destroy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sistema Operativo no soportado para el programa, este programa solo funciona en Windows, Linux y Mac");
        }
    }
}
