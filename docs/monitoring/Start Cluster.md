Set the environment variables (after pasting the Project ID):

```
# configure the project id of the 
PROJECT_ID="## Paste Copied Project ID ##"
REGION=us-central1
ZONE=us-central1-a
CLUSTER=observability-workshop-gke
```

Enable Google API for creating containers for the project

```
gcloud services enable container.googleapis.com --project ${PROJECT_ID}
```

Start GKE cluster:

```
gcloud container clusters create ${CLUSTER} \
    --project=${PROJECT_ID} --zone=${ZONE} \
    --machine-type=e2-standard-8 --num-nodes=1
    
```
