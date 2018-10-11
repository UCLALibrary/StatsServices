package edu.ucla.library.libservices.pubstats.generators;

import edu.ucla.library.libservices.pubstats.beans.UserAccount;
import edu.ucla.library.libservices.pubstats.db.mappers.UserAccountMapper;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.jdbc.core.JdbcTemplate;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "Users" )
public class UserAccountGenerator
{
  private static final String USER_QUERY =
    "SELECT DISTINCT rua.LogonID,rua.FirstName + ' ' + rua.LastName AS Name,ru.Descrpt AS Unit FROM dbo.RefUserAccounts rua" +
    " LEFT OUTER JOIN dbo.RefUserUnit ruu ON rua.AccountID = ruu.RefUserAccountID LEFT OUTER JOIN dbo.RefUnit ru" +
    " ON ruu.RefUnitID = ru.UnitID WHERE rua.AccountType = 2 AND rua.Active = 1";
  private static final String ALL_USERS_QUERY =
    "SELECT DISTINCT rua.LogonID,rua.FirstName + ' ' + rua.LastName AS Name,ru.Descrpt AS Unit FROM dbo.RefUserAccounts rua" +
    " LEFT OUTER JOIN dbo.RefUserUnit ruu ON rua.AccountID = ruu.RefUserAccountID LEFT OUTER JOIN dbo.RefUnit ru" +
    " ON ruu.RefUnitID = ru.UnitID WHERE rua.Active = 1";


  @XmlElement( name = "users" )
  private List<UserAccount> users;
  private String dbName;
  private DataSource ds;

  public UserAccountGenerator()
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

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.getStatsConnection();
  }

  public List<UserAccount> getUsers()
  {
    return users;
  }

  public void populateUsers( String type )
  {
    makeConnection();

    if ( type.equalsIgnoreCase( "all" ) )
      users = new JdbcTemplate( ds ).query( ALL_USERS_QUERY, new UserAccountMapper() );
    else
      users = new JdbcTemplate( ds ).query( USER_QUERY, new UserAccountMapper() );
  }
}
