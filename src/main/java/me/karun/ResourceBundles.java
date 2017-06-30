package me.karun;

import java.util.*;

import static java.util.stream.Collectors.toList;

class ResourceBundles {
  private final List<ResourceBundle> resourceBundles;

  ResourceBundles(final String[] configFileNames) {
    resourceBundles = Arrays.stream(configFileNames)
      .map(ResourceBundle::getBundle)
      .collect(toList());
  }

  List<String> getKeys() {
    return resourceBundles.stream()
      .map(ResourceBundle::getKeys)
      .map(Collections::list)
      .flatMap(Collection::stream)
      .collect(toList());
  }

  Optional<String> getString(final String key) {
    return resourceBundles.stream()
      .filter(r -> r.containsKey(key))
      .map(r -> r.getString(key))
      .findFirst();
  }
}
