package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentMapper
  implements RowMapper
{
  public DepartmentMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    Department bean;
    
    bean = new Department();
    bean.setDepartmentID( rs.getInt( "DepartmentID" ) );
    bean.setDepartmentName( rs.getString( "Department" ) );
    
    return bean;
  }
}
