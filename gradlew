#!/bin/sh
set -e
DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
GRADLE_VERSION="8.8"
CACHE="$HOME/.gradle/wrapper/dists/titan-gradle/$GRADLE_VERSION"
DIST="$CACHE/gradle-$GRADLE_VERSION"
if [ ! -x "$DIST/bin/gradle" ]; then
  mkdir -p "$CACHE"
  TMP="$CACHE/gradle.zip"
  if command -v curl >/dev/null 2>&1; then
    curl -L --fail --retry 3 -o "$TMP" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
  elif command -v wget >/dev/null 2>&1; then
    wget -O "$TMP" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
  else
    echo "Please install curl or wget." >&2
    exit 1
  fi
  unzip -q -o "$TMP" -d "$CACHE"
  rm -f "$TMP"
fi
exec "$DIST/bin/gradle" "$@"
