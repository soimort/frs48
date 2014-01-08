.PHONY: build deploy all
default: all

build:
	mkdir -p WebContent/WEB-INF/classes
	javac -cp '.:/usr/share/java/servlet-api.jar' -d 'WebContent/WEB-INF/classes' src/**/*.java

deploy:
	jar cvf frs.war -C WebContent/ .
	sudo cp frs.war /usr/share/tomcat7/webapps

all: build deploy
