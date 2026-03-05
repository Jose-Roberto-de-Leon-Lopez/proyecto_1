package eli_nosql.util;

import analisis.Lexer;
import analisis.sym;
import java.util.ArrayList;
import java.util.List;

public class ReporteUtil {
    
    public static class TokenInfo {
        public int id;
        public String nombre;
        public String lexema;
        public int linea;
        public int columna;
        
        public TokenInfo(int id, String nombre, String lexema, int linea, int columna) {
            this.id = id;
            this.nombre = nombre;
            this.lexema = lexema;
            this.linea = linea;
            this.columna = columna;
        }
    }
    
    private List<TokenInfo> tokens = new ArrayList<>();
    
    public void addToken(int id, String nombre, String lexema, int linea, int columna) {
        tokens.add(new TokenInfo(id, nombre, lexema, linea, columna));
    }
    
    public void clearTokens() {
        tokens.clear();
    }
    
    public String generarReporteTokensHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Reporte de Tokens - ELI-NOSQL</title>\n");
        html.append("    <style>\n");
        html.append("        body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("        h1 { color: #2c3e50; }\n");
        html.append("        table { border-collapse: collapse; width: 100%; }\n");
        html.append("        th { background-color: #3498db; color: white; padding: 10px; }\n");
        html.append("        td { border: 1px solid #ddd; padding: 8px; }\n");
        html.append("        tr:nth-child(even) { background-color: #f2f2f2; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Reporte de Tokens - ELI-NOSQL</h1>\n");
        html.append("    <table>\n");
        html.append("        <tr><th>#</th><th>Token ID</th><th>Nombre</th><th>Lexema</th><th>Línea</th><th>Columna</th></tr>\n");
        
        int contador = 1;
        for (TokenInfo token : tokens) {
            html.append("        <tr>");
            html.append("<td>").append(contador++).append("</td>");
            html.append("<td>").append(token.id).append("</td>");
            html.append("<td>").append(token.nombre).append("</td>");
            html.append("<td>").append(token.lexema).append("</td>");
            html.append("<td>").append(token.linea).append("</td>");
            html.append("<td>").append(token.columna).append("</td>");
            html.append("</tr>\n");
        }
        
        html.append("    </table>\n");
        html.append("</body>\n");
        html.append("</html>\n");
        
        return html.toString();
    }
    
    public String generarReporteErroresHTML(Lexer lexer) {
        StringBuilder html = new StringBuilder();
        List<Lexer.LexicalError> errores = lexer.getLexicalErrors();
        
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Reporte de Errores Léxicos - ELI-NOSQL</title>\n");
        html.append("    <style>\n");
        html.append("        body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("        h1 { color: #c0392b; }\n");
        html.append("        table { border-collapse: collapse; width: 100%; }\n");
        html.append("        th { background-color: #e74c3c; color: white; padding: 10px; }\n");
        html.append("        td { border: 1px solid #ddd; padding: 8px; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Reporte de Errores Léxicos - ELI-NOSQL</h1>\n");
        
        if (errores.isEmpty()) {
            html.append("    <p>No se encontraron errores léxicos.</p>\n");
        } else {
            html.append("    <table>\n");
            html.append("        <tr><th>#</th><th>Tipo</th><th>Descripción</th><th>Línea</th><th>Columna</th></tr>\n");
            
            int contador = 1;
            for (Lexer.LexicalError error : errores) {
                html.append("        <tr>");
                html.append("<td>").append(contador++).append("</td>");
                html.append("<td>").append(error.type).append("</td>");
                html.append("<td>").append(error.description).append("</td>");
                html.append("<td>").append(error.line).append("</td>");
                html.append("<td>").append(error.column).append("</td>");
                html.append("</tr>\n");
            }
            html.append("    </table>\n");
        }
        
        html.append("</body>\n");
        html.append("</html>\n");
        
        return html.toString();
    }
    
    public static String getTokenName(int tokenId) {
        switch (tokenId) {
            case sym.DATABASE: return "DATABASE";
            case sym.USE: return "USE";
            case sym.TABLE: return "TABLE";
            case sym.READ: return "READ";
            case sym.ADD: return "ADD";
            case sym.UPDATE: return "UPDATE";
            case sym.CLEAR: return "CLEAR";
            case sym.EXPORT: return "EXPORT";
            case sym.STOREAT: return "STOREAT";
            case sym.FIELDS: return "FIELDS";
            case sym.FILTER: return "FILTER";
            case sym.SET: return "SET";
            case sym.INT: return "INT";
            case sym.FLOAT: return "FLOAT";
            case sym.BOOL: return "BOOL";
            case sym.STRING: return "STRING";
            case sym.ARRAY: return "ARRAY";
            case sym.TRUE: return "TRUE";
            case sym.FALSE: return "FALSE";
            case sym.ENTERO: return "ENTERO";
            case sym.DECIMAL: return "DECIMAL";
            case sym.CADENA: return "CADENA";
            case sym.IDENTIFICADOR: return "IDENTIFICADOR";
            case sym.IGUALDAD: return "IGUALDAD";
            case sym.DIFERENTE: return "DIFERENTE";
            case sym.MAYOR: return "MAYOR";
            case sym.MENOR: return "MENOR";
            case sym.MAYOR_IGUAL: return "MAYOR_IGUAL";
            case sym.MENOR_IGUAL: return "MENOR_IGUAL";
            case sym.AND: return "AND";
            case sym.OR: return "OR";
            case sym.NOT: return "NOT";
            case sym.LLAVE_IZQ: return "LLAVE_IZQ";
            case sym.LLAVE_DER: return "LLAVE_DER";
            case sym.PAREN_IZQ: return "PAREN_IZQ";
            case sym.PAREN_DER: return "PAREN_DER";
            case sym.CORCHETE_IZQ: return "CORCHETE_IZQ";
            case sym.CORCHETE_DER: return "CORCHETE_DER";
            case sym.PUNTOCOMA: return "PUNTOCOMA";
            case sym.COMA: return "COMA";
            case sym.DOS_PUNTOS: return "DOS_PUNTOS";
            case sym.IGUAL: return "IGUAL";
            case sym.ASTERISCO: return "ASTERISCO";
            default: return "DESCONOCIDO(" + tokenId + ")";
        }
    }
}