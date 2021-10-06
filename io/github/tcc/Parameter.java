package io.github.tcc;

public class Parameter 
{
	String name;
	Value value;
	
	public Parameter(String name, Value value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName() 
	{
		return name;
	}

	public Value getValue() 
	{
		return value;
	}
}
