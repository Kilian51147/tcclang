package io.github.tcc;

import java.util.ArrayList;
import java.util.List;

public class Scope 
{
	public Scope() {}
	
	public Scope(Scope parent)
	{
		this.parent = parent;
	}
	
	Scope parent;
	List<Variable> values = new ArrayList<Variable>();
	
	public Variable load(String name)
	{
		for (Variable var : values) if (name.equals(var.getName())) return var;
		return parent.load(name);
	}
	
	public void save(Variable variable)
	{
		for (Variable var : values) if (var.getName().equals(variable.getName())) throw new RuntimeException("Duplicate variable name: '" + variable.getName() + "'!");
		values.add(variable);
	}
	
	public void set(String name, Value value)
	{
		load(name).setValue(value);
	}
	
	public Scope createNew()
	{
		return new Scope(this);
	}
}
