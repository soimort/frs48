.PHONY: build war deploy all
default: all

build:
	mkdir -p WebContent/WEB-INF/classes
	javac -cp '.:/usr/share/java/servlet-api.jar' -d 'WebContent/WEB-INF/classes' src/**/*.java

war:
	jar cvf frs.war -C WebContent/ .

deploy:
	sudo cp frs.war /usr/share/tomcat7/webapps

all: build war deploy
