#! /usr/bin/env php
<?php
$fp = fopen("data.csv", "r");

while($line = fgets($fp)) {
  if(strpos("#", $line) === 0) continue;

  $data = explode(",", $line, 9);

  if(preg_match("/SALTA|CORDOBA/", $data[2])) continue;

  $mujeres = $data[3];
  $varones = $data[4];

  if($mujeres > $varones) {
    echo implode(", ", array_reverse(array_slice($data, 0, 3))), "\n";
  }
}
