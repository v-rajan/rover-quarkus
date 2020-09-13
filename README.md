Compute Mars rover movement with REST and gRPC.

The solution build with Quarkus to be deployed on Kubernetes.

[Quarkus](https://quarkus.io/)

# Prerequites
* JDK 11
* Maven
* Docker
* kubectl for Kubernetes deployment

# Tools
* [LENS : Kubernetes IDE](https://k8slens.dev/)

# Setup
### Build
```console
cd rover-quarkus
mvn clean install
```
### Start Server

```console
./mvnw compile quarkus:dev
```

# Docker
[rover-quarkus](https://hub.docker.com/repository/docker/vrajan/rover-quarkus)
## Start Server
```console
docker run  -t \
    --name rover-quarku \
    -p 8080:8080 \
    -p 9000:9000 \
    vrajan/rover-quarkus:latest
````
# kubernetes
 
## Install
```console
kubectl create  -f target/kubernetes/kubernetes.yml
serviceaccount/rover-quarkus created
service/rover-quarkus created
deployment.apps/rover-quarkus created

kubectl get pods
NAME                             READY   STATUS    RESTARTS   AGE
rover-quarkus-844b6699f5-f7fgq   1/1     Running   0          77s

kubectl get svc
NAME            TYPE           CLUSTER-IP    EXTERNAL-IP      PORT(S)                         AGE
rover-quarkus   LoadBalancer   10.158.12.2   34.122.173.125   9000:30050/TCP,8080:32507/TCP   3m22s

kubectl get deployment
NAME            READY   UP-TO-DATE   AVAILABLE   AGE
rover-quarkus   0/1     1            0           4m33s
````

# Testing

### REST test
```console
cd src/test/resources/payload/sample001
curl -v -d "@request.json" http://34.122.173.125:8080/command
```   

### gRPC test
```console
vi src/main/resource/application.properties

Change host to direct to the deployed server.

# gRPC
quarkus.grpc.clients.command.host=localhost
quarkus.grpc.clients.command.port=9000

Run src/test/java/org/vrajan/rover/grpc/CommandTest.java
```   

	
