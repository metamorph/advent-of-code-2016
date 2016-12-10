(ns day8.core
  (:gen-class))

(defn make-display [width height]
  (->> \.
       (repeat width)
       (vec)
       (repeat height)
       (vec)))

(defn rotate-column [display col n] display)
(defn rotate-row [display row n] display)
(defn rect [display width height] display)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
