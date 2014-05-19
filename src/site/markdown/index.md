
# About Java Config

The search for a configuration management solution.

Primary focus is placed on:

* A loosely coupled API, i.e. easy to replace or remove
* Type safety and constraints of property values
* Distribution of properties
* Namespacing of properties

## Type Safety & Constraints

### Property Constraints

Affects the property as a whole:

* Default values
  * Proposed: Defined in Bean
* Presence (required)
  * Proposed: NoSuchElementException

### Value Type Constraints

Built-in type support:

* String
* Integer
* Long
* Enum
* URL

Custom types:

* Range

### Application Constraints

Application level constraint validation of property values is left mostly
to the application, i.e. the properties bean could be annotated with
JSR 303 (Bean Validation) annotations, and validation of the bean could be
performed after construction. An even simpler approach would be to evaluate
the property values on the bean directly after construction.

The API provides registration of custom type converters. The application
can provide custom type converters, and constraints could then be enforced
by the application in the conversion process.

Custom type examples:

* Port, validates in range (1 - 65535)
* PrivilegedPort, validates in range (1 - 1023)
* NonPrivilegedPort, validates in range (1024 - 65535)
* Email, i.e. ensures valid email

Note that the above can directly be translated into custom JSR 303 validation
types, i.e.:

* @Port
* @PrivilegedPort
* @NonPrivilegedPort
* @Email (already provided by some implementations)

Custom type converters require no external dependencies (JSR 303 API and
implementation), and the nature of the converters allow easy removal
of the Configuration API.

## Namespace

### Traditional Approach

Traditionally properties are namespaced using a prefix, i.e.:

```
file: app.properties
my.app.db.hostname = database.com
my.app.db.port = 3306

my.app.uploads.folder = /srv/uploads
my.app.uploads.enable = true
```

Properties are then all placed together in a big, logical pool. Mechanisms
are generally put in place as well to inherit default properties, and to
layer access to property sources to provide precedence (order, hierarchy).

### Property Beans

With the Configuration API properties are grouped using properties beans.
The beans are already namespaced in Java using packages and classes.
This mechanism is re-used for namespacing properties, i.e.:

```
class: com.abc.app.database_properties
entry [key: 'hostname', value: 'database.com']
entry [key: 'port', value: '3306']

class: com.abc.app.uploads_properties
entry [key: 'folder', value: '/srv/uploads']
entry [key: 'enable', value: 'true']
```

This mechanism provides a simple way to logically group properties and
fits in well with the Object Oriented paradigm.

### Scalability

Properties beans provide logical grouping and management of properties.
As with source code, there is another aspect of the environment in which
the application run.

An application may load an in-memory database when running in integration
test mode, and connect to a database server in production mode.

The same is true for properties. Different properties are loaded based
on the mode the application is run in. Additionally, different properties
may need to be loaded based on the node on which the application is run
when configured in a cluster.

The Configuration API supports this additional dimension by adding a
namespace element.

```
class: com.abc.app.uploads_properties

namespace: test
entry [key: 'enable', value: 'false']

namespace: development
entry [key: 'folder', value: '~/app/uploads']
entry [key: 'enable', value: 'true']

namespace: prod-1
entry [key: 'folder', value: '/srv/uploads']
entry [key: 'enable', value: 'true']

namespace: prod-2
entry [key: 'enable', value: 'false']
```

### Versioning

There are two aspects to versioning. Which properties currently are
applied to each node. This could be management using an scm or some
other IT operations management tool.

Which properties are defined by the application: Defined by the src

## Resolving

Resolving is done using providers. Providers implement the Provider
API.

A provider resolves a property based on 3 attributes:

* class
* namespace
* key

=========================
Repository-based approach
=========================

i.e. Eclipse Aether, Maven Wagon

groupId
artifactId
version


FILE:

/etc/com.app.database_properties.cfg
/etc/com.app.database_properties.yaml
/etc/com.app.database_properties.xml

DATABASE:

NamespaceTable: ID, Namespace
PropertiesTable: NamespaceID, Key, Value

HTTP:

Single Property
http://www.properties.com/?namespace=com.app.database_properties&key=app.key

Multiple Properties
http://www.properties.com/?namespace=com.app.database_properties&key=app.key

WEB-DAV:
http://www.properties.com/etc/


JNDI:
Context initContext = new InitialContext();
Context envContext  = (Context)initContext.lookup("java:/comp/env");
DatabaseProperties properties = (DatabaseProperties) envContext.lookup("etc/com.app.database_properties");

GIT:
Becomes http, i.e:
http://www.gitrepo.com/branch/raw/etc/com.app.database_properties.cfg
<-           BASE URL           -><--><-        Property name      ->


SVN:

## Inventory

Which properties are there

## State

State history is kept of all properties loaded. The state history could
be queried using the API. A textual and object based (a copy of the
properties bean) representation of the properties are provided.

The state also forms the basis of the caching mechanism.

## Mutability

Mutability is concerned with property changes during runtime.

The Configuration API providers will by default fetch property values
directly from the source during construction. This means that every
time a properties object is constructed, the file be read again from
the file system or a database will be accessed.

TODO:

CacheablePropertiesFactory to allow caching of specified properties
objects to accommodate applications that by design cannot or do not
cache properties objects.

See: State

### Refresh & Listeners

Monitoring of property sources at this time is out of scope for the
configuration provider, and is left up to the application.

Once the application has been notified of property value changes,
the properties could be reconstructed using the Configuration API
to get the latest changes.

TODO:

Future versions could perhaps define a last-changed attribute that
could be monitored. Possibly monitor a folder for files, upon a
change a ConfigurationUpdatedEvent could be fired.

This could be phased in, i.e. an entire namespace is changed, later on
checking for specific property changes within a namespace.

## Format

MULTI-ENVIRONMENT SUPPORT
-------------------------
Production
----------
Load properties from /etc/file

Scalability?
------------

1 node

100 node cluster?
 - Puppet (File)
 - Database
 - REST

Operations
----------

Easy to update files





State
-----

List currently loaded property state



Embeddable
----------




==========

PropertiesFactory.refreshProviders

Property refresh
Property change listener








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
