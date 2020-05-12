# edusync

## Building and running

### 1. package the app using Maven as a jar file (JAVA_HOME must be set on the machine)

`./mvnw package` if you're on Linux or Mac

### 2. build the Docker image from the jar file.

`docker build --tag edusync .`

### 3. run the container as an image.

`docker run --publish 8000:8080 --detach edusync`
