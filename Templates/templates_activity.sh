#!/bin/bash
activityTmpDir="V2EXActivityTmp"
DIR="/Applications/Android Studio.app/Contents/plugins/android/lib/templates/activities/${activityTmpDir}/"
if [ -d "$DIR" ]; then
    printf '%s\n' "Template is exist,deleting it---->($DIR)"
    rm -rf "$DIR"
fi
    cp -r "${activityTmpDir}" "$DIR"

