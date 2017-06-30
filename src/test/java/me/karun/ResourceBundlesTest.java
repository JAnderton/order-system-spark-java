package me.karun;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceBundlesTest {

  @Test
  public void getKeys_whenCalled_thenReturnsAllKeysFromFile() {
    assertThat(new ResourceBundles(new String[]{"test"}).getKeys())
      .containsExactlyInAnyOrder("build.groupId", "build.artifactId", "build.version");
  }

  @Test
  public void getString_whenCalledWithAValidKey_thenTheValueIsReturned() {
    assertThat(new ResourceBundles(new String[]{"test"}).getString("build.groupId"))
      .isPresent()
      .contains("Foo");
  }

  @Test
  public void getString_whenCalledWithAnInvalidKey_thenTheResponseIsEmpty() {
    assertThat(new ResourceBundles(new String[]{"test"}).getString("invalid.key"))
      .isEmpty();
  }
}