package io.github.tcc.expressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperatorBase 
{
	static List<Operator> operators = new ArrayList<Operator>();
	
	public static void addOperator(Operator operator)
	{
		operators.add(operator);
		Collections.sort(operators);
		System.out.println(operators);
	}
	
	public static List<Operator> getOperators()
	{
		return operators;
	}
}
