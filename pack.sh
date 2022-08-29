#!/bin/bash

set -e

#
# 打包
#
./gradlew clean
./gradlew "assembleRelease"

#
# 查找APK
#
apks=()
files=$(find . -name "*.apk")
for f in ${files}; do
  apks[${#apks[*]}]="$f"
done

apk=${apks[0]}
file_name=$(basename "$apk")

echo
if [ -z "$apk" ]; then
  echo "APK Not Found!"
  exit 1
else
  echo "APK: $apk"
fi
echo

