package edu.ucla.library.libservices.pubstats.web.service;

public class SubmitService
{
  public SubmitService()
  {
    super();
  }
}
/*
 * receive submission from drupal page
 * set time per = total time/basestat count
 * for each BaseStat entry do:
 *  determine if transaction
 *  assemble aggregate ID
 *  extract month/year fields
 *  submit via uspAddRefStat proc
 *  capture stat ID
 * done;
 * if transaction flag, submit transaction row
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