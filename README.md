# Vert.x Example Maven Project

Example project for creating a Vert.x module with a Gradle build.

By default this module contains a simple Java verticle which listens on the event bus and responds to `ping!`
messages with `pong!`.

This example also shows you how to write tests in Java, Groovy, Ruby and Python

Changes:

* Removed all non-java code
* Removed integration tests
* Changed pom to use newest vertx version
* Added maven-jxr-plugin to avoid warnings during build
* Added some verticles to check some api-features
* Added some external lib (GSON) in pom to see how integration works
** Problem: During compile phase no target/dependencies is created so you always get ClassNotFoundException
** Solution: Changed phase of task "copy-mod-dependencies-to-target-dependencies" to "generate-resources"
* To use reload mechanism you have two option as intellij user:
** Option 1. run maven in terminal (external) and activate in intellij compiler-option to "make project automatically"
** Option 2. run maven in intellij, to trigger reload you have to manually make project shortcut: crtl + f9 (better custom keybinding)
* You can't send pojos over eventbus -> always json to/from object mapping necessary?
* One handler per verticle a good ratio?
* Message bus addresses as constants in one class that is provided as non-runnable module?