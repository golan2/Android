package com.example.SimpleListFromLocalXML;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ConfigurationTest {

  @Test
  public void testGetConfigurationFile() throws Exception {

  }

  @Test
  public void testReadConfigurationAsString() throws Exception {
    Configuration configuration = new Configuration();
    File testConfigFile = new Configuration4Test().getConfigurationFile();
    String s = configuration.readConfigurationAsString(testConfigFile);
    System.out.println(s);
  }

  @Test
  public void testReadConfiguration() throws Exception {
    new Configuration4Test().readConfiguration();
  }

  private static class Configuration4Test extends Configuration {
    public Configuration4Test() throws IOException, JSONException {}

    @Override
    File getConfigurationFile() throws FileNotFoundException {
      return new File("C:\\Users\\golaniz\\Documents\\Izik\\Java\\Projects\\Android\\SimpleListFromLocalXML\\profiles\\profiles_configuration.json");
    }
  }
}