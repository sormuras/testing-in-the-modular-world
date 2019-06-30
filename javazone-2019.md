# JUnit 5 - Testing In The Modular World

## Outline

- Show of hands 1: JUnit 3/4 - JUnit 5
- Show of hands 2: method - type - package - module - repository - "cloud"

## Static Check Module
- `jdeps --check` option is designed to determine if a named module has any unused module dependence or unused qualified-exports.

## Runtime matters

- https://blog.joda.org/2018/03/jpms-negative-benefits.html
> _The basic idea is simple, you put old fashioned non-modular jar files on the class-path, while you put modular jar files on the module-path. Nothing enforces this however, and it turns out this is a bit of a problem. There are thus four possibilities: modular jar on the module-path, modular jar on the class-path, classic non-modular jar on the module-path, and classic non-modular jar on the class-path._
> _To be sure your library works correctly, you need to test it both on the class-path and on the module-path. For example, service loading is very different on the module-path compared to the class-path. And some resource lookup methods also work completely differently._

## Misc
- Multi-Release JAR
- IDE support?
- Build Tool support? -> "make-java"
