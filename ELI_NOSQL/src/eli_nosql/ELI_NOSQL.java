package eli_nosql;

import analisis.Lexer;
import analisis.Parser;
import eli_nosql.ast.*;
import eli_nosql.simbolo.TablaSimbolos;
import java.io.StringReader;

public class ELI_NOSQL {
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   ELI-NOSQL - FASE 2: PRUEBA MÍNIMA");
        System.out.println("======================================");
        
        String codigoPrueba = """
            database universidad {
                store at "universidad.json";
            }
            """;
        
        try {
            // 1. Lexer
            Lexer lexer = new Lexer(new StringReader(codigoPrueba));
            
            // 2. Parser
            Parser parser = new Parser(lexer);
            TablaSimbolos.reiniciar();
            
            // 3. Ejecutar parseo
            parser.parse();
            
            System.out.println("✅ Análisis completado sin errores");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}