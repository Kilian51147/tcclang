package io.github.tcc;

import java.util.ArrayList;
import java.util.List;

public class ObjectRepresentation extends Value
{
	public ObjectRepresentation()
	{
		super(0);
		setValue(this);
	}
	
	List<Variable> values = new ArrayList<Variable>();
	List<Function> functions = new ArrayList<Function>();
	
	public void setVariable(Value value, String name)
	{
		Variable variable = new Variable(name, value);
		for (Variable var : values) if (var.getName().equals(name))
			{
				values.remove(var);
				break;
			}
		
		values.add(variable);
	}
	
	public void setFunction(Function function)
	{
		for (Function func : functions)if (func.getName().equals(function.getName()))
			{
				functions.remove(func);
				break;
			}
		
		functions.add(function);
	}
	
	public void setFunction(Function function, String name)
	{
		for (Function func : functions) if (func.getName().equals(name))
			{
				functions.remove(func);
				break;
			}
		
		Function newFunction = new Function(name) 
		{
			@Override
			public Value invoke(Parameter... p) 
			{
				return function.invoke(p);
			}
		};
		
		functions.add(newFunction);
	}
	
	public Value getValue(String name)
	{
		for (int i = 0; i < values.size(); i++) if (values.get(i).getName().equals(name)) return values.get(i).getValue();
		throw new RuntimeException("No variable with such name: " + name + "!");
	}
	
	public Function getFunction(String name)
	{
		for (int i = 0; i < functions.size(); i++) if (functions.get(i).getName().equals(name)) return functions.get(i);
		throw new RuntimeException("No function with such name: " + name + "!");
	}
}
