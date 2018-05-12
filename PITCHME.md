@title[Testing In The Modular World]
# Testing In The
# Modular World

+++

@title[Living In The Material World]
#### Testing In The Modular World
<br>

_"Can't say what I'm doing here_

_But I hope to see much clearer,_

_After living in the material world."_

**George Harrison**

+++

@title[Agenda]
#### Testing In The Modular World
## Agenda
<br>

@ul

- Using **JUnit 5** ✅ to launch tests
- Organized in **Java Modules** ☕
- Foundation tools **`javac`** and **`java`**
- Questions and Answers

@ulend

---

# What is JUnit 5?

+++

## There is **no** JUnit 5

+++
 
#### JUnit 5 = ...
# Platform
<br>

@ul

- Foundation for launching test engines
- Defines and uses **`TestEngine`** interface

@ulend

+++
 
#### JUnit 5 = Platform + ...
# Jupiter
<br>

@ul

- New programming model for writing tests
- New extension model for writing extensions
- **`JupiterTestEngine implements TestEngine`**

@ulend

+++
 
#### JUnit 5 = Platform + Jupiter + ...
# Vintage
<br>

@ul

- Enables running JUnit 3 and 4 tests
- **`VintageTestEngine implements TestEngine`**

@ulend

+++

#### JUnit 5 = Platform + Jupiter + Vintage + ...
# Your Engine
<br>

@ul

- What is a test? **You define it!**
- How is a test evaluated? **You define it!**
- **`YourTestEngine implements TestEngine`**

@ulend

+++

#### JUnit 5 = Platform + *many engines*
# 3<sup>rd</sup>-party Engines

Specsy, Spek, KotlinTest, Cucumber, Drools, jqwik, ...

<small><https://github.com/junit-team/junit5/wiki/Third-party-Extensions></small>

---

# This is JUnit 5

+++

![JUnit 5 Architecture](img/junit5-architecture-0.png)


+++

![JUnit 5 Architecture](img/junit5-architecture-1.png)

---

# Jupiter Test Engine

+++

### First Jupiter Test

```java
import org.junit.jupiter.api.*;

class FirstJupiterTests {

    @Test
    void myFirstTest() {
        Assertions.assertEquals(2, 1 + 1, "2 is two");
    }

}
```
@[1](Import JUnit Jupiter API)
@[3,6](Use 'package-private' modifier)
@[5-8](@Test-annotated method, also 'package-private')
@[1-10]

+++

### Composed Annotations

```java
@Tag("fast")                     // file: FastSystemTest.java
@Tag("system")
@Test
@interface FastSystemTest {}

@FastSystemTest                  // file: FirstJupiterTests.java
void mySecondTest() {...} 
```
@[1-2](Multiple tags)
@[3](Mark as test)
@[6-7](Use your meta-annotation)
@[1-7]

+++

### Jupiter Features 1
<br>

@ul

- Basic Annotations - *`@Test`*
- Meta-Annotations - *`@FastSystemTest`*
- Display Names - *Emojis! 😎 🐌* @note[Spaces in names...]
- Tagging and Filtering
- Assertions @note[Use external assertion libs. They are good!]
- Assumptions
- Disabling Tests
- Conditional Test Execution

@ulend

+++

### Jupiter Features 2
<br>

@ul

- Test Instance Lifecycle
- Nested Tests
- Dependency Injection
- Test Interfaces
- Repeated Tests - 🔂
- Dynamic Tests
- Test Templates
- Parameterized Tests - ✨

@ulend

+++

### Jupiter Features 3
## User Guide
<br>

https://junit.org/junit5/docs/current/user-guide

Note:
The user guide contains the latest documentation and usage examples.
Most of it explains the Jupiter API and programming model.
Platform and Vintage are covered, too.

+++

### Jupiter Features 4
## 3<sup>rd</sup>-party Extensions

Spring, Mockito, Docker, Wiremock, JPA, Selenium/WebDriver,
DbUnit, Kafka, Jersey, GreenMail, S3Mock, Citrus Framework,
XWiki, ...

<small>https://github.com/junit-team/junit5/wiki/Third-party-Extensions</small>

---

@title[Java Platform Module System]
# Java Platform
# Module System

- JSR 376 <https://jcp.org/en/jsr/detail?id=376>
- Jigsaw <http://openjdk.java.net/projects/jigsaw>

Note: https://twitter.com/mreinhold/status/994669659029999616
Please stop calling it "JPMS", which sounds like an unpleasant medical condition.
It’s just “the Java module system.” #jigsaw #jdk9 #java

+++

### Goals of the ~~JPMS~~
## Java Module System

@ul

