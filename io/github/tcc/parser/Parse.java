package io.github.tcc.parser;

import io.github.tcc.Scope;
import io.github.tcc.Value;

public class Parse 
{
	String current = "";
	Scope scope = new Scope();
	Value currentValue;
	int i = 0;
	
	public void execute(String code)
	{
		creationPhase(code);
		executionPhase(code);
	}

	private void executionPhase(String code) 
	{
		for (i = 0; i < code.length(); i++)
		{
			if (useAsSpecialCharacter(code.charAt(i)))
			{
				String keyword = current;
				current = "";
				enterSystem(keyword, i, code);
			}
			else
			{
				current += code.charAt(i);
			}
		}
		
		System.out.println();
	}
	
	private void enterSystem(String keyword, int index, String code) 
	{
		if (keyword.isEmpty()) return;
		if (keyword.equals("var")) 
		{
			i = ParserSystems.createVariable(index, code);
		}
		else
		{
			currentValue = loadValue(keyword);
		}
		
//		if (keyword.equals("new")) ParserSystems.createObject(keyword, index, code);
		
		System.out.println(keyword);
	}

	private Value loadValue(String keyword) 
	{
		return scope.load(keyword).getValue();
	}

	private boolean useAsSpecialCharacter(char character)
	{
		if (character == ';')return true;
		if (character == ' ')return true;
		if (character == '.')return true;
		
		return false;
	}

	private void creationPhase(String code) 
	{
		
	}
}
