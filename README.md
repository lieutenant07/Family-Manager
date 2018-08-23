
# Family Manager

This project is a demo app for family management. It provides functionalities such as creating new family by specifying father and subsequent children, and a basic search engine to retrieve results based on certain criteria.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites
Technologies used along the way:
* [`maven 3.5.4`](https://maven.apache.org/download.cgi)
* [`node.js 8.11.4`](https://nodejs.org/en/)
* [`npm 6.4.0`](https://www.npmjs.com/get-npm)
* [`angular cli`](https://cli.angular.io/)
* [`docker 18.03`](https://store.docker.com/editions/community/docker-ce-desktop-windows) (desktop for win10, and [`docker toolbox`] for win7 (make sure docker-compose is installed as well!)
* [`mysql 5.6`](https://hub.docker.com/r/mysql/mysql-server/)
* [`nginx:1.13.12`](https://docs.docker.com/samples/library/nginx/)
* [`jdk 1.8`](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* 
### #0 Quick installation using Docker 
* Navigate to`/spring-boot-rest-api` and execute
```
mvn clean install docker:build
```
* Navigate to the root folder (where `docker-compose.yml` resides) and execute 
```
docker-compose up
```
App is up and running at `http://192.168.99.100:80`

### #1 Installing using Docker - in detail

Project has two underlying modules:
* `spring-boot-rest-api` - provides REST API which will in turn be consumed by a client (back-end)
* `angular-spring-boot-client` - a client providing front for the REST API

The goal is to build and run whole application using `maven` and `docker-compose`. There are three containers to be built - one for the front-end, one for back-end, and one for the `MySQL database 5.6`.

>Before we move forward, let us remember that it is crucial to use JAVA 8, and we need to specify that version in system environment variables, so the terminal executes properly (Windows: Control Panel->System->Advanced system settings ->Environment variables), e.g.
>```
>JAVA_HOME: C:\Program Files\Java\jdk1.8.0_171;
>```
>```
>add to PATH: C:\Program Files\Java\jdk1.8.0_171\bin;
>```
>Sililarly for Maven, e.g.
>```
>add to PATH: C:\Program Files\apache-maven-3.5.4\bin;
>```
Alternatively we can use any given IDE to run Java and Maven and run the projects from there. For simplicity, we'll stick with terminal.
#### Step 1
Once repository is cloned, navigate to `spring-boot-rest-api` folder, and run following command from the terminal
```
mvn clean install docker:build
```
or
```
mvn clean install docker:build -X
```
for additional debug output. Maven will download all dependencies for the project, and `docker-maven-plugin` will automatically build a docker image named `restfullapi` using appropriate `jar` file 
> There is a `Dockerfile` in `spring-boot-rest-api/docker` which is being picked up by `docker-maven-plugin`

![mvn clean install docker-build](https://lh3.googleusercontent.com/2g0D62G0gRul2_2XBrTPEXAWEC4NBkYLS7d33mDf6UaGoy961wsjpIGvlnf1ph-0kj4aFYk-0aM)
#### Step 2
Once `restfullapi` image is up, it's ready to run via docker. 
![enter image description here](https://lh3.googleusercontent.com/qbIJS5e23qN6N_kVRqZHTq1Bak_dtYI-lPhF-1qs__ccAmZrFKVq7QsgRi_nBJv9gnISihHbnv8)
Similarly, there is a `Dockerfile` in `angular-spring-boot-client` root folder which is being picked up by `docker-compose.yml`, in which there is `mysql` image defined, even though we haven't created it before.
> Docker is smart enough to pull appropriate [`mysql image`](https://hub.docker.com/r/mysql/mysql-server/) (only if it hasn't been found locally on out virtual machine) and build it on our behalf.
> ![enter image description here](https://lh3.googleusercontent.com/lg8UBW35JHa7xuu5mWo2TUOhX9vVhX6V2xUoVKaa0xV6sDgAmurd8BPkJ4R_Ml3B71EFAMMfT1A)

Building container from `restfullapi` image as well as creating remaining images and building docker-containers for them (front-end + mysql db) is triggered by `docker-compose`. In order to initialize docker-compose navigate to project root folder and execute
```
docker-compose up
```
> `angular-spring-boot-client` is set up using `nginx`. Necessary configuration file `nginx.conf` is located in the main project root folder

This should perform all necessary actions to deploy our app on docker machine.

![enter image description here](https://lh3.googleusercontent.com/g0bGUcP19Y1OYTK46TXp9MsogdStUAh6Q3JTE2QC64SIqpb3XbcsAH1PafZ9c53V9zya6f0er-E)

**IMPORTANT!**
>In angular `family.service.ts` file we specified URL's for the back-end REST API's to be consumed. Be aware that `localhost` host name in URL address has been replaced with default docker-machine IP, which is usually `192.168.99.100`. If your docker-machine is running under different IP address, you have to amend the URL's in `family.service.ts`. 
>![enter image description here](https://lh3.googleusercontent.com/-N0uxX5JTBJaJYoL6DOhUyIIuergX4kAb8xRR5gFoTZo4XDtSPbBeagX7qMC1HI6PREffvgP0jI)

To check what is docker-machine address type in terminal
```
docker-machine ls
```
![enter image description here](https://lh3.googleusercontent.com/p4UDsoOHh7z8U4NzgYQcryqGrYmgDkzb1lPEX4y7n2ZKaVOi2b6k3EyMiJEcSGCvZ8YcWw0do5E)
Once docker-compose is done with creating images and running containers for us, Family Manager application will be available at `http://192.168.99.100:80`.
![enter image description here](https://lh3.googleusercontent.com/VuLyjSprXidqmh4awb7g5HV0kh3jzwHKLH4YMZWDMVfXbil5jEbhPMgo3L4vg7uV9s0Fb9EY37o)

>Remark: We could use only docker-compose feature but since there were instructions to use maven for building images we used `docker-maven-plugin` as well.
###  #2 Alternative setup (locally, without using docker VM)
We can start our application without docker. But it is crucial we make some amendments in `family.service.ts` before we start, namely we have to point to localhost host name instead of docker-machine IP address. We change those lines
```
private  baseUrl  =  'http://192.168.99.100:8080/fam';
private  searchUrl  =  'http://192.168.99.100:8080/searchChild';
```
to
```
private  baseUrl  =  'http://localhost:8080/fam';
private  searchUrl  =  'http://localhost:8080/searchChild';
```
Other thing to do is to amend `application.properties` db connection, pointing to local MySQL database (5.6), since in this scenario docker will not create MySQL instance for us, hence we have to do that manually. We can use `MySQL Workbench` to set up local database with adding specific users and privileges. We can use other databases as well, but in such case we need to remember to apply appropriate db connector as maven dependencies in order to establish successful db connection.
Last thing would be to download all dependencies for the client by executing following command from `angular-spring-boot-client`

```
npm install
```
This will populate `angular-spring-boot-client/node_modules` folder with all modules required to run the app.
> Note that this step is not required in docker approach, since this is included in a `Dockerfile` and will be automatically captured by docker-compose.

Once this is done, we can navigate to `spring-boot-rest-api` and execute commands
```
mvn clean install 
```
```
mvn spring-boot:run 
```
, and SpringBoot should start. We have now exposed our REST API's at `http://localhost:8080`.
Then to start the client navigate to `angular-spring-boot-client` and execute 
```
ng serve
```
Our client should start and we should be able to access is at `http://localhost:4200`

## Authors

* **Dawid Mazur** - *Initial work* - [GitHub](https://github.com/lieutenant07)

## Acknowledgments

* [Lukas Marx](https://malcoded.com/posts/angular-docker)
* [Aarsh Talati](https://wildclick.wordpress.com/category/code-library/docker/)
* [Koushik Kothagal](https://javabrains.io/courses/angular_basics/)
* spring.io: 	
	* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/) 
	* [Spring Data JPA - Getting started](https://spring.io/blog/2011/02/10/getting-started-with-spring-data-jpa/), 
	* [Spring Data REST - Getting started](https://spring.io/guides/gs/accessing-data-rest/), 
	* [Spring Data JPA - Reference documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/), 
	* [Spring Data REST - Reference Documentation](https://docs.spring.io/spring-data/rest/docs/current/reference/html/)
	* [Spring Initializr](https://start.spring.io/)