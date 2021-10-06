package io.github.tcc.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.github.tcc.Function;
import io.github.tcc.ObjectRepresentation;
import io.github.tcc.Value;
import io.github.tcc.Variable;

public class JavaObjectRepresentation extends ObjectRepresentation 
{
	public JavaObjectRepresentation(Object obj)
	{
		Class<?> c = obj.getClass();
		
		// load Fields
		Field[] fields = c.getFields();
		for (Field field : fields)
		{
			try 
			{
				Variable var = new Variable(field.getName(), new Value(field.get(getValue())));
			} 
			catch (IllegalArgumentException | IllegalAccessException e) { }
		}
		
		// load Functions
		Method[] methods = c.getMethods();
		for (Method method : methods)
		{
			Function func = null;
			try
			{
				func = getFunction(method.getName());
			}
			catch (RuntimeException e)
			{
				
			}
			
			setFunction(new JavaFunction(method.getName(), method, this, func));
		}
		
		setValue(obj);
	}
}
