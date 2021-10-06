package io.github.tcc;

public class Value 
{
	Object value;
	boolean primitive = false;
	
	public Value(Object value)
	{
		this.value = value;
	}
	
	public Value(double value)
	{
		this.value = value;
		primitive = true;
	}
	
	public Value(boolean value)
	{
		this.value = value;
		primitive = true;
	}
	
	public Value(char value)
	{
		this.value = value;
		primitive = true;
	}
	
	public Class<?> getType()
	{
		if (primitive)
		{
			if (value.getClass() == Boolean.class)
			{
				return boolean.class;
			}
			if (value.getClass() == Double.class)
			{
				return double.class;
			}
			if (value.getClass() == Character.class)
			{
				return char.class;
			}
		}
		return value.getClass();
	}

	public Object getValue() 
	{
		return value;
	}

	public void setValue(Object value) 
	{
		primitive = false;
		this.value = value;
	}
	
	public void setValue(double value) 
	{
		primitive = true;
		this.value = value;
	}
	
	public void setValue(boolean value) 
	{
		primitive = true;
		this.value = value;
	}
	
	public void setValue(char value) 
	{
		primitive = true;
		this.value = value;
	}

	public boolean isPrimitive() {
		return primitive;
	}
}