- Reliable configuration @note[to replace the brittle, error-prone class-path mechanism with a means for program components to declare explicit dependences upon one another]
- **Strong** encapsulation @note[to allow a component to declare which of its APIs are accessible by other components, and which are not]
- A scalable Java SE Platform @note[whose components can be assembled by developers into custom configurations that contain only the functionality actually required by an application]
- Greater platform integrity @note[to ensure that code that is internal to a platform implementation is not accessible from outside the implementation]
- Improved performance @note[by applying whole-program optimization techniques to complete configurations of platform, library, and application components]

@ulend

+++ 

# What is a module?

_Modules_ are named, self-describing program components consisting of code and data.
A module must be able to contain Java classes and interfaces, as organized into packages,
and also native code, in the form of dynamically-loadable libraries.
A module’s data must be able to contain static resource files and user-editable configuration files.
<small><http://openjdk.java.net/projects/jigsaw/spec/reqs/#fundamentals></small>

+++

# That is a module!

- Named program component
- Set of packages (code and data)
- Module meta-data (module descriptor)

---

# Modular Application

@ul

- Application named **`com.example.application`**
- contains only **`Main`** entry point and
- uses library called **`com.example.tool`**.<br>

- Libray named **`com.example.tool`**
- publishes **`Calculator`**
- uses **`MathHelper`** internally.<br>

- Bonus! Own test engine: **`ice.cream`** 🍦🍦🍦

@ulend

+++

#### Main Modules: `tree src/main`

```txt
main
├──📀 com.example.application
│   ├── com
│   │   └── example
│   │       └── application
│   │           └── 📜 Main.java
│   └── ☕ module-info.java
│
├──📀 com.example.tool
│   ├── com
│   │   └── example
│   │       └── tool
│   │           ├── 📜 Calculator.java
│   │           └── internal
│   │               └── 📜 MathHelper.java
│   └── ☕ module-info.java
│
└──📀 ice.cream
    ├── ice
    │   └── cream
    │       ├── 📜 Flavor.java
    │       ├── 🍦 Machine.java
    │       └── 📜 Scoop.java
    └── ☕ module-info.java
```
@[2-7](`module com.example.application`)
@[9-16](`module com.example.tool`)
@[18-24](`module ice.cream`)

+++

#### Main Modules: `module-info.java`

```java
module com.example.tool {          module com.example.application {
  exports com.example.tool; 📁       requires com.example.tool; 📀
}                                  }
```

```java
module ice.cream {
	requires org.junit.platform.engine; 📀

	provides org.junit.platform.engine.TestEngine 📜
	    with ice.cream.Machine; 🍦
}
```
<small><https://docs.oracle.com/javase/specs/jls/se10/html/jls-7.html#jls-7.7></small>

+++

#### Main Modules: Compile

```sh
javac                            javac
  -d bin/main                      -d bin/main
  --module-source-path src/main    --module-source-path src/main
  --module com.example.tool        --module com.example.application
```
@[1](Compiles Java source files into class files.)
@[2](Sets the destination directory for class files.)
@[3](Specifies where to find input source files for multiple modules.)
@[4](Compiles only the specified module and checks time stamps.)
@[1-4]

Note: Analogue command for the ice.cream module

+++

#### Main Modules: Package

```sh
jar
  --create
  --file       bin/main-jars/com.example.application.jar
  --main-class com.example.application.Main
  -C           bin/main/com.example.application
  .
```
@[1](Archiving and compression tool.)
@[2](Creates the archive.)
@[3](Specifies the archive file name.)
@[4](Specifies the application entry point for standalone applications bundled into a modular or executable modular JAR file.)
@[5-6](Changes the specified directory and includes the files specified at the end of the command line.)
@[1-6]

+++

#### Main Binaries

![bin/main](img/screenshot-bin.png)

+++

#### Tool Module

```bash
com.example.tool .../bin/main-jars/...tool.jar/!module-info.class
exports com.example.tool
requires java.base mandated
contains com.example.tool.internal
```
@[1](Module name and archive file)
@[2](Published package)
@[3](Dependency)
@[4](Non-published package)
@[1-4]

+++

#### Application Module

```bash
com.example.application ...application.jar/!module-info.class
requires com.example.tool
requires java.base mandated
contains com.example.application
main-class com.example.application.Main
```
@[1](Module name and archive file)
@[2-3](Dependencies)
@[4](Non-published package)
@[5](Entry-point)
@[1-5]

+++

#### Ice Cream 🍦 Module

```bash
ice.cream@47.11 .../bin/main-jars/ice.cream.jar/!module-info.class
requires java.base mandated
requires org.junit.platform.engine
provides org.junit.platform.engine.TestEngine
    with ice.cream.Machine
contains ice.cream
```
@[1](Module name, version and archive file)
@[2-3](Dependencies)
@[4-5](Service)
@[6](Non-published package)
@[1-6]

---

# Modular World

https://github.com/junit-team/junit5-samples/blob/master/README.md
