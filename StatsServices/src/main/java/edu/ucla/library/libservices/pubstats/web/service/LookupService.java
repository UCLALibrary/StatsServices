package edu.ucla.library.libservices.pubstats.web.service;

import edu.ucla.library.libservices.pubstats.generators.DepartmentGenerator;
import edu.ucla.library.libservices.pubstats.generators.UnitPointGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import edu.ucla.library.libservices.pubstats.beans.Record;
import edu.ucla.library.libservices.pubstats.generators.RecordGenerator;
import edu.ucla.library.libservices.pubstats.generators.UserAccountGenerator;

@Api(value = "/lookup", description = "Lookup operations on db")
@Path( "/lookup" )
public class LookupService
{
  private static final String ALL_UNITS = "full_units";
  private static final String ALL_POINTS = "full_points";

  @Context
  ServletConfig config;

  public LookupService()
  {
    super();
  }

  @GET
  @Produces( "application/json" )
  @Path( "/units/{unitID}" )
  @ApiOperation(value = "Finds Library units/service points",
    notes = "Valid unitID values are full_units, full_points, unit ID from db",
    response = UnitPointGenerator.class,
    responseContainer = "List")
  public Response getUnits( @ApiParam(value = "unit(s) to be retrieved", required = true)  @PathParam( "unitID" ) String unitID )
  {
    UnitPointGenerator docMaker;

    docMaker = new UnitPointGenerator();
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.stats" ) );

    switch ( unitID )
    {
      case ALL_UNITS:
        docMaker.populateUnits();
        break;
      case ALL_POINTS: 
        docMaker.populateAllUnitPoints();
        break;
      default:
        docMaker.setUnitID( unitID );
        docMaker.populateUnitPoints();
        break;
    }

    return Response.ok( docMaker ).build();
  }

  @GET
  @Produces( "application/json" )
  @Path( "/departments" )
  @ApiOperation(value = "Finds UCLA departments/units",
    response = DepartmentGenerator.class,
    responseContainer = "List")
  public Response getDepartments()
  {
    DepartmentGenerator docMaker;

    docMaker = new DepartmentGenerator();
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.stats" ) );
    
    docMaker.populateDepartments();

    return Response.ok( docMaker ).build();
  }

  @GET
  @Produces( "application/json" )
  @Path( "/latest" )
  @ApiOperation(value = "Finds latest stats record",
    response = Record.class,
    responseContainer = "Object")
  public Response getRecord()
  {
    RecordGenerator docMaker;

    docMaker = new RecordGenerator();
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.stats" ) );
    
    return Response.ok( docMaker.getTheRecord() ).build();
  }

  @GET
  @Produces( "application/json" )
  @Path( "/users" )
  @ApiOperation(value = "Finds generic department user accounts",
    response = UserAccountGenerator.class,
    responseContainer = "List")
  public Response getUsers()
  {
    UserAccountGenerator docMaker;

    docMaker = new UserAccountGenerator();
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.stats" ) );
    
    docMaker.populateUsers();

    return Response.ok( docMaker ).build();
  }
}
