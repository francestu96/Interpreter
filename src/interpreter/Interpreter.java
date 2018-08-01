package interpreter;

import static java.lang.System.err;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.lang.IndexOutOfBoundsException;

import interpreter.ast.Prog;
import interpreter.visitors.evaluation.Eval;
import interpreter.visitors.typechecking.TypeCheck;

public class Interpreter {
	public static void main(String[] args) {
		InputStreamReader in=new InputStreamReader(System.in);
		PrintStream out= new PrintStream(System.out);
		int i;

		try{
			for(i=0; i<args.length; i++){
	    		if(args[i].equals("-o")){
							try{
	       				out=new PrintStream(args[i+1]);
							}
							catch(IndexOutOfBoundsException e){
								err.println("Usage: Interpreter -o <destination file> <source file>");
								System.exit(1);
							}
							i++;
	    		}
	    		else{
	        		in=new FileReader(args[i]);
	    		}
			}
		}
		catch(FileNotFoundException e){
			err.println("Source file doesn't find");
			System.exit(2);
		}

		try (Tokenizer tokenizer = new StreamTokenizer(in)) {
			Parser parser = new StreamParser(tokenizer);
			Prog prog = parser.parseProg();
			out.println("Program correctly parsed: " + prog);
			prog.accept(new TypeCheck());
			out.println("Program statically correct");
			prog.accept(new Eval(out));
		} catch (ScannerException e) {
			String skipped = e.getSkipped();
			if (skipped != null)
				err.println(e.getMessage() + e.getSkipped());
			else
				err.println(e.getMessage());
		} catch (Exception e) {
			err.println(e.getMessage());
		} catch (Throwable e) {
			err.println("Unexpected error. " + e.getMessage());
		}
	}
}
