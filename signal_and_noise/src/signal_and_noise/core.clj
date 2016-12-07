(ns signal-and-noise.core
  (:gen-class))

(defn most-common-c [freqs]
  (first (first (sort-by #(* -1 (last %)) freqs))))

(defn error-corrected [messages]
  (let [seed (repeat (count (first messages)) [])
        cols (reduce (fn [lists chars] (mapv conj lists chars)) seed messages)
        freqs (map frequencies cols)]
    (apply str (map most-common-c freqs))))

(defn -main
  [& args]
  (let [lines (clojure.string/split-lines (slurp (clojure.java.io/resource "input.txt")))]
    (println (error-corrected lines))))
