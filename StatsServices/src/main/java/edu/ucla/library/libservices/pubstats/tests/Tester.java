package edu.ucla.library.libservices.pubstats.tests;

import edu.ucla.library.libservices.pubstats.beans.BaseStat;
import edu.ucla.library.libservices.pubstats.beans.Department;
import edu.ucla.library.libservices.pubstats.beans.Interaction;
import edu.ucla.library.libservices.pubstats.beans.Referral;
import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefInteractionsProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefReferralProc;
import edu.ucla.library.libservices.pubstats.db.procs.AddRefStatProc;

import edu.ucla.library.libservices.pubstats.generators.DepartmentGenerator;

import java.time.LocalDateTime;

//import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.ucla.library.libservices.pubstats.beans.Interaction;
import edu.ucla.library.libservices.pubstats.beans.Record;
import edu.ucla.library.libservices.pubstats.generators.RecordGenerator;

import edu.ucla.library.libservices.pubstats.handlers.SubmissionHandler;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

public class Tester
{
  public Tester()
  {
    super();
  }

  public static void main( String[] args )
  {
    RecordGenerator generator;
    Record theRecord;
    generator = new RecordGenerator();
    generator.setDbName( "dbName" );
    theRecord = generator.getTheRecord();
    System.out.println( "record date = " + theRecord.getCreatedDT() );
    /*Interaction interaction;
    List<BaseStat> stats;
    Referral referral;
    Submission submit;
    SubmissionHandler handler;

    submit = new Submission();
    submit.setDateTime( new Date() );
    submit.setDetailed( true );
    submit.setOperator( "drickard1967" );
    submit.setPatronCount( 1 );
    submit.setTimeSpent( 15D );
    submit.setUnitPointID( "ART0001" );

    stats = new ArrayList<BaseStat>( 3 );
    stats.add( addStat( "01", "01", 1 ) );
    stats.add( addStat( "01", "02", 2 ) );
    stats.add( addStat( "01", "03", 3 ) );

    submit.setStats( stats );

    referral = new Referral();
    referral.setText( "testing submission" );

    submit.setReferral( referral );

    interaction = new Interaction();
    interaction.setCourse( "Math 305" );
    interaction.setDepartmentID( 35 );
    interaction.setPatronFeedback( "party on wayne" );
    interaction.setPatronType( 1 );
    interaction.setStaffFeedback( "de nada" );
    interaction.setTopic( "linear differential equations" );

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
