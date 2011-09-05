#! /usr/bin/env python

import re

for line in open("data.csv"):
    if line.startswith("#"):
        continue

    data = line.split(",", 9)

    if re.search("SALTA|CORDOBA", data[2]):
        continue

    mujeres, varones = int(data[3]), int(data[4])

    if mujeres > varones:
        nombres = data[0:3]
        nombres.reverse()
        print ", ".join(nombres)
