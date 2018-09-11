# testing-in-the-modular-world

## blog

[2018-09-11-testing-in-the-modular-world](https://sormuras.github.io/blog/2018-09-11-testing-in-the-modular-world)

## slide deck

"Testing In The Modular World" (Living In The Material World)
Testing Modules With JUnit 5 - Initial Approaches

## abstract

The Java Platform Module System (JPMS) introduced with Java 9 poses new challenges when it comes to organizing and executing automated software tests.
Finding tests and executing them via the Reflection API is still possible but needs some extra configuration.
JUnit 5 supports scanning for tests in modules since 5.1 and has a sample project that demonstrates three possible approaches.
Sources are available at: https://github.com/junit-team/junit5-samples/tree/master/junit5-modular-world

I'll start the talk with a basic introduction to JUnit 5 and the JPMS.
The remainder of the talk will be spent on presenting the three approaches for executing tests when using the JPMS from the command line:
- Resorting to the `--class-path`
- Patching test binaries into main modules at runtime
- Patching main sources into test modules at compile time

Future work and outlook:
- Variations or other approaches?
- How do build tools support you?
- What are best practices in IDEs?

Happy modular testing!
