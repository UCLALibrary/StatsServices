package edu.ucla.library.libservices.pubstats.web.service;

import edu.ucla.library.libservices.pubstats.beans.Submission;

import edu.ucla.library.libservices.pubstats.handlers.SubmissionHandler;

import javax.servlet.ServletConfig;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

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
  public Response submitStats(Submission theStats)
  {
    SubmissionHandler handler;
    handler = new SubmissionHandler();
    handler.setDbName( config.getServletContext().getInitParameter( "datasource.stats" ) );
    handler.setSubmission( theStats );
    
    try
    {
      handler.submitStats();
      return Response.ok().build();
    }
    catch ( Exception e )
    {
      return Response.serverError().entity( "Stats insertion failed: " + e.getMessage() ).build();
    }
  }
  
}
