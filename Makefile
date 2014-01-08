.PHONY: deploy

deploy:
	jar -cvf frs.war *
	sudo cp frs.war /usr/share/tomcat7/webapps
