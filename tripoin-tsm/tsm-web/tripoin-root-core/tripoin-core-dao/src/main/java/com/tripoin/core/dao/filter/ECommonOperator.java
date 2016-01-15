package com.tripoin.core.dao.filter;

import java.io.Serializable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public enum ECommonOperator implements Serializable {

	OBJECT_SPACE_CONSTANT(" obj"),
	OBJECT_POINT_CONSTANT("obj."),
	COMA_BOTH_SIDE_SPACE(" , "),

	FROM(" FROM "),
	WHERE(" WHERE "),
	OR(" OR "),
	AND(" AND "),
	
	EQUALS(" = ?"), 
	LESS(" < ?"), 
	LESS_EQUALS(" < ?"), 
	GREATER(" > ?"), 
	GREATER_EQUALS(" >= ?"),
	NOT_EQUALS(" <> ?"),

	fieldIsNull(" IS NULL ?"),
	fieldIsNotNull(" IS NOT NULL ?"),

	FIELD_IN(" IN "),
	FIELD_NOT_IN(" NOT IN "),

	BETWEEN(" BETWEEN ? AND ?"),
	LIKE_BOTH_SIDE(" LIKE '%'||?||'%'"),
	LIKE_SIDE_RIGHT(" LIKE '%'||?"),
	LIKE_SIDE_LEFT(" LIKE ?||'%'"),
	SORT(" ORDER BY "),
	ASC(" ASC"),
	DESC(" DESC");
	
	private String operator;	
	
	private ECommonOperator(String operator){
		this.operator = operator ;
	}
		
	@Override
	public String toString() {
		return operator;
	}
	
}
