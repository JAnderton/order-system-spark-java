package me.karun;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigTest {

  @Test
  public void getValues_whenCalledOnTheTestFile_thenReturnsCorrectValues() {
    final Map<String, String> values = new Config("test")
      .valuesForKey("build");

    assertThat(values)
      .containsOnly(
        new Entry<>("build.groupId", "Foo"),
        new Entry<>("build.artifactId", "Bar"),
        new Entry<>("build.version", "1")
      );
  }

  @RequiredArgsConstructor
  @ToString
  class Entry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private final V value;

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(final V value) {
      return null;
    }
  }
}