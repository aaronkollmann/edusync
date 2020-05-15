# edusync

1. package the app using Maven as a jar file
- if you're on Linux or Mac
- `./mvnw clean install`
- `./mvnw package`

- if you're on Windows
- `mvnw clean install`
- `mvnw package`


2. build the Docker image from the jar file.

- `docker build --tag edusync .`

3. run the container as an image.

- `docker run --publish 8000:8080 --detach edusync`

4. using the app

- this app uses TLS to protect your NextCloud passwords in-transit
- navigate to `https://localhost:8000` and accept the SSL-Warning
