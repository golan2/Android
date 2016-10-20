package com.example.SimpleListFromLocalXML;

/**
* <pre>
* <B>Copyright:</B>   HP Software IL
* <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
* <B>Creation:</B>    23/03/2015 00:49
* <B>Since:</B>       BSM 9.21
* <B>Description:</B>
*
* </pre>
*/
class Profile {
  public static final int NO_CHANGE = -1;
  public enum GpsState {On, Off, NoChange}

  private final String name;
  private final GpsState gps;
  private final int ringerVolume;

  public Profile(String name, GpsState gps, int ringerVolume) {
    this.name = name;
    this.gps = gps;
    this.ringerVolume = ringerVolume;
  }

  public String getName() {
    return name;
  }

  public GpsState getGps() {
    return gps;
  }

  public int getRingerVolume() {
    return ringerVolume;
  }

  @Override
  public String toString() {
    return "Profile{" +
        "name='" + name + '\'' +
        ", gps=" + gps +
        ", ringerVolume=" + ringerVolume +
        '}';
  }
}
