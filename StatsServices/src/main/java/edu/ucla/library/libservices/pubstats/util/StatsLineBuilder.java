package edu.ucla.library.libservices.pubstats.util;

import edu.ucla.library.libservices.pubstats.beans.BaseStat;
import edu.ucla.library.libservices.pubstats.beans.StatsLine;
import edu.ucla.library.libservices.pubstats.beans.Submission;

import java.util.Calendar;

public class StatsLineBuilder
{

  public StatsLineBuilder()
  {
    super();
  }

  public static StatsLine buildLine( Submission general, BaseStat details )
  {
    StatsLine theLine;
    theLine = new StatsLine();

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
}
