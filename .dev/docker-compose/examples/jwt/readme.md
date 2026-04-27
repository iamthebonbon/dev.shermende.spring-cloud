## Create RSA key-pair for JWT

```
openssl req -nodes -new -keyout private.key -out server.cert
openssl rsa -pubout -in private.key -out public.pem
```