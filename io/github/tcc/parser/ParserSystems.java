package io.github.tcc.parser;

import io.github.tcc.Value;

public class ParserSystems 
{
	public static int createVariable(int index, String code) 
	{
		String varName = "";
		
		for (int i = index; (code.charAt(i) + "").matches("\\s"); i++)
		{
			index = i;
		}
		
		for (int i = index + 1; Character.isAlphabetic(code.charAt(i)); i++)
		{
			varName += code.charAt(i);
			index = i;
		}
		
		for (int i = index + 1; (code.charAt(i) + "").matches("\\s"); i++)
		{
			index = i;
		}
		
		if (code.charAt(index + 1) != '=')
		{
			throw new RuntimeException("'=' expected");
		}
		
		index += 1;
		
		for (int i = index + 1; (code.charAt(i) + "").matches("\\s"); i++)
		{
			index = i;
		}
		
		Value parsed = parseExpressionValue(index, code);
		
		System.out.println(varName);
		
		return index;
	}

	private static Value parseExpressionValue(int index, String code) 
	{
		String expression = "";
		boolean inString = false;
		for (int i = index + 1; code.charAt(i) != ';' || inString; i++)
		{
			expression += code.charAt(i);
			if (code.charAt(i) == '"') inString = !inString;
		}
		System.out.println(expression);
		return parseExpression(expression);
	}

	private static Value parseExpression(String expression) 
	{
		System.err.println(expression);
		return null;
	}
}
