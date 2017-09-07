package edu.ucla.library.libservices.pubstats.tests;

import edu.ucla.library.libservices.pubstats.beans.Referral;
import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefReferralProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefStatProc;

import java.time.LocalDateTime;

//import java.util.Date;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Tester
{
  public Tester()
  {
    super();
  }

  public static void main( String[] args )
  {
    AddRefStatProc statProc;
    AddRefReferralProc refProc;
    Map results;
    Referral referral;
    Set keys;
    StatsLine line;
    Submission submit;
    
    line = new StatsLine();
    line.setAggregateID( "SRL00020203" );
    line.setCount( 1 );
    line.setDataMonth( 7 );
    line.setDataYear( 2017 );
    line.setDateTime( new Date() );
    line.setLogonID( "drickard1967" );
    line.setTimeSpent( 20.0D );
    
    referral = new Referral();
    referral.setText( "this is a referral note" );
    submit = new Submission();
    submit.setDateTime( new Date() );
    submit.setOperator( "drickard1967" );
    submit.setReferral( referral );
    
    statProc = new AddRefStatProc();
    statProc.setData( line );
    statProc.setDbName( "dbName" );

    results = statProc.addStat();
    keys = results.keySet();
    for ( Object key : results.keySet() )
      System.out.println( "return = " + results.get( key ) );
    
    refProc = new AddRefReferralProc();
    refProc.setData( submit );
    refProc.setDbName( "dbName" );
    refProc.addStat();
  }
}
