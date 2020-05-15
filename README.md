# edusync

### Packaging the app
- if you're on Linux or Mac
- `./mvnw clean install`
- `./mvnw package`

- if you're on Windows
- `mvnw clean install`
- `mvnw package`


### Building the Docker image

- `docker build --tag edusync .`

### Running the Docker image as container

- `docker run --publish 8000:8080 --detach edusync`

### Using the app

- this app uses TLS to protect your NextCloud passwords in-transit
- assuming youre running the container locally,  navigate to `https://localhost:8000` and accept the SSL-Warning
- otherwise, replace `localhost` with your Docker Server domain name or IP-Address
