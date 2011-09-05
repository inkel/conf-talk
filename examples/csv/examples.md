!SLIDE

# Objetivo

Dado un archivo CSV con información sobre el último censo argentino, mostrar las localidades donde haya más mujeres que hombres, excepto en la provincias de Salta y Córdoba.

!SLIDE

# Restricciones

Utilizar en la medida de lo posible las instrucciones más básicas del lenguaje.

!SLIDE

Este ejemplo es bueno porque muestra como:

* abrir un archivo;
* iterar línea por línea;
* leer valores;
* convertir entre tipos;
* emitir información en STDOUT;
* comparar contra una expresión regular.

!SLIDE

El archivo que vamos a leer tiene la siguiente estructura:

<pre style="font-size: 0.7em;margin: 1em 0">
# DEPARTAMENTO,CABECERA,PROVINCIA,TOTAL_MUJERES,TOTAL_VARONES,TOTAL_VIVIENDAS,SUP__HAS_,PERIMETRO
SAN NICOLAS,SAN NICOLAS DE LOS ARROYOS,BUENOS AIRES,74395,71426,49785,666197340.76,120819.62
RAMALLO,RAMALLO,BUENOS AIRES,16627,16329,12122,1086482251.19,172090.96
PERGAMINO,PERGAMINO,BUENOS AIRES,54407,50515,41125,3046808952.09,262993.17
SAN PEDRO,SAN PEDRO,BUENOS AIRES,29879,29368,21564,1367221534.47,174180.19
COLON,COLON,BUENOS AIRES,12742,12133,9403,1012549579.59,167136.92
BARADERO,BARADERO,BUENOS AIRES,16624,16297,12480,1533608755.35,219580.04
ARRECIFES,ARRECIFES,BUENOS AIRES,15005,14022,10713,1232504679.73,167674.02
ROJAS,ROJAS,BUENOS AIRES,12023,11429,10491,2023404490.47,223270.44
CAPITAN SARMIENTO,CAPITAN SARMIENTO,BUENOS AIRES,7851,7471,5663,553816827.43,110794.03
...
</pre>

!SLIDE

# PHP

!SLIDE

@@@ php
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
@@@

!SLIDE

# Python

!SLIDE

@@@ python
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
@@@

!SLIDE

# Java

!SLIDE

@@@ java
    import java.io.*;

    public class ReadCSVExample
    {
        public static void main(String args[]) throws FileNotFoundException, IOException {
            FileInputStream fstream = new FileInputStream("data.csv");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int mujeres, varones;

            while ((line = br.readLine()) != null)   {
                if(line.startsWith("#"))
                    continue;

                String data[] = line.split(",", 9);

                if(data[2].matches("SALTA|CORDOBA"))
                    continue;

                mujeres = Integer.parseInt(data[3]);
                varones = Integer.parseInt(data[4]);

                if(mujeres > varones) {
                    System.out.println(data[2] + ", " + data[1] + ", " + data[0]);
                }
            }

            in.close();
        }
    }
    @@@

!SLIDE

No encontré manera de hacerlo más simple, se aceptan sugerencias.

!SLIDE

# Ruby

!SLIDE

@@@ ruby
    #! /usr/bin/env ruby

    File.open("data.csv").each do |line|
      next if line.start_with? "#"

      data = line.split(",", 9)

      next if data[2] =~ /SALTA|CORDOBA/

      mujeres, varones = data[3].to_i, data[4].to_i

      puts data.first(3).reverse.join(", ") unless varones > mujeres
    end
@@@

[rb]: https://twimg0-a.akamaihd.net/profile_images/1366384022/rubyconfar_fist.png
