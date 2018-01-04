package edu.ucla.library.libservices.pubstats.generators;

import edu.ucla.library.libservices.pubstats.beans.Record;
import edu.ucla.library.libservices.pubstats.db.mappers.RecordMapper;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class RecordGenerator
{
  private static final String QUERY =
    "SELECT AggregateID,Count,rs.Created_DT,rs.LogonID,TimeSpent,PatronCount,ReferralText,Topic,DepartmentID,Course," 
    + "StaffFeedback,PatronType,PatronFeedback FROM dbo.ReferenceStatistics rs LEFT JOIN dbo.RefStatReferrals rsr ON" 
    + " rs.RecordID = rsr.RefStatID LEFT JOIN dbo.RefReferrals rr ON rsr.RefReferralID = rr.ReferralID LEFT JOIN" 
    + " dbo.RefStatInteractions rsi ON rs.RecordID = rsi.RefStatID LEFT JOIN dbo.RefInteractions ri ON " 
    + "rsi.RefInteractionID = ri.InteractionID WHERE rs.RecordID = (select max(RecordID) from dbo.ReferenceStatistics" 
    + " WHERE AggregateID NOT LIKE '%0000')";

  private Record theRecord;
  private String dbName;
  //private DriverManagerDataSource ds;
  private DataSource ds;

  public RecordGenerator()
  {
    super();
  }

  @SuppressWarnings( "unchecked" )
  public Record getTheRecord()
  {
    makeConnection();
    List<Record> records;
    records = new JdbcTemplate( ds ).query( QUERY, new RecordMapper() );
    return records.get( 0 );
    //theRecord = (Record)new JdbcTemplate( ds ).queryForObject( QUERY, new RecordMapper(), Record.class );
    //return theRecord;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  private void makeConnection()
  {
    //ds = DataSourceFactory.createDataSource( getDbName() );
    ds = DataSourceFactory.getStatsConnection();
  }
}
