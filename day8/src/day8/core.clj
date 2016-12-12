(ns day8.core
  (:gen-class))

(defn make-display [width height]
  (->> \.
       (repeat width)
       (vec)
       (repeat height)
       (vec)))

(defn rotate [coll n] (take (count coll)
                            (drop n
                                  (cycle (seq coll)))))
(defn rotate-column [display col n] display)
(defn rotate-row [display row n] (update display row rotate n))
(defn rect [display width height] display)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
