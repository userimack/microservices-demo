# Setup Prometheus Cluster
Use the following command to list the projects and copy the right project id with name  `ObservabilityWorkshop1`:

```
gcloud projects list
```

Set the environment variables (after pasting the Project ID):

```
# configure the project id of the 
PROJECT_ID="## Paste Copied Project ID ##"
REGION=us-central1
ZONE=us-central1-a
CLUSTER=observability-workshop-gke
```

Make sure `kubectl` uses the correct created cluster:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID}  --zone=${ZONE}
```

To set up your cluster to understand Application resources, run the following command:
```
kubectl apply -f "https://raw.githubusercontent.com/GoogleCloudPlatform/marketplace-k8s-app-tools/master/crd/app-crd.yaml"
```

Navigate to the EFK Manifest directory

```
cd ~/cloudshell_open/microservices-demo/monitoring-manifest/prometheus-stack/
```


Create a namespace

```
kubectl create namespace "kube-monitoring"
```


Use `kubectl` to apply the generated manifest to your Kubernetes cluster:

```
kubectl apply -f "prometheus_manifest.yaml" --namespace "kube-monitoring"
```

The cluster would take a few minutes to be up and running.

```
kubectl get pods --namespace=kube-logging
```

To get the GCP Console URL, run the following command:

```
echo "https://console.cloud.google.com/kubernetes/workload/overview?project=${PROJECT_ID}"
```



# Viewing the Prometheus UI
By default, the Prometheus does not have an external IP. To expose an external IP for Prometheus, run the following command:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID} --zone=${ZONE} \
	&& kubectl port-forward \
	--namespace=kube-monitoring $(kubectl get pod --namespace=kube-monitoring \
	--selector="k8s-app=prometheus" \
	--output jsonpath='{.items[0].metadata.name}') 9091:9090
```

And then use the below URL to access the Prometheus:

```
https://ssh.cloud.google.com/devshell/proxy?port=9091
```


# Viewing the Grafana UI
By default, the Grafana does not have an external IP. To expose an external IP for Grafana, run the following command:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID} --zone=${ZONE} \
	&& kubectl port-forward \
	--namespace=kube-monitoring $(kubectl get pod --namespace=kube-monitoring \
	--selector="k8s-app=grafana" \
	--output jsonpath='{.items[0].metadata.name}') 9092:3000
```

And then use the below URL to access the Grafana:

```
https://ssh.cloud.google.com/devshell/proxy?port=9092
```

And then use the below credentials to access Grafana:

```

Username: admin
Password: superSecretGrafanaPassword

```

# Viewing the AlertManager UI
By default, the AlertManager does not have an external IP. To expose an external IP for AlertManager, run the following command:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID} --zone=${ZONE} \
	&& kubectl port-forward \
	--namespace=kube-monitoring $(kubectl get pod --namespace=kube-monitoring \
	--selector="k8s-app=alertmanager" \
	--output jsonpath='{.items[0].metadata.name}') 9093:9093
```

And then use the below URL to access the AlertManager:

```
https://ssh.cloud.google.com/devshell/proxy?port=9093
```


# Viewing the SMTP
By default, the SMTP does not have an external IP. To expose an external IP for SMTP, run the following command:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID} --zone=${ZONE} \
	&& kubectl port-forward \
	--namespace=kube-smtp $(kubectl get pod --namespace=kube-smtp \
	--selector="app=smtp4dev" \
	--output jsonpath='{.items[0].metadata.name}') 9000:80
```

And then use the below URL to access the Kibana:

```
https://ssh.cloud.google.com/devshell/proxy?port=9000
```
