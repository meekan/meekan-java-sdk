package com.meekan.api.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountsTest.class, MeetingsTest.class, MeetingVotesTest.class, SlotsTest.class, FreeBusyTest.class })
public class AllTests {

}
