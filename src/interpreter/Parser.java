package interpreter;

import java.io.IOException;

import interpreter.ast.Prog;

public interface Parser {

	Prog parseProg() throws IOException, ScannerException, ParserException;

}