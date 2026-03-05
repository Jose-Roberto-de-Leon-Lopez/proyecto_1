package eli_nosql.ast;

import java.util.ArrayList;
import java.util.List;

public class Programa extends NodoAST {
    private List<Instruccion> instrucciones;
    
    public Programa(int linea, int columna) {
        super(linea, columna);
        this.instrucciones = new ArrayList<>();
    }
    
    public void addInstruccion(Instruccion inst) {
        instrucciones.add(inst);
    }
    
    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }
}