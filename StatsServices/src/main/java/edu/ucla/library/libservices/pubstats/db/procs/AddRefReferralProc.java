package edu.ucla.library.libservices.pubstats.db.procs;

import edu.ucla.library.libservices.pubstats.beans.Submission;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;
import edu.ucla.library.libservices.pubstats.util.DateExtractor;

import java.sql.Types;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class AddRefReferralProc
  extends StoredProcedure
{
  private DataSource ds;
  private String dbName;
  private Submission data;
  
  public AddRefReferralProc( JdbcTemplate jdbcTemplate, String string )
  {
    super( jdbcTemplate, string );
  }

  public AddRefReferralProc( DataSource dataSource, String string )
  {
    super( dataSource, string );
  }

  public AddRefReferralProc()
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
    setSql( "PSS_Test.dbo.uspAddRefReferral" );
    declareParameter( new SqlParameter( "@dataMonth", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@dataYear", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@DateTime", Types.TIMESTAMP ) ); //timestamp
    declareParameter( new SqlParameter( "@LogonID", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@ReferralText", Types.VARCHAR ) );
    compile();
  }

  private Map execute()
  {
    Map input;
    Map out;
    
    out = null;
    input = new HashMap();

    input.put( "@dataMonth", DateExtractor.getCalendarPart( getData().getDateTime(), Calendar.MONTH ) );
    input.put( "@dataYear", DateExtractor.getCalendarPart( getData().getDateTime(), Calendar.YEAR ) );
    input.put( "@DateTime", getData().getDateTime() );
    input.put( "@LogonID", getData().getOperator() );
    input.put( "@ReferralText", getData().getReferral().getText() );

    out = execute( input );

    return out;
  }
}
