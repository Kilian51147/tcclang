package io.github.tcc.expressions;

import io.github.tcc.Value;

public class Plus extends Operator 
{
	public Plus() 
	{
		super(2, "+", OperatorParseDirection.LEFT_TO_RIGHT);
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
			String linked = v1.getValue().toString() + v2.getValue();
			return new Value(linked);
		}
	}
}
