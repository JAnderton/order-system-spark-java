package me.karun;

import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;
import static java.util.ResourceBundle.getBundle;

class Config {
  private final ResourceBundle config;

  Config(final String configFileName) {
    config = getBundle(configFileName);
  }

  int getInt(final String key) {
    return parseInt(config.getString(key));
  }
}