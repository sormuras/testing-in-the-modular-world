# JUnit 5 - Testing In The Modular World

---?include=deck/project-modules/PITCHME.md

---

## Code first, Test first

---

- Create test module "pura.vida" in IDEA
- Talk about module names
  - Valid modules names are ... JLS

---

- My naming preference for shared modules is as follows: 
  - Group: `a.b`
  - Artifact: `a.b.c` (= module name)
  - Contains only packages starting with `a.b.c`
  - Version: `ModuleDescriptor.Version.parse()`-able

```java
module pura.vida {
  requires java.base; // module(s) under test
}
```

---

- Create main class `pura.vida.Main` with _psvm_
- Execute `pura.vida.Main` and show first failing and then working test case

---

### Let JUnit Platform Launcher take over
- Extend test module `pura.vida` with external test frameworks and open it for deep reflection

---

```java
open module pura.vida {
  // (...) module(s) under test

  requires org.junit.jupiter.api;
  requires org.assertj.core;
}
```

---

- Resolve dependencies via `bach` or use prepared `lib/` directory
- Execute `pura.vida.Main` and see Jupiter Engine kick in
- Execute test run within IDEA, which uses JUnit Platform Launcher internally

---

### Introduce own module in same project

- Create empty module `de.sormuras.tool`
- Extend test module `pura.vida` with `requires de.sormuras.tool`
- Create public type `de.sormuras.tool.PublicType` and export its package
- Extend test module with `pura.vida.PublicTypeTests` class
- Create package-private type `de.sormuras.tool.PackagePrivateType` in exported package
- Create public type `de.sormuras.tool.internal.PublicHelpers` -- don't export it
- Try to extend test module `pure.vida` with test cases for non-exported types...

---

## Create `test/java` directory in module `de.sormuras.tool`

- Write (copy?) test cases for non-exported types
- Fix `class-path` in IDEA setup.
- Execute tests...
- Show that non-exported types from Jupiter are now visible and accessible
- Show that tests are executed on the class-path, modular assertions fail
- ...enter --patch-module and Bach.java

---

## Patching at compile-time

---

- Use same approach as with `pura.vida`
- Write a test module descriptor with all bells and whistles
- Patch main types into test types for compilation
- Pretend that your main types are siblings of test types... or as noted in JUnit 4 FAQ
- Proceed with command line support, names Bach.java

---

## Unique Modules published at Maven Central

- Talk about modules already published at Maven Central
- Current numbers
- Progression...
- Invalid names, Impostors, and other errors

---

## Backup

- Include `mainrunner` at test runtime
  - See engine getting picked up by JUnit Platform
  - First test program is executed as a test case
- Show modularization of JUnit 5's User Guide example sources: https://github.com/junit-team/junit5/blob/master/platform-tooling-support-tests/src/test/java/platform/tooling/support/tests/ModularUserGuideTests.java
- Show how OpenJDK project Skara performs tests: https://github.com/openjdk/skara#testing

---

## Outdated

- Show of hands "test": JUnit 3/4 - JUnit 5
- Show of hands "module": method - type - package - module - repository - "cloud"
- What are tests? Where to store tests?
  - Automated checks, mini-programs or "many main methods"
  - https://junit.org/junit4/faq.html#organize_1
  > _You can place your tests in the same package and directory as the classes under test._

- Show of hands "package": Ever used a different test package name for public/protected main types?
- Module under test requires a module
- What's a module?
  - Definition
  - Best practices regarding naming, requires modifiers, etc...
- Demo: Let's create a modular library project
  - `src/de.sormuras.bach.demo/main/java/`
  - Compile "main" = `javac` + `jar`
- Demo: Create an integration test module
  - `src/integration/test/java/`
  - Compile "test" = `javac` + `jar`
- Demo: Create white box tests
  - `src/de.sormuras.bach.demo/test/java/`
  - Compile "main" = `javac` + `jar`
- Demo: Launch JUnit Platform with `--scan-modules`

## Runtime matters

- https://blog.joda.org/2018/03/jpms-negative-benefits.html
> _The basic idea is simple, you put old fashioned non-modular jar files on the class-path, while you put modular jar files on the module-path. Nothing enforces this however, and it turns out this is a bit of a problem. There are thus four possibilities: modular jar on the module-path, modular jar on the class-path, classic non-modular jar on the module-path, and classic non-modular jar on the class-path._
> _To be sure your library works correctly, you need to test it both on the class-path and on the module-path. For example, service loading is very different on the module-path compared to the class-path. And some resource lookup methods also work completely differently._

## Static Check Module
- `jdeps --check` option is designed to determine if a named module has any unused module dependence or unused qualified-exports.
- Create `ModuleChecker(Path:GAV).assertNoUnusedDependences().assertNotUnusedQualifiedExports()` support

## Misc
- Multi-Release JAR
- IDE support?
- Build Tool support? -> "make-java"
- JUnit on Java 9 -> https://github.com/junit-team/junit5/issues/775
- Module version numbers -> https://github.com/sormuras/sormuras.github.io/blob/master/demo/test/parameterized/ParseModuleVersions.java

