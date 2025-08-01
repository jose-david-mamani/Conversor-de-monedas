import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class miConversion {

    @SerializedName("base_code")
    String moneda_base = "";
    @SerializedName("target_code")
    String otra_moneda = "";
    String conversion_rate = "";
    String conversion_result = "";    // por cuanto se quiere ver el dinero por usd



    public static void main(String[] args)  throws IOException, InterruptedException {


        String mensajeCotizacion= """
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

        //primera moneda base

        System.out.println("        ********* QUE MONEDA POSEE? ********* \n" + mensajeCotizacion);
        String monedaBase = escanea.next().toUpperCase();


        //segunda moneda

        System.out.println("  ********* A QUE MONEDA DESEA CONVERTIR *********  \n".toUpperCase() + mensajeCotizacion);
        String segundaMoneda = escanea.next().toUpperCase();  // ................... aqui se define la segunda moneda



        System.out.println("CUANTOS " + monedaBase + " VA A CONVERTIRLOS EN " + segundaMoneda );
        int num = escanea.nextInt();

        String enlace = "https://v6.exchangerate-api.com/v6/035a59fa61757caf8da3925c/pair/" + monedaBase + "/" + segundaMoneda + "/"+ num ;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enlace))         //"https://v6.exchangerate-api.com/v6/035a59fa61757caf8da3925c/latest/USD "
                    .build();                     // esto muestra todos los pares// esto solo dos
        HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String json = response.body();

        // Deserialization
        miConversion lista = gson.fromJson(json, miConversion.class);

        String textoResultado ="para comprar 1 ".toUpperCase()+ monedaBase + " necesitarás ".toUpperCase() + lista.conversion_rate
                + " " + segundaMoneda ;
        System.out.println(textoResultado);

        String textoConversion = "LA  CONVERSION DE " + num + " " + monedaBase + " A " + segundaMoneda +
                " SON EN TOTAL DE: $" + lista.conversion_result + segundaMoneda ;
        System.out.println(textoConversion);



    }

}
//        String soloNumer = obj2.conversion_rate.substring(0,5);
//        double stringNum = Double.parseDouble(soloNumer);
//
//        String text = """
//                El cambio oficial es de:
//                 $ %s
//                """.formatted(soloNumer);
//
//
//        System.out.println(text);
//
//
//        num = num * stringNum;
//
//        System.out.println("el resultado es de " + num);
//
//        DecimalFormat numComas = new DecimalFormat();
//        txt = numComas.format(stringNum);
//        System.out.println(txt);
//

        /* En la séptima fase de nuestro desafío, nos sumergimos en el análisis de la respuesta JSON utilizando la biblioteca
Gson en Java. La manipulación de datos JSON es esencial, ya que la mayoría las respuestas de las API se presentan en este formato.

--> Para facilitar el análisis de los datos que se obtendrán de la API, recomendamos el uso de herramientas como Postman.

Con la biblioteca Gson, puedes realizar el mapeo eficiente de los datos JSON a objetos Java, facilitando así la extracción
 y manipulación de la información necesaria.

Recuerda utilizar las clases proporcionadas por Gson, como JsonParser y JsonObject, para acceder a las distintas propiedades
 de la respuesta JSON. */

