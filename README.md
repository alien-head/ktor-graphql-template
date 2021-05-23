# ktor-graphql-template
Template GraphQL Ktor application running in Docker.

### Features
* backed by postgres db
  * uses Exposed
  * Hikari Connection Pooling to support coroutines (used [this repository](https://github.com/raharrison/kotlin-ktor-exposed-starter) as an example)
  * Flyway for database migrations
* docker/docker compose
* makefile for running scripts
* Kotest
* functional tests
* verify
* separate models library

### Environment
| Name | Version |
|------|---------|
| JDK | amazon-corretto 16.0.1 |
| Kotlin | 1.5 |
| Gradle | 7.0 |
| Ktor | 1.5.4 |

A full list of dependency versions can be found in [gradle.properties](/gradle.properties)

### Running the app
If you use Jabba for java version management, Amazon Corretto 16 is not available in `ls-remote` yet. 
Do `jabba install amazon-corretto@1.16.0=tgz+https://corretto.aws/downloads/latest/amazon-corretto-16-x64-macos-jdk.tar.gz` instead.

Run `make setup`. This will setup ktlint and git precommit hooks.

After that do `make run` or `./scripts/run.sh` in the command line. 
This script builds the project, removes existing docker containers, starts postgres, and starts the api (flyway migrations are handled by the app).

Run `make help` for a list of commands.
