# Install SSL

1. https://app.zerossl.com/certificate/new
2. fill up domain
3. press next until you get verify domain page
4. check http file upload
5. download the auth txt file
6. now copy the file to server from your local `scp <fileName>.txt  <user>@<ip>:/home/<user>/ssl/`
7. in server copy the `sudo cp /home/<user>/ssl/<fileName>.txt /var/www/html/`
8. in server edit the nginx config file `sudo vim /etc/nginx/sites-available/default`
9. modify the line alias `/var/www/html/*.txt;`
10. remove `*.txt` and put `<fileName>.txt` name in above line
11. goto zeroSSL and click next step
12. verify domain
13. select server type ubuntu and click download certificates
14. now copy the file to server from your local `scp <ip>.zip  <user>@<ip>:/home/<user>/ssl/`
15. in server `unzip <ip>.zip`
16. in server `cat certificate.crt ca_bundle.crt >> nginx-certificate.crt`
17. in server `sudo cp nginx-certificate.crt /etc/ssl/certs/`
18. in server `sudo cp private.key /etc/ssl/private/nginx-private.key`
19. in server edit the nginx server config `sudo vim /etc/nginx/sites-available/default`
20. comment out first server {} block
21. comment out below line in second server {} block `return 301 https://$host$request_uri;`
22. comment in below lines
    ```
    #location ~ ^/.well-known/pki-validation(/?)(.*) {
    #                alias /var/www/html/<fileName>.txt;
    #       }
    ```
23. save the config file and run `sudo nginx -t`
24. `sudo nginx -s reload`
25. now goto the zeroSSL site and click check installation