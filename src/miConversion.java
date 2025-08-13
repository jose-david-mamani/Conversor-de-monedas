import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class miConversion {


    public static void main(String[] args) throws IOException, InterruptedException {

        String mensajeCotizacion = """
                Código de moneda        Nombre de la moneda           País
                 USD	                Dólar estadounidense	      Estados Unidos
                 ARS	                Peso argentino	              Argentina
                 BOB                    Boliviano boliviano      	  Bolivia
                 BRL	                Real brasileño            	  Brasil
                 CLP                	Peso chileno	              Chile
                 COP	                Peso colombiano	              Colombia
                 PYG	                Guaraní paraguayo	          Paraguay
                 UYU	                Peso uruguayo	              Uruguay
                 PAB	                Balboa panameño	              Panamá
                 PEN                	Sol peruano             	  Perú
                 MXN	                Peso mexicano	              México
                 EUR	                Euro	                      Unión Europea
                """;


        Scanner escanea = new Scanner(System.in);

        System.out.println("¿Estás preparado para comenzar a conocer las cotizaciones? -si/no");
        String unString = escanea.next();
        int respuesta = unString.compareToIgnoreCase("si");
        int i = 0;

        while (i == respuesta) {
            System.out.println(mensajeCotizacion);
            System.out.println("Que moneda desea utilizar como moneda base");
        String monedaBase = escanea.next().toUpperCase();

            System.out.println("Que moneda desea utilizar como (segunda moneda)");
            String segundaMoneda = escanea.next().toUpperCase();

        System.out.println("CUANTOS " + monedaBase + " VA A CONVERTIRLOS EN " + segundaMoneda );
            double num = escanea.nextDouble();


        String enlace = "https://v6.exchangerate-api.com/v6/035a59fa61757caf8da3925c/pair/" + monedaBase + "/" + segundaMoneda + "/"+ num ;

        HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(enlace)).
                    build();
        HttpResponse<String> response = client
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String cuerpoRespuesta = response.body();

        Gson gson = new Gson();
            formulario form = gson.fromJson(cuerpoRespuesta, formulario.class);
            System.out.println(form);

            System.out.println("Desea hacer de nuevo otra operacion? *** si/no ***");
            String yesNo = escanea.next();
            i = yesNo.compareToIgnoreCase("si");
            if (i == 1) {
                break;

            }

    }

}
}