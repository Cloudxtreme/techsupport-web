package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.util.Map;

public interface Template
{
	public abstract Template bind (String key, String value);

	public abstract String build ( )
	throws TemplateParameterMissing;
}