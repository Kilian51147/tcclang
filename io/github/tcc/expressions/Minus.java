package io.github.tcc.expressions;

import io.github.tcc.Value;

public class Minus extends Operator 
{
	public Minus() 
	{
		super(1, "-", OperatorParseDirection.LEFT_TO_RIGHT);
	}

	@Override
	public Value operate(Value v1, Value v2) 
	{
		if (v1.getValue() instanceof Number && v2.getValue() instanceof Number)
		{
			Number n1 = (Number) v1.getValue();
			Number n2 = (Number) v2.getValue();
			return new Value(n1.doubleValue() + n2.doubleValue());
		}
		else
		{
			throw new RuntimeException("Operator '-' is not defined for argument types: " + v1.getType().getName() + " and " + v2.getType().getName() + "!");
		}
	}
}
