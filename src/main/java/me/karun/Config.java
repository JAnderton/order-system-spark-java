package me.karun;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.function.Function.identity;

class Config {
  private final ResourceBundles bundles;

  Config(final String... configFileNames) {
    bundles = new ResourceBundles(configFileNames);
  }

  int getInt(final String key) {
    return parseInt(get(key).get());
  }

  Optional<String> get(final String key) {
    return bundles.getString(key);
  }

  Map<String, String> valuesForKey(final String baseKey) {
    return bundles.getKeys().stream()
      .filter(s -> s.startsWith(baseKey))
      .collect(Collectors.toMap(identity(), s->get(s).get()));
  }
}

