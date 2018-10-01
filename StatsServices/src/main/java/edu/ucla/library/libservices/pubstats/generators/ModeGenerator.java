package edu.ucla.library.libservices.pubstats.generators;

import edu.ucla.library.libservices.pubstats.beans.InteractionMode;

import edu.ucla.library.libservices.pubstats.db.mappers.ModeMapper;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ModeGenerator
{
  private static final String QUERY =
    "SELECT DISTINCT Mode, ModeID FROM View_RefUnitCategory WHERE UnitPointID = ? AND InputMethodID = 2 AND (TypeID" 
    + " <> '00' AND TypeID <> '03' AND TypeID <> '04' AND TypeID <> '07' AND TypeID <> '08' AND ModeID <> '00') ORDER BY Mode";

  private List<InteractionMode> modes;
  private String dbName;
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String unitPointID;

  public ModeGenerator()
  {
    super();
  }

  public List<InteractionMode> getModes()
  {
    makeConnection();
    modes = new JdbcTemplate( ds ).query( QUERY, new Object[]
        { getUnitPointID() }, new ModeMapper() );
    return modes;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void setUnitPointID( String unitPointID )
  {
    this.unitPointID = unitPointID;
  }

  private String getUnitPointID()
  {
    return unitPointID;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.getStatsConnection();
  }
}
