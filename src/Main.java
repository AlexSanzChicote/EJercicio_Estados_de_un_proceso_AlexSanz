import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Process process = null;
        //ping, -c para que pare, 1 paquete a localhost
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "-c", "1", "localhost");

        try {
            process = processBuilder.start();
            //Esta vivo?
            System.out.println(process.isAlive());
            //Mostrar info
            //handle
            ProcessHandle handle = process.toHandle();
            //para mostrar info
            ProcessHandle.Info informacion = handle.info();
            //Pid
            System.out.println("PID: " + handle.pid());
            //Comando
            System.out.println("Comando: " + informacion.command());
            //Argumentos
            System.out.println("Argumentos: " + informacion.arguments().isPresent());
            //Usuario
            System.out.println("Usuario: " + informacion.user());

            //Mostar resultados
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println("---Salida Standar---");
            stdOut.lines().forEach(System.out::println);
            System.out.println("---Salida Error---");
            stdError.lines().forEach(System.out::println);

            //Waitfor

            int codigo = process.waitFor();

            // Mostrar el resultado final y
            System.out.println("\n---- Estado Final ----");
            System.out.println("Código: " + codigo);
            System.out.println("¿Se encuentra vivo una vez finalizado el proceso?: " + process.isAlive());


        } catch (InterruptedException e) {
            System.err.println("fue interrumpido:");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("---Error---");
            e.printStackTrace();

        }
    }
}