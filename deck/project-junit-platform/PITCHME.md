#### JUnit 5 = ...
# Platform
<br>

@ul

- Defines and uses **`TestEngine`**☑ interface
- Foundation for launching test engines

@ulend

+++

#### Test Engine ☑ Interface

```java
package org.junit.platform.engine;

public interface TestEngine {

  TestDescriptor discover(EngineDiscoveryRequest discoveryRequest...);

  void execute(ExecutionRequest executionRequest);

}
```
@[3](`TestEngine` interface)
@[5](Discovery)
@[7](Execution)
@[1-9]

<small>📜 [JUnit Platform Launcher API](https://junit.org/junit5/docs/current/user-guide/#launcher-api)</small>

+++

#### Platform Launcher

```java
   LauncherDiscoveryRequestBuilder.request()
     .selectors(
        selectPackage("org.example.user"),
        selectClass("org.example.payment.PaymentTests"),
        selectClasspathResource("/some/asset.txt"),
        selectClasspathRoots(Set.of(Path.of("..."), Path...)),
        selectDirectory("/home/user/tests"),
        selectFile("./test.list"),
        selectMethod(OrderTests.class, "test4"),
        selectModule("com.example.tool"),
        selectUniqueId("unique-id-1"),
        selectUri("http://what-ever.abc")
     )
     .filters(
        includeEngines("junit-jupiter", "spek"),
        excludeEngines("junit-vintage"),
        includeTags("fast"),
        excludeTags("slow"),
        includeClassNamePatterns(".*Test[s]?")
        includeClassNamePatterns("org\.example\.tests.*")
     )
     .configurationParameter("key1", "value1")
     .configurationParameters(configParameterMap)
     .build();
```
@[1](Launcher)
@[2-13](Selectors)
@[14-21](Filters)
@[22-23](Custom configuration parameters)
@[24](Build Discovery Request and...)

@ulend

+++

#### JUnit 5 = Platform + ...

![JUnit 5 Architecture](https://raw.githubusercontent.com/sormuras/sormuras.github.io/master/asset/img/junit5-architecture-a-platform.png)

+++

#### JUnit 5 = Platform + ...
# Vintage
<br>

@ul

- **`VintageTestEngine` is a `TestEngine`**☑
- Enables running JUnit 3 and 4 tests

@ulend

+++

![JUnit 5 Architecture](https://raw.githubusercontent.com/sormuras/sormuras.github.io/master/asset/img/junit5-architecture-b-vintage.png)

+++
 
#### JUnit 5 = Platform + Vintage + ...
# Jupiter
<br>

@ul

- **`JupiterTestEngine` is a `TestEngine`**☑
- New programming model for writing tests
- New extension model for writing extensions
- "*JUnit 5*"

@ulend

+++

![JUnit 5 Architecture](https://raw.githubusercontent.com/sormuras/sormuras.github.io/master/asset/img/junit5-architecture-c-jupiter.png)

+++

#### JUnit 5 = Platform + Vintage + Jupiter + ...
# Your Engine
<br>

@ul

- **`YourTestEngine implements TestEngine`**☑
- What is a test? **You define it!**
- How is a test evaluated? **You define it!**

@ulend

+++

![JUnit 5 Architecture](https://raw.githubusercontent.com/sormuras/sormuras.github.io/master/asset/img/junit5-architecture-d-overview.png)

+++

#### JUnit 5 = Platform + *many engines*
# 3<sup>rd</sup>-party Engines ☑

Specsy, Spek, KotlinTest, Cucumber, Drools, jqwik, Mainrunner...

<small><https://github.com/junit-team/junit5/wiki/Third-party-Extensions></small>

---

# This is JUnit 5

+++

![JUnit 5 Architecture](https://github.com/sormuras/testing-in-the-modular-world/raw/master/img/junit5-architecture-1.png)

+++

## IDE Support

- Excellent support
  - IntelliJ IDEA (≥ 2016.2)
  - Eclipse (≥ 4.7.1a)
  - Visual Studio Code (Java Test Runner ≥ 0.4.0)
  - Netbeans (≥ 10.0)
- For other tools, there's `@RunWith(JUnitPlatform)`

+++

## Build Tool Support

- Native support in
  - **Ant** (≥ 1.10.3)
  - **Gradle** (≥ 4.6)
  - **Maven** Surefire (≥ 2.22.0)
- **`ConsoleLauncher`** to run tests from the command line or to support other build tools (e.g. Bazel)
