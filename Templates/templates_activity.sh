#!/bin/bash
DIR=/Applications/Android\ Studio.app/Contents/plugins/android/lib/templates/activities/V2EXActivityTmp/
if [ -d "$DIR" ]; then
    printf '%s\n' "Template is exist,deleting it---->($DIR)"
    rm -rf "$DIR"
fi
    cp -r IronHideActivityTmp/ "$DIR"

