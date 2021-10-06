package io.github.tcc.expressions;

import io.github.tcc.Value;

public abstract class Operator implements Comparable<Operator>
{
	int priority;
	String look;
	OperatorParseDirection parseDirection;
	
	public Operator(int priority, String look, OperatorParseDirection parseDirection)
	{
		this.priority = priority;
		this.look = look;
		this.parseDirection = parseDirection;
	}
	
	public abstract Value operate(Value v1, Value v2);

	@Override
	public int compareTo(Operator o) 
	{
		if (o.priority < priority) return -1;
		if (o.priority > priority) return 1;
		return 0;
	}

	public OperatorParseDirection getParseDirection() 
	{
		return parseDirection;
	}
}
