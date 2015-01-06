#!/bin/bash - 

javadoc -sourcepath src/jvm/ -subpackages clojure -d 00javadoc -splitIndex -windowtitle 'Clojure language implementation' -doctitle 'Clojure language implementation' -J-Xmx500m
