package io.github.tcc;

public abstract class Function 
{
	public String name;
	
	public Function(String name)
	{
		this.name = name;
	}
	
	public abstract Value invoke(Parameter... p);
	
	public String getName()
	{
		return name;
	}
}
