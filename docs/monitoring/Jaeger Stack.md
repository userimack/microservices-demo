# Setup Jaeger Cluster
Use the following command to list the projects and copy the right project id with name  `ObservabilityWorkshop1`:

```
gcloud projects list
```

Set the environment variables (after pasting the Project ID):

```
# configure the project id of the 
PROJECT_ID="## Paste Copied Project ID ##"
PROJECT_ID="observabilityworkshop1-365105"
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
cd ~/cloudshell_open/microservices-demo/monitoring-manifest/jaeger-stack/
```


Create a namespace

```
kubectl create namespace "kube-tracing"
```


Use `kubectl` to apply the generated manifest to your Kubernetes cluster:

```
kubectl apply -f "jaeger_manifest.yaml" --namespace "kube-tracing"
```

The cluster would take a few minutes to be up and running.

```
kubectl get pods --namespace=kube-tracing
```

To get the GCP Console URL, run the following command:

```
echo "https://console.cloud.google.com/kubernetes/workload/overview?project=${PROJECT_ID}"
```


# Viewing the Jaegar UI
By default, the Jaegar does not have an external IP. To expose an external IP for Jaegar, run the following command:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID} --zone=${ZONE} \
	&& kubectl port-forward \
	--namespace=kube-tracing $(kubectl get pod --namespace=kube-tracing \
	--selector="app=jaeger" \
	--output jsonpath='{.items[0].metadata.name}') 16686:16686

```

And then use the below URL to access the Jaegar:

```
https://ssh.cloud.google.com/devshell/proxy?port=16686
```

