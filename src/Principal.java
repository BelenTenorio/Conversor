import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        String menu = """
                1) Dólar => Peso argentino
                2) Peso argentino => Dólar
                3) Dólar => Real brasileño
                4) Real brasileño => Dolar
                5) Dólar => Peso colombiano
                6) Peso colombiano => Dólar
                7) Salir
                *********************************
                Elija una opción válida:
                """;

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        imprimirConversion("ARS", "USD", "Peso argentino");
                        break;
                    case 2:
                        imprimirConversion("USD", "ARS", "Dólar");
                        break;
                    case 3:
                        imprimirConversion("BRL", "USD", "Real brasileño");
                        break;
                    case 4:
                        imprimirConversion("USD", "BRL", "Dólar");
                        break;
                    case 5:
                        imprimirConversion("COP", "USD", "Peso colombiano");
                        break;
                    case 6:
                        imprimirConversion("USD", "COP", "Dólar");
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción del 1 al 7.");
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
            }
        }

        teclado.close();
    }

    private static void imprimirConversion(String fromCurrency, String toCurrency, String currencyName) throws IOException, InterruptedException {
        double tasaCambio = Conversor.getConvertidor(toCurrency, fromCurrency); //Si cambiasen de posicion el resultado es a la inversa y no concuerda con el menu previamente escrito
        System.out.printf("1 %s es igual a %.5f %s%n", toCurrency, tasaCambio, currencyName);
    }
}