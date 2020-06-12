package ru.stqa.jt.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String yourIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("185.13.112.139");
    assertEquals(yourIP, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }
}
