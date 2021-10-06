package io.github.tcc;

public class Variable 
{
	final String name;
	Value value;
	
	public Variable(String name, Value value)
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

	public void setValue(Value value) 
	{
		this.value = value;
	}
}
