# Setup EFK Cluster
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
cd ~/cloudshell_open/microservices-demo/monitoring-manifest/efk-stack/
```


Create a namespace

```
kubectl create namespace "kube-logging"
```


Create the Service account for `fluentd` to access the logs

```
kubectl create serviceaccount elastic-logging-1-fluentdserviceaccount --namespace kube-logging

kubectl create clusterrole elastic-logging-1-fluentdserviceaccount-role --verb=get,list,watch --resource=pods,namespaces

kubectl create clusterrolebinding elastic-logging-1-fluentdserviceaccount-rule --clusterrole=elastic-logging-1-fluentdserviceaccount-role --serviceaccount=kube-logging:elastic-logging-1-fluentdserviceaccount

```

Use `kubectl` to apply the generated manifest to your Kubernetes cluster:

```
kubectl apply -f "efk_manifest.yaml" --namespace "kube-logging"
```

The cluster would take a few minutes to be up and running.

```
kubectl get pods --namespace=kube-logging
```

To get the GCP Console URL, run the following command:

```
echo "https://console.cloud.google.com/kubernetes/workload/overview?project=${PROJECT_ID}"
```

# Viewing the Kibana UI
By default, the Kibana does not have an external IP. To expose an external IP for Kibana, run the following command:

```
gcloud container clusters get-credentials ${CLUSTER} \
	--project=${PROJECT_ID} --zone=${ZONE} \
	&& kubectl port-forward \
	--namespace=kube-logging $(kubectl get pod --namespace=kube-logging \
	--selector="app.kubernetes.io/component=kibana-server,app.kubernetes.io/name=elastic-logging-1" \
	--output jsonpath='{.items[0].metadata.name}') 8081:5601
```

And then use the below URL to access the Kibana:

```
https://ssh.cloud.google.com/devshell/proxy?port=8081
```

By now you should see logs flowing in from various apps
