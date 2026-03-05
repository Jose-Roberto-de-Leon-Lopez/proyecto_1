package eli_nosql.simbolo;

import java.util.*;

public class TablaSimbolos {
    private static TablaSimbolos instancia;
    private String baseDatosActual;
    private List<String> errores;
    
    private TablaSimbolos() {
        this.errores = new ArrayList<>();
    }
    
    public static TablaSimbolos getInstancia() {
        if (instancia == null) {
            instancia = new TablaSimbolos();
        }
        return instancia;
    }
    
    public static void reiniciar() {
        instancia = new TablaSimbolos();
    }
    
    public void setBaseDatosActual(String nombre) {
        this.baseDatosActual = nombre;
    }
    
    public String getBaseDatosActual() {
        return baseDatosActual;
    }
    
    public void addError(String msg) {
        errores.add(msg);
    }
    
    public List<String> getErrores() {
        return errores;
    }
}