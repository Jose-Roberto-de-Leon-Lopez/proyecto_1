package eli_nosql.ast;

public abstract class NodoAST {
    private int linea;
    private int columna;
    
    public NodoAST(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }
    
    public int getLinea() { return linea; }
    public int getColumna() { return columna; }
}