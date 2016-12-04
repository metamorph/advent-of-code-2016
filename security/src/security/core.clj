(ns security.core)

(defn enc->room "Parse a string to a room struct (name/sector-id/checksum)."
  [s]
  (let [[m a b c] (re-find #"([a-z-]+)-([0-9]+)\[([a-z]+)\]" s)]
    (if m
      {:name a :sector-id b :checksum c}
      (throw (IllegalArgumentException. (str "Not a valid room: " s))))))

(defn valid-room? [{:keys [name sector-id checksum]}] false)

(defn sum-sector-ids [input]
  (let [rooms (map enc->room input)
        valid (filter valid-room? rooms)]
    (reduce + 0 (map :sector-id))))
