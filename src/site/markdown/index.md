## About Java Config

Java Application Configuration.

## Goals

A simple configuration API with the ability to:

* Load properties from:
  * Defaults
  * Classpath?
  * File
  * System properties?
  * System environment variables?
* Formats:
  * Java Properties
  * YAML
* Defaults
* Constraints:
  * Required properties
  * Hide Password?
* Typesafe
  * Match properties to method
* Properties objects should have no dependency on API

## Defaults

* Directly in properties bean class
* Hierarchy?
