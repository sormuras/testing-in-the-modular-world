package foo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

class PackageFooTests {

  @Test
  void accessPackageFooInModuleFoo() {
    var mainModule = PackageFoo.class.getModule();
    assertSame(mainModule, getClass().getModule());
    assumeTrue(mainModule.isNamed(), "module is not named");
    assertEquals("foo", mainModule.getName());
  }

  @Test
  void accessPackageFooInUnnamedModule() {
    var mainModule = PackageFoo.class.getModule();
    assertSame(mainModule, getClass().getModule());
    assumeFalse(mainModule.isNamed(), "module is named");
    assertNull(mainModule.getName());
  }
}
