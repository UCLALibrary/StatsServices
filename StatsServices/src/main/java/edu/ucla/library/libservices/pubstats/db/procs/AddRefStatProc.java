package edu.ucla.library.libservices.pubstats.db.procs;

import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.sql.Types;

import java.time.ZoneOffset;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class AddRefStatProc
  extends StoredProcedure
{
  private static final int INPUT_METHOD = 2;
  
  private DataSource ds;
  private String dbName;
  private StatsLine data;

  public AddRefStatProc( JdbcTemplate jdbcTemplate, String string )
  {
    super( jdbcTemplate, string );
  }

  public AddRefStatProc( DataSource dataSource, String string )
  {
    super( dataSource, string );
  }

  public AddRefStatProc()
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

  public void setData( StatsLine data )
  {
    this.data = data;
  }

  private StatsLine getData()
  {
    return data;
  }

  private void makeConnection()
  {
    //ds = DataSourceFactory.createDataSource( getDbName() );
    ds = DataSourceFactory.getStatsConnection();
  }

  public Map addStat()
  {
    Map results;

    makeConnection();
    prepProc();
    results = execute();
    
    return results;
  }

  private void prepProc()
  {
    setDataSource( ds );
    setFunction( false );
    setSql( "PSS_Test.dbo.uspAddRefStat" );
    declareParameter( new SqlParameter( "@AggregateID", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@Count", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@dataMonth", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@dataYear", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@DateTime", Types.TIMESTAMP ) ); //timestamp
    declareParameter( new SqlParameter( "@LogonID", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@InputMethod", Types.INTEGER ) );
    declareParameter( new SqlParameter( "@TimeSpent", Types.FLOAT ) ); //float
    declareParameter( new SqlParameter( "@PatronCount", Types.INTEGER ) ); //int
    compile();
  }

  private Map execute()
  {
    Map input;
    Map out;
    
    out = null;
    input = new HashMap();

    input.put( "@AggregateID", getData().getAggregateID() );
    input.put( "@Count", getData().getCount() );
    input.put( "@dataMonth", getData().getDataMonth() );
    input.put( "@dataYear", getData().getDataYear() );
    input.put( "@DateTime", getData().getDateTime() );
    input.put( "@LogonID", getData().getLogonID() );
    input.put( "@InputMethod", INPUT_METHOD );
    input.put( "@TimeSpent", getData().getTimeSpent() );
    input.put( "@PatronCount", getData().getPatronCount() );

    out = execute( input );

    return out;
  }
}
