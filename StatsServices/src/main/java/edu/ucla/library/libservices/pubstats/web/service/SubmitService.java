package edu.ucla.library.libservices.pubstats.web.service;

import edu.ucla.library.libservices.pubstats.beans.Submission;
import edu.ucla.library.libservices.pubstats.handlers.SubmissionHandler;

import edu.ucla.library.libservices.pubstats.util.StatsLineBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.ServletConfig;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Api(value = "/stats", description = "Submit data to db")
@Path( "/stats/" )
public class SubmitService
{
  @Context
  ServletConfig config;
  final static Logger logger = LogManager.getLogger( SubmitService.class );

  public SubmitService()
  {
    super();
  }

  @PUT
  @Consumes( "application/json,application/xml,text/xml" )
  @Path( "submit" )
  @ApiOperation(value = "Submits statistics to db",
    notes = "Consumes a Submission object")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Stats saved"),
      @ApiResponse(code = 500, message = "Stats insertion failed: ")})
  public Response submitStats(Submission theStats)
  {
    SubmissionHandler handler;
    handler = new SubmissionHandler();
    handler.setDbName( config.getServletContext().getInitParameter( "datasource.stats" ) );
    //logger.info( "receieved stats data \n" + theStats );
    handler.setSubmission( theStats );
    
    try
    {
      handler.submitStats();
      return Response.ok().entity( "Stats saved" ).build();
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      logger.fatal( "error with stats submission: " + e.getMessage() );
      return Response.serverError().entity( "Stats insertion failed: " + e.getMessage() ).build();
    }
  }
  
}
