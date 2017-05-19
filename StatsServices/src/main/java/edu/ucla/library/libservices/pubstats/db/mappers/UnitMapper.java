package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UnitMapper
  implements RowMapper
{
  public UnitMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    Unit bean;
    bean = new Unit();
    bean.setUnitID( rs.getString( "UnitID" ) );
    bean.setUnitName( rs.getString( "Unit" ) );
    return bean;
  }
}
