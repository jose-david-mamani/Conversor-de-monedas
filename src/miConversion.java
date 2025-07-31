import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Scanner;

public class miConversion {

    @SerializedName("base_code")
    String moneda_base = "";
    @SerializedName("target_code")
    String otra_moneda = "";
    String conversion_rate = "";



    public static void main(String[] args)  throws IOException, InterruptedException {



        String lol1 = "Que moneda desea usar de base";
        //    String base_code =
        Scanner escanea = new Scanner(System.in);


        //String lol = String.valueOf(new Gson());
        miConversion lol2 = new miConversion();


        System.out.println(lol1);
        String txt = escanea.next();
        String base_code = "";
        base_code = txt;

        System.out.println("ahora ingrese la segunda moneda");
        txt = escanea.next();
        String target_code = "";
        target_code = txt;

        String uri = "https://v6.exchangerate-api.com/v6/035a59fa61757caf8da3925c/pair/" + base_code + "/" + target_code;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))// esto solo dos
                    .build();       // https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD esto muestra todos los pares
        HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String json = response.body();

        // Deserialization
        miConversion obj2 = gson.fromJson(json, miConversion.class);


        String soloNumer = obj2.conversion_rate.substring(0,5);
        double stringNum = Double.parseDouble(soloNumer);

        String text = """
                El cambio oficial es de:
                 $ %s
                """.formatted(soloNumer);


        System.out.println(text);

        System.out.println("por cuanto dinero quiere cambiar?");
        double num = escanea.nextByte();

        num = num * stringNum;

        System.out.println("el resultado es de " + num);

        DecimalFormat numComas = new DecimalFormat();
        txt = numComas.format(stringNum);
        System.out.println(txt);


    }
}

/* En la séptima fase de nuestro desafío, nos sumergimos en el análisis de la respuesta JSON utilizando la biblioteca
Gson en Java. La manipulación de datos JSON es esencial, ya que la mayoría de las respuestas de las API se presentan en este formato.

--> Para facilitar el análisis de los datos que se obtendrán de la API, recomendamos el uso de herramientas como Postman.

Con la biblioteca Gson, puedes realizar el mapeo eficiente de los datos JSON a objetos Java, facilitando así la extracción
 y manipulación de la información necesaria.

Recuerda utilizar las clases proporcionadas por Gson, como JsonParser y JsonObject, para acceder a las distintas propiedades
 de la respuesta JSON. */

