
## Install keycloak

```bash
helm -n keycloak upgrade --install keycloak oci://registry-1.docker.io/bitnamicharts/keycloak --version 24.4.9 \
  --set postgresql.enabled=false \
  --set externalDatabase.host=postgresql.postgres.svc \
  --set externalDatabase.user=keycloak \
  --set externalDatabase.password=$(oc -n keycloak get secret/postgres-pguser-keycloak -o jsonpath={.data.password} | base64 -d) \
  --set externalDatabase.database=keycloak \
  --set externalDatabase.port=5432 \
  --set ingress.enabled=true \
  --set production=true \
  --set proxy=edge \
  --set ingress.hostname=sso.computate.org \
  --set ingress.controller=gce \
  --set ingress.pathType=Prefix \
  --set adminIngress.controller=gce \
  --set adminIngress.hostname=sso.computate.org \
  --set adminIngress.pathType=Prefix \
  --set ingress.tls=true \
  --set image.tag=26.0.8 \
  --set replicaCount=1 \
  --set auth.existingSecret=keycloak-initial-admin \
  --set auth.adminUser=admin \
  --set auth.passwordSecretKey=admin-password
```

