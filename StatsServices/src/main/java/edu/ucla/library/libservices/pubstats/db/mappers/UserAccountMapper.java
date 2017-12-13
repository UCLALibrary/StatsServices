package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserAccountMapper
  implements RowMapper
{
  public UserAccountMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    UserAccount bean;
    
    bean = new UserAccount();
    bean.setLogonID( rs.getString( "LogonID" ) );
    bean.setName( rs.getString( "Name" ) );
    bean.setUnit( rs.getString( "Unit" ) );
    
    return bean;
  }
}
