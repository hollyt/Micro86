import java.io.*;

class Lexer {
	Lexer(File file) throws IOException {
		is = new FileInputStream(file);
		advance();
		next();
	}

	Lexer(String s) throws IOException {
		is = new StringBufferInputStream(s);
		advance();
	}

	public Token next() throws IOException {
		currentToken = null;
		String lexeme = null;
		int state = START;

		while (currentToken == null) {
			boolean shouldAdvance = true;
			switch (state) {
				case START:
					lexeme = "";
					if (Character.isWhitespace(currChar)) state = START;
					else if (currChar == '#') state = COMMENT;
					else if (Character.isLetter(currChar)) state = ID;
					else if (Character.isDigit(currChar)) state = INT;
					else if (currChar == '+') currentToken = new Token(Token.PLUS_OP);
					else if (currChar == '-') currentToken = new Token(Token.MINUS_OP);
					else if (currChar == '*') currentToken = new Token(Token.MUL_OP);
					else if (currChar == '/') currentToken = new Token(Token.DIV_OP);
					else if (currChar == '%') currentToken = new Token(Token.MOD_OP);
					else if (currChar == '=') state = ASSIGN_OR_EQUAL;
					else if (currChar == '!') state = MAYBE_NE;
					else if (currChar == '>') state = GT_OR_GE;
					else if (currChar == '<') state = LT_OR_LE;
					else if (currChar == '(') currentToken = new Token(Token.L_PAREN);
					else if (currChar == ')') currentToken = new Token(Token.R_PAREN);
					else if (currChar == '{') currentToken = new Token(Token.L_BRACE);
					else if (currChar == '}') currentToken = new Token(Token.R_BRACE);
					else if (currChar == ',') currentToken = new Token(Token.COMMA);
					else if (currChar == ';') currentToken = new Token(Token.SEMICOLON);
					else if (currChar == '\0') state = EOI;
					else state = ERROR;
					break;

				case ID:
					if (Character.isLetterOrDigit(currChar)) {
						state = ID;
					}
					else {
						if (Token.keywordMap.get(lexeme) != null) {
							currentToken = Token.keywordMap.get(lexeme);
						}
						else 
							currentToken = new Token(Token.ID, lexeme);
						shouldAdvance = false;
					}
					break;

				case INT:
					if (Character.isDigit(currChar)) {
						state = INT;
					}
					else {
						currentToken = new Token(Token.INT);
						shouldAdvance = false;
					}
					break;

				case COMMENT:
					if (currChar != '\n') {
						state = COMMENT;
					}
					else {
						state = START;
					}
					break;

				case ASSIGN_OR_EQUAL:
					if (currChar == '=') {
						currentToken = new Token(Token.EQUAL_OP);
					}
					else {
						currentToken = new Token(Token.ASSIGN_OP);
						shouldAdvance = false;
					}
					break;

				case MAYBE_NE:
					if (currChar == '=') {
						currentToken = new Token(Token.NE_OP);
					}
					else {
						state = ERROR;
					}
					break;

				case GT_OR_GE:  
					if (currChar == '=') {
						currentToken = new Token(Token.GE_OP);
					}
					else {
						currentToken = new Token(Token.GT_OP);
						shouldAdvance = false;
					}
					break;

				case LT_OR_LE:
					if (currChar == '=') {
						currentToken = new Token(Token.LE_OP);
					}
					else {
						currentToken = new Token(Token.LT_OP);
						shouldAdvance = false;
					}
					break;

				case ERROR:
					System.err.println("Unexpected character " + currChar + " on line " + line + " at column " + col);
					state = START;
					break;
					
				case EOI:  currentToken = new Token(Token.EOI); break;

				default:
					System.err.println("Unknown state " + state);
					System.exit(0);
					
			}

			lexeme += currChar;
			if (shouldAdvance && state != ERROR) advance();

		}
		return currentToken;
	}

	public Token token() {return currentToken;}
		
	void advance() throws IOException {
		int i = is.read();
		currChar = (i < 0) ? '\0' : (char)i;
		if (currChar == '\n') line ++; col = 0;
		col++;
	}

	InputStream is;
	char currChar;
	Token currentToken = null;
	int line = 1, col = 1;

	static final int 
		EOI = -2,
		ERROR = -1,
		START = 0,
		ID = 1,
		INT = 2,
		COMMENT = 3,
		ASSIGN_OR_EQUAL = 4,
		MAYBE_NE = 5,
		GT_OR_GE = 6,
		LT_OR_LE = 7;
}
