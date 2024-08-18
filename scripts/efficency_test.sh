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
    sum=$(($sum + $(java -jar target/cell-index-method-1.0-SNAPSHOT-jar-with-dependencies.jar -g -out data -L 20 -r 0.25 -rc 1 -N $1 -M $2)))
  done
  time=$(($sum/$count))
  echo "N:$1 M:$2 $count runs mean-time:${time}ms"
}

echo ""
N=400
benchmark $N 1
benchmark $N 2
benchmark $N 3
benchmark $N 4
benchmark $N 5
benchmark $N 6
benchmark $N 7
benchmark $N 8
benchmark $N 9
benchmark $N 10
benchmark $N 11
benchmark $N 12
benchmark $N 13
benchmark $N 14
benchmark $N 15

echo ""
N=800
benchmark $N 1
benchmark $N 2
benchmark $N 3
benchmark $N 4
benchmark $N 5
benchmark $N 6
benchmark $N 7
benchmark $N 8
benchmark $N 9
benchmark $N 10
benchmark $N 11
benchmark $N 12
benchmark $N 13
benchmark $N 14
benchmark $N 15

echo ""
N=1000
benchmark $N 1
benchmark $N 2
benchmark $N 3
benchmark $N 4
benchmark $N 5
benchmark $N 6
benchmark $N 7
benchmark $N 8
benchmark $N 9
benchmark $N 10
benchmark $N 11
benchmark $N 12
benchmark $N 13
benchmark $N 14
benchmark $N 15
