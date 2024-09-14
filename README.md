
## How to run the application as a Podman container

### Install the prerequiste packages for buildah and podman

```bash
pkcon install -y buildah
pkcon install -y podman
```

### Build the container with podman

```bash
cd ~/.local/src/computate.org
podman build -t computateorg/computate.org:latest .
```

### Push the container up to quay.io
```bash
podman login quay.io
podman push computateorg/computate.org:latest quay.io/computateorg/computate.org:latest
```

### Restart site pod
```bash
oc -n computateorg delete pod -l app=computateorg
```

