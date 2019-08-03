# JUnit 5 - Testing In The Modular World

## Code first, Test first

- Create test module "pura.vida" in IDEA

```java
module pura.vida {
  requires java.base; // module(s) under test
}
```
- Create main class `pura.vida.Main` with _psvm_
- Execute `pura.vida.Main` and show first failing and then working test case

### Move entry point to 
- Extend test module `pura.vida` with external test frameworks and open it for deep reflection

```java
open module pura.vida {
  // (...) module(s) under test

  requires org.junit.jupiter.api;
  requires org.assertj.core;
}
```

- Resolve dependencies via `bach` or use prepared `lib/` directory
- Talk about module names 

## Outline

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
  - Best practices regaring naming, requires modifiers, etc...
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

