package me.karun;

import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigTest {

  @Test
  public void getValues_whenCalledOnTheTestFile_thenReturnsCorrectValues() {
    final Map<String, String> values = new Config("test")
      .getValues("build", "groupId", "artifactId", "version");

    assertThat(values)
      .containsOnly(new Entry<>("groupId", "Foo"),
        new Entry<>("artifactId", "Bar"),
        new Entry<>("version", "1")
      );
  }

  @RequiredArgsConstructor
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