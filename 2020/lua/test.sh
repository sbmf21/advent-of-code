#!/bin/bash
for d in ./Day*; do
  if [ -d "$d" ]; then
    cd "$d"

    IFS=' '
    read -a strarr <<< "$d"
    echo "Testing day ${strarr[1]}"

    ./test.lua

    code=$?
    if [ $code -ne 0 ]; then
      exit $code
    fi

    echo
    cd ..
  fi
done
