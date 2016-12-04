(ns security.core)

(defn enc->room [s]
  s)

(defn valid-room? [{:keys [name sector-id checksum]}] false)

(defn sum-sector-ids [input]
  (let [rooms (map enc->room input)
        valid (filter valid-room? rooms)]
    (reduce + 0 (map :sector-id))))
