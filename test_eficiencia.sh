#!/bin/bash

echo "executing mvn package"
mvn package >/dev/null

#
function benchmark() {

  # calculate mean time
  sum=0
  count=10
  for ((i=0; i<$count; i++))
  do
    sum=$(($sum + $(java -jar target/cell-index-method-1.0-SNAPSHOT-jar-with-dependencies.jar -g -out data -L 20 -r 0.25 -rc 0.5 -N $1 -M $2)))
  done
  time=$(($sum/$count))
  echo "N:$1 M:$2 10 runs mean-time:${time}ms"
}

echo ""
N=50
benchmark $N 1
benchmark $N 2
benchmark $N 4
benchmark $N 8
benchmark $N 12
benchmark $N 16
benchmark $N 24
benchmark $N 32
benchmark $N 36
benchmark $N 39

echo ""
N=100
benchmark $N 1
benchmark $N 2
benchmark $N 4
benchmark $N 8
benchmark $N 12
benchmark $N 16
benchmark $N 24
benchmark $N 32
benchmark $N 36
benchmark $N 39

echo ""
N=200
benchmark $N 1
benchmark $N 2
benchmark $N 4
benchmark $N 8
benchmark $N 12
benchmark $N 16
benchmark $N 24
benchmark $N 32
benchmark $N 36
benchmark $N 39

echo ""
N=400
benchmark $N 1
benchmark $N 2
benchmark $N 4
benchmark $N 8
benchmark $N 12
benchmark $N 16
benchmark $N 24
benchmark $N 32
benchmark $N 36
benchmark $N 39
