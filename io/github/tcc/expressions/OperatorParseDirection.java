package io.github.tcc.expressions;

public enum OperatorParseDirection 
{
	LEFT_TO_RIGHT, RIGHT_TO_LEFT;
	
	public int getStartIndex(String expression)
	{
		if (this == LEFT_TO_RIGHT) return 0;
		if (this == RIGHT_TO_LEFT) return expression.length() - 1;
		throw new RuntimeException("Unknown operator parse direction: " + this + "!");
	}
	
	public int changeLoopCounter(int current)
	{
		if (this == LEFT_TO_RIGHT) return current + 1;
		if (this == RIGHT_TO_LEFT) return current - 1;
		throw new RuntimeException("Unknown operator parse direction: " + this + "!");
	}
	
	public boolean isPossibleIndex(int current, String expression)
	{
		if (current < 0) return false;
		return !(expression.length() <= current);
	}
}
