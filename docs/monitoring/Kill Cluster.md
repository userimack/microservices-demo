# Clean up 

To delete the entire Kubenetes cluster after the workshop; This may take a while

```
gcloud container clusters delete ${CLUSTER} \
    --project=${PROJECT_ID} --zone=${ZONE}
```