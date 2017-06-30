package me.karun;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;
import static java.util.ResourceBundle.getBundle;

class Config {
  private final ResourceBundle config;

  Config(final String configFileName) {
    config = getBundle(configFileName);
  }

  int getInt(final String key) {
    return parseInt(get(key));
  }

  String get(final String key) {
    return config.getString(key);
  }

  Map<String, String> getValues(final String baseKey, final String... keys) {
    final Map<String, String> map = new HashMap<>();
    for (final String key : keys) {
      map.put(key, get(baseKey + "." + key));
    }

    return map;
  }
}