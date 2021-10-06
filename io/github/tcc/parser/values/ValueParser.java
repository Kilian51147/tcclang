package io.github.tcc.parser.values;

import io.github.tcc.Scope;
import io.github.tcc.Value;
import io.github.tcc.parser.FunctionCall;

public class ValueParser 
{
	public static Value parseSingleValue(String value, Scope scope)
	{
		if (value.equals("true")) return new Value(true);
		if (value.equals("false")) return new Value(false);
		if (!value.isEmpty())
		{
			if (Character.isDigit(value.charAt(0)) || value.charAt(0) == '(') 
			{
				System.err.println("Parsing Number");
				return new Value(Double.parseDouble(value.replace('(', ' ').replace(')', ' ')));
			}
			else if (value.charAt(0) == '"')
			{
				System.err.println("String Variable");
				String newValue = value.substring(1, value.length() - 1);
				System.out.println(newValue);
				return new Value(newValue);
			}
			else if (value.charAt(0) == '\'') 
			{
				System.err.println("Char Variable");
				return new Value(value.charAt(1));
			}
			else if (value.startsWith("new ") || value.startsWith("new\t") || value.startsWith("new\n"))
			{
				System.err.println("Creating Value");
				return FunctionCall.createObject(value);
			}
			else if (value.contains("("))
			{
				System.err.println("Call function");
				return FunctionCall.callFunction(value, scope);
			}
			else
			{
				System.err.println("Loading Variable");
				return scope.load(value).getValue();
			}
		}
		else
		{
			return null;
		}
	}
}
