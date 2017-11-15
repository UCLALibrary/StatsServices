package edu.ucla.library.libservices.pubstats.db.procs;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

public class AddRefStatInteractionProc
  extends StoredProcedure
{
  private DataSource ds;
  private String dbName;
  private int statID;
  private int interactionID;

  public AddRefStatInteractionProc( JdbcTemplate jdbcTemplate, String string )
  {
    super( jdbcTemplate, string );
  }

  public AddRefStatInteractionProc( DataSource dataSource, String string )
  {
    super( dataSource, string );
  }

  public AddRefStatInteractionProc()
  {
    super();
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void setStatID( int statID )
  {
    this.statID = statID;
  }

  private int getStatID()
  {
    return statID;
  }

  public void setInteractionID( int interactionID )
  {
    this.interactionID = interactionID;
  }

  private int getInteractionID()
  {
    return interactionID;
  }

  private void makeConnection()
  {
    //ds = DataSourceFactory.createDataSource( getDbName() );
    ds = DataSourceFactory.getStatsConnection();
  }

  public void addStat()
  {
    Map results;

    makeConnection();
    prepProc();
    results = execute();
  }

  private void prepProc()
  {
    setDataSource( ds );
    setFunction( false );
    setSql( "PSS_Test.dbo.uspAddRefStatInteraction" );
    declareParameter( new SqlParameter( "@RefStatID", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@RefInteractionID", Types.INTEGER ) ); //int
    compile();
  }

  private Map execute()
  {
    Map input;
    Map out;
    
    out = null;
    input = new HashMap();

    input.put( "@RefStatID", getStatID() );
    input.put( "@RefInteractionID", getInteractionID() );

    out = execute( input );

    return out;
  }
}
