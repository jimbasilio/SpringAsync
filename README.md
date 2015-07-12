Spring Boot application to demonstrate async behavior in spring

MUST PASS ARGUMENT TO JVM TO SELECT ACTIVE PROFILE:
user@machine:~/your/projects$ mvn clean package
user@machine:~/your/projects$ java -Dspring.profiles.active=[badasync|goodasync]-jar target/async-0.0.1-SNAPSHOT.jar

NOTES:
* painful lesson learned.  on ubuntu you can't have 'node' and 'nodejs' installed or bower (and probably other things) won't work.  'sudo apt-get autoremove node' and everything should work.  (https://github.com/bower/bower/issues/475)
* NOTE ABOUT THE NODE NOTE!!  Apparently ubuntu packages don't always link 'node' to 'nodejs'.  You can manually create this link if you are getting a '/usr/bin/env: node: No such file or directory' error.  Run 'sudo ln -s /usr/bin/nodejs /usr/bin/node' and it'll create a link to the nodejs app which will then allow bower to run.  I don't understand the packaging issues .. but I'm moving on from it.