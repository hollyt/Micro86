import java.util.*;

class Token {
	Token(int type, String lexeme) {
		this.type = type;
		this.lexeme = lexeme;
	}

	Token(int type) {this(type, null);}

	public String toString() {return tokMap.get(type) + (lexeme == null ? "" : " (" + lexeme + ")");}

	int type;
	String lexeme;

	static final int 
		EOI = 0,
		ID = 1,
		INT_LITERAL = 2,
		ASSIGN_OP = 3,
		PLUS_OP = 4,
		MINUS_OP = 5,
		MUL_OP = 6,
		DIV_OP = 7,
		MOD_OP = 8,
		EQUAL_OP = 9,
		NE_OP = 10,
		GT_OP = 11,
		GE_OP = 12,
		LT_OP = 13,
		LE_OP = 14,
		L_PAREN = 15,
		R_PAREN = 16,
		L_BRACE = 17,
		R_BRACE = 18,
		COMMA = 19,
		SEMICOLON = 20,				// Keywords start here
		PROGRAM = 21,
		INT = 22,
		IF = 23,
		WHILE = 24,
		FOR = 25,
		PRINT = 26,
		READ = 27,
		END = 28;


	static final Map<Integer, String> tokMap = new TreeMap<Integer, String>();

	static {
		tokMap.put(EOI, "EOI");
		tokMap.put(ID, "ID");
		tokMap.put(INT_LITERAL, "INT_LITERAL");
		tokMap.put(ASSIGN_OP, "ASSIGN_OP");
		tokMap.put(PLUS_OP, "PLUS_OP");
		tokMap.put(MINUS_OP, "MINUS_OP");
		tokMap.put(MUL_OP, "MUL_OP");
		tokMap.put(DIV_OP, "DIV_OP");
		tokMap.put(MOD_OP, "MOD_OP");
		tokMap.put(EQUAL_OP, "EQUAL_OP");
		tokMap.put(NE_OP, "NE_OP");
		tokMap.put(GT_OP, "GT_OP");
		tokMap.put(GE_OP, "GE_OP");
		tokMap.put(LT_OP, "LT_OP");
		tokMap.put(LE_OP, "LE_OP");
		tokMap.put(L_PAREN, "L_PAREN");
		tokMap.put(R_PAREN, "R_PAREN");
		tokMap.put(L_BRACE, "L_BRACE");
		tokMap.put(R_BRACE, "R_BRACE");
		tokMap.put(COMMA, "COMMA");
		tokMap.put(SEMICOLON, "SEMICOLON");
		tokMap.put(PROGRAM, "program");
		tokMap.put(INT, "int");
		tokMap.put(IF, "if");
		tokMap.put(WHILE, "while");
		tokMap.put(FOR, "for");
		tokMap.put(PRINT, "print");
		tokMap.put(READ, "read");
		tokMap.put(END, "end");
	}

	static final Map<String, Token> keywordMap = new TreeMap<String, Token>();

	static {
		keywordMap.put("program", new Token(PROGRAM));
		keywordMap.put("int", new Token(INT));
		keywordMap.put("if", new Token(IF));
		keywordMap.put("while", new Token(WHILE));
		keywordMap.put("for", new Token(FOR));
		keywordMap.put("print", new Token(PRINT));
		keywordMap.put("read", new Token(READ));
		keywordMap.put("end", new Token(END));
	}
}
		
