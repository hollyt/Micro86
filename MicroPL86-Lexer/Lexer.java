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
					else if (currChar == '#') {
						state = COMMENT;
					}
					else if (Character.isLetter(currChar)) state = ID;
					else if (currChar == '+') currentToken = new Token(Token.PLUS_OP);
					else if (currChar == '>') state = GT_OR_GE;
					else if (currChar == ')') currentToken = new Token(Token.RPAREN);
					...
					else if (currChar == '\0') state = EOI;
					else state = ERROR;
					break;

				case COMMENT:
					...

				case ID:
					if (Character.isLetterOrDigit(currChar))
						state = ID;
					else {
						if (/* lexeme is a keyword */) != null) 
							currentToken = /* the token corresponding to the keyword */
						else 
							currentToken = new Token(Token.ID, lexeme);
						shouldAdvance = false;
					}
					break;

					
				case GT_OR_GE:  
					if (currChar == '=') 
						currentToken = new Token(Token.GE_OP); 
					else {
						currentToken = new Token(Token.GT_OP);
						shouldAdvance = false;
					}
					break;

				...
				...

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
		GT_OR_GE = ...,
		...
		COMMENT = ...;
}
