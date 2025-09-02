#!/usr/bin/env bash

colima start --network-address
minikube start
minikube mount $(pwd)/build/libs:/apps &
minikube mount $(pwd)/data/grafana:/grafana --uid 472 --gid 472 &