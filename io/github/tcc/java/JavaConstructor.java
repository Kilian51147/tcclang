package io.github.tcc.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.github.tcc.Function;
import io.github.tcc.Parameter;
import io.github.tcc.Value;

public class JavaConstructor extends Function
{
	Constructor<?> constructor;
	Function alternative;
	
	public JavaConstructor(Constructor<?> constructor, Function alternative) 
	{
		super("create");
		this.constructor = constructor;
		this.alternative = alternative;
	}

	@Override
	public Value invoke(Parameter... p) 
	{
		Object[] values = new Object[p.length];
		
		for (int i = 0; i < p.length; i++)
		{
			Object param = p[i].getValue().getValue();
			
			try 
			{
				if (constructor.getParameterTypes()[i].isPrimitive())
				{
					try 
					{
						values[i] = param.getClass().getMethod(constructor.getParameterTypes()[i].getTypeName() + "Value").invoke(param);
					} 
					catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
					{
						return alternative.invoke(p);
					}
				}
				else try
				{
					values[i] = constructor.getParameterTypes()[i].cast(p[i].getValue().getValue());
				}
				catch (RuntimeException e) 
				{ 
					return alternative.invoke(p);
				}
			} 
			catch (RuntimeException e) 
			{
				return alternative.invoke(p);
			}
		}
		
		try 
		{
			return new Value(constructor.newInstance(values));
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) 
		{
			return alternative.invoke(p);
		}
	}
}
