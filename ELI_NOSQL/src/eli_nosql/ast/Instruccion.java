package eli_nosql.ast;

import java.util.*;

public class Instruccion extends NodoAST {
    private String tipo;
    private String nombre;
    private String ruta;
    private List<String> campos;
    private Map<String, Object> valores;
    private Expresion filtro;
    private Map<String, String> estructura;
    
    public Instruccion(int linea, int columna, String tipo) {
        super(linea, columna);
        this.tipo = tipo;
        this.campos = new ArrayList<>();
        this.valores = new HashMap<>();
        this.estructura = new HashMap<>();
    }
    
    // Getters y setters
    public String getTipo() { return tipo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getRuta() { return ruta; }
    public void setRuta(String ruta) { this.ruta = ruta; }
    
    public List<String> getCampos() { return campos; }
    public void addCampo(String campo) { this.campos.add(campo); }
    
    public Map<String, Object> getValores() { return valores; }
    public void addValor(String key, Object value) { this.valores.put(key, value); }
    
    public Map<String, String> getEstructura() { return estructura; }
    public void addEstructura(String campo, String tipo) { this.estructura.put(campo, tipo); }
    
    public Expresion getFiltro() { return filtro; }
    public void setFiltro(Expresion filtro) { this.filtro = filtro; }
}