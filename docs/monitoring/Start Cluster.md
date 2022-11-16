Start GKE cluster:

```
gcloud container clusters create ${CLUSTER} \
    --project=${PROJECT_ID} --zone=${ZONE} \
    --machine-type=e2-standard-8 --num-nodes=1
    
```
