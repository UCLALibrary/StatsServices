package edu.ucla.library.libservices.pubstats.handlers;

public class SubmissionHandler
{
  public SubmissionHandler()
  {
    super();
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