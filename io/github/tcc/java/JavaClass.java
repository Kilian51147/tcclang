package io.github.tcc.java;

import java.lang.reflect.Constructor;

import io.github.tcc.Function;
import io.github.tcc.ObjectRepresentation;

public class JavaClass extends ObjectRepresentation
{
	public JavaClass(String className) throws ClassNotFoundException
	{
		Class<?> c = Class.forName(className);
		
		// load Constructors
		Constructor<?>[] constructors = c.getConstructors();
		for (Constructor<?> constructor : constructors)
		{
			Function func = null;
			try
			{
				func = getFunction(constructor.getName());
			}
			catch (RuntimeException e)
			{
				
			}
			
			setFunction(new JavaConstructor(constructor, func));
		}
	}
}
