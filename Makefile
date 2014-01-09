CATALINA_HOME := $(HOME)/Programs/apache-tomee-plus-1.6.0
SOURCES := $(shell find $(SOURCEDIR) -name '*.java')

.PHONY: build war deploy all
default: all

build:
	mkdir -p WebContent/WEB-INF/classes
	javac -cp .:$(CATALINA_HOME)/lib/servlet-api.jar:$(CATALINA_HOME)/lib/javaee-api-6.0-5-tomcat.jar -d 'WebContent/WEB-INF/classes' $(SOURCES)

war:
	jar cvf frs.war -C WebContent/ .

deploy:
	cp frs.war $(CATALINA_HOME)/webapps

all: build war deploy
