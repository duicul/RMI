#!/bin/bash
sudo a2enmod userdir;
sudo service apache2 restart;
chmod -R 755 ~/public_html;
