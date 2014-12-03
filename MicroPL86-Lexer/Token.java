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
		INT_LITERAL = 2
		PLUS_OP = 3,
		MINUS_OP = 4,
		MUL_OP = 5,
		DIV_OP = 6,
		EQUAL_OP = 7,
		NE_OP = 8,
		GT_OP = 9,
		GE_OP = 10,
		LT_OP = 11,
		LE_OP = 12,
		L_PAREN = 13,
		R_PAREN = 14,
		L_BRACE = 15,
		R_BRACE = 16,
		COMMA = 17,
		SEMICOLON = 18,				// Keywords start here
		PROGRAM = 19,
		INT = 20,
		IF = 21,
		WHILE = 22,
		FOR = 23,
		PRINT = 24,
		READ = 25,
		END = 26;


	static final Map<Integer, String> tokMap = new TreeMap<Integer, String>();

	static {
		tokMap.put(EOI, "EOI");
		tokMap.put(ID, "ID");
		tokMap.put(INT_LITERAL, "INT_LITERAL");
		tokMap.put(PLUS_OP, "PLUS_OP");
		tokMap.put(MINUS_OP, "MINUS_OP");
		tokMap.put(MUL_OP, "MUL_OP");
		tokMap.put(DIV_OP, "DIV_OP");
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
		
