package me.karun;

import com.google.gson.Gson;

import java.util.Map;
import java.util.TreeMap;

class InfoEndpointBuilder {
  private final String appInfo;

  InfoEndpointBuilder(final Config config, final Gson gson) {
    final Map<String, String> info = new TreeMap<>();
    info.putAll(config.valuesForKey("build"));
    info.putAll(config.valuesForKey("git"));

    this.appInfo = gson.toJson(info);
  }

  String fetchAppInfo() {
    return appInfo;
  }
}

