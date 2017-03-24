package edu.ucla.library.libservices.pubstats.db.procs;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;
import edu.ucla.library.libservices.pubstats.beans.Submission;
import java.sql.Types;

import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.SqlParameter;

public class AddRefStatProc
  extends StoredProcedure
{
  private static final int INPUT_METHOD = 2;
  
  private DataSource ds;
  private String dbName;
  private Submission data;

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

  public void setData( Submission data )
  {
    this.data = data;
  }

  private Submission getData()
  {
    return data;
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
    setSql( "uspAddRefStat" );
    declareParameter( new SqlParameter( "@AggregateID", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@Count", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@dataMonth", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@dataYear", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@DateTime", Types.TIMESTAMP ) ); //timestamp
    declareParameter( new SqlParameter( "@LogonID", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@InputMethod", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@TimeSpent", Types.FLOAT ) ); //float
    compile();
  }

  private Map execute()
  {
    Map input;
    Map out;
    
    out = null;
    input = new HashMap();

    input.put( "@AggregateID", getData().getPointID() );
    input.put( "@Count", getData().getStats().get( 0 ).getCount() );
    input.put( "@dataMonth", getData().getDateTime() );
    input.put( "@dataYear", getData().getDateTime() );
    input.put( "@DateTime", getData().getDateTime() );
    input.put( "@LogonID", getData().getOperator() );
    input.put( "@InputMethod", INPUT_METHOD );
    input.put( "@TimeSpent", getData().getTimeSpent() / data.getStats().size() );

    out = execute( input );

    return out;
  }
}
