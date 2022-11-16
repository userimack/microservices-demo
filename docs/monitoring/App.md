# Setup Online Boutique App cluster

Navigate to :

```
cd ~/cloudshell_open/
```


Clone the required packages:

```
git clone --depth 1 https://github.com/anushasingh/microservices-demo
```

Navigate into the cloned repository:

```
cd microservices-demo
```

Deploy the Online boutique sample app in the cluster:

```
scaffold
```

Wait for the Pods to be ready; It would take a few mins to Pods to spin up

```
kubectl get pods --namespace default
```

Access the web frontend in a browser** using the frontend's `EXTERNAL_IP`.

```
kubectl get service frontend-external | awk '{print $4}'
```
