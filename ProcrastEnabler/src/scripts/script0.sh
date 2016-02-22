#!/bin/bash
#
url="http://hackertyper.com"
if which xdg-open > /dev/null
then
  xdg-open $url
elif which gnome-open > /dev/null
then
  gnome-open $url
elif which open > /dev/null
then
  open $url
fi

