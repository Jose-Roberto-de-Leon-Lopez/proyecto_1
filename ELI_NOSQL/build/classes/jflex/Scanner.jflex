package analisis;

import java_cup.runtime.Symbol;
import java.util.ArrayList;
import java.util.List;

%%

%class Lexer
%unicode
%cup
%line
%column
%public

%{
    private List<LexicalError> lexicalErrors = new ArrayList<>();
    
    public static class LexicalError {
        public String type;
        public String description;
        public int line;
        public int column;
        
        public LexicalError(String type, String description, int line, int column) {
            this.type = type;
            this.description = description;
            this.line = line;
            this.column = column;
        }
        
        @Override
        public String toString() {
            return "Error " + type + ": " + description + " en línea " + line + ", columna " + column;
        }
    }
    
    public List<LexicalError> getLexicalErrors() {
        return lexicalErrors;
    }
    
    public void clearErrors() {
        lexicalErrors.clear();
    }
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

/* Definiciones regulares */
Letra = [a-zA-Z]
Digito = [0-9]
Entero = {Digito}+
Decimal = {Digito}+ "." {Digito}+
Identificador = {Letra}({Letra}|{Digito}|"_")*
Espacio = [ \t\r\n\f]+
ComillaDoble = \"
Cadena = {ComillaDoble}([^\"\n]|"\\\"")*{ComillaDoble}
ComentarioLinea = "##".*
ComentarioMultilineaInicio = "#*"
ComentarioMultilineaFin = "*#"

/* Palabras reservadas */
DB = "database"
USE = "use"
TABLE = "table"
READ = "read"
ADD = "add"
UPDATE = "update"
CLEAR = "clear"
EXPORT = "export"
STOREAT = "store at"
FIELDS = "fields"
FILTER = "filter"
SET = "set"

/* Tipos de datos */
INT = "int"
FLOAT = "float"
BOOL = "bool"
STRING = "string"
ARRAY = "array"

/* Valores booleanos */
TRUE = "true"
FALSE = "false"

/* Operadores */
IGUALDAD = "=="
DIFERENTE = "!="
MAYOR = ">"
MENOR = "<"
MAYOR_IGUAL = ">="
MENOR_IGUAL = "<="
AND = "&&"
OR = "||"
NOT = "!"

/* Signos */
LLAVE_IZQ = "{"
LLAVE_DER = "}"
PAREN_IZQ = "("
PAREN_DER = ")"
CORCHETE_IZQ = "["
CORCHETE_DER = "]"
PUNTOCOMA = ";"
COMA = ","
DOS_PUNTOS = ":"
IGUAL = "="
ASTERISCO = "*"

%state COMENTARIO_MULTILINEA

%%

/* Palabras reservadas */
{DB}                    { return symbol(sym.DATABASE); }
{USE}                   { return symbol(sym.USE); }
{TABLE}                 { return symbol(sym.TABLE); }
{READ}                  { return symbol(sym.READ); }
{ADD}                   { return symbol(sym.ADD); }
{UPDATE}                { return symbol(sym.UPDATE); }
{CLEAR}                 { return symbol(sym.CLEAR); }
{EXPORT}                { return symbol(sym.EXPORT); }
{STOREAT}               { return symbol(sym.STOREAT); }
{FIELDS}                { return symbol(sym.FIELDS); }
{FILTER}                { return symbol(sym.FILTER); }
{SET}                   { return symbol(sym.SET); }

/* Tipos de datos */
{INT}                   { return symbol(sym.INT); }
{FLOAT}                 { return symbol(sym.FLOAT); }
{BOOL}                  { return symbol(sym.BOOL); }
{STRING}                { return symbol(sym.STRING); }
{ARRAY}                 { return symbol(sym.ARRAY); }

/* Valores booleanos */
{TRUE}                  { return symbol(sym.TRUE, true); }
{FALSE}                 { return symbol(sym.FALSE, false); }

/* Operadores */
{IGUALDAD}              { return symbol(sym.IGUALDAD); }
{DIFERENTE}             { return symbol(sym.DIFERENTE); }
{MAYOR}                 { return symbol(sym.MAYOR); }
{MENOR}                 { return symbol(sym.MENOR); }
{MAYOR_IGUAL}           { return symbol(sym.MAYOR_IGUAL); }
{MENOR_IGUAL}           { return symbol(sym.MENOR_IGUAL); }
{AND}                   { return symbol(sym.AND); }
{OR}                    { return symbol(sym.OR); }
{NOT}                   { return symbol(sym.NOT); }

/* Signos */
{LLAVE_IZQ}             { return symbol(sym.LLAVE_IZQ); }
{LLAVE_DER}             { return symbol(sym.LLAVE_DER); }
{PAREN_IZQ}             { return symbol(sym.PAREN_IZQ); }
{PAREN_DER}             { return symbol(sym.PAREN_DER); }
{CORCHETE_IZQ}          { return symbol(sym.CORCHETE_IZQ); }
{CORCHETE_DER}          { return symbol(sym.CORCHETE_DER); }
{PUNTOCOMA}             { return symbol(sym.PUNTOCOMA); }
{COMA}                  { return symbol(sym.COMA); }
{DOS_PUNTOS}            { return symbol(sym.DOS_PUNTOS); }
{IGUAL}                 { return symbol(sym.IGUAL); }
{ASTERISCO}             { return symbol(sym.ASTERISCO); }

/* Literales */
{Entero}                { return symbol(sym.ENTERO, Integer.parseInt(yytext())); }
{Decimal}               { return symbol(sym.DECIMAL, Float.parseFloat(yytext())); }
{Cadena}                { 
    String str = yytext();
    str = str.substring(1, str.length() - 1);
    str = str.replace("\\\"", "\"");
    return symbol(sym.CADENA, str); 
}
{Identificador}         { return symbol(sym.IDENTIFICADOR, yytext()); }

/* Comentarios */
{ComentarioLinea}       { /* Ignorar */ }
{ComentarioMultilineaInicio} { yybegin(COMENTARIO_MULTILINEA); }

<COMENTARIO_MULTILINEA> {
    {ComentarioMultilineaFin}  { yybegin(YYINITIAL); }
    [^]                       { /* Ignorar */ }
    <<EOF>>                    { 
        lexicalErrors.add(new LexicalError("Léxico", 
            "Comentario multilínea no cerrado", yyline + 1, yycolumn + 1));
        yybegin(YYINITIAL);
    }
}

/* Espacios */
{Espacio}               { /* Ignorar */ }

/* Errores */
[^]                     { 
    lexicalErrors.add(new LexicalError("Léxico", 
        "El carácter '" + yytext() + "' no pertenece al lenguaje", 
        yyline + 1, yycolumn + 1));
}

<<EOF>>                 { return symbol(sym.EOF); }