```
docker build -t nginx-test .
docker run --rm -it -p 8080:80 nginx-test:latest
```

https://www.google.com/search?q=nginx+conf+for+forward+proxy&oq=nginx+conf+for+forward+proxy&aqs=chrome..69i57j0i22i30l4j0i390l2.7210j0j15&sourceid=chrome&ie=UTF-8

https://stackoverflow.com/questions/46330313/nginx-ssl-forward-proxy-config
The reason NGINX does not support HTTPS forward proxying is because it doesn't support the CONNECT method. However, if you are interested in using it as a HTTPS forwarding proxy you can use the ngx_http_proxy_connect_module


https://github.com/chobits/ngx_http_proxy_connect_module
server {
     listen                         3128;

     # dns resolver used by forward proxying
     resolver                       8.8.8.8;

     # forward proxy for CONNECT request
     proxy_connect;
     proxy_connect_allow            443 563;
     proxy_connect_connect_timeout  10s;
     proxy_connect_read_timeout     10s;
     proxy_connect_send_timeout     10s;

     # forward proxy for non-CONNECT request
     location / {
         proxy_pass http://$host;
         proxy_set_header Host $host;
     }
 }
