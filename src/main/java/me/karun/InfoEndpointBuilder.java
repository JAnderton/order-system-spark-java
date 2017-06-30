package me.karun;

import com.google.gson.Gson;

class InfoEndpointBuilder {
  private final String appInfo;

  InfoEndpointBuilder(final Config config, final Gson gson) {
    this.appInfo = gson.toJson(config.getValues("build",
      "groupId", "artifactId", "version"));
  }

  String fetchAppInfo() {
    return appInfo;
  }
}

