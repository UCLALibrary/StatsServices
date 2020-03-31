package edu.ucla.library.libservices.pubstats.util;

import edu.ucla.library.libservices.pubstats.beans.BaseStat;
import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatsLineBuilder
{
  final static Logger logger = LogManager.getLogger( StatsLineBuilder.class );
  final static double MIN_TIME = 1.0D;
  final static int MIN_PATRON = 1;

  public StatsLineBuilder()
  {
    super();
  }

  public static StatsLine buildLine( Submission general, BaseStat details )
  {
    StatsLine theLine;
    theLine = new StatsLine();
    //logger.info( "building stats line" );

    try
    {
      //logger.info( "building stats line with " + general.getUnitPointID() + ", " + details.getTypeID() + " , " + details.getModeID() );
      theLine.setAggregateID( general.getUnitPointID().concat( details.getTypeID() ).concat( details.getModeID() ) );
      //logger.info( "building stats line with count " + details.getCount() );
      if ( general.isDetailed() )
        theLine.setCount( details.getCount() );
      else
        theLine.setCount( 1 );
      //logger.info( "building stats line with data month" );
      theLine.setDataMonth( DateExtractor.getCalendarPart( general.getDateTime(), Calendar.MONTH ) );
      //logger.info( "building stats line with data year" );
      theLine.setDataYear( DateExtractor.getCalendarPart( general.getDateTime(), Calendar.YEAR ) );
      //logger.info( "building stats line with date " + general.getDateTime() );
      theLine.setDateTime( general.getDateTime() );
      //logger.info( "building stats line with operator " + general.getOperator() );
      theLine.setLogonID( general.getOperator() );
      //logger.info( "building stats line with time spent " + general.getTimeSpent() + " and size " + general.getStats().size() );
      if ( general.isDetailed() )
      {
        double perLine;
        perLine = general.getTimeSpent() / general.getActualRows();
        if ( perLine < MIN_TIME )
          perLine = MIN_TIME;
        else
          perLine = Math.round( perLine );
        theLine.setTimeSpent( perLine );        
      }
      else
        theLine.setTimeSpent( MIN_TIME );
      //logger.info( "building stats line with patrons " + general.getPatronCount() );
      if ( general.getPatronCount() > 0 )
        theLine.setPatronCount( general.getPatronCount() );
      else
        theLine.setPatronCount( MIN_PATRON );

      return theLine;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      logger.fatal( "fatal error building line: " + e.getMessage() );
      throw e;
    }
  }
}
