CATALINA_HOME := $(HOME)/Programs/apache-tomee-plus-1.6.0

.PHONY: build deploy all
default: all

build:
	mvn clean install

deploy:
	cp target/frs.war $(CATALINA_HOME)/webapps

all: build deploy
