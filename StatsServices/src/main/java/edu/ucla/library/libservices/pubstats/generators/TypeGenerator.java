package edu.ucla.library.libservices.pubstats.generators;

import edu.ucla.library.libservices.pubstats.beans.QuestionType;

import edu.ucla.library.libservices.pubstats.db.mappers.QuestionMapper;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TypeGenerator
{
  private static final String QUERY =
    "SELECT DISTINCT QuestionType, HelpText, TypeID FROM View_RefUnitCategory WHERE UnitPointID = ? AND InputMethodID" 
    + " = 2 AND (TypeID <> '00' AND TypeID <> '03' AND TypeID <> '04' AND TypeID <> '07' AND TypeID <> '08' AND" 
    + " ModeID <> '00') ORDER BY QuestionType";

  private List<QuestionType> types;
  private String dbName;
  private DataSource ds;
  private String unitPointID;

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.getStatsConnection();
  }

  public List<QuestionType> getTypes()
  {
    makeConnection();
    types = new JdbcTemplate( ds ).query( QUERY, new Object[]
        { getUnitPointID() }, new QuestionMapper() );
    return types;
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

  public String getUnitPointID()
  {
    return unitPointID;
  }

  private TypeGenerator()
  {
    super();
  }
}
