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
    logger.info( "building stats line" );

    try
    {
      theLine.setAggregateID( general.getUnitID().concat( general.getPointID() )
                                     .concat( details.getTypeID() ).concat( details.getModeID() ) );
      theLine.setCount( details.getCount() );
      theLine.setDataMonth( DateExtractor.getCalendarPart( general.getDateTime(), Calendar.MONTH ) );
      theLine.setDataYear( DateExtractor.getCalendarPart( general.getDateTime(), Calendar.YEAR ) );
      theLine.setDateTime( general.getDateTime() );
      theLine.setLogonID( general.getOperator() );
      theLine.setTimeSpent( general.getTimeSpent() / general.getStats().size() );

      return theLine;
    }
    catch ( Exception e )
    {
      logger.fatal( "fatal error building line: " + e.getMessage() );
      throw e;
    }
  }
}
