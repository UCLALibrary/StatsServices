package edu.ucla.library.libservices.pubstats.generators;

import edu.ucla.library.libservices.pubstats.beans.Unit;

import edu.ucla.library.libservices.pubstats.beans.UnitPoint;

import edu.ucla.library.libservices.pubstats.db.mappers.UnitMapper;
import edu.ucla.library.libservices.pubstats.db.mappers.UnitPointMapper;
import edu.ucla.library.libservices.pubstats.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.jdbc.core.JdbcTemplate;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "library" )
public class UnitPointGenerator
{
  private static final String ALL_POINTS =
    "SELECT DISTINCT ru.UnitID + rsp.PointID AS UnitPointID, ru.Descrpt AS Unit, rsp.Descrpt AS ServicePoint FROM" +
    " PSS_Test.dbo.RefUnitCategory ruc INNER JOIN PSS_Test.dbo.RefUnit ru ON ruc.UnitID = ru.UnitID inner join " +
    "PSS_Test.dbo.RefServicePoint rsp ON ruc.PointID = rsp.PointID WHERE ruc.Active = 1";
  private static final String ALL_UNITS =
    "SELECT DISTINCT ru.UnitID, ru.Descrpt AS Unit FROM PSS_Test.dbo.RefUnitCategory ruc INNER " +
    "JOIN PSS_Test.dbo.RefUnit ru ON ruc.UnitID = ru.UnitID WHERE ruc.Active = 1";
  private static final String UNIT_POINTS =
    "SELECT DISTINCT ru.UnitID + rsp.PointID AS UnitPointID,ru.Descrpt AS Unit,rsp.Descrpt AS ServicePoint FROM " +
    "PSS_Test.dbo.RefUnitCategory ruc INNER JOIN PSS_Test.dbo.RefUnit ru ON ruc.UnitID = ru.UnitID INNER JOIN " +
    "PSS_Test.dbo.RefServicePoint rsp ON ruc.PointID = rsp.PointID WHERE ru.UnitID = ?";

  @XmlElement( name = "units" )
  private List<Unit> units;
  @XmlElement( name = "unitPoints" )
  private List<UnitPoint> unitPoints;
  private String dbName;
  private DataSource ds;
  private String unitID;

  public UnitPointGenerator()
  {
    super();
  }

  public void populateUnits()
  {
    makeConnection();

    units = new JdbcTemplate( ds ).query( ALL_UNITS, new UnitMapper() );
    //return units;
  }

  public void populateUnitPoints()
  {
    makeConnection();
    unitPoints = new JdbcTemplate( ds ).query( UNIT_POINTS, new Object[] { getUnitID() }, new UnitPointMapper() );
    //return unitPoints;
  }

  public void populateAllUnitPoints()
  {
    makeConnection();
    unitPoints = new JdbcTemplate( ds ).query( ALL_POINTS, new UnitPointMapper() );
    //return unitPoints;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void setUnitID( String unitID )
  {
    this.unitID = unitID;
  }

  private String getUnitID()
  {
    return unitID;
  }

  private void makeConnection()
  {
    //ds = DataSourceFactory.createDataSource( getDbName() );
    ds = DataSourceFactory.getStatsConnection();
  }
}
