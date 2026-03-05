package eli_nosql.ast;

import java.util.*;

public class Expresion extends NodoAST {
    private String tipo;
    private Object valor;
    private String operador;
    private List<Expresion> hijos;
    
    public Expresion(int linea, int columna, String tipo, Object valor) {
        super(linea, columna);
        this.tipo = tipo;
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }
    
    public Expresion(int linea, int columna, String tipo, String operador, Expresion izq, Expresion der) {
        super(linea, columna);
        this.tipo = tipo;
        this.operador = operador;
        this.hijos = new ArrayList<>();
        this.hijos.add(izq);
        this.hijos.add(der);
    }
    
    public Expresion(int linea, int columna, String tipo, String operador, Expresion hijo) {
        super(linea, columna);
        this.tipo = tipo;
        this.operador = operador;
        this.hijos = new ArrayList<>();
        this.hijos.add(hijo);
    }
    
    public String getTipo() { return tipo; }
    public Object getValor() { return valor; }
    public String getOperador() { return operador; }
    public List<Expresion> getHijos() { return hijos; }
}