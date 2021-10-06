package io.github.tcc.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.github.tcc.Function;
import io.github.tcc.Parameter;
import io.github.tcc.Value;

public class JavaFunction extends Function 
{
	public Function alternative;
	
	Method method;
	JavaObjectRepresentation obj;
	
	public JavaFunction(String name, Method method, JavaObjectRepresentation obj, Function alternative) 
	{
		super(name);
		this.method = method;
		this.obj = obj;
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
				if (method.getParameterTypes()[i].isPrimitive())
				{
					try 
					{
						values[i] = param.getClass().getMethod(method.getParameterTypes()[i].getTypeName() + "Value").invoke(param);
					} 
					catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
					{
						return alternative.invoke(p);
					}
				}
				else try
				{
					values[i] = method.getParameterTypes()[i].cast(p[i].getValue().getValue());
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
			return new Value(method.invoke(obj.getValue(), values));
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			return alternative.invoke(p);
		}
	}
	
}
