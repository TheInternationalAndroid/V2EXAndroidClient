#!/usr/bin/env bash
echo $1

jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore ../ironhide.jks $1 ironhide

