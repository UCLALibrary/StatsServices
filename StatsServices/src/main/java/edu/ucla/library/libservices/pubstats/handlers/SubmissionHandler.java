package edu.ucla.library.libservices.pubstats.handlers;

import edu.ucla.library.libservices.pubstats.beans.BaseStat;
import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefInteractionsProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefReferralProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefStatInteractionProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefStatProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefStatReferralProc;
import edu.ucla.library.libservices.pubstats.util.StatsLineBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class SubmissionHandler
{
  private static final List<String> TRANSACTION_TYPES =
    new ArrayList<String>( Arrays.asList( new String[] { "02", "03", "04", "05", "06", "08", "00" } ) );
  final static Logger logger = Logger.getLogger( SubmissionHandler.class );

  private List<String> statsIDs;
  private String dbName;
  private Submission submission;

  public SubmissionHandler()
  {
    super();
  }

  public void setSubmission( Submission submission )
  {
    this.submission = submission;
  }

  private Submission getSubmission()
  {
    return submission;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void submitStats()
  {
    boolean isTransaction;

    isTransaction = false;
    statsIDs = new ArrayList<String>();

    logger.info( "in submitStats" );
    //System.out.println( "in submitStats" );

    try
    {
      for ( BaseStat theStat : getSubmission().getStats() )
      {
        if ( theStat.getCount() > 0 )
        {
          for ( int i = 1; i <= theStat.getCount(); i++ )
          {
            StatsLine inputLine;

            //logger.info( "building stats line with mode " + theStat.getModeID() );

            //logger.info( "checking if transaction for type " + theStat.getTypeID() );
            if ( TRANSACTION_TYPES.contains( theStat.getTypeID() ) )
            {
              //logger.info( "this is a transaction" );
              //System.out.println( "this is a transaction" );
              isTransaction = true;
            }

            inputLine = StatsLineBuilder.buildLine( getSubmission(), theStat );

            submitLine( inputLine, true );
          }
        }
      }
    }
    catch ( Exception e )
    {
      logger.fatal( "in submitStats, fatal error: " + e.getMessage() );
      System.err.println( "in submitStats, fatal error: " + e.getMessage() );
      e.printStackTrace();
      throw e;
    }

    if ( isTransaction )
      submitTransaction( getSubmission().getStats().get( 0 ) );

    /*if ( getSubmission().getReferral() != null && getSubmission().getReferral().getText() != null &&
         getSubmission().getReferral().getText().trim().length() > 0 )*/
    if ( getSubmission().getReferral() )
      submitReferral();

    if ( getSubmission().isDetailed() )
      submitInteraction();
  }

  private void submitLine( StatsLine input, boolean saveID )
  {
    AddRefStatProc proc;
    Map results;

    proc = new AddRefStatProc();
    proc.setData( input );
    proc.setDbName( getDbName() );

    logger.info( "in submitLine; AggregateID = " + input.getAggregateID() );
    //System.out.println( "in submitLine; AggregateID = " + input.getAggregateID() );

    try
    {
      results = proc.addStat();

      if ( saveID )
        for ( Object key : results.keySet() )
          if ( results.get( key ).toString().contains( "StatisticID" ) )
          {
            logger.info( "saved stat " + results.get( key ).toString() );
            //System.out.println( "saved stat " + results.get( key ).toString() );
            statsIDs.add( results.get( key ).toString().substring( results.get( key ).toString().indexOf( "=" ) + 1,
                                                                   results.get( key ).toString().indexOf( "}" ) ) );
          }
    }
    catch ( Exception e )
    {
      logger.fatal( "error submitting stats line: " + e.getMessage() );
      System.err.println( "error submitting stats line: " + e.getMessage() );
      e.printStackTrace();
      throw e;
    }

  }

  private void submitTransaction( BaseStat theStat )
  {
    StatsLine transaction;

    transaction = StatsLineBuilder.buildLine( getSubmission(), theStat );
    transaction.setAggregateID( getSubmission().getUnitPointID().concat( "0000" ) );
    transaction.setTimeSpent( getSubmission().getTimeSpent() );
    transaction.setCount( 1 );
    submitLine( transaction, false );
  }

  private void submitReferral()
  {
    logger.info( "handling referral record" );
    //System.out.println( "handling referral record" );
    AddRefReferralProc proc;
    List<String> refIDs;
    Map results;

    proc = new AddRefReferralProc();
    proc.setData( getSubmission() );
    proc.setDbName( getDbName() );

    try
    {
      results = proc.addStat();

      refIDs = new ArrayList<String>();
      for ( Object key : results.keySet() )
        if ( results.get( key ).toString().contains( "ReferralID" ) )
          refIDs.add( results.get( key ).toString().substring( results.get( key ).toString().indexOf( "=" ) + 1,
                                                               results.get( key ).toString().indexOf( "}" ) ) );

      linkRefStat( refIDs );
    }
    catch ( Exception e )
    {
      logger.fatal( "error submitting referral line: " + e.getMessage() );
      System.err.println( "error submitting referral line: " + e.getMessage() );
      e.printStackTrace();
      throw e;
    }
  }

  private void linkRefStat( List<String> referrals )
  {
    for ( String refID : referrals )
    {
      for ( String statID : statsIDs )
      {
        AddRefStatReferralProc proc;

        logger.info( "handling stat-referral link for stat " + statID + " and referral " + refID );
        //System.out.println( "handling stat-referral link for stat " + statID + " and referral " + refID );

        proc = new AddRefStatReferralProc();
        proc.setDbName( getDbName() );
        proc.setReferralID( Integer.parseInt( refID ) );
        proc.setStatID( Integer.parseInt( statID ) );

        try
        {
          proc.addStat();
        }
        catch ( Exception e )
        {
          logger.fatal( "error submitting referral link: " + e.getMessage() );
          System.err.println( "error submitting referral link: " + e.getMessage() );
          e.printStackTrace();
          throw e;
        }
      }
    }
  }

  private void submitInteraction()
  {
    logger.info( "handling interaction insertion" );
    //System.out.println( "handling interaction insertion" );
    AddRefInteractionsProc proc;
    List<String> interactIDs;
    Map results;

    proc = new AddRefInteractionsProc();
    proc.setData( getSubmission() );
    proc.setDbName( getDbName() );

    try
    {
      results = proc.addStat();

      interactIDs = new ArrayList<String>();
      for ( Object key : results.keySet() )
        if ( results.get( key ).toString().contains( "InteractionID" ) )
          interactIDs.add( results.get( key ).toString().substring( results.get( key ).toString().indexOf( "=" ) + 1,
                                                                    results.get( key ).toString().indexOf( "}" ) ) );

      linkIntStat( interactIDs );
    }
    catch ( Exception e )
    {
      logger.fatal( "error submitting interaction: " + e.getMessage() );
      System.err.println( "error submitting interaction: " + e.getMessage() );
      e.printStackTrace();
      throw e;
    }
  }

  private void linkIntStat( List<String> interactions )
  {
    for ( String intID : interactions )
    {
      for ( String statID : statsIDs )
      {
        AddRefStatInteractionProc proc;

        logger.info( "handling stat-referral link for stat " + statID + " and interaction " + intID );
        //System.out.println( "handling stat-referral link for stat " + statID + " and interaction " + intID );

        proc = new AddRefStatInteractionProc();
        proc.setDbName( getDbName() );
        proc.setInteractionID( Integer.parseInt( intID ) );
        proc.setStatID( Integer.parseInt( statID ) );

        try
        {
          proc.addStat();
        }
        catch ( Exception e )
        {
          logger.fatal( "error submitting interaction link: " + e.getMessage() );
          System.err.println( "error submitting interaction link: " + e.getMessage() );
          e.printStackTrace();
          throw e;
        }
      }
    }
  }
}
/*
 * receive submission from drupal page
 * for each BaseStat entry do:
 *  determine if transaction
 *  build stats line object
 *  submit via uspAddRefStat proc
 *  capture stat ID
 * done;
 * if transaction flag, submit transaction row
 *  build stats line object
 *  override aggregate ID as UnitIDPointID0000
 *  override time spent as full time from submission
 *  override count as 1
 *  submit via uspAddRefStat proc
 * if referral do
 *  add referral record via uspAddRefReferral proc
 *  for each stat ID do
 *    add link row via uspAddRefStatReferral proc
 *  done
 * done
 * if interaction value(s) do
 *  add record via uspAddRefInteractions proc
 *  for each stat ID do
 *    add link row via uspAddRefStatInteraction proc
 *  done
 * done
 */