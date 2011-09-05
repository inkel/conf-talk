#! /usr/bin/env bash

export TIME="%C:%K/%M RAM:%P CPU:%E (%Uu %Ss)"
OUT=$(mktemp)

for ex in 01.read-csv.{rb,php,py}; do
    /usr/bin/time ./$ex >/dev/null 2>>$OUT
done

/usr/bin/time java ReadCSVExample >/dev/null 2>>$OUT

#./01.read-csv.sh 2>&1 | column -t -s

column -ts: $OUT

rm $OUT
