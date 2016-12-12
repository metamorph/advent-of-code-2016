(ns day9.core
  (:gen-class))

(defn decompress [s]
  s)
(defn decompressed-length [s] (count (filter (complement #(= % \space)) (decompress s))))

(defn -main
  [& args]
  (decompressed-length (slurp (clojure.java.io/resource "input.txt"))))
