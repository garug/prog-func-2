(ns aula4.exercicio
  (:require [aula4.data :as data]))


;; retornar o vetor de clientes a chave `:full-name` adicionada nos mapas de clientes. (full-name deve ser a concatenacao do `:first-name` com o `:last-name`).
(defn full-name
  [data]
  (map (fn [{:keys [first-name last-name] :as cliente}] (assoc cliente :full-name (str first-name " " last-name))) data))

;; retornar clientes que tem `:age` maior que 35. Usar tanto `filter` quanto `remove`.
(defn above-35-filter
  [data]
  (filter (fn [cliente] (> (:age cliente) 35)) data))

(defn above-35-remove
  [data]
  (remove (fn [cliente] (<= (:age cliente) 35)) data))

;; retornar um mapa, tendo a chave do item como o id do produto e o valor sendo a quantidade total comprada desse item.
(defn products-map
  [data]
  (reduce (fn [acc item]
            (let [{key-item :item-id amount-item :amount } item
                  acc-value (get acc key-item 0)
                  new-value (+ amount-item acc-value)]
              (assoc acc key-item new-value)))
          {}
          data))

;; retornar um mapa de compras, onde a chave deve ser o id do cliente e o valor deve ser um vetor que contendo todas as compras do cliente.
(defn purchases-by-client
  [data]
  (reduce (fn [acc item]
            (let [key-item (:client-id item)
                  purchase (dissoc item :client-id) ; removendo clinet-id, gosto pessoal, evita repetição na saída
                  acc-value (get acc key-item [])
                  new-value (conj acc-value purchase)]
              (assoc acc key-item new-value)))
          {}
          data))

;; retornar o vetor de clientes com a chave `:purchases`. O valor dessa chave sera um vetor contendo mapas com `:item-id` e `:amount`.
(defn clients-with-purchases
  [clients purchases]
  (let [purchases-by-client (purchases-by-client purchases)]
    (map #(assoc % :purchases (get purchases-by-client (:id %) [])) clients)))

;; testes manuais
(comment
  (full-name data/clients)
  (above-35-filter data/cliens)
  (above-35-remove data/clients)
  (products-map data/purchases)
  (purchases-by-client data/purchases)
  (clients-with-purchases data/clients data/purchases))