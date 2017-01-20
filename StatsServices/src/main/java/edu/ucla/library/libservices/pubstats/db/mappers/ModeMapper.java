package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.InteractionMode;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ModeMapper
  implements RowMapper
{
  public ModeMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    InteractionMode bean;
    bean = new InteractionMode();
    bean.setModeID( rs.getString( "ModeID" ) );
    bean.setModeType( rs.getString( "Mode" ) );
    return bean;
  }
}
