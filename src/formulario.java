import com.google.gson.annotations.SerializedName;

public class formulario {

    @SerializedName("base_code")
    String moneda_base = "";
    @SerializedName("target_code")
    String segunda_moneda = "";
    @SerializedName("conversion_rate")
    double precio = 0;
    @SerializedName("conversion_result")
    double multiplicadoPor = 0;

    @Override
    public String toString() {
        return "La moneda " + moneda_base + " se cotiza al valor de la moneda  " + segunda_moneda + " al precio de $" + precio + " actualmente en el mercado Internacional\n" +
                " y con la cantidad que dio usted seria un total de $" + multiplicadoPor;
    }
}
// idea para futuras escalaciones de programas seria dar mas imformacion al usuario como por ejemplo el tiempo que tardo el programa
// la fecha que se hiso la comsulta y muchas otra cosas