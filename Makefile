.PHONY: deploy

deploy:
	jar cvf frs.war -C WebContent/ .
	sudo cp frs.war /usr/share/tomcat7/webapps
