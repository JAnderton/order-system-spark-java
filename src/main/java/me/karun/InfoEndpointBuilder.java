package me.karun;

import com.google.gson.Gson;

import java.util.Map;

class InfoEndpointBuilder {
  private final String appInfo;

  InfoEndpointBuilder(final Config config, final Gson gson) {
    final Map<String, String> info = config.valuesForKey("build");
    info.putAll(config.valuesForKey("git"));

    this.appInfo = gson.toJson(info);
  }

  String fetchAppInfo() {
    return appInfo;
  }
}

