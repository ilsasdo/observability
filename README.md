
```sh
colima start --network-address
minikube start
minikube mount $(pwd)/build/libs:/apps
minikube mount $(pwd)/grafana:/grafana --uid 472 --gid 472
```

Access Grafana:
```shell
minikube service grafana
```

Access App:
```shell
minikube service myapp
```



## Resources

1. `grafana/otel-lgtm`: https://grafana.com/blog/2024/03/13/an-opentelemetry-backend-in-a-docker-image-introducing-grafana/otel-lgtm/
2. 