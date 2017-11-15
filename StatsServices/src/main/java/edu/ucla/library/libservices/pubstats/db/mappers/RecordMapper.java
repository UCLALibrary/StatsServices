package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.Record;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RecordMapper
  implements RowMapper
{
  public RecordMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    Record bean;

    bean = new Record();
    bean.setAggregateID( rs.getString( "AggregateID" ) );
    bean.setCount( rs.getInt( "Count" ) );
    bean.setCourse( rs.getString( "Course" ) );
    bean.setCreatedDT( rs.getDate( "Created_DT" ) );
    bean.setDepartmentID( rs.getInt( "DepartmentID" ) );
    bean.setLogonID( rs.getString( "LogonID" ) );
    bean.setPatronCount( rs.getInt( "PatronCount" ) );
    bean.setPatronFeedback( rs.getString( "PatronFeedback" ) );
    bean.setPatronType( rs.getInt( "PatronType" ) );
    bean.setReferralText( rs.getString( "ReferralText" ) );
    bean.setStaffFeedback( rs.getString( "StaffFeedback" ) );
    bean.setTimeSpent( rs.getFloat( "TimeSpent" ) );
    bean.setTopic( rs.getString( "Topic" ) );
    
    return bean;
  }
}
