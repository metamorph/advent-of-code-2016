(ns ipv7.core
  (:gen-class))

(defn abba?
  "Takes a four char string and checks if it's an ABBA."
  [[a b c d]]
  (and (= a d)
       (= b c)
       (not= a b)))
(defn support-tls? [ip] false)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
