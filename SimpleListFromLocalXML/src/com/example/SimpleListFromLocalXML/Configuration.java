package com.example.SimpleListFromLocalXML;

import android.os.Environment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    22/03/2015 08:34
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Configuration {

  private List<Profile> profiles = Collections.emptyList();

  public Configuration() throws IOException, JSONException {
    readConfiguration();
  }

  /*package*/ File getConfigurationFile() throws FileNotFoundException {
    if (!isExternalStorageReadable()) {
      throw new FileNotFoundException("Failed to read configuration due to [Environment.getExternalStorageState] returning false");
    }

    File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    File[] files = downloadDir.listFiles(new ConfigurationFileFilterByName());

    if (files.length==0) {
      throw new FileNotFoundException("Configuration file ["+ ConfigurationFileFilterByName.CONFIG_FILE+"] was not found under the Downloads directory");
    }

    return files[0];
  }

  /*package*/ String readConfigurationAsString(File configFile) throws IOException {
    FileInputStream fis = new FileInputStream(configFile);
    BufferedReader streamReader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
    StringBuilder responseStrBuilder = new StringBuilder();
    String inputStr;
    while ((inputStr = streamReader.readLine()) != null) {
      responseStrBuilder.append(inputStr);
    }
    return responseStrBuilder.toString();
  }

  /*package*/ void readConfiguration() throws IOException, JSONException {
    File configFile = getConfigurationFile();
    String configString = readConfigurationAsString(configFile);
    this.profiles = convertJsonToProfilesList(configString);
  }

  private List<Profile> convertJsonToProfilesList(String configString) throws JSONException {
    List<Profile> result = new ArrayList<>();
    JSONObject jConfiguration = new JSONObject(configString);
    JSONArray jProfiles = jConfiguration.getJSONArray("profile");
    for (int i = 0; i < jProfiles.length(); i++) {
      JSONObject jProfile = jProfiles.getJSONObject(i);
      String name = jProfile.getString("name");
      Profile.GpsState gps = Profile.GpsState.valueOf(jProfile.getString("gps"));
      int ringer = Integer.parseInt(jProfile.getString("ringer"));
      result.add(new Profile(name, gps, ringer));
    }
    return result;
  }

  private boolean isExternalStorageReadable() {
    String state = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
  }

  private static class ConfigurationFileFilterByName implements FilenameFilter {
    public static final String CONFIG_FILE = "profiles_configuration.json";

    @Override
    public boolean accept(File dir, String filename) {
      return CONFIG_FILE.equals(filename);
    }
  }

  public List<Profile> getProfiles() {
    return profiles;
  }

}
