package edu.ucla.library.libservices.pubstats.tests;

import edu.ucla.library.libservices.pubstats.beans.BaseStat;
//import edu.ucla.library.libservices.pubstats.beans.Department;
import edu.ucla.library.libservices.pubstats.beans.Interaction;
//import edu.ucla.library.libservices.pubstats.beans.Referral;
//import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;
//import edu.ucla.library.libservices.pubstats.db.procs.AddRefInteractionsProc;
//import edu.ucla.library.libservices.pubstats.db.procs.AddRefReferralProc;
//import edu.ucla.library.libservices.pubstats.db.procs.AddRefStatProc;

//import edu.ucla.library.libservices.pubstats.generators.DepartmentGenerator;

//import java.time.LocalDateTime;

//import java.util.Date;
import edu.ucla.library.libservices.pubstats.beans.UserAccount;
import edu.ucla.library.libservices.pubstats.generators.UserAccountGenerator;

import java.util.Date;
import java.util.List;
//import java.util.Map;
//import java.util.Set;

//import edu.ucla.library.libservices.pubstats.beans.Interaction;
//import edu.ucla.library.libservices.pubstats.beans.Record;
//import edu.ucla.library.libservices.pubstats.generators.RecordGenerator;

import edu.ucla.library.libservices.pubstats.handlers.SubmissionHandler;

import java.util.ArrayList;

//import javax.ws.rs.core.Response;

public class Tester
{
  public Tester()
  {
    super();
  }

  public static void main( String[] args )
  {
    List<UserAccount> users;
    UserAccountGenerator gen;
    gen = new UserAccountGenerator();
    gen.setDbName( "dbName" );
    gen.populateUsers( "all" );
    users = gen.getUsers();
    for ( UserAccount theUser : users )
      System.out.println( theUser.getName() + "\t" + theUser.getUnit() + "\t" + theUser.getLogonID() );
    /*double value = 1.123D;
    System.out.println( "rounded value = " + Math.round( value ) );
    RecordGenerator generator;
    Record theRecord;
    generator = new RecordGenerator();
    generator.setDbName( "dbName" );
    theRecord = generator.getTheRecord();
    System.out.println( "record date = " + theRecord.getCreatedDT() );
    Interaction interaction;
    List<BaseStat> stats;
    //Referral referral;
    Submission submit;
    SubmissionHandler handler;

    submit = new Submission();
    submit.setDateTime( new Date() );
    submit.setDetailed( true );
    submit.setOperator( "drickard" );
    submit.setPatronCount( 1 );
    submit.setTimeSpent( 17D );
    submit.setUnitPointID( "CLK0001" );

    stats = new ArrayList<BaseStat>( 6 );
    stats.add( addStat( "01", "01", 0 ) );
    stats.add( addStat( "02", "01", 0 ) );
    stats.add( addStat( "05", "01", 1 ) );
    stats.add( addStat( "10", "01", 1 ) );
    stats.add( addStat( "12", "01", 0 ) );
    stats.add( addStat( "13", "01", 0 ) );

    submit.setStats( stats );

    //referral = new Referral();
    //referral.setText( "testing submission" );

    //submit.setReferral( referral );

    submit.setReferral( true );

    interaction = new Interaction();
    interaction.setCourse( "Math 503" );
    interaction.setDepartmentID( 22 );
    interaction.setPatronFeedback( "tubular" );
    interaction.setPatronType( 1 );
    interaction.setStaffFeedback( "whatevs" );
    interaction.setTopic( "P vs NP" );

    submit.setInteraction( interaction );

    handler = new SubmissionHandler();
    handler.setDbName( "dbname" );
    handler.setSubmission( submit );

    try
    {
      handler.submitStats();
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }*/
  }

  private static BaseStat addStat( String type, String mode, int count )
  {
    BaseStat stat;

    stat = new BaseStat();
    stat.setCount( count );
    stat.setModeID( mode );
    stat.setTypeID( type );

    return stat;
  }
}
