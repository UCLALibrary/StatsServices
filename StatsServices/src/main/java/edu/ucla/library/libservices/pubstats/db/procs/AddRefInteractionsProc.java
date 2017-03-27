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

public class AddRefInteractionsProc
  extends StoredProcedure
{
  private DataSource ds;
  private String dbName;
  private Submission data;

  public AddRefInteractionsProc( JdbcTemplate jdbcTemplate, String string )
  {
    super( jdbcTemplate, string );
  }

  public AddRefInteractionsProc( DataSource dataSource, String string )
  {
    super( dataSource, string );
  }

  public AddRefInteractionsProc()
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
    setSql( "uspAddRefInteractions" );
    declareParameter( new SqlParameter( "@dataMonth", Types.INTEGER ) );
    declareParameter( new SqlParameter( "@dataYear", Types.INTEGER ) ); 
    declareParameter( new SqlParameter( "@DateTime", Types.TIMESTAMP ) );
    declareParameter( new SqlParameter( "@LogonID", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@Topic", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@DepartmentID", Types.INTEGER ) );
    declareParameter( new SqlParameter( "@Course", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@StaffFeedback", Types.VARCHAR ) );
    declareParameter( new SqlParameter( "@PatronType", Types.INTEGER ) );
    declareParameter( new SqlParameter( "@PatronFeedback", Types.VARCHAR ) );
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
    input.put( "@Topic", getData().getInteraction().getTopic() );
    input.put( "@DepartmentID", getData().getInteraction().getDepartmentID() );
    input.put( "@Course", getData().getInteraction().getCourse() );
    input.put( "@StaffFeedback", getData().getInteraction().getStaffFeedback() );
    input.put( "@PatronType", getData().getInteraction().getPatronType() );
    input.put( "@PatronFeedback", getData().getInteraction().getPatronFeedback() );

    out = execute( input );

    return out;
  }
}
