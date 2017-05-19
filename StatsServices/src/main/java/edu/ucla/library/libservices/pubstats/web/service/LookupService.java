package edu.ucla.library.libservices.pubstats.web.service;

import edu.ucla.library.libservices.pubstats.generators.UnitPointGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.servlet.ServletConfig;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

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
  public Response getUnits( @PathParam( "unitID" ) String unitID )
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
}
