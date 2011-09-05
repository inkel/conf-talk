#! /usr/bin/env ruby

File.open("data.csv").each do |line|
  next if line.start_with? "#"

  data = line.split(",", 9)

  next if data[2] =~ /SALTA|CORDOBA/

  mujeres, varones = data[3].to_i, data[4].to_i

  puts data.first(3).reverse.join(", ") unless varones > mujeres
end
