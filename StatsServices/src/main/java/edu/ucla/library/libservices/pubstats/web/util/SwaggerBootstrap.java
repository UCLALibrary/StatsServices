package edu.ucla.library.libservices.pubstats.web.util;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class SwaggerBootstrap
  extends HttpServlet
{
  public SwaggerBootstrap()
  {
    super();
  }

  @Override
  public void init( ServletConfig config )
    throws ServletException
  {
    super.init( config );

    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion( "1.0.0" );
    beanConfig.setSchemes( new String[] { "https" } );
    beanConfig.setHost( "webservices-test.library.ucla.edu" );
    beanConfig.setBasePath( "/pss" );
    beanConfig.setResourcePackage( "edu.ucla.library.libservices.pubstats.beans,edu.ucla.library.libservices.pubstats.generators" );
    beanConfig.setScan( true );
  }
}
/*
Method	                Property Name	Purpose
setTitle(String)	title	Sets the title of the application.
setDescription(String)	description	Sets the description of the application.
setTermsOfServiceUrl(String)	termsOfServiceUrl	Sets the URL of the application's Terms of Service.
setContact(String)	contact	Sets the contact information for the application.
setLicense(String)	license	Sets the license of the application.
setLicenseUrl(String)	licenseUrl	Sets the licesne url of the application.
setVersion(String)	version	Sets the version of the API.
setSchemes(String[])	schemes	Sets the schemes for the API URLs (http, https).
setHost(String)	host	Sets the host (including a port) for the API URLs. Does not include the schemes nor context root.
setBasePath(String)	basePath	Sets the context root for the API calls.
setFilterClass(Sting)	filterClass	Sets a security filter for Swagger's documentation.
setResourcePackage(String)	resourcePackage	Sets which package(s) Swagger should scan to pick up resources. If there's more than one package, it can be a list of comma-separated packages.
setScan(boolean)	scan	When set to true, Swagger will build the documentation.
setPrettyPrint(boolean)	prettyPrint	Sets whether the swagger.json will be pretty printed. */