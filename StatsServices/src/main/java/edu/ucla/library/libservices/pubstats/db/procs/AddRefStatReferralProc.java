package edu.ucla.library.libservices.pubstats.db.procs;

import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class AddRefStatReferralProc
  extends StoredProcedure
{
  private DataSource ds;
  private String dbName;
  private int statID;
  private int referralID;

  public AddRefStatReferralProc( JdbcTemplate jdbcTemplate, String string )
  {
    super( jdbcTemplate, string );
  }

  public AddRefStatReferralProc( DataSource dataSource, String string )
  {
    super( dataSource, string );
  }

  public AddRefStatReferralProc()
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

  public void setReferralID( int referralID )
  {
    this.referralID = referralID;
  }

  private int getReferralID()
  {
    return referralID;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.getStatsConnection();
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
    setSql( "dbo.uspAddRefStatReferral" );
    declareParameter( new SqlParameter( "@RefStatID", Types.INTEGER ) ); //int
    declareParameter( new SqlParameter( "@RefReferralID", Types.INTEGER ) ); //int
    compile();
  }

  private Map execute()
  {
    Map input;
    Map out;
    
    out = null;
    input = new HashMap();

    input.put( "@RefStatID", getStatID() );
    input.put( "@RefReferralID", getReferralID() );

    out = execute( input );

    return out;
  }
}
