import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Metodos archivo = new Metodos();
        Scanner scanner = new Scanner(System.in);

        String nombreArchivo = "c:/Users/danie/Downloads/Aforos-RedPropia.csv";
        String separador = ","; // El delimitador
        boolean salir = false;

        while (!salir) {
            
            System.out.println("\nMenu de Opciones:");
            System.out.println("1. Ver el contenido del archivo");
            System.out.println("2. Buscar una palabra y contar sus repeticiones");
            System.out.println("3. Ver todas las palabras y sus recuentos");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    
                    archivo.lectorArchivo(nombreArchivo);
                    break;

                case 2:
                    
                    System.out.print("Introduce la palabra que deseas buscar: ");
                    String palabraBuscada = scanner.nextLine();
                    int repeticiones = archivo.palabraRepetida(nombreArchivo, palabraBuscada, separador);
                    System.out.println("La palabra '" + palabraBuscada + "' se repite " + repeticiones + " veces en el archivo CSV.");
                    break;

                case 3:
                    
                    Map<String, Integer> palabrasYRepeticiones = archivo.obtenerPalabrasYRepeticiones(nombreArchivo, separador);
                    System.out.println("Palabras y sus repeticiones en el archivo CSV:");
                    for (Map.Entry<String, Integer> entry : palabrasYRepeticiones.entrySet()) {
                        System.out.println("Palabra: '" + entry.getKey() + "' - Repeticiones: " + entry.getValue());
                    }
                    break;

                case 4:
                    
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        }

        scanner.close();
    }
}
