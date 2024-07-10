// Importar Gson para manejar JSON
import com.google.gson.Gson;
import com.google.gson.JsonObject;

// Importaciones necesarias para entrada/salida, URI y HTTP
import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Variables para almacenar la categoría seleccionada, el monto, y las divisas
        int categoria = 0;
        double monto = 0;
        String divisaOrigen = "";
        String divisaDestino = "";

        // Crear una instancia de Gson para manejar la conversión de JSON
        Gson gson = new Gson();

        // Bucle infinito para mantener el menú hasta que el usuario decida salir
        while (true) {
            // Mostrar el menú al usuario
            System.out.println("""
                    
                    **************************
                    Selecciona una opción dentro del menú:
                    1 - USD a MXN
                    2 - MXN a USD
                    3 - EUR a MXN
                    4 - MXN a EUR
                    5 - USD a EUR
                    6 - EUR a USD
                    7 - Salir
                    **************************
                    """);

            // Leer la opción seleccionada por el usuario
            categoria = scanner.nextInt();

            // Si el usuario selecciona la opción 7, salir del programa
            if (categoria == 7) {
                System.out.println("""
                Gracias por usar el conversor de divisas.
                ¡Vuelva pronto!
                """);
                break;
            }
            // Si el usuario selecciona una opción no válida, mostrar un mensaje y continuar el bucle
            else if (categoria < 1 || categoria > 7) {
                System.out.println("Por favor selecciona una opción válida");
                continue;
            }

            // Asignar las divisas origen y destino según la opción seleccionada
            switch (categoria) {
                case 1 -> {
                    divisaOrigen = "USD";
                    divisaDestino = "MXN";
                }
                case 2 -> {
                    divisaOrigen = "MXN";
                    divisaDestino = "USD";
                }
                case 3 -> {
                    divisaOrigen = "EUR";
                    divisaDestino = "MXN";
                }
                case 4 -> {
                    divisaOrigen = "MXN";
                    divisaDestino = "EUR";
                }
                case 5 -> {
                    divisaOrigen = "USD";
                    divisaDestino = "EUR";
                }
                case 6 -> {
                    divisaOrigen = "EUR";
                    divisaDestino = "USD";
                }
            }

            // Crear un cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear la URI de la solicitud HTTP usando la divisa de origen y la API Key
            URI uri = URI.create("https://v6.exchangerate-api.com/v6/6703eb13a387649146aff318/latest/" + divisaOrigen);
            // Construir la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

            try {
                // Enviar la solicitud HTTP y obtener la respuesta
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                // Obtener el cuerpo de la respuesta como una cadena JSON
                String json = response.body();

                // Convertir el JSON a un objeto JsonObject usando Gson
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                // Obtener el objeto de tasas de conversión del JSON
                JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

                // Pedir al usuario que ingrese el monto a convertir
                System.out.println("Ingresa el monto a convertir: ");
                monto = scanner.nextDouble();

                // Obtener la tasa de conversión de la divisa destino
                double tasaConversion = conversionRates.get(divisaDestino).getAsDouble();
                // Calcular el monto convertido
                double conversion = monto * tasaConversion;

                // Mostrar el resultado de la conversión
                System.out.println("La conversión es: " + conversion);

            } catch (IOException | InterruptedException e) {
                // Manejar posibles excepciones durante la solicitud HTTP
                System.err.println("Error al realizar la conversión: " + e.getMessage());
            }
        }
    }
}
