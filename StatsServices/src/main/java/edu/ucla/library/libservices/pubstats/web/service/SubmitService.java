package edu.ucla.library.libservices.pubstats.web.service;

import edu.ucla.library.libservices.pubstats.beans.Submission;
import edu.ucla.library.libservices.pubstats.handlers.SubmissionHandler;

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

@Api(value = "/stats", description = "Submit data to db")
@Path( "/stats/" )
public class SubmitService
{
  @Context
  ServletConfig config;

  public SubmitService()
  {
    super();
  }

  @PUT
  @Consumes( "application/json" )
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
    handler.setSubmission( theStats );
    
    try
    {
      handler.submitStats();
      return Response.ok().entity( "Stats saved" ).build();
    }
    catch ( Exception e )
    {
      return Response.serverError().entity( "Stats insertion failed: " + e.getMessage() ).build();
    }
  }
  
}