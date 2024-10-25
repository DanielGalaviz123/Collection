import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Metodos {

    // imprimir
    public void lectorArchivo(String archivoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            System.out.println("Contenido del archivo CSV:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int palabraRepetida(String archivoCSV, String palabraBuscada, String separador) {
    int contador = 0; 
    String palabraBuscadaMinu = normalizarPalabra(palabraBuscada.toLowerCase());

    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivoCSV), "UTF-8"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            // Dividir la linea en columnas usando el separador del CSV
            String[] valores = linea.split(separador);

            // Comparar cada valor con la palabra buscada
            for (String valor : valores) {
                // Normalizar cada valor y comparar
                String valorNormalizado = normalizarPalabra(valor.trim().toLowerCase());
                if (valorNormalizado.equals(palabraBuscadaMinu)) {
                    contador++;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return contador; 
}
    

    

public Map<String, Integer> obtenerPalabrasYRepeticiones(String archivoCSV, String separador) {
    Map<String, Integer> mapaPalabras = new HashMap<>(); // HashMap para almacenar palabras y su conteo

    try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            // Verificar si la linea no esta vacia
            if (!linea.trim().isEmpty()) {
                // Dividir la linea en columnas usando el separador del CSV
                String[] valores = linea.split(separador);

                // Procesar cada valor en la linea
                for (String valor : valores) {
                    // Limpiar la cadena y convertir a minusculas
                    String palabra = valor.trim().toLowerCase();

                    // Verificar si la palabra es valida 
                    if (palabra.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+(?:\\s+[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*$")) {
                        // Añadir o actualizar 
                        mapaPalabras.put(palabra, mapaPalabras.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }

    return mapaPalabras; 
}


    private String normalizarPalabra(String palabra) {
        return palabra
                .replaceAll("á", "a")
                .replaceAll("é", "e")
                .replaceAll("í", "i")
                .replaceAll("ó", "o")
                .replaceAll("ú", "u")
                .replaceAll("ñ", "n");
    }
    
}
