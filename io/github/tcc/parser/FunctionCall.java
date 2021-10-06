package io.github.tcc.parser;

import java.util.ArrayList;
import java.util.List;

import io.github.tcc.Function;
import io.github.tcc.ObjectRepresentation;
import io.github.tcc.Parameter;
import io.github.tcc.Scope;
import io.github.tcc.Value;
import io.github.tcc.parser.values.ValueParser;

public class FunctionCall 
{
	public static Value callFunction(String call, Scope scope)
	{
		String name = "";
		int index = 0;
		
		for (int i = 0; call.charAt(i) != '('; i++)
		{
			name += call.charAt(i);
			index = i;
		}
		
		name = name.replaceAll("\\s*", "");
		System.out.println(name);
		
		String[] array = name.split("\\.");
		String funcName = array[1];
		
		System.out.println(funcName);
		
		ObjectRepresentation o = (ObjectRepresentation) scope.load(array[0]).getValue();
		for (int i = 1; i < array.length - 1; i++)
		{
			o = (ObjectRepresentation) o.getValue(array[i]).getValue();
		}
		
		Function func = o.getFunction(funcName);
		System.out.println(func);
		
		String parameterList = "";
		int layer = 0;
		boolean inString = false;
		for (int i = index + 2; layer >= 0 || inString; i++)
		{
			if (call.charAt(i) == '"') inString = !inString;
			if (!inString)
			{
				if (call.charAt(i) == '(') layer++;
				if (call.charAt(i) == ')') layer--;
			}
			if (layer < 0) continue;
			parameterList += call.charAt(i);
		}
		
		System.out.println(parameterList);
		
		Parameter[] parameters = parseParameters(parameterList, scope);
		
		return func.invoke(parameters);
	}
	
	private static Parameter[] parseParameters(String parameterList, Scope scope) 
	{
		List<String> parts = new ArrayList<String>();
		boolean inString = false;
		String current = "";
		int layer = 0;
		for (int i = 0; i < parameterList.length(); i++)
		{
			if (parameterList.charAt(i) == '"') inString = !inString;
			if (!inString)
			{
				if (parameterList.charAt(i) == '(') layer++;
				if (parameterList.charAt(i) == ')') layer--;
				
				if (parameterList.charAt(i) == ',' && layer == 0)
				{
					parts.add(current.trim());
					current = "";
					continue;
				}
			}
			current += parameterList.charAt(i);
		}
		if (!current.trim().isEmpty()) parts.add(current.trim());
		
		Parameter[] toReturn = new Parameter[parts.size()];
		for (int i = 0; i < toReturn.length; i++)
		{
			Parameter p = new Parameter(null, ValueParser.parseSingleValue(parts.get(i), scope));
			toReturn[i] = p;
		}
		
		return toReturn;
	}

	public static Value createObject(String call)
	{
		return null;
	}
}
