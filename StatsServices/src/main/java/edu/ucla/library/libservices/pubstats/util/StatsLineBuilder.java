package edu.ucla.library.libservices.pubstats.util;

import edu.ucla.library.libservices.pubstats.beans.BaseStat;
import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;

import java.util.Calendar;

import org.apache.log4j.Logger;

public class StatsLineBuilder
{
  final static Logger logger = Logger.getLogger( StatsLineBuilder.class );

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
      theLine.setCount( details.getCount() );
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
        theLine.setTimeSpent( general.getTimeSpent() / general.getStats().size() );
      else
        theLine.setTimeSpent( 1.0D );
      //logger.info( "building stats line with patrons " + general.getPatronCount() );
      theLine.setPatronCount( general.getPatronCount() );

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
