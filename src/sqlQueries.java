 

public class sqlQueries {
	
	public String insertStatement(String tableName, Tuple... Attributes) {
		StringBuilder statement = new StringBuilder();
		String _statement = "INSERT INTO " + tableName + " (";
		statement.append(_statement);
		for (int i = 0; i < Attributes.length; ++i)
		{
			statement.append(Attributes[i].x);
			if (i != (Attributes.length - 1))
				statement.append(", ");
		}
		statement.append(") VALUES ('");
		for (int i = 0; i < Attributes.length; ++i)
		{
			statement.append(Attributes[i].y);
			if (i != (Attributes.length - 1))
				statement.append("','");
		}
		statement.append("');");

		_statement = statement.toString();
		System.out.println(_statement); //test
	    return _statement;
	}
	
	public String selectStatement(String tableName, String... Attributes) {
		//String sql = "SELECT id, first, last, age FROM Registration";
		StringBuilder statement = new StringBuilder();
		String _statement = "SELECT ";
		statement.append(_statement);
		for (int i = 0; i < Attributes.length; ++i)
		{
			statement.append(Attributes[i]);
			if (i != (Attributes.length - 1))
				statement.append(", ");
		}
		statement.append(" FROM "+ tableName);

		_statement = statement.toString();
		System.out.println(_statement); //test
	    return _statement;
	}

}
