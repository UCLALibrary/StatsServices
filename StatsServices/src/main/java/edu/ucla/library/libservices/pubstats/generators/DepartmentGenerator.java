package edu.ucla.library.libservices.pubstats.generators;

import edu.ucla.library.libservices.pubstats.beans.Department;

import edu.ucla.library.libservices.pubstats.db.mappers.DepartmentMapper;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.jdbc.core.JdbcTemplate;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "Departments" )
public class DepartmentGenerator
{
  private static final String DEPT_QUERY =
    "SELECT DepartmentID, Department FROM SIA.dbo.DepartmentLookup ORDER BY Ordering, Department";

  @XmlElement( name = "department" )
  private List<Department> departments;
  private String dbName;
  private DataSource ds;

  public DepartmentGenerator()
  {
    super();
  }

  public void populateDepartments()
  {
    makeConnection();

    departments = new JdbcTemplate( ds ).query( DEPT_QUERY, new DepartmentMapper() );
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

  public List<Department> getDepartments()
  {
    return departments;
  }
}
