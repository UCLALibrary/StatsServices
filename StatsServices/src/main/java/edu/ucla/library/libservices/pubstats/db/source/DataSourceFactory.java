package edu.ucla.library.libservices.pubstats.db.source;

import javax.naming.Context;
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
    ds.setPassword( "PubStatsRep_pwd" );
    
    return ds;
  }

  public static DataSource createDataSource( String name )
  {
    Context envContext;
    InitialContext context;
    DataSource connection;

    try
    {
      context = new InitialContext();
      envContext = (Context)context.lookup("java:/comp/env");
      connection = ( DataSource ) envContext.lookup( name ); //( DataSource ) context.lookup( name );
    }
    catch ( NamingException e )
    {
      e.printStackTrace();
      connection = null;
    }

    return connection;
  }
}
