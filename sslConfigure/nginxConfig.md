# NGINX Config:

### path : /etc/nginx/sites-available/default

```
upstream <ip>{
    server localhost:8103;
}

#server {
#	listen 443 ssl http2;
#	listen [::]:443 ssl http2;

#	server_name <ip>;

#	ssl_certificate	/etc/ssl/certs/nginx-certificate.crt;
#    	ssl_certificate_key	/etc/ssl/private/nginx-private.key;

#	location / {
#	       proxy_pass http://$host$request_uri;
#	}
#}

server {
    listen 80 default_server;
    listen [::]:80 default_server;

    server_name <ip>;
    
    #return 301 https://$host$request_uri;
    
    location ~ ^/.well-known/pki-validation(/?)(.*) {
        alias /var/www/html/*.txt;
    }
}
```