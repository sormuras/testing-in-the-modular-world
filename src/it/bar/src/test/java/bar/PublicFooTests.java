package bar;

import foo.PublicFoo;

import org.junit.jupiter.api.Test;

class PublicFooTests {

  @Test
  void accessPublicFoo() {
    assert "foo".equals(PublicFoo.class.getModule().getName());
  }
}
