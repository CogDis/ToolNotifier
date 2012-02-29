package com.precipicegames.utilities;

public class LoadSettings
{
  static String Notify;
  static String Color;
  
  //TODO: use Bukkit's built in YamlConfiguration in place of this
  public static void loadMain()
  {
    String propertiesFile = ToolNotifier.mainDirectory + "config.properties";
    PluginProperties properties = new PluginProperties(propertiesFile);
    properties.load();

    Notify = properties.getString("Notify", "3,Tool is dead in 3 uses:6,6 uses left:9,9 more hits till your tool dies");
    Color = properties.getString("Color", "GOLD");
    properties.save("===ToolNotifier===");
  }
}
