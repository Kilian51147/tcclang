import io.github.tcc.Scope;
import io.github.tcc.Variable;
import io.github.tcc.expressions.Minus;
import io.github.tcc.expressions.OperatorBase;
import io.github.tcc.expressions.Plus;
import io.github.tcc.java.JavaObjectRepresentation;
import io.github.tcc.parser.values.ValueParser;

public class Main 
{
	public static void main(String[] args) throws ClassNotFoundException 
	{
		OperatorBase.addOperator(new Plus());
		OperatorBase.addOperator(new Minus());
		
		Scope scope = new Scope();
		scope.save(new Variable("frame", new JavaObjectRepresentation(new javax.swing.JFrame())));
		scope.save(new Variable("text", new JavaObjectRepresentation(new javax.swing.JTextArea())));

		ValueParser.parseSingleValue("frame.setVisible(true)", scope);
		ValueParser.parseSingleValue("frame.add(text)", scope);
		ValueParser.parseSingleValue("frame.setSize(800, 500)", scope);
		ValueParser.parseSingleValue("frame.setTitle(\"Hallo Welt!\")", scope);
		
//		Parse parser = new Parse();
//		parser.execute("var test = 5 + 4;");
		
//		JavaClass c = new JavaClass("javax.swing.JFrame");
//		
//		JavaObjectRepresentation o = new JavaObjectRepresentation(c.getFunction("create").invoke().getValue());
//		o.getFunction("setSize").invoke(new Parameter("width", new Value(800)), new Parameter("height", new Value(500)));
//		o.getFunction("setTitle").invoke(new Parameter("title", new Value("800")));
//		o.getFunction("setLocationRelativeTo").invoke(new Parameter("centered", new Value(null)));
//		o.getFunction("setVisible").invoke(new Parameter("visible", new Value(true)));
	}
}
