package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.UnitPoint;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PointMapper
  implements RowMapper
{
  public PointMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    UnitPoint bean;
    bean = new UnitPoint();
    bean.setServicePoint( rs.getString( "ServicePoint" ) );
    bean.setUnitName( rs.getString( "Unit" ) );
    bean.setUnitPointID( rs.getString( "UnitPointID" ) );
    return bean;
  }
}
