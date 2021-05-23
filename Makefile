SHELL = /bin/sh

define HELP_TEXT
ktor-graphql-template Currently supported commands are:
 * verify       - Builds, launches, and runs all tests
 * run   		- Builds and runs the complete application suite
 * stop-all     - Stops the docker containers if they are running
 * f-test       - Runs the functional tests
 * f            - shortcut for f-test
 * lint         - Runs the Kotlin linter

endef
export HELP_TEXT

.PHONY: help
help:
	@echo "$$HELP_TEXT"

###############################################
######## Docker Compose wrap commands #########
###############################################

.PHONY: verify
verify:
	@make lint
	@make run
	@sleep 15
	@make f-tests

.PHONY: run
run:
	@./scripts/run.sh
	@sleep 2
	@echo "Started ktor-graphql-template"

.PHONY: f-tests
f-tests:
	@echo "Running Functional Tests"
	@./scripts/functional-tests.sh

.PHONY: f
f:
	@make f-tests

.PHONY: stop-all
stop-all:
	@./scripts/stop-all.sh

.PHONY: lint
lint:
	@./gradlew ktlintFormat

.PHONY: setup
setup:
	@echo "Applying lint style to IntelliJ"
	@./gradlew ktlintApplyToIdea
	@echo ""
	@echo "Adding linter pre-commit hook"
	@./gradlew addKtlintFormatGitPreCommitHook
