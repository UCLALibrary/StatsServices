package edu.ucla.library.libservices.pubstats.db.source;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
    super();
  }
  
  public static DataSource getStatsConnection()
  {
    DriverManagerDataSource ds;
    
    ds = new DriverManagerDataSource();
    ds.setDriverClassName( "net.sourceforge.jtds.jdbc.Driver" );
    ds.setUrl( "jdbc:jtds:sqlserver://db-pubservstats.library.ucla.edu:1433/PSS_Test" );
    ds.setUsername( "Pub_Stats_Report" );
    ds.setPassword( "not our password" );
    
    return ds;
  }

  public static DataSource createDataSource( String name )
  {
    InitialContext context;
    DataSource connection;

    try
    {
      context = new InitialContext();
      connection = ( DataSource ) context.lookup( name );
    }
    catch ( NamingException e )
    {
      e.printStackTrace();
      connection = null;
    }

    return connection;
  }
}
