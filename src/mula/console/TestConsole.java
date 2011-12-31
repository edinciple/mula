package mula.console;

import java.io.Console;
import java.io.StringReader;

import mula.parser.MulaParser;
import mula.parser.ParseException;
import mula.parser.TokenMgrError;
import mula.structure.MulaSubstance;

public class TestConsole {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
        Console console = System.console();
    	console.printf("Mula Preview on Java, (version 0.1)\n\n");
        while(true)
        {
        	console.printf("mula-preview >>> ");
        	String text = System.console().readLine();
        	MulaParser parser = new MulaParser(new StringReader(text));
        	MulaSubstance[] substances = parser.article();
            for(int i = 0;i<substances.length;i++) {
                console.printf("%1$s%n", substances[i].toString());
            }
        }
	}

}
