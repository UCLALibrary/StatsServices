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

public class SubmissionHandler
{
  private static final List<String> TRANSACTION_TYPES =
    new ArrayList<String>( Arrays.asList( new String[] { "02", "03", "04", "05", "06", "08", "00" } ) );

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

    for ( BaseStat theStat : getSubmission().getStats() )
    {
      StatsLine inputLine;

      if ( TRANSACTION_TYPES.contains( theStat.getTypeID() ) )
        isTransaction = true;

      inputLine = StatsLineBuilder.buildLine( getSubmission(), theStat );

      submitLine( inputLine, true );
    }
    if ( isTransaction )
      submitTransaction( getSubmission().getStats().get( 0 ) );
    
    if ( getSubmission().getReferral().getText() != null && getSubmission().getReferral().getText().trim().length() > 0 )
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

    results = proc.addStat();

    if ( saveID )
      for ( Object key : results.keySet() )
        statsIDs.add( results.get( key ).toString() );
  }

  private void submitTransaction( BaseStat theStat )
  {
    StatsLine transaction;

    transaction = StatsLineBuilder.buildLine( getSubmission(), theStat );
    transaction.setAggregateID( getSubmission().getUnitID().concat( getSubmission().getPointID() ).concat( "0000" ) );
    transaction.setTimeSpent( getSubmission().getTimeSpent() );
    transaction.setCount( 1 );
    submitLine( transaction, false );
  }
  
  private void submitReferral()
  {
    AddRefReferralProc proc;
    List<String> refIDs;
    Map results;

    proc = new AddRefReferralProc();
    proc.setData( getSubmission() );
    proc.setDbName( getDbName() );

    results = proc.addStat();

    refIDs = new ArrayList<String>();
    for ( Object key : results.keySet() )
      refIDs.add( results.get( key ).toString() );
    
    linkRefStat(refIDs);
  }
  
  private void linkRefStat(List<String> referrals)
  {
    AddRefStatReferralProc proc;

    proc = new AddRefStatReferralProc();
    proc.setDbName( getDbName() );

    for ( String refID : referrals )
    {
      for ( String statID : statsIDs )
      {
        proc.setReferralID( Integer.parseInt( refID ) );
        proc.setStatID( Integer.parseInt( statID ) );
        
        proc.addStat();
      }
    }
  }
  
  private void submitInteraction()
  {
    AddRefInteractionsProc proc;
    List<String> interactIDs;
    Map results;

    proc = new AddRefInteractionsProc();
    proc.setData( getSubmission() );
    proc.setDbName( getDbName() );

    results = proc.addStat();

    interactIDs = new ArrayList<String>();
    for ( Object key : results.keySet() )
      interactIDs.add( results.get( key ).toString() );
    
    linkIntStat(interactIDs);
  }
  
  private void linkIntStat(List<String> interactions)
  {
    AddRefStatInteractionProc proc;

    proc = new AddRefStatInteractionProc();
    proc.setDbName( getDbName() );

    for ( String intID : interactions )
    {
      for ( String statID : statsIDs )
      {
        proc.setInteractionID( Integer.parseInt( intID ) );
        proc.setStatID( Integer.parseInt( statID ) );
        
        proc.addStat();
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