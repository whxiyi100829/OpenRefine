package com.metaweb.gridworks.expr;

import java.util.Properties;

import org.json.JSONObject;

public class LiteralExpr implements Evaluable {
	final protected Object _value;
	
	public LiteralExpr(Object value) {
		_value = value;
	}
	                          
	public Object evaluate(Properties bindings) {
		return _value;
	}

	public String toString() {
		return _value instanceof String ? JSONObject.quote((String) _value) : _value.toString();
	}
}